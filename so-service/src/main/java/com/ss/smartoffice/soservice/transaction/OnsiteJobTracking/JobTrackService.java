package com.ss.smartoffice.soservice.transaction.OnsiteJobTracking;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.Processlog.ProcessLog;
import com.ss.smartoffice.shared.Processlog.ProcessLogService;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.soservice.transaction.job.JobAssetsRepository;
import com.ss.smartoffice.soservice.transaction.job.JobMilestoneRepository;
import com.ss.smartoffice.soservice.transaction.job.JobService;
import com.ss.smartoffice.soservice.transaction.job.JobTaskListRepo;
import com.ss.smartoffice.soservice.transaction.job.JobsRepository;

import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobAsset;
import com.ss.smartoffice.soservice.transaction.model.JobAssetDocs;
import com.ss.smartoffice.soservice.transaction.model.JobMilestone;
import com.ss.smartoffice.soservice.transaction.model.JobTaskList;
import com.ss.smartoffice.soservice.transaction.model.JobTaskType;
import com.ss.smartoffice.soservice.transaction.task.Task;
import com.ss.smartoffice.soservice.transaction.task.TaskRepo;
import com.ss.smartoffice.soservice.transaction.task.TaskService;

@RestController
@RequestMapping(path = "/transaction/job-tracks")
@Scope("prototype")
public class JobTrackService {
	
	@Autowired
	JobsRepository jobsRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	ProcessLogService processLogService;
	
	@Autowired
	JobMilestoneRepository jobMilestoneRepository;
	
	@Autowired
	JobTaskListRepo  jobTaskListRepo;
	
	@Autowired
	JobAssetsRepository jobAssetsRepository;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	TaskRepo taskRepo;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	
	@GetMapping("/")
	public Iterable<Job> getJob(Model model, Pageable pageable,
			@RequestParam(value = "jobCode",required = false) String jobCode,
			@RequestParam(value = "partnerId", required = false) String partnerId,
			@RequestParam(value = "mJobTypeId", required = false) String mJobTypeId,
			@RequestParam(value = "jobPlanStatus", required = false) String jobPlanStatus) throws SmartOfficeException {
		
		Page<Job> pages = getJobTracks(pageable,jobCode,partnerId,mJobTypeId, jobPlanStatus);
		  model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("JobDetails", pages.getContent());
	        return pages;
		

	}

	private Page<Job> getJobTracks(Pageable pageable, String jobCode, String partnerId, String mJobTypeId, String jobPlanStatus) {
		// TODO Auto-generated method stub
		try {
		return jobsRepository.findByTrackSummaries(pageable, jobCode, partnerId, mJobTypeId, jobPlanStatus);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : JobTrack Service - getJobTracks");
		}
		
	}
	
	@GetMapping("/{jobId}")
	public Optional<Job> getJobTrackById(@PathVariable(value = "jobId", required = false) Integer jobId) throws SmartOfficeException {
       try {
		return jobsRepository.findById(jobId);
       }catch (Exception e) {
		// TODO: handle exception
    	   e.getLocalizedMessage();
    		throw new SmartOfficeException("Exception Occured : JobTrackService - getJobTrackById");
	}
	
	}
	
//	Start function (startJob)
	@PatchMapping("/{jobId}/start-job")
			
				private Job jobStart(@PathVariable(value="jobId")String jobId)throws SmartOfficeException{
		Job job=new Job();
		try {			
		 job=jobsRepository.findById(Integer.valueOf(jobId)).get();
					job.setJobTrackStatus("STARTED");
					jobsRepository.save(job);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
    		throw new SmartOfficeException("Exception Occured : JobTrackService - jobStart");
		}
					return job;
					
				}
	
	//Received PTW 
	@PatchMapping("/{jobId}/received-ptw")
	private Job jobPtw(@RequestBody Job job,@PathVariable(value = "jobId")String jobId) throws SmartOfficeException{
		Job job1=new Job();
		try {
	
		job1=jobsRepository.findById(Integer.parseInt(jobId)).get();	
		job1.setJobTrackStatus("RECEIVED-PTW");
		job1.setReceivedPtwDt(LocalDateTime.now());
		job1.setReceivedPtwRemarks(job.getReceivedPtwRemarks());
		job1.setPtwDocId(job.getPtwDocId());		
		jobsRepository.save(job1);
		
		DocInfo docInfo = new DocInfo(); 
		docInfo.setDocId(job.getPtwDocId());
		DocMetadata docMetadata = new DocMetadata();
		docMetadata.setMdCode("job-id");
		docMetadata.setMdValue(job.getId().toString());
		List<DocMetadata> docMetadataList = new ArrayList<>();
		docMetadataList.add(docMetadata);
		docInfo.setMetadataList(docMetadataList);
		List<DocInfo> docInfos = new ArrayList<>();
		docInfos.add(docInfo);
		documentManagementHelper.checkInDocs(docInfos);
		ProcessLog processLog= new ProcessLog(null,"JOB TRACK SERVICE" , jobId, "RECEIVED-PTW ", "RECEIVED-PTW", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
    		throw new SmartOfficeException("Exception Occured : JobTrackService - jobPtw");
		}
		return job;
		
	}
	
	//Site Survey Completed
	@PatchMapping("/{jobId}/site-survey-completed")
	private Job jobSurvey(@RequestBody Job job,@PathVariable(value = "jobId")String jobId) throws SmartOfficeException{
		Job jobById= jobsRepository.findById(Integer.parseInt(jobId)).get();
		System.out.println(jobById);
		
		try {
		
			jobById.setJobTrackStatus("SITE-SURVEY-COMPLETED");
			jobById.setSiteSurveyCompDt(LocalDateTime.now());
			jobById.setSiteSurveyRemarks(job.getSiteSurveyRemarks());
			jobById.setSurveyDocId(job.getSurveyDocId());
			jobsRepository.save(jobById);
			
			DocInfo docInfo = new DocInfo(); 
			docInfo.setDocId(job.getSurveyDocId());
			DocMetadata docMetadata = new DocMetadata();
			docMetadata.setMdCode("job-id");
			docMetadata.setMdValue(job.getId().toString());
			List<DocMetadata> docMetadataList = new ArrayList<>();
			docMetadataList.add(docMetadata);
			docInfo.setMetadataList(docMetadataList);
			List<DocInfo> docInfos = new ArrayList<>();
			docInfos.add(docInfo);
			documentManagementHelper.checkInDocs(docInfos);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("	Exception Occured : JobTrack - jobSurvey");
		}
		ProcessLog processLog= new ProcessLog(null,"JOB TRACK SERVICE" , jobId, "SITE-SURVEY-COMPLETED ", "SITE-SURVEY-COMPLETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
		return jobById;
		
	}
	
	//workspace setup add
	@PatchMapping("/{jobId}/workspace-setup-save")
	private Job saveWorkspace(@RequestBody Job job,@PathVariable(value = "jobId")String jobId) throws SmartOfficeException{
		
		//Insertion
		
		if(job.getJobMilestones()!=null&&!job.getJobMilestones().isEmpty()) {
		for(JobMilestone jobMilestone:job.getJobMilestones()) {
			
			jobMilestone.setJobId(Integer.valueOf(jobId));
			
			jobMilestone =jobMilestoneRepository.save(jobMilestone);
			
		
				for (JobTaskList jobTaskList : jobMilestone.getJobTaskList()) {
				
					jobTaskList.setMilestoneId(jobMilestone.getId());
					jobTaskListRepo.save(jobTaskList);
					
					
				}
			
				
			
		
		}
		}else {
			throw new SmartOfficeException("Expection Occured : JobTrackService - jobSurvey");
		}
		
		return  jobsRepository.findById(Integer.parseInt(jobId)).get();
		
	}
	//MOM Draft Prepared
	@PatchMapping("/{jobId}/mom-draft-prepared")
	private Job jobMomDraftPrep(@RequestBody Job job,@PathVariable (value = "jobId" )String jobId) throws SmartOfficeException{
		Job jobById= jobsRepository.findById(Integer.valueOf(jobId)).get();
		try {
			jobById.setJobTrackStatus("MOM-DRAFT-PREPARED");
			jobById.setMomDraftPreparedDt(LocalDateTime.now());
			jobById.setMomDraftPreparedDocId(job.getMomDraftPreparedDocId());
			jobsRepository.save(jobById);
			
			DocInfo docInfo = new DocInfo(); 
			docInfo.setDocId(job.getMomDraftPreparedDocId());
			DocMetadata docMetadata = new DocMetadata();
			docMetadata.setMdCode("job-id");
			docMetadata.setMdValue(job.getId().toString());
			List<DocMetadata> docMetadataList = new ArrayList<>();
			docMetadataList.add(docMetadata);
			docInfo.setMetadataList(docMetadataList);
			List<DocInfo> docInfos = new ArrayList<>();
			docInfos.add(docInfo);
			documentManagementHelper.checkInDocs(docInfos);
			}catch (Exception e) {
				// TODO: handle exception
				e.getLocalizedMessage();
	    		throw new SmartOfficeException("Exception Occured : JobTrack - jobMomDraftPrep");
			}
		ProcessLog processLog= new ProcessLog(null,"JOB TRACK SERVICE" , jobId, "MOM-DRAFT-PREPARED ", "MOM-DRAFT-PREPARED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
			return job;
			
		}
	
	// MOM Draft Approved 
	@PatchMapping("/{jobId}/mom-draft-approved")
	private Job jobMomDraftAppr(@RequestBody Job job, @PathVariable(value = "jobId")String jobId) throws SmartOfficeException{
		Job jobById= jobsRepository.findById(Integer.valueOf(jobId)).get();
		try {
			jobById.setJobTrackStatus("MOM-DRAFT-APPROVED");
			jobById.setMomDraftApprovedDocId(job.getMomDraftApprovedDocId());
			jobById.setMomDraftApprovedDt(LocalDateTime.now());
			jobById.setMomDraftApprovedRemarks(job.getMomDraftApprovedRemarks());
			jobsRepository.save(jobById);
			
			DocInfo docInfo = new DocInfo(); 
			docInfo.setDocId(job.getMomDraftApprovedDocId());
			DocMetadata docMetadata = new DocMetadata();
			docMetadata.setMdCode("job-id");
			docMetadata.setMdValue(job.getId().toString());
			List<DocMetadata> docMetadataList = new ArrayList<>();
			docMetadataList.add(docMetadata);
			docInfo.setMetadataList(docMetadataList);
			List<DocInfo> docInfos = new ArrayList<>();
			docInfos.add(docInfo);
			documentManagementHelper.checkInDocs(docInfos);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			
    		throw new SmartOfficeException("Exception Occured : JobTrack - jobMomDraftAppr");
		}
		ProcessLog processLog= new ProcessLog(null,"JOB TRACK SERVICE" , jobId, "MOM-DRAFT-APPROVED ", "MOM-DRAFT-APPROVED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
		return job;
		
	}
	
	// Completed function
	@PatchMapping("/{jobId}/completed")
	private Job jobCompleted (@PathVariable(value = "jobId")String jobId) throws SmartOfficeException{
		Job job=new Job();
		try {
			job = jobsRepository.findById(Integer.valueOf(jobId)).get();
			job.setJobTrackStatus("COMPLETED");
			job.setJobCompleteDt(job.getJobCompleteDt());
			jobsRepository.save(job);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
    		throw new SmartOfficeException("Exception Occured : JobTrackService - JobCompleted");
		}
		ProcessLog processLog= new ProcessLog(null,"JOB TRACK SERVICE" , jobId, "COMPLETED", "COMPLETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
		return job;
		
	}

	
    //Reset Tasks
	@PatchMapping("/{jobId}/reset-tasks")
	private Job resetTasks(@PathVariable(value = "jobId")Integer jobId) throws SmartOfficeException{
//		Job job=new Job();
//		job = jobsRepository.findById(Integer.valueOf(jobId)).get();
//		List<JobMilestone> jobMilestone= new ArrayList<JobMilestone>();
//		jobMilestone=jobMilestoneRepository.findByJobId(Integer.valueOf(jobId));
		List<Task> task= taskRepo.findByJobId(jobId);
		if(task!=null&&!task.isEmpty()) {
			for(Task task2:task) {
				taskRepo.delete(task2.getId());
			}
		}
		
//		for(JobMilestone jobMilestone1
//				:job.getJobMilestones()) {
//			for(JobMilestone jobMilestone5:jobMilestone) {
//				if(jobMilestone5.getJobTaskList()!=null&&!jobMilestone5.getJobTaskList().isEmpty()) {
//					for(JobTaskList JobTaskList:jobMilestone5.getJobTaskList()) {
//					for(JobTaskType jobTaskType:job.getJobTaskTypes()) {
//						Task savedTask= new Task();
//						savedTask.setJobId(Integer.valueOf(jobId));
//						savedTask.setPartnerId(savedTask.getPartnerId());
//						job.setPartnerId(job.getPartnerId());
//						savedTask.setMilestoneId(savedTask.getMilestoneId());
//						savedTask.setTaskListId(JobTaskList.getId());
//						savedTask.setTaskTypeId(jobTaskType.getTaskTypeId());
//						savedTask.setTaskStatus("CREATED");
//						savedTask.setIsEnabled("Y");
//						List<Task>newTask= new ArrayList<Task>();
//						newTask.add(savedTask);
//						if(!newTask.isEmpty()) {
//							newTask = taskService.createTasks(newTask);
//					
//						}
//					     taskRepo.save(savedTask);
//					}
//					
//				}
//				
//				}
//			}
//		}
		Job job = jobsRepository.findById(Integer.valueOf(jobId)).get();
		return job;
		
	}
	
	
	@GetMapping("/{jobId}/job-asset")
	private Job getAllJobAssets(@PathVariable(value = "jobId")Integer jobId) throws SmartOfficeException{
		
		return jobService.getJobById(jobId);
	
	}
	
	@PatchMapping("/{jobId}/job-asset-save")
	private List<JobAsset> jobAssetsSave(@PathVariable(value = "jobId")String jobId,@RequestBody Job job) throws SmartOfficeException{
		List<JobAsset> jobAssetList = new ArrayList<JobAsset>();
		if(job.getJobAssets()!=null) {
			for(JobAsset jobAsset:job.getJobAssets()) {
				jobAsset.setJobId(jobId);
				JobAsset savedJobAsset= jobAssetsRepository.save(jobAsset);
//				if(savedJobAsset.getJobAssetDocs()!=null) {
//					System.out.println(savedJobAsset);
//					for(JobAssetDocs jobAssetDocs :savedJobAsset.getJobAssetDocs()) {
////						System.out.println(jobAssetDocs);	
//						if(jobAssetDocs.getDocId()!=null) {
//							DocInfo docInfo = new DocInfo(); 
//							docInfo.setDocId(jobAssetDocs.getDocId());
//							DocMetadata docMetadata = new DocMetadata();
//							docMetadata.setMdCode("job-id");
//							docMetadata.setMdValue(job.getId().toString());
//							DocMetadata docMetadata1 = new DocMetadata();
//							docMetadata1.setMdCode("asset-id");
//							docMetadata1.setMdValue(savedJobAsset.getId().toString());
//							List<DocMetadata> docMetadataList = new ArrayList<>();
//							docMetadataList.add(docMetadata);
//							docMetadataList.add(docMetadata1);
//							docInfo.setMetadataList(docMetadataList);
//							List<DocInfo> docInfos = new ArrayList<>();
//							docInfos.add(docInfo);
//							documentManagementHelper.checkInDocs(docInfos);
//						}
//						
//					}
					
//					todo -- have to fix document checkInDocs
					
//				}
				jobAssetList.add(savedJobAsset);
			}
		}
		return jobAssetList;
	}
	
	@DeleteMapping("/{jobId}/{jobAssetId}/job-asset-delete")
	private void jobAssetsDelete(@PathVariable(value = "jobId")int jobId,@PathVariable(value = "jobAssetId")String jobAssetId) throws SmartOfficeException{
		
		Job job = jobService.getJobById(jobId);
		if(job.getJobAssets()!=null) {
			
			for(JobAsset jobAsset:job.getJobAssets()) {
				if(jobAsset.getId()==Integer.parseInt(jobAssetId)) {				
					jobAssetsRepository.delete(Integer.parseInt(jobAssetId));
				}
			}
		}
		
	}
	
	@PatchMapping("/{jobId}/update-job-status")
	@Transactional
	private Job updateJobStatus(@PathVariable(value = "jobId")Integer jobId) throws SmartOfficeException{
	
		float totalTskCountInJob = 0;
		float completedTskCountInJob = 0;
		float totalTskCountInMilestone = 0;
		float completedTskCountInMilestone = 0;
		float totalTskCountInTaskList = 0;
		float completedTskCountInTaskList = 0;

		Job job = jobService.getJobById(jobId);
		
		if(job.getJobMilestones()!=null) {
			
			for(JobMilestone jobMilestone:job.getJobMilestones()) {
				totalTskCountInMilestone = 0;
				completedTskCountInMilestone = 0;
				
				if(jobMilestone.getJobTaskList()!=null) {
					
					for(JobTaskList jobTaskList:jobMilestone.getJobTaskList()) {
						totalTskCountInTaskList = 0;
						completedTskCountInTaskList = 0;
						
						if(jobTaskList.getTasks()!=null) {
							
							for(Task task:jobTaskList.getTasks()) {
								totalTskCountInTaskList =totalTskCountInTaskList+1;
								if(task.getTaskStatus().equals("COMPLETED")) {
									completedTskCountInTaskList = completedTskCountInTaskList+1;
									
								}
								
							}
							
						}
//						System.out.println("TaskList"+jobTaskList.getTaskListName());
//						System.out.println("totalTskCountInTaskList"+totalTskCountInTaskList);
//						System.out.println("completedTskCountInTaskList"+completedTskCountInTaskList);
						if(completedTskCountInTaskList!=0) {
//							System.out.println((completedTskCountInTaskList/totalTskCountInTaskList));
//							System.out.println((completedTskCountInTaskList/totalTskCountInTaskList)*100);
							jobTaskList.setProgress((int)((completedTskCountInTaskList/totalTskCountInTaskList) * 100) );
							jobTaskListRepo.save(jobTaskList);
						}
						
						totalTskCountInMilestone = totalTskCountInMilestone + totalTskCountInTaskList;
						completedTskCountInMilestone = completedTskCountInMilestone + completedTskCountInTaskList;
					}
				}
				if(completedTskCountInMilestone!=0) {
				
					jobMilestone.setProgress((int)((completedTskCountInMilestone/totalTskCountInMilestone) * 100));
					jobMilestoneRepository.save(jobMilestone);
				}
				
				totalTskCountInJob = totalTskCountInJob + totalTskCountInMilestone;
				completedTskCountInJob = completedTskCountInJob + completedTskCountInMilestone;


			}
			if(completedTskCountInJob!=0) {
				job.setProgress((int)(completedTskCountInJob/totalTskCountInJob*100));
				jobService.updateJobById(job);
			}
			
			job.setJobTrackStatus("COMPLETED");
			jobsRepository.save(job);
			
		}
		
		return job;
	}
		
	
}


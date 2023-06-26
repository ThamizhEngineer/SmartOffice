package com.ss.smartoffice.soservice.transaction.job;

import java.util.ArrayList;
import java.util.List;

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

import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobAsset;
import com.ss.smartoffice.soservice.transaction.model.JobDoc;
import com.ss.smartoffice.soservice.transaction.model.JobMaterial;
import com.ss.smartoffice.soservice.transaction.model.JobTip;

@RestController
@RequestMapping(path = "transaction/job-reports")
@Scope("prototype")
public class JobReportService {

	@Autowired
	JobsRepository jobsRepository;

	@Autowired
	JobTipRepository jobTipRepository;

	@Autowired
	JobDocRepository jobDocRepository;

	@Autowired
	JobMaterialRepository jobMaterialRepository;

	@Autowired
	JobService jobService;

	@Autowired
	DocumentManagementHelper documentManagementHelper;

	@GetMapping
	public Iterable<Job> getJobsForReport(Model model, Pageable pageable,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "partnerId", required = false) String partnerId,
			@RequestParam(value = "endClientId", required = false) String endClientId,
			@RequestParam(value = "mJobTypeId", required = false) String mJobTypeId,
			@RequestParam(value = "jobPlanStatus", required = false) String jobPlanStatus) throws SmartOfficeException {
		Page<Job> pages = getJobDetails(pageable, partnerId, jobCode, endClientId, mJobTypeId, jobPlanStatus);
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("bundledetails", pages.getContent());
		return pages;

	}

	private Page<Job> getJobDetails(Pageable pageable, String partnerId, String jobCode, String endClientId,
			String mJobTypeId, String jobPlanStatus) {

		try {
			return jobsRepository.findByJobReport(pageable, jobCode, partnerId, endClientId, mJobTypeId, jobPlanStatus);
		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured :JobFeedback Service - getJobDetails");
		}

	}

	@GetMapping("/{id}")
	public Job getJobById(@PathVariable(value = "id") int id) throws SmartOfficeException {

		Job job = jobService.getJobById(id);
		try {
			job.setSaleOrder(null);
			job.setJobPlanTaskTypes(null);
			job.setJobPlanEmps(null);
			job.setJobPlanMaterials(null);
			job.setJobPlanProfiles(null);
			job.setJobPlanStatus(null);
			job.setJobPlanAssets(null);
			job.setJobProfiles(null);
			job.setJobAccomodations(null);
			job.setJobMilestones(null);
			job.setJobTravels(null);
			job.setJobAssets(null);
			job.setJobPlanAssets(null);
			job.setJobTaskTypes(null);

			return job;
		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured :JonReport Service - getJobEmployeeByJobId");

		}

	}

	@PatchMapping()
	public Job updateJobReport(@RequestBody Job job) {

		Job savedJob = jobService.getJobById(job.getId());
		savedJob.setInternalFeedback(job.getInternalFeedback());
		savedJob.setSummary(job.getSummary());
//		if (job.getDocAttachedReport1() != null) {
//			savedJob.setDocAttachedReport1(job.getDocAttachedReport1());
//		}
//		if (job.getDocAttachedReport2() != null) {
//			savedJob.setDocAttachedReport2(job.getDocAttachedReport2());
//		}
//		if (job.getDocAttachedReport3() != null) {
//			savedJob.setDocAttachedReport3(job.getDocAttachedReport3());
//		}
//		if (job.getDocAttachedReport4() != null) {
//			savedJob.setDocAttachedReport4(job.getDocAttachedReport4());
//		}
//		if (job.getDocAttachedReport5() != null) {
//			savedJob.setDocAttachedReport1(job.getDocAttachedReport5());
//		}
//		checkInDocs(job);
		
		savedJob = jobService.updateJobById(savedJob);
		
		if (job.getJobTips() != null && !job.getJobTips().isEmpty()) {
			for (JobTip jobTip : job.getJobTips()) {
				if (jobTip.getJobId().equals(job.getId().toString())) {
					jobTipRepository.save(jobTip);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}

			}
		}

		if (job.getJobMaterials() != null && !job.getJobMaterials().isEmpty()) {
			for (JobMaterial jobMaterial : job.getJobMaterials()) {
				if (jobMaterial.getJobId().equals(job.getId().toString())) {
					jobMaterialRepository.save(jobMaterial);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}

			}
		}

		if (job.getJobDocs() != null && !job.getJobDocs().isEmpty()) {
			checkInDocs(job);
			for (JobDoc jobDoc : job.getJobDocs()) {
				if (jobDoc.getJobId().equals(job.getId().toString())) {
					
					jobDocRepository.save(jobDoc);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}

			}
		}

		if (job.getJobMaterials() != null && !job.getJobMaterials().isEmpty()) {
			for (JobMaterial jobMaterial : job.getJobMaterials()) {
				if (jobMaterial.getJobId().equals(job.getId().toString())) {
					jobMaterialRepository.save(jobMaterial);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}

			}
		}
		return savedJob;

	}

	private void checkInDocs(Job job) {
		
		List<DocInfo> docInfos = new ArrayList<>();

		if (job.getJobDocs() != null && !job.getJobDocs().isEmpty()) {
			
			for (JobDoc jobDoc : job.getJobDocs()) {
				if (jobDoc.getDocId()!=null) {
					
					
						
						DocInfo docInfo = new DocInfo();
						docInfo.setDocId(jobDoc.getDocId());
						DocMetadata docMetadata = new DocMetadata();
						docMetadata.setMdCode("job-id");
						docMetadata.setMdValue(job.getId().toString());
						List<DocMetadata> docMetadataList = new ArrayList<>();
						docMetadataList.add(docMetadata);
						
						if(jobDoc.getDocTypeCode().equals("JOB-ASSET-BEFORE") || jobDoc.getDocTypeCode().equals("JOB-ASSET-AFTER") ) {
							DocMetadata docMetadata1 = new DocMetadata();
							docMetadata1.setMdCode("asset-id");
							docMetadata1.setMdValue(job.getId().toString());
							docMetadataList.add(docMetadata1);
						}
						if(jobDoc.getDocTypeCode().equals("JOB-DAILY-STATUS") ) {
							DocMetadata docMetadata2 = new DocMetadata();
							docMetadata2.setMdCode("memployee-id");
							docMetadata2.setMdValue(job.getId().toString());
							docMetadataList.add(docMetadata2);
						}
						docInfo.setMetadataList(docMetadataList);
						docInfos.add(docInfo);
					
				
				} else {
					throw new SmartOfficeException("Invalid Job Doc Id");
				}

			}
		}
	
	
			documentManagementHelper.checkInDocs(docInfos);
		

	}
	
	
	@DeleteMapping("/{jobId}/{jobDocId}/job-docs-delete")
	private void jobDocsDelete(@PathVariable(value = "jobId")int jobId,@PathVariable(value = "jobDocId")String jobDocId) throws SmartOfficeException{
		
		Job job = jobService.getJobById(jobId);
		if(job.getJobDocs()!=null) {
			
			for(JobDoc jobDoc : job.getJobDocs()) {
				if(jobDoc.getId()==Integer.parseInt(jobDocId)) {				
					jobDocRepository.delete(Integer.parseInt(jobDocId));
				}
			}
		}
		
	}
}

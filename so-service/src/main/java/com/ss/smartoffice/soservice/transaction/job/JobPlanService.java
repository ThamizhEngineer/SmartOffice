package com.ss.smartoffice.soservice.transaction.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.Processlog.ProcessLog;
import com.ss.smartoffice.shared.Processlog.ProcessLogService;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthClient;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.chat.ChatGroup;
import com.ss.smartoffice.shared.model.chat.ChatGroupUser;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.JobTypeService.JobType;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeMaterial;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeMaterialRepository;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeProfile;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeProfileRepository;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeRepository;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeTaskType;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeTaskTypeRepository;
import com.ss.smartoffice.soservice.master.employeeprofile.ProfileService;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;
import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobEmployee;
import com.ss.smartoffice.soservice.transaction.model.JobMaterial;
import com.ss.smartoffice.soservice.transaction.model.JobPlanMaterial;
import com.ss.smartoffice.soservice.transaction.model.JobPlanProfile;
import com.ss.smartoffice.soservice.transaction.model.JobPlanTaskType;
import com.ss.smartoffice.soservice.transaction.model.JobProfile;
import com.ss.smartoffice.soservice.transaction.model.JobTaskType;
import com.ss.smartoffice.soservice.transaction.model.SaleOrder;
import com.ss.smartoffice.soservice.transaction.model.JobPlanEmp;
import com.ss.smartoffice.soservice.transaction.saleorder.SaleOrderRepository;

@RestController
@RequestMapping(path = "transaction/job-plans")
@Scope("prototype")
public class JobPlanService {
	
	@Value("${chat-grp.url}")
	private String chatGroupUrl;
	
	@Autowired
	JobsRepository jobsRepository;

	@Autowired
	JobProfileRepository jobProfileRepository;
	@Autowired
	JobEmployeeRepository jobEmployeeRepository;
	@Autowired
	JobTaskTypeRepository jobTaskTypeRepository;
	@Autowired
	JobMaterialRepository jobMaterialRepository;
	@Autowired
	DocInfoRepository docInfoRepository;
	@Autowired
	JobDocRepository jobDocRepository;
	@Autowired
	JobTypeRepository jobTypeRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	JobPlanEmployeeRepository jobPlanEmployeeRepository;
	@Autowired
	ProcessLogService processLogService;
	@Autowired
	JobPlanMaterialRepository jobPlanMaterialRepository;
	@Autowired
	JobPlanTaskTypeRepository jobPlanTaskTypeRepository;
	@Autowired
	JobPlanProfileRepository jobPlanProfileRepository;

	@Autowired
	JobPlanTeamRepository jobPlanTeamRepository;

	@Autowired
	JobPlanEmpSkillRepository jobPlanEmpSkillRepository;

	@Autowired
	JobPlanEmpCommitmentRepository jobPlanEmpCommitmentRepository;

	@Autowired
	JobPlanAssetsRepository jobPlanTestKitRepository;

	@Autowired
	JobTypeMaterialRepository jobTypeMaterialRepository;

	@Autowired
	JobTypeTaskTypeRepository jobTypeTaskTypeRepository;

	@Autowired
	JobTypeProfileRepository jobTypeProfileRepository;

	@Autowired
	SequenceGenerationService sequenceGenerationService;

	@Autowired
	EventPublisherService eventPublisherService;

	@Autowired
	SaleOrderRepository saleOrderRepository;
	
	@Autowired
	AuthUserRepository authUserRepository;

	@Autowired
	JobService jobService;
	@Autowired
	ProfileService profileService;

	@GetMapping
	public Iterable<Job> getJob(Model model, Pageable pageable,
			@RequestParam(value = "partnerId", required = false) String partnerId,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "jobName", required = false) String jobName,
			@RequestParam(value = "jobTypeId", required = false) String jobTypeId) throws SmartOfficeException {
		try {
			return jobsRepository.findByJobPlan(partnerId, jobCode, jobTypeId,jobName);
		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : JobPlanService - getJobDetails");
		}
	}

	@GetMapping("/{jobId}")
	public Job getJobPlanById(@PathVariable(value = "jobId", required = false) Integer jobId)
			throws SmartOfficeException {
		try {
			return jobService.getJobById(jobId);
		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : JobPlanService - getJobPlanById");
		}
	}

public String CreateJobCode(String saleOrderId)throws SmartOfficeException{
		
		List<Job> jobs = jobsRepository.findBysaleOrderId(saleOrderId);
		SaleOrder saleOrder = saleOrderRepository.findById(Integer.parseInt(saleOrderId)).get();
		String code=saleOrder.getSaleOrderCode();
		if(jobs.isEmpty()) {
			return code+"-01";
		}else {
			int seqCode = jobs.get(0).getSeqNumber()+1;
			for(Job job:jobs) {
				job.setSeqNumber(seqCode);
			}			
			int length = String.valueOf(jobs.get(0).getSeqNumber()).length();			
			if(length==1) {
				return code+"-0"+jobs.get(0).getSeqNumber();	
			}else {
				return code+"-"+jobs.get(0).getSeqNumber();
			}
				
		}				
		
	}
	
	@PostMapping("/start-job-plan")
	public Job startJobPlan(@RequestBody Job job) throws SmartOfficeException {
		Job savedJob = new Job();
		try {
			job.setPartnerId(job.getPartnerId());
			job.setSaleOrderId(job.getSaleOrderId());
			job.setProjId(job.getProjId());
			job.setJobName(job.getJobName());
			job.setmJobTypeId(job.getmJobTypeId());
			job.setJobCode(CreateJobCode(job.getSaleOrderId()));
			job.setStartDt(job.getStartDt());
			job.setEndDt(job.getEndDt());
			System.out.println(job);
			savedJob = jobService.addJob(job);
			System.out.println(savedJob);

			JobType jobType = jobTypeRepository.findById(Integer.parseInt(job.getmJobTypeId())).get();
			List<JobPlanMaterial> jobPlanMaterials = new ArrayList<JobPlanMaterial>();

			for (JobTypeMaterial jobTypeMaterial : jobType.getJobTypeMaterials()) {
				JobPlanMaterial jobPlanMaterial = new JobPlanMaterial();
				jobPlanMaterial.setJobId(job.getId().toString());
				jobPlanMaterial.setMaterialId(jobTypeMaterial.getMaterialId());
				jobPlanMaterial.setTaskTypeOrder(jobTypeMaterial.getTaskTypeOrder());
				jobPlanMaterial.setMaterialTypeOrder(jobTypeMaterial.getMaterialTypeOrder());
				jobPlanMaterial.setMaterialQuantity(jobTypeMaterial.getMaterialQuantity());
				jobPlanMaterial.setIsEnabled(jobTypeMaterial.getIsEnabled());
				jobPlanMaterial.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
				jobPlanMaterials.add(jobPlanMaterial);
				job.setJobPlanMaterials(jobPlanMaterials);
				jobService.addUpdateJobPlanChilds(job, job.getId());
			}

			List<JobPlanTaskType> jobPlanTaskTypes = new ArrayList<JobPlanTaskType>();
			for (JobTypeTaskType jobTypeTaskType : jobType.getJobTypeTaskTypes()) {
				JobPlanTaskType jobPlanTaskType = new JobPlanTaskType();
				jobPlanTaskType.setJobId(job.getId().toString());
				jobPlanTaskType.setTaskTypeId(jobTypeTaskType.getTaskTypeId());
				jobPlanTaskType.setTaskTypeOrder(jobTypeTaskType.getTaskTypeOrder());
				jobPlanTaskType.setWeightage(jobTypeTaskType.getWeightage());
				jobPlanTaskType.setIsEnabled(jobTypeTaskType.getIsEnabled());
				jobPlanTaskType.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
				jobPlanTaskTypes.add(jobPlanTaskType);
				job.setJobPlanTaskTypes(jobPlanTaskTypes);
				jobService.addUpdateJobPlanChilds(job, job.getId());
			}
			List<JobPlanProfile> jobPlanProfiles = new ArrayList<JobPlanProfile>();
			for (JobTypeProfile jobTypeProfile : jobType.getJobTypeProfile()) {
				JobPlanProfile jobPlanProfile = new JobPlanProfile();
				jobPlanProfile.settJobId(job.getId());
				jobPlanProfile.setProfileId(jobTypeProfile.getmProfileId());
				jobPlanProfile.setHeadCount(jobTypeProfile.getHeadCount());
				jobPlanProfile.setNotes(jobTypeProfile.getNotes());
				jobPlanProfile.setIsEnabled(jobTypeProfile.getIsEnabled());
				jobPlanProfiles.add(jobPlanProfile);
				job.setJobPlanProfiles(jobPlanProfiles);
				jobService.addUpdateJobPlanChilds(job, job.getId());
			}
			
			ProcessLog processLog = new ProcessLog(null, "JOB PLAN SERVICE", String.valueOf(job.getId()),
					"START JOB PLAN", "START JOB PLAN", null, String.valueOf(job.getId()),
					commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
			return jobService.getJobById(savedJob.getId());

		} catch (Exception e) {
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : JobPlanService - startJobPlan");
		}
	}

	@PatchMapping("/{jobId}/start-resourcing")
	@Transactional
	public List<JobPlanEmp> startResourcing(@PathVariable(value = "jobId", required = false) Integer jobId,
			@RequestBody Job job) throws SmartOfficeException {
		List<JobPlanEmp> createJobPlanTeam = new ArrayList<JobPlanEmp>();
//		Job job = jobService.getJobById(jobId);
		Job savedJob = jobService.addUpdateJobPlanChilds(job, jobId);

		try {

			List<JobPlanEmp> jobPlanEmps = jobPlanTeamRepository.findByJobId(jobId);
			for (JobPlanEmp jobPlanEmp : jobPlanEmps) {
				System.out.println(jobPlanEmp);
				jobPlanTeamRepository.delete(jobPlanEmp.getId());
				System.out.println("1");
//			JobPlanEmpSkill jobPlanEmpSkill = new JobPlanEmpSkill();
//			jobPlanEmpSkill.settJobPlanEmpId(jobPlanEmp.getId().toString());
				jobPlanEmpSkillRepository.deleteBytJobPlanEmpId(jobPlanEmp.getId().toString());
				System.out.println("2");
				jobPlanEmpCommitmentRepository.deleteByJobPlanEmpId(jobPlanEmp.getId().toString());
				System.out.println("3");
			}

//		JobType jobType = jobTypeRepository.findById(Integer.valueOf(job.getmJobTypeId())).get();
//		for (JobTypeProfile jobTypeProfile :jobType.getJobTypeProfile() ) {
//			for (int i = 1; i <= Integer.parseInt(jobTypeProfile.getHeadCount()); i++) {
//				JobPlanEmp jobPlanEmp= new JobPlanEmp();
//				jobPlanEmp.setProfileId(jobTypeProfile.getmProfileId());
//				jobPlanEmp.setJobId(jobId);
//				createJobPlanTeam.add(jobPlanEmp);
//				jobPlanTeamRepository.saveAll(createJobPlanTeam);
//
//			}
//			
//		}

			for (JobPlanProfile jobPlanProfile : savedJob.getJobPlanProfiles()) {
				for (int i = 1; i <= Integer.parseInt(jobPlanProfile.getHeadCount()); i++) {
					JobPlanEmp jobPlanEmp = new JobPlanEmp();
					jobPlanEmp.setProfileId(jobPlanProfile.getProfileId());
					jobPlanEmp.setJobId(jobId);
					jobPlanEmp.setStartDt(job.getStartDt());
					jobPlanEmp.setEndDt(job.getEndDt());
					createJobPlanTeam.add(jobPlanEmp);

				}

			}
			jobPlanTeamRepository.saveAll(createJobPlanTeam);
			ProcessLog processLog = new ProcessLog(null, "JOB PLAN SERVICE", String.valueOf(job.getId()),
					"START RESOURCING", "START RESOURCING", null, String.valueOf(job.getId()),
					commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
			return createJobPlanTeam;

		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : JobPlanService - startResourcing");
		}

//		return createJobPlanTeam;

	}

	public Job startJob(Integer id, Job job) throws SmartOfficeException {
		// Creating Job memployee based on Job Plan Emp

		List<JobEmployee> jobEmployees = new ArrayList<JobEmployee>();
		List<JobPlanEmp> jobPlanEmps = job.getJobPlanEmps();

		for (JobPlanEmp jobPlanEmp : jobPlanEmps) {

			JobEmployee jobEmployee = new JobEmployee();
			jobEmployee.settJobId(String.valueOf(id));
			jobEmployee.setEmployeeId(Integer.valueOf(jobPlanEmp.getEmployeeId()));
			jobEmployee.setExpStartDt(jobPlanEmp.getStartDt());
			jobEmployee.setExpEndDt(jobPlanEmp.getEndDt());
			jobEmployee.settJobProfileId(Integer.parseInt(jobPlanEmp.getProfileId()));
			jobEmployees.add(jobEmployee);
			jobEmployeeRepository.save(jobEmployee);
			jobEmployees.add(jobEmployee);
			job.setJobEmployees(jobEmployees);

			jobService.addUpdateJobPlanChilds(job, id);

		}

		List<JobMaterial> jobMaterials = new ArrayList<JobMaterial>();
		List<JobPlanMaterial> jobPlanMaterials = jobPlanMaterialRepository.findByJobId(String.valueOf(id));

		for (JobPlanMaterial jobPlanMaterial : jobPlanMaterials) {

			JobMaterial jobMaterial = new JobMaterial();
			jobMaterial.setJobId(String.valueOf(id));
			jobMaterial.setMaterialQuantity(jobPlanMaterial.getMaterialQuantity());
			jobMaterial.setMaterialTypeName(jobPlanMaterial.getMaterialName());
			jobMaterial.setMaterialTypeOrder(jobPlanMaterial.getMaterialCode());
			jobMaterials.add(jobMaterial);
			jobMaterialRepository.save(jobMaterial);

			job.setJobMaterials(jobMaterials);
			jobService.addUpdateJobPlanChilds(job, id);
		}
		List<JobTaskType> jobTaskTypes = new ArrayList<JobTaskType>();
		List<JobPlanTaskType> jobPlanTaskTypes = jobPlanTaskTypeRepository.findByJobId(String.valueOf(id));

		for (JobPlanTaskType jobPlanTaskType : jobPlanTaskTypes) {
			JobTaskType jobTaskType = new JobTaskType();
			jobTaskType.setJobId(String.valueOf(id));
			jobTaskType.setTaskTypeId(jobPlanTaskType.getTaskTypeId());
			jobTaskType.setTaskTypeOrder(jobPlanTaskType.getTaskTypeOrder());
			jobTaskType.setWeightage(jobPlanTaskType.getWeightage());
			jobTaskTypeRepository.save(jobTaskType);
			jobTaskTypes.add(jobTaskType);
			job.setJobTaskTypes(jobTaskTypes);
			jobService.addUpdateJobPlanChilds(job, id);
		}

		List<JobProfile> jobProfiles = new ArrayList<JobProfile>();
		List<JobPlanProfile> jobPlanProfiles = jobPlanProfileRepository.findByTJobId(id);

		for (JobPlanProfile jobPlanProfile : jobPlanProfiles) {
			JobProfile jobProfile = new JobProfile();
			jobProfile.settJobId(String.valueOf(id));
			jobProfile.setHeadCount(Integer.valueOf(jobPlanProfile.getHeadCount()));
			jobProfile.setProfileName(jobPlanProfile.getProfileName());
			jobProfile.setProfileId(Integer.valueOf(jobPlanProfile.getProfileId()));

			jobProfileRepository.save(jobProfile);
			jobProfiles.add(jobProfile);
			job.setJobProfiles(jobProfiles);

			jobService.addUpdateJobPlanChilds(job, id);

		}

		return job;

	}

	@PatchMapping("/{jobId}/request-job-confirmation")
	public Job requestJobConfirmation(@PathVariable(value = "jobId") String jobId) throws SmartOfficeException {
		Job job = jobService.getJobById(Integer.valueOf(jobId));
		try {

			startJob(Integer.parseInt(jobId), job);
			Event event = new Event();
			event.setName(Event.EventTypes.JobConfirmationEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			List<NotificationKey> notificationKeys = new ArrayList<>();
			NotificationKey notificationKey = new NotificationKey();
			notificationKey.setSendEmail(job.getClientEmailId());
			event.setNotificationKeys(notificationKeys);
			eventPublisherService.pushEvent(event);

			ProcessLog processLog = new ProcessLog(null, "JOB PLAN SERVICE", String.valueOf(job.getId()),
					"REQUEST JOB CONFIRMATION", "REQUEST JOB CONFIRMATION", null, jobId,
					commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);

		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
//			throw new SmartOfficeException("Exception Occured : JobPlanService - requestJobConformation");
		}
		return job;

	}

	@PutMapping("/{jobId}/confirm")
	public Job confirmJobPlan(@PathVariable(value = "jobId") Integer jobId) throws SmartOfficeException {
		Job job = jobsRepository.findById(jobId).get();
		try {
			job = jobService.getJobById(jobId);
			job.setJobPlanStatus("COMPLETED");
			jobService.addJob(job);

			startJob(jobId, job);

		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
//			throw new SmartOfficeException("Exception Occured : JobPlanService - confirmJobPlan");
		}

		return job;

	}

	@PatchMapping("/{jobId}/save-job-plan-childs")
	public Job addUpdateJobPlanChilds(@RequestBody Job job, @PathVariable(value = "jobId") Integer jobId)
			throws SmartOfficeException {

		return jobService.addUpdateJobPlanChilds(job, jobId);

	}

	@PatchMapping("/{jobId}/save-job")
	public Job saveJob(@PathVariable(value = "jobId") String jobId, @RequestBody Job job) throws SmartOfficeException {
		Job job1 = new Job();
		try {
			job1 = jobService.getJobById(Integer.parseInt(jobId));
			job1.setJobName(job.getJobName());
			job1.setProjId(job.getProjId());
			job1.setStartDt(job.getStartDt());
			job1.setEndDt(job.getEndDt());
			job1.setClientRemarks(job.getClientRemarks());

			jobService.updateJobById(job1);

//		startJobPlan(job);
			ProcessLog processLog = new ProcessLog(null, "JOB PLAN SERVICE", String.valueOf(job.getId()), "JOB CONFIRM",
					"JOB CONFIRM", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(),
					job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);

		} catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured : JobPlanService - confirmJobPlan");
		}
		createChatGroup(job);
		return job1;

	}
	
	public void createChatGroup(Job job) throws SmartOfficeException{
		
		List<ChatGroupUser> chatGroupUsers = new ArrayList<ChatGroupUser>();
		ChatGroup chatGrp = new ChatGroup();
		
		for(JobPlanEmp jobEmp:job.getJobPlanEmps()) {
			ChatGroupUser chatGrpUsr = new ChatGroupUser();
		List<AuthUser> authUsers=authUserRepository.findByEmployeeId(jobEmp.getEmployeeId());
		if(!authUsers.isEmpty()) {
			chatGrpUsr.setAuthUserId(authUsers.get(0).getId());
			chatGroupUsers.add(chatGrpUsr);
		}
		}
		List<AuthClient> authClients = authUserRepository.authClientUserByPartnerID(job.getPartnerId());
		if(!authClients.isEmpty()) {
			for(AuthClient authclient:authClients) {
				ChatGroupUser chatGrpUsr = new ChatGroupUser();
				chatGrpUsr.setAuthUserId(authclient.getAuthId());
				chatGroupUsers.add(chatGrpUsr);
			}
		}
		chatGrp.setGroupId(job.getId());
		chatGrp.setGroupName(job.getJobName());
		chatGrp.setGroupDesc(job.getJobName());
		chatGrp.setChatGroupUsers(chatGroupUsers);
		chatGrp.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		chatGrp.setGroupType("JOB Plan");
		System.out.println(chatGrp);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
		HttpEntity<ChatGroup> request = new HttpEntity<ChatGroup>(chatGrp);
		System.out.println(request);
		commonUtils.getRestTemplate().exchange(chatGroupUrl,HttpMethod.POST, request, ChatGroup.class);
	}

	@DeleteMapping("/{jobId}/{lineId}/delete-job-plan-lines")
	public void deleteJobPlanLines(@PathVariable(value = "jobId") Integer jobId,
			@PathVariable(value = "lineId") Integer lineId,
			@RequestParam(value = "line-type", required = false) String lineType) throws SmartOfficeException {

//		jobService.deleteJobPlanLines(job, id);
		if (lineType.equals("job-plan-material")) {
			jobPlanMaterialRepository.delete(lineId);
		}
		if (lineType.equals("job-plan-profile")) {
			jobPlanProfileRepository.delete(lineId);

		}
		if (lineType.equals("job-plan-task-type")) {
			jobPlanTaskTypeRepository.delete(lineId);
		}
		if (lineType.equals("job-plan-emp")) {
			jobPlanTeamRepository.delete(lineId);
		}

	}
	
	@DeleteMapping("/{id}")
	public void deleteProjectById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		jobsRepository.deleteById(id);
	}
	
	@GetMapping("/job-emp/{empId}")
	public List<JobPlanEmp> findJobPlanEmp(@PathVariable(value = "empId")String empId)throws SmartOfficeException{
		return jobPlanEmployeeRepository.findByEmployee(empId);
	}
	
	
}

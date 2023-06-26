package com.ss.smartoffice.soservice.transaction.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobEmployee;

@RestController
@RequestMapping(path = "transaction/job-feedbacks")
@Scope("prototype")
public class JobFeedbackService {

	@Autowired
	JobEmployeeRepository jobEmployeeRepository;
	
	@Autowired
	JobService jobService;

	@GetMapping
	public Iterable<Job> getJobsForFeedback(Model model, Pageable pageable,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "partnerId", required = false) String partnerId,
			@RequestParam(value = "endClientId", required = false) String endClientId,
			@RequestParam(value = "mJobTypeId", required = false) String mJobTypeId,
			@RequestParam(value = "jobTrackStatus", required = false) String jobTrackStatus) throws SmartOfficeException {
		Page<Job> pages = getJobDetails(pageable, partnerId,jobCode, endClientId, mJobTypeId, jobTrackStatus);
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("bundledetails", pages.getContent());
		return pages;

	}

	private Page<Job> getJobDetails(Pageable pageable, String partnerId, String jobCode, String endClientId,
			String mJobTypeId, String jobTrackStatus) {

		try {
		return jobEmployeeRepository.findByJobFeedback(pageable, jobCode, partnerId, endClientId, mJobTypeId,jobTrackStatus);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured :JobFeedback Service - getJobDetails");
		}

	}

		
	
	@GetMapping("/{id}")
	public Job getJobEmployeeByJobId(@PathVariable(value = "id") int id) throws SmartOfficeException {
		Job job= jobService.getJobById(id);
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
		job.setJobChatHistories(null);
		job.setJobTravels(null);
		job.setJobAssets(null);
		job.setJobPlanAssets(null);
		job.setJobMaterials(null);
		job.setJobTaskTypes(null);
		job.setJobDocs(null);
		job.setJobTips(null);
		return job;
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured :JobFeedback Service - getJobEmployeeByJobId");
			
		}


	}

	
	@PatchMapping
	public Job updateFeedback(@RequestBody Job job) throws SmartOfficeException {
		try {
		Job jobGet = jobService.getJobById(job.getId());
		jobGet.setClientFeedbackRemarks(job.getClientFeedbackRemarks());
		jobGet.setClientFeedbackScore(job.getClientFeedbackScore());
		
		for(JobEmployee jobEmployee:job.getJobEmployees()) {
			jobEmployeeRepository.save(jobEmployee);
		}
		return jobService.updateJobById(jobGet);
		}catch (Exception e) {
			// TODO: handle exception
			e.getLocalizedMessage();
			throw new SmartOfficeException("Exception Occured :JobFeedback Service - jobUpdateFeedback");
			
		}
//		return jobService.updateJobById(jobGet);
	

	}
}

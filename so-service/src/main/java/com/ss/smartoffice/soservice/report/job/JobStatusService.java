package com.ss.smartoffice.soservice.report.job;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.job.JobMilestoneRepository;
import com.ss.smartoffice.soservice.transaction.job.JobsRepository;
import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobMilestone;

@RestController
@RequestMapping(path = "report/job-statuses")
public class JobStatusService {
	
	@Autowired
	JobsRepository jobsRepository;
	
	@Autowired
	JobMilestoneRepository jobMilestoneRepository;
	
	@GetMapping
	public Page<Job> getJobsForStatus(Model model, Pageable pageable,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "partnerId",required = false) String partnerId,
			@RequestParam(value = "endClientId",required = false) String endClientId,
			@RequestParam(value = "mJobTypeId", required = false) String mJobTypeId) throws SmartOfficeException {
		Page<Job> pages =getJobDetails(pageable,jobCode,partnerId,endClientId,mJobTypeId);
		  model.addAttribute("number", pages.getNumber());
	       model.addAttribute("totalPages", pages.getTotalPages());
	       model.addAttribute("totalElements", pages.getTotalElements());	
	       model.addAttribute("size", pages.getSize());
	       model.addAttribute("bundledetails", pages.getContent());
	       return pages;
		

	}

private Page<Job> getJobDetails(Pageable pageable,  String jobCode,String partnerId,String endClientId, String mJobTypeId) {
	// TODO Auto-generated method stub
	return jobsRepository.findBySummaries(pageable, jobCode, partnerId, endClientId);
}
//	@GetMapping("/{id}")
//	public Optional<JobBay> getJobStatusesById(@PathVariable(value = "id") int id) throws SmartOfficeException {
//		return jobBayRepository.findById(id);
//
//	}
	
	@GetMapping("/{id}")
	public List<JobMilestone> getJobStatusesByJobId(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return jobMilestoneRepository.findByJobId(id);

	}
	
	
}

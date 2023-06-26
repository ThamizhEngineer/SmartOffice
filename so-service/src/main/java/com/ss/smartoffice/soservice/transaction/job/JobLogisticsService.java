package com.ss.smartoffice.soservice.transaction.job;

import java.time.LocalDateTime;
import java.util.List;

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

import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobAccomodation;
import com.ss.smartoffice.soservice.transaction.model.JobEmployee;
import com.ss.smartoffice.soservice.transaction.model.JobTravel;

@RestController
@RequestMapping(path = "transaction/job-logistics")
@Scope("prototype")
public class JobLogisticsService {
	@Autowired
	JobsRepository jobsRepository;
	@Autowired
	JobEmployeeRepository jobEmployeeRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	JobTravelRepository jobTravelRepository;
	@Autowired
	JobService jobService;
	@Autowired
	ProcessLogService processLogService;
	@Autowired
	JobAccomodationRepository jobAccomodationRepository;

	@GetMapping
	public Iterable<Job> getJobLogistics(Model model, Pageable pageable,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "partnerId", required = false) String partnerId,
			@RequestParam(value = "endClientId", required = false) String endClientId,
			@RequestParam(value = "jobPlanStatus", required = false) String jobPlanStatus) throws SmartOfficeException {
		Page<Job> pages = getJobLogisticsDetails(pageable, jobCode, partnerId, endClientId, jobPlanStatus);
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("bundledetails", pages.getContent());
		return pages;

	}

	private Page<Job> getJobLogisticsDetails(Pageable pageable, String jobCode, String partnerId, String endClientId,
			String jobPlanStatus) {
		return jobsRepository.findByJobLogisticsSummaries(pageable, jobCode, partnerId, endClientId, jobPlanStatus);

	}

	@GetMapping("/{jobId}")
	public Job getJobsById(@PathVariable(value = "jobId") String jobId) throws SmartOfficeException {
		Job job = jobsRepository.findById(Integer.valueOf(jobId)).get();
		job.setJobPlanEmps(null);
		job.setJobPlanMaterials(null);
		job.setJobPlanProfiles(null);
		job.setJobPlanTaskTypes(null);
		job.setJobPlanStatus(null);
		job.setJobPlanAssets(null);
		
		return job;

	}

	@PatchMapping("/{jobId}/add-update-memployee")
	private Job addEmployee(@RequestBody Job job) throws SmartOfficeException {

		if (job.getJobEmployees() != null && !job.getJobEmployees().isEmpty()) {
			for (JobEmployee jobEmployee : job.getJobEmployees()) {
				jobEmployeeRepository.save(jobEmployee);
			}
		} else {
			throw new SmartOfficeException("Invalid Operation");
		}
		return job;

	}

	@DeleteMapping("/{jobId}/{jobEmpId}")
	@Transactional
	private void deleteEmployee(@PathVariable(value = "jobId") String jobId,
			@PathVariable(value = "jobEmpId") String jobEmpId) throws SmartOfficeException {
		
		Job job=jobsRepository.findById(Integer.valueOf(jobId)).get();
		if(job.getJobEmployees()!=null&&!job.getJobEmployees().isEmpty()) {
			for(JobEmployee jobEmployee:job.getJobEmployees()) {
				if((jobEmployee.gettJobId().equals(jobId))&&(jobEmployee.getId()==Integer.parseInt(jobEmpId))) {
					jobEmployeeRepository.delete(Integer.parseInt(jobEmpId));
				}
				
			}
			ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , jobId, "JOB EMP TRAVEL ", "JOB EMP TRAVEL DELETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
		}

	}

	@PatchMapping("/{jobId}/{jobEmpId}/reported")
	public JobEmployee reported(@PathVariable(value = "jobId") String jobId,
			@PathVariable(value = "jobEmpId") String jobEmpId) throws SmartOfficeException {

		JobEmployee jobEmployee = jobEmployeeRepository.findById(Integer.parseInt(jobEmpId)).get();
		jobEmployee.setHasReported("Y");
		jobEmployee.setReportedDt(LocalDateTime.now());
		jobEmployee.setReportedRemarks("REPORTED");
		jobEmployeeRepository.save(jobEmployee);
		ProcessLog processLog= new ProcessLog();
		processLog.setJobId(String.valueOf(jobEmployee.getEmployeeId()));
		processLog.setEmployeeId(commonUtils.getLoggedinEmployeeId());
		processLog.setProcessName("EMPLOYEE REPORTING");
		processLog.setProcessId(String.valueOf(jobEmployee.gettJobId()));
		processLogService.addLog(processLog);
		return jobEmployee;

	}

	@PatchMapping("/{jobId}/{jobEmpId}/departed")
	public JobEmployee departed(@PathVariable(value = "jobId") String jobId,
			@PathVariable(value = "jobEmpId") String jobEmpId) throws SmartOfficeException {

		JobEmployee jobEmployee = jobEmployeeRepository.findById(Integer.parseInt(jobEmpId)).get();
		jobEmployee.setHasDeparted("Y");
		jobEmployee.setDepartedDt(LocalDateTime.now());
		jobEmployee.setDepartedRemarks("DEPARTED");
		jobEmployeeRepository.save(jobEmployee);
		ProcessLog processLog= new ProcessLog();
		processLog.setJobId(String.valueOf(jobEmployee.getEmployeeId()));
		processLog.setEmployeeId(commonUtils.getLoggedinEmployeeId());
		processLog.setProcessName("EMPLOYEE DEPARTING");
		processLog.setProcessId(String.valueOf(jobEmployee.gettJobId()));
		processLogService.addLog(processLog);
		return jobEmployee;
	}

	@PatchMapping("/{jobId}/{jobEmpId}/emp-travel")
	public List<JobTravel> addAndUpdateTravel(@RequestBody JobTravel jobTravel, @PathVariable(value = "jobId") String jobId,
			@PathVariable(value = "jobEmpId") String jobEmpId) throws SmartOfficeException {
		
			Job job= jobsRepository.findById(Integer.valueOf(jobId)).get();
			jobTravel.settJobId(jobId);
			jobTravel.settJobEmpId(jobEmpId);
			jobTravel.setTravelType(jobTravel.getTravelType());
			jobTravel.setTravelNumber(jobTravel.getTravelNumber());
			jobTravel.setTravelTime(jobTravel.getTravelTime());
			jobTravel.setPnrNo(jobTravel.getPnrNo());
			jobTravel.setBoardingPoint(jobTravel.getBoardingPoint());
			jobTravel.setDroppingAt(jobTravel.getDroppingAt());
			ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , jobId, "JOB EMP TRAVEL", "JOB EMP TRAVEL", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
			jobTravelRepository.save(jobTravel);
		
		
		return jobTravelRepository.findByTJobId(jobId);

	}
	@Transactional
	@DeleteMapping("/{jobId}/{jobEmpId}/emp-travel/{jobEmpTravelId}")
	private void removeTravel(@PathVariable(value="jobId")String jobId,@PathVariable(value="jobEmpId")String jobEmpId,@PathVariable(value="jobEmpTravelId")String jobEmpTravelId)throws SmartOfficeException{
		Job job=jobsRepository.findById(Integer.valueOf(jobId)).get();
		if(job.getJobTravels()!=null&&!job.getJobTravels().isEmpty()) {
			for(JobTravel jobTravel:job.getJobTravels()) {
				if((jobTravel.gettJobId().equals(jobId))&&(jobTravel.gettJobEmpId().equals(jobEmpId))&&(jobTravel.getId()==Integer.parseInt(jobEmpTravelId))) {
					jobTravelRepository.delete(Integer.parseInt(jobEmpTravelId));
				}
				
			}
			ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , jobId, "JOB EMP TRAVEL ", "JOB EMP TRAVEL DELETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
		}
		
	}
	@PatchMapping("/{jobId}/{jobEmpId}/emp-travel-complete")
	private JobEmployee travelBookingComplete(@PathVariable(value="jobId")String jobId,@PathVariable(value="jobEmpId")String jobEmpId)throws SmartOfficeException{
		Job job= jobsRepository.findById(Integer.valueOf(jobId)).get();
		JobEmployee jobEmployee=jobEmployeeRepository.findById(Integer.parseInt(jobEmpId)).get();
		jobEmployee.setIsJobTravelComplete("Y");
		jobEmployeeRepository.save(jobEmployee);
		ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , jobId, "JOB EMP TRAVEL ", "JOB EMP TRAVEL COMPLETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
		return jobEmployee;
		
	}
	@PatchMapping("/{jobId}/{jobEmpId}/emp-accommodation")
	private List<JobAccomodation> addAndupdateAccommodation(@RequestBody JobAccomodation jobAccomodation ,@PathVariable(value="jobId")String jobId,@PathVariable(value="jobEmpId")String jobEmpId) throws SmartOfficeException {
		
		
			Job job= jobsRepository.findById(Integer.valueOf(jobId)).get();
			jobAccomodation.setJobId(jobId);
			jobAccomodation.setJobEmpId(jobEmpId);
			jobAccomodation.setAccDate(jobAccomodation.getAccDate());
			jobAccomodation.setAccName(jobAccomodation.getAccName());
			jobAccomodation.setAccType(jobAccomodation.getAccType());
			jobAccomodation.setBookingId(jobAccomodation.getBookingId());
			jobAccomodation.setPickUp(jobAccomodation.getPickUp());
			ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , String.valueOf(job.getId()), "JOB ACCOMODATION", "JOB CONFIRM", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
			 jobAccomodationRepository.save(jobAccomodation);
		

		return jobAccomodationRepository.findByJobId(jobId);
}
	@Transactional
	@DeleteMapping("/{jobId}/{jobEmpId}/emp-accommodation/{jobAccommodationId}")
	private void removeAccommodation(@PathVariable(value="jobId")String jobId,@PathVariable(value="jobEmpId")String jobEmpId,@PathVariable(value="jobAccommodationId")String jobAccommodationId )throws SmartOfficeException{
		Job job=jobsRepository.findById(Integer.valueOf(jobId)).get();
		if(job.getJobAccomodations()!=null&&!job.getJobAccomodations().isEmpty()) {
			for(JobAccomodation jobAccomodation:job.getJobAccomodations()) {
				if((jobAccomodation.getJobId().equals(jobId))&&(jobAccomodation.getJobEmpId().equals(jobEmpId))&&(jobAccomodation.getId()==Integer.parseInt(jobAccommodationId))) {
					jobAccomodationRepository.delete(Integer.parseInt(jobAccommodationId));
				}
				
			}
			ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , jobId, "JOB EMP ACCOMODATION ", "JOB EMP ACCOMODATION DELETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
			processLogService.addLog(processLog);
		}
	
	}
	@PatchMapping("/{jobId}/{jobEmpId}/emp-accommodation-complete")
	private JobEmployee accommodationBookingComplete(@PathVariable(value="jobId")String jobId,@PathVariable(value="jobEmpId")String jobEmpId )throws SmartOfficeException{
		Job job= jobsRepository.findById(Integer.valueOf(jobId)).get();
		JobEmployee jobEmployee=jobEmployeeRepository.findById(Integer.parseInt(jobEmpId)).get();
		jobEmployee.setIsJobAccComplete("Y");
		ProcessLog processLog= new ProcessLog(null,"JOB LOGISTICS SERVICE" , jobId, "JOB EMP ACCOMODATION ", "JOB EMP ACCOMODATION COMPLETED", null, jobId, commonUtils.getLoggedinEmployeeId(), job.getPartnerId(), job.getEndClientId(), null, null, null);
		processLogService.addLog(processLog);
		return jobEmployeeRepository.save(jobEmployee);
		
	}
	@PatchMapping("/{jobId}/job-details")
	private Job addJobdetail(@PathVariable(value="jobId")String jobId,@RequestBody Job job)throws SmartOfficeException{
		Job jobIds =jobsRepository.findById(Integer.parseInt(jobId)).get();
		jobIds.setStartDt(job.getStartDt());
		jobIds.setEndDt(job.getEndDt());
		return jobsRepository.save(jobIds);
	}
}

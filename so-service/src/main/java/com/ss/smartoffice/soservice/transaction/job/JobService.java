package com.ss.smartoffice.soservice.transaction.job;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeMaterialRepository;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeProfileRepository;
import com.ss.smartoffice.soservice.master.JobTypeService.JobTypeTaskTypeRepository;
import com.ss.smartoffice.soservice.master.employeeprofile.ProfileService;
import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobPlanMaterial;
import com.ss.smartoffice.soservice.transaction.model.JobPlanProfile;
import com.ss.smartoffice.soservice.transaction.model.JobPlanTaskType;
import com.ss.smartoffice.soservice.transaction.model.SaleOrder;
import com.ss.smartoffice.soservice.transaction.model.JobPlanEmp;
import com.ss.smartoffice.soservice.transaction.model.JobPlanAssets;
import com.ss.smartoffice.soservice.transaction.saleorder.SaleOrderRepository;
import com.ss.smartoffice.soservice.transaction.saleorder.SaleOrderService;

@RestController
@RequestMapping(path = "transaction/jobs")
@Scope("prototype")
public class JobService {

	@Autowired
	JobsRepository jobsRepository;

	@Autowired
	DocInfoRepository docInfoRepository;
	@Autowired
	JobDocRepository jobDocRepository;

	@Autowired
	CommonUtils commonUtils;
	@Autowired
	JobPlanMaterialRepository jobPlanMaterialRepository;
	@Autowired
	JobPlanTaskTypeRepository jobPlanTaskTypeRepository;
	@Autowired
	JobPlanProfileRepository jobPlanProfileRepository;

	@Autowired
	JobPlanTeamRepository jobPlanTeamRepository;

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
	SaleOrderService saleOrderService;

	@Autowired
	ProfileService profileService;
	
	@Autowired
	SaleOrderRepository saleOrderRepository;

//	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Job> getJob(Model model, Pageable pageable,
			@RequestParam(value = "partnerId", required = false) String partnerId,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "jobTypeId", required = false) String jobTypeId) throws SmartOfficeException {
		Page<Job> pages = getJobDetails(pageable, partnerId, jobCode, jobTypeId);
		model.addAttribute("number", pages.getNumber());
		model.addAttribute("totalPages", pages.getTotalPages());
		model.addAttribute("totalElements", pages.getTotalElements());
		model.addAttribute("size", pages.getSize());
		model.addAttribute("bundledetails", pages.getContent());
		return pages;
	}

	private Page<Job> getJobDetails(Pageable pageable, String partnerId, String jobCode, String jobTypeId) {
		// TODO Auto-generated method stub
		return jobsRepository.findBySummaries(pageable, partnerId, jobCode, jobTypeId);
	}

//	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Job getJobById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		Job job = jobsRepository.findById(id).get();
		System.out.println(job);
		job.setSaleOrder(saleOrderService.getSaleOrderById(Integer.parseInt(job.getSaleOrderId())).get());
		List<JobPlanProfile> jobPlanProfiles = new ArrayList<JobPlanProfile>();

		if (job.getJobPlanProfiles() != null && !job.getJobPlanProfiles().isEmpty()) {
			for (JobPlanProfile jobPlanProfile : job.getJobPlanProfiles()) {
				// ideally profileId should be available in records.
				jobPlanProfile.setProfile(
						profileService.getProfileById(Integer.parseInt(jobPlanProfile.getProfileId())).get());
				jobPlanProfiles.add(jobPlanProfile);
			}
			System.out.println(jobPlanProfiles);
			job.setJobPlanProfiles(jobPlanProfiles);
		}
		return job;
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

//	@CrossOrigin(origins = "*")
	@PostMapping
	public Job addJob(@RequestBody Job job) throws SmartOfficeException {
		HashMap<String, String> buisnessKeys = new HashMap<>();
		job.setJobCode(CreateJobCode(job.getSaleOrderId()));
		return jobsRepository.save(job);
	}

//	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Job updateJobById(@RequestBody Job job) throws SmartOfficeException {
		return jobsRepository.save(job);
	}

	@PatchMapping("/{jobId}")
	public Job addUpdateJobPlanChilds(@RequestBody Job job, @PathVariable(value = "jobId") Integer jobId)
			throws SmartOfficeException {
//		Job jobById = getJobById(jobId);
		if (job.getJobPlanMaterials() != null && !job.getJobPlanMaterials().isEmpty()) {
			for (JobPlanMaterial jobPlanMaterial : job.getJobPlanMaterials()) {
				if (jobPlanMaterial.getJobId().equals(jobId.toString())) {
					jobPlanMaterialRepository.save(jobPlanMaterial);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}
			}
		}
		if (job.getJobPlanProfiles() != null && !job.getJobPlanProfiles().isEmpty()) {
			for (JobPlanProfile jobPlanProfile : job.getJobPlanProfiles()) {
				if (jobPlanProfile.gettJobId().toString().equals(jobId.toString())) {
					jobPlanProfileRepository.save(jobPlanProfile);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}
			}
		}
		if (job.getJobPlanTaskTypes() != null && !job.getJobPlanTaskTypes().isEmpty()) {
			for (JobPlanTaskType jobPlanTaskType : job.getJobPlanTaskTypes()) {
				if (jobPlanTaskType.getJobId().equals(jobId.toString())) {
					jobPlanTaskTypeRepository.save(jobPlanTaskType);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}
			}
		}
		if (job.getJobPlanEmps() != null && !job.getJobPlanEmps().isEmpty()) {
//			System.out.println(Integer.valueOf(jobId));
			for (JobPlanEmp jobPlanEmp : job.getJobPlanEmps()) {
				if (jobPlanEmp.getJobId().toString().equals(jobId.toString())) {
					jobPlanTeamRepository.save(jobPlanEmp);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}
			}
		}
		if (job.getJobPlanAssets() != null && !job.getJobPlanAssets().isEmpty()) {
			for (JobPlanAssets jobPlanTestKit : job.getJobPlanAssets()) {
				if (jobPlanTestKit.getId().equals(jobId)) {
					jobPlanTestKitRepository.save(jobPlanTestKit);
				} else {
					throw new SmartOfficeException("Invalid Job Id");
				}
			}
		}
		return job;
	}

	@DeleteMapping("/{id}/lines")
	@Transactional
	public void deleteJobPlanLines(@RequestBody Job job, @PathVariable(value = "id") Integer id)
			throws SmartOfficeException {
		if (job.getJobPlanMaterials() != null && !job.getJobPlanMaterials().isEmpty()) {
			for (JobPlanMaterial jobPlanMaterial : job.getJobPlanMaterials()) {
				jobPlanMaterialRepository.delete(jobPlanMaterial.getId());
			}
		} else if (job.getJobPlanProfiles() != null && !job.getJobPlanProfiles().isEmpty()) {
			for (JobPlanProfile jobPlanProfile : job.getJobPlanProfiles()) {
				jobPlanProfileRepository.delete(jobPlanProfile.getId());
			}

		} else if (job.getJobPlanTaskTypes() != null && !job.getJobPlanTaskTypes().isEmpty()) {
			for (JobPlanTaskType jobPlanTaskType : job.getJobPlanTaskTypes()) {
				jobPlanTaskTypeRepository.delete(jobPlanTaskType.getId());
			}
		} else if (job.getJobPlanEmps() != null && !job.getJobPlanEmps().isEmpty()) {
			for (JobPlanEmp jobPlanEmp : job.getJobPlanEmps()) {
				jobPlanTeamRepository.delete(jobPlanEmp.getId());
			}
		} else if (job.getJobPlanAssets() != null && !job.getJobPlanAssets().isEmpty()) {
			for (JobPlanAssets jobPlanTestKit : job.getJobPlanAssets()) {
				jobPlanTestKitRepository.delete(jobPlanTestKit.getId());
			}
		} else {
			throw new SmartOfficeException("Invalid Request");
		}
	}

}


package com.ss.smartoffice.soservice.master.JobTypeService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.employeeprofile.ProfileService;

@RestController
@RequestMapping(path="master/job-types")
@Scope("prototype")
public class JobTypeService {
@Autowired
JobTypeRepository jobTypeRepository;
@Autowired
JobTypeMaterialRepository jobTypeMaterialRepository;
@Autowired
JobTypeProfileRepository jobTypeProfileRepository;

@Autowired
JobTypeTaskTypeRepository jobTypeTaskTypeRepository;

@Autowired
ProfileService profileService;
@Autowired
SequenceGenerationService sequenceGenerationService;

//@CrossOrigin(origins="*")
@GetMapping
public Iterable<JobType> getjobType(Model model, Pageable pageable,
		@RequestParam(value = "jobTypeName",required = false) String jobTypeName)
		throws Exception {
	
	Page<JobType> pages = getjobType(pageable,jobTypeName);
       model.addAttribute("number", pages.getNumber());
       model.addAttribute("totalPages", pages.getTotalPages());
       model.addAttribute("totalElements", pages.getTotalElements());	
       model.addAttribute("size", pages.getSize());
       model.addAttribute("jobtypes", pages.getContent());
        return pages;
}


public Page<JobType>getjobType(Pageable pageable,String jobTypeName){
	 return jobTypeRepository.findAll(pageable);
 }


//@CrossOrigin(origins="*")
@GetMapping("/")
public Iterable<JobType> getAlljobType(@RequestParam(value="jobTypeName",required=false)String jobTypeName)throws SmartOfficeException{
	boolean searchByJobTypeName=false;
	if(jobTypeName!=null&&!jobTypeName.isEmpty()) {
		searchByJobTypeName=true;
	}
	if(searchByJobTypeName) {
		return jobTypeRepository.findByJobTypeName(jobTypeName);
	}
	return jobTypeRepository.findBySummaries();
	
}
//@CrossOrigin(origins = "*")
//@GetMapping
//public Iterable<JobType> getAlljobType1() throws SmartOfficeException {
//	return jobTypeRepository.findAll();
//
//}
//@CrossOrigin(origins="*")
@GetMapping("/{id}")
public JobType getjobTypeById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	JobType jobType =jobTypeRepository.findById(id).get();
	
	if(jobType.getJobTypeProfile()!=null&&!jobType.getJobTypeProfile().isEmpty()) {
		List<JobTypeProfile> jobTypeProfiles = new ArrayList<JobTypeProfile>();
		for (JobTypeProfile jobTypeProfile : jobType.getJobTypeProfile()) {
			//ideally profileId should be available in records.
			
			jobTypeProfile.setProfile(profileService.getProfileById(Integer.parseInt(jobTypeProfile.getmProfileId())).get());
			jobTypeProfiles.add(jobTypeProfile);
	
		}
		
		
		
//		System.out.println(jobPlanProfiles);
		jobType.setJobTypeProfile(jobTypeProfiles);
		}
	return jobType;
	
}
//@CrossOrigin(origins="*")
@PostMapping
public JobType addJobType(@RequestBody JobType jobType)throws SmartOfficeException{
	HashMap<String, String> buisnessKeys = new HashMap<>();
	jobType.setJobTypeCode(sequenceGenerationService.nextSeq("JOB-TYPE-MASTER",buisnessKeys ));
	List<JobType> nameList = (List<JobType>) jobTypeRepository.findAll();
	List<JobType> type = new ArrayList<>();
	for(JobType list:nameList) {
		if(list.getJobTypeName().equals(jobType.getJobTypeName())) {
			type.add(list);
			if(!type.isEmpty()) {
				throw new SmartOfficeException("Name Already Exists");
				
			}
		}
	}
	jobTypeRepository.save(jobType);
	return jobType;
	
}
//@CrossOrigin(origins="*")
@PatchMapping("/{id}")
public JobType updateJobType(@RequestBody JobType jobType)throws SmartOfficeException{
	return jobTypeRepository.save(jobType);
	
	
}

@PatchMapping("/{id}/lines")
public JobType addOrUpdateLines(@RequestBody JobType jobType)throws SmartOfficeException{
	if(jobType.getJobTypeMaterials()!=null&&!jobType.getJobTypeMaterials().isEmpty()) {
		for(JobTypeMaterial jobTypeMaterial:jobType.getJobTypeMaterials()) {
			jobTypeMaterialRepository.save(jobTypeMaterial);
		}
	}
	if(jobType.getJobTypeProfile()!=null&&!jobType.getJobTypeProfile().isEmpty()) {
		for(JobTypeProfile jobTypeProfile:jobType.getJobTypeProfile()) {
			jobTypeProfileRepository.save(jobTypeProfile);
		}
	}
	if(jobType.getJobTypeTaskTypes()!=null&&!jobType.getJobTypeTaskTypes().isEmpty()) {
		for(JobTypeTaskType jobTypeTaskType:jobType.getJobTypeTaskTypes()) {
			if(jobTypeTaskType.getWeightage()<=100) {
				jobTypeTaskTypeRepository.save(jobTypeTaskType);
			}else {
				throw new SmartOfficeException("Weightage cannot be more than 100");
			}
			
		}
	}
	
	return jobType;
	
}


//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deleteJobType(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	jobTypeRepository.deleteById(id);
}

@Transactional

@DeleteMapping("/{id}/delete/lines")
public void deleteJobTypeLines(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	JobType jobType=getjobTypeById(id);
	if(jobType.getJobTypeMaterials()!=null&&!jobType.getJobTypeMaterials().isEmpty()) {
		for(JobTypeMaterial jobTypeMaterial:jobType.getJobTypeMaterials()) {
			jobTypeMaterialRepository.delete(jobTypeMaterial.getId());
		}
	}else if (jobType.getJobTypeProfile()!=null&&!jobType.getJobTypeProfile().isEmpty()) {
		for(JobTypeProfile jobTypeProfile:jobType.getJobTypeProfile()) {
			jobTypeProfileRepository.delete(jobTypeProfile.getId());
		}
		
	}else if (jobType.getJobTypeTaskTypes()!=null&&!jobType.getJobTypeTaskTypes().isEmpty()) {
		for(JobTypeTaskType jobTypeTaskType:jobType.getJobTypeTaskTypes()) {
			jobTypeTaskTypeRepository.delete(jobTypeTaskType.getId());
		}
	}else {
		throw new SmartOfficeException("No Specific Value for JobType Present");
	}
}


}

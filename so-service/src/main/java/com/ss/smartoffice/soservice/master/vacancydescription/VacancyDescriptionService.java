package com.ss.smartoffice.soservice.master.vacancydescription;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;


@RestController
@RequestMapping("master/vacancy-descriptions")
public class VacancyDescriptionService {
@Autowired
VacancyDescriptionRepo vacancyDescriptionRepo;
@Autowired
SequenceGenerationService sequenceGenerationService;
@Autowired
VacancyDescriptionSkillRepo vacancyDescriptionSkillRepo;
@Autowired
UserGroupEmployeeMappingService userGroupEmployeeMappingService;
@Autowired
CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<VacancyDescription> getAllJobDescs()throws SmartOfficeException{
	return vacancyDescriptionRepo.findAll();	
		}
	@GetMapping("/{id}")
	public Optional<VacancyDescription> getJobDescById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return vacancyDescriptionRepo.findById(id);
	}

	@PostMapping
	public VacancyDescription addJobDescription(@RequestBody VacancyDescription vacancyDescription)throws SmartOfficeException{
		List<String> userGroupIds=userGroupEmployeeMappingService.getUserGroupHrId();
		if(userGroupIds.isEmpty()) {
			throw new SmartOfficeException("Only HR 1 Manager can Create Job Description");}
		else {
			if (vacancyDescription.getVcCode()== null || vacancyDescription.getVcCode().equals("")) {
				HashMap<String, String> buisnessKeys = new HashMap<>();
				vacancyDescription.setVcCode(sequenceGenerationService.nextSeq("JOB-DESC", buisnessKeys));
			}
			return vacancyDescriptionRepo.save(vacancyDescription);
			}
	}




	@PatchMapping("/{id}/update")
	public VacancyDescription updateJobDescription(@RequestBody VacancyDescription vacancyDescription)throws SmartOfficeException{
		List<String> userGroupIds=userGroupEmployeeMappingService.getUserGroupHrId();
		if(vacancyDescription.getVacancyDescriptionSkills()!=null && !vacancyDescription.getVacancyDescriptionSkills().isEmpty()) {
			for(VacancyDescriptionSkill vacancyDescriptionSkill:vacancyDescription.getVacancyDescriptionSkills()) {
				vacancyDescriptionSkill.setVacancyDescId(vacancyDescription.getId().toString());
				vacancyDescriptionSkillRepo.save(vacancyDescriptionSkill);
			}
		}
		if(userGroupIds.isEmpty()) {
			throw new SmartOfficeException("Only HR 1 Manager can Update Job Description");}
		else {return vacancyDescriptionRepo.save(vacancyDescription);}
	}
	

	@DeleteMapping("/{id}/delete-header")
	public void deleteJobDescription(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		vacancyDescriptionRepo.deleteById(id);
	}

	@DeleteMapping("/{id}/delete-lines")
	public void deleteJobDescriptionSkill(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		vacancyDescriptionSkillRepo.deleteById(id);
	}
}

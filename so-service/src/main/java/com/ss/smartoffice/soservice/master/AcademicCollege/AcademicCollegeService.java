package com.ss.smartoffice.soservice.master.AcademicCollege;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

@RestController
@RequestMapping("master/academic-colleges")
@Scope("prototype")
public class AcademicCollegeService {
	@Autowired
	AcademicCollegeRepository academicCollegeRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@GetMapping
	public Iterable<AcademicCollege> getAllAcademicColleges()throws SmartOfficeException{
		return academicCollegeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AcademicCollege> getAcademicCollegeById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return academicCollegeRepository.findById(id);
	}
	
	@PostMapping
	public AcademicCollege addAcademicCollege(@RequestBody AcademicCollege academicCollege)throws SmartOfficeException{
		List<AcademicCollege> academicColleges = (List<AcademicCollege>) academicCollegeRepository.findAll();
	
		for(AcademicCollege academicCollege2:academicColleges) {
			if(!(academicCollege2.getCollegeName().equalsIgnoreCase(academicCollege.getCollegeName()))&& (!academicCollege2.getAbbreviation().equalsIgnoreCase(academicCollege.getAbbreviation()))) {
				academicCollege.setCollegeCode(sequenceGenerationService.nextSequence("ACADEMIC COLLEGE").getOutput());
				academicCollege.setCollegeName(academicCollege.getCollegeName());
				
				academicCollege.setAbbreviation(academicCollege.getAbbreviation());
				academicCollege.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				
			}else {
				throw new SmartOfficeException("College Name Or Abbreviation Already Exists");
			}	
		}
		return academicCollegeRepository.save(academicCollege);
	}
	
	@PostMapping("multiadd")
	public void multiAdd(@RequestBody List<AcademicCollege> academicColleges)throws SmartOfficeException{
		for (AcademicCollege academicCollege : academicColleges) {
			addAcademicCollege(academicCollege);
		}
	}
	
	@PatchMapping("/{id}/update")
	public AcademicCollege updateAcademicCollege(@RequestBody AcademicCollege academicCollege)throws SmartOfficeException{
		List<AcademicCollege> academicColleges = (List<AcademicCollege>) academicCollegeRepository.findAll();
		
		for(AcademicCollege academicCollege2:academicColleges) {
			if(!(academicCollege2.getCollegeName().equalsIgnoreCase(academicCollege.getCollegeName()))&& (!academicCollege2.getAbbreviation().equalsIgnoreCase(academicCollege.getAbbreviation()))) {
				
				academicCollege.setCollegeName(academicCollege.getCollegeName());
				
				academicCollege.setAbbreviation(academicCollege.getAbbreviation());
				academicCollege.setModifiedBy((commonUtils.getLoggedinEmployeeId()));
				
			}else {
				throw new SmartOfficeException("College Name Or Abbreviation Already Exists");
			}
			
			
			
		}
		return academicCollegeRepository.save(academicCollege);

		
	}
	@DeleteMapping("/{id}/delete")
	public void deleteAcademicCollege(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		academicCollegeRepository.deleteById(id);
	}
	
}

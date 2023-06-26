package com.ss.smartoffice.soservice.master.AcademicDegree;

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
import com.ss.smartoffice.soservice.master.AcademicCollege.AcademicCollege;


@RestController
@RequestMapping("master/academic-degrees")
@Scope("prototype")
public class AcademicDegreeService {
	@Autowired
	AcademicDegreeRepository academicDegreeRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@GetMapping
	public Iterable<AcademicDegree> getAllAcademicDegree()throws SmartOfficeException{
		return academicDegreeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AcademicDegree> getAcademicDegreeById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return academicDegreeRepository.findById(id);
	}
	
	@PostMapping
	public AcademicDegree addAcademicDegree(@RequestBody AcademicDegree academicDegree)throws SmartOfficeException{
		List<AcademicDegree> academicDegrees = (List<AcademicDegree>) academicDegreeRepository.findAll();
	
		for(AcademicDegree academicDegree2:academicDegrees) {
			if(!(academicDegree2.getDegreeName().equalsIgnoreCase(academicDegree.getDegreeName()))&& (!academicDegree2.getAbbreviation().equalsIgnoreCase(academicDegree.getAbbreviation()))) {
			
				academicDegree.setDegreeName(academicDegree.getDegreeName());
				
				academicDegree.setAbbreviation(academicDegree.getAbbreviation());
				academicDegree.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				
			}else {
				throw new SmartOfficeException("Degree Name Or Abbreviation Already Exists");
			}
			
			
			
		}
		return academicDegreeRepository.save(academicDegree);		
	}
	
	@PostMapping("multiadd")
	public void multiAdd(@RequestBody List<AcademicDegree> academicDegrees)throws SmartOfficeException{
		for (AcademicDegree academicDegree : academicDegrees) {
			addAcademicDegree(academicDegree);
		}
	}
	
	@PatchMapping("/{id}/update")
	public AcademicDegree updateAcademicDegree(@RequestBody AcademicDegree academicDegree)throws SmartOfficeException{
		List<AcademicDegree> academicDegrees = (List<AcademicDegree>) academicDegreeRepository.findAll();
		
		for(AcademicDegree academicDegree2:academicDegrees) {
			if(!(academicDegree2.getDegreeName().equalsIgnoreCase(academicDegree.getDegreeName()))&& (!academicDegree2.getAbbreviation().equalsIgnoreCase(academicDegree.getAbbreviation()))) {
			
				academicDegree.setDegreeName(academicDegree.getDegreeName());
				
				academicDegree.setAbbreviation(academicDegree.getAbbreviation());
				academicDegree.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				
			}else {
				throw new SmartOfficeException("Degree Name Or Abbreviation Already Exists");
			}
			
			
			
		}
		return academicDegreeRepository.save(academicDegree);
	}
	@DeleteMapping("/{id}/delete")
	public void deleteAcademicDegree(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		academicDegreeRepository.deleteById(id);
	}
	

}

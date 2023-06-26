package com.ss.smartoffice.soservice.master.AcademicCourse;

import java.util.ArrayList;
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
import com.ss.smartoffice.soservice.master.AcademicDegree.AcademicDegree;

@RestController
@RequestMapping("master/academic-courses")
@Scope("prototype")
public class AcademicCourseService {
	@Autowired
	AcademicCourseRepository academicCourseRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@GetMapping
	public Iterable<AcademicCourse> getAllAcademicCourse()throws SmartOfficeException{
		return academicCourseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AcademicCourse> getAcademicCourseById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return academicCourseRepository.findById(id);
	}
	
	@PostMapping
	public AcademicCourse addAcademicCourse(@RequestBody AcademicCourse academicCourse)throws SmartOfficeException{
		List<AcademicCourse> academicCourses = (List<AcademicCourse>) academicCourseRepository.findAll();
	
		for(AcademicCourse academicSpecialisation2:academicCourses) {
			if(!(academicSpecialisation2.getCourseName().equalsIgnoreCase(academicCourse.getCourseName()))&& (!academicSpecialisation2.getAbbreviation().equalsIgnoreCase(academicCourse.getAbbreviation()))) {
				academicCourse.setCourseCode(sequenceGenerationService.nextSequence("ACADEMIC COURSE").getOutput());
				academicCourse.setCourseName(academicCourse.getCourseName());
				
				academicCourse.setAbbreviation(academicCourse.getAbbreviation());
				academicCourse.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				
			}else {
				throw new SmartOfficeException("Course Name Or Abbreviation Already Exists");
			}		
		}
		return academicCourseRepository.save(academicCourse);	
	}
	
	@PostMapping("multiadd")
	public void multiAdd(@RequestBody List<AcademicCourse> academicCourses)throws SmartOfficeException{
		for (AcademicCourse academicCourse : academicCourses) {
			addAcademicCourse(academicCourse);
		}
	}
	
	@PatchMapping("/{id}/update")
	public AcademicCourse updateAcademicCourse(@RequestBody AcademicCourse academicCourse)throws SmartOfficeException{
		List<AcademicCourse> academicCourses = (List<AcademicCourse>) academicCourseRepository.findAll();
		for(AcademicCourse academicSpecialisation2:academicCourses) {
			if(!(academicSpecialisation2.getCourseName().equalsIgnoreCase(academicCourse.getCourseName()))&& (!academicSpecialisation2.getAbbreviation().equalsIgnoreCase(academicCourse.getAbbreviation()))) {
				
				academicCourse.setCourseName(academicCourse.getCourseName());
				academicCourse.setAbbreviation(academicCourse.getAbbreviation());
				academicCourse.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				
			}else {
				throw new SmartOfficeException("Specialisation Name Or Abbreviation Already Exists");
			}
			
			
			
		}

		
		
		academicCourse.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		return academicCourseRepository.save(academicCourse);
	}
	@DeleteMapping("/{id}/delete")
	public void deleteAcademicCourse(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		academicCourseRepository.deleteById(id);
	}
	
}

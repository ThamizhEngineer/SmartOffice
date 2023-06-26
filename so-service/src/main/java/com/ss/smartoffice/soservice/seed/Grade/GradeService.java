package com.ss.smartoffice.soservice.seed.Grade;

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


import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("seed/grades")
@Scope("prototype")
public class GradeService {
	@Autowired
	GradeRepository gradeRepository;
	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Grade> getGrades() throws SmartOfficeException {
		return gradeRepository.findAll();

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<Grade> getGradeById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return gradeRepository.findById(id);

	}

	//@CrossOrigin(origins = "*")
	@PostMapping
	public Grade addGradeById(@RequestBody Grade grade) throws SmartOfficeException {
		return gradeRepository.save(grade);

	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public Grade updateGradeById(@RequestBody Grade grade) throws SmartOfficeException {
		return gradeRepository.save(grade);

	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/bulk-update")
	public Iterable<Grade> bulkAddandUpdate(@RequestBody List<Grade> grades) throws SmartOfficeException {
		List<Grade> gradeList = new ArrayList<Grade>();
		System.out.println(gradeList);
		for (Grade grade : grades) {
			if (grade.getId() > 0) {
				Grade gradeFromDB = gradeRepository.findById(grade.getId()).orElse(new Grade());
				gradeFromDB = this.matchAndUpdateFields(gradeFromDB, grade);
			} else {
				grade = addingNewRecord(grade);
			}
			grade.setIsEnabled("Y");
			gradeList.add(grade);
		}
		System.out.println(gradeList);
		return (Iterable<Grade>) gradeRepository.saveAll(gradeList);

	}

	private Grade addingNewRecord(Grade grade) {
		// this Service will add new Record if there is no existing Record
		Grade newGrade = new Grade();
		newGrade.setGradeCode(grade.getGradeCode());
		newGrade.setGradeName(grade.getGradeName());
		newGrade.setGradeOrder(grade.getGradeOrder());
		newGrade.setLocationId(grade.getLocationId());
		return newGrade;
	}

	private Grade matchAndUpdateFields(Grade gradeFromDB, Grade grade) {
		// only update relevant fields to the db-record
		gradeFromDB.setId(grade.getId());
		gradeFromDB.setGradeCode(grade.getGradeCode());
		gradeFromDB.setGradeName(grade.getGradeName());
		gradeFromDB.setGradeOrder(grade.getGradeOrder());
		gradeFromDB.setLocationId(grade.getLocationId());
		return gradeFromDB;
	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteGradeById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		gradeRepository.deleteById(id);
	}

}

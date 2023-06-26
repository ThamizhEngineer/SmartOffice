package com.ss.smartoffice.soservice.seed.GradeLeave;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("seed/grade-leaves")
public class GradeLeaveService {
@Autowired
GradeLeaveRepository gradeLeaveRepository;


//@CrossOrigin(origins="*")
@GetMapping

public Iterable<GradeLeave> getGradeLeave()throws SmartOfficeException{
	return gradeLeaveRepository.findAll();
	
}


//@CrossOrigin(origins="*")
@GetMapping("/{id}")

public Optional<GradeLeave> getGradeLeaveById(@PathVariable(value="id")int id)throws SmartOfficeException{
	return gradeLeaveRepository.findById(id);
	
}


//@CrossOrigin(origins="*")
@PostMapping
public GradeLeave addGradeLeave(@RequestBody GradeLeave gradeLeave)throws SmartOfficeException{
	return gradeLeaveRepository.save(gradeLeave);
	
}

//@CrossOrigin(origins="*")
@PatchMapping("/{id}")

public GradeLeave updateGradeLeaveById(@RequestBody GradeLeave gradeLeave)throws SmartOfficeException{
	return gradeLeaveRepository.save(gradeLeave);
	
}

//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")

public void deleteGradeLeaveById(@PathVariable(value="id")int id)throws SmartOfficeException{
	gradeLeaveRepository.deleteById(id);
}
	
	
}

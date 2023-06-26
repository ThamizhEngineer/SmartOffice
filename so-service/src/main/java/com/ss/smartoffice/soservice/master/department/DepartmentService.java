package com.ss.smartoffice.soservice.master.department;
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
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.model.Department;


@RestController
@RequestMapping(path="master/departments")
@Scope("prototype")
public class DepartmentService {
//	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	DepartmentRepository departmentRepository;
//	@Autowired
//	NumberToWords numberToWords;
//	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Department> getDepartment() throws SmartOfficeException {
	
//		System.out.println(numberToWords.convert(100000));

		
		return departmentRepository.findAll();
		
		
	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional <Department> getDepartmentById(@PathVariable(value="id")int id) throws SmartOfficeException {
		return departmentRepository.findById(id);
		
	}
	//@CrossOrigin(origins = "*")
	@PostMapping
	
	public Department addDepartmentById(@RequestBody Department department) throws SmartOfficeException {
		System.out.println(department);
//		Float pfOpening= Float.parseFloat(department.getDeptCode());
//		System.out.println(pfOpening);
//		department.setDeptCode(pfOpening.toString());
		
		return departmentRepository.save(department);
		
	}
	
	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public Department updatedepartmentById(@RequestBody Department department) throws SmartOfficeException {
		return departmentRepository.save(department);
		
	}
	
	@DeleteMapping(value="/{id}/delete")
	public void deleteBankInfo(@PathVariable(value="id")Integer id)throws SmartOfficeException {
		departmentRepository.deleteById(id);
	}

}

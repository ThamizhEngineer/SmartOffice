package com.ss.smartoffice.soservice.master.AcademicType;

import java.time.LocalDateTime;
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

@RestController
@RequestMapping("master/academic-type")
@Scope("prototype")
public class AcademicTypeService {

	@Autowired
	AcademicTypeRepository academicTypeRepository;
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<AcademicType> getAllAcademicType()throws SmartOfficeException{
		return academicTypeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<AcademicType> getAcademicTypeById(@PathVariable(value="id") Integer id)throws SmartOfficeException{
		return academicTypeRepository.findById(id);
	}
	
	@PostMapping
	public AcademicType createAcademicType(@RequestBody AcademicType academicType)throws SmartOfficeException{
		academicType.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		academicType.setCreatedDt(LocalDateTime.now());
		return academicTypeRepository.save(academicType);
	}
	@PatchMapping("/update-type")
	public AcademicType updateAcademicType(@RequestBody AcademicType academicType)throws SmartOfficeException{
		academicType.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		academicType.setModifiedDt(LocalDateTime.now());
		return academicTypeRepository.save(academicType);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAcademicType(@PathVariable(value="id") Integer id) {
		academicTypeRepository.deleteById(id);	
	}
	
}

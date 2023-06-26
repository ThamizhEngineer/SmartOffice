package com.ss.smartoffice.soservice.seed.companyType;

import java.time.LocalDateTime;
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
import com.ss.smartoffice.shared.interceptor.CompanyTypeRepo;
import com.ss.smartoffice.shared.model.CompanyType;

@RestController
@RequestMapping("seed/company-type")
public class ComapnyTypeService {

	@Autowired
	CompanyTypeRepo companyTypeRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<CompanyType> getAllCompanyTypes()throws SmartOfficeException{
		return companyTypeRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<CompanyType> getCompanyTypeById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		return companyTypeRepo.findById(id);
	}
	
	@PostMapping
	public CompanyType createCompanyType(@RequestBody CompanyType companyType) throws SmartOfficeException{
		companyType.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		companyType.setCreatedDt(LocalDateTime.now());
		return companyTypeRepo.save(companyType);
	}
	
	@PatchMapping("/update")
	public CompanyType updateCompanyType(@RequestBody CompanyType companyType) throws SmartOfficeException{
		companyType.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		companyType.setModifiedDt(LocalDateTime.now());
		return companyTypeRepo.save(companyType);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompanyTypeById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		 companyTypeRepo.deleteById(id);
	}
}

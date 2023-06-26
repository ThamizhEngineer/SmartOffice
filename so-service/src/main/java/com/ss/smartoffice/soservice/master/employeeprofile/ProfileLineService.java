package com.ss.smartoffice.soservice.master.employeeprofile;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("master/employeedetails")

@Scope("prototype")
public class ProfileLineService {
	
	@Autowired
	ProfileLineRepo ProfileLineRepo;

	
	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ProfileLine> getEmpDetail() throws SmartOfficeException {
		return ProfileLineRepo.findAll();

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<ProfileLine> getEmpDetailById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		return ProfileLineRepo.findById(id);

	}

	//@CrossOrigin(origins = "*")
	@PostMapping
	public ProfileLine addEmpDetail(@RequestBody ProfileLine ProfileLine) throws SmartOfficeException {
		return ProfileLineRepo.save(ProfileLine); 
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public ProfileLine updateEmpDetail(@RequestBody ProfileLine ProfileLine) throws SmartOfficeException {
		return ProfileLineRepo.save(ProfileLine);

	}

	//@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteEmpDetail(@PathVariable(value = "id") int id) throws SmartOfficeException {
		ProfileLineRepo.deleteById(id);

	}


}

package com.ss.smartoffice.soservice.transaction.job;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.model.SmartOfficeException;

import com.ss.smartoffice.soservice.transaction.job.JobTeamStructureRepository;
import com.ss.smartoffice.soservice.transaction.model.JobProfile;


@RestController
@RequestMapping(path="transaction/team-structures")
@Scope("prototype")
public class JobTeamStructureService {

	@Autowired
	JobTeamStructureRepository jobTeamStructureRepository;
	
	
	
	//@CrossOrigin(origins="*")
	@GetMapping

	public Iterable<JobProfile> getJobTeamStructure() throws SmartOfficeException{
		return jobTeamStructureRepository.findAll();
		
	}
	//@CrossOrigin(origins="*")
	 @GetMapping("/{id}")

	 public Optional<JobProfile> getJobTeamStructureById(@PathVariable (value = "id") int id) throws SmartOfficeException{

		return jobTeamStructureRepository.findById(id);
		 
	 }
	//@CrossOrigin(origins="*")
	 @PostMapping

	 public JobProfile addJobTeamStructureById(@RequestBody JobProfile jobProfile) throws SmartOfficeException{

		return jobTeamStructureRepository.save(jobProfile);
		 
	 }
	//@CrossOrigin(origins="*")
	 @PutMapping("/{id}")

	 public JobProfile updateJobTeamStructureById(@RequestBody JobProfile jobProfile) throws SmartOfficeException{

		return jobTeamStructureRepository.save(jobProfile);
		 
	 }
	 
}

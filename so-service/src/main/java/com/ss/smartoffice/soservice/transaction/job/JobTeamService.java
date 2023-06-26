package com.ss.smartoffice.soservice.transaction.job;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ss.smartoffice.shared.model.SmartOfficeException;

import com.ss.smartoffice.soservice.transaction.job.JobEmployeeRepository;

import com.ss.smartoffice.soservice.transaction.model.JobEmployee;

@RestController
@RequestMapping(path="transaction/teams")
@Scope("prototype")
public class JobTeamService {

	@Autowired
	JobEmployeeRepository jobEmployeeRepository;
	
	
	//@CrossOrigin(origins = "*")
	@GetMapping

	public Iterable<JobEmployee> getJobTeam() throws SmartOfficeException{
		
		return jobEmployeeRepository.findAll();
		
	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")

	public Optional<JobEmployee> getJobTeamById(@PathVariable(value="id")int id) throws SmartOfficeException {

		return jobEmployeeRepository.findById(id);
	}
	//@CrossOrigin(origins = "*")
	@PostMapping

	public JobEmployee addJobTeamById(@RequestBody JobEmployee jobEmployee) throws SmartOfficeException {

		return jobEmployeeRepository.save(jobEmployee);
		
	}
	//@CrossOrigin(origins = "*")
	@PutMapping("/{id}")

	public JobEmployee updateJobTeamById(@RequestBody JobEmployee jobEmployee) throws SmartOfficeException{

		return jobEmployeeRepository.save(jobEmployee);
	}
		
	
}

package com.ss.smartoffice.soservice.master.trainingrequest;

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

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.transaction.incident.Incident;

@RestController
@RequestMapping(path="master/training-request")
public class TrainingRequestService {

	@Autowired
	TrainingRequestRepo trainingRequestRepo;
	
	@GetMapping
	public Iterable<Incident>getAllStages()throws SmartOfficeException{
		return trainingRequestRepo.findAll();	
	}
	
	@GetMapping("/{id}")
	public Optional<Incident>gettrainigById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return trainingRequestRepo.findById(id);	
	}
	
	@PostMapping
	public Incident addTraining(@RequestBody Incident incident)throws SmartOfficeException{
		return trainingRequestRepo.save(incident);	
	}
	
	@PatchMapping("/{id}")
	public Incident updateTraining( @PathVariable(value = "id") Integer id,@RequestBody Incident incident)throws SmartOfficeException{
		return trainingRequestRepo.save(incident);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStage(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		trainingRequestRepo.deleteById(id);
	}
}

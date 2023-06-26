package com.ss.smartoffice.soservice.seed.StatusService;

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

@RestController
@RequestMapping(path="seed/statuses")
public class StatusService {
@Autowired
StatusRepository statusRepository;
@GetMapping
public Iterable<Status> getStatuses()throws SmartOfficeException{
	return statusRepository.findAll();
}

@GetMapping("/{id}")
public Optional<Status> getStatusById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return statusRepository.findById(id);
}

@PostMapping
public Status addStatus(@RequestBody Status status)throws SmartOfficeException{
	return statusRepository.save(status);
}

@PatchMapping("/{id}")
public Status updateStatus(@RequestBody Status status)throws SmartOfficeException{
	return statusRepository.save(status);
}
@DeleteMapping("/{id}/delete")
public void deleteStatusById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	statusRepository.deleteById(id);
}

}

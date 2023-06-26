package com.ss.smartoffice.soservice.master.TaskType;

import java.util.HashMap;
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
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.seed.fiscalYear.FiscalYear;

@RestController
@RequestMapping(path="master/task-types")
public class TaskTypeService {
@Autowired
TaskTypeRepository taskTypeRepository;
@Autowired
SequenceGenerationService sequenceGenerationService;

//@CrossOrigin(origins="*")
@GetMapping
public Iterable<TaskType>getAllStages()throws SmartOfficeException{
	return taskTypeRepository.findAll();
	
}

//@CrossOrigin(origins="*")
@GetMapping("/{id}")
public Optional<TaskType>getStageById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return taskTypeRepository.findById(id);
	
}

//@CrossOrigin(origins="*")
@PostMapping
public TaskType addStage(@RequestBody TaskType taskType)throws SmartOfficeException{
	HashMap<String, String> buisnessKeys = new HashMap<>();
	taskType.setTaskTypeCode(sequenceGenerationService.nextSeq("TASK-TYPE-CODE", buisnessKeys));
	return taskTypeRepository.save(taskType);
	
}

//@CrossOrigin(origins="*")
@PatchMapping("/{id}")
public TaskType updateStage( @PathVariable(value = "id") Integer id,@RequestBody TaskType taskType)throws SmartOfficeException{
	return taskTypeRepository.save(taskType);
	
}

//@CrossOrigin(origins="*")
@DeleteMapping("/{id}")
public void deleteStage(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	taskTypeRepository.deleteById(id);
}


}

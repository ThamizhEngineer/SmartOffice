package com.ss.smartoffice.soservice.seed.LeaveType;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@RequestMapping("seed/leave-types")
@Scope("prototype")
public class LeaveTypeService {
	
	
	@Autowired
	LeaveTypeRepository leaveTypeRepository;
	// @CrossOrigin(origins="*")
	@GetMapping
	public Iterable<LeaveType>getLeaveTypes()throws SmartOfficeException{
		
		
		return leaveTypeRepository.findAll();
		
	}
	// @CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<LeaveType>getLeaveTypeById(@PathVariable(value="id")int id)throws SmartOfficeException{
		return leaveTypeRepository.findById(id);
		
	}
	// @CrossOrigin(origins="*")
	@PostMapping
	public LeaveType addLeaveType(@RequestBody LeaveType leaveType)throws SmartOfficeException{
		return leaveTypeRepository.save(leaveType);
		
	}
	// @CrossOrigin(origins="*")
    @PatchMapping("/{id}")
	public LeaveType updateLeaveTypeById(@RequestBody LeaveType leaveType)throws SmartOfficeException{
		return leaveTypeRepository.save(leaveType);
		
	}
	// @CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	public void deleteLeaveTypeById(@PathVariable(value="id")int id)throws SmartOfficeException{
		leaveTypeRepository.deleteById(id);
	}
	
	
	
	
}

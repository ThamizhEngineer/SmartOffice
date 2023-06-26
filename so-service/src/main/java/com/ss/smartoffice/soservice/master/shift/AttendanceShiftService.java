package com.ss.smartoffice.soservice.master.shift;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ss.smartoffice.soservice.seed.LeaveType.LeaveType;

@RestController
@RequestMapping("/master/shift")
public class AttendanceShiftService {

	@Autowired
	AttendanceShiftRepo shiftRepo; 
	
	
	@GetMapping
	public Iterable<AttendanceShift> getAllShift()throws SmartOfficeException{
		return shiftRepo.findAll();
	}
	@GetMapping("/{id}")
	public Optional<AttendanceShift> getShiftById(@PathVariable (value="id")Integer id)throws SmartOfficeException{
		return shiftRepo.findById(id);
	}
	@PostMapping
	public AttendanceShift createShift(@RequestBody AttendanceShift shift)throws SmartOfficeException{
		
		AttendanceShift shiftFromDb = shiftRepo.findDuplicate(shift.getShiftCode());
		
		if(shiftFromDb==null) {
			shiftRepo.save(shift);
		}else if(!shiftFromDb.equals(null)){
			throw new SmartOfficeException("Name already Present");			 
		}
		return shift;
		
//		return shiftRepo.save(shift);
	}
	@PatchMapping("/{id}")
	public AttendanceShift update(@RequestBody AttendanceShift shift)throws SmartOfficeException{
		return shiftRepo.save(shift);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteShift(@PathVariable (value="id")Integer id)throws SmartOfficeException{
		shiftRepo.deleteById(id);
	}
	
}

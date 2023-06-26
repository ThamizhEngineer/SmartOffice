package com.ss.smartoffice.soservice.transaction.ShiftChange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.ShiftChange.ShiftChangeHelper;

@RestController
@EnableScheduling
@RequestMapping("transaction/shift-change")
public class ShiftChangeService {
	

	@Autowired
	ShiftChangeRepo shiftChangeRepo;
	
	@Autowired
	ShiftChangeHelper shiftChangeHelper;
	
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	ArrayList<String> knownActions = new ArrayList<String>( Arrays.asList("request","cancel","approve","reject","proxy-request"));
	
	@GetMapping
	public Iterable<ShiftChange> getAllShiftChanges()throws SmartOfficeException{
		return shiftChangeRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<ShiftChange> getShiftChangeById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return shiftChangeRepo.findById(id);
	}
	@GetMapping("/employee-view")
	public List<ShiftChange> getShiftChangesbyEmployee()throws SmartOfficeException{
		return shiftChangeRepo.findByEmpId(commonUtils.getLoggedinEmployeeId());
	}
	
	@GetMapping("/manager-view")
	public List<ShiftChange> getShiftChangesbyManager()throws SmartOfficeException{
		return shiftChangeRepo.findByN1IdOrN2Id(commonUtils.getLoggedinEmployeeId(), commonUtils.getLoggedinEmployeeId());
	}
	
	@GetMapping("/hr1-shift-view")
	public List<ShiftChange> getShiftChangesByHrManager()throws SmartOfficeException{
		List<String> hr1Ids = userGroupEmployeeMappingService.getUserGroupHrId();
		
		System.out.println(hr1Ids);
		return shiftChangeRepo.findByHr1GroupId(hr1Ids);
	}
	
	@PostMapping("/{action}")
	public ShiftChange createShiftChange(@RequestBody ShiftChange shiftChange,@PathVariable(value = "action")String action)throws SmartOfficeException{	
		if(knownActions.contains(action)) {
			System.out.println("Service Hit");
		return	shiftChangeHelper.proccessShiftChange(action, shiftChange);
		}else {
			throw new SmartOfficeException("Invalid Url");	
		}					
	}
	
	@DeleteMapping("/{id}")
	public void DeleteShiftChange(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		shiftChangeRepo.deleteById(id);
	}
	
//	
	
	@Async
	@Scheduled(fixedRate=60*60*1000)
	public void shiftChangeRequest()throws SmartOfficeException{
			List<ShiftChange> shiftChange = shiftChangeRepo.shiftChangeRequest(LocalDate.now());
			
			if(shiftChange!=null) {
				for(ShiftChange shift:shiftChange) {		
					if(shift.getFromTime().getHour() >= LocalTime.now().getHour()) {						
						memployee emp = employeeRepository.findById(Integer.parseInt(shift.getEmpId())).get();
						emp.setShiftId(shift.getNewShiftId());
						employeeRepository.save(emp);
						shift.setIsComplete("Y");
						shiftChangeRepo.save(shift);
					}
					
				}
			}
			System.out.println(LocalTime.now());
			
			
	}
	
	
	

}

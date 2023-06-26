package com.ss.smartoffice.soservice.transaction.exitInterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
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
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;

@Service
@RestController
@RequestMapping("transaction/exit-interviews")
@Scope("prototype")
public class ExitInterviewService {
	
	@Autowired
	ExitInterviewBusHelper exitInterviewBusHelper;
	
	@Autowired
	ExitInterviewRepository exitInterviewRepository;
	
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;
	
	@Autowired
	CommonUtils commonUtils;
	
	ArrayList<String> knownActions = new ArrayList<String>(Arrays.asList("create","n1-clearance", "acc-clearance","hr-clearance"));

	@GetMapping
	public Iterable<ExitInterview> getExitInterview(){
		return exitInterviewRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<ExitInterview> getExitInterviewById(@PathVariable(value="id")int id){
		return exitInterviewRepository.findById(id);
	}
	
	@PostMapping
	public ExitInterview addExitInterview(@RequestBody ExitInterview exitInterview) {		
		return exitInterviewBusHelper.processExitInterview("create",exitInterview);		
	}
	
	@PatchMapping("/{id}/{action}")
	public ExitInterview updateExitInterviewProcess(@RequestBody ExitInterview exitInterview,@PathVariable(value = "id")Integer id,@PathVariable(value = "action")String action) throws SmartOfficeException{	
		if (knownActions.contains(action)) {			
			return exitInterviewBusHelper.processExitInterview(action,exitInterview);
		}else {
			throw new SmartOfficeException("Invalid Url");
		}		
	}
	
	@PatchMapping("/update/{id}")
	public ExitInterview updateExitInterview(@RequestBody ExitInterview exitInterview,@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		ExitInterview exitInterviewFromDb =exitInterviewRepository.findById(id).get();
		if(exitInterviewFromDb.getStatus().equals("COMPLETED")) {
			throw new SmartOfficeException("Exit Intervew Is in Complete State");
		}else {
			return exitInterviewRepository.save(exitInterview);
		}
		
	}
	
	
	@GetMapping("/view/{action}")
	public List<ExitInterview> getExitInterviewByAction(@PathVariable(value = "action")String action)throws SmartOfficeException{
		
		switch (action) {
		case "n1":			
			return exitInterviewRepository.findByN1(commonUtils.getLoggedinEmployeeId());
		case "acc2":
			List<String> acc2Ids=userGroupEmployeeMappingService.getUserGroupEmployeeById();	
			return exitInterviewRepository.findByAcc2(acc2Ids);
		case "hr2":
			List<String> hr2Ids=userGroupEmployeeMappingService.getUserGroupHr2Id();	
			return exitInterviewRepository.findByHr2(hr2Ids);
		default:
			throw new SmartOfficeException("Unknow url");
		}					
	}
 
	
	@DeleteMapping("/{id}")
	public void deleteExitInterview(@PathVariable(value="id")int id) {
		exitInterviewRepository.deleteById(id);
	}
	
}

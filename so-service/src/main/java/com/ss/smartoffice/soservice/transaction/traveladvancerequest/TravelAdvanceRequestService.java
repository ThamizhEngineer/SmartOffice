package com.ss.smartoffice.soservice.transaction.traveladvancerequest;

import java.util.ArrayList;
import java.util.List;
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

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim;
import com.ss.smartoffice.soservice.transaction.job.JobEmployeeRepository;
import com.ss.smartoffice.soservice.transaction.model.JobEmployee;

@RestController
@RequestMapping(path="transaction/tars")
@Scope("prototype")
public class TravelAdvanceRequestService {
@Autowired
TravelAdvanceRequestRepo travelAdvanceRequestRepo;
@Autowired
CommonUtils commonutils;

@Autowired
TravelAdvanceRequestHelper travelAdvanceRequestHelper;

@Autowired
UserGroupEmployeeMappingService userGroupEmployeeMappingService;

@Autowired
JobEmployeeRepository jobEmployeeRepository;

//EmployeeJob()
@GetMapping("/emp-jobs")
public List<JobEmployee> getEmpJobs()throws SmartOfficeException{

	int employeeId=Integer.valueOf(commonutils.getLoggedinEmployeeId());
	return jobEmployeeRepository.findByEmployeeId(employeeId);
}

//getMyTars
@GetMapping
public List<TravelAdvanceRequest>getMyTars()throws SmartOfficeException{
	String employeeId=commonutils.getLoggedinEmployeeId();
	System.out.println(commonutils.getAuthenticatedUser());
	return travelAdvanceRequestRepo.findByEmployeeId(employeeId);
}
@GetMapping("/{id}")
public Optional<TravelAdvanceRequest>getTarById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	return travelAdvanceRequestRepo.findById(id);
}
@GetMapping("/approved")
public List<TravelAdvanceRequest> getApproval()throws SmartOfficeException{

	return travelAdvanceRequestRepo.findByApprovalAmountIn();
}

//getN1Tars
@GetMapping("/n1-approval-pending")
public List<TravelAdvanceRequest>getN1Tars()throws SmartOfficeException{
	String n1EmployeeId=commonutils.getLoggedinEmployeeId();
	return travelAdvanceRequestRepo.findByN1EmployeeId(n1EmployeeId);
}

//getAcc2Tars
@GetMapping("/acc2-approval-pending")
public List<TravelAdvanceRequest>getAcc2Tars()throws SmartOfficeException{
	List<TravelAdvanceRequest> tars = new ArrayList<TravelAdvanceRequest>();
	List<String> userGroupIds=userGroupEmployeeMappingService.getUserGroupEmployeeById();
	if(!userGroupIds.isEmpty()) {
		tars =  travelAdvanceRequestRepo.findByAcc2UserGroupIdIn(userGroupIds);
	}
	return tars;
}
//apply
@PostMapping("/{action}")
public TravelAdvanceRequest apply(@PathVariable(value="action")String action,@RequestBody TravelAdvanceRequest travelAdvanceRequest)throws SmartOfficeException{
	System.out.println(action);
	if(action.equals("create")||action.equals("apply")) {
		return travelAdvanceRequestHelper.processTar(null, action, travelAdvanceRequest);

	}else {
		throw new SmartOfficeException("Invalid Url");
	}
	
}

//processApproval
@PatchMapping("{id}/{action}")
public TravelAdvanceRequest processApproval(@PathVariable(value="id")Integer id,@PathVariable(value="action")String action,@RequestBody TravelAdvanceRequest travelAdvanceRequest)throws SmartOfficeException{
	TravelAdvanceRequest returntar= new TravelAdvanceRequest();
	if(action.equals("n1-approve")||action.equals("n1-reject")||action.equals("acc2-approve")||action.equals("acc2-reject")||action.equals("cancel")) {
		return	travelAdvanceRequestHelper.processTar(id,action, travelAdvanceRequest);
	}else {
		throw new SmartOfficeException("Invalid url");
	}
	
}




}

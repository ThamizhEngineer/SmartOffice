package com.ss.smartoffice.soservice.transaction.exitInterview;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.interceptor.AuthUserRoleRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.repos.AuthTokenRepo;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
@Service
@Scope("prototype")
public class ExitInterviewBusHelper {
	
	@Value("${authuserrole.url}")
	private String AuthUserRole;
	
	@Autowired
	ExitInterviewRepository exitInterviewRepository;
	
	@Autowired
	AuthTokenRepo authTokenRepo;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AuthUserRepository authUserRepository;
	
	@Autowired
	AuthUserRoleRepository authUserRoleRepository;
	
	@Autowired
	ExitInterviewEventGenerator exitInterviewEventGenerator;
	
	@Autowired
	CommonUtils commonUtils;

	public ExitInterview processExitInterview(String action,ExitInterview exitInterview)throws SmartOfficeException{
	
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		ExitInterview exitInterviewFromDb = new ExitInterview();
		if(!(action.equals("create"))) {			
				exitInterviewFromDb=exitInterviewRepository.findById(exitInterview.getId()).get();	
				System.out.println("exitInterviewFromDb"+exitInterviewFromDb);
		}
		switch (action) {
		case "create":
			exitInterview =createExitInterview(exitInterview);	
			exitInterview.setStatus("PENDING-N1-CLEARENCE");
			exitInterviewRepository.save(exitInterview);			
			System.out.println("TESTING.....!!!");			
			exitInterviewFromDb =exitInterview;
			System.out.println(exitInterviewFromDb);
			exitInterviewEventGenerator.exitInterviewCreateEvent(exitInterviewFromDb,loggedInUser);
			break;
		case "n1-clearance":
			if(exitInterviewFromDb.getStatus().equals("PENDING-N1-CLEARENCE")) {
				exitInterviewFromDb=n1Clearance(exitInterviewFromDb,exitInterview);
				exitInterviewFromDb.setStatus("PENDING-ACCOUNT-CLEARENCE");
				exitInterviewRepository.save(exitInterviewFromDb);
				exitInterviewEventGenerator.exitInterviewN1AppovedEvent(exitInterviewFromDb, loggedInUser);
			}else {
				throw new SmartOfficeException("Status Not in PENDING-N1-CLEARENCE");
			}
			
			break;
		case "acc-clearance":
			if(exitInterviewFromDb.getStatus().equals("PENDING-ACCOUNT-CLEARENCE")) {
				exitInterviewFromDb=accountClearance(exitInterviewFromDb,exitInterview);
				exitInterviewFromDb.setStatus("PENDING-HR-CLEARENCE");
				exitInterviewRepository.save(exitInterviewFromDb);
				exitInterviewEventGenerator.exitInterviewAccClearedEvent(exitInterviewFromDb, loggedInUser);
			}else {
				throw new SmartOfficeException("Status Not in PENDING-ACCOUNT-CLEARENCE");
			}			
			break;
		case "hr-clearance":
			if(exitInterviewFromDb.getStatus().equals("PENDING-HR-CLEARENCE")) {
				clearLoginAccess(exitInterviewFromDb.getEmpId());
				employeeResigned(Integer.parseInt(exitInterviewFromDb.getEmpId()));
				exitInterviewFromDb=hrClearance(exitInterviewFromDb,exitInterview);
				exitInterviewFromDb.setStatus("COMPLETED");
				exitInterviewRepository.save(exitInterviewFromDb);
				exitInterviewEventGenerator.exitInterviewCompleteEvent(exitInterviewFromDb, loggedInUser);
			}else {
				throw new SmartOfficeException("Status Not in PENDING-HR-CLEARENCE");
			}
			break;
		default:
			break;
		}
		
		return exitInterviewFromDb;
	}
	
	
	public ExitInterview createExitInterview(ExitInterview exitInterview)throws SmartOfficeException{
		if (!commonUtils.isHr()) {
			throw new SmartOfficeException("Not a valid user to perform this action");
		}else {
			memployee employee = employeeRepository.findById(Integer.parseInt(exitInterview.getEmpId())).get();
			exitInterview.setEmpCode(employee.getEmpCode());
			exitInterview.setEmpName(employee.getEmpName());
			exitInterview.setAcc2GroupId(employee.getAcc2UsrGrpId());
			exitInterview.setDirGroupId(employee.getDirUsrGrpId());
			exitInterview.setHr2GroupId(employee.getHr2UsrGrpId());
			exitInterview.setOfficeId(employee.getOfficeId().toString());
			exitInterview.setN1ManagerId(employee.getN1EmpId());	
			exitInterview.setN1ManagerName(employee.getManagerName());	
			exitInterview.setN2ManagerId(employee.getN2EmpId());
			exitInterview.setDeptId(employee.getDeptId().toString());
			exitInterview.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			exitInterview.setCreatedDt(LocalDateTime.now());
			exitInterview.setStatus("CREATED");			
		}
		return exitInterview;		
	}
	
	public ExitInterview n1Clearance(ExitInterview exitInterviewFromDb,ExitInterview exitInterview)throws SmartOfficeException{		
		exitInterviewFromDb.setN1ClearedBy(commonUtils.getLoggedinEmployeeId());
		exitInterviewFromDb.setN1ClearedDt(LocalDateTime.now());
		exitInterviewFromDb.setN1ClearanceRemarks(exitInterview.getN1ClearanceRemarks());
		exitInterviewFromDb.setStatus("N1-CLEARED");
		exitInterviewFromDb.setIsN1Cleared("Y");
		return exitInterviewFromDb;
	}
	
	public ExitInterview accountClearance(ExitInterview exitInterviewFromDb,ExitInterview exitInterview)throws SmartOfficeException{
		exitInterviewFromDb.setAccClearedBy(commonUtils.getLoggedinEmployeeId());
		exitInterviewFromDb.setAccClearedDt(LocalDateTime.now());
		exitInterviewFromDb.setAccClearanceRemarks(exitInterview.getAccClearanceRemarks());
		exitInterviewFromDb.setStatus("ACCOUNT-CLEARED");
		exitInterviewFromDb.setIsAccCleared("Y");
		return exitInterviewFromDb;
	}
	
	public ExitInterview hrClearance(ExitInterview exitInterviewFromDb,ExitInterview exitInterview)throws SmartOfficeException{
		exitInterviewFromDb.setHrClearedBy(commonUtils.getLoggedinEmployeeId());
		exitInterviewFromDb.setHrClearedDt(LocalDateTime.now());
		exitInterviewFromDb.setHrClearanceRemarks(exitInterview.getHrClearanceRemarks());
		exitInterviewFromDb.setStatus("HR-CLEARED");
		exitInterviewFromDb.setIsHrCleared("Y");
		return exitInterviewFromDb;
	}

	public memployee employeeResigned(Integer empId)throws SmartOfficeException{
		memployee employee = employeeRepository.findById(empId).get();
		employee.setEmpStatus("RESIGNED");		
		return employeeRepository.save(employee);
	}
	public List<AuthUser> clearLoginAccess(String empId)throws SmartOfficeException{
		List<AuthUser> authUsers = authUserRepository.findByEmployeeId(empId);
		for(AuthUser user:authUsers) {
			user.setPassword(null);
			user.setUserStatus("INACTIVE");
			
//			for deleting authToke
			authTokenRepo.deleteByAuthId(user.getId());			
//			for deleting authRoles			
			authUserRoleRepository.deleteByAuthId(user.getId().toString());
			
			authUserRepository.save(user);
		}
		return authUsers;
	}
	
//	public ExitInterview completeExitInterview(@PathVariable(value="id")Integer id) {
//		ExitInterview exitInterviewById= exitInterviewRepository.findById(id).get();
//		
//		if(exitInterviewById.getStatus().equals("In Process")) {
//			exitInterviewById.setStatus("Completed");
//			
//		}else {
//			throw  new SmartOfficeException("Status should be In Process before Completing Interview");
//		}
//		
//		return exitInterviewRepository.save(exitInterviewById);
//	}

}

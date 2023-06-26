package com.ss.smartoffice.soservice.event.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.busconfig.ConfigBusHelper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.Employee;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalance;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalanceService;
import com.ss.smartoffice.soservice.master.employee.EmployeeProfileEventGenerator;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.seed.LeaveType.LeaveType;
import com.ss.smartoffice.soservice.seed.LeaveType.LeaveTypeService;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;
import com.ss.smartoffice.soservice.transaction.cashbalance.EmpCashBalService;
import com.ss.smartoffice.soservice.transaction.cashbalance.EmployeeCashBalance;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventService;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;
import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.Config;

@Service
public class EmployeeCreatedEventProcessor  {
	
	@Value("${user.url}")
	private String userUrl;
	
	@Value("${authuserrole.url}")
	private String authUserRoleUrl;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	NewEmailService newUserEmailService;
	
	@Autowired
	ConfigService configService;
	

	@Autowired
	LeaveTypeService leaveTypeService;

	@Autowired
	EmpCashBalService empCashBalService;
	
	@Autowired
	LeaveBalanceService leaveBalanceService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EventService eventService;

	@Autowired
	EmployeeProfileEventGenerator employeeProfileEventGenerator;
	
	 @Autowired
	 EventPublisherService eventPublisherService;
	 
	 @Autowired
	 CommonUtils commonUtils;
	 
	 @Autowired
	 UserRoleRefresher userRoleRefresher;
	 /*
	  * 0. set office from config if empty
	  * 1. create user
	  * 2. create user-role
	  * 3. create cash-balance from empty values
	  * 4. create leave-balance from config
	  * 5. cost center entry - TBD
	  */
	public Event process(Event event) {
		try {
			
			System.out.println("EmployeeCreatedEventProcessor - start");
			String employeeId = event.getBusinessKeys().get(0).getEmployeeId();			
			Optional<memployee> optionalEmployee = employeeService.getEmployeeById(Integer.parseInt(employeeId));
			memployee memployee = optionalEmployee.get();
			this.createUser(memployee,event);
			this.createEmpCashBalance(memployee);
			this.createEmpLeaveBalance(memployee);
			
			// create roles of this employee
			userRoleRefresher.refreshAuthUserRolesSynchronously(employeeId+"");
			// recreate roles of the employees associated with this employee
			for (String empId : commonUtils.findAssociatedEmployees(memployee)) {
				userRoleRefresher.refreshAuthUserRolesSynchronously(empId);
			};
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
			
			employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, "user-created", loggedInUser);
			

			} catch (Exception e) {
				e.printStackTrace();
				throw new SmartOfficeException(e.getLocalizedMessage());
			}
		
		return event;

	}
 
	private void createUser(memployee emp, Event event) {
		try {
			
			// create User object
			AuthUser user = new AuthUser();
			user.setEmployeeId(emp.getId().toString());
			user.setFirstName(emp.getFirstName());
			user.setLastName(emp.getLastName());
			user.setEmailId(emp.getContactEmailId());
			user.setMobileNumber(emp.getContactMobileNo());
			user.setEmpCode(emp.getEmpCode());
			user.setUserName(emp.getEmpCode());
			user.setPassword(String.valueOf(commonUtils.getPassword()));
			user.setEmpDesignation(emp.getDesigName());
			user.setAppClientId("pothigai-power");
			user.setGroupId("1");
			user.setUserAccessDt(LocalDateTime.now());
			user.setUserType("EMPLOYEE");
			user.setAcceptedAgmt("N");
			user.setUserStatus("ACTIVE");

			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",event.getAppToken());

			//create user
			HttpEntity<AuthUser> request = new HttpEntity<AuthUser>(user, headers);
			AuthUser savedUser = commonUtils.getRestTemplate().postForObject(userUrl, request, AuthUser.class);
			
			
			//construct AuthUserRole object
//			AuthUserRole authUserRole = new AuthUserRole();
//			authUserRole.setRoleId("6"); // TBD - this needs to be referred based on RoleCode
//			authUserRole.setAuthUserId(savedUser.getId().toString());
//			List<AuthUserRole> authUserRoleList = new ArrayList<AuthUserRole>();
//			authUserRoleList.add(authUserRole);
//			//create AuthUserRole with user-role as 'E' (employee)
//			HttpEntity<List<AuthUserRole>> reqAuthUserRoles = new HttpEntity<List<AuthUserRole>>(authUserRoleList, headers);
//			commonUtils.getRestTemplate().postForObject(authUserRoleUrl, reqAuthUserRoles, AuthUserRole[].class);
			
			
			
			//update userId in employee
			emp.setLoginUserId(savedUser.getId().toString());
			employeeRepository.save(emp);
			


		} catch (Exception e) {
			System.out.println("Error while creating user for EmployeeCreatedEvent");
			e.printStackTrace();
		}	
	}
	
	private void createEmpCashBalance(memployee emp) {
		try {
			EmployeeCashBalance employeeCashBalance = new EmployeeCashBalance();
			employeeCashBalance.setEmployeeId(emp.getId()+"");
			employeeCashBalance.setImportDt(LocalDate.now());
			employeeCashBalance.setPayToCompAmt("0");
			employeeCashBalance.setPayToEmpAmt("0");
			employeeCashBalance = empCashBalService.addEmployeeCashBalance(employeeCashBalance);
			System.out.println("employeeCashBalance-"+employeeCashBalance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createEmpLeaveBalance(memployee emp) {
		try {
			List<LeaveBalance> empLeaveBalances = new ArrayList<LeaveBalance>();
			Iterable<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes();
			Iterable<Config> configs = configService.getConfig("", "DEFAULT-LEAVE-BALANCES"); 
//			System.out.println("leave configs-"+configs);
//			System.out.println("leaveTypes-"+leaveTypes);
			LeaveBalance newleaveBalance=new LeaveBalance();
			for (LeaveType leaveType : leaveTypes) {
				try {
					newleaveBalance=new LeaveBalance();
					newleaveBalance.setEmployeeId(emp.getId()+"");
					newleaveBalance.setFiscalYearId("1");  // TBD - to be pulled based fiscalyear service
					newleaveBalance.setCarriedOver("0");
					for (Config config : configs) { // TBD - use streams here
						if(config.getConfigDtlCode().equalsIgnoreCase(leaveType.getLeaveTypeCode())) {
							newleaveBalance.setLeaveTypeId(leaveType.getId()+"");
							newleaveBalance.setLeaveBalance(config.getConfigDtlValue());
							break;
						}
					}
					if(newleaveBalance.getLeaveTypeId() != null) {
						empLeaveBalances.add(newleaveBalance);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("continuing to next leavetype");
				}
			}
//			System.out.println("empLeaveBalances-"+empLeaveBalances);
			if(!empLeaveBalances.isEmpty()) {
				Iterable<LeaveBalance> savedLb = leaveBalanceService.bulkaddAndupdate(empLeaveBalances);

//				System.out.println("savedLb-"+savedLb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

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
import com.ss.smartoffice.shared.util.AuthHelper;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalance;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalanceService;
import com.ss.smartoffice.soservice.master.employee.EmpCodeGeneration;
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
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventService;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;
import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.Config;

@Service
public class EmployeeUpdatedByHrEventProcessor  {
	
	@Value("${user.url}")
	private String userUrl;
	
	@Value("${authuserrole.url}")
	private String authUserRoleUrl;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	ConfigService configService;
	
	@Autowired
	EmpCodeGeneration empCodeGeneration;
	@Autowired
	EmployeeProfileEventGenerator employeeProfileEventGenerator;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	UserRoleRefresher userRoleRefresher;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	AuthHelper authHelper;
	 
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
			
			System.out.println("EmployeeUpdatedByHrEventProcessor - start");
			String employeeId = event.getBusinessKeys().get(0).getEmployeeId();
			Optional<memployee> optionalEmployee = employeeService.getEmployeeById(Integer.parseInt(employeeId));
			
			memployee memployee = optionalEmployee.get();
			
			//TBD - call auth-user-role-refresh
			userRoleRefresher.refreshAuthUserRolesSynchronously(employeeId);
			// refresh roles of the employees associated with this employees
			for (String empId : commonUtils.findAssociatedEmployees(memployee)) {
				userRoleRefresher.refreshAuthUserRolesSynchronously(empId);
			};
			if(event.getEventKeyValues()!=null) {
				for(EventKeyValue eventKeyValue:event.getEventKeyValues()) {
					if(eventKeyValue.getKeyPair().equals("office-change")&&eventKeyValue.getValue().equals("Y")) {
						String employeeCode=empCodeGeneration.empCodeGenerationByOffice(memployee);
						employeeProfileEventGenerator.triggerEmpProfileEvents(memployee, "office-change", authHelper.getUserByToken(event.getAppToken()));
			} 
				}
			}
		}catch (Exception e) {
				e.printStackTrace();
				throw new SmartOfficeException(e.getLocalizedMessage());
			}
		
		return event;

	}
 
}

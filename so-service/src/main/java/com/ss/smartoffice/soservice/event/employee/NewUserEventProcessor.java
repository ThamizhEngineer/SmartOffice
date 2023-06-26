package com.ss.smartoffice.soservice.event.employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.Employee;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventService;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;
import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;
import com.ss.smartoffice.shared.model.AuthUser;

@Service
public class NewUserEventProcessor  {
	
	@Value("${user.url}")
	private String userUrl;

	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	NewEmailService newUserEmailService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EventService eventService;

//	@Autowired
//	NewUserEmailService newUserEmailService;
	
	 @Autowired
	 EventPublisherService eventPublisherService;
	 
	 @Autowired
	 CommonUtils commonUtils;
	 
	public Event process(Event event) {
		try {
			

			
			System.out.println("hi in buisness queue");
			String employeeId = event.getBusinessKeys().get(0).getEmployeeId();
			System.out.println(employeeId);
			Optional<memployee> optionalEmployee = employeeService.getEmployeeById(Integer.parseInt(employeeId));
			memployee memployee = optionalEmployee.get();
			System.out.println("employee"+memployee);
			AuthUser user = new AuthUser();
			user.setEmployeeId(memployee.getId().toString());
			user.setFirstName(memployee.getEmpName());
			user.setEmailId(memployee.getContactEmailId());
			user.setEmpCode(memployee.getEmpCode());
			user.setUserName(memployee.getContactMobileNo());
			user.setPassword(memployee.getDob().replaceAll("-", ""));
			user.setAppClientId("pothigai-power");
			user.setGroupId("1");
			user.setUserAccessDt(LocalDateTime.now());
			user.setUserType("EMPLOYEE");
			user.setAcceptedAgmt("N");
			user.setUserStatus("ACTIVE");
			
			// ...

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",event.getAppToken());      

			HttpEntity<AuthUser> request = new HttpEntity<AuthUser>(user, headers);

			AuthUser savedUser = commonUtils.getRestTemplate().postForObject(userUrl, request, AuthUser.class);
			
			memployee.setLoginUserId(savedUser.getId().toString());
			employeeRepository.save(memployee);

			
			
				
				if (event.getBusinessKeys()!= null) {
					
					
					System.out.println(employeeId);
					Employee employee1 =new Employee();
					
					headers.set("Authorization",event.getAppToken());      

					HttpEntity<Employee> request1 = new HttpEntity<Employee>(employee1, headers);
//					ResponseEntity<Employee> employee = commonUtils.getRestTemplate().exchange(employeeUrl+employeeId,
//							HttpMethod.GET, request1,Employee.class);
					
				memployee employeeGet=employeeService.getEmployeeById(Integer.parseInt(employeeId)).get();
//					memployee employee = commonUtils.getRestTemplate().getForObject(employeeUrl+employeeId,request,
//							memployee.class);
				
//					Employee employeeGet =employee.getBody();
					
					System.out.println("employeeGet"+employeeGet);
					try {
						newUserEmailService.sendMailNewUser(employeeGet);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new SmartOfficeException("event keyvalues is empty ");
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new SmartOfficeException(e.getLocalizedMessage());
			}
			
		
		return event;

	}
	
}

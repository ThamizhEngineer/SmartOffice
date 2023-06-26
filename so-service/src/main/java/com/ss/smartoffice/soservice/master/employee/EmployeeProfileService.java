package com.ss.smartoffice.soservice.master.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.print.PrintBusHelper;
import com.ss.smartoffice.soservice.event.employee.UserRoleRefresher;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest;

@Controller
@RequestMapping(path="master/emp-profiles")
public class EmployeeProfileService {
	
	@Value("${sys-user.token}")
	private String sysUsrToken;
	@Value("${sys-user.authId}")
	private String sysUsrAuthId;
	@Autowired
	CommonUtils commonutils;
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;
	@Autowired
	PrintBusHelper printBusHelper;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	AuthUserRepository authUserRepository;
	@Autowired
	EventPublisherService eventPublisherService;
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	
	@Autowired
	EmployeeProfileBusHelper employeeProfileBusHelper;
	
	@Autowired
	UserRoleRefresher userRoleRefresher;
	
	ArrayList<String> knownActions = new ArrayList<String>( Arrays.asList("create","onboard","complete-profile","profile-update","profile-validate","skill-update","skill-validate","official-info","profile-update-by-hr"));
	@GetMapping
	public List<memployee> getAllEmployees(Model model)throws SmartOfficeException{
		List<memployee> allEmployees = (List<memployee>) employeeRepository.findAll();
		
	Map<String, String> printAttributes = new HashMap<>();
		
		printAttributes.put("EmpName", "empName");
		printAttributes.put("EmpCode", "empCode");
		model.addAttribute("dataKeyName", "memployeeList");
		model.addAttribute("dataType", "memployee");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "my-emp");
		return allEmployees;
	}
	@GetMapping("/_internal/{id}")
	public Optional<memployee>getEmpByIdInternal(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return employeeRepository.findById(id);
	}
	@PostMapping("/_internal/{id}/app-pdf-emp-profile")
    public memployee updateempByPdfId(@RequestBody memployee employee) throws SmartOfficeException {
		memployee memployeeFromDb=getEmpByIdInternal(employee.getId()).get();
		memployeeFromDb.setEmpProfilePdfId(employee.getEmpProfilePdfId());
		return employeeRepository.save(memployeeFromDb);
		
	}
	
	
	@GetMapping("/{id}")
	public Optional<memployee>getEmpById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return employeeRepository.findById(id);
	}
	@GetMapping("/complete-profile")
	public List<memployee> getEmpProfileApprovedByDirector()throws SmartOfficeException{
		String empId=commonutils.getLoggedinEmployeeId();
		return employeeRepository.findBynewJoineeEmpId(Integer.parseInt(empId));
	}
	@GetMapping("/hr1-profile-validate")
	public List<memployee> getEmpProfileToBeValidatedByHr1(@RequestParam("empName")String empName,@RequestParam("desigName")String desigName,
			@RequestParam("emailId")String emailId,@RequestParam("contactMobileNo")String contactMobileNo,
			@RequestParam("officeName")String officeName,@RequestParam("empCode")String empCode,@RequestParam("empStatus")String empStatus,
			@RequestParam("id")String id,@RequestParam("n1EmpId")String n1EmpId,@RequestParam("n2EmpId")String n2EmpId)throws SmartOfficeException{

		if(commonutils.isSuperUser()) {
			return employeeRepository.findRegEmpSummaries();
		}
		else {
			List<memployee> emps = new ArrayList<memployee>();
			List<String> hr1UgIds = userGroupEmployeeMappingService.getUserGroupHrId();
			if(hr1UgIds!=null && !hr1UgIds.isEmpty()) {
				emps = employeeRepository.findByHr1UserGroupIdIn(hr1UgIds,isNullOrEmpty(empName),isNullOrEmpty(desigName),isNullOrEmpty(emailId),
						isNullOrEmpty(contactMobileNo),isNullOrEmpty(officeName),isNullOrEmpty(empCode),isNullOrEmpty(empStatus),isNullOrEmpty(id),isNullOrEmpty(n1EmpId),isNullOrEmpty(n2EmpId));
			}
			return emps;
		}
	}
	@GetMapping("/n1-skill-validate")
	public List<memployee> getEmpSkillToBeValidatedByN1()throws SmartOfficeException{
		String n1EmpId=commonutils.getLoggedinEmployeeId();
		return employeeRepository.findByN1EmpId(n1EmpId);
	}
	//apply
	@PostMapping("/{action}")
	public memployee apply(@PathVariable(value="action")String action,@RequestBody memployee memployee)throws SmartOfficeException{
		System.out.println(action);
		memployee=checkLegalPending(memployee);
		if(knownActions.contains(action)) {
			return employeeProfileBusHelper.processEmpProfile(null, action, memployee);

		}else {
			throw new SmartOfficeException("Invalid Url");
		}
	}
	@GetMapping("/create-auth-emp/{id}")
	public Map<String, String> createAuthEmp(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		Map<String, String> message =  new HashMap<String, String>();
		memployee emp = employeeRepository.findById(id).get();
		List<AuthUser> authUser = authUserRepository.findByEmployeeId(id.toString());
		if(authUser.isEmpty()) {
			if(emp.getEmpCode()!=null && emp.getEmailId()!=null && emp.getOfficeId()!=null && emp.getHr1UsrGrpId() !=null && emp.getN1EmpId()!=null && emp.getN2EmpId()!=null && emp.getShiftId()!=null) {
				employeeProfileBusHelper.createLoginAccessForEmp(emp);
				message.put("message","Login Access created Successfully");
				return message;
			}else {
				message.put("message","Official Information Is Pending");
				return message;
			}
		}else {
			message.put("message","Login Access Already Present for this User");
			return message;
		}
	}
	
	
	public memployee checkLegalPending(@RequestBody memployee memployee) {
		if(memployee.getPfNo()==null||memployee.getEsiNo()==null||memployee.getUanNo()==null) {
			memployee.setLegalPending("Y");
			
		}else {
			memployee.setLegalPending("N");
		}
//		employeeRepository.save(memployee);
		return memployee;
	}
	
	@PatchMapping("/emp-forgot-username-pwd/_internal")
	public String forgotUserNamePwd(@RequestParam(value="emailId")String emailId)throws SmartOfficeException{
		try {
			System.out.println(emailId);
			
			List<memployee>applicants=(List<memployee>) employeeRepository.findAll();
			List<String> emails=applicants.stream().filter(x->x.getContactEmailId()!=null).map(x -> x.getContactEmailId())
				.collect(Collectors.toList());
			System.out.println("emails" +emails);
			if(emails.contains(emailId)) {
				memployee emp=employeeRepository.findByContactEmailId(emailId);
				System.out.println(emp);
				triggerForgotPwdEvent(emp);
			
			}else {
				return "EmailId Doesnot Exists";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Password Has been Sent to your email address.Please do check it";
	}


	private void triggerForgotPwdEvent(memployee emp) {
		// TODO Auto-generated method stub
		try {
			AuthUser loggedInUser = new AuthUser();
			loggedInUser.setEmployeeId(sysUsrAuthId);
			loggedInUser.setId(Integer.parseInt(sysUsrAuthId));
			commonutils.setAuthenticationContext(Integer.parseInt(sysUsrAuthId),"async");
			Event event = new Event();
			event.setName(Event.EventTypes.EmployeeForgotPwdEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setAppToken(sysUsrToken);
			event.setContextAuthUserId(Integer.parseInt(sysUsrAuthId));
			Event savedEvent = eventRepository.save(event);
			System.out.println("sa" +savedEvent);
			List<BusinessKey>businessKeys= new ArrayList<BusinessKey>();
			BusinessKey businessKey = new BusinessKey();
			businessKey.setEmployeeId(String.valueOf(emp.getId()));
			businessKey.setEventId(savedEvent.getId().toString());
			businessKeyRepository.save(businessKey);
			businessKeys.add(businessKey);
			event.setBusinessKeys(businessKeys);
			List<EventKeyValue> eventKeyValues = new ArrayList<>();
			EventKeyValue eventKeyValue = new EventKeyValue();
			eventKeyValue.setEventId(savedEvent.getId().toString());
			eventKeyValue.setKeyPair("employeeName");
			eventKeyValue.setValue(emp.getFirstName()+emp.getLastName());
			eventKeyValueRepository.save(eventKeyValue);
			eventKeyValues.add(eventKeyValue);
			AuthUser authUser=authUserRepository.findByEmployeeId(emp.getId().toString()).get(0);
			if(authUser.getPassword()==null||authUser.getPassword().isEmpty()) {
				throw new SmartOfficeException("Password Is Empty");
			}
			else {
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(savedEvent.getId().toString());
				eventKeyValue1.setKeyPair("password");
				eventKeyValue1.setValue(authUser.getPassword());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(savedEvent.getId().toString());
				eventKeyValue2.setKeyPair("userName");
				eventKeyValue2.setValue(authUser.getUserName());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
			}
			event.setEventKeyValues(eventKeyValues);
			eventPublisherService.pushEvent(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//processApproval
	@PatchMapping("{id}/{action}")
	public memployee processApproval(@PathVariable(value="id")Integer id,@PathVariable(value="action")String action,@RequestBody memployee memployee)throws SmartOfficeException{
		if(knownActions.contains(action)) {
			checkLegalPending(memployee);
			return employeeProfileBusHelper.processEmpProfile(id, action, memployee);
		}else {
			throw new SmartOfficeException("Invalid Url");
		}
		
	}
	
	@PatchMapping("/refresh-user-roles")
	public String refreshRoles(@RequestBody List<String> employeeIds){
		
		try {
			for (String employeeId : employeeIds) {
				userRoleRefresher.refreshAuthUserRoles(employeeId, commonutils.getLoggedInUser());	
			}
			return "initiated - please check server logs for status";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}
	}
    public String isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return str;
        return "";
    }
	
	
}

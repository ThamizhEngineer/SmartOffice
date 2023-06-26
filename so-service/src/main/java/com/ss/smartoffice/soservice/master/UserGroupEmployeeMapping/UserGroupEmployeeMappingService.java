package com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepo;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;
import com.ss.smartoffice.shared.repos.UserGroupEmployeeMappingRepository;
import com.ss.smartoffice.shared.model.AuthUserSummary;
import com.ss.smartoffice.soservice.event.employee.UserRoleRefresher;

@RestController
@RequestMapping(path="master/user-group-emp-mappings")
@Scope("prototype")
public class UserGroupEmployeeMappingService {
@Autowired
UserGroupEmployeeMappingRepository userGroupEmployeeMappingRepository;
@Autowired
CommonUtils commonUtils;

@Autowired
UserRoleRefresher userRoleRefresher;

@Autowired
AuthUserRepository authUserRepository;
@Autowired
AuthUserRepo authUserRepo;
@GetMapping
public Iterable<UserGroupEmployeeMapping> getAllUserGroupEmployee() throws SmartOfficeException{
	return userGroupEmployeeMappingRepository.findAll();
}
@GetMapping("/user-grp-emp-id")
public List<String> getUserGroupEmployeeById()throws SmartOfficeException{
	String employeeId=commonUtils.getLoggedinEmployeeId();
	System.out.println("employeeId-"+employeeId);
	List<UserGroupEmployeeMapping>allUserGroupsbyEmployeeId=userGroupEmployeeMappingRepository.findByEmployeeId(employeeId);
	List<UserGroupEmployeeMapping>acctL2List= new ArrayList<UserGroupEmployeeMapping>();
	List<String> allUserGroupIds = new ArrayList<String>();
	for(UserGroupEmployeeMapping userGroupEmployeeMapping:allUserGroupsbyEmployeeId) {
		if(userGroupEmployeeMapping.getIsAcctL2().equals("Y")) {
			acctL2List.add(userGroupEmployeeMapping);
			allUserGroupIds.add(userGroupEmployeeMapping.getUserGroupId());
		}
//		for(UserGroupEmployeeMapping userGrpId:acctL2List) {
//			allUserGroupIds.add(userGrpId.getUserGroupId());
//		}
		
	}
	System.out.println("allUserGroupIds-"+allUserGroupIds);
	return allUserGroupIds;
}
@GetMapping("/emp-id/{employeeId}")
public List<UserGroupEmployeeMapping>getUserGroupBasedOnEmployeeId(@PathVariable(value="employeeId")String employeeId)throws SmartOfficeException{
	return userGroupEmployeeMappingRepository.findByEmployeeId(employeeId);
}
@GetMapping("/user-grp-emp-hr-id")
public List<String> getUserGroupHrId()throws SmartOfficeException{
	String employeeId=commonUtils.getLoggedinEmployeeId();
	List<UserGroupEmployeeMapping>allUserGroupsbyEmployeeId=userGroupEmployeeMappingRepository.findByEmployeeId(employeeId);
	List<UserGroupEmployeeMapping>hrL1List= new ArrayList<UserGroupEmployeeMapping>();
	List<String> allUserGroupIds = new ArrayList<String>();
	for(UserGroupEmployeeMapping userGroupEmployeeMapping:allUserGroupsbyEmployeeId) {
		
		if(userGroupEmployeeMapping.getIsHrL1()!=null && userGroupEmployeeMapping.getIsHrL1().equals("Y")) {
			hrL1List.add(userGroupEmployeeMapping);
			allUserGroupIds.add(userGroupEmployeeMapping.getUserGroupId());
			
		}
//		for(UserGroupEmployeeMapping userGrpId:hrL1List) {
//			allUserGroupIds.add(userGrpId.getUserGroupId());
//		}
		
	}
	System.out.println("groupsReturned-"+allUserGroupIds);
	return allUserGroupIds;
}
@GetMapping("/user-grp-emp-hr2-id")
public List<String> getUserGroupHr2Id()throws SmartOfficeException{
	String employeeId=commonUtils.getLoggedinEmployeeId(); 
	List<UserGroupEmployeeMapping>allUserGroupsbyEmployeeId=userGroupEmployeeMappingRepository.findByEmployeeId(employeeId);
	List<UserGroupEmployeeMapping>hrL1List= new ArrayList<UserGroupEmployeeMapping>();
	List<String> allUserGroupIds = new ArrayList<String>();
	for(UserGroupEmployeeMapping userGroupEmployeeMapping:allUserGroupsbyEmployeeId) {
		
		if(userGroupEmployeeMapping.getIsHrL2()!=null && userGroupEmployeeMapping.getIsHrL2().equals("Y")) {
			hrL1List.add(userGroupEmployeeMapping);
			allUserGroupIds.add(userGroupEmployeeMapping.getUserGroupId());
			
		}
//		for(UserGroupEmployeeMapping userGrpId:hrL1List) {
//			allUserGroupIds.add(userGrpId.getUserGroupId());
//		}
		
	}
	System.out.println("groupsReturned-"+allUserGroupIds);
	return allUserGroupIds;
}
@GetMapping("/user-grp-emp-acc1-id")
public List<String> getUserGroupAcc1Id()throws SmartOfficeException{
	String employeeId=commonUtils.getLoggedinEmployeeId();
	System.out.println("hrgroup-"+employeeId);
	List<UserGroupEmployeeMapping>allUserGroupsbyEmployeeId=userGroupEmployeeMappingRepository.findByEmployeeId(employeeId);
	List<UserGroupEmployeeMapping>hrL1List= new ArrayList<UserGroupEmployeeMapping>();
	List<String> allUserGroupIds = new ArrayList<String>();
	for(UserGroupEmployeeMapping userGroupEmployeeMapping:allUserGroupsbyEmployeeId) {
		
		if(userGroupEmployeeMapping.getIsAcctL1()!=null && userGroupEmployeeMapping.getIsAcctL1().equals("Y")) {
			hrL1List.add(userGroupEmployeeMapping);
			allUserGroupIds.add(userGroupEmployeeMapping.getUserGroupId());
			
		}
//		for(UserGroupEmployeeMapping userGrpId:hrL1List) {
//			allUserGroupIds.add(userGrpId.getUserGroupId());
//		}
		
	}
	return allUserGroupIds;
}

@GetMapping("/get-user-group/{type}")
public List<String> getUserGroups(@PathVariable(value="type")String type){
	List<String> list = new ArrayList<String>();
	
	if(type.equals("hrgroup")) {
		list = getUserGroupHrId();
	}
	else if(type.equals("usergroup")) {
		list = getUserGroupEmployeeById();
	}
	return list;
}

@GetMapping("/{id}")
public UserGroupEmployeeMapping getUserGroupEmployeeMappingById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
	
	
	
	UserGroupEmployeeMapping userGroupEmployeeMappings = userGroupEmployeeMappingRepository.findById(id).get();
	
	return userGroupEmployeeMappings;

}

@GetMapping("/userGroupId/{userGroupId}")
public List<UserGroupEmployeeMapping> getUserGroupEmployeeMappingByInternal(@PathVariable(value="userGroupId")String userGroupId) throws SmartOfficeException{
	System.out.println(userGroupId);
	List<UserGroupEmployeeMapping> userGroupEmployeeMappings = userGroupEmployeeMappingRepository.findByUserGroupId(userGroupId);
	List<AuthUserSummary>authusersList= new ArrayList<>();
	for(UserGroupEmployeeMapping userGroupEmployeeMapping:userGroupEmployeeMappings) {
	AuthUserSummary AuthUserSummary=authUserRepo.findById(Integer.valueOf(userGroupEmployeeMapping.getAuthUserId())).get();

	authusersList.add(AuthUserSummary);
	userGroupEmployeeMapping.setAuthUser(AuthUserSummary);
	System.out.println(AuthUserSummary.getEmailId()+"getUserGroupEmployeeMappingByInternal");
	}
	
	
	return userGroupEmployeeMappings;
}
@PostMapping
public UserGroupEmployeeMapping addUserGroupEmployeeMapping(@RequestBody UserGroupEmployeeMapping userGroupEmployeeMapping )throws SmartOfficeException{
	
	AuthUser authUser=authUserRepository.findByEmployeeId(userGroupEmployeeMapping.getEmployeeId()).get(0);
	userGroupEmployeeMapping.setAuthUserId(authUser.getId().toString());
	userGroupEmployeeMapping.setCreatedBy(commonUtils.getLoggedinEmployeeId());
	userGroupEmployeeMapping.setCreatedDt(LocalDateTime.now());
	UserGroupEmployeeMapping ugp = userGroupEmployeeMappingRepository.save(userGroupEmployeeMapping);
	List<String> employeeIds = new ArrayList<String>();



	employeeIds.add(userGroupEmployeeMapping.getEmployeeId()+"");
	refreshRoles(employeeIds);
	return ugp;
	
	
	
//	AuthUser au = getAuthUser(employeeId);
//	for (AuthUserRole authUserRole : au.getAuthUserRoles()) {		//TODO - use streams filter
//		if(authUserRole.getAuthRoleCode()!=null && authUserRole.getAuthRoleCode().equalsIgnoreCase("SU")) {
//			isSuperUser = true;
//			break;
//		}
//	}
}
@PatchMapping("/bulk-update")
public Iterable<UserGroupEmployeeMapping> bulkAddandUpdate(@RequestBody List<UserGroupEmployeeMapping> userGroupEmployeeMappings) throws SmartOfficeException {
	List<UserGroupEmployeeMapping> userGroupEmployeeMappingList = new ArrayList<UserGroupEmployeeMapping>();
	List<String> employeeIds = new ArrayList<String>();
	for(UserGroupEmployeeMapping userGroupEmployeeMapping:userGroupEmployeeMappings) {
		if(userGroupEmployeeMapping.getId() > 0) {
			UserGroupEmployeeMapping userGroupEmployeeFromDB=userGroupEmployeeMappingRepository.findById(userGroupEmployeeMapping.getId()).orElse(new UserGroupEmployeeMapping());
			userGroupEmployeeFromDB=this.matchAndUpdateFields(userGroupEmployeeFromDB, userGroupEmployeeMapping);
		}else {
			userGroupEmployeeMapping=addingNewRecord(userGroupEmployeeMapping);
		}
		userGroupEmployeeMapping.setIsEnabled("Y");
		userGroupEmployeeMappingList.add(userGroupEmployeeMapping);
		employeeIds.add(userGroupEmployeeMapping.getEmployeeId()+"");
	}
	Iterable<UserGroupEmployeeMapping> ugpList = (Iterable<UserGroupEmployeeMapping>) userGroupEmployeeMappingRepository.saveAll(userGroupEmployeeMappingList);
	refreshRoles(employeeIds);
	
	 return ugpList;

}
private UserGroupEmployeeMapping addingNewRecord(UserGroupEmployeeMapping userGroupEmployeeMapping) {
	UserGroupEmployeeMapping newUserGroupEmployeeMapping = new UserGroupEmployeeMapping();
	newUserGroupEmployeeMapping.setUserGroupId(userGroupEmployeeMapping.getUserGroupId());
	newUserGroupEmployeeMapping.setEmployeeId(userGroupEmployeeMapping.getEmployeeId());	
	AuthUser authUser=authUserRepository.findByEmployeeId(newUserGroupEmployeeMapping.getEmployeeId()).get(0);
	newUserGroupEmployeeMapping.setAuthUserId(authUser.getId().toString());
	newUserGroupEmployeeMapping.setModifiedBy(commonUtils.getLoggedinEmployeeId());
	newUserGroupEmployeeMapping.setModifiedDt(LocalDateTime.now());
	return newUserGroupEmployeeMapping;
}
private UserGroupEmployeeMapping matchAndUpdateFields(UserGroupEmployeeMapping userGroupEmployeeFromDB,
		UserGroupEmployeeMapping userGroupEmployeeMapping) {
	userGroupEmployeeFromDB.setUserGroupId(userGroupEmployeeMapping.getUserGroupId());
	userGroupEmployeeFromDB.setEmployeeId(userGroupEmployeeMapping.getEmployeeId());
	AuthUser authUser=authUserRepository.findByEmployeeId(userGroupEmployeeFromDB.getEmployeeId()).get(0);
	
	userGroupEmployeeFromDB.setAuthUserId(authUser.getId().toString());
	userGroupEmployeeFromDB.setModifiedBy(commonUtils.getLoggedinEmployeeId());
	userGroupEmployeeFromDB.setModifiedDt(LocalDateTime.now());
	return userGroupEmployeeFromDB;
}

@DeleteMapping("/{id}")
public void deleteUserGroupEmployeeMappingById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	userGroupEmployeeMappingRepository.deleteById(id);
}

private String refreshRoles(@RequestBody List<String> employeeIds){
	
	try {
		for (String employeeId : employeeIds) {
			userRoleRefresher.refreshAuthUserRoles(employeeId, commonUtils.getLoggedInUser());	
		}
		return "initiated - please check server logs for status";
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "failure";
	}
}

}

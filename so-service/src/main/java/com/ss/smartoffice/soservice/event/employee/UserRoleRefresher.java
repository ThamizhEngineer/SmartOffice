package com.ss.smartoffice.soservice.event.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;
import com.ss.smartoffice.shared.repos.UserGroupEmployeeMappingRepository;

@Service
public class UserRoleRefresher {

@Value("${user.url}")
private String userUrl;


@Value("${authuserrole.url}")
private String authUserRoleUrl;

@Autowired
CommonUtils commonUtils;


@Autowired
EmployeeService employeeService;

@Autowired
UserGroupEmployeeMappingRepository userGroupEmployeeMappingRepository;

	@Async("asyncThreadPoolTaskExecutor")
	public void refreshAuthUserRoles(String employeeId, AuthUser loggedInUser) {
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // sleep for 2 seconds
			//establish contet for login
//			SecurityContextHolder.getContext().setAuthentication(commonUtils.getAuthObject(loggedInUser));
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			refreshAuthUserRolesSynchronously(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshAuthUserRolesSynchronously(String employeeId) {
		try {
			
			System.out.println("UserRoleRefresher - This function might take long time to complete");

			refreshRolesForAnEmployee(employeeId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void refreshRolesForAnEmployee(String employeeId) {
		try {
			
			System.out.println("Refreshing roles for - "+employeeId);
			//get Employee by authuser.empId
				memployee employee = employeeService.getEmployeeById(Integer.parseInt(employeeId)).get();
			//get UserGroups by employeeId
				List<UserGroupEmployeeMapping> assignedUserGroupMappings = userGroupEmployeeMappingRepository.findByEmployeeId(employeeId+"");
			// Motto is make sure all authUserRoles and authUserGroups are assigned to an employee
			// based on his/her n1, n2 responsibility and hr1, hr2, acc1, acc2 responsibility
			
			List<String> rolesNeeded = new ArrayList<String>();
			List<String> rolesToAdd = new ArrayList<String>();
			//if emp is of type E, then
				if(employee.getEmpTypeCode()!=null && employee.getEmpTypeCode().equalsIgnoreCase("REGULAR")) {
					rolesNeeded.add("E");
				}
			//if already has SU role, then
				boolean isSuperUser = false;
				
				AuthUser au = getAuthUser(employeeId);
				for (AuthUserRole authUserRole : au.getAuthUserRoles()) {		//TODO - use streams filter
					if(authUserRole.getAuthRoleCode()!=null && authUserRole.getAuthRoleCode().equalsIgnoreCase("SU")) {
						isSuperUser = true;
						break;
					}
				}
				if(isSuperUser)
					rolesNeeded.add("SU"); // then add SU to the list rolesNeeded
				

				//if the given employee is n1 for any  employee,	
				if(!employeeService.findEmployeeByN1EmpId(employeeId).isEmpty()) {
					// then add N1 to the list rolesNeeded
					rolesNeeded.add("N1");
				}
				
				//if the given employee is n2 for any  employee,	
				if(!employeeService.findEmployeeByN2EmpId(employeeId).isEmpty()) {
					// then add N1 to the list rolesNeeded
					rolesNeeded.add("N2");
				}
			
				System.out.println("rolesNeeded-"+rolesNeeded);
			//Has hr1UserGroups
				// then add HR1 to the list rolesNeeded
			//Has hr2UserGroupsemployee
				// then add HR2 to the list rolesNeeded
			//Has acc1UserGroups
				// then add ACC1 to the list rolesNeeded
			//Has acc2UserGroups
				// then add ACC2 to the list rolesNeeded
			//Has directorUserGroups
				// then add D to the list rolesNeeded
				
				boolean isHr1 = false, isHr2 = false ,isAcc1 = false, isAcc2 = false, isDirector = false, isMgnt1 = false;
				//
				for (UserGroupEmployeeMapping assignedUG : assignedUserGroupMappings) {
					if(assignedUG.getIsHrL1()!=null && assignedUG.getIsHrL1().equalsIgnoreCase("Y")){
						isHr1 = true;
					}
					if(assignedUG.getIsHrL2()!=null && assignedUG.getIsHrL2().equalsIgnoreCase("Y")){
						isHr2 = true;
					}
					if(assignedUG.getIsAcctL1()!=null && assignedUG.getIsAcctL1().equalsIgnoreCase("Y")){
						isAcc1 = true;
					}
					if(assignedUG.getIsAcctL2()!=null && assignedUG.getIsAcctL2().equalsIgnoreCase("Y")){
						isAcc2 = true;
					}
					if(assignedUG.getIsDir()!=null && assignedUG.getIsDir().equalsIgnoreCase("Y")){
						isDirector = true;
					}
					if(assignedUG.getIsMgnt()!=null && assignedUG.getIsMgnt().equalsIgnoreCase("Y")){
						isMgnt1 = true;
					}
					
				}
				if(isHr1)
					rolesNeeded.add("HR1");
				if(isHr2)
					rolesNeeded.add("HR2");
				if(isAcc1)
					rolesNeeded.add("ACC1");
				if(isAcc2)
					rolesNeeded.add("ACC2");
				if(isDirector)
					rolesNeeded.add("D");
				if(isMgnt1)
					rolesNeeded.add("M");
				System.out.println("rolesNeeded-"+rolesNeeded);
			
			// loop through all authUserRoles for the authUser
				for (AuthUserRole authUserRole : au.getAuthUserRoles()) {
					// if authCode is not in rolesNeeded
//					if(!rolesNeeded.contains(authUserRole.getAuthRoleCode())) {
						// then delete that authUserRoles
						deleteAuthUserRole(authUserRole.getId()+"");
//					}
				}
			
				
				au = getAuthUser(employeeId);
			// loop through rolesNeeded
				boolean addRole = false;
				for (String neededRole : rolesNeeded) {
					 addRole = true;
					// if its not available in authUserRole
//					for (AuthUserRole authUserRole : au.getAuthUserRoles()) {
//						if(neededRole.equalsIgnoreCase(authUserRole.getAuthRoleCode())) {
//							addRole = false;
//							break;
//						}
//					}
					// then add that authUserRole
					if(addRole) {
						rolesToAdd.add(neededRole);
					}
				}
				System.out.println("rolesToAdd-"+rolesToAdd);
			// add AuthUserRoles for that user
				addAuthUserRoles(rolesToAdd, au.getId()+"");	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private AuthUser getAuthUser(String employeeId) {
		String url=userUrl+"/getByEmpId/"+employeeId+"/_internal";
		
		try {
			List<AuthUser> authUsers = Arrays.asList(commonUtils.getRestTemplate().getForObject(url,AuthUser[].class));
			return authUsers.get(0);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private void deleteAuthUserRole(String authUserRoleId) {
		String url=authUserRoleUrl+"/"+authUserRoleId+"/_internal";
		System.out.println("url-"+url);
		
		try {
			commonUtils.getRestTemplate().delete(url);
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private AuthUserRole[] addAuthUserRoles(List<String> roleCodes, String authUserId) {
		

		try {

			AuthUserRole authUserRole = new AuthUserRole();
			List<AuthUserRole> authUserRoleList = new ArrayList<AuthUserRole>();
			for (String rc : roleCodes) {
				authUserRole = new AuthUserRole();
				authUserRole.setAuthRoleId(Integer.parseInt(commonUtils.getRoleId(rc)));
				authUserRole.setAuthUserId(authUserId);
				authUserRoleList.add(authUserRole);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken());

			HttpEntity<List<AuthUserRole>> reqAuthUserRoles = new HttpEntity<List<AuthUserRole>>(authUserRoleList, headers);
			return commonUtils.getRestTemplate().postForObject(authUserRoleUrl, reqAuthUserRoles, AuthUserRole[].class);
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}

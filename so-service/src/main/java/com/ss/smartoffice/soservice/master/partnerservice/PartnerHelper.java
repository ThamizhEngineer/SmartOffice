package com.ss.smartoffice.soservice.master.partnerservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.partner.PartnerEmployee;


@Service
public class PartnerHelper {

	@Autowired
	AuthUserRepository authUserRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	PartnerEventGenerator partnerEventGenerator;
	
	public List<PartnerEmployee> processAuthPartner(List<PartnerEmployee> partnerEmployees)throws SmartOfficeException{
		
		try {
			AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
			for(PartnerEmployee partnerEmployee:partnerEmployees) {				
				List<AuthUser> authusers = authUserRepository.findByPartnerId(partnerEmployee.getId().toString());				
				if(authusers.isEmpty()) {
//					create Auth User	
					createAuthPartner(partnerEmployee,loggedInUser);
				}else {
//					update check
					updateAuthPartner(authusers, partnerEmployee);
				}				
			}
			return partnerEmployees;
			
		}catch (Exception e) {			
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : PartnerHelper - Create Auth User For Partner"); 
		}				
	}
	
	@Async("asyncThreadPoolTaskExecutor")
	public void createAuthPartner(PartnerEmployee partnerEmployee,AuthUser loggedInUser)throws SmartOfficeException{
		AuthUser authUser = new AuthUser();
		AuthUserRole authUserRole = new AuthUserRole();
		List<AuthUserRole> authUserRoles = new ArrayList<AuthUserRole>();				
		authUser.setPartnerId(String.valueOf(partnerEmployee.getId()));
		authUser.setFirstName(partnerEmployee.getFirstName());
		authUser.setAppClientId("pothigai-power");
		authUser.setGroupId("1");
		authUser.setUserAccessDt(LocalDateTime.now());
		if(partnerEmployee.getIsVendor()=="Y") {
			authUser.setUserType("VENDOR");	
			authUserRole.setAuthRoleId(18);
		}else if(partnerEmployee.getIsClient()=="Y") {
			authUser.setUserType("CLIENT");
			authUserRole.setAuthRoleId(5);
		}else {
			throw new SmartOfficeException("Partner Type Not mentioned");
		}	
		authUserRoles.add(authUserRole);
		authUser.setAuthUserRoles(authUserRoles);
		authUser.setAcceptedAgmt("N");
		authUser.setUserStatus("ACTIVE");
		authUser.setLastName(partnerEmployee.getLastName());
		authUser.setUserName(partnerEmployee.getEmailId());
		authUser.setPassword(String.valueOf(commonUtils.getPassword()));		
		authUser.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		authUser.setCreatedDt(LocalDateTime.now());
		authUser.setEmailId(partnerEmployee.getEmailId());
		authUserRepository.save(authUser);	
		partnerEventGenerator.triggerPartnerUserDetail(partnerEmployee, authUser, loggedInUser);
	}
	
	public void updateAuthPartner(List<AuthUser> authusers,PartnerEmployee partnerEmployee) throws SmartOfficeException{
	
		for(AuthUser user:authusers) {
			if(user.getEmailId()!=partnerEmployee.getEmailId() || user.getMobileNumber()!=partnerEmployee.getMobileNo()) {
				user.setEmailId(partnerEmployee.getEmailId());
				user.setMobileNumber(partnerEmployee.getMobileNo());				
			}
		}
		authUserRepository.saveAll(authusers);
		
	}
	
	
}

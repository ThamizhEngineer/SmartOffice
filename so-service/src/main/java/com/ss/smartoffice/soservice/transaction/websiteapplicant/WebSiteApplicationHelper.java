package com.ss.smartoffice.soservice.transaction.websiteapplicant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.vacancyrequest.VacancyRequest;

@Service
public class WebSiteApplicationHelper {
@Autowired
WebSiteApplicantRepository webSiteApplicantRepository;
@Autowired
CommonUtils commonUtils;
@Autowired
EmployeeRepository employeeRepository;
@Autowired
UserGroupEmployeeMappingService userGroupEmployeeMappingService;

public Iterable<WebSiteApplicant>getAll()throws SmartOfficeException{
return webSiteApplicantRepository.findAll();
}

public Optional<WebSiteApplicant>getById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
return webSiteApplicantRepository.findById(id);
}
public WebSiteApplicant createWebSiteApplicant(@RequestBody WebSiteApplicant webSiteApplicant)throws SmartOfficeException{
	
	List<String> hr1UserGroupIds = userGroupEmployeeMappingService.getUserGroupHrId();
	List<String> hr2UserGroupIds =userGroupEmployeeMappingService.getUserGroupHr2Id();
	if(!hr1UserGroupIds.contains(commonUtils.getLoggedinEmployeeId())||!(hr2UserGroupIds.contains(commonUtils.getLoggedinEmployeeId()))) {
		throw new SmartOfficeException("Not a valid user to perform this action");
	}else {
		memployee empById=employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		webSiteApplicant.setHr1UsrGrpId(empById.getHr1UsrGrpId());
		webSiteApplicant.setHr2UsrGrpId(empById.getHr2UsrGrpId());
		webSiteApplicant.setStatus("CREATED");
	}
	
	return webSiteApplicantRepository.save(webSiteApplicant);
}

public WebSiteApplicant processWebSiteApplicant(String id, String action ,@RequestBody WebSiteApplicant webSiteApplicant)throws SmartOfficeException{
	WebSiteApplicant webSiteApplicantFromDb = new WebSiteApplicant();
	if (!(action.equals("create"))){
		webSiteApplicantFromDb=webSiteApplicantRepository.findById(Integer.parseInt(id)).get();
		
	}
	AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
	switch (action) {
	case "create":
		createWebSiteApplicant(webSiteApplicantFromDb);
		break;
	case "validate":
		
		break;

	default:
		break;
	}
	return webSiteApplicant;
}

}

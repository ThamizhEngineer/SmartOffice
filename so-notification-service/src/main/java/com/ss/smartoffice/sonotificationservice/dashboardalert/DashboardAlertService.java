package com.ss.smartoffice.sonotificationservice.dashboardalert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthFeature;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.sonotificationservice.dashboardalert.DashboardAlertRepository;
import com.ss.smartoffice.sonotificationservice.expenseclaimsummary.ExpenseClaimService;
import com.ss.smartoffice.sonotificationservice.incidentsummary.IncidentSummaryRepo;
import com.ss.smartoffice.sonotificationservice.incidentsummary.IncidentSummaryService;
import com.ss.smartoffice.sonotificationservice.leaveapplicationsummary.LeaveApplicationSummaryService;
import com.ss.smartoffice.sonotificationservice.traveladvancerequestsummary.TravelAdvanceRequestSummaryRepo;
import com.ss.smartoffice.sonotificationservice.traveladvancerequestsummary.TravelAdvanceRequestSummaryService;

@RestController
@RequestMapping(path="dash/dash-metrics")

public class DashboardAlertService {
	
@Value("${authuserbyempid.url}")
private String authUserByEmpIdUrl;

@Value("${authrolefeature.url}")
private String authRoleFeatureUrl;

@Value("${authfeaturebyid.url}")
private String authFeatureByIdUrl;
	
@Autowired
DashboardAlertRepository dashboardAlertRepository;

@Autowired
TravelAdvanceRequestSummaryRepo tarRepo;

@Autowired
IncidentSummaryRepo incidentRepo;

@Autowired
TravelAdvanceRequestSummaryService tarService;

@Autowired
LeaveApplicationSummaryService leaveService;

@Autowired
ExpenseClaimService exClaimService;

@Autowired
IncidentSummaryService incidentSummaryService;

@Autowired
CommonUtils commonUtils;

@GetMapping("/latest")
public List<DashboardAlert>getDashMetricsByEmployeeId()throws SmartOfficeException{
	String employeeId=commonUtils.getLoggedinEmployeeId();
	return dashboardAlertRepository.findByEmployeeId(employeeId);
}


@GetMapping("/{empid}")
public List<DashboardAlert> formingDashboard(@PathVariable(value = "empid")String employeeId) {
//	System.out.println("start-employeeId-"+employeeId);

	List<AuthUserRole> authUserRoles = getAuthUserByEmployeeId(employeeId);
	List<AuthRoleFeature> authRoleFeatures = new ArrayList<AuthRoleFeature>();
	List<AuthFeature> authFeatures = new ArrayList<AuthFeature>();

	List<DashboardAlert> finalDash = new ArrayList<DashboardAlert>();
	
	for (AuthUserRole authUserRole : authUserRoles) {
		Integer authRoldId = authUserRole.getAuthRoleId();
		authRoleFeatures = getAuthRoleFeature(authUserRole.getAuthRoleId());
//		System.out.println("authRoldId-"+authRoldId);


		for (AuthRoleFeature authRoleFeature : authRoleFeatures) {
			String authFeatureId = authRoleFeature.getAuthFeatureId();
//			System.out.println("authFeatureId-"+authFeatureId);

			authFeatures=getAuthFeatureById(Integer.parseInt(authFeatureId));
//			System.out.println("authFeatureId-"+authFeatureId);

			
			for (AuthFeature authFeature : authFeatures) {
				DashboardAlert dash = new DashboardAlert();
				String featureCode = authFeature.getFeatureCode();
//				System.out.println("featureCodeinLoop-"+featureCode);

				dash.setEmployeeId(employeeId);
				dash.setFeatureId(authFeatureId);
				dash = processWithFeature(featureCode,dash);
				finalDash.add(dash);
			}
			finalDash = (List<DashboardAlert>) addOrUpdate(finalDash);
//			System.out.println("final-dash-finalDash"+finalDash);
		}
	}
	return finalDash;
}

private DashboardAlert processWithFeature(String featureCode, DashboardAlert inDash) {
	System.out.println("featureCode-"+featureCode);
	System.out.println("inDash-"+inDash);


    switch (featureCode) {
    case "TAR-N1-APPROVAL":
    	inDash.setDashCount(Long.toString(tarService.getN1Count()));
        break;
        
    case "TAR-ACC2-APPROVAL":
    	inDash.setDashCount(Long.toString(tarService.getAcc2Count()));
        break;
        
    case "LR-N1-APPROVAL":
    	inDash.setDashCount(Long.toString(leaveService.getN1Count()));
        break;
        
    case "LR-N2-APPROVAL":
    	inDash.setDashCount(Long.toString(leaveService.getN2Count()));
        break;
        
//    case "LR-SETTLEMENT":
//    	inDash.setDashCount(Long.toString(leaveService.countByValidateUserGroup()));
//        break;
        
//    case "ECR-SETTLEMENT":
//    	inDash.setDashCount(Long.toString(exClaimService.countByUserGroupId()));
//        break;
        
//    case "ECR-VALIDATION":
//    	inDash.setDashCount(Long.toString(exClaimService.countByValidateUserGroup()));
//        break;
        
    case "ECR-N1-APPROVAL":
    	inDash.setDashCount(Long.toString(exClaimService.getN1Count()));
        break;
        
    case "ECR-N2-APPROVAL":
    	inDash.setDashCount(Long.toString(exClaimService.getN2Count()));
        break; 
	case "PENDING-APPROVAL-RECRUIT":
		System.out.println(incidentSummaryService.countRecruit());
		inDash.setDashCount(Long.toString(incidentSummaryService.countRecruit()));
		
		break;
	case "PENDING-APPROVAL-TRAINING":
		System.out.println(incidentSummaryService.countTraining());
		inDash.setDashCount(Long.toString(incidentSummaryService.countTraining()));
		
		break;
        
    default:
        break;
}
	
	return inDash;
}

//add or update -----------------------------------------------------------------------------------------------------------

@PatchMapping("/add-or-update")
public Iterable<DashboardAlert> addOrUpdate(@RequestBody List<DashboardAlert> Inputdashs) {
	System.out.println(Inputdashs);
	DashboardAlert d = new DashboardAlert();
	List<DashboardAlert> dashBoardAlertList = new ArrayList<DashboardAlert>();
	for(DashboardAlert inputDash:Inputdashs) {
		Optional<List<DashboardAlert>> list = dashboardAlertRepository.findByEmployeeIdAndFeatureId(inputDash.getEmployeeId(),inputDash.getFeatureId());
		System.out.println("listda="+list);
		if (list.isPresent()) {
			dashBoardAlertList.add(list.get().get(0));
			dashboardAlertRepository.saveAll(dashBoardAlertList);
		}
		else {
			dashBoardAlertList.add(inputDash);
			dashboardAlertRepository.saveAll(dashBoardAlertList);
		}
	}
	return dashBoardAlertList;
}


private DashboardAlert addNewRecord(DashboardAlert dashboardAlert) {
	
	dashboardAlert.setEmployeeId(dashboardAlert.getEmployeeId());
	dashboardAlert.setFeatureId(dashboardAlert.getFeatureId());
	dashboardAlert.setDashCount(dashboardAlert.getDashCount());
	dashboardAlert.setTargetRoute(dashboardAlert.getTargetRoute());
	dashboardAlert.setIsEnabled(dashboardAlert.getIsEnabled());
	dashboardAlertRepository.save(dashboardAlert);
	System.out.println("adddash");

	return dashboardAlert;
}

private DashboardAlert matchAndUpdateFields(DashboardAlert dashFromDB, DashboardAlert dash) {
	
	dashFromDB.setEmployeeId(dash.getEmployeeId());
	dashFromDB.setFeatureId(dash.getFeatureId());
	dashFromDB.setDashCount(dash.getDashCount());
	dashFromDB.setTargetRoute(dash.getTargetRoute());
	dashFromDB.setIsEnabled(dash.getIsEnabled());
	System.out.println("updatedash");
	System.out.println("dashFromDB-"+dashFromDB);
	System.out.println("dash-"+dash);

	
	dashboardAlertRepository.save(dashFromDB);
	return dashFromDB;
}

//Rest Calls --------------------------------------------------------------------------------------------------------------

private List<AuthUserRole> getAuthUserByEmployeeId(String employeeId) {
	String url=authUserByEmpIdUrl+employeeId+"/_internal";
	List<AuthUserRole> authUserRoles = new ArrayList<AuthUserRole>();
	
	try {
		List<AuthUser> authUsers = Arrays.asList(commonUtils.getRestTemplate().getForObject(url,
				AuthUser[].class));
		
		for (AuthUser authUser2 : authUsers) {
			authUserRoles = authUser2.getAuthUserRoles();
			System.out.println("1"+authUserRoles);
		}
	} catch (RestClientException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return authUserRoles;
}

private List<AuthRoleFeature> getAuthRoleFeature(Integer authRoldId){
	String url=authRoleFeatureUrl+authRoldId+"/_internal";
	System.out.println(url);
	List<AuthRoleFeature> authRoleFeatures = new ArrayList<AuthRoleFeature>();

	try {
		authRoleFeatures = Arrays.asList(commonUtils.getRestTemplate().getForObject(url, AuthRoleFeature[].class));
		System.out.println("get authroles");
	} catch (RestClientException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return authRoleFeatures;
}

private List<AuthFeature> getAuthFeatureById(Integer id){
	String url=authFeatureByIdUrl+id+"/_internal";
	System.out.println("url" +url);
	List<AuthFeature> authFeatures = new ArrayList<AuthFeature>();
	
	try {
		authFeatures = Arrays.asList(commonUtils.getRestTemplate().getForObject(url, AuthFeature[].class));
	} catch (RestClientException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return authFeatures;
}

public DashboardAlert employeeFeatures()throws SmartOfficeException{
	String authUserId=commonUtils.getLoggedinUserId();
	System.out.println(authUserId);
	return null;
}

}

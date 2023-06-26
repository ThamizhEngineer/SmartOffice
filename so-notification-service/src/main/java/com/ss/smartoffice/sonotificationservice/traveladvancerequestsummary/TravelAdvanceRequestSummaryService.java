package com.ss.smartoffice.sonotificationservice.traveladvancerequestsummary;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path="summary/tars")
public class TravelAdvanceRequestSummaryService {
	
	@Value("${usergroup.url}")
	private String userGroupUrl;
	
	@Autowired
	CommonUtils commonutils;
	
	@Autowired
	TravelAdvanceRequestSummaryRepo repo;
	
	@GetMapping
	public Iterable<TravelAdvanceRequestSummary> getAll(){
		return repo.findAll();
	}
	
	@GetMapping("/countN1employeeStatus")
	public Long getN1Count()throws SmartOfficeException{
		String employeeId=commonutils.getLoggedinEmployeeId();
		String tarStatus="N1-APPROVAL-PENDING";
		System.out.println(employeeId);
		return repo.countByN1EmployeeIdAndTarStatus(employeeId,tarStatus);
	}

	@GetMapping("/countAcc2employeeStatus")
	public Long getAcc2Count()throws SmartOfficeException{
		
		String tarStatus="ACC2-APPROVAL-PENDING";
		List<String> userGroupIds = getUserGroups();
		System.out.println("user group ids"+userGroupIds);
		return repo.countByAcc2UserGroupIdInAndTarStatus(userGroupIds, tarStatus);
			
	}
	
	
	private List<String> getUserGroups() {
		String url = userGroupUrl+"usergroup";
		System.out.println(url);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonutils.getLoggedinAppToken());
		HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String[]> entity = commonutils.getRestTemplate().exchange(url,
                HttpMethod.GET, request,String[].class);
        List<String> list = Arrays.asList(entity.getBody());
        System.out.println("restUserGroup-"+list);
		return list;
	}

}

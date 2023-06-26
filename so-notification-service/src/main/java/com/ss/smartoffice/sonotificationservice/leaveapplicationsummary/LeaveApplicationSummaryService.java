package com.ss.smartoffice.sonotificationservice.leaveapplicationsummary;

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
@RequestMapping("summary/leave-applications")
public class LeaveApplicationSummaryService {
	
	@Value("${usergroup.url}")
	private String userGroupHrIdUrl;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	LeaveApplicationSummaryRepo repo;
	
	@GetMapping
	public Iterable<LeaveApplicationSummary> getAll(){
		return repo.findAll();
	}
	
	@GetMapping("/countN1")
	public Long getN1Count()throws SmartOfficeException{
		String n1EmpId=commonUtils.getLoggedinEmployeeId();
		String leaveStatus="N1-APPROVAL-PENDING";
		return repo.countByN1EmpIdAndLeaveStatus(n1EmpId, leaveStatus);
	}
	
	@GetMapping("/countN2")
	public Long getN2Count()throws SmartOfficeException{
		String n2EmpId=commonUtils.getLoggedinEmployeeId();
		String leaveStatus="N2-APPROVAL-PENDING";
		return repo.countByN2EmpIdAndLeaveStatus(n2EmpId, leaveStatus);
	}
	
//	@GetMapping("/settlementCount")
//	public Long countByValidateUserGroup()throws SmartOfficeException{
//		List<String> userGroupIds = getUserGroupHrid();
//		String leaveStatus="SETTLEMENT-PENDING";
//		return repo.countByHr1UserGroupIdAndLeaveStatusIn(userGroupIds, leaveStatus);
//	}
	
	private List<String> getUserGroupHrid() {
		String url = userGroupHrIdUrl+"hrgroup";
		
		System.out.println(url);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken());
		HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String[]> entity = commonUtils.getRestTemplate().exchange(url,
                HttpMethod.GET, request,String[].class);
        List<String> list = Arrays.asList(entity.getBody());
        System.out.println("restHrGroup-"+list);

		return list;
	}
	
}

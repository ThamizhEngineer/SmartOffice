package com.ss.smartoffice.sonotificationservice.expenseclaimsummary;


import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("summary/expense-claims")
public class ExpenseClaimService {
	
	@Value("${usergroup.url}")
	private String userGroupHrIdUrl;
	
	
	@Autowired
	ExpenseClaimRepo repo;
	
	@Autowired
	CommonUtils commonUtils;

	@GetMapping("/all")
	public Iterable<ExpenseClaimSummary> getAllExpenseClaims() throws SmartOfficeException {
		return repo.findAll();
	}
	
	@GetMapping("/countN1")
	public Long getN1Count()throws SmartOfficeException{
		String n1EmpId = commonUtils.getLoggedinEmployeeId();
		String expenseClaimStatus="N1-APPROVAL-PENDING";
		return repo.countByN1EmpIdAndExpenseClaimStatus(n1EmpId, expenseClaimStatus);
	}
	
	@GetMapping("/countN2")
	public Long getN2Count()throws SmartOfficeException{
		String n2EmpId = commonUtils.getLoggedinEmployeeId();
		String expenseClaimStatus="N2-APPROVAL-PENDING";
		return repo.countByN2EmpIdAndExpenseClaimStatus(n2EmpId, expenseClaimStatus);
	}
	
	
//	@GetMapping("/validateUserGroup")
//	public Long countByValidateUserGroup()throws SmartOfficeException{
//		List<String> userGroupIds = getUserGroupHrid();
//		String expenseClaimStatus="VALIDATION-PENDING";
//		return repo.countByValidateUserGroupIdAndExpenseClaimStatusIn(userGroupIds, expenseClaimStatus);
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
	
//	@GetMapping("/settlementCount")
//	public Long countByUserGroupId() {
//		List<String> userGroupIds = getUserGroups();
//		String expenseClaimStatus="SETTLEMENT-PENDING";
//		return repo.countBySettleUserGroupIdAndExpenseClaimStatusIn(userGroupIds, expenseClaimStatus);
//	}
	
	private List<String> getUserGroups() {
		String url = userGroupHrIdUrl+"usergroup";
		System.out.println(url);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken());
		HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String[]> entity = commonUtils.getRestTemplate().exchange(url,
                HttpMethod.GET, request,String[].class);
        List<String> list = Arrays.asList(entity.getBody());
        System.out.println("restUserGroup-"+list);
		return list;
	}
	
	
	  @CrossOrigin
	  @PostMapping("/upload")
	  public boolean pictureupload(@RequestParam("file") MultipartFile file) {

	    System.out.println(file.getName());
	    System.out.println(file.getOriginalFilename());
	    System.out.println(file.getSize());

	    try {
	      Path downloadedFile = Paths.get(file.getOriginalFilename());
	      Files.deleteIfExists(downloadedFile);

	      Files.copy(file.getInputStream(), downloadedFile);

	      return true;
	    }
	    catch (IOException e) {
	      LoggerFactory.getLogger(this.getClass()).error("pictureupload", e);
	      return false;
	    }

	  }

}

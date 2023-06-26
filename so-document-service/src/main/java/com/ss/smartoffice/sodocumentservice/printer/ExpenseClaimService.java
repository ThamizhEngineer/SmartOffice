package com.ss.smartoffice.sodocumentservice.printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sodocumentservice.model.Applicant;
import com.ss.smartoffice.sodocumentservice.model.ExpenseClaim;

@RestController
@RequestMapping(path="document/prints/expense-claim")
public class ExpenseClaimService {
@Value("${expense.url}")
private String expenseUrl;
@Autowired
CommonUtils commonUtils;
@Autowired
ExpenseClaimPdfGenerator expenseClaimPdfGenerator;
@GetMapping("/{id}/generate-pdf")
public void generateExpenseClaimPdf(@PathVariable(value="id")int id) throws SmartOfficeException{
	expenseClaimPdfGenerator.generatePdf(doGetExpenses(id));	
}
private ExpenseClaim doGetExpenses(int id)throws SmartOfficeException{

	ExpenseClaim expenseClaim = new ExpenseClaim();
	HttpHeaders headers = new HttpHeaders();
	
	headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
	HttpEntity<ExpenseClaim>request=new HttpEntity<ExpenseClaim>(expenseClaim,headers);
	System.out.println(expenseUrl);
	ResponseEntity<ExpenseClaim> expenseEntity = commonUtils.getRestTemplate().exchange(expenseUrl+ id,
			HttpMethod.GET, request,ExpenseClaim.class);
	ExpenseClaim expenseClaimFromRest =expenseEntity.getBody();
	return expenseClaimFromRest;
}

}

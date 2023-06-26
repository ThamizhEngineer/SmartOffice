package com.ss.smartoffice.soservice.transaction.expenseclaim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.BankAdviseData;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.print.PrintBusHelper;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.minioAPIservice.MinioAPIService;


@Controller
@RequestMapping("transaction/expense-claims")

public class ExpenseClaimService {
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;
	@Autowired
	ExpenseClaimBusHelper expenseClaimBusHelper;
	@Autowired
	EmployeeRepository empRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	PrintBusHelper printBusHelper;
	@Autowired
	MinioAPIService minioAPIService;
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;
	  private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
	ArrayList<String> knownActions = new ArrayList<String>( Arrays.asList("update","validate","validation-reject","n1-approve","n1-reject","n2-approve","n2-reject","settle","settlement-reject","cancel"));
	
	@GetMapping("/my-expense-claims")
	public List<ExpenseClaim> getMyExpenseClaims() throws SmartOfficeException {
		String employeeId = commonUtils.getLoggedinEmployeeId();
//		List<ExpenseClaim> myExpenseClaims = expenseClaimRepository.findExpenseClaimsByEmpId(employeeId);
//		printBusHelper.downloadHelper(model);
//		return expenseClaimRepository.findExpenseClaimsByEmpId(employeeId);
//		Map<String, String> printAttributes = new HashMap<>();
//		
//		printAttributes.put("EmpName", "empName");
//		printAttributes.put("EmpCode", "empCode");
//		model.addAttribute("dataKeyName", "expenseClaimList");
//		model.addAttribute("dataType", "ExpenseClaim");
//		model.addAttribute("printAttributes", printAttributes);
//		model.addAttribute("fileName", "my-emp");
		return expenseClaimRepository.findExpenseClaimsByEmpId(employeeId);
	}
	
	@GetMapping("/my-acc1-create-claims")
	public List<memployee> getAcc1CreateClaims()throws SmartOfficeException{
	
		List<String> validationUserGroupIds = userGroupEmployeeMappingService.getUserGroupAcc1Id();
		return empRepository.findByAcc1UserGroupIdIn(validationUserGroupIds);
	}
	@GetMapping("/{id}")
	public Optional<ExpenseClaim>getExpClaimById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return expenseClaimRepository.findById(id);
	}
	@GetMapping("/_internal/{id}")
	public Optional<ExpenseClaim>getExpClaimInternalById(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		return expenseClaimRepository.findById(id);
	}

	@GetMapping("/expense-claims-for-my-validation")
	public List<ExpenseClaim> getMyExpenseClaimsForValidation() throws SmartOfficeException {

		List<String> validationUserGroupIds = userGroupEmployeeMappingService.getUserGroupAcc1Id();
		return expenseClaimRepository.findExpenseClaimsForValidation(validationUserGroupIds);
	}

	@GetMapping("/expense-claims-for-my-approval")
	public List<ExpenseClaim> getMyExpenseClaimsForApproval() throws SmartOfficeException {
		String n1EmpId = commonUtils.getLoggedinEmployeeId();
		String n2EmpId = commonUtils.getLoggedinEmployeeId();
		
		return expenseClaimRepository.findByN1EmpIdOrN2EmpId(n1EmpId, n2EmpId);

	}

	@GetMapping("/expense-claims-for-my-settlement")
	public List<ExpenseClaim> getMyExpenseClaimsForSettlement() throws SmartOfficeException {
		//String employeeId = commonUtils.getLoggedinEmployeeId();
		List<ExpenseClaim> claims = new ArrayList<ExpenseClaim>();
		List<String> settlementUserGroupIds=userGroupEmployeeMappingService.getUserGroupEmployeeById();	
		if(!settlementUserGroupIds.isEmpty()) {
			claims = expenseClaimRepository.findExpenseClaimsForSettlement(settlementUserGroupIds);
		}
		return claims;
	}

	// apply
	@PostMapping("/{action}")
	public ExpenseClaim apply(@PathVariable(value = "action") String action, @RequestBody ExpenseClaim expenseClaim)
			throws SmartOfficeException {
		System.out.println(action);
		if (action.equals("apply")||action.equals("create")) {
			return expenseClaimBusHelper.processExpenseClaim(null, action, expenseClaim);
		} else {
			throw new SmartOfficeException("Invalid Url");
		}

	}
	@PostMapping("/_internal/{id}/exp-pdf")
    public ExpenseClaim updateApplicantByPdfId(@RequestBody ExpenseClaim expenseClaim) throws SmartOfficeException {
		ExpenseClaim expenseFromDb =getExpClaimById(expenseClaim.getId()).get();
		expenseFromDb.setExpPdfId(expenseClaim.getExpPdfId());
		return expenseClaimRepository.save(expenseFromDb);
		
	}
	@PatchMapping("/{id}/{action}")
	public ExpenseClaim processApproval(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "action") String action, @RequestBody ExpenseClaim expenseClaim)
			throws SmartOfficeException {

		if (knownActions.contains(action)) {
			return expenseClaimBusHelper.processExpenseClaim(id.toString(), action, expenseClaim);
		} else {
			throw new SmartOfficeException("Invalid url");
		}

	}
	
	@GetMapping("/{id}/employee-generate-pdf")
	public void generateExpenseClaimByEmployeePdf(@PathVariable(value="id")int id) throws SmartOfficeException{
		ExpenseClaim expenseClaimById = expenseClaimRepository.findById(id).get();	
	}
	
	@GetMapping("/countN1")
	public Long getN1Count()throws SmartOfficeException{
		String n1EmpId = commonUtils.getLoggedinEmployeeId();
		String expenseClaimStatus="N1-APPROVAL-PENDING";
		return expenseClaimRepository.countByN1EmpIdAndExpenseClaimStatus(n1EmpId, expenseClaimStatus);
	}
	
	@GetMapping("/countN2")
	public Long getN2Count()throws SmartOfficeException{
		String n2EmpId = commonUtils.getLoggedinEmployeeId();
		String expenseClaimStatus="N2-APPROVAL-PENDING";
		return expenseClaimRepository.countByN2EmpIdAndExpenseClaimStatus(n2EmpId, expenseClaimStatus);
	}
	
	@GetMapping("/validateUserGroup")
	public Long countByValidateUserGroup()throws SmartOfficeException{
		List<String> userGroupIds = userGroupEmployeeMappingService.getUserGroupHrId();
		Long validateUserGroups= null;
		String expenseClaimStatus="VALIDATION-PENDING";
		if(!(userGroupIds.isEmpty())) {
			validateUserGroups=expenseClaimRepository.countByValidateUserGroupIdInAndExpenseClaimStatus(userGroupIds,expenseClaimStatus);
		}
		return validateUserGroups;
	}
	
	@GetMapping("/bank-advise-report-expense-claim/{expensePurpose}/{fromDt}/{endDt}")
	public List<BankAdviseData> getBankReports(Model model,@PathVariable(value = "expensePurpose")String expensePurpose,@PathVariable("fromDt")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDt,@PathVariable("endDt")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)   LocalDate endDt)throws SmartOfficeException{
		List<BankAdviseData>bankDetailsExpenses=expenseClaimRepository.findByAccDetails(expensePurpose,fromDt, endDt);
		
		Map<String, String> printAttributes = new HashMap<>();
		printAttributes.put("Debit Account No", "debitAccountNo");
		printAttributes.put("Beneficiary Ac No", "accNo");
		printAttributes.put("Amt", "totalEntitledAmount");
		printAttributes.put("IfscCode", "ifscCode");
		printAttributes.put("BankName", "bankName");
		printAttributes.put("Purpose", "expensePurpose");
		printAttributes.put("SettleRemarks", "settleRemarks");
		printAttributes.put("Beneficiary Name", "accountName");
		printAttributes.put("Bene add1", "");
		printAttributes.put("Add details 3", "");
		printAttributes.put("Print Location", "");
		printAttributes.put("PayMod", "");
		printAttributes.put("Date", "");
		printAttributes.put("Payable Location", "");
		printAttributes.put("Add details 4", "");
		printAttributes.put("Bene Mobile No", "");
		
		
		
		printAttributes.put("Bene add3", "");
		printAttributes.put("Bene add4", "");
		printAttributes.put("Add details 1", "");
		printAttributes.put("Add details 2","");
		
		
	
		printAttributes.put("Add details 5", "");
		model.addAttribute("dataKeyName", "bankAdviseDataList");
		model.addAttribute("dataType", "BankAdviseData");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "BankAdviseData-details-Expense");
		  

return bankDetailsExpenses;
	}
}
package com.ss.smartoffice.soservice.transaction.expenseclaim;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.shared.model.*;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.seed.configs.ConfigService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;

@Component
public class ExpenseClaimBusHelper {
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;

	@Autowired
	ExpenseClaimBillRepository expenseClaimBillRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ConfigService configService;
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	@Autowired
	EventRepository eventRepository;
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	@Autowired
	EventPublisherService eventPublisherService;

	@Autowired
	ExpenseClaimEventGenerator expenseClaimEventGenerator;

	/*
	 * getExpenseClaimById -
	 */

	/*
	 * findAuthForExpenseClaim a. find the current EmployeeId b. if currEmpId ==
	 * claim.EmpId then return "SELF" c. else if currEmpId == claim.n1EmplId then
	 * return "N1" d. else if currEmpId == claim.n1EmplId
	 * 
	 */
	public ExpenseClaim createExpenseClaim(ExpenseClaim expenseClaim) throws SmartOfficeException {
		try {
			HashMap<String, String> buisnessKeys = new HashMap<>();
			String jobCode = null;
			String costCode = null;
			expenseClaim.setIsJobBased(expenseClaim.getIsJobBased());
			expenseClaim.setJobId(expenseClaim.getJobId());
			expenseClaim.setCostCenterId(expenseClaim.getCostCenterId());
			expenseClaim.setExpenseClaimAmount(expenseClaim.getExpenseClaimAmount());
			expenseClaim.setEmpRemarks(expenseClaim.getEmpRemarks());
			if(expenseClaim.getEmployeeId()!=null) {
			memployee employeeById = employeeRepository.findById(Integer.valueOf(expenseClaim.getEmployeeId())).get();					
			expenseClaim.setN1EmpId(employeeById.getN1EmpId());
			expenseClaim.setN2EmpId(employeeById.getN2EmpId());
			expenseClaim.setValidateUserGroupId(employeeById.getAcc1UsrGrpId());
			expenseClaim.setSettleUserGroupId(employeeById.getAcc2UsrGrpId());
			}else {
				memployee employeeById = employeeRepository.findById(Integer.valueOf(commonUtils.getLoggedinEmployeeId())).get();	
				expenseClaim.setN1EmpId(employeeById.getN1EmpId());
				expenseClaim.setN2EmpId(employeeById.getN2EmpId());
				expenseClaim.setValidateUserGroupId(employeeById.getAcc1UsrGrpId());
				expenseClaim.setSettleUserGroupId(employeeById.getAcc2UsrGrpId());
			}
			expenseClaimRepository.save(expenseClaim);
				if (expenseClaim.getIsJobBased() != null && expenseClaim.getIsJobBased().equals("Y")) {
				

					jobCode = "JC";
					expenseClaim.setExpenseClaimCode(sequenceGenerationService.nextSeq("EXPENSE-CLAIM", buisnessKeys));

//					expenseClaim.setExpenseClaimCode(
//							sequenceGenerationService.nextSequence("EXPENSE-CLAIM").getOutput() + "-" + jobCode);
				} else {
					costCode = "CC";
//					expenseClaim.setExpenseClaimCode(
//							sequenceGenerationService.nextSequence("EXPENSE-CLAIM").getOutput() + "-" + costCode);
					expenseClaim.setExpenseClaimCode(sequenceGenerationService.nextSeq("EXPENSE-CLAIM", buisnessKeys));

				}			
			if(expenseClaim.getEmployeeId()!=null) {
				expenseClaim.setEmployeeId(expenseClaim.getEmployeeId());
			}else {
			expenseClaim.setEmployeeId(commonUtils.getLoggedinEmployeeId());
			
			}
			expenseClaim.setAppliedEmpId(commonUtils.getLoggedinEmployeeId());
			expenseClaim.setAppliedDt(expenseClaim.getAppliedDt());
			expenseClaim.setExpenseClaimStatus("CREATED");
			expenseClaim.setInputCreditAmount("0");
			expenseClaim.setIsEnabled("Y");
			expenseClaim.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			expenseClaim.setCreatedDt(LocalDateTime.now());
			expenseClaimRepository.save(expenseClaim);
			for (ExpenseClaimBill expenseClaimBill : expenseClaim.getExpenseClaimBills()) {
				expenseClaimBill.setExpenseClaimId(expenseClaim.getId().toString());
				expenseClaimBill.setIsEnabled("Y");
				expenseClaimBill.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				expenseClaimBill.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				expenseClaimBillRepository.save(expenseClaimBill);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return expenseClaim;
	}

	public ExpenseClaim updateExpenseClaim(ExpenseClaim expenseClaim) throws SmartOfficeException {
		expenseClaim.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		expenseClaim.setModifiedDt(LocalDateTime.now());
		ExpenseClaim savedExp=expenseClaimRepository.save(expenseClaim);
		for(ExpenseClaimBill expenseClaimBill:expenseClaim.getExpenseClaimBills()) {
			expenseClaimBill.setExpenseClaimId(savedExp.getId().toString());
			expenseClaimBillRepository.save(expenseClaimBill);
		}
		return savedExp;
	}

	public ExpenseClaim processExpenseClaim(String id, String action, @RequestBody ExpenseClaim expenseClaim)
			throws SmartOfficeException {
		ExpenseClaim expenseClaimFromDb = new ExpenseClaim();
		double totalEntitledAmount = 0;
		if (!(action.equals("create")) && !(action.equals("apply"))) {
			expenseClaimFromDb = expenseClaimRepository.findById(expenseClaim.getId()).get();

		}

		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		

		switch (action) {
		case ("create"):

			createExpenseClaim(expenseClaim);

			break;

		case ("apply"):

			if (expenseClaim.getId() != null) {
				if (!expenseClaim.getExpenseClaimStatus().equals("CREATED")) {
					throw new SmartOfficeException("Expense Claim is already applied");
				} else {
					expenseClaim.setExpenseClaimStatus("APPLIED");
					expenseClaim = updateExpenseClaim(expenseClaim);
					expenseClaim.setExpenseClaimStatus("VALIDATION-PENDING");
					expenseClaim = updateExpenseClaim(expenseClaim);
					expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaim, action, loggedInUser);
				}
				
			}else {
				expenseClaim = createExpenseClaim(expenseClaim);
				expenseClaim.setExpenseClaimStatus("APPLIED");
				expenseClaim = updateExpenseClaim(expenseClaim);
				expenseClaim.setExpenseClaimStatus("VALIDATION-PENDING");
				expenseClaim = updateExpenseClaim(expenseClaim);
				expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaim, action, loggedInUser);
			}
		
			break;
			
		case ("validate"):
			System.out.println();
			System.out.println(expenseClaimFromDb.getExpenseClaimStatus());
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("VALIDATION-PENDING")) {
				throw new SmartOfficeException("Expense Claim not in validation stage");
			} else {
				
				List<String> userGroupIds = commonUtils.findLoggedInUserGroups();
				if (!userGroupIds.contains(expenseClaimFromDb.getValidateUserGroupId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");

				} else {
					expenseClaim.setValidateRemarks(expenseClaim.getValidateRemarks());
					
						for(ExpenseClaimBill expenseClaimBill:expenseClaim.getExpenseClaimBills()) {
							expenseClaimBill.setBillStatus(expenseClaimBill.getBillStatus());
							if(expenseClaimBill.getBillStatus().equals("APPROVED")) {								
								expenseClaimBill.setEntitledAmount(expenseClaimBill.getBillAmount());
								totalEntitledAmount=totalEntitledAmount+(expenseClaimBill.getEntitledAmount());
							}else if (expenseClaimBill.getBillStatus().equals("PARTIALLY APPROVED")) {								
								expenseClaimBill.setEntitledAmount(expenseClaimBill.getEntitledAmount());
								totalEntitledAmount=totalEntitledAmount+(expenseClaimBill.getEntitledAmount());
							}else if (expenseClaimBill.getBillStatus().equals("REJECTED")) {
								expenseClaimBill.setEntitledAmount(0.0);
								totalEntitledAmount=totalEntitledAmount+(0.0);
							}
//							totalEntitledAmount+=expenseClaimBill.getEntitledAmount();
							
					}
						if(String.valueOf(totalEntitledAmount).equals(String.valueOf(expenseClaim.getTotalEntitledAmount()))) {
							expenseClaim.setTotalEntitledAmount(totalEntitledAmount);
							
						}else {
							System.out.println(totalEntitledAmount);
							System.out.println(expenseClaim.getTotalEntitledAmount());
							throw new SmartOfficeException("Total Value is not same as entitled amount ");
						}
						expenseClaim.setValidateEmpId(commonUtils.getLoggedinEmployeeId());
						expenseClaim.setExpenseClaimStatus("VALIDATED");
						expenseClaim.setValidateRemarks(expenseClaim.getValidateRemarks());
						expenseClaim.setValidateDecision("VALIDATED");
						expenseClaim.setValidatedDt(LocalDateTime.now());
						expenseClaim.setExpenseClaimStatus("N1-APPROVAL-PENDING");
						expenseClaim = updateExpenseClaim(expenseClaim);

					expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaim, action, loggedInUser);
				}
			}
			break;

		case ("validation-reject"):
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("VALIDATION-PENDING")) {
				throw new SmartOfficeException("Expense Claim not in validation stage");
			} else {
				List<String> userGroupIds = commonUtils.findLoggedInUserGroups();
				if (!userGroupIds.contains(expenseClaimFromDb.getValidateUserGroupId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");

				} else {
					expenseClaimFromDb.setValidateRemarks(expenseClaim.getValidateRemarks());
					expenseClaimFromDb.setValidateEmpId(commonUtils.getLoggedinEmployeeId());
					
					expenseClaimFromDb.setExpenseClaimStatus("VALIDATION-REJECTED");
					expenseClaimFromDb.setValidateDecision("VALIDATION-REJECTED");
					expenseClaimFromDb.setValidatedDt(LocalDateTime.now());
					expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
					expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaimFromDb, action, loggedInUser);
				}
			}
			break;

		case ("n1-approve"):
			System.out.println("hi");
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("N1-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Expense Claim can be approved only in n1-approval ");
			} else {
				if (!expenseClaimFromDb.getN1EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
				}

				expenseClaimFromDb.setN1Remarks(expenseClaim.getN1Remarks());
				expenseClaimFromDb.setNeedN2Approval(expenseClaim.getNeedN2Approval());

				
				expenseClaimFromDb.setExpenseClaimStatus("N1-APPROVED");
				expenseClaimFromDb.setN1Decision("N1-APPROVED");
				expenseClaimFromDb.setN1DecisionDt(LocalDateTime.now());
				expenseClaimFromDb = expenseClaimRepository.save(expenseClaimFromDb);
				System.out.println(expenseClaimFromDb);
				if (expenseClaimFromDb.getNeedN2Approval().equals("Y")) {
					expenseClaimFromDb.setExpenseClaimStatus("N2-APPROVAL-PENDING");
					expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
				} else if (expenseClaimFromDb.getNeedN2Approval().equals("N")) {

					expenseClaimFromDb.setExpenseClaimStatus("SETTLEMENT-PENDING");
					expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
				}
				expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaimFromDb, action, loggedInUser);
			}
			break;
		case ("n1-reject"):
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("N1-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Expense Claim be rejected only in n1-approval stage");
			} else {
				if (!expenseClaimFromDb.getN1EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("You Are Not N1 Manager For this Employee");
				}
				expenseClaimFromDb.setN1Remarks(expenseClaim.getN1Remarks());
				
				expenseClaimFromDb.setExpenseClaimStatus("N1-REJECTED");
				expenseClaimFromDb.setN1Decision("N1-REJECTED");
				expenseClaimFromDb.setN1DecisionDt(LocalDateTime.now());
				expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);

			}
			expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaimFromDb, action, loggedInUser);
			break;
		case ("n2-approve"):
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("N2-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Expense Claim can be approved only in n2-approval ");
			} else {
				if (!expenseClaimFromDb.getN2EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("You Are Not N2 Manager For this Employee");
				}
				expenseClaimFromDb.setN2Remarks(expenseClaim.getN2Remarks());

				
				expenseClaimFromDb.setExpenseClaimStatus("N2-APPROVED");
				expenseClaimFromDb.setN2Decision("N2-APPROVED");
				expenseClaimFromDb.setN2ApprovedDt(LocalDateTime.now());
				expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
				expenseClaimFromDb.setExpenseClaimStatus("APPROVED");
				expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
				expenseClaimFromDb.setExpenseClaimStatus("SETTLEMENT-PENDING");
				expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
			}
			expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaimFromDb, action, loggedInUser);
			break;
		case ("n2-reject"):
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("N2-APPROVAL-PENDING")) {
				throw new SmartOfficeException("Expense Claim can be approved only in n2-approval ");
			} else {
				if (!expenseClaimFromDb.getN2EmpId().equals(commonUtils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("You Are Not N2 Manager For this Employee");
				}
				expenseClaimFromDb.setN2Remarks(expenseClaim.getN2Remarks());

				
				expenseClaimFromDb.setExpenseClaimStatus("N2-REJECTED");
				expenseClaimFromDb.setN2Decision("N2-REJECTED");
				expenseClaimFromDb.setN2ApprovedDt(LocalDateTime.now());
				expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);

			}
			expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaimFromDb, action, loggedInUser);
			break;

		case ("settle"):
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("SETTLEMENT-PENDING")) {
				throw new SmartOfficeException("Expense Claim cannot be settled in settlement-pending ");
			} else {
				List<String> userGroupIds = commonUtils.findLoggedInUserGroups();
				if (!userGroupIds.contains(expenseClaimFromDb.getSettleUserGroupId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");

				} else {
					expenseClaimFromDb.setInputCreditAmount(expenseClaim.getInputCreditAmount());
					expenseClaimFromDb.setSettleRemarks(expenseClaim.getSettleRemarks());
					expenseClaimFromDb.setSettleEmpId(commonUtils.getLoggedinEmployeeId());

					expenseClaimFromDb.setExpenseClaimStatus("SETTLED");
					expenseClaimFromDb.setSettleDecision("SETTLED");
					expenseClaimFromDb.setExpensePurpose("EXPENSE-CLAIM-SETLLEMENT");
					List<Config> config = (List<Config>) configService.getConfig("COMPANY-DEBIT-ACCOUNT", "COMPANY-DEBIT-ACCOUNT");
					expenseClaimFromDb.setDebitAccountNo(expenseClaim.getDebitAccountNo());
					expenseClaimFromDb.setSettledDt(LocalDate.now());
					expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
					expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaimFromDb, action, loggedInUser);
				}
			}
			
			break;
		case ("settlement-reject"):
			if (!expenseClaimFromDb.getExpenseClaimStatus().equals("SETTLEMENT-PENDING")) {
				throw new SmartOfficeException("Expense Claim cannot be settled in settlement-pending ");
			} else {
				List<String> userGroupIds = commonUtils.findLoggedInUserGroups();
				if (!userGroupIds.contains(expenseClaimFromDb.getSettleUserGroupId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");

				} else {
					expenseClaimFromDb.setSettleRemarks(expenseClaim.getSettleRemarks());
					expenseClaimFromDb.setSettleEmpId(commonUtils.getLoggedinEmployeeId());

					expenseClaimFromDb.setExpenseClaimStatus("SETTLED-REJECTED");
					expenseClaimFromDb.setSettleDecision("SETTLED-REJECTED");
					expenseClaimFromDb.setSettledDt(LocalDate.now());
					expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
				}
			}
			expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaim, action, loggedInUser);
			break;
		case ("cancel"):
			if (!(expenseClaimFromDb.getExpenseClaimStatus().equals("CREATED"))
					&& (!expenseClaimFromDb.getExpenseClaimStatus().equals("APPLIED")
							&& (!expenseClaimFromDb.getExpenseClaimStatus().equals("VALIDATION-PENDING")))) {
				throw new SmartOfficeException("Expense Claim cannot be cancelled at this stage");
			} else {
				if (!expenseClaimFromDb.getEmployeeId().equals(commonUtils.getLoggedinEmployeeId())) {
					throw new SmartOfficeException("Not a valid user to perform this action");
				}
				expenseClaimFromDb.setCancelRemarks(expenseClaim.getCancelRemarks());
				expenseClaimFromDb.setCancelEmpId(commonUtils.getLoggedinEmployeeId());
				expenseClaimFromDb.setExpenseClaimStatus("CANCELLED");
				expenseClaimFromDb.setCancelledDt(LocalDateTime.now());
				expenseClaimFromDb = updateExpenseClaim(expenseClaimFromDb);
			}
			expenseClaimEventGenerator.triggerExpenseClaimEvents(expenseClaim, action, loggedInUser);
			break;
		default:
			break;
		}

		return expenseClaim;
	}

}
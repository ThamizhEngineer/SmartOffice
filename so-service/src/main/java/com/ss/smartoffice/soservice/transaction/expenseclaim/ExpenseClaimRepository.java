package com.ss.smartoffice.soservice.transaction.expenseclaim;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.smartoffice.shared.model.BankAdviseData;



@Repository
@Transactional
@Scope("prototype")
public interface ExpenseClaimRepository extends CrudRepository<ExpenseClaim, Integer> {

	@Query("select new com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim(expense.id,expense.expenseClaimCode,expense.isJobBased,expense.jobId,expense.jobCode,expense.jobName,expense.costCenterId,expense.costCenterCode,expense.costCenterName,expense.expenseClaimStatus,expense.expenseClaimStatusName,expense.employeeId,expense.employeeCode,expense.employeeFName,expense.employeeLName,expense.expenseClaimAmount,expense.empRemarks,expense.appliedDt, "
			+ "expense.employeeId," + "expense.n1EmpId,expense.n1EmpCode,expense.n1EmpFName,expense.n1EmpLName,"
			+ "expense.n2EmpId ,expense.n2EmpCode,expense.n2EmpFName,expense.n2EmpLName,expense.createdDt) "
			+ "from ExpenseClaim  expense where expense.employeeId = :employeeId "
			+ "ORDER BY expense.appliedDt , expense.createdDt")
	List<ExpenseClaim> findExpenseClaimsByEmpId(String employeeId);

	@Query("select new com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim(expense.id,expense.expenseClaimCode,expense.isJobBased,expense.jobId,expense.jobCode,expense.jobName,expense.costCenterId,expense.costCenterCode,expense.costCenterName,expense.expenseClaimStatus,expense.expenseClaimStatusName,expense.employeeId,expense.employeeFName,expense.employeeLName,expense.expenseClaimAmount,expense.empRemarks,expense.appliedDt,"
			+ "expense.n1EmpId,expense.n1EmpCode,expense.n1EmpFName,expense.n1EmpLName, "
			+ "expense.n2EmpId ,expense.n2EmpCode,expense.n2EmpFName,expense.n2EmpLName,expense.settleUserGroupId,expense.createdDt) "
			+ "from ExpenseClaim  expense " + "where ifnull(expense.validateUserGroupId,'') in (:validationUserGroupIds) and (expense.expenseClaimStatus='VALIDATION-PENDING' or expense.validateDecision is not null) "
			+ "ORDER BY expense.appliedDt , expense.createdDt ")

	List<ExpenseClaim> findExpenseClaimsForValidation(List<String> validationUserGroupIds);
	
	Long countByValidateUserGroupIdInAndExpenseClaimStatus(List<String> validateUserGroupId, String expenseClaimStatus);

	@Query("select new com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim(expense.id,expense.expenseClaimCode,expense.isJobBased,expense.jobId,expense.jobCode,expense.jobName,expense.costCenterId,expense.costCenterCode,expense.costCenterName,expense.expenseClaimStatus,expense.expenseClaimStatusName,expense.employeeId,expense.employeeFName,expense.employeeLName,expense.expenseClaimAmount,"
            + "expense.empRemarks,expense.appliedDt,expense.n1EmpId,expense.n1EmpCode,expense.n1EmpFName,expense.n1EmpLName,expense.n2EmpId ,expense.n2EmpCode,expense.n2EmpFName,expense.n2EmpLName,expense.createdDt) "
            + "from ExpenseClaim  expense  where ( expense.n1EmpId = :n1EmpId and (expense.expenseClaimStatus = 'N1-APPROVAL-PENDING' or expense.n1Decision is not null) ) "
            + "OR  ( expense.n2EmpId = :n2EmpId and (expense.expenseClaimStatus = 'N2-APPROVAL-PENDING' or expense.n2Decision is not null) ) "
            + "ORDER BY expense.appliedDt , expense.createdDt")
List<ExpenseClaim> findByN1EmpIdOrN2EmpId(String n1EmpId, String n2EmpId);
	
	Long countByN1EmpIdAndExpenseClaimStatus(String n1EmpId, String expenseClaimStatus);
	Long countByN2EmpIdAndExpenseClaimStatus(String n2EmpId, String expenseClaimStatus);


	@Query("select new com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim(expense.id,expense.expenseClaimCode,expense.isJobBased,expense.jobId,expense.jobCode,expense.jobName,expense.costCenterId,expense.costCenterCode,expense.costCenterName,expense.expenseClaimStatus,expense.expenseClaimStatusName,expense.employeeId,expense.employeeFName,expense.employeeLName,expense.expenseClaimAmount,expense.empRemarks,expense.appliedDt,"
			+ "expense.employeeId,expense.n1EmpId,expense.n1EmpCode,expense.n1EmpFName,expense.n1EmpLName,"
			+ "expense.n2EmpId,expense.n2EmpCode,expense.n2EmpFName,expense.n2EmpLName,expense.createdDt) "
			+ "from ExpenseClaim expense where (expense.expenseClaimStatus='SETTLEMENT-PENDING' or expense.settleDecision is not null) and ifnull(expense.settleUserGroupId,'') in (:settlementUserGroupIds)  " 
			+ "ORDER BY expense.appliedDt , expense.createdDt ")

	List<ExpenseClaim> findExpenseClaimsForSettlement(List<String> settlementUserGroupIds);
	
	@Query( "select new com.ss.smartoffice.shared.model.BankAdviseData(emp.bankName,emp.ifscCode,emp.accNo,emp.accountName,expense.totalEntitledAmount,expense.settleRemarks,expense.expensePurpose,expense.debitAccountNo) from ExpenseClaim as expense  left join  BankDetails as emp on expense.employeeId=emp.employeeId where emp.isDefault='Y' and expense.expenseClaimStatus='SETTLED'and expense.expensePurpose = :expensePurpose AND expense.settledDt " + 
			"BETWEEN :fromDt and :endDt" )
	List<BankAdviseData> findByAccDetails(String expensePurpose,LocalDate fromDt,LocalDate endDt);


}

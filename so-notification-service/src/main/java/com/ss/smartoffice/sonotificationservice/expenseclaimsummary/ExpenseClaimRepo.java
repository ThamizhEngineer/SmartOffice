package com.ss.smartoffice.sonotificationservice.expenseclaimsummary;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ExpenseClaimRepo extends CrudRepository<ExpenseClaimSummary, Integer>{
	
	Long countByN1EmpIdAndExpenseClaimStatus(String n1EmpId,String expenseClaimStatus);
	Long countByN2EmpIdAndExpenseClaimStatus(String n2EmpId,String expenseClaimStatus);
//	Long countByValidateUserGroupIdAndExpenseClaimStatusIn(List<String> validateUserGroupId, String expenseClaimStatus);
//	Long countBySettleUserGroupIdAndExpenseClaimStatusIn(List<String> settleUserGroupId, String expenseClaimStatus);
}

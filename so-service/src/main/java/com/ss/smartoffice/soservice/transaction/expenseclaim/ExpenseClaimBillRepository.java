package com.ss.smartoffice.soservice.transaction.expenseclaim;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ExpenseClaimBillRepository extends CrudRepository<ExpenseClaimBill,Integer>{

}

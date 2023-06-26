package com.ss.smartoffice.soservice.master.expensecategory;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ExpenseRepository extends CrudRepository<ExpenseCategory, Integer> {

}

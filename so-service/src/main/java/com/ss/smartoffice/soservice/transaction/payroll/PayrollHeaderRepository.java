package com.ss.smartoffice.soservice.transaction.payroll;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.PayrollHeader;

public interface PayrollHeaderRepository extends CrudRepository<PayrollHeader, Integer> {

}

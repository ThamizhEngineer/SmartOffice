package com.ss.smartoffice.shared.pay;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.EmployeePayoutLeaveBalance;
@Scope("prototype")
public interface EmployeePayoutLeaveBalanceRepository extends CrudRepository<EmployeePayoutLeaveBalance, Integer> {
	List<EmployeePayoutLeaveBalance>findByEmployeeId(String employeeId);
}

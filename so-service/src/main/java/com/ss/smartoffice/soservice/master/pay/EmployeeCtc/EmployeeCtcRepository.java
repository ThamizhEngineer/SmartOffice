package com.ss.smartoffice.soservice.master.pay.EmployeeCtc;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface EmployeeCtcRepository extends CrudRepository<EmployeeCtc, Integer> {
	
	List<EmployeeCtc> findByEmployeeId(String employeeId);
}

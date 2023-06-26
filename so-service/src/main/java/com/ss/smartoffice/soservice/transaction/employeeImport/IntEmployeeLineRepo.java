package com.ss.smartoffice.soservice.transaction.employeeImport;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IntEmployeeLineRepo extends CrudRepository<IntEmployeeLine, Integer>{

	List<IntEmployeeLine> findByEmployeeHdrIdAndIsValid(String empHdrId,String isValid);
	
	List<IntEmployeeLine> findByEmployeeHdrId(String empHdrId);
}

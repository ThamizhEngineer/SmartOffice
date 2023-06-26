package com.ss.smartoffice.soservice.master.pay.EmployeeCtc;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface EmployeeCtcLinesRepository extends CrudRepository<EmployeeCtcLine, Integer>{

}

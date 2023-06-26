package com.ss.smartoffice.soservice.transaction.employeeservicerecord;

import org.springframework.context.annotation.Scope;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public interface EmployeeServiceLineRepo extends CrudRepository<EmployeeServiceLine,Integer>  {

}

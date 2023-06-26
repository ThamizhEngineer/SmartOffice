package com.ss.smartoffice.soservice.master.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.smartoffice.shared.model.employee.EmployeeCc;

@Repository
public interface EmployeeCcRepository extends CrudRepository<EmployeeCc,Integer> {

}

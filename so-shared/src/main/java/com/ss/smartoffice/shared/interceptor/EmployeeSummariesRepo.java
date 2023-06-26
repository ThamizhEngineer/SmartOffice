package com.ss.smartoffice.shared.interceptor;

import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.shared.model.employee.EmployeeSummaries;

public interface EmployeeSummariesRepo extends CrudRepository<EmployeeSummaries, Integer> {

}

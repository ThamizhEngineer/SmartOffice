package com.ss.smartoffice.soservice.transaction.dashboardservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DashboardAlertRepository extends CrudRepository<DashboardAlert, Integer> {
	List<DashboardAlert>findByEmployeeId(String employeeId);
}

package com.ss.smartoffice.sonotificationservice.dashboardalert;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface DashboardAlertRepository extends CrudRepository<DashboardAlert, Integer> {
	List<DashboardAlert>findByEmployeeId(String employeeId);
	Optional<List<DashboardAlert>> findByEmployeeIdAndFeatureId(String employeeId,String featureId);
}

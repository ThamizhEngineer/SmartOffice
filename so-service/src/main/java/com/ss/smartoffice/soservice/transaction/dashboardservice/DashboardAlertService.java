package com.ss.smartoffice.soservice.transaction.dashboardservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;

@RestController
@RequestMapping(path="transaction/dash-metrics")

public class DashboardAlertService {
@Autowired
DashboardAlertRepository dashboardAlertRepository;
@Autowired
EmployeeService employeeService;
@Autowired
CommonUtils commonUtils;
@GetMapping("/latest")
public List<DashboardAlert>getDashMetricsByEmployeeId()throws SmartOfficeException{
	String employeeId=commonUtils.getLoggedinEmployeeId();
	return dashboardAlertRepository.findByEmployeeId(employeeId);
	
}
public DashboardAlert employeeFeatures()throws SmartOfficeException{
	String authUserId=commonUtils.getLoggedinUserId();
//	List<>
	return null;
}

}

package com.ss.smartoffice.sonotificationservice.employee;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.sonotificationservice.model.Employee;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
@Component
public class EmployeeN2ManagerUpdateProcessor {
	@Value("${employees.url}")
	private String employeeUrl;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	EmployeeN2ManagerUpdateService employeeN2ManagerUpdateService;
	public void processEmployeeN2Update(Event event) {
		try {
			if(event.getNotificationKeys()!=null) {
				
			
//				String employeeId = event.getNotificationKeys().get(0).getEmployeeId();
				
//	Employee employee = commonUtils.getRestTemplate().getForObject(employeeUrl+ "/"+employeeId,
//						Employee.class);
//	
				try {
//					employeeN2ManagerUpdateService.updateN2ManagerToSelectedEmployees(employee);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				throw new SmartOfficeException("event keyvalues is empty ");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
	}
}

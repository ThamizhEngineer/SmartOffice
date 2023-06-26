//package com.ss.smartoffice.sonotificationservice.employee;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.ss.smartoffice.shared.common.CommonUtils;
//import com.ss.smartoffice.shared.model.SmartOfficeException;
//import com.ss.smartoffice.shared.model.Event;
//import com.ss.smartoffice.sonotificationservice.model.Employee;
//@Service
//public class EmployeeAcc2ManagerUpdateEventProcessor {
//	@Value("${employees.url}")
//	private String employeeUrl;
//	@Autowired
//	CommonUtils commonUtils;
//	@Autowired
//	EmployeeAcc2ManagerUpdateService employeeAcc2ManagerUpdateService;
//
//	public void processEmployeeAcc2Update(Event event) {
//		try {
//			if (event.getKeyValues() != null) {
//				Map<String, String> keyValues = event.getKeyValues();
//
//				String employeeId = keyValues.get("employee-id");
//
//				Employee employee = commonUtils.getRestTemplate().getForObject(employeeUrl + "/" + employeeId,
//						Employee.class);
//				try {
//					employeeAcc2ManagerUpdateService.updateAcc2ManagerToSelectedEmp(employee);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				throw new SmartOfficeException("event keyvalues is empty ");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new SmartOfficeException(e.getMessage());
//		}
//
//	}
//
//}

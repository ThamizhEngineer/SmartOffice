package com.ss.smartoffice.soservice.transaction.eligibility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;

@Service
public class EligibilityHelper {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	public void ProcessEligibility(String action,String empId) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();

		switch (action) {
		case "employee":
			List<memployee> empList = new ArrayList<memployee>();
			if(empId!=null) {
				empList.add(employeeRepository.findById(Integer.parseInt(empId)).get());
			}else {
				empList = (List<memployee>) employeeRepository.findAll();
			}
			employeeEligibilityCheck(empList);
			break;
		default:
			break;	
		}
		
	}
	
	public void employeeEligibilityCheck(@RequestBody List<memployee> empList)throws SmartOfficeException{
		for(memployee emp:empList) {
			if(emp.getOfficeId()==null || emp.getHr1UsrGrpId()==null || emp.getHr2UsrGrpId()==null 
					|| emp.getN1EmpId()==null || emp.getN2EmpId()==null || emp.getShiftId()==null) {
				emp.setAttendEligibility("N");
			}else {
				emp.setAttendEligibility("Y");
			}
		}
		employeeRepository.saveAll(empList);
	}
	
}

package com.ss.smartoffice.soservice.transaction.daybreak;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthToken;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.OfficeCalender;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.UserGroup;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.repos.AuthTokenRepo;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.officeCalender.OfficeCalenderRepo;
import com.ss.smartoffice.soservice.seed.userGroups.UserGroupRepo;
import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication;
import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplicationRepository;

@Service
public class DaybreakCheckHelper {
	
	@Autowired
	OfficeCalenderRepo officeCalenderRepo;
	
	@Autowired
	DaybreakCheckEventGenerator daybreakCheckEventGenerator;
	
	@Autowired 
	UserGroupRepo userGroupRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	AuthTokenRepo authTokenRepo;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;

	@Async("asyncThreadPoolTaskExecutor")
	public String process(String shiftCode,AuthUser loggedInUser) throws SmartOfficeException{		
		checkDayType(loggedInUser,shiftCode);		
		checkLeaveApprove(loggedInUser,shiftCode);
		checkEmployeeField(loggedInUser,shiftCode);
		return "process is initiated";
	}
	
	public void checkDayType(AuthUser loggedInUser,String shiftCode) throws SmartOfficeException{				
		List<OfficeCalender> officeCalenders =officeCalenderRepo.findByDayTypeIsNull(shiftCode);
		List<UserGroup> userGroups = userGroupRepo.findByIsAdmin();
		System.out.println(officeCalenderRepo.findByDayTypeIsNull(shiftCode));	
		if(!officeCalenders.isEmpty()) {
			for(OfficeCalender officeCalender:officeCalenders) {				
				System.out.println(userGroups);
				for(UserGroup usrGrp:userGroups) {
					daybreakCheckEventGenerator.checkDayTypeEvent(officeCalender,usrGrp.getId(),loggedInUser);	
				}
			}	
		}	
	}
	
	
	public void checkEmployeeField(AuthUser loggedInUser,String shiftCode) throws SmartOfficeException{
		List<memployee> empList = employeeRepository.findByAttendEligibility();
		List<memployee> empShiftList = employeeRepository.findByShiftCode(shiftCode);
		List<UserGroup> userGroups = userGroupRepo.findByIsHrL1();	
		for(memployee emp:empShiftList) {
			if(emp.getHr1UsrGrpId()==null) {
				for(UserGroup urGrp:userGroups) {						
					daybreakCheckEventGenerator.checkEmployeeEvent(emp, String.valueOf(urGrp.getId()),urGrp.getUserGroupName(),loggedInUser);
				}
			}else {					
				daybreakCheckEventGenerator.checkEmployeeEvent(emp, emp.getHr1UsrGrpId(),emp.getHr1UsrGrpName(),loggedInUser);
			}	
		}				
		for(memployee emp:empList) {			
			if(emp.getShiftCode()==null) {
				if(emp.getHr1UsrGrpId()==null) {
					for(UserGroup urGrp:userGroups) {						
						daybreakCheckEventGenerator.employeeShiftNotSetEvent(emp, String.valueOf(urGrp.getId()),urGrp.getUserGroupName(),loggedInUser);
					}
				}else {					
					daybreakCheckEventGenerator.employeeShiftNotSetEvent(emp, emp.getHr1UsrGrpId(),emp.getHr1UsrGrpName(),loggedInUser);
				}	
			}	
		}
				
	}
	
	public void checkLeaveApprove(AuthUser loggedInUser,String shiftCode) throws SmartOfficeException{
		LocalDate date=LocalDate.now().plusDays(1);		
		List<LeaveApplication> leaveAppls = leaveApplicationRepository.findUnApprovedLeave(date,shiftCode);
		for(LeaveApplication leave:leaveAppls) {
			if(leave.getLeaveStatus().equals("N1-APPROVAL-PENDING")) {
				daybreakCheckEventGenerator.checkUnApproveLeaveEvent(leave, leave.getN1EmpId(),loggedInUser);
			}else if(leave.getLeaveStatus().equals("N2-APPROVAL-PENDING")) {
				daybreakCheckEventGenerator.checkUnApproveLeaveEvent(leave, leave.getN2EmpId(),loggedInUser);
			}
		}
	}
	
}

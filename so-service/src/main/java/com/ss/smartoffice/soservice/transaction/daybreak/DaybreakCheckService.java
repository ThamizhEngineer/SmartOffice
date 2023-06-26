package com.ss.smartoffice.soservice.transaction.daybreak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;
import com.ss.smartoffice.soservice.master.shift.AttendanceShift;
import com.ss.smartoffice.soservice.master.shift.AttendanceShiftRepo;

@RestController
@RequestMapping("transaction/daybreak")
public class DaybreakCheckService {

	@Autowired
	DaybreakCheckHelper daybreakCheckHelper;
		
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping("/{shiftCode}")
	public String process(@PathVariable(value = "shiftCode")String shiftCode) throws SmartOfficeException{
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		daybreakCheckHelper.process(shiftCode,loggedInUser);				
		return "Process Started"; 
	}
	
}

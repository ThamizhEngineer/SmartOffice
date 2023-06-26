package com.ss.smartoffice.soservice.transaction.ShiftChange;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@Service
public class ShiftChangeHelper {

	@Autowired
	ShiftChangeRepo shiftChangeRepo;	
	
	@Autowired
	ShiftChangeRequestEventService shiftChangeRequestEventService;
	
	@Autowired
	CommonUtils commonUtils;
	
//	"request","cancel","approve","reject","proxy-request"
	
	public ShiftChange proccessShiftChange(String action,ShiftChange shiftChange)throws SmartOfficeException{
		System.out.println("Hit");
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		ShiftChange shiftChangeFromDb = new ShiftChange();
		if(!action.equals("request")&&!action.equals("proxy-request")) {
			shiftChangeFromDb=shiftChangeRepo.findById(shiftChange.getId()).get();
		}
		System.out.println("Hit 2");
		Integer count =shiftChangeRepo.checkDatePresent(shiftChange.getFromDt(), shiftChange.getToDt(), shiftChange.getEmpId());
		switch (action) {
		case "request":
			System.out.println("Hit 3");
			System.out.println(count);
			if(count==0) {
				shiftChange.setStatus("PENDING-APPROVAL");
				shiftChange.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				shiftChange.setCreatedDt(LocalDateTime.now());
				shiftChangeRepo.save(shiftChange);
				shiftChangeRequestEventService.shiftCreateEvent(shiftChange.getId(), loggedInUser);
				
			}else {
				throw new SmartOfficeException("Shift Already assigned from the given Date");
			}
			
			break;	
		case "cancel":
			
			if(shiftChangeFromDb.getStatus().equals("PENDING-APPROVAL")) {
				shiftChangeFromDb.setStatus("CANCELLED");
				shiftChangeFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				shiftChangeFromDb.setModifiedDt(LocalDateTime.now());
				shiftChangeRepo.save(shiftChangeFromDb);
			}else {
				throw new SmartOfficeException("shift change not in PENDING-APPROVAL state");
			}			
			break;	
		case "approve":
			if(shiftChangeFromDb.getStatus().equals("PENDING-APPROVAL")) {
				shiftChangeFromDb.setStatus("APPROVED");
				shiftChangeFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				shiftChangeFromDb.setModifiedDt(LocalDateTime.now());
				shiftChangeRepo.save(shiftChangeFromDb);
				shiftChangeRequestEventService.shiftActionEvent(shiftChangeFromDb.getId(), loggedInUser);
			}else {
				throw new SmartOfficeException("shift change not in PENDING-APPROVAL state");
			}
			break;	
		case "reject":
			if(shiftChangeFromDb.getStatus().equals("PENDING-APPROVAL")) {
				shiftChangeFromDb.setStatus("REJECTED");
				shiftChangeFromDb.setRemark(shiftChange.getRemark());
				shiftChangeFromDb.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				shiftChangeFromDb.setModifiedDt(LocalDateTime.now());
				shiftChangeRepo.save(shiftChangeFromDb);
				shiftChangeRequestEventService.shiftActionEvent(shiftChangeFromDb.getId(), loggedInUser);
			}else {
				throw new SmartOfficeException("shift change not in PENDING-APPROVAL state");
			}
			break;	
		case "proxy-request":
			
			if(count==0) {
				shiftChange.setStatus("APPROVED");
				shiftChange.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				shiftChange.setCreatedDt(LocalDateTime.now());
				shiftChangeRepo.save(shiftChange);
				shiftChangeRequestEventService.shiftActionEvent(shiftChange.getId(), loggedInUser);
			}else {
				throw new SmartOfficeException("Shift Already assigned from the given Date");
			}
			
			break;	
		}
		return shiftChange;
	}
	
	
}

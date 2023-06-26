package com.ss.smartoffice.soservice.myspace;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.model.attendance.Attendance;
import com.ss.smartoffice.shared.model.attendance.AttendanceRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
import com.ss.smartoffice.soservice.seed.holiday.Holiday;
import com.ss.smartoffice.soservice.seed.holiday.HolidayRepository;
// import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim;
// import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaimRepository;
import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication;
import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplicationBusHelper;
import com.ss.smartoffice.soservice.transaction.task.Task;
import com.ss.smartoffice.soservice.transaction.task.TaskService;

@RestController
@RequestMapping(path="/my-space")

public class MySpaceService {
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	EmployeePayoutRepository employeePayoutRepository;

	
	// @Autowired
	// ExpenseClaimRepository expenseClaimRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	HolidayRepository holidayRepository;
	
	
	
	@GetMapping("/my-attendances")
	private Iterable<Attendance> getMyAttendances(
			@RequestParam(value = "attendanceMonth", required = false) String attendanceMonth , @RequestParam(value = "attendanceYear",required = false)String attendanceYear)throws SmartOfficeException{
		String employeeId=commonUtils.getLoggedinEmployeeId();
		System.out.println("employeeId-"+employeeId);
		return attendanceRepository.getMyAttendances(attendanceMonth,attendanceYear,employeeId);
}
	
	@GetMapping("/my-team-attendances")
	private Iterable<Attendance> getAttendances(
			@RequestParam(value = "attendanceDt", required = false) String attendanceDtString,@RequestParam(value = "attendanceMonth", required = false) String attendanceMonth , @RequestParam(value = "attendanceYear",required = false)String attendanceYear)throws SmartOfficeException{
		String managerId=commonUtils.getLoggedinEmployeeId();
		System.out.println(managerId);
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		if(attendanceDtString!=null) {
	
			attendanceList = attendanceRepository.findByN1EmpIdAndAttendanceDt(managerId,LocalDate.parse(attendanceDtString));
		}else {
			attendanceList = attendanceRepository.findByAttendanceMonthAndAttendanceYearAndN1EmpId(Integer.parseInt(attendanceMonth) ,Integer.parseInt(attendanceYear)  , managerId);
		}

		return attendanceList;
}
	

	
//	@GetMapping("/my-leaves")
//	public Iterable<LeaveApplication>getMyLeaves(Model model, Pageable pageable)
//			throws Exception {
//		System.out.println(commonUtils.getLoggedinEmployeeId());
//		return leaveApplicationBusHelper.fetchMyLeaveResults(pageable,commonUtils.getLoggedinEmployeeId(),null);
//	}
//	
//
//	
//	@GetMapping("/my-leave-approvals")
//	public Iterable<LeaveApplication> getMyLeaveApprovals(Model model, Pageable pageable)
//			throws Exception {
//		
//		System.out.println(commonUtils.getLoggedinEmployeeId());
//		return leaveApplicationBusHelper.fetchMyLeaveResults(pageable,null,commonUtils.getLoggedinEmployeeId());
//	}
	
 
	
//	@GetMapping("/my-expense-claims")
//	public Iterable<ExpenseClaim>getMyExpense(Model model, Pageable pageable,
//			@RequestParam(value = "employeeId",required = false) String employeeId)
//			throws Exception {
//		
//	       Page<ExpenseClaim> pages = expenseDetail(pageable,employeeId);
//	       model.addAttribute("number", pages.getNumber());
//	       model.addAttribute("totalPages", pages.getTotalPages());
//	       model.addAttribute("totalElements", pages.getTotalElements());	
//	       model.addAttribute("size", pages.getSize());
//	       model.addAttribute("expenseDetail", pages.getContent());
//	       return pages;
//	}
//	
//
//	public Page<ExpenseClaim> expenseDetail(Pageable pageable,String employeeId){
//		boolean searchByEmployeeId = false;
//		if (employeeId != null)
//			searchByEmployeeId = true;
//		
//		 if (searchByEmployeeId) {
//			return expenseClaimRepository.findByEmployeeId(pageable, employeeId);			
//
//		} 
//        return expenseClaimRepository.findAll(pageable);
//
//	}
//	
//	@GetMapping("/my-expense-claim-approvals")
//	public Iterable<ExpenseClaim>getMyClaim(Model model, Pageable pageable,
//			@RequestParam(value = "approveEmployeeId",required = false) Integer approveEmployeeId)
//			throws Exception {
//		
//	       Page<ExpenseClaim> pages = claimDetail(pageable,approveEmployeeId);
//	       model.addAttribute("number", pages.getNumber());
//	       model.addAttribute("totalPages", pages.getTotalPages());
//	       model.addAttribute("totalElements", pages.getTotalElements());	
//	       model.addAttribute("size", pages.getSize());
//	       model.addAttribute("claimDetail", pages.getContent());
//	       return pages;
//	}
//	
//
//	public Page<ExpenseClaim> claimDetail(Pageable pageable,Integer approveEmployeeId){
//		boolean searchByApproveEmployeeId = false;
//		if (approveEmployeeId != null)
//			searchByApproveEmployeeId = true;
//		
//		 if (searchByApproveEmployeeId) {
//			return expenseClaimRepository.findByApproveEmployeeId(pageable, approveEmployeeId);			
//
//		} 
//        return expenseClaimRepository.findAll(pageable);
//
//	}
//	
//	@GetMapping("/my-expense-claim-reviews")
//	public Iterable<ExpenseClaim>getMyReviews(Model model, Pageable pageable,
//			@RequestParam(value = "reviewEmployeeId",required = false) Integer reviewEmployeeId)
//			throws Exception {
//		
//	       Page<ExpenseClaim> pages = reviewDetails(pageable,reviewEmployeeId);
//	       model.addAttribute("number", pages.getNumber());
//	       model.addAttribute("totalPages", pages.getTotalPages());
//	       model.addAttribute("totalElements", pages.getTotalElements());	
//	       model.addAttribute("size", pages.getSize());
//	       model.addAttribute("reviewDetails", pages.getContent());
//	       return pages;
//	}
//	
//
//	public Page<ExpenseClaim> reviewDetails(Pageable pageable,Integer reviewEmployeeId){
//		boolean searchByReviewEmployeeId = false;
//		String employeeId=commonUtils.getLoggedinEmployeeId();
//
//		if (reviewEmployeeId != null)
//			searchByReviewEmployeeId = true;
//		
//		 if (searchByReviewEmployeeId) {
//			return expenseClaimRepository.findByReviewEmployeeId(pageable, reviewEmployeeId);			
//
//		} 
//        return expenseClaimRepository.findAll(pageable);
//
//	}
	
	@GetMapping("/my-payouts")
	public Iterable<EmployeePayout> getMyPayouts()
			throws Exception {
		return employeePayoutRepository.findByEmployeeId(commonUtils.getLoggedinEmployeeId());
	}
	
	@GetMapping("/my-tasks")
	public Iterable<Task> getMyTasks(Model model, Pageable pageable)throws SmartOfficeException{
		return taskService.getAllTasks(pageable, model, null, null, null, commonUtils.getLoggedinUserId(), null, null, null, null, null, null);
	
	}
	
	@GetMapping("/my-holidays")
	public Iterable<Holiday> getMyHolidays(@RequestParam(value = "holidayYear",required = false) String holidayYear)throws SmartOfficeException{
		return holidayRepository.findByHolidayYear(holidayYear);
	
	}
}



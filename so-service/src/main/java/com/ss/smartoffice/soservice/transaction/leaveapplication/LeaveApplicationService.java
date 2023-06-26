package com.ss.smartoffice.soservice.transaction.leaveapplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.id.enhanced.LegacyHiLoAlgorithmOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;

import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalance;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalanceRepository;
import com.ss.smartoffice.soservice.master.UserGroupEmployeeMapping.UserGroupEmployeeMappingService;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.seed.LeaveType.LeaveType;
import com.ss.smartoffice.soservice.seed.LeaveType.LeaveTypeRepository;
import com.ss.smartoffice.soservice.temp.jobemployee.TempJobEmp;
import com.ss.smartoffice.soservice.temp.jobemployee.TempJobService;
import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim;
import com.ss.smartoffice.soservice.transaction.job.JobPlanEmployeeRepository;
import com.ss.smartoffice.soservice.transaction.model.JobPlanEmp;

@RestController
@RequestMapping("transaction/leave-applications")

public class LeaveApplicationService {
	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;
	@Autowired
	LeaveTypeRepository leaveTypeRepository;

	@Autowired
	LeaveApplicationBusHelper leaveApplicationBusHelper;

	@Autowired
	LeaveBalanceRepository leaveBalanceRepository;

	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	JobPlanEmployeeRepository jobPlanEmployeeRepository;
	
	@Autowired
	UserGroupEmployeeMappingService userGroupEmployeeMappingService;

	// @CrossOrigin(origins = "*")
	@GetMapping("/my-leave-applns")
	
	public List<LeaveApplication>getMyLeaveApplns()throws SmartOfficeException{
		String employeeId=commonUtils.getLoggedinEmployeeId();
		return leaveApplicationRepository.findByEmployeeIdByLeaveApplnDt(employeeId);
	}
	@GetMapping("/jobs-in-leave")
	public List<JobPlanEmp> jobsInleave(@RequestParam(value="employeeId")Integer employeeId,@RequestParam(value="fromDt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDt,@RequestParam(value = "toDt")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDt)throws SmartOfficeException{
		List<JobPlanEmp>empLeaveJobs= jobPlanEmployeeRepository.findByJobsBtwDates(fromDt, toDt, employeeId.toString());
		return empLeaveJobs;
	}
	
	@GetMapping("/leave-applns-for-my-approval")
	
	public List<LeaveApplication>getLeaveApplnsForMyApproval()throws SmartOfficeException{
		String n1EmpId=commonUtils.getLoggedinEmployeeId();
		String n2EmpId=n1EmpId;
		return leaveApplicationRepository.findByN1EmpIdOrN2EmpId(n1EmpId,n2EmpId);
	}
	
	@GetMapping("/leave-applns-for-settlement")
	public List<LeaveApplication>getLeaveApplnsForSettlement()throws SmartOfficeException{
		List<LeaveApplication> leaves = new ArrayList<LeaveApplication>();
		List<String> userGroupIds = userGroupEmployeeMappingService.getUserGroupHrId();
		if(userGroupIds!=null) {
			leaves=leaveApplicationRepository.findByHr1EmpIdByIsSettledIn(userGroupIds);
		}
		return leaves;
		}
	
	@GetMapping("/{id}")
	public Optional<LeaveApplication> getleaveApplnById(@PathVariable (value="id")int id)throws SmartOfficeException{
		
	return leaveApplicationRepository.findById(id);
		
	}

	@PatchMapping("/leave-balances/check-validity")
	public LeaveApplication checkLeaveValidity(@RequestBody LeaveApplication leaveApplication)
			throws SmartOfficeException {
	
	
System.out.println(leaveApplication);
		Integer id=Integer.parseInt(leaveApplication.getLeaveTypeId());
		
		LeaveType leaveType = leaveTypeRepository.findById(id).get();
	
			if (leaveType.getLeaveTypeCode().equals("CL")) {
				leaveApplication.setLeaveTypeCode("CL");

				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveApplication.setStartDt(leaveApplication.getStartDt());
			
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Casual Leave");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for CL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for CL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				
				

			} if  (leaveType.getLeaveTypeCode().equals("SL")) {
				  leaveApplication.setLeaveTypeCode("SL");

				System.out.println(leaveType.getLeaveTypeCode());
				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setStartDt(leaveApplication.getStartDt());
				
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Sick Leave");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for SL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for SL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}
				else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				
				

			}
			if  (leaveType.getLeaveTypeCode().equals("PL")) {
				  leaveApplication.setLeaveTypeCode("PL");

				System.out.println(leaveType.getLeaveTypeCode());
				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setStartDt(leaveApplication.getStartDt());
				
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Privileage Leave");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for WL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for WL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}
				else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				
				

			}
			
			

			 if (leaveType.getLeaveTypeCode().equals("WL")) {
				  leaveApplication.setLeaveTypeCode("WL");

				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				
				leaveApplication.setStartDt(leaveApplication.getStartDt());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Wedding Leave");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for WL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for WL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}
				else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				
				

			}

			 if (leaveType.getLeaveTypeCode().equals("ML")) {
				  leaveApplication.setLeaveTypeCode("ML");

				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveApplication.setStartDt(leaveApplication.getStartDt());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Maternity Leave");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for ML");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for ML");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}
				else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				
				
			}
		 if (leaveType.getLeaveTypeCode().equals("PTL")) {
			 leaveApplication.setLeaveTypeCode("PTL");
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setStartDt(leaveApplication.getStartDt());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Paternity Leave");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for PTL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for PTL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}
				else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				
			

			}

		  if (leaveType.getLeaveTypeCode().equals("AL")) {
			  leaveApplication.setLeaveTypeCode("AL");
				leaveApplication.setLeaveTypeId(leaveApplication.getLeaveTypeId());
				leaveApplication.setLeaveAppliedDate(LocalDate.now());
				leaveApplication.setStartDt(leaveApplication.getStartDt());
				leaveApplication.setEmployeeId(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setEndDt(leaveApplication.getEndDt());
				leaveApplication.setStartSession(leaveApplication.getStartSession());
				leaveApplication.setEndSession(leaveApplication.getEndSession());
				leaveApplication.setEligible("Y");
				leaveApplication.setCreatedBy(commonUtils.getLoggedinEmployeeId());
				leaveApplication.setCreatedDt(LocalDateTime.now());
				double leaveDuration = ChronoUnit.DAYS.between(leaveApplication.getStartDt(), leaveApplication.getEndDt());
				System.out.println(leaveDuration); 
				if(leaveApplication.getStartSession().equals("AN")) {
					 leaveDuration= (leaveDuration-0.5);
					System.out.println("AN"+leaveDuration);
				}
				if(leaveApplication.getEndSession().equals("AN")) {
					leaveDuration= (leaveDuration+0.5);
					System.out.println("AN-end"+leaveDuration);
				}
				if(leaveDuration<=0.0) {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleRemarks("Leave duration less than zero not applicable");
					
				}
				LeaveBalance leaveBalanceForEmpId=leaveBalanceRepository.findByleaveTypeIdAndEmployeeId(leaveApplication.getLeaveTypeId(), leaveApplication.getEmployeeId()).get(0);
				Integer leaveBalance=Integer.parseInt(leaveBalanceForEmpId.getLeaveBalance());
				if(leaveBalance<=0) {
					leaveApplication.setEligibleRemarks("No more leaves allowed for Adoption Leave ");
					leaveApplication.setEligible("Y");
				}
				if(leaveType.getApprovalDriven().equals("Y")) {
					leaveApplication.setEligibleRemarks("Leave is totally based on manager's approval");
					leaveApplication.setEligible("A");
				}
				if(Double.parseDouble(leaveType.getMinDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Minimum Duration"+leaveType.getMinDuration()+" permitted for AL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveType.getMaxDuration())>leaveDuration) {
					leaveApplication.setEligibleRemarks("Applied Leave Duration"+leaveDuration+" is less than Maximum Duration"+leaveType.getMaxDuration()+" permitted for AL");
					leaveApplication.setEligible("N");
				
				}
				else if(Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance())<leaveDuration) {
					leaveApplication.setEligibleRemarks("Not Enough leaves available in this leave type");
					leaveApplication.setEligible("N");
					leaveApplication.setEligibleDuration(leaveDuration-Double.parseDouble(leaveBalanceForEmpId.getLeaveBalance()));
				}
				else {
					leaveApplication.setEligible("Y");
					leaveApplication.setEligibleDuration(leaveDuration);
				}
				

			}
		
		return leaveApplication;

	}
	@PostMapping("/{action}")
	public LeaveApplication createOrApply(@PathVariable(value="action")String action,@RequestBody LeaveApplication leaveApplication)throws SmartOfficeException{
		
		if(action.equals("apply")|| action.equals("create")) {
			leaveApplication=leaveApplicationBusHelper.processLeave(action, leaveApplication);	
			System.out.println(leaveApplication);
			}else {
				throw new SmartOfficeException("Invalid URL");
			}
			return leaveApplication;
	}
	
	@PatchMapping("/{action}")
	public LeaveApplication processReq(@PathVariable(value="action")String action,@RequestBody LeaveApplication leaveApplication)throws SmartOfficeException{
		if(!action.equals("update")&&!action.equals("n1-approve")&&!action.equals("n1-reject")&&!action.equals("n2-approve")&&!action.equals("n2-reject")&&!action.equals("hr1-settle")&&!action.equals("cancel")) {
			throw new SmartOfficeException("Invalid URL");
		}else {
			leaveApplication=leaveApplicationBusHelper.processLeave(action, leaveApplication);
		}
		return leaveApplication;
		
	}
	

	

}

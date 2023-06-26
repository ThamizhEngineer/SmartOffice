package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.interceptor.NumberToWords;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.model.EmployeePayoutLeaveBalance;
import com.ss.smartoffice.shared.model.EmployeePayoutLine;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.pay.EmployeePayoutLeaveBalanceRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutLinesRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
//import com.ss.smartoffice.soservice.event.EventGenerator;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalance;
import com.ss.smartoffice.soservice.master.Leavebalance.LeaveBalanceRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.master.pay.CompanyPayCycle.CompanyPayCycleLinesRepository;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplicationRepository;
import com.ss.smartoffice.soservice.transaction.model.ManualUploadMap;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipHdr;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
import com.ss.smartoffice.soservice.transaction.pay.EmployeePayouts.EmployeePayoutService;
@Service
public class UploadPaySlipLineProcessor {
	
	List<ManualUploadMap> allowanceList;
	List<ManualUploadMap> deductionList;
	List<ManualUploadMap> leaveList;


	Integer lineOrder=0;
	float a1PeriodTotal=0;
	float a2PeriodTotal=0;
	float bPeriodTotal=0;
	float a1ArrearTotal=0;
	float a2ArrearTotal=0;
	float bArrearTotal=0;
	float a1Total=0;
	float a2Total=0;
	float bTotal=0;
	float a1YtdTotal=0;
	float a2YtdTotal=0;
	float bYtdTotal=0;
	float allowanceTotal=0;
	float deductionYtdTotal=0;
	float deductionTotal=0;
	int leaveOpeningTotal=0;
	int leaveAccuredTotal=0;
	int leaveAvailedTotal=0;
	int leaveBalanceTotal=0;
	
	
	@Autowired
	UploadPayslipHdrRepository uploadPayslipHdrRepository;

	@Autowired
	EventRepository eventRepository;
	@Autowired
	EmployeePayout employeePayout;
	
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	@Autowired
	EventPublisherService eventPublisherService;
	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;
	@Autowired
	LeaveBalanceRepository leaveBalanceRepository;
	@Autowired
	memployee memployee;
	@Autowired
	CompanyPayCycleLinesRepository companyPayCycleLinesRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeePayoutService employeePayoutService;
	@Autowired
	ManualUploadMapService manualUploadMapService;
	@Autowired
	EmployeePayoutRepository employeePayoutRepository;
	@Autowired
	NumberToWords numberToWords;
	@Autowired
	EmployeePayoutLinesRepository employeePayoutLinesRepository;
	@Autowired
	EmployeePayoutLeaveBalanceRepository employeePayoutLeaveBalanceRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	UploadPayslipHdrService uploadPayslipHdrService;
	@Autowired
	UploadPayslipLineRepository uploadPaySlipLineRepository;

	/**
	 * 
	 * @param uploadPayslipLine
	 * @return uploadPayslipLine - after setting status/remarks
	 * ProcessLogic - 
	 * 		validate uploadPayslipLine
	 * 		create memployee if doesn't exist
	 *  	transform to empPayoutHeader
	 *  	get allowances, deductions, leaves to be mapped from seed table
	 *  	create empPayout
	 *  	
	 *  	update uploadSalaryLine
	 */

	public UploadPayslipLine process(UploadPayslipLine psLine) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		UploadPayslipLine uploadPayslipLine = new UploadPayslipLine();
		try {
			init();

			psLine.setStatus("PROCESSING");
			uploadPayslipLine = uploadPaySlipLineRepository.save(psLine);
			validate(uploadPayslipLine);
			findOrCreateEmployee(uploadPayslipLine);
//			check if payout already exists for that memployee in that salary Month and year
			if (employeePayoutService.doesPayoutExist(uploadPayslipLine.getEmployeeCode(),
					uploadPayslipLine.getSalaryMonth(), uploadPayslipLine.getSalaryYear())) {
				throw new SmartOfficeException("Salary Slip already Processed ");
			}
			mapPayoutHeader(uploadPayslipLine);
			employeePayout = employeePayoutService.addEmployeePayout(employeePayout);
			fetchConfiguration();
			mapAllowancesToEmployeePayout(uploadPayslipLine);
			mapDeductionsToEmployeePayout(uploadPayslipLine);
			employeePayout = employeePayoutService.addOrUpdateEmployeePayoutLines(employeePayout);
			mapLeavesToEmployeePayout(uploadPayslipLine);
			employeePayout = employeePayoutService.addOrUpdateLeaveBalances(employeePayout);
			mapSummariesToPayoutHeader();
			uploadPayslipLine.setStatus("SUCCESS");
			employeePayout = employeePayoutService.updateEmployeePayout(employeePayout);
			if (uploadPayslipLine.getRemarks() == null) {

				uploadPayslipLine.setStatus("SUCCESS");
				uploadPayslipLine.setRemarks("PaySlip Created");
				uploadPaySlipLineRepository.save(uploadPayslipLine);
//			System.out.println("psLine"+uploadPayslipLine);
			}
			try {
				if (uploadPayslipLine.getRemarks().equalsIgnoreCase("PaySlip Created")) {
					employeePayoutService.sendEmail(employeePayout.getId(), employeePayout);
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			psLine.setStatus("SUCCESS");
			psLine.setRemarks("Payslip Created");
			uploadPayslipLine = uploadPaySlipLineRepository.save(psLine);
		} catch (Exception e) {
			e.printStackTrace();
			uploadPayslipLine.setStatus("ERROR");
			uploadPayslipLine.setRemarks(e.getMessage());
//			salaryProcessErrorEvent(loggedInUser, psLine, psLine.getRemarks());
		}
		return psLine;
	}
	@Lazy
	@Async("asyncThreadPoolTaskExecutor")
	public void triggerFailedSalaryProcessedEvent(AuthUser loggedInUser,UploadPayslipHdr uploadPayslipHdr,UploadPayslipLine uploadPayslipLine,String message, Integer total)throws SmartOfficeException{
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		Event savedEvent =new Event();
		
		List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
		BusinessKey busKey = new BusinessKey();
		EventKeyValue keyValue = new EventKeyValue();
		List<EventKeyValue> ekvs = new ArrayList<>();
		event.setName(Event.EventTypes.SalaryProcessFailureEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
//		event.setAppToken(commonUtils.getLoggedinAppToken());
		event.setContextAuthUserId(loggedInUser.getId());
		savedEvent = eventRepository.save(event);
		busKeys = new ArrayList<BusinessKey>();
		busKey = new BusinessKey();
		busKey.setEmployeeId(commonUtils.getLoggedinEmployeeId());
		businessKeyRepository.save(busKey);
		busKeys.add(busKey);
		savedEvent.setBusinessKeys(busKeys);
		System.out.println("kh 2 - uphline - savedEvent"+savedEvent);

		memployee emp=employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		if(message==null) {
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1UsrGrpName", emp.getEmpName());
		ekvs.add(keyValue);
		
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "uploadCode", uploadPayslipHdr.getUpload_code());
		ekvs.add(keyValue);
		
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "total", String.valueOf(total));
		ekvs.add(keyValue); 

		}
		if(message!=null) {
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1UsrGrpName", emp.getEmpName());
			ekvs.add(keyValue);
				
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "uploadCode", uploadPayslipHdr.getUpload_code());
			ekvs.add(keyValue);
			
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "total", String.valueOf(total));
			ekvs.add(keyValue); 
				
		}
		savedEvent.setEventKeyValues(ekvs);
		eventPublisherService.pushEvent(savedEvent);
	}
	
	@Async("asyncThreadPoolTaskExecutor")
	public void salaryProcessErrorEvent(AuthUser loggedInUser,UploadPayslipLine uploadPayslipLine,String message)throws SmartOfficeException{
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		Event savedEvent =new Event();
		
		List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
		BusinessKey busKey = new BusinessKey();
		EventKeyValue keyValue = new EventKeyValue();
		List<EventKeyValue> ekvs = new ArrayList<>();
		event.setName(Event.EventTypes.SalaryProcessErrorEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		savedEvent = eventRepository.save(event);
		System.out.println("kh - uphline - savedEvent"+savedEvent);

		busKeys = new ArrayList<BusinessKey>();
		busKey = new BusinessKey();
		busKey.setEmployeeId(commonUtils.getLoggedinEmployeeId());
		businessKeyRepository.save(busKey);
		busKeys.add(busKey);
		savedEvent.setBusinessKeys(busKeys);
		memployee emp=employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		if(message==null) {
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1UsrGrpName", emp.getEmpName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "employeeName", uploadPayslipLine.getEmployeeName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "errorMessage", uploadPayslipLine.getRemarks());
			ekvs.add(keyValue);
		}
		if(message!=null) {
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1UsrGrpName", emp.getEmpName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "employeeName", uploadPayslipLine.getEmployeeName());
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "errorMessage", uploadPayslipLine.getRemarks());
			ekvs.add(keyValue);
		}
		savedEvent.setEventKeyValues(ekvs);
		eventPublisherService.pushEvent(savedEvent);
	}
	private void init() {
		allowanceList = new ArrayList<ManualUploadMap>() ;
		deductionList = new ArrayList<ManualUploadMap>() ;
		leaveList = new ArrayList<ManualUploadMap>() ;
		lineOrder=0;
		a1PeriodTotal=0;
		a2PeriodTotal=0;
		bPeriodTotal=0;
		a1ArrearTotal=0;
		a2ArrearTotal=0;
		bArrearTotal=0;
		a1Total=0;
		a2Total=0;
		bTotal=0;
		a1YtdTotal=0;
		a2YtdTotal=0;
		bYtdTotal=0;
		allowanceTotal=0;
		deductionYtdTotal=0;
		deductionTotal=0;
		leaveOpeningTotal=0;
		leaveAccuredTotal=0;
		leaveAvailedTotal=0;
		leaveBalanceTotal=0;	
	}
	private void validate(UploadPayslipLine psLine) {
		try {
//			System.out.println("hi");
			String validationError = "";
			if(psLine.getEmployeeCode().trim().isEmpty()||psLine.getEmployeeCode().trim().equals("-")) {
				validationError = "EmployeeCode is mandatory";
			}
			if(psLine.getEmployeeName().trim().isEmpty()||psLine.getEmployeeName().trim().equals("-")) {
				validationError = "EmployeeName is mandatory";
			}
			if(psLine.getMobileNo().trim().isEmpty()||psLine.getMobileNo().trim().equals("-")) {
				validationError = "Mobile No is mandatory";
			}
			if(psLine.getEmail().trim().isEmpty()||psLine.getEmail().trim().equals("-")) {
				validationError = "EmailId is mandatory";
			}
			if(psLine.getSalaryMonth().trim().isEmpty()||psLine.getSalaryMonth().trim().equals("-")) {
				validationError = "Salary Month is mandatory";
			}
			if(psLine.getSalaryYear().trim().isEmpty()||psLine.getSalaryYear().trim().equals("-")) {
				validationError = "Salary Year is mandatory";
			}
			if(!validationError.isEmpty()) {
				throw new SmartOfficeException("VALIDATION ERROR - "+validationError);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("VALIDATION ERROR - "+e.getLocalizedMessage());
		}
	}
	
	private void findOrCreateEmployee(UploadPayslipLine psLine) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		List<memployee> existingEmployees = employeeService.findByEmployeeCode(psLine.getEmployeeCode());
		
//			System.out.println("hi in find or create employee"+existingEmployees);
		
			if(!existingEmployees.isEmpty()) {
				memployee = existingEmployees.get(0);
//				System.out.println(memployee);
			}else {
				psLine.setRemarks("Given employee not found in employee table- New Employee cannot be Processed");
				psLine.setStatus("Error-Payslip Not Created");
				uploadPaySlipLineRepository.save(psLine);
				
			}
	}
	
	private void mapPayoutHeader(UploadPayslipLine psLine) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		try {
			
			employeePayout = new EmployeePayout();
			employeePayout.setEmployeeCode(memployee.getEmpCode());
			employeePayout.setEmployeeName(memployee.getEmpName());
			if(memployee.getId()!=null) {
			employeePayout.setEmployeeId(memployee.getId().toString());
			}
			employeePayout.setdEmail(psLine.getEmail());
			employeePayout.setdMobileNumber(psLine.getMobileNo());
			employeePayout.setDob(psLine.getDob());
			employeePayout.setDoj(psLine.getDoj());
			employeePayout.setDepartment(psLine.getDepartment());
			employeePayout.setdDesignation(psLine.getDesignation());
			employeePayout.setdGrade(psLine.getGrade());
			employeePayout.setSpecialDesignation(psLine.getSpecialDesignation());
			employeePayout.setSkill(psLine.getSkill());
			employeePayout.setBuisness(psLine.getBusiness());
			employeePayout.setNatureOfPosition(psLine.getNatureOfPosition());
			employeePayout.setDepartment(psLine.getDepartment());
			employeePayout.setLocationOfPosition(psLine.getLocationOfPosition());
			employeePayout.setdSalaryForMonth(psLine.getSalaryMonth());
			employeePayout.setdSalaryForYear(psLine.getSalaryYear());
			employeePayout.setRemarks(psLine.getRemarksValue());
			employeePayout.setdPfNo(psLine.getPfNo());
			employeePayout.setEpsNo(psLine.getEpsNo());
			employeePayout.setdEsiNumber(psLine.getEsiNo());
			employeePayout.setdAccountNumber(psLine.getBankAccountNumber());
			employeePayout.setdBankName(psLine.getBank());
			employeePayout.setIfscCode(psLine.getIfscCode());
			employeePayout.setdPanNumber(psLine.getPanNo());
			employeePayout.setAadharNo(psLine.getAadharNo());
			employeePayout.setUanNo(psLine.getUanNo());
			employeePayout.setCurrency(psLine.getCurrency());
			employeePayout.setPeriodDays(psLine.getPeriodDays());
			employeePayout.setPeriodHolidays(psLine.getPeriodHolidays());
			employeePayout.setWorkedDays(psLine.getWorkedDays());
			employeePayout.setLopAndLwop(psLine.getLopAndLwop());
			employeePayout.setArrearDays(psLine.getArrearDays());
			employeePayout.setLateDaysDeduction(psLine.getLateDaysDeduction());
			employeePayout.setOvertimeHours(psLine.getOvertimeHours());
			employeePayout.setWageDays(psLine.getWageDays());
			
//			employeePayout.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
			employeePayout.setIsActive("Y");
			employeePayout.setIsEnabled("Y");
			employeePayout.setAdditionalInfo1(psLine.getAdditionalInfo1());
//			System.out.println(employeePayout.getAdditionalInfo3());
			employeePayout.setAdditionalInfo2(psLine.getAdditionalInfo2());
			employeePayout.setAdditionalInfo3(psLine.getAdditionalInfo3());
			employeePayout.setPfAccumulationByEmployer(psLine.getPfAccumulationByEmployer());
			employeePayout.setPfOpeningValue(psLine.getPfOpeningValue());
			employeePayout.setPfCurrentValue(psLine.getPfClosingValue());
			employeePayout.setPfBalanceValue(psLine.getPfBalanceValue());
		
		} catch (Exception e) {
			e.printStackTrace();
//			triggerFailedSalaryProcessedEvent(loggedInUser, psLine);
//			throw new SmartOfficeException("TRANSFORMATION ERROR - "+e.getLocalizedMessage());
			
		}
		
	}
	
	private void fetchConfiguration() {
		try {

			Iterable<ManualUploadMap> manualUploadMapIterable = manualUploadMapService.getUploadMap();
			List<ManualUploadMap> manualUploadMapList = new ArrayList<ManualUploadMap>();
			manualUploadMapIterable.forEach(manualUploadMapList::add);
			allowanceList= manualUploadMapList.stream()
									.filter(map -> map.getMapType().equalsIgnoreCase("ALLOWANCE"))
									.collect(Collectors.toList());
			deductionList= manualUploadMapList.stream()
					.filter(map -> map.getMapType().equalsIgnoreCase("DEDUCTION"))
					.collect(Collectors.toList());
			leaveList= manualUploadMapList.stream()
					.filter(map -> map.getMapType().equalsIgnoreCase("LEAVE"))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("CONFIGURATION ERROR- "+e.getLocalizedMessage());
		}
	}
	
	private String cleanCellValue(String csvLabel,UploadPayslipLine psLine) {
		String cellValue = "";
		try {

			cellValue = commonUtils.getValue(psLine, csvLabel);
//			System.out.println("csvLabel"+csvLabel+"----> cellValue"+cellValue);
//			System.out.println("cellValue"+cellValue);
			if(cellValue==null ) {
				cellValue = "0";
			}
			else {
				cellValue = cellValue.trim();
			
				if( cellValue.isEmpty() || cellValue.equals("-")) {
					cellValue = "0";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return cellValue;
	}
	
	private float currentYtd(@RequestParam(value = "empCode") String empCode,
			@RequestParam(value = "dPayTypeCode") String dPayTypeCode) {
		List<Float> ytd = new ArrayList<>();
		float sum1 = 0;
		List<EmployeePayoutLine> empPayoutLinesList = employeePayoutService.getAllEmployeePayoutLines(empCode,
				dPayTypeCode);

		for (EmployeePayoutLine employeePayoutLines2 : empPayoutLinesList) {
//			System.out.println("employeePayoutLines2"+employeePayoutLines2);
			String empPayout = employeePayoutLines2.getPeriodAmt();
			
//			System.out.println("empPayout"+empPayout);
			if(empPayout!=null) {
Float currentYtds = Float.parseFloat(empPayout);
				
				ytd.add(currentYtds);
			}
				
				
			
		}

		for (int i = 0; i < ytd.size(); i++) {
			sum1 += ytd.get(i);
			
		}
		return sum1;
	}
	private void mapAllowancesToEmployeePayout(UploadPayslipLine psLine) {
		try {
			EmployeePayoutLine employeePayoutLine = new EmployeePayoutLine();
			for (ManualUploadMap allowance : allowanceList) {
				try {
					employeePayoutLine = new EmployeePayoutLine();
					employeePayoutLine.setEmployeePayoutId(employeePayout.getId().toString());
					employeePayoutLine.setEmpCode(memployee.getEmpCode());
					employeePayoutLine.setLineOrder(++lineOrder);
					employeePayoutLine.setGrouping(allowance.getGrouping());
					employeePayoutLine.setIsAllowance("Y");
					employeePayoutLine.setsPayoutTypeId(allowance.getPayoutTypeId());
					String allowCode = cleanCellValue(allowance.getCsvLabel(),psLine);
					employeePayoutLine.setdPayTypeCode(allowCode);
					employeePayoutLine.setdPayTypeName(allowCode);
					Float periodAmt =  Float.parseFloat(cleanCellValue(allowance.getCsvPeriodValue(),psLine));
					Float arrearAmt =  Float.parseFloat(cleanCellValue(allowance.getCsvArrearValue(),psLine));
					Float lineAmt =  Float.parseFloat(cleanCellValue(allowance.getCsvValue(),psLine));
					
					float ytdAmt =  currentYtd(psLine.getEmployeeCode(),allowCode)+lineAmt;
					
					employeePayoutLine.setPeriodAmt(""+periodAmt);
					employeePayoutLine.setArrearAmt(""+arrearAmt);
					employeePayoutLine.setLineAmt(""+lineAmt);
					employeePayoutLine.setYtdAmt(""+ytdAmt);
					if(allowance.getGrouping().equalsIgnoreCase("a1")) {
						a1PeriodTotal+=periodAmt;
						a1ArrearTotal+=arrearAmt;
						a1Total += lineAmt;
						a1YtdTotal += ytdAmt;
						
					}else if(allowance.getGrouping().equalsIgnoreCase("a2")) {
						a2PeriodTotal+=periodAmt;
						a2ArrearTotal+=arrearAmt;
						a2Total += lineAmt;
//						System.out.println("lineAmt");
//						System.out.println(lineAmt);
//						System.out.println("a2Total");
//						System.out.println(a2Total);
						a2YtdTotal+= ytdAmt;
					}else {
						bPeriodTotal+=periodAmt;
						bArrearTotal+=arrearAmt;
						bTotal += lineAmt;
						bYtdTotal+= ytdAmt;
					}
						
					allowanceTotal += lineAmt;
					employeePayout.getEmployeePayoutLines().add(employeePayoutLine);
				} catch (Exception e) {
					e.printStackTrace();
					//mask the exception and move on to next loop
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("ERROR WHILE MAPPING ALLOWANCES - "+e.getLocalizedMessage());
		}
	}

	private void mapDeductionsToEmployeePayout(UploadPayslipLine psLine) {
		try {
			EmployeePayoutLine employeePayoutLine = new EmployeePayoutLine();
			for (ManualUploadMap deduction : deductionList) {
				try {
					employeePayoutLine = new EmployeePayoutLine();
					employeePayoutLine.setEmployeePayoutId(employeePayout.getId().toString());
					employeePayoutLine.setLineOrder(++lineOrder);
					employeePayoutLine.setGrouping(deduction.getGrouping());
					employeePayoutLine.setEmpCode(memployee.getEmpCode());
					employeePayoutLine.setIsAllowance("N");
					employeePayoutLine.setsPayoutTypeId(deduction.getPayoutTypeId());
					String allowCode = cleanCellValue(deduction.getCsvLabel(),psLine);
					employeePayoutLine.setdPayTypeCode(allowCode);
					employeePayoutLine.setdPayTypeName(allowCode);
					float lineAmt =  Float.parseFloat(cleanCellValue(deduction.getCsvValue(),psLine));
					float ytdAmt = currentYtd(psLine.getEmployeeCode(),allowCode) + lineAmt;
					employeePayoutLine.setLineAmt(""+lineAmt);
					employeePayoutLine.setYtdAmt(""+ytdAmt); 
					deductionTotal+= lineAmt;
//					System.out.println(deductionTotal);
					deductionYtdTotal+= ytdAmt;
					employeePayout.setTotalD1value(deductionTotal);
					employeePayout.setTotalD1Ytd(deductionYtdTotal);
					employeePayout.getEmployeePayoutLines().add(employeePayoutLine);
				} catch (Exception e) {
					e.printStackTrace();
					//mask the exception and move on to next loop
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("ERROR WHILE MAPPING DEDUCTIONS - "+e.getLocalizedMessage());
		}
	}

	private void mapLeavesToEmployeePayout(UploadPayslipLine psLine) {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		try {

			List<LeaveBalance> empLeaveBalList = new ArrayList<LeaveBalance>();
			if (memployee.getId() != null) {
				empLeaveBalList = leaveBalanceRepository.findByEmployeeId(memployee.getId().toString());
			}
			 System.out.println(empLeaveBalList);
			if (empLeaveBalList != null && !empLeaveBalList.isEmpty()) {
				List<EmployeePayoutLeaveBalance> employeePayoutLeaveBalances = new ArrayList<EmployeePayoutLeaveBalance>();
				for (LeaveBalance leaveBalance : empLeaveBalList) {

					try {
						EmployeePayoutLeaveBalance employeePayoutLeaveBalance = new EmployeePayoutLeaveBalance();
						employeePayoutLeaveBalance.setmEmpPayoutId(employeePayout.getId().toString());
						employeePayoutLeaveBalance.setEmployeeId(employeePayout.getEmployeeId());
						employeePayoutLeaveBalance.setLeaveType((leaveBalance.getLeaveTypeName()));

						 System.out.println("Leave Balance");
						 System.out.println(leaveBalance.getLeaveBalance()+"-"+leaveBalance.getCarriedOver()+"-"+leaveBalance.getAvailedCount());

						Integer openingCount = transform(leaveBalance.getLeaveBalance());
						Integer accuredCount = transform(leaveBalance.getCarriedOver());
						Integer availedCount = transform(leaveBalance.getCarriedOver());
						Integer balanceCount = transform(leaveBalance.getAvailedCount());

						employeePayoutLeaveBalance.setOpeningBalance("" + openingCount);
						employeePayoutLeaveBalance.setAccured("" + accuredCount);
						employeePayoutLeaveBalance.setAvailed("" + availedCount);
						employeePayoutLeaveBalance.setBalance("" + balanceCount);
						employeePayoutLeaveBalance.setIsEnabled("Y");

						// if(commonUtils.getLoggedinEmployeeId()!=null ||
						// !commonUtils.getLoggedinEmployeeId().equals("")) {
						// employeePayoutLeaveBalance.setCreatedBy(commonUtils.getLoggedinEmployeeId());
						// }
						//
						// System.out.println("employeePayoutLeaveBalance");
						// System.out.println(employeePayoutLeaveBalance);

						leaveOpeningTotal += openingCount;
						leaveAccuredTotal += accuredCount;
						leaveAvailedTotal += availedCount;
						leaveBalanceTotal += balanceCount;
						employeePayoutLeaveBalances.add(employeePayoutLeaveBalance);
						// System.out.println("employeePayoutLeaveBalances");
						// System.out.println(employeePayoutLeaveBalances);
						employeePayoutLeaveBalanceRepository.save(employeePayoutLeaveBalance);
						employeePayout.setEmployeePayoutLeaveBalances(employeePayoutLeaveBalances);
					} catch (Exception e) {
						e.printStackTrace();
						// mask the exception and move on to next loop
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
//			triggerFailedSalaryProcessedEvent(loggedInUser, psLine);
			throw new SmartOfficeException("ERROR WHILE MAPPING LEAVES - " + e.getLocalizedMessage());
		}
	}

	private Integer transform(String input) {
		Integer output=0;
		if(input==null || input.isEmpty() || input.contains("")) {
			output=0;
		}else {
			output=Integer.parseInt(input);
		}
		return output;
	}
	

	private void mapSummariesToPayoutHeader() {
		try {

			employeePayout.setTotalEarnings(allowanceTotal + "");
			employeePayout.setTotalAllowanceAmt(allowanceTotal + "");
			employeePayout.setGrossSalary(allowanceTotal + "");

			// a1-summary
			employeePayout.setTotalA1Value(a1PeriodTotal);
			employeePayout.setTotalA1ArrearAmt(a1ArrearTotal);
			employeePayout.setTotalA1CurrentPeriod(a1Total);
			employeePayout.setTotalA1Ytd(a1YtdTotal);

			// a2-summary
			employeePayout.setTotalA2Value(a2PeriodTotal);
			employeePayout.setTotalA2ArrearAmt(a2ArrearTotal);
			employeePayout.setTotalA2CurrentPeriod(a2Total);
			employeePayout.setTotalA2Ytd(a2YtdTotal);

			// b-summary
			employeePayout.setTotalBValue(bPeriodTotal);
			employeePayout.setTotalBArrearAmt(bArrearTotal);
			employeePayout.setTotalBCurrentPeriod(bTotal);
			employeePayout.setTotalBYtd(bYtdTotal);
			employeePayout.setTotalVariablePay(bTotal+"");
			
			// deduction-summary
			

			employeePayout.setTotalArrearAmt(a1ArrearTotal + a2ArrearTotal + bArrearTotal);
			employeePayout.setTotalCurrentPeriod(a1Total + a2Total + bTotal);
			employeePayout.setTotalYtd(a1YtdTotal + a2YtdTotal + bYtdTotal);

			//leave-summary
			employeePayout.setTotalLeaveOpening(leaveOpeningTotal);
			employeePayout.setTotalAccured(leaveAccuredTotal);
			employeePayout.setTotalAvailed(leaveAvailedTotal);
			employeePayout.setTotalBalance(leaveBalanceTotal);
			
			float netPayAmount = allowanceTotal - deductionTotal;
			employeePayout.setNetPayAmt(""+netPayAmount);
			//TODO - this has to be fixed to handle long
			float netPayAmountInt = Float.parseFloat(netPayAmount+"");
			employeePayout.setNetPayInWords(numberToWords.convert((int)netPayAmountInt)+" Rupees "+ "Only");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("ERROR MAPPING SUMMARIES- "+e.getLocalizedMessage());
		}
		
	}
	

}

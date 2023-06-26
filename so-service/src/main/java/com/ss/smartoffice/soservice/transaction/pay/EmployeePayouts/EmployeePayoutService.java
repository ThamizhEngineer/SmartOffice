package com.ss.smartoffice.soservice.transaction.pay.EmployeePayouts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.model.EmployeePayoutLeaveBalance;
import com.ss.smartoffice.shared.model.EmployeePayoutLine;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.shared.pay.EmployeePayoutLeaveBalanceRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutLinesRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.NotificationKey;

@RestController
@RequestMapping("transaction/pay/memployee-payouts")
@Scope("prototype")
public class EmployeePayoutService {
	@Autowired
	EmployeePayoutRepository employeePayoutRepository;

	@Autowired
	EmployeePayoutLinesRepository employeePayoutLinesRepository;
	@Autowired
	EmployeePayoutLeaveBalanceRepository employeePayoutLeaveBalanceRepository;

	@Autowired
	EventPublisherService eventPublisherService;

	@Autowired
	CommonUtils commonUtils;

	// @CrossOrigin(origins = "*")
	@GetMapping
	public List<EmployeePayout> getAllEmployeePayoutSummaries(
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "salaryForMonth", required = false) String dSalaryForMonth,
			@RequestParam(value = "salaryForYear", required = false) String dSalaryForYear)
			throws SmartOfficeException {

		employeeId = (employeeId == null) ? "" : employeeId;
		dSalaryForMonth = (dSalaryForMonth == null) ? "" : dSalaryForMonth;
		dSalaryForYear = (dSalaryForYear == null) ? "" : dSalaryForYear;
//		System.out.println("employeeId-"+employeeId+",dSalaryForMonth-"+dSalaryForMonth+",dSalaryForYear-"+dSalaryForYear);
		if (dSalaryForMonth.startsWith("0") && dSalaryForMonth.length() == 2) {
			dSalaryForMonth = dSalaryForMonth.substring(1);
		}
		if (employeeId.isEmpty()) {
			return employeePayoutRepository.findByDSalaryForMonthAndDSalaryForYear(dSalaryForMonth, dSalaryForYear);
		} else {
			if (!(dSalaryForMonth.isEmpty() || dSalaryForYear.isEmpty())) {
				return employeePayoutRepository.findByEmployeeIdAndDSalaryForMonthAndDSalaryForYear(employeeId,
						dSalaryForMonth, dSalaryForYear);
			} else {
				return employeePayoutRepository.findByEmployeeId(employeeId);
			}
		}

	}

	// @CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<EmployeePayout> getEmployeePayoutById(@PathVariable(value = "id") int id)
			throws SmartOfficeException {
		return employeePayoutRepository.findById(id);

	}

	// @CrossOrigin(origins = "*")
	@GetMapping("/_internal/{id}")
	public Optional<EmployeePayout> getEmployeePayoutByIdInternal(@PathVariable(value = "id") int id)
			throws SmartOfficeException {
		return employeePayoutRepository.findById(id);

	}

	@GetMapping("/pay")
	public List<EmployeePayoutLine> getAllEmployeePayoutLines(@RequestParam(value = "empCode") String empCode,
			@RequestParam(value = "dPayTypeCode") String dPayTypeCode) {
		return employeePayoutLinesRepository.findByEmpCodeAndDPayTypeCode(empCode, dPayTypeCode);
	}

	// @CrossOrigin(origins = "*")
	@PostMapping
	public EmployeePayout addEmployeePayout(@RequestBody EmployeePayout employeePayout) throws SmartOfficeException {
		return employeePayoutRepository.save(employeePayout);

	}

	// @CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public EmployeePayout updateEmployeePayout(@RequestBody EmployeePayout employeePayout) throws SmartOfficeException {
		return employeePayoutRepository.save(employeePayout);

	}

	// @CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteEmployeePayoutById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		employeePayoutRepository.deleteById(id);
	}

	public void deleteEmployeePayoutLineById(@PathVariable(value = "id") int id) throws SmartOfficeException {
		employeePayoutLinesRepository.deleteById(id);
	}

	// @CrossOrigin(origins = "*")
	@PostMapping("/{id}/lines")
	public EmployeePayout addOrUpdateEmployeePayoutLines(@RequestBody EmployeePayout employeePayout)
			throws SmartOfficeException {
		if (employeePayout.getEmployeePayoutLines() != null) {
			for (EmployeePayoutLine empPayLoop : employeePayout.getEmployeePayoutLines()) {
				employeePayoutLinesRepository.save(empPayLoop);
			}

		} else {
			throw new SmartOfficeException("EmployeePayoutLine Is Empty");
		}
		return employeePayout;
	}

	// @CrossOrigin(origins = "*")
	@PostMapping("/{id}/leaves")
	public EmployeePayout addOrUpdateLeaveBalances(@RequestBody EmployeePayout employeePayout)
			throws SmartOfficeException {
		if (employeePayout.getEmployeePayoutLeaveBalances() != null) {
			for (EmployeePayoutLeaveBalance leaveBalance : employeePayout.getEmployeePayoutLeaveBalances()) {
				employeePayoutLeaveBalanceRepository.save(leaveBalance);
			}

		} else {
			throw new SmartOfficeException("Leave Balance Is Empty");
		}
		return employeePayout;
	}

	// @CrossOrigin(origins = "*")
	@PatchMapping("/{id}/send-email")
	public String sendEmail(@PathVariable(value = "id") int id, @RequestBody EmployeePayout employeePayout) {
		System.out.println("kh-mail in send-email");
		Event event = new Event();
		event.setName(Event.EventTypes.EmployeeSalaryCreatedEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setEmployeeId(employeePayout.getEmployeeId());
		businessKey.setEmpPayoutId(Integer.toString(id));
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		System.out.println("kh-mail2 - " + event);
		eventPublisherService.pushEvent(event);

		return "success";
	}

	// @CrossOrigin(origins = "*")
	@PostMapping("/{id}/process")

	public void processEmpPayout() throws SmartOfficeException {
		employeePayoutRepository.processEmpPayout("1", "1");
	}

	public boolean doesPayoutExist(String employeeCode, String payMonth, String payYear) throws SmartOfficeException {
		boolean result = false;
		try {
			if (employeePayoutRepository
					.findByEmployeeCodeAndDSalaryForMonthAndDSalaryForYear(employeeCode, payMonth, payYear).isEmpty()) {
				result = false;
			} else {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@CrossOrigin(origins = "*")
	@PatchMapping("/update/_internal/")
	public EmployeePayout updateEmployeePayoutInternal(@RequestBody EmployeePayout employeePayout)
			throws SmartOfficeException {
		return updateEmployeePayout(employeePayout);

	}

	@PostMapping("update/_internal/{id}/app-pdf")
	public EmployeePayout updateEmployeePayoutByPdfId(@RequestBody EmployeePayout employeePayout)
			throws SmartOfficeException {
		EmployeePayout employeePayoutFromDb = getEmployeePayoutByIdInternal(employeePayout.getId()).get();
		employeePayoutFromDb.setPayslipDocId(employeePayout.getPayslipDocId());
		return employeePayoutRepository.save(employeePayoutFromDb);

	}
// 
//	 //@CrossOrigin(origins = "*")
//	 @GetMapping("/_internal")
//	 public Iterable<EmployeePayout> getEmployeePayoutInternal(@RequestParam(value = "employeeId", required = false) String employeeId,
//				@RequestParam(value = "payDt", required = false) String payDtString){
//				return getEmployeePayout(employeeId, payDtString);
//	 }
//	 

}

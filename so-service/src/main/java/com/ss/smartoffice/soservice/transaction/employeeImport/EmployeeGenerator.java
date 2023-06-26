package com.ss.smartoffice.soservice.transaction.employeeImport;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.BankDetails;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.employee.EmployeeProfileEventGenerator;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.invoice.InvoiceGenerator;

@Service
public class EmployeeGenerator {
	private static Logger log = LoggerFactory.getLogger(InvoiceGenerator.class);
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	IntEmployeeLineRepo intEmployeeLineRepo;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeProfileEventGenerator employeeProfileEventGenerator;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Async("asyncThreadPoolTaskExecutor")
	public void start(String iEmployeeHdrId,AuthUser loggedInUser) {
		log.info("Employee generation sarted for: " + iEmployeeHdrId);
		List<IntEmployeeLine> employeeLines = intEmployeeLineRepo.findByEmployeeHdrIdAndIsValid(iEmployeeHdrId, "Y");
		System.out.println("Employee List: " + employeeLines);
		for(IntEmployeeLine empLine:employeeLines) {
			formEmployee(empLine,loggedInUser);
		}
	}
	
	private memployee formEmployee(IntEmployeeLine e,AuthUser loggedInUser) {
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		memployee emp = new memployee();
		emp.setEmpCode(e.getEmpCode());
		emp.setFirstName(e.getFirstName());
		emp.setLastName(e.getLastName());
		emp.setEmpName(e.getFirstName()+" "+e.getLastName());
		emp.setEmpStatus(e.getStatus());
		emp.setContactMobileNo(e.getMobileNo());
		emp.setDob(e.getDob());
		emp.setDoj(e.getDoj());
		emp.setContactEmailId(e.getEmail());
		emp.setEmailId(e.getEmail());
		emp.setGender(e.getSex().toLowerCase());
		emp.setDesignationId(Integer.parseInt(e.getDesignationId()));
		emp.setHr1UsrGrpId(e.getHr1GrpId());
		emp.setHr2UsrGrpId(e.getHr2GrpId());
		emp.setAcc1UsrGrpId(e.getAcc1GrpId());
		emp.setAcc2UsrGrpId(e.getAcc2GrpId());
		emp.setDirUsrGrpId(e.getDirGrpId());
		emp.setIsSystemUser("N");
		emp.setPfNo(e.getPf());
		emp.setEsiNo(e.getEsi());
		emp.setUanNo(e.getUan());
		emp.setOfficeId(Integer.parseInt(e.getOfficeId()));
		emp.setDeptId(Integer.parseInt(e.getDepartmentId()));
		emp.setEmpCategory(e.getEmployeeCategory());
		emp.setDesigName(e.getDesignation());
		emp.setShiftId(e.getShiftId());
		emp.setEmpTypeCode("REGULAR");
		BankDetails bankdetail = new BankDetails();
		bankdetail.setBankName(e.getBankName());
		bankdetail.setAccountName(e.getBankAccName());
		bankdetail.setAccNo(e.getAccNumber());
		bankdetail.setIfscCode(e.getIfscCode());
		emp.setBankDetails(Arrays.asList(bankdetail));
		HashMap<String, String> buisnessKeys = new HashMap<>();
		emp.setInternalId(sequenceGenerationService.nextSeq("INTERNAL-ID", buisnessKeys));
		List<memployee> n1Emp = employeeRepository.findByEmpCode(e.getN1EmpCode());
		List<memployee> n2Emp = employeeRepository.findByEmpCode(e.getN2EmpCode());
		if(n1Emp.size()!=0) {
			emp.setN1EmpId(n1Emp.get(0).getId().toString());
		}
		if(n2Emp.size()!=0) {
			emp.setN2EmpId(n2Emp.get(0).getId().toString());
		}
		emp=employeeRepository.save(emp);		
		employeeProfileEventGenerator.triggerEmpProfileEvents(emp, "onboard", loggedInUser);
		try {
			createCtcForEmployee(emp.getId().toString());
		}catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}		
		return emp;
	}
	
	@Async("asyncThreadPoolTaskExecutor")
	public void createCtcForEmployee(String employeeId)throws SmartOfficeException{
		System.out.println("Hit Procedure");
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("basic_ctc_structure");
		
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
		
		query.setParameter(1, employeeId);
		query.execute();
		    
		query.getOutputParameterValue(2);
		query.getOutputParameterValue(3);
		
		query.getOutputParameterValue(2);
	}
	
}

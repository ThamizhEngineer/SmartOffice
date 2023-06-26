package com.ss.smartoffice.soservice.transaction.invoice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;

@Service
public class InvoiceHelperService {

	@Autowired
	InvoiceHdrRepo invoiceHdrRepo;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	InvoiceHdrRepo hdrRepo;
	
	@Autowired
	InvoiceLineRepo lineRepo;
	
	@Autowired
	InvoiceGenerator invoiceGnerator;
	
	@Autowired
	InvoiceService invoiceService;	
	
	@Autowired
	InvoiceEventGenerator invoiceEventGenerator;
	
public InvoiceHdr processInvoice(String action,InvoiceHdr invoiceHdr) throws SmartOfficeException{
	AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
	InvoiceHdr invoiceHdrFromDb = new InvoiceHdr();
	if (!(action.equals("create"))) {
		invoiceHdrFromDb=invoiceService.fetchById(invoiceHdr.getId());
	}	
		switch (action) {
		case "create":
			InvoiceCreate(invoiceHdr);
			break;
		case "submit":
			invoiceHdrFromDb = InvoiceSubmit(invoiceHdrFromDb);
			invoiceHdrFromDb.setInvoiceStatus("DIR-APPROVAL-PENDING");
			hdrRepo.save(invoiceHdrFromDb);
			invoiceEventGenerator.InvoiceSubmitEvent(invoiceHdrFromDb, loggedInUser);
			break;	
		case "dir-approve":
			invoiceHdrFromDb.setApprovalComments(invoiceHdr.getApprovalComments());			
			invoiceHdrFromDb = InvoiceApprove(invoiceHdrFromDb);
			invoiceHdrFromDb.setInvoiceStatus("APPROVED");
			hdrRepo.save(invoiceHdrFromDb);
			invoiceEventGenerator.InvoiceApproveEvent(invoiceHdrFromDb, loggedInUser);
			break;			
		case "void":
			invoiceHdrFromDb = InvoiceVoid(invoiceHdrFromDb);
			invoiceHdrFromDb.setInvoiceStatus("VOID");
			hdrRepo.save(invoiceHdrFromDb);
			invoiceEventGenerator.InvoiceVoidEvent(invoiceHdrFromDb, loggedInUser);
			break;

		default:
			break;
		}		
		return invoiceHdr;		
	}

	public InvoiceHdr InvoiceCreate(InvoiceHdr invoiceHdr)throws SmartOfficeException{
	try {
		memployee employee = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		invoiceHdr.setInvoiceCreatedBy(commonUtils.getLoggedinEmployeeId());
		invoiceHdr.setInvoiceCreatedDt(LocalDateTime.now());
		invoiceHdr.setInvoiceCreatedByEmpName(employee.getEmpName());
		invoiceHdr.setDirGroupId(employee.getDirUsrGrpId());
		invoiceHdr.setDirGroupName(employee.getDirUsrGrpName());
		invoiceHdr.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		invoiceHdr.setCreatedDate(LocalDateTime.now());
		hdrRepo.save(invoiceHdr);		
		for(InvoiceLine line:invoiceHdr.getInvoiceLines()) {
			line.setInvoiceHdrId(invoiceHdr.getId().toString());
			invoiceGnerator.calculatingLine(line);
			lineRepo.save(line);
		}
		invoiceGnerator.calculatingHdr(invoiceHdr, invoiceHdr.getInvoiceLines());
		invoiceHdr.setInvoiceStatus("CREATED");
		hdrRepo.save(invoiceHdr);
	}catch (Exception e){
		e.printStackTrace();
	}
	return invoiceHdr;	
	}
	
	public InvoiceHdr InvoiceSubmit(InvoiceHdr invoiceHdr)throws SmartOfficeException{
		invoiceHdr.setModifiedBy(commonUtils.getLoggedinEmployeeId());
		invoiceHdr.setModifiedDate(LocalDateTime.now());
		invoiceHdr.setInvoiceStatus("SUBMITTED");		
		return invoiceHdr;
	}

	public InvoiceHdr InvoiceApprove(InvoiceHdr invoiceHdr)throws SmartOfficeException{
		memployee employee = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		invoiceHdr.setApprovedBy(commonUtils.getLoggedinEmployeeId());
		invoiceHdr.setApprovedDt(LocalDateTime.now());		
		invoiceHdr.setApprovedByEmpName(employee.getEmpName());
		invoiceHdr.setInvoiceStatus("DIR-APPROVED");
		return invoiceHdr;
	}
	
	public InvoiceHdr InvoiceVoid(InvoiceHdr invoiceHdr)throws SmartOfficeException{
		memployee employee = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		invoiceHdr.setVoidedBy(commonUtils.getLoggedinEmployeeId());
		invoiceHdr.setVoidedDt(LocalDateTime.now());
		invoiceHdr.setVoidedByEmpName(employee.getEmpName());
		invoiceHdr.setInvoiceStatus("VOID");
		return invoiceHdr;
	}
}

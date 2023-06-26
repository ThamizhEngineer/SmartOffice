package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.EmployeePayout;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.pay.EmployeePayoutLeaveBalanceRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutLinesRepository;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.employee.EmployeeService;
import com.ss.smartoffice.soservice.master.pay.CompanyPayCycle.CompanyPayCycleLinesRepository;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipHdr;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
import com.ss.smartoffice.soservice.transaction.pay.EmployeePayouts.EmployeePayoutService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@RestController
@RequestMapping("transaction/upload-payslip")
public class UploadPayslipHdrService {
	@Value( "${doc-upload.final.location}" )
	String docFinalLocation;
	

//	@Autowired
//	Job job;

	@Autowired
	UploadPayslipHdrRepository uploadPayslipHdrRepository;
	
	@Autowired
	EventPublisherService eventPublisherService;

	@Autowired
	EmployeePayout employeePayout;

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
	EmployeePayoutLinesRepository employeePayoutLinesRepository;
	@Autowired
	EmployeePayoutLeaveBalanceRepository employeePayoutLeaveBalanceRepository;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	

	@Autowired
	UploadPayslipLineRepository uploadPaySlipLineRepository;

	//@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<UploadPayslipHdr> getUploadPayslipHdr() throws SmartOfficeException {

		return uploadPayslipHdrRepository.findAll();

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Optional<UploadPayslipHdr> getUploadPayslipHdrById(@PathVariable(value = "id") int id)
			throws SmartOfficeException {

		return uploadPayslipHdrRepository.findById(id);

	}

	//@CrossOrigin(origins = "*")
	@GetMapping("/{id}/lines")
	public Optional<UploadPayslipLine> getUploadPaySlipLineById(@PathVariable(value = "id") int id)
			throws SmartOfficeException {
		return uploadPaySlipLineRepository.findById(id);

	}
	//@CrossOrigin(origins = "*")
	@GetMapping("/lines")
	public List<UploadPayslipLine> getUploadPaySlipLineById()
			throws SmartOfficeException {
		return uploadPaySlipLineRepository.findAll();

	}
public void dltUpdatePayslipLine(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	uploadPaySlipLineRepository.deleteById(id);
}
public void dltUpdatePayslipHdr(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	uploadPayslipHdrRepository.deleteById(id);
}
	//@CrossOrigin(origins = "*")
	@PostMapping
	public UploadPayslipHdr addUploadPayslipHdr(@RequestBody UploadPayslipHdr uploadPayslipHdr)
			throws SmartOfficeException {
		try {
			System.out.println("kh - uphdr - addUploadPayslipHdr");
			AuthUser authUser = commonUtils.getLoggedInUser();
			DocInfo docInfo = new DocInfo();
			docInfo.setDocId(uploadPayslipHdr.getDoc_id());
			List<DocInfo> docInfos = new ArrayList<DocInfo>();
			docInfos.add(docInfo);
			documentManagementHelper.checkInDocs(docInfos);
			uploadPayslipHdr.setCreatedBy(commonUtils.getAuthenticatedUser().getName());
			uploadPayslipHdr = uploadPayslipHdrRepository.save(uploadPayslipHdr);
//			String appToken =commonUtils.getLoggedinAppToken();
			//fetching primary-key and set the same as upload-code
			uploadPayslipHdr.setProcess_status("PROCESSING");
			uploadPayslipHdr.setUpload_code(uploadPayslipHdr.getId().toString());
			System.out.println("kh uphdr - ckh - heck uploadPayslipHdr");
			System.out.println(uploadPayslipHdr);
			uploadPayslipHdrRepository.save(uploadPayslipHdr);
			
			//generate a event for processing asynchronously
			Event event = new Event();
			
			event.setName(Event.EventTypes.ManualPaySlipProcessEvent);
			event.setCategory(Event.EventCategory.BuisnessEvent);

			List<BusinessKey>businessKeys= new ArrayList<BusinessKey>();
			BusinessKey businessKey= new BusinessKey();
			businessKey.setKey1(uploadPayslipHdr.getDoc_id());
			businessKey.setUploadPayslipHdrId(uploadPayslipHdr.getId().toString());
//			businessKey.setDocId(uploadPayslipHdr.getDoc_id().toString());
			event.setContextAuthUserId(authUser.getId());
			System.out.println("kh uphdr -  - event"+event);

			businessKeys.add(businessKey);
			event.setBusinessKeys(businessKeys);
			eventPublisherService.pushEvent(event);

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

		return uploadPayslipHdr;
	}

	//@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public UploadPayslipHdr updateUploadPayslipHdr(@RequestBody UploadPayslipHdr uploadPayslipHdr) {
		System.out.println("Hi in process");
	
		
		
		
		uploadPayslipHdr = uploadPayslipHdrRepository.save(uploadPayslipHdr);
		return uploadPayslipHdr;
	}
	@Transactional
public void deleteUploadPayslipLine(@PathVariable(value="id")Integer id)throws SmartOfficeException{
	uploadPaySlipLineRepository.delete(id);
}
	/*load()
	 * This will load the upload-salary-line table with data from csvFile
	 * Spring batch is used for the same.
	 * i/p - fileName
	 */
	

/*
 * updates the upload-salary-line with headerId 
 */
	public void updateOrphanLines(Integer uploadPayslipHdrId) {
		List<UploadPayslipLine> orphanedLines = uploadPaySlipLineRepository.findByUploadPayslipHdrId(0);
		for (UploadPayslipLine uploadPayslipLine : orphanedLines) {
			uploadPayslipLine.setUploadPayslipHdrId(uploadPayslipHdrId);
			uploadPaySlipLineRepository.save(uploadPayslipLine);
		}
	}


}

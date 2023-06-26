package com.ss.smartoffice.soservice.transaction.uploadpayslip;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.model.EmployeePayoutLine;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.bkprocess.BkProcess;
import com.ss.smartoffice.shared.model.bkprocess.BkProcessRepo;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.pay.EmployeePayoutRepository;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.transaction.bkProcess.BkProcessService;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipHdr;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
import com.ss.smartoffice.soservice.transaction.pay.EmployeePayouts.EmployeePayoutService;

@RestController
@RequestMapping("csv/payslips")
public class CsvMapperPayslipUploadService {

	@Autowired
	DocumentManagementHelper documentManagementHelper;

	@Autowired
	UploadPayslipHdrService uploadPayslipHdrService;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	UploadPayslipHdrRepository uploadPayslipHdrRepository;

	@Autowired
	UploadPayslipLineRepository uploadPayslipLineRepository;

	@Autowired
	UploadPaySlipLineProcessor uploadPaySlipLineProcessor;
	@Autowired
	EmployeePayoutService empPayoutService;
	
	@Autowired
	EmployeePayoutRepository employeePayoutRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	SequenceGenerationService sequenceGenerationService;
	
	@Autowired 
	DocInfoRepository docInfoRepository;
	
	@Autowired
	BkProcessRepo bkProcessRepo; 
	
	@Autowired
	@Lazy
	BkProcessService bkProcessService;
	
	@GetMapping("/get-all")
	public Iterable<UploadPayslipHdr> getAll()throws SmartOfficeException{
		return uploadPayslipHdrRepository.findAll();
	}
	
	@GetMapping("/get-filter")
	public List<UploadPayslipHdr>getFilter(@RequestParam("payMonth")String payMonth,@RequestParam("payYear")String payYear,@RequestParam("isOverWriteFlag")String isOverWriteFlag)throws SmartOfficeException{
		return uploadPayslipHdrRepository.findByPayMonthAndPayYearAndIsOverwriteFlag(Integer.parseInt(payMonth), payYear, isOverWriteFlag);
	}
	@GetMapping("/id/{id}")
	public Optional<UploadPayslipHdr> getAll(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return uploadPayslipHdrRepository.findById(id);
	}

	@Async("asyncThreadPoolTaskExecutor")
	public void triggerPayslipUpload(@RequestParam("docId") String docId, @RequestParam("payMonth") String payMonth,
			@RequestParam("payYear") String payYear, @RequestParam("bkProcessId") Integer bkProcessId,
			AuthUser loggedInUser) throws SmartOfficeException {
//		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		UploadPayslipHdr uploadPayslipHdr = new UploadPayslipHdr();
		UploadPayslipHdr savedHdr = new UploadPayslipHdr();
//		List<DocInfo> docInfo = documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		List<PayslipUploadLine> payslipLineList = new ArrayList<PayslipUploadLine>();

		System.out.println(docId);
		System.out.println(docInfoRepository.findByDocId(docId));
		DocInfo d = docInfoRepository.findByDocId(docId).get(0);
		System.out.println(d.getDocId());
		if (d.getDocId() != null && !d.getDocId().isEmpty()) {
			try {

				HashMap<String, String> buisnessKeys = new HashMap<>();
				String path = d.getDocLocation() + "/" + d.getDocName();
				System.out.println(path);
				payslipLineList = trigger(path);
				uploadPayslipHdr.setDoc_id(d.getDocId());
				uploadPayslipHdr.setPayMonth(Integer.parseInt(payMonth));
				uploadPayslipHdr.setUpload_code(sequenceGenerationService.nextSeq("UPLOAD-PAYSLIP", buisnessKeys));
				uploadPayslipHdr.setPayYear(payYear);
				uploadPayslipHdr.setIsOverwriteFlag("N");
				uploadPayslipHdr.setProcess_status("PROCESSING");

				uploadPayslipHdr.setProcessId(bkProcessId.toString());
				savedHdr = uploadPayslipHdrRepository.save(uploadPayslipHdr);

//					to Save pay_roll_hdr Id

				bkProcessService.startProgress(bkProcessId, savedHdr.getId().toString());

				savedHdr = moveCsvDatatoTempPayslipLineTable(payslipLineList, savedHdr, d.getDocId(), loggedInUser,
						payMonth, payYear, null, bkProcessId);
			} catch (IOException e) {
				e.printStackTrace();
				bkProcessService.error(bkProcessId, e.getLocalizedMessage());
			}

		}
//		return savedHdr;
	}

	@PatchMapping("/update-payslip/{docTypeCode}/{hdrId}/{id}")
	public List<PayslipUploadLine> updatePayslipUpload(@PathVariable(value = "docTypeCode") String docTypeCode,
			@RequestParam("uploadingFiles") MultipartFile[] filesToUpload, @PathVariable(value = "id") Integer id,@PathVariable(value = "hdrId") Integer hdrId,
			@RequestParam("payMonth") String payMonth, @RequestParam("payYear") String payYear)
			throws SmartOfficeException {
		
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		UploadPayslipHdr uploadPayslipHdr = uploadPayslipHdrRepository.findById(hdrId).get();
		UploadPayslipLine dltUploadPayslipLine=uploadPayslipLineRepository.findById(id).get();
		dltExistingRecords(dltUploadPayslipLine);
		UploadPayslipHdr savedHdr =new UploadPayslipHdr();
		List<DocInfo> docInfo = documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		List<PayslipUploadLine> payslipLineList = new ArrayList<PayslipUploadLine>();
		DocInfo d = docInfo.get(0);
		System.out.println(d.getDocId());
		if (d.getDocId() != null && !d.getDocId().isEmpty()) {
			try {
				String path = d.getDocLocation() + "/" + d.getDocName();
				System.out.println(path);
				payslipLineList = trigger(path);
				uploadPayslipHdr.setDoc_id(d.getDocId());
				uploadPayslipHdr.setProcess_status("COMPLETED");
				uploadPayslipHdr.setPayMonth(Integer.parseInt(payMonth));
				uploadPayslipHdr.setPayYear(payYear);
				uploadPayslipHdr.setIsOverwriteFlag("Y");
				 savedHdr = uploadPayslipHdrRepository.save(uploadPayslipHdr);
				
			
			
				savedHdr= moveCsvDatatoTempPayslipLineTable(payslipLineList, savedHdr, d.getDocId(),
						loggedInUser, payMonth, payYear,dltUploadPayslipLine,0);
				
//				todo -->> have to change this to from bk process
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return payslipLineList;
	}
	@Transactional
	@Async("asyncThreadPoolTaskExecutor")
	
	private void dltExistingRecords( UploadPayslipLine dltUploadPayslipLine) {
		// TODO Auto-generated method stub
		List<EmployeePayout> employeePayouts=employeePayoutRepository.findByEmployeeCodeAndDSalaryForMonthAndDSalaryForYear(dltUploadPayslipLine.getEmployeeCode(), dltUploadPayslipLine.getSalaryMonth(),dltUploadPayslipLine.getSalaryYear());

	
		for(EmployeePayout employeePayout:employeePayouts) {
			for(EmployeePayoutLine employeePayoutLine:employeePayout.getEmployeePayoutLines()) {
				empPayoutService.deleteEmployeePayoutLineById(employeePayoutLine.getId());
			}
			empPayoutService.deleteEmployeePayoutById(employeePayout.getId());
			
		}
	
		
	}

	@GetMapping
	public List<PayslipUploadLine> trigger(String path) throws IOException {
		File input = new File(path);
		System.out.println(path + "path");
		List<PayslipUploadLine> data = readLineObjectsFromCsv(input);

		return data;
	}
	
	public List<PayslipUploadLine> readLineObjectsFromCsv(File file) throws IOException {
	
		
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema bootstrap = csvMapper.schemaFor(PayslipUploadLine.class).withHeader();
		MappingIterator<PayslipUploadLine> mappingIterator = csvMapper.readerFor(PayslipUploadLine.class)
				.with(bootstrap).readValues(file);
		
	
		return mappingIterator.readAll();

	}

	@Async("asyncThreadPoolTaskExecutor")
	public UploadPayslipHdr moveCsvDatatoTempPayslipLineTable(List<PayslipUploadLine> data,
			UploadPayslipHdr savedHdr, String docId, AuthUser loggedInUser, String payMonth, String payYear,UploadPayslipLine updatePayslip,Integer bkProcessId)
			throws SmartOfficeException {
		commonUtils.setAuthenticationContext(loggedInUser,"async");		
		System.out.println(data);
		int csvRecordCount = data.size();
		int employeeCount=employeeRepository.countAll();
		int total=employeeCount-csvRecordCount;
		
		String message = csvRecordCount+"Pay Slip yet To Process";
		
		bkProcessService.updateProgress(bkProcessId,message);
		
		if(total!=0) {
			System.out.println(total);
			uploadPaySlipLineProcessor.triggerFailedSalaryProcessedEvent(loggedInUser,savedHdr, null, null,total);
		}
		if(updatePayslip==null) {
		for (PayslipUploadLine payslipUploadLine : data) {

			UploadPayslipLine uploadPayslipLine = new UploadPayslipLine();
			if (payslipUploadLine.getSalaryMonth() != null && payslipUploadLine.getSalaryYear() != null) {
				if (payslipUploadLine.getSalaryMonth().equals(payMonth)
						&& payslipUploadLine.getSalaryYear().equals(payYear)) {
					formUploadPayslipLine(payslipUploadLine, savedHdr, uploadPayslipLine);
				
					
					uploadPaySlipLineProcessor.process(uploadPayslipLine);					
					bkProcessService.completed(bkProcessId,csvRecordCount+"PaySlip were analysed");
				
				} else {
					uploadPayslipHdrRepository.deleteById(savedHdr.getId());
					String message2 = "For only selected Salary Month and salary year payslip can be processed";
					uploadPayslipLine.setRemarks(message2);
					uploadPayslipLine.setEmployeeName(payslipUploadLine.getEmployeeName());
//					bkProcess.setLastMessageUpdated(message2);
					bkProcessService.error(bkProcessId,message2);
					throw new SmartOfficeException(message);
				}
			}
		}		
		}else {
		uploadPaySlipLineProcessor.process(updatePayslip);		
		bkProcessService.completed(bkProcessId,csvRecordCount+"PaySlip were analysed");
		}
		return savedHdr;

	}

	public UploadPayslipLine formUploadPayslipLine(PayslipUploadLine payslipUploadLine, UploadPayslipHdr savedHdr,
			UploadPayslipLine uploadPayslipLine) throws SmartOfficeException {
		int successCount = 0;
	
		uploadPayslipLine.setId(payslipUploadLine.getId());
		uploadPayslipLine.setEmployeeCode(payslipUploadLine.getEmployeeCode());
		uploadPayslipLine.setEmployeeName(payslipUploadLine.getEmployeeName());
		uploadPayslipLine.setEmail(payslipUploadLine.getEmail());
		uploadPayslipLine.setMobileNo(payslipUploadLine.getMobileNo());
		uploadPayslipLine.setDob(payslipUploadLine.getDob());
		uploadPayslipLine.setDoj(payslipUploadLine.getDoj());
		uploadPayslipLine.setDepartment(payslipUploadLine.getDepartment());
		uploadPayslipLine.setDesignation(payslipUploadLine.getDesignation());
		uploadPayslipLine.setGrade(payslipUploadLine.getGrade());
		uploadPayslipLine.setSpecialDesignation(payslipUploadLine.getSpecialDesignation());
		uploadPayslipLine.setSkill(payslipUploadLine.getSkill());
		uploadPayslipLine.setBusiness(payslipUploadLine.getBusiness());
		uploadPayslipLine.setNatureOfPosition(payslipUploadLine.getNatureOfPosition());
		uploadPayslipLine.setSalaryMonth(payslipUploadLine.getSalaryMonth());
		uploadPayslipLine.setSalaryYear(payslipUploadLine.getSalaryYear());
		uploadPayslipLine.setLocationOfPosition(payslipUploadLine.getLocationOfPosition());
		uploadPayslipLine.setPfNo(payslipUploadLine.getPfNo());
		uploadPayslipLine.setEpsNo(payslipUploadLine.getEpsNo());
		uploadPayslipLine.setEsiNo(payslipUploadLine.getEsiNo());
		uploadPayslipLine.setBankAccountNumber(payslipUploadLine.getBankAccountNumber());
		uploadPayslipLine.setBank(payslipUploadLine.getBank());
		uploadPayslipLine.setIfscCode(payslipUploadLine.getIfscCode());
		uploadPayslipLine.setPanNo(payslipUploadLine.getPanNo());
		uploadPayslipLine.setAadharNo(payslipUploadLine.getAadharNo());
		uploadPayslipLine.setUanNo(payslipUploadLine.getUanNo());
		uploadPayslipLine.setCurrency(payslipUploadLine.getCurrency());
		uploadPayslipLine.setPeriodDays(payslipUploadLine.getPeriodDays());
		uploadPayslipLine.setPeriodHolidays(payslipUploadLine.getPeriodHolidays());
		uploadPayslipLine.setWorkedDays(payslipUploadLine.getWorkedDays());
		uploadPayslipLine.setLopAndLwop(payslipUploadLine.getLopAndLwop());
		uploadPayslipLine.setArrearDays(payslipUploadLine.getArrearDays());
		uploadPayslipLine.setLateDaysDeduction(payslipUploadLine.getLateDaysDeduction());
		uploadPayslipLine.setOvertimeHours(payslipUploadLine.getOvertimeHours());
		uploadPayslipLine.setWageDays(payslipUploadLine.getWageDays());
		uploadPayslipLine.setAdditionalInfo1(payslipUploadLine.getAdditionalInfo1());
		uploadPayslipLine.setAdditionalInfo2(payslipUploadLine.getAdditionalInfo2());
		uploadPayslipLine.setAdditionalInfo3(payslipUploadLine.getAdditionalInfo3());
		uploadPayslipLine.setA11Name(payslipUploadLine.getA11Name());
		uploadPayslipLine.setA11ValueEarningPeriod(payslipUploadLine.getA11ValueEarningPeriod());
		uploadPayslipLine.setA11ValueArrears(payslipUploadLine.getA11ValueArrears());
		uploadPayslipLine.setA11ValueCurrentPeriod(payslipUploadLine.getA11ValueCurrentPeriod());
		uploadPayslipLine.setA11ValueYtdEarnings(payslipUploadLine.getA11ValueYtdEarnings());
		uploadPayslipLine.setA12Name(payslipUploadLine.getA12Name());
		uploadPayslipLine.setA12ValueEarningPeriod(payslipUploadLine.getA12ValueEarningPeriod());
		uploadPayslipLine.setA12ValueArrears(payslipUploadLine.getA12ValueArrears());
		uploadPayslipLine.setA12ValueCurrentPeriod(payslipUploadLine.getA12ValueCurrentPeriod());
		uploadPayslipLine.setA12ValueYtdEarnings(payslipUploadLine.getA12ValueYtdEarnings());
		uploadPayslipLine.setA13Name(payslipUploadLine.getA13Name());
		uploadPayslipLine.setA13ValueEarningPeriod(payslipUploadLine.getA13ValueEarningPeriod());
		uploadPayslipLine.setA13ValueArrears(payslipUploadLine.getA13ValueArrears());
		uploadPayslipLine.setA13ValueCurrentPeriod(payslipUploadLine.getA13ValueCurrentPeriod());
		uploadPayslipLine.setA13ValueYtdEarnings(payslipUploadLine.getA13ValueYtdEarnings());
		uploadPayslipLine.setA14Name(payslipUploadLine.getA14Name());
		uploadPayslipLine.setA14ValueEarningPeriod(payslipUploadLine.getA14ValueEarningPeriod());
		uploadPayslipLine.setA14ValueArrears(payslipUploadLine.getA14ValueArrears());
		uploadPayslipLine.setA14ValueCurrentPeriod(payslipUploadLine.getA14ValueCurrentPeriod());
		uploadPayslipLine.setA14ValueYtdEarnings(payslipUploadLine.getA14ValueYtdEarnings());
		uploadPayslipLine.setA15Name(payslipUploadLine.getA15Name());
		uploadPayslipLine.setA15ValueEarningPeriod(payslipUploadLine.getA15ValueEarningPeriod());
		uploadPayslipLine.setA15ValueArrears(payslipUploadLine.getA15ValueArrears());
		uploadPayslipLine.setA15ValueCurrentPeriod(payslipUploadLine.getA15ValueCurrentPeriod());
		uploadPayslipLine.setA15ValueYtdEarnings(payslipUploadLine.getA15ValueYtdEarnings());
		uploadPayslipLine.setA16Name(payslipUploadLine.getA16Name());
		uploadPayslipLine.setA16ValueEarningPeriod(payslipUploadLine.getA16ValueEarningPeriod());
		uploadPayslipLine.setA16ValueArrears(payslipUploadLine.getA16ValueArrears());
		uploadPayslipLine.setA16ValueCurrentPeriod(payslipUploadLine.getA16ValueCurrentPeriod());
		uploadPayslipLine.setA16ValueYtdEarnings(payslipUploadLine.getA16ValueYtdEarnings());
		uploadPayslipLine.setA17Name(payslipUploadLine.getA17Name());
		uploadPayslipLine.setA17ValueEarningPeriod(payslipUploadLine.getA17ValueEarningPeriod());
		uploadPayslipLine.setA17ValueArrears(payslipUploadLine.getA17ValueArrears());
		uploadPayslipLine.setA17ValueCurrentPeriod(payslipUploadLine.getA17ValueCurrentPeriod());
		uploadPayslipLine.setA17ValueYtdEarnings(payslipUploadLine.getA17ValueYtdEarnings());
		uploadPayslipLine.setA18Name(payslipUploadLine.getA18Name());
		uploadPayslipLine.setA18ValueEarningPeriod(payslipUploadLine.getA18ValueEarningPeriod());
		uploadPayslipLine.setA18ValueArrears(payslipUploadLine.getA18ValueArrears());
		uploadPayslipLine.setA18ValueCurrentPeriod(payslipUploadLine.getA18ValueCurrentPeriod());
		uploadPayslipLine.setA18ValueYtdEarnings(payslipUploadLine.getA18ValueYtdEarnings());
		uploadPayslipLine.setA19Name(payslipUploadLine.getA19Name());
		uploadPayslipLine.setA19ValueEarningPeriod(payslipUploadLine.getA19ValueEarningPeriod());
		uploadPayslipLine.setA19ValueArrears(payslipUploadLine.getA19ValueArrears());
		uploadPayslipLine.setA19ValueCurrentPeriod(payslipUploadLine.getA19ValueCurrentPeriod());
		uploadPayslipLine.setA19ValueYtdEarnings(payslipUploadLine.getA19ValueYtdEarnings());
		uploadPayslipLine.setA21Name(payslipUploadLine.getA21Name());
		uploadPayslipLine.setA21ValueEarningPeriod(payslipUploadLine.getA21ValueEarningPeriod());
		uploadPayslipLine.setA21ValueArrears(payslipUploadLine.getA21ValueArrears());
		uploadPayslipLine.setA21ValueCurrentPeriod(payslipUploadLine.getA21ValueCurrentPeriod());
		uploadPayslipLine.setA21ValueYtdEarnings(payslipUploadLine.getA21ValueYtdEarnings());
		uploadPayslipLine.setA22Name(payslipUploadLine.getA22Name());
		uploadPayslipLine.setA22ValueEarningPeriod(payslipUploadLine.getA22ValueEarningPeriod());
		uploadPayslipLine.setA22ValueArrears(payslipUploadLine.getA22ValueArrears());
		uploadPayslipLine.setA22ValueCurrentPeriod(payslipUploadLine.getA22ValueCurrentPeriod());
		uploadPayslipLine.setA22ValueYtdEarnings(payslipUploadLine.getA22ValueYtdEarnings());
		uploadPayslipLine.setA23Name(payslipUploadLine.getA23Name());
		uploadPayslipLine.setA23ValueEarningPeriod(payslipUploadLine.getA23ValueEarningPeriod());
		uploadPayslipLine.setA23ValueArrears(payslipUploadLine.getA23ValueArrears());
		uploadPayslipLine.setA23ValueCurrentPeriod(payslipUploadLine.getA23ValueCurrentPeriod());
		uploadPayslipLine.setA23ValueYtdEarnings(payslipUploadLine.getA23ValueYtdEarnings());
		uploadPayslipLine.setA24Name(payslipUploadLine.getA24Name());
		uploadPayslipLine.setA24ValueEarningPeriod(payslipUploadLine.getA24ValueEarningPeriod());
		uploadPayslipLine.setA24ValueArrears(payslipUploadLine.getA24ValueArrears());
		uploadPayslipLine.setA24ValueCurrentPeriod(payslipUploadLine.getA24ValueCurrentPeriod());
		uploadPayslipLine.setA24ValueYtdEarnings(payslipUploadLine.getA24ValueYtdEarnings());
		uploadPayslipLine.setA25Name(payslipUploadLine.getA25Name());
		uploadPayslipLine.setA25ValueEarningPeriod(payslipUploadLine.getA25ValueEarningPeriod());
		uploadPayslipLine.setA25ValueArrears(payslipUploadLine.getA25ValueArrears());
		uploadPayslipLine.setA25ValueCurrentPeriod(payslipUploadLine.getA25ValueCurrentPeriod());
		uploadPayslipLine.setA25ValueYtdEarnings(payslipUploadLine.getA25ValueYtdEarnings());
		uploadPayslipLine.setA26Name(payslipUploadLine.getA26Name());
		uploadPayslipLine.setA26ValueEarningPeriod(payslipUploadLine.getA26ValueEarningPeriod());
		uploadPayslipLine.setA26ValueArrears(payslipUploadLine.getA26ValueArrears());
		uploadPayslipLine.setA26ValueCurrentPeriod(payslipUploadLine.getA26ValueCurrentPeriod());
		uploadPayslipLine.setA26ValueYtdEarnings(payslipUploadLine.getA26ValueYtdEarnings());
		uploadPayslipLine.setA27Name(payslipUploadLine.getA27Name());
		uploadPayslipLine.setA27ValueEarningPeriod(payslipUploadLine.getA27ValueEarningPeriod());
		uploadPayslipLine.setA27ValueArrears(payslipUploadLine.getA27ValueArrears());
		uploadPayslipLine.setA27ValueCurrentPeriod(payslipUploadLine.getA27ValueCurrentPeriod());
		uploadPayslipLine.setA27ValueYtdEarnings(payslipUploadLine.getA27ValueYtdEarnings());
		uploadPayslipLine.setB11Name(payslipUploadLine.getB11Name());
		uploadPayslipLine.setB11ValueEarningPeriod(payslipUploadLine.getB11ValueEarningPeriod());
		uploadPayslipLine.setB11ValueArrears(payslipUploadLine.getB11ValueArrears());
		uploadPayslipLine.setB11ValueCurrentPeriod(payslipUploadLine.getB11ValueCurrentPeriod());
		uploadPayslipLine.setB11ValueYtdEarnings(payslipUploadLine.getB11ValueYtdEarnings());
		uploadPayslipLine.setB12Name(payslipUploadLine.getB12Name());
		uploadPayslipLine.setB12ValueEarningPeriod(payslipUploadLine.getB12ValueEarningPeriod());
		uploadPayslipLine.setB12ValueArrears(payslipUploadLine.getB12ValueArrears());
		uploadPayslipLine.setB12ValueCurrentPeriod(payslipUploadLine.getB12ValueCurrentPeriod());
		uploadPayslipLine.setB12ValueYtdEarnings(payslipUploadLine.getB12ValueYtdEarnings());
		uploadPayslipLine.setB13Name(payslipUploadLine.getB13Name());
		uploadPayslipLine.setB13ValueEarningPeriod(payslipUploadLine.getB13ValueEarningPeriod());
		uploadPayslipLine.setB13ValueArrears(payslipUploadLine.getB13ValueArrears());
		uploadPayslipLine.setB13ValueCurrentPeriod(payslipUploadLine.getB13ValueCurrentPeriod());
		uploadPayslipLine.setB13ValueYtdEarnings(payslipUploadLine.getB13ValueYtdEarnings());
		uploadPayslipLine.setB14Name(payslipUploadLine.getB14Name());
		uploadPayslipLine.setB14ValueEarningPeriod(payslipUploadLine.getB14ValueEarningPeriod());
		uploadPayslipLine.setB14ValueArrears(payslipUploadLine.getB14ValueArrears());
		uploadPayslipLine.setB14ValueCurrentPeriod(payslipUploadLine.getB14ValueCurrentPeriod());
		uploadPayslipLine.setB14ValueYtdEarnings(payslipUploadLine.getB14ValueYtdEarnings());
		uploadPayslipLine.setD11Name(payslipUploadLine.getD11Name());
		uploadPayslipLine.setD11ValueCurrentPeriod(payslipUploadLine.getD11ValueCurrentPeriod());
		uploadPayslipLine.setD11ValueYtdDeductions(payslipUploadLine.getD11ValueYtdDeductions());
		uploadPayslipLine.setD12Name(payslipUploadLine.getD12Name());
		uploadPayslipLine.setD12ValueCurrentPeriod(payslipUploadLine.getD12ValueCurrentPeriod());
		uploadPayslipLine.setD12ValueYtdDeductions(payslipUploadLine.getD12ValueYtdDeductions());
		uploadPayslipLine.setD13Name(payslipUploadLine.getD13Name());
		uploadPayslipLine.setD13ValueCurrentPeriod(payslipUploadLine.getD13ValueCurrentPeriod());
		uploadPayslipLine.setD13ValueYtdDeductions(payslipUploadLine.getD13ValueYtdDeductions());
		uploadPayslipLine.setD14Name(payslipUploadLine.getD14Name());
		uploadPayslipLine.setD14ValueCurrentPeriod(payslipUploadLine.getD14ValueCurrentPeriod());
		uploadPayslipLine.setD14ValueYtdDeductions(payslipUploadLine.getD14ValueYtdDeductions());
		uploadPayslipLine.setD15Name(payslipUploadLine.getD15Name());
		uploadPayslipLine.setD15ValueCurrentPeriod(payslipUploadLine.getD15ValueCurrentPeriod());
		uploadPayslipLine.setD15ValueYtdDeductions(payslipUploadLine.getD15ValueYtdDeductions());
		uploadPayslipLine.setD16Name(payslipUploadLine.getD16Name());
		uploadPayslipLine.setD16ValueCurrentPeriod(payslipUploadLine.getD16ValueCurrentPeriod());
		uploadPayslipLine.setD16ValueYtdDeductions(payslipUploadLine.getD16ValueYtdDeductions());
		uploadPayslipLine.setD17Name(payslipUploadLine.getD17Name());
		uploadPayslipLine.setD17ValueCurrentPeriod(payslipUploadLine.getD17ValueCurrentPeriod());
		uploadPayslipLine.setD17ValueYtdDeductions(payslipUploadLine.getD17ValueYtdDeductions());
		uploadPayslipLine.setLeave1Name(payslipUploadLine.getLeave1Name());
		uploadPayslipLine.setLeave1ValueOpening(payslipUploadLine.getLeave1ValueOpening());
		uploadPayslipLine.setLeave1ValueAccured(payslipUploadLine.getLeave1ValueAccured());
		uploadPayslipLine.setLeave1Availed(payslipUploadLine.getLeave1Availed());
		uploadPayslipLine.setLeave1ValueBalance(payslipUploadLine.getLeave1ValueBalance());
		uploadPayslipLine.setLeave2Name(payslipUploadLine.getLeave2Name());
		uploadPayslipLine.setLeave2ValueOpening(payslipUploadLine.getLeave2ValueOpening());
		uploadPayslipLine.setLeave2ValueAccured(payslipUploadLine.getLeave2ValueAccured());
		uploadPayslipLine.setLeave2Availed(payslipUploadLine.getLeave2Availed());
		uploadPayslipLine.setLeave2ValueBalance(payslipUploadLine.getLeave2ValueBalance());
		uploadPayslipLine.setLeave3Name(payslipUploadLine.getLeave3Name());
		uploadPayslipLine.setLeave3ValueOpening(payslipUploadLine.getLeave3ValueOpening());
		uploadPayslipLine.setLeave3ValueAccured(payslipUploadLine.getLeave3ValueAccured());
		uploadPayslipLine.setLeave3Availed(payslipUploadLine.getLeave3Availed());
		uploadPayslipLine.setLeave3ValueBalance(payslipUploadLine.getLeave3ValueBalance());
		uploadPayslipLine.setLeave4Name(payslipUploadLine.getLeave4Name());
		uploadPayslipLine.setLeave4ValueOpening(payslipUploadLine.getLeave4ValueOpening());
		uploadPayslipLine.setLeave4ValueAccured(payslipUploadLine.getLeave4ValueAccured());
		uploadPayslipLine.setLeave4Availed(payslipUploadLine.getLeave4Availed());
		uploadPayslipLine.setLeave4ValueBalance(payslipUploadLine.getLeave4ValueBalance());
		uploadPayslipLine.setRemarksName(payslipUploadLine.getRemarksName());
		uploadPayslipLine.setRemarksValue(payslipUploadLine.getRemarksValue());
		uploadPayslipLine.setNetPayPeriodName(payslipUploadLine.getNetPayPeriodName());
		uploadPayslipLine.setNetPayPeriodValue(payslipUploadLine.getNetPayPeriodValue());
		uploadPayslipLine.setNetPayWordsName(payslipUploadLine.getNetPayWordsName());
		uploadPayslipLine.setNetPayWordsValue(payslipUploadLine.getNetPayWordsValue());
		uploadPayslipLine.setPfAccumulationByEmployer(payslipUploadLine.getPfAccumulationByEmployer());
		uploadPayslipLine.setPfOpeningValue(payslipUploadLine.getPfOpeningValue());
		uploadPayslipLine.setPfClosingValue(payslipUploadLine.getPfClosingValue());
		uploadPayslipLine.setPfBalanceValue(payslipUploadLine.getPfBalanceValue());
		uploadPayslipLine.setIsClean("Y");
		
		uploadPayslipLine.setStatus("SUCCESS");
		uploadPayslipLine.setUploadPayslipHdrId(savedHdr.getId());
		uploadPayslipLine.setCreatedBy(commonUtils.getLoggedinEmployeeId());
		uploadPayslipLine.setCreatedDt(LocalDateTime.now());
		if (uploadPayslipLine.getStatus().equalsIgnoreCase("SUCCESS")) {
			successCount++;
			savedHdr.setSuccess_count(successCount);
			savedHdr.setRecords_uploaded(String.valueOf(successCount));

			savedHdr.setProcess_status("COMPLETED");

		
			uploadPayslipHdrRepository.save(savedHdr);
		}
		
		return uploadPayslipLine;
	}

}

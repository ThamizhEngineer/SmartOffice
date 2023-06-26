package com.ss.smartoffice.soservice.event.uploadpayslip;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.soservice.master.pay.CompanyPayCycle.CompanyPayCycleLinesRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipHdr;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
import com.ss.smartoffice.soservice.transaction.uploadpayslip.UploadPaySlipLineProcessor;
import com.ss.smartoffice.soservice.transaction.uploadpayslip.UploadPayslipHdrRepository;
import com.ss.smartoffice.soservice.transaction.uploadpayslip.UploadPayslipHdrService;
import com.ss.smartoffice.soservice.transaction.uploadpayslip.UploadPayslipLineRepository;

@Service
@RestController
@RequestMapping("event/manual-payslip-process")
public class ManualPaySlipProcessEventProcessor {

	@Value("${docs.folder.delimitter}")
	private String folderDelimitter;
	@Autowired
	UploadPayslipHdrService uploadPayslipHdrService;
	
	@Autowired
	UploadPayslipHdrRepository uploadPayslipHdrRepository;
	@Autowired
	CompanyPayCycleLinesRepository companyPayCycleLinesRepository;
	
	@Autowired
	UploadPaySlipLineProcessor uploadPaySlipLineProcessor;
	
	@Autowired
	UploadPayslipLineRepository  uploadPayslipLineRepository;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	DocumentManagementHelper documentManagementHelper;
	
	/**
	 * 
	 * @param event - ManualPayslipProcessEvent
	 * 	Highlevel Steps
	 *  1. load Salary-data from csv file onto upload-payslip-line table
	 *  2. update newly added line-data with headerId
	 *  3. update stats of above operations to header table
	 *  4. for each line (salary for an memployee)
	 *  	validate
	 *  	transform
	 *  	create empPayout
	 *  	update uploadSalaryLine
	 *  5. update process-complete
	 * 
	 * @return
	 * @throws Exception
	 */
	
}

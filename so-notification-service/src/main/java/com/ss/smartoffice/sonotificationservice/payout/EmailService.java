/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.ss.smartoffice.sonotificationservice.payout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.interceptor.NumberToWords;
import com.ss.smartoffice.shared.model.EmployeePaySlipLine;
import com.ss.smartoffice.sonotificationservice.common.SmsHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.shared.pay.EmployeePayoutLinesRepository;

import com.ss.smartoffice.shared.model.EmployeePayoutLine;

@Service
public class EmailService {

	@Value("${pdf.location}")
	private String payslipPdfLocation;

	@Value("${employees.url}")
	private String employeeUrl;
	@Autowired
	DocInfoHelper docInfoHelper;

	@Value("${document.url}")
	private String documentUrl;

	@Value("${updatepayout.url}")
	private String updatepayoutUrl;

	@Value("${smartoffice.url}")
	private String smartofficeUrl;

	@Value("${podhigai.url}")
	private String podhigaiUrl;
	@Autowired
	DocInfoRepository docInfoRepository;
	@Autowired
	NumberToWords numberToWords;

	private static final String EMAIL_PAYROLL_TEMPLATE_NAME = "html/salary-payout";
	private static final String EMAIL_PAYROLL_TEMPLATE_NAME_NEW = "html/salary-payout-new";
	private static final String EMAIL_WITHATTACHMENT_TEMPLATE_NAME = "html/salary-payout-email-withattachment";

	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper docMgmtService;
	@Autowired
	SmsHelper smsHelper;

	@Autowired
	EmployeePayoutLinesRepository employeePayoutLineRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	int allowanceSize;
	int deductionSize;
	int lineCount;

	String salaryForMonth = "";
	String salaryForYear = "";
	String salaryPaidMonth = "";
	String salaryPaidYear = "";

	public String sendMailWithAttachment(EmployeePayout employeePayout)
			throws SmartOfficeException{
		
		try {
			System.out.println("kh-sendMailWithAttachment");

			final Context ctx = new Context(Locale.ENGLISH);
			String additionalInfos1 = "Additional Info 1";
			String additionalInfos2 = "Additional Info 2";
			String additionalInfos3 = "Additional Info 3";
			String additionalFixedCompensation1 = "AdditionalFixedCompensation1";
			Iterable<EmployeePayoutLine> employeePayoutLinesList = employeePayoutLineRepository
					.findByemployeePayoutId(employeePayout.getId().toString());

			List<EmployeePayoutLine> allowanceList = new ArrayList<>();
			List<EmployeePaySlipLine> a1List = new ArrayList<>();

			List<EmployeePaySlipLine> adfcList = new ArrayList<>();
			List<EmployeePaySlipLine> avcList = new ArrayList<>();
			List<EmployeePaySlipLine> a2List = new ArrayList<>();
			List<EmployeePaySlipLine> bList = new ArrayList<>();
			List<EmployeePayoutLine> dList = new ArrayList<>();
			List<EmployeePayoutLine> deductionList = new ArrayList<>();
			EmployeePaySlipLine employeePaySlipLine = new EmployeePaySlipLine();

			try {

				for (EmployeePayoutLine employeePayoutLine : employeePayoutLinesList) {
					employeePaySlipLine = new EmployeePaySlipLine();
					if (Float.parseFloat(employeePayoutLine.getLineAmt()) != 0.0
							&& !(employeePayoutLine.getLineAmt().isEmpty())) {

						if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("a1")) {
							allowanceList.add(employeePayoutLine);

							employeePaySlipLine.setAllowance(employeePayoutLine);
							System.out.println(a1List);
							a1List.add(employeePaySlipLine);

						} else if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("a2")) {
							allowanceList.add(employeePayoutLine);

							employeePaySlipLine.setAllowance(employeePayoutLine);

							a2List.add(employeePaySlipLine);

						} else if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("b")) {
							allowanceList.add(employeePayoutLine);
							employeePaySlipLine.setAllowance(employeePayoutLine);
							bList.add(employeePaySlipLine);

						} else if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("adfc")) {
							adfcList.add(employeePaySlipLine);
						} else if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("avc")) {

							avcList.add(employeePaySlipLine);
						} else if (employeePayoutLine.getIsAllowance().equals("N")
								&& employeePayoutLine.getGrouping().equals("d")) {
							dList.add(employeePayoutLine);
							deductionList.add(employeePayoutLine);

						}
					} else {
						if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("adfc")) {
							adfcList.add(employeePaySlipLine);
						}
						if (employeePayoutLine.getIsAllowance().equals("Y")
								&& employeePayoutLine.getGrouping().equals("b")) {
							avcList.add(employeePaySlipLine);
						}

					}

				}

				for (int i = 0; i < a1List.size(); i++) {
					if (i < dList.size()) {
						a1List.get(i).setDeduction(dList.get(i));
					}
				}
				if (a1List.size() < dList.size()) {
					for (int i = a1List.size(); i < dList.size(); i++) {
						employeePaySlipLine = new EmployeePaySlipLine();
						employeePaySlipLine.setDeduction(dList.get(i));
					}
				}

				if (!adfcList.isEmpty()) {
					additionalFixedCompensation1 = "AdditionalFixedCompensation1";

				} else {
					additionalFixedCompensation1 = "";
				}

				if (employeePayout.getAdditionalInfo1() != null && !employeePayout.getAdditionalInfo1().isEmpty()) {
					additionalInfos1 = "AdditionalInfo1";
				} else {
					additionalInfos1 = null;
				}
				if (employeePayout.getAdditionalInfo2() != null && !employeePayout.getAdditionalInfo2().isEmpty()) {
					additionalInfos2 = "AdditionalInfo2";

				} else {
					additionalInfos2 = null;
				}
				if (employeePayout.getAdditionalInfo3() != null && !employeePayout.getAdditionalInfo3().isEmpty()) {
					additionalInfos3 = "AdditionalInfo3";
				} else {
					additionalInfos3 = null;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				ctx.setVariable("empCode", employeePayout.getEmployeeCode());
				ctx.setVariable("bank", employeePayout.getdBankName());
				ctx.setVariable("empName", employeePayout.getEmployeeName());
				ctx.setVariable("ifsc", employeePayout.getIfscCode());
				ctx.setVariable("payDate", employeePayout.getCreatedDt());
				ctx.setVariable("dob", employeePayout.getDob());
				ctx.setVariable("department", employeePayout.getDepartment());
				ctx.setVariable("pfNo", employeePayout.getdPfNo());
				ctx.setVariable("locationOfPosition", employeePayout.getLocationOfPosition());
				ctx.setVariable("accountNumber", employeePayout.getdAccountNumber());
				ctx.setVariable("periodDays", employeePayout.getPeriodDays());
				ctx.setVariable("doj", employeePayout.getDoj());
				ctx.setVariable("additionalInfos1", additionalInfos1);
				ctx.setVariable("additionalInfos2", additionalInfos2);
				ctx.setVariable("additionalInfos3", additionalInfos3);
				ctx.setVariable("additionalInfo1", employeePayout.getAdditionalInfo1());
				ctx.setVariable("additionalInfo2", employeePayout.getAdditionalInfo2());
				ctx.setVariable("additionalInfo3", employeePayout.getAdditionalInfo3());
				ctx.setVariable("additionalFixedCompensation1", additionalFixedCompensation1);
				ctx.setVariable("uanNo", employeePayout.getUanNo());
				ctx.setVariable("periodHolidays", employeePayout.getPeriodHolidays());
				ctx.setVariable("designation", employeePayout.getdDesignation());
				ctx.setVariable("workedDays", employeePayout.getWorkedDays());
				ctx.setVariable("grade", employeePayout.getdGrade());
				ctx.setVariable("epsNo", employeePayout.getEpsNo());
				ctx.setVariable("lopAndlwop", employeePayout.getLopAndLwop());
				ctx.setVariable("specialDesignation", employeePayout.getSpecialDesignation());
				ctx.setVariable("esiNo", employeePayout.getdEsiNumber());
				ctx.setVariable("arrearDays", employeePayout.getArrearDays());
				ctx.setVariable("skill", employeePayout.getSkill());
				ctx.setVariable("panNo", employeePayout.getdPanNumber());
				ctx.setVariable("lateDaysDeduction", employeePayout.getLateDaysDeduction());
				ctx.setVariable("business", employeePayout.getBuisness());
				ctx.setVariable("aadharNo", employeePayout.getAadharNo());
				ctx.setVariable("overtimeHours", employeePayout.getOvertimeHours());
				ctx.setVariable("natureOfPosition", employeePayout.getNatureOfPosition());
				ctx.setVariable("currency", employeePayout.getCurrency());
				ctx.setVariable("wageDays", employeePayout.getWageDays());
				ctx.setVariable("pfAccumulationByEmployer", employeePayout.getPfAccumulationByEmployer());
				ctx.setVariable("pfOpeningValue", employeePayout.getPfOpeningValue());
				ctx.setVariable("pfCurrentValue", employeePayout.getPfCurrentValue());
				ctx.setVariable("pfBalanceValue", employeePayout.getPfBalanceValue());
				ctx.setVariable("totalAllowanceAmt", commonUtils.printInLakhs(""
						+ (employeePayout.getTotalAllowanceAmt() != null ? employeePayout.getTotalAllowanceAmt() : "00")));
				ctx.setVariable("totalDeductionAmt", commonUtils.printInLakhs(""
						+ (employeePayout.getTotalDeductionAmt() != null ? employeePayout.getTotalDeductionAmt() : "00")));
				ctx.setVariable("totalVariablePay", commonUtils.printInLakhs(
						"" + (employeePayout.getTotalVariablePay() != null ? employeePayout.getTotalVariablePay() : "00")));
				ctx.setVariable("grossSalary", commonUtils.printInLakhs("" + employeePayout.getGrossSalary()));
				ctx.setVariable("totalTax", commonUtils
						.printInLakhs("" + (employeePayout.getTotalTax() != null ? employeePayout.getTotalTax() : "00")));

				ctx.setVariable("netSalary", commonUtils
						.printInLakhs("" + (employeePayout.getNetPayAmt() != null ? employeePayout.getNetPayAmt() : "00")));
				
				
				ctx.setVariable("netPayInWords", numberToWords.convert(Integer.parseInt(employeePayout.getNetPayAmt().replace(".0", "")))+" Rupees "+ "Only");
				
				
				ctx.setVariable("totalA1Value", commonUtils.printInLakhs("" + employeePayout.getTotalA1Value()));
				ctx.setVariable("totalA1ArrearValue", commonUtils.printInLakhs("" + employeePayout.getTotalA1ArrearAmt()));

				ctx.setVariable("totalA1CurrentValue",
						commonUtils.printInLakhs("" + employeePayout.getTotalA1CurrentPeriod()));
				ctx.setVariable("totalA1YtdValue", commonUtils.printInLakhs("" + employeePayout.getTotalA1Ytd()));
				ctx.setVariable("totalD1Value", commonUtils.printInLakhs("" + employeePayout.getTotalD1value()));
				ctx.setVariable("totalD1ValueYtd", commonUtils.printInLakhs("" + employeePayout.getTotalD1Ytd()));
				ctx.setVariable("totalA2Value", commonUtils.printInLakhs("" + employeePayout.getTotalA2Value()));
				ctx.setVariable("totalA2ArrearValue", commonUtils.printInLakhs("" + employeePayout.getTotalA2ArrearAmt()));
				ctx.setVariable("totalA2CurrentValue",
						commonUtils.printInLakhs("" + employeePayout.getTotalA2CurrentPeriod()));

				ctx.setVariable("totalA2YtdValue", commonUtils.printInLakhs("" + employeePayout.getTotalA2Ytd()));
				ctx.setVariable("totalBValue", commonUtils.printInLakhs("" + employeePayout.getTotalBValue()));
				ctx.setVariable("totalBArrearValue", commonUtils.printInLakhs("" + employeePayout.getTotalBArrearAmt()));
				ctx.setVariable("totalBCurrentValue",
						commonUtils.printInLakhs("" + employeePayout.getTotalBCurrentPeriod()));
				ctx.setVariable("totalBYtdValue", commonUtils.printInLakhs("" + employeePayout.getTotalBYtd()));
				ctx.setVariable("remarks", employeePayout.getRemarks());
				ctx.setVariable("totalArrearAmt", commonUtils.printInLakhs("" + employeePayout.getTotalArrearAmt()));
				ctx.setVariable("totalCurrentPeriod",
						commonUtils.printInLakhs("" + employeePayout.getTotalCurrentPeriod()));
				ctx.setVariable("totalYtd", commonUtils.printInLakhs("" + employeePayout.getTotalYtd()));
				ctx.setVariable("totalLeaveOpening", employeePayout.getTotalLeaveOpening());
				ctx.setVariable("totalAccured", employeePayout.getTotalAccured());
				ctx.setVariable("totalAvailed", employeePayout.getTotalAvailed());
				ctx.setVariable("totalBalance", employeePayout.getTotalBalance());
				ctx.setVariable("bListSize", bList.size());
				ctx.setVariable("leaveListSize", employeePayout.getEmployeePayoutLeaveBalances().size());
				ctx.setVariable("a1List", a1List);
				ctx.setVariable("a2List", a2List);
				ctx.setVariable("bList", bList);
				ctx.setVariable("dList", dList);
				ctx.setVariable("adfcList", adfcList);
				ctx.setVariable("avcList", avcList);
				salaryForMonth = CommonUtils.monthConversion(employeePayout.getdSalaryForMonth());
				salaryForYear = employeePayout.getdSalaryForYear();
				ctx.setVariable("salaryForYear", employeePayout.getdSalaryForYear());
				ctx.setVariable("salaryForMonth", salaryForMonth);
				
				if (employeePayout.getEmployeePayoutLeaveBalances() != null
						&& !employeePayout.getEmployeePayoutLeaveBalances().isEmpty()) {
					ctx.setVariable("leaveList", employeePayout.getEmployeePayoutLeaveBalances());
					System.out.println(employeePayout.getEmployeePayoutLeaveBalances());
					
					salaryPaidMonth = salaryForMonth;
					salaryPaidYear = salaryForYear;

					
					ctx.setVariable("salaryPaidMonth", salaryPaidMonth + "-" + salaryPaidYear);
					ctx.setVariable("esiNumber", employeePayout.getdEsiNumber());
					ctx.setVariable("panNumber", employeePayout.getdPanNumber());
					ctx.setVariable("email", employeePayout.getdEmail());
					ctx.setVariable("bankName", employeePayout.getdBankName());

				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new SmartOfficeException(e.getLocalizedMessage());
			}

			// Create the HTML body using Thymeleaf
			final String htmlContent = this.htmlTemplateEngine.process(EMAIL_PAYROLL_TEMPLATE_NAME, ctx);

			FileOutputStream os = null;
			String fileNamePrefix = "payout-" + commonUtils.generateId();

			String fileNameSuffix = ".pdf";

			final File outputFile = File.createTempFile(fileNamePrefix, fileNameSuffix);
			os = new FileOutputStream(outputFile);

			String fileName = payslipPdfLocation + fileNamePrefix + fileNameSuffix;
			try {

				ITextRenderer renderer = new ITextRenderer();

				renderer.setDocumentFromString(htmlContent);
				renderer.layout();
				renderer.createPDF(new FileOutputStream(fileName));

				renderer.finishPDF();

				final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
				message.setSubject(
						"Salary slip for the month " + commonUtils.monthConversion(employeePayout.getdSalaryForMonth())
								+ "-" + employeePayout.getdSalaryForYear());
				message.setFrom(commonUtils.getSenderEmail());
				message.setTo(employeePayout.getdEmail());
				final Context ctx1 = new Context(Locale.ENGLISH);
				ctx1.setVariable("empName", employeePayout.getEmployeeName());
				ctx1.setVariable("salaryPaidMonth", commonUtils.monthConversion(employeePayout.getdSalaryForMonth()) + "-"
						+ employeePayout.getdSalaryForYear());
				// Create the HTML body using Thymeleaf
				final String htmlContent1 = this.htmlTemplateEngine.process(EMAIL_WITHATTACHMENT_TEMPLATE_NAME, ctx1);
				message.setText(htmlContent1, true /* isHtml */);

				// Add the attachment

				FileSystemResource file = new FileSystemResource(new File(fileName));
				message.addAttachment("Salary slip.pdf", file, "application/pdf");

				// Send mail
				this.mailSender.send(mimeMessage);

			} catch (Exception e) {			
				e.printStackTrace();
			}

			memployee employee1 = new memployee();
			HttpHeaders headers = new HttpHeaders();

			System.out.println("9");
			HttpEntity<memployee> request = new HttpEntity<memployee>(employee1, headers);
			ResponseEntity<memployee> employeeEntity = commonUtils.getRestTemplate()
					.exchange(employeeUrl + employeePayout.getEmployeeId(), HttpMethod.GET, request, memployee.class);

			memployee memployee = employeeEntity.getBody();
 

			System.out.println("10");

			String smsSessage = "Hi " + employeePayout.getEmployeeName() + " Salary slip for the month" + salaryForMonth
					+ " is generated." + memployee.getContactMobileNo() + "Please logon to " + podhigaiUrl
					+ " for further information.";
			smsHelper.sendSms(memployee.getContactMobileNo(), smsSessage);

			MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix + fileNameSuffix,
					fileNamePrefix + fileNameSuffix, "", new FileInputStream(new File(fileName)));
			MultipartFile[] multipartFileArray = new MultipartFile[1];
			multipartFileArray[0] = multipartFile;
			List<DocInfo> docInfos = docMgmtService.uploadDocsAsBinary("PAYSLIP-USER-PDF", multipartFileArray);
			String docPdfId = "";
			for (DocInfo docInfo : docInfos) {
				docPdfId = docInfo.getDocId();
			}

			List<DocInfo> docInfosFromDb = docMgmtService.getDocInfoByDocId(docPdfId);
			employeePayout.setPayslipDocId(docPdfId);
			for (DocInfo docInfo : docInfosFromDb) {

				List<DocMetadata> docMetadataList = new ArrayList<>();
				DocMetadata docMetadata = new DocMetadata();
				docMetadata.setMdCode("emp-id");
				docMetadata.setMdValue(employeePayout.getId().toString());
				docMetadataList.add(docMetadata);

				docInfo.setMetadataList(docMetadataList);
				docInfoHelper.saveAll(docInfosFromDb);
			}

//		HttpEntity<DocInfo> requestDoc = new HttpEntity<DocInfo>(docInfo, headers);
//		DocInfo savedDocInfo = commonUtils.getRestTemplate().postForObject(documentUrl, requestDoc, DocInfo.class);
//		System.out.println("doc-info");

			HttpEntity<EmployeePayout> requestPayout = new HttpEntity<EmployeePayout>(employeePayout, headers);
			String applicantUrlForPatch = updatepayoutUrl + employeePayout.getId() + "/app-pdf";
			System.out.println(applicantUrlForPatch);
			commonUtils.getRestTemplate().exchange(applicantUrlForPatch, HttpMethod.POST, requestPayout,
					EmployeePayout.class);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e);
		}

	}

}

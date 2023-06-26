package com.ss.smartoffice.soservice.transaction.incident;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.print.PrintBusHelper;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.InterviewResults;

@Controller
@RequestMapping("transaction/incidents/export-app")
public class IncidentExportApplicantService {
	@Autowired
	IncidentApplicantRepo incidentApplicantRepo;
	
	@Autowired
	IncidentTestRepo incidentTestRepo;
	
	@Autowired
	PrintBusHelper printBusHelper;
	
	@GetMapping("/{incidentId}/overall-test-status")
	public List<com.ss.smartoffice.shared.model.IncidentApplicant> getAllOverall(
			@PathVariable(value = "incidentId") String incidentId, Model model) throws SmartOfficeException {
		List<com.ss.smartoffice.shared.model.IncidentApplicant> incidentsApplicants = incidentApplicantRepo
				.findByIncidentId(incidentId);
		Map<String, String> printAttributes = new LinkedHashMap<String,String>();

		printAttributes.put("FirstName", "firstName");
		printAttributes.put("LastName", "lastName");
		printAttributes.put("EmailId", "email");
		printAttributes.put("MobileNumber", "mobileNumber");
		printAttributes.put("IsExperienced", "expType");
		printAttributes.put("InterviewEligibible", "isInterviewEligible");
		printAttributes.put("TestEligibible", "isEligibleForTest");
		printAttributes.put("OverallTestStatus", "finalTestStatus");
		printAttributes.put("OverallInterviewStatus", "interviewScheduledStatus");
		printAttributes.put("FinalDecision", "finalDecision");
		printAttributes.put("FinalDecisionDt", "finalDecisionDt");
		printAttributes.put("UserName", "userName");
		printAttributes.put("Password", "password");

		model.addAttribute("dataKeyName", "incidentApplicantList");
		model.addAttribute("dataType", "IncidentApplicant");

		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "overall-test-status");

		return incidentsApplicants;
	}
	

	@GetMapping("/{incidentId}/applicant-filtered")
	public List<com.ss.smartoffice.shared.model.IncidentApplicant> getExportApplicant(
			@PathVariable(value = "incidentId") String incidentId, Model model) throws SmartOfficeException {
		List<com.ss.smartoffice.shared.model.IncidentApplicant> incidentsApplicants = incidentApplicantRepo
				.findByIncidentId(incidentId);
		Map<String, String> printAttributes = new LinkedHashMap<String,String>();

		printAttributes.put("FirstName", "firstName");
		printAttributes.put("LastName", "lastName");
		printAttributes.put("EmailId", "email");
		printAttributes.put("MobileNumber", "mobileNumber");
		printAttributes.put("Institute", "institute");
		printAttributes.put("DegreeName", "degreeName");
		printAttributes.put("Course", "course");
		printAttributes.put("PassoutYear", "passoutYear");
		printAttributes.put("CoverLetter", "coverLetter");
		printAttributes.put("CurrCompany", "currCompany");
		printAttributes.put("CurrDesignation", "currDesignation");
		printAttributes.put("CurrExperience", "currExperience");
		printAttributes.put("CurrSalary", "currSalary");
		printAttributes.put("Marks", "marks");
		printAttributes.put("HistoryOfArrears", "historyOfArrears");
		printAttributes.put("ExpType", "expType");
		printAttributes.put("UserName", "userName");
		printAttributes.put("Password", "password");

		model.addAttribute("dataKeyName", "incidentApplicantList");
		model.addAttribute("dataType", "IncidentApplicant");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "applicants");
		return incidentsApplicants;
	}
 
	
////	@GetMapping("/{incidentId}/results")
////	public List<InterviewResults> getInterviewResults(@PathVariable(value="incidentId")Integer incidentId)throws SmartOfficeException{
////		System.out.println(incidentId);
////		return incidentApplicantRepo.findByIDE(incidentId);
////	}
//	
	@GetMapping("/{incidentId}/export-test-result")
	public List<com.ss.smartoffice.shared.model.ExportTestResultView> getExportTestResult(
			@PathVariable(value="incidentId") Integer incidentId,Model model) throws SmartOfficeException {
		List<com.ss.smartoffice.shared.model.ExportTestResultView> incidentests= incidentTestRepo.findByincidentsId(incidentId);
		Map<String, String> printAttributes = new LinkedHashMap<String,String>();
		
		printAttributes.put("TestName", "testTemplateName");
		printAttributes.put("Applicant Name", "applicantName");
		printAttributes.put("Test Progress", "testStatus");
		printAttributes.put("Correct Answers", "totalCorrect");
		printAttributes.put("Wrong Answers", "totalWrong");
		printAttributes.put("Total Correct Easy", "totalCorrectEasy");
		printAttributes.put("Total Correct Medium", "totalCorrectMedium");
		printAttributes.put("Total Correct Tough", "totalCorrectTough");
		printAttributes.put("unattended questions", "totalUnAttended");
		printAttributes.put("Score", "score");
		printAttributes.put("TestResult", "testResult");
		model.addAttribute("dataKeyName", "exportTestResultViewList");
		model.addAttribute("dataType", "ExportTestResultView");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "export-test-result");
		
		
		return incidentests;
		 
	}
}

package com.ss.smartoffice.soreport.revenue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reports/revenue")
public class RevenueService {

	@Autowired
	RevenueRepo repo;

	@GetMapping("/summary")
	public List<com.ss.smartoffice.shared.model.Revenue> summary(@RequestParam(value = "soCode", required = false) String soCode,
			@RequestParam(value = "invoiceCode", required = false) String invoiceCode,
			@RequestParam(value = "monthNo", required = false) String monthNo,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "quarterName", required = false) String quarterName,
			@RequestParam(value = "buName", required = false) String buName,
			@RequestParam(value = "divisionName", required = false) String divisionName,
			@RequestParam(value = "functionName", required = false) String functionName,
			@RequestParam(value = "clientCode", required = false) String clientCode,
			@RequestParam(value = "clientName", required = false) String clientName,
			@RequestParam(value = "countryName", required = false) String countryName,
			@RequestParam(value = "jobCodes", required = false) String jobCodes,
			Model model) {

		List<com.ss.smartoffice.shared.model.Revenue> resSummary = repo.summary(soCode, invoiceCode, monthNo, year,quarterName, buName, divisionName,
				functionName, clientCode, clientName, countryName, jobCodes);

		Map<String, String> printAttributes = formPrintAttrib();
		model.addAttribute("dataKeyName", "revenueList");
		model.addAttribute("dataType", "Revenue");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "Revenue");
		return resSummary;
	}

	private Map<String, String> formPrintAttrib() {
		Map<String, String> printAttributes = new LinkedHashMap<String, String>();
		printAttributes.put("clientCode", "clientCode");
		printAttributes.put("clientName", "clientName");
		printAttributes.put("buName", "buName");
		printAttributes.put("divisionName", "divisionName");
		printAttributes.put("functionName", "functionName");
		printAttributes.put("soCode", "soCode");
		printAttributes.put("countryName", "countryName");
		printAttributes.put("quarterName", "quarterName");
		printAttributes.put("invoiceCode", "invoiceCode");
		printAttributes.put("invFinalAmt", "invFinalAmt");
		printAttributes.put("invPaidAmt", "invPaidAmt");
		printAttributes.put("invBalAmt", "invBalAmt");
		printAttributes.put("jobCodes", "jobCodes");
		return printAttributes;
	}

}

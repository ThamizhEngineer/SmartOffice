package com.ss.smartoffice.soreport.orderintake;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@Controller
@RequestMapping("/reports/order-intake")
public class OrderInTakeService {

	@Autowired
	OrderInTakeRepo orderInTakeRepo;

	@GetMapping
	public Iterable<com.ss.smartoffice.shared.model.OrderInTake> getAll() throws SmartOfficeException {
		return orderInTakeRepo.findAll();
	}

	@GetMapping("/summary")
	public List<com.ss.smartoffice.shared.model.OrderInTake> summary(
			@RequestParam(value = "soCode", required = false) String soCode,
			@RequestParam(value = "soOrderAmount", required = false) String soOrderAmount,
			@RequestParam(value = "isVirtualPo", required = false) String isVirtualPo,
			@RequestParam(value = "virtualPoNum", required = false) String virtualPoNum,
			@RequestParam(value = "hasGoods", required = false) String hasGoods,
			@RequestParam(value = "hasServices", required = false) String hasServices,
			@RequestParam(value = "buName", required = false) String buName,
			@RequestParam(value = "divisionName", required = false) String divisionName,
			@RequestParam(value = "functionName", required = false) String functionName,
			@RequestParam(value = "clientCode", required = false) String clientCode,
			@RequestParam(value = "clientName", required = false) String clientName,
			@RequestParam(value = "countryName", required = false) String countryName,
			@RequestParam(value = "jobCodes", required = false) String jobCodes,
			@RequestParam(value = "monthNo", required = false) String monthNo,
			@RequestParam(value = "year", required = false) String year,
			Model model) {
		List<com.ss.smartoffice.shared.model.OrderInTake> resSummary = orderInTakeRepo.summary(soCode, soOrderAmount,
				isVirtualPo, virtualPoNum, hasGoods, hasServices, buName, divisionName, functionName, clientCode,
				clientName, countryName, jobCodes, monthNo, year);
		Map<String, String> printAttributes = formPrintAttrib();
		model.addAttribute("dataKeyName", "orderInTakeList");
		model.addAttribute("dataType", "OrderInTake");
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "OrderInTake");
		return resSummary;
	}

	private Map<String, String> formPrintAttrib() {
		Map<String, String> printAttributes = new LinkedHashMap<String, String>();
		printAttributes.put("soCode", "soCode");
		printAttributes.put("soOrderAmount", "soOrderAmount");
		printAttributes.put("isVirtualPo", "isVirtualPo");
		printAttributes.put("virtualPoNum", "virtualPoNum");
		printAttributes.put("hasGoods", "hasGoods");
		printAttributes.put("hasServices", "hasServices");
		printAttributes.put("buName", "buName");
		printAttributes.put("divisionName", "divisionName");
		printAttributes.put("functionName", "functionName");
		printAttributes.put("clientCode", "clientCode");
		printAttributes.put("clientName", "clientName");
		printAttributes.put("countryName", "countryName");
		printAttributes.put("jobCodes", "jobCodes");
		return printAttributes;
	}

}

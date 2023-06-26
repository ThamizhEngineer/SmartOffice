package com.ss.smartoffice.shared.print;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.smartoffice.shared.interceptor.EmployeeSummariesRepo;

@Controller
public class PrintService {
	
	@Autowired
	EmployeeSummariesRepo employeeSummariesRepo;
	@Autowired
	PrintBusHelper printBusHelper;
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)

	public String getEmployees(Model model ) {
//		return printBusHelper.downloadHelper(model);

		Map<String, String> printAttributes = new HashMap<>();
	
		printAttributes.put("EmpName", "empName");
		printAttributes.put("EmpCode", "empCode");
		model.addAttribute("data", employeeSummariesRepo.findAll());
		model.addAttribute("printAttributes", printAttributes);
		model.addAttribute("fileName", "my-emp");
		return "";
		
	}

}

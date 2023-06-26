package com.ss.smartoffice.soservice.master.employeeprofileprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.smartoffice.shared.print.PrintBusHelper;

@Controller
public class EmployeeProfilePrint {
	@Autowired
	PrintBusHelper printBusHelper;
	@RequestMapping(value = "/emp-profile-print", method = RequestMethod.GET)

	public String downloadEmployeeProfile(Model model) {
		return printBusHelper.downloadHelper(model);
	}
}

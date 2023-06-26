package com.ss.smartoffice.soservice.transaction.eligibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("transaction/eligibility")
public class EligibilityService {
	
	@Autowired
	EligibilityHelper eligibilityHelper;

	ArrayList<String> knownActions = new ArrayList<String>(Arrays.asList("employee"));

	
	@GetMapping("/{action}")
	public void updateAttendanceEligibility(@PathVariable(value = "action")String action,
			@RequestParam(value = "empId", required = false) String empId) {
		if (knownActions.contains(action)) {
			eligibilityHelper.ProcessEligibility(action, empId);
		}else {
			throw new SmartOfficeException("Invalid Url");
		}
	}
	
}

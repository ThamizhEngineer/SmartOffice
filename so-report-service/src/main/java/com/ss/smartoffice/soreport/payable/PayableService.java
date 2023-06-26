package com.ss.smartoffice.soreport.payable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.smartoffice.shared.model.Payable;

import java.util.List;


@Controller
@RequestMapping("/reports/payable")
public class PayableService {

	@Autowired
	PayableRepo payableRepo;
	
	@GetMapping("/summary")
	public List<Payable> summary(@RequestParam(value = "poCode", required = false) String poCode,
			@RequestParam(value = "vendorId", required = false) String vendorId,
			@RequestParam(value = "vendorCode", required = false) String vendorCode,
			@RequestParam(value = "vendorName", required = false) String vendorName,
			@RequestParam(value = "monthNo", required = false) String monthNo,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "quarterName", required = false) String quarterName,
			@RequestParam(value = "countryName", required = false) String countryName){
		return payableRepo.summary(poCode, vendorId, vendorCode, vendorName, monthNo, year, quarterName, countryName);
	}
	
}

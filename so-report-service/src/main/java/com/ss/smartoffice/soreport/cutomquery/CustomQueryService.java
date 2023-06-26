package com.ss.smartoffice.soreport.cutomquery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.soreport.model.CustomOutput;
import com.ss.smartoffice.soreport.model.RevenuePerBu;
import com.ss.smartoffice.soreport.model.RevenuePerCustomer;

@RestController
@RequestMapping("/reports")
public class CustomQueryService {

	@Autowired
	CustomQueryHandler handler;
	
	@GetMapping("/revenue-per-customer")
	public List<RevenuePerCustomer> revenuePerCustomerApi(){
		return handler.fetchRevenuePerCustomer();
	}
	
	@GetMapping("/revenue-per-bu")
	public List<RevenuePerBu> revenuePerBuApi(){
		return handler.fetchRevenuePerBu();
	}
	
	@GetMapping("/custom/{key}")
	public List<CustomOutput> customQueryPicker(@PathVariable(value = "key") String key){
		return handler.queryPicker(key);
	}
}

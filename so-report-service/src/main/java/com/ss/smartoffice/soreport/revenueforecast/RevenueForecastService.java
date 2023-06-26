package com.ss.smartoffice.soreport.revenueforecast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports/forecasts")
public class RevenueForecastService {
	
	@Autowired
	RevenueForecastImpl revenueForecastImpl;
	
	@GetMapping
	public List<RevenueForecast> fetchRes(){
		return revenueForecastImpl.fetchResult();
	}

}

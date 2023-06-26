package com.ss.smartoffice.shared.healthCheck;

import java.io.BufferedInputStream;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.apiLogger.ApiLogger;
import com.ss.smartoffice.shared.apiLogger.ApiLoggerRepo;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path="health-check")
@Service
public class HealthCheck {
	
	@Autowired
	ApiLoggerRepo apiLoggerRepo;
	
	private static Logger log = LoggerFactory.getLogger(HealthCheck.class);

	@GetMapping("/_internal")
	public String healthCheck()throws SmartOfficeException{
		return "success";
	}
	
	
}

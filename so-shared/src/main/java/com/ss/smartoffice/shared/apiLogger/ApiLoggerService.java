package com.ss.smartoffice.shared.apiLogger;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;

@Service
public class ApiLoggerService {
	private static Logger log = LoggerFactory.getLogger(ApiLoggerService.class);
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	ApiLoggerRepo apiLoggerRepo;

	public void apiLogger(HttpServletRequest request,String userId) throws IllegalStateException, InterruptedException, IOException{					
			apiHit(request, userId);
	}

	public void apiHit(HttpServletRequest request,String userId) {
		try {				
			if(!request.getMethod().equals("GET")) {
				ApiLogger apiLogger = new ApiLogger();
				apiLogger.setOperationType(request.getMethod());
				if(request.getRequestURL().toString().length()<=200) {
					apiLogger.setUrl(request.getRequestURL().toString());
				}else {
					apiLogger.setUrl(request.getRequestURL().toString().substring(0, 200));
				}														
				apiLogger.setUserId(userId);
				apiLogger.setTimestamp(LocalDateTime.now());
				apiLogger.setMonth(LocalDateTime.now().getMonthValue());
				apiLogger.setYear(LocalDateTime.now().getYear());
//				apiLogger=apiLoggerRepo.save(apiLogger);
				try {
					if(IOUtils.toString(request.getInputStream()).length()<=2000) {
						apiLogger.setPayload(IOUtils.toString(request.getInputStream()));
					}else {
						apiLogger.setPayload(IOUtils.toString(request.getInputStream()).substring(0, 2000));
					}
				}catch (IllegalStateException e) {
					e.getSuppressed();
					log.error(e.getLocalizedMessage());		
				}catch (Exception e) {
					e.getSuppressed();
					log.error(e.getLocalizedMessage());		
				}	
				
				apiLoggerRepo.save(apiLogger);
			}					
		}catch (IllegalStateException e) {
			e.getSuppressed();
			log.error(e.getLocalizedMessage());		
		}catch (Exception e) {
			e.getSuppressed();
			log.error(e.getLocalizedMessage());		
		}	
	}
}

package com.ss.smartoffice.sonotificationservice.incidentsummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping(path="summary/incidents")
public class IncidentSummaryService {
	@Autowired
	CommonUtils commonutils;
	@Autowired
	IncidentSummaryRepo incidentSummaryRepo;
	
	
	@GetMapping("/count-recruitment")
	public Long countRecruit()throws SmartOfficeException{
		
		if(commonutils.isDir()) {
			
		return incidentSummaryRepo.incidentRecruitCount();
		}else {
			return null;
		}
		}
	@GetMapping("/count-training")
	public Long countTraining()throws SmartOfficeException{
		if(commonutils.isDir()) {
		return incidentSummaryRepo.incidentTrainingCount();
		}else {
			return null;
		}
		}
}

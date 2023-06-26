package com.ss.smartoffice.soservice.transaction.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction/incident/test-template")
public class IncidentTestTemplateService {
	
	@Autowired
	IncidentTestTemplateRepo incTestTemplateRepo;
	
	@Autowired
	IncidentTestTemplateQuestionRepo incTestTemplateQuestionRepo;
	
	@PostMapping
	public IncidentTestTemplate createNew(@RequestBody IncidentTestTemplate incidentTestTemplate) {
		return incTestTemplateRepo.save(incidentTestTemplate);
	}
	
	
	@PostMapping("/post-question")
	public IncidentTestTemplateQuestion createNewTestTemplateQuestion(@RequestBody IncidentTestTemplateQuestion incidentTestTemplateQuestion) {
		return incTestTemplateQuestionRepo.save(incidentTestTemplateQuestion);
	}
	

}

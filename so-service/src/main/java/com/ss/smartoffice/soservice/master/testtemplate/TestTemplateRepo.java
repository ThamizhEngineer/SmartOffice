package com.ss.smartoffice.soservice.master.testtemplate;





import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.incident.IncidentTestTemplate;



@Scope("prototype")
public interface TestTemplateRepo extends CrudRepository<TestTemplate, Integer>{
	
	Iterable<TestTemplate> findBytestTemplateName(String testTemplateName);
	
	Iterable<TestTemplate> findByduration (String duration);
}

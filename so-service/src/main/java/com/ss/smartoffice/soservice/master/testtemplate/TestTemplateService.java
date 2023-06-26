package com.ss.smartoffice.soservice.master.testtemplate;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;




@RestController
@RequestMapping("master/testtemplate")
@Scope("prototype")
public class TestTemplateService {
	
	@Autowired
	TestTemplateRepo testTemplateRepo;
	
	@Autowired
	TestTemplateCatagoryRepo testTemplateCatagoryRepo;
	
//	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<TestTemplate>getTestTemplate(
			@RequestParam(name="testTemplateName", required=false) String testTemplateName,
			@RequestParam(name="duration", required=false) String duration)throws Exception{
			boolean searchBytestTemplateName=false, searchByduration=false;
			if(testTemplateName!=null&&!testTemplateName.isEmpty()) {
				searchBytestTemplateName=true;
			}
			if(duration!=null&&!duration.isEmpty()) {
				searchByduration=true;
			}
			if(searchBytestTemplateName) {
				return testTemplateRepo.findBytestTemplateName(testTemplateName);
			}
			if(searchByduration) {
				return testTemplateRepo.findByduration(duration);
			}
			return testTemplateRepo.findAll();
	}
	
//	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<TestTemplate> getTestById(@PathVariable (value="id")Integer id)throws SmartOfficeException{
		return testTemplateRepo.findById(id);
	}
	
	@PostMapping
	public TestTemplate addTemplate(@RequestBody TestTemplate testTemplate) throws SmartOfficeException {
//		TestTemplate test = new TestTemplate();
//		if(!testTemplate.getTestTemplateName().isEmpty() || testTemplate.getTestTemplateName()!=null) {
//			test.setTestTemplateName(testTemplate.getTestTemplateName());
//		}
//		if(!testTemplate.getDuration().isEmpty() || testTemplate.getDuration()!=null) {
//		test.setDuration(testTemplate.getDuration());
//		}
//		if(!testTemplate.getNegativeMarking().isEmpty() || testTemplate.getNegativeMarking()!=null) {
//		test.setNegativeMarking(testTemplate.getNegativeMarking());
//		}
//		if(!testTemplate.getMarksPerQuestion().isEmpty() || testTemplate.getMarksPerQuestion()!=null) {
//		test.setMarksPerQuestion(testTemplate.getMarksPerQuestion());
//		}
//		if(!testTemplate.getPassPercentage().isEmpty() || testTemplate.getPassPercentage()!=null) {
//		test.setPassPercentage(testTemplate.getPassPercentage());
//		}
//		if(!testTemplate.getDescription().isEmpty() || testTemplate.getDescription()!=null) {
//		test.setDescription(testTemplate.getDescription());
//		}
//		if(!testTemplate.getTotalQuestions().isEmpty() || testTemplate.getTotalQuestions()!=null) {
//		test.setTotalQuestions(testTemplate.getTotalQuestions());
//		}
//		System.out.println("test"+test);
		return testTemplateRepo.save(testTemplate);
		
	}
	
	@PatchMapping("/{id}")
	public TestTemplate updateTemplate(@RequestBody TestTemplate testTemplate) throws SmartOfficeException{
		return testTemplateRepo.save(testTemplate);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTemplate(@PathVariable (value="id")int id) throws SmartOfficeException{
		testTemplateRepo.deleteById(id);
	}
		

}

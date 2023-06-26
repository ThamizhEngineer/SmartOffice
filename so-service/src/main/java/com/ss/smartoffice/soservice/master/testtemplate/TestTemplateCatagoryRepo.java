package com.ss.smartoffice.soservice.master.testtemplate;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface TestTemplateCatagoryRepo extends CrudRepository<TestTemplateCatagory, Integer>{

@Query("select x from com.ss.smartoffice.soservice.master.testtemplate.TestTemplateCatagory x where x.mTestTemplateId =:mTestTemplateId")
List<TestTemplateCatagory> findByMTestTemplateId(Integer mTestTemplateId);
}

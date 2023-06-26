package com.ss.smartoffice.soservice.master.testcategory;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TestCategoryRepo extends CrudRepository<TestCategory, Integer> {
	
	@Query("select testCategory from com.ss.smartoffice.soservice.master.testcategory.TestCategory testCategory where testCategory.testCategoryName=:testCategoryName")
	List<TestCategory> findByTestCategoryName(String testCategoryName);

}

package com.ss.smartoffice.soservice.master.expensecategory;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface ExpenseSubCategoryRepo extends CrudRepository<ExpenseSubCategory, Integer>{

	@Query("select ia from com.ss.smartoffice.soservice.master.expensecategory.ExpenseSubCategory ia where "
			+ "ifnull(LOWER(ia.categoryName),'') LIKE LOWER(CONCAT('%',ifnull(:categoryName,''),'%')) AND  "
			+ "ifnull(LOWER(ia.subCategoryName),'') LIKE LOWER(CONCAT('%',ifnull(:subCategoryName,''),'%'))")
Iterable<ExpenseSubCategory> fliter(String categoryName, String subCategoryName);

}

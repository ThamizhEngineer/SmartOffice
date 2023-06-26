package com.ss.smartoffice.soservice.master.expensecategory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("master/expense-category")
@Scope("prototype")
public class ExpenseService {

	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	ExpenseSubCategoryRepo expenseSubCategoryRepo;
	
//	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<ExpenseCategory> getExpenseCategories()throws SmartOfficeException{
		
		return expenseRepository.findAll();
		
	}
	
//	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<ExpenseCategory> getExpenseCategoryById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
		return expenseRepository.findById(id);
		
	}
//	@CrossOrigin(origins="*")
	@PostMapping
	public ExpenseCategory addExpenseCategory(@RequestBody ExpenseCategory expenseCategory)throws SmartOfficeException{
		return expenseRepository.save(expenseCategory);
		
	}
	
//	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	
	public ExpenseCategory updateExpenseCategory(@RequestBody ExpenseCategory expenseCategory) throws SmartOfficeException{
		if(expenseCategory.getId()!=null) {
		expenseRepository.save(expenseCategory);
		}else {
			throw new SmartOfficeException("Id value not Present");
		}
		return expenseCategory;
	}
//	@CrossOrigin(origins="*")
	@DeleteMapping("/{id}")
	
	public void deleteExpenseCategory(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		expenseRepository.deleteById(id);
	}
	
	
	@GetMapping("/sub-category")
	public Iterable<ExpenseSubCategory> getSubExpenseCategories()throws SmartOfficeException{
		return expenseSubCategoryRepo.findAll();	
	}
	
	
	@GetMapping("/{id}/sub-category")
	public Optional<ExpenseSubCategory> getSubExpenseCategoryById(@PathVariable(value="id")Integer id) throws SmartOfficeException{
		return expenseSubCategoryRepo.findById(id);	
	}

	@PostMapping("/sub-category")
	public ExpenseSubCategory addSubExpenseCategory(@RequestBody ExpenseSubCategory expenseSubCategory)throws SmartOfficeException{
		return expenseSubCategoryRepo.save(expenseSubCategory);
	}
	
	@PatchMapping("/sub-category/{id}")
	public ExpenseSubCategory updateSubExpenseCategory(@RequestBody ExpenseSubCategory expenseSubCategory)throws SmartOfficeException{
		return expenseSubCategoryRepo.save(expenseSubCategory);
	}
	
	@DeleteMapping("/{id}/sub-category")
	public void deleteSubExpenseCategory(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		expenseSubCategoryRepo.deleteById(id);
	}
	
	@GetMapping("/search")
	public Iterable<ExpenseSubCategory> advanceSearch
			(@RequestParam(value = "categoryName", required = false) String categoryName,
			@RequestParam(value = "subCategoryName", required = false) String subCategoryName)
			throws SmartOfficeException {
		return expenseSubCategoryRepo.fliter(categoryName,subCategoryName);
	}
}

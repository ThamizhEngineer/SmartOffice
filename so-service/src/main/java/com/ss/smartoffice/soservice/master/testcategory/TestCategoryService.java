package com.ss.smartoffice.soservice.master.testcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.Question;
import com.ss.smartoffice.soservice.master.Questions.QuestionRepo;

@Service
@RestController
@RequestMapping("/master/test-category")
public class TestCategoryService {

	@Autowired
	TestCategoryRepo testCategoryRepo;

	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	CommonUtils commonUtils;

	@GetMapping
	public Iterable<TestCategory> GetAllTestCategory() throws SmartOfficeException {
		return testCategoryRepo.findAll();
	}
	
	@GetMapping("/ids/_internal")
	public List<String> getIds() throws SmartOfficeException {
		List<String> ids = new ArrayList<String>();
		Iterable<TestCategory> i = testCategoryRepo.findAll();
		for (TestCategory testCategory : i) {
			ids.add(testCategory.getId().toString());
		}
		return ids;
	}

	@GetMapping("/{id}")
	public Optional<TestCategory> GetTestCategoryById(@PathVariable(value = "id") Integer id)
			throws SmartOfficeException {
		return testCategoryRepo.findById(id);
	}

	@PostMapping
	public TestCategory CreateTestCategory(@RequestBody TestCategory testCategory) {
		List<TestCategory> testCategoryFrom = (List<TestCategory>) testCategoryRepo
				.findByTestCategoryName(testCategory.getTestCategoryName());
		if (testCategoryFrom.isEmpty()) {
			testCategoryRepo.save(testCategory);

		} else {
			for (TestCategory obj : testCategoryFrom) {
				if (testCategory.getTestCategoryName().toLowerCase().equals(obj.getTestCategoryName().toLowerCase())) {
					throw new SmartOfficeException("Name Already Present");
				} else {
					testCategoryRepo.save(testCategory);
				}
			}

		}
		return testCategory;
	}

	@PatchMapping("/{id}")
	public TestCategory UpdateTestCategory(@PathVariable(value = "id") int id, @RequestBody TestCategory testCategory) {
		List<TestCategory> testCategoryFrom = (List<TestCategory>) testCategoryRepo
				.findByTestCategoryName(testCategory.getTestCategoryName());
		if (testCategoryFrom.isEmpty()) {
			testCategoryRepo.save(testCategory);

		} else {
			for (TestCategory obj : testCategoryFrom) {
				if (testCategory.getTestCategoryName().toLowerCase().equals(obj.getTestCategoryName().toLowerCase())) {
					throw new SmartOfficeException("Name Already Present");
				} else {
					testCategoryRepo.save(testCategory);
				}
			}

		}

		return testCategory;
	}
	
	@DeleteMapping("/{id}")
	public void deleteInterview(@PathVariable(value = "id") Integer id) {
		List<Question> questionList = questionRepo.findByTestCategoryId(Integer.toString(id));
		System.out.println(questionList);
		if(questionList.isEmpty()) {
			testCategoryRepo.deleteById(id);
		}else {
			throw new SmartOfficeException("Questions Present for this Test Category");
		}
	}
	

}

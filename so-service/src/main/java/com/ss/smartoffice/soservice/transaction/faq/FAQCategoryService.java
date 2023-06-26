package com.ss.smartoffice.soservice.transaction.faq;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ss.smartoffice.shared.model.faq.FAQ;
import com.ss.smartoffice.shared.model.faq.FAQCategory;
import com.ss.smartoffice.shared.model.faq.FAQCategoryRepo;
import com.ss.smartoffice.shared.model.faq.FAQRepo;

@RestController
@RequestMapping("/transaction/faq-category")
public class FAQCategoryService {

	@Autowired
	FAQCategoryRepo faqCategoryRepo;
	
	@Autowired
	FAQRepo faqRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping()
	public Iterable<FAQCategory> findAllFAQCategory()throws SmartOfficeException{
		return faqCategoryRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public FAQCategory findById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		FAQCategory faqCategoryFromDb = faqCategoryRepo.findById(id).get();
		faqCategoryFromDb.setFaqs(faqRepo.findBymFaqCatId(id.toString()));
		return faqCategoryFromDb;
	}
	
	@PostMapping
	public FAQCategory createFAQCategory(@RequestBody FAQCategory faqCategory) throws SmartOfficeException{
		return faqCategoryRepo.save(faqCategory);
	}
	@PatchMapping("/update")
	public FAQCategory updateFAQCategory(@RequestBody FAQCategory faqCategory) throws SmartOfficeException{
		return faqCategoryRepo.save(faqCategory);
	}
	
	@DeleteMapping("/{id}")
	public void deleteFAQCategory(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		List<FAQ> faqFromDB = faqRepo.findBymFaqCatId(id.toString());
		if(faqFromDB.isEmpty()) {
			faqCategoryRepo.deleteById(id);
		}else {
			throw new SmartOfficeException("FAQ Are Present In The Category");
		}
	}
}

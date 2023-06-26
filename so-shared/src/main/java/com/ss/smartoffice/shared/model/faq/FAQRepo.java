package com.ss.smartoffice.shared.model.faq;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FAQRepo extends CrudRepository<FAQ, Integer>  {

	@Query("select new com.ss.smartoffice.shared.model.faq.FAQ(faq.id,faq.mFaqCatId,faq.faqCatName,faq.faqCatDesc,faq.header) from com.ss.smartoffice.shared.model.faq.FAQ faq ")
	List<FAQ>findBySummaries();
	
	@Query("select new com.ss.smartoffice.shared.model.faq.FAQ(faq.id,faq.mFaqCatId,faq.faqCatName,faq.faqCatDesc,faq.header) from com.ss.smartoffice.shared.model.faq.FAQ faq where faq.mFaqCatId=:faqCatId")
	List<FAQ>findBymFaqCatId(String faqCatId);
}


package com.ss.smartoffice.shared.model.faq;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FAQLikeRepo extends CrudRepository<FAQLike, Integer> {

	@Query("select x from com.ss.smartoffice.shared.model.faq.FAQLike x where x.tFaqId =:faqId And x.createdBy=:empId")
	FAQLike findByTFaqIdAndCreatedBy(String faqId,String empId);
}

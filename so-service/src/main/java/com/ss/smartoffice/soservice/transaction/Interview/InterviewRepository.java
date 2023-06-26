package com.ss.smartoffice.soservice.transaction.Interview;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface InterviewRepository extends CrudRepository<Interview, Integer> {
	Page<Interview> findAll(Pageable pageable);
	
	Page<Interview> findByApplicantName(Pageable pageable,String applicantName);
		
	@Query("select interview from com.ss.smartoffice.soservice.transaction.Interview.Interview interview where interview.firstInterviewerId=:id OR interview.secondInterviewerId=:id OR interview.thirdInterviewerId=:id")
	List<Interview> findByInterviewerId(String id);
}
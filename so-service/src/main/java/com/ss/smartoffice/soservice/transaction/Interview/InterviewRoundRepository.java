package com.ss.smartoffice.soservice.transaction.Interview;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface InterviewRoundRepository extends CrudRepository<InterviewRound, Integer>  {
	
	List<InterviewRound> findByInterviewEmpIdOrderByRoundDateTimeAsc(String interviewEmpId);

}

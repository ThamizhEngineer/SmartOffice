package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobAccomodation;
@Scope("prototype")
public interface JobAccomodationRepository extends CrudRepository<JobAccomodation, Integer> {
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobAccomodation jobAccomodation where jobAccomodation.id= ?1")
	void delete(int id);
	List<JobAccomodation> findByJobId(String jobId);
}

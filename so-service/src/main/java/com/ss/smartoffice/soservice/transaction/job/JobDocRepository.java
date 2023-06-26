package com.ss.smartoffice.soservice.transaction.job;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobDoc;
@Scope("prototype")
public interface JobDocRepository extends CrudRepository<JobDoc, Integer> {
	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobDoc jobDoc where jobDoc.id= ?1")
	void delete(int id);
}

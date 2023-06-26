package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanProfile;

@Scope("prototype")
public interface JobPlanProfileRepository extends CrudRepository<JobPlanProfile, Integer>{
	
	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanProfile jobPlanProfile where jobPlanProfile.id= ?1")
	void delete(int id);
	
	@Query("select jobplan from com.ss.smartoffice.soservice.transaction.model.JobPlanProfile jobplan where jobplan.tJobId=:tJobId")
	List<JobPlanProfile>findByTJobId(Integer tJobId);
}

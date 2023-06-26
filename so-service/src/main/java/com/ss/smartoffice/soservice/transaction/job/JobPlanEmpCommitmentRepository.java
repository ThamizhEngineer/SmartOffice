package com.ss.smartoffice.soservice.transaction.job;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanEmpCommitment;
@Scope("prototype")
public interface JobPlanEmpCommitmentRepository extends CrudRepository<JobPlanEmpCommitment, Integer> {

	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanEmpCommitment jobPlanEmpCommitment where jobPlanEmpCommitment.id= ?1")
	void delete(int id);
	
	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanEmpCommitment jobPlanEmpCommitment where jobPlanEmpCommitment.jobPlanEmpId= ?1")
	void deleteByJobPlanEmpId(String id);
}


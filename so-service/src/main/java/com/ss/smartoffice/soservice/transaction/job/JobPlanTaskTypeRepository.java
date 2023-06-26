package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanTaskType;
@Scope("prototype")
public interface JobPlanTaskTypeRepository extends CrudRepository<JobPlanTaskType, Integer> {
	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanTaskType jobPlanStage where jobPlanStage.id= ?1")
	void delete(int id);
	List<JobPlanTaskType> findByJobId(String jobId);
}

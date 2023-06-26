package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanEmp;
@Scope("prototype")
public interface JobPlanTeamRepository extends CrudRepository<JobPlanEmp, Integer> {
	List<JobPlanEmp>findByJobId(Integer jobId);
	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanEmp jobPlanTeam where jobPlanTeam.id= ?1")
	void delete(int id);
}

package com.ss.smartoffice.soservice.transaction.job;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanEmpSkill;
@Scope("prototype")
public interface JobPlanEmpSkillRepository extends CrudRepository<JobPlanEmpSkill, Integer> {

	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanEmpSkill jobPlanEmpSkill where jobPlanEmpSkill.id= ?1")
	void delete(int id);
	
	@Modifying
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanEmpSkill jobPlanEmpSkill where jobPlanEmpSkill.tJobPlanEmpId= ?1")
	void deleteBytJobPlanEmpId(String id);
}

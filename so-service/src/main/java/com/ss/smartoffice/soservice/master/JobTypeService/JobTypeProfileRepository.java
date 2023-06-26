package com.ss.smartoffice.soservice.master.JobTypeService;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface JobTypeProfileRepository extends CrudRepository<JobTypeProfile, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.master.JobTypeService.JobTypeProfile jobTypeSkill where jobTypeSkill.id= ?1")
	void delete(int id);
	List<JobTypeProfile>findByJobtypeId(Integer jobtypeId);
}

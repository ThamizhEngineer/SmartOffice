package com.ss.smartoffice.soservice.master.JobTypeService;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface JobTypeTaskTypeRepository extends CrudRepository<JobTypeTaskType, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.master.JobTypeService.JobTypeTaskType jobTypeTaskType where jobTypeTaskType.id= ?1")
	void delete(int id);
	List<JobTypeTaskType>findByJobtypeId(Integer jobTypeId);
}

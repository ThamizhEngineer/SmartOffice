package com.ss.smartoffice.soservice.master.JobTypeService;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Scope("prototype")
public interface JobTypeMaterialRepository extends CrudRepository<JobTypeMaterial, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.master.JobTypeService.JobTypeMaterial jobTypeMaterial where jobTypeMaterial.id= ?1")
	void delete(int id);
	List<JobTypeMaterial>findByJobtypeId(Integer jobTypeId);
}

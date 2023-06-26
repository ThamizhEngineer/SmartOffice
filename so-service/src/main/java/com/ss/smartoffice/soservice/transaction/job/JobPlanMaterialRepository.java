package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanMaterial;
@Scope("prototype")
public interface JobPlanMaterialRepository extends CrudRepository<JobPlanMaterial, Integer>{
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanMaterial jobPlanMaterial where jobPlanMaterial.id= ?1")
	void delete(int id);
	
	List<JobPlanMaterial>findByJobId(String jobId);
}

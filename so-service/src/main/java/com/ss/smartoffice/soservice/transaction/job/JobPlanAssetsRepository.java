package com.ss.smartoffice.soservice.transaction.job;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobPlanAssets;
@Scope("prototype")
public interface JobPlanAssetsRepository extends CrudRepository<JobPlanAssets, Integer>{
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobPlanAssets jobPlanAssets where jobPlanAssets.id= ?1")
	void delete(int id);
}

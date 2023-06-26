package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.smartoffice.soservice.transaction.model.JobAsset;
import com.ss.smartoffice.soservice.transaction.model.JobAssetDocs;
@Scope("prototype")
public interface JobAssetDocsRepository extends CrudRepository<JobAssetDocs, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobAssetDocs jobAssetDocs where jobAssetDocs.id= ?1")
	void delete(int id);

	List<JobAssetDocs> findByJobId(String jobId);

}

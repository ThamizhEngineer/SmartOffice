package com.ss.smartoffice.soservice.master.JobTypeService;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
@Scope("prototype")
public interface JobTypeRepository extends PagingAndSortingRepository<JobType, Integer> {
	@Query("SELECT new com.ss.smartoffice.soservice.master.JobTypeService.JobType(j.id,j.jobTypeCode,j.jobTypeName,j.jobTypeTaskTypeCount,j.jobTypeMaterialCount,j.jobTypeProfileCount) from JobType j ")
    Iterable<JobType> findBySummaries();
	@Query("SELECT new com.ss.smartoffice.soservice.master.JobTypeService.JobType(j.id,j.jobTypeCode,j.jobTypeName,j.jobTypeTaskTypeCount,j.jobTypeMaterialCount,j.jobTypeProfileCount) from JobType j where ifnull(LOWER(j.jobTypeName),'') LIKE LOWER(CONCAT('%',ifnull(:jobTypeName,''),'%'))")
	List<JobType>findByJobTypeName(@Param("jobTypeName")String jobTypeName);
	
	 Page<JobType> findAll(Pageable pageable) ;
	

	
}

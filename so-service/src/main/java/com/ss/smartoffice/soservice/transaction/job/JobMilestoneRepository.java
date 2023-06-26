package com.ss.smartoffice.soservice.transaction.job;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ss.smartoffice.soservice.transaction.model.JobMilestone;
import java.lang.String;
import java.util.List;
@Scope("prototype")

public interface JobMilestoneRepository extends CrudRepository<JobMilestone, Integer> {

	
	
	@Query("select new com.ss.smartoffice.soservice.transaction.model.JobMilestone(j.id,j.jobId,j.jobCode,j.mJobTypeId,j.partnerId,j.endClientId,j.clientName,j.jobTypeName,j.mapLocationId,j.jobName,j.endClientName)"
			+ " from JobMilestone j where ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''),'%')) "
			+ "AND ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''), '%')) "
			+ "AND ifnull(LOWER(j.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%'))"
			+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))")
	Page<JobMilestone> findByJobMilestoneStatus(Pageable pageable,@Param("jobCode")String jobCode,@Param("partnerId")String partnerId,@Param("endClientId")String endClientId,@Param("mJobTypeId")String mJobTypeId);
	 List<JobMilestone> findByJobId(Integer jobid);
}


package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.soservice.transaction.model.Job;
import com.ss.smartoffice.soservice.transaction.model.JobEmployee;

@Scope("prototype")
public interface JobEmployeeRepository extends CrudRepository<JobEmployee, Integer>{
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.model.JobEmployee jobEmployee where jobEmployee.id= ?1")
	void delete(int id);
	
	@Query("select new com.ss.smartoffice.soservice.transaction.model.JobEmployee(j.id,j.tJobId,j.jobCode,j.mJobTypeId,j.partnerId,j.endClientId,j.clientName,j.jobTypeName,j.mapLocationId,j.jobName,j.endClientName)"
			+ " from JobEmployee j where ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''),'%')) "
			+ "AND ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''), '%')) "
			+ "AND ifnull(LOWER(j.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%'))"
			+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))")
	Page<JobEmployee> findByJobsForFeedback(Pageable pageable,@Param("jobCode")String jobCode,@Param("partnerId")String partnerId,@Param("endClientId")String endClientId,@Param("mJobTypeId")String mJobTypeId);
	 List<JobEmployee> findBytJobId(String tJobId);
	 
	 @Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.jobName,j.mJobTypeId,j.partnerId,j.endClientId,j.endClientName,j.clientName,j.jobTypeName)"
				+ " from Job j where ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''),'%')) "
				+ "AND ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''), '%')) "
				+ "AND ifnull(LOWER(j.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%'))"
				+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))"
				+ "AND ifnull(LOWER(j.jobTrackStatus),'') LIKE LOWER(CONCAT('%',ifnull(:jobTrackStatus,''), '%'))")
		Page<Job> findByJobFeedback(Pageable pageable,@Param("partnerId")String partnerId,@Param("jobCode")String jobCode,@Param("endClientId")String endClientId,@Param("mJobTypeId")String mJobTypeId,@Param("jobTrackStatus")String jobTrackStatus);
	 
	// JobEmployee findByEmployeeId(int id);
	 
	 List<JobEmployee> findByEmployeeId(int employeeId);
		
}

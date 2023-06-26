package com.ss.smartoffice.soservice.transaction.job;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.smartoffice.soservice.transaction.model.Job;

@Scope("prototype")
public interface JobsRepository extends PagingAndSortingRepository<Job, Integer> {
	
	//Refer constructor summaries
	@Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.jobName,j.mJobTypeId,j.mJobTypeName,j.siteLocationId,j.siteLocationName,j.partnerId,j.endClientId,j.endClientName,j.startDt,j.jobPlanStatus)"
			+ " from Job j where ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''),'%')) "
			+ "AND ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''), '%')) "
			+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))")
	Page<Job> findBySummaries(Pageable pageable, @Param("partnerId") String partnerId, @Param("jobCode") String jobCode,
			@Param("mJobTypeId") String mJobTypeId);

	@Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.mJobTypeId,j.partnerId,j.endClientId,j.startDt,j.jobPlanStatus,j.endClientName,j.clientName,j.jobTypeName,j.jobName)"
			+ " from Job j where ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''),'%')) "
			+ "AND ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''), '%')) "
			+ "AND ifnull(LOWER(j.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%'))"
			+ "AND ifnull(LOWER(j.jobPlanStatus),'') LIKE LOWER(CONCAT('%',ifnull(:jobPlanStatus,''), '%'))")
	Page<Job> findByJobLogisticsSummaries(Pageable pageable, @Param("jobCode") String jobCode,
			@Param("partnerId") String partnerId, @Param("endClientId") String endClientId,
			@Param("jobPlanStatus") String jobPlanStatus);

// For Job Track :	
	@Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.jobName,j.mJobTypeId,j.mJobTypeName,j.partnerId,j.endClientId,j.endClientName,j.startDt,j.jobPlanStatus)"
			+ " from Job j where ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''),'%')) "
			+ "AND ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''), '%')) "
			+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))"
			+ "AND ifnull(LOWER(j.jobPlanStatus),'') LIKE LOWER(CONCAT('%',ifnull(:jobPlanStatus,''), '%'))")
	Page<Job> findByTrackSummaries(Pageable pageable, @Param("partnerId") String partnerId,
			@Param("jobCode") String jobCode, @Param("mJobTypeId") String mJobTypeId,
			@Param("jobPlanStatus") String jobPlanStatus);

//For Job Report
	@Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.jobName,j.mJobTypeId,j.partnerId,j.endClientId,j.endClientName,j.clientName,j.jobTypeName)"
			+ " from Job j where ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''),'%')) "
			+ "AND ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''), '%')) "
			+ "AND ifnull(LOWER(j.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%'))"
			+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))"
			+ "AND ifnull(LOWER(j.jobPlanStatus),'') LIKE LOWER(CONCAT('%',ifnull(:jobPlanStatus,''), '%'))")
	Page<Job> findByJobReport(Pageable pageable, @Param("partnerId") String partnerId, @Param("jobCode") String jobCode,
			@Param("endClientId") String endClientId, @Param("mJobTypeId") String mJobTypeId,
			@Param("jobPlanStatus") String jobPlanStatus);

	@Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.jobName,j.mJobTypeId,j.siteLocationId,j.siteLocationName,j.partnerId,j.endClientId,j.endClientName,j.startDt,j.jobPlanStatus,j.jobTypeName,j.clientName)"
			+ " from Job j where ifnull(LOWER(j.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''),'%')) "
			+ "AND ifnull(LOWER(j.jobCode),'') LIKE LOWER(CONCAT('%',ifnull(:jobCode,''), '%')) "
			+ "AND ifnull(LOWER(j.jobName),'') LIKE LOWER(CONCAT('%',ifnull(:jobName,''), '%')) "
			+ "AND ifnull(LOWER(j.mJobTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:mJobTypeId,''), '%'))")
	List<Job> findByJobPlan(@Param("partnerId") String partnerId, @Param("jobCode") String jobCode,
			@Param("mJobTypeId") String mJobTypeId,@Param("jobName") String jobName);
	
	@Query("select jbs from com.ss.smartoffice.soservice.transaction.model.Job jbs where jbs.siteLocationId = :siteLocationId")
	List<Job> fetchBySiteLocationId(String siteLocationId);
	
	@Query("select new com.ss.smartoffice.soservice.transaction.model.Job(j.id,j.jobCode,j.jobName,j.mJobTypeId,j.mJobTypeName,j.siteLocationId,j.siteLocationName,j.partnerId,j.endClientId,j.endClientName,j.startDt,j.jobPlanStatus)from Job j where j.saleOrderId=:saleOrderId")
	List<Job> getBySaleOrderId(@Param("saleOrderId") String saleOrderId);

	List<Job> findBysaleOrderId(String saleOrderId);
}

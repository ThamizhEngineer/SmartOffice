package com.ss.smartoffice.shared.Processlog;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProcessLogRepository extends CrudRepository<ProcessLog, Integer>{

	@Query("select new com.ss.smartoffice.shared.Processlog.ProcessLog(pl.id,pl.processName,pl.processId,pl.activityName,pl.logDt,pl.userId,pl.processStatus,"
			+ "pl.remarks,pl.jobId,pl.employeeId,pl.partnerId,pl.endClientId,pl.jobBayId,pl.jobEquipmentId,pl.jobStageId,"
			+ "pl.attr1,pl.attr2,pl.attr3)"
			+ " from ProcessLog pl where ifnull(LOWER(pl.processName),'') LIKE LOWER(CONCAT('%',ifnull(:processName,''),'%')) "
			+ "AND ifnull(LOWER(pl.processId),'') LIKE LOWER(CONCAT('%',ifnull(:processId,''), '%')) "
			+"AND ifnull(LOWER(pl.activityName),'') LIKE LOWER(CONCAT('%',ifnull(:activityName,''), '%')) "
			
			+"AND ifnull(LOWER(pl.userId),'') LIKE LOWER(CONCAT('%',ifnull(:userId,''), '%')) "
			+"AND ifnull(LOWER(pl.processStatus),'') LIKE LOWER(CONCAT('%',ifnull(:processStatus,''), '%')) "
			+"AND ifnull(LOWER(pl.remarks),'') LIKE LOWER(CONCAT('%',ifnull(:remarks,''), '%')) "
			+"AND ifnull(LOWER(pl.jobId),'') LIKE LOWER(CONCAT('%',ifnull(:jobId,''), '%')) "
			+"AND ifnull(LOWER(pl.employeeId),'') LIKE LOWER(CONCAT('%',ifnull(:employeeId,''), '%')) "
			+"AND ifnull(LOWER(pl.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''), '%')) "
			+"AND ifnull(LOWER(pl.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%')) "
			+"AND ifnull(LOWER(pl.jobBayId),'') LIKE LOWER(CONCAT('%',ifnull(:jobBayId,''), '%')) "
			+"AND ifnull(LOWER(pl.jobEquipmentId),'') LIKE LOWER(CONCAT('%',ifnull(:jobEquipmentId,''), '%')) "
			+"AND ifnull(LOWER(pl.jobStageId),'') LIKE LOWER(CONCAT('%',ifnull(:jobStageId,''), '%')) "
			+"AND ifnull(LOWER(pl.attr1),'') LIKE LOWER(CONCAT('%',ifnull(:attr1,''), '%')) "
			+"AND ifnull(LOWER(pl.attr2),'') LIKE LOWER(CONCAT('%',ifnull(:attr2,''), '%')) "
			+ "AND ifnull(LOWER(pl.attr3),'') LIKE LOWER(CONCAT('%',ifnull(:attr3,''), '%'))")
	List<ProcessLog>findByProcessLog(@Param("processName")String processName,
			@Param("processId")String processId,
			@Param("activityName")String activityName,
			
			@Param("userId")String userId,
			@Param("processStatus")String processStatus,
			@Param("remarks")String remarks,
			@Param("jobId")String jobId,
			@Param("employeeId")String employeeId,
			@Param("partnerId")String partnerId,
			@Param("endClientId")String endClientId,
			@Param("jobBayId")String jobBayId,
			@Param("jobEquipmentId")String jobEquipmentId,
			@Param("jobStageId")String jobStageId,
			@Param("attr1")String attr1,
			@Param("attr2")String attr2,
			@Param("attr3")String attr3);
}

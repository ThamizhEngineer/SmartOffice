package com.ss.smartoffice.soservice.transaction.task;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.PagingAndSortingRepository;


@Scope("prototype")
public interface TaskRepo extends PagingAndSortingRepository<Task, Integer>  {
	
	@Query("SELECT new com.ss.smartoffice.soservice.transaction.task.Task (t.id,t.taskType,t.taskStatus,t.taskListId,t.assignedToUserId,t.isDelayed,t.isBlocked,"
			+ "t.jobId,t.endClientId,t.milestoneId,t.taskTypeId) FROM Task t where ifnull(LOWER(t.taskType),'') LIKE LOWER(CONCAT('%',ifnull(:taskType,''),'%'))"
			+ "AND ifnull(LOWER(t.taskStatus),'') LIKE LOWER(CONCAT('%',ifnull(:taskStatus,''), '%'))"
			+ "AND ifnull(LOWER(t.taskListId),'') LIKE LOWER(CONCAT('%',ifnull(:taskListId,''), '%'))"
			+ "AND ifnull(LOWER(t.assignedToUserId),'') LIKE LOWER(CONCAT('%',ifnull(:assignedToUserId,''), '%'))"
			+ "AND ifnull(LOWER(t.isDelayed),'') LIKE LOWER(CONCAT('%',ifnull(:isDelayed,''), '%'))"
			+ "AND ifnull(LOWER(t.isBlocked),'') LIKE LOWER(CONCAT('%',ifnull(:isBlocked,''), '%'))"
			+ "AND ifnull(LOWER(t.jobId),'') LIKE LOWER(CONCAT('%',ifnull(:jobId,''), '%'))"
			+ "AND ifnull(LOWER(t.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%'))"
			+ "AND ifnull(LOWER(t.milestoneId),'') LIKE LOWER(CONCAT('%',ifnull(:milestoneId,''), '%'))"
			+ "AND ifnull(LOWER(t.taskTypeId),'') LIKE LOWER(CONCAT('%',ifnull(:taskTypeId,''), '%'))")
	
	 Page<Task>findBySummaries(Pageable pageable,@Param("taskType")String taskType,@Param("taskStatus")String taskStatus,
			 @Param("taskListId")Integer taskListId,@Param("assignedToUserId")String assignedToUserId,
			 @Param("isDelayed")String isDelayed,@Param("isBlocked")String isBlocked,@Param("jobId")Integer jobId,
			 @Param("endClientId")Integer endClientId,@Param("milestoneId")Integer milestoneId,@Param("taskTypeId")Integer taskTypeId);
//List<Task>findByJobId(Integer jobId);
	
	List<Task> findByJobId(Integer jobId);
//	Page<Task> findByMonthAndYear(Pageable pageable, String year, String month);
//
//	Page<Task> findByMonth(Pageable pageable, String month);
//
//	Page<Task> findByYear(Pageable pageable, String year);


	@Transactional
	@Modifying
	@Query("DELETE from com.ss.smartoffice.soservice.transaction.task.Task task where task.id= ?1")
	void delete(int id);
	

}




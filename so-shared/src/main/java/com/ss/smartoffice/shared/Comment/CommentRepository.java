package com.ss.smartoffice.shared.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<Comment, Integer>{
	@Query("select new com.ss.smartoffice.shared.Comment.Comment(c.id,c.commentDesc,c.userId,c.jobId,"
			+ "c.employeeId,c.partnerId,c.endClientId,c.jobMilestoneId,c.jobTasklistId,c.taskId)"
			+ " from Comment c where ifnull(LOWER(c.commentDesc),'') LIKE LOWER(CONCAT('%',ifnull(:commentDesc,''),'%')) "
			+ "AND ifnull(LOWER(c.userId),'') LIKE LOWER(CONCAT('%',ifnull(:userId,''), '%')) "
			
			+"AND ifnull(LOWER(c.jobId),'') LIKE LOWER(CONCAT('%',ifnull(:jobId,''), '%')) "
			+"AND ifnull(LOWER(c.employeeId),'') LIKE LOWER(CONCAT('%',ifnull(:employeeId,''), '%')) "
			+"AND ifnull(LOWER(c.partnerId),'') LIKE LOWER(CONCAT('%',ifnull(:partnerId,''), '%')) "
			+"AND ifnull(LOWER(c.endClientId),'') LIKE LOWER(CONCAT('%',ifnull(:endClientId,''), '%')) "
			+"AND ifnull(LOWER(c.jobMilestoneId),'') LIKE LOWER(CONCAT('%',ifnull(:jobMilestoneId,''), '%')) "
			+"AND ifnull(LOWER(c.jobTasklistId),'') LIKE LOWER(CONCAT('%',ifnull(:jobTasklistId,''), '%')) "
			+"AND ifnull(LOWER(c.taskId),'') LIKE LOWER(CONCAT('%',ifnull(:taskId,''), '%')) ")
	List<Comment>findByComment(@Param("commentDesc")String commentDesc,
			@Param("userId")String userId,
			@Param("jobId")String jobId,
			@Param("employeeId")String employeeId,
			@Param("partnerId")String partnerId,
			@Param("endClientId")String endClientId,
			@Param("jobMilestoneId")String getJobMilestoneId,
			@Param("jobTasklistId")String getJobTasklistId,
			@Param("taskId")String taskId
			);
	List<Comment>findByTaskId(String taskId);
}

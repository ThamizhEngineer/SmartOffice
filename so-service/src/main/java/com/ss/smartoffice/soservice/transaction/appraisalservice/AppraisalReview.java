package com.ss.smartoffice.soservice.transaction.appraisalservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="t_appraisal_review")

@Data
public class AppraisalReview {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_appraisal_goal_id")
	private String appraisalGoalId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate reviewDt;
	private String reviewTypeCode;
	private String isOverDue;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate reviewSubDt;
//	@JsonFormat(pattern="yyyy-MM-dd")
//	private LocalDate reviewCompDt;
//	@JsonFormat(pattern="yyyy-MM-dd")
//	private LocalDate reviewAchvmt;
	private String comments;
	private String empReview;
	private String reviewScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=review_score_code)")
	private String reviewScoreName;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=review_score_code)")
	private String reviewStatusCode;
	//reviewCompStatus
	private String reviewCompStatus="CREATED";
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	
}

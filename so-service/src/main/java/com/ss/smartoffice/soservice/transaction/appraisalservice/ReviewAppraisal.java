package com.ss.smartoffice.soservice.transaction.appraisalservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="t_appraisal_review")

@Data
public class ReviewAppraisal {
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
	private String comments;
	private String empComments;
	private String empAcheivedTarget;
	private String empRemarks;
	private String reviewScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=review_score_code)")
	private String reviewScoreName;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=review_score_code)")
	private String reviewStatusCode;
	private String reviewCompStatus="CREATED";
	@Formula("(select goal.m_metric_id from t_appraisal_goal goal where goal.id=t_appraisal_goal_id)")
	private String metricId;
	@Formula("(select metric.metric_name from m_metric metric left join t_appraisal_goal goals on goals.m_metric_id=metric.id where goals.id=t_appraisal_goal_id)")
	private String metricName;
	@Formula("(select goal.n1_operator from t_appraisal_goal goal where goal.id=t_appraisal_goal_id)")
	private String n1Operator;
	@Formula("(select goal.n1_target from t_appraisal_goal goal where goal.id=t_appraisal_goal_id)")
	private String n1Target;
	@Formula("(select unit.disp_symbol from m_metric metric LEFT join s_units unit on unit.unit_code=metric.unit_type_id LEFT join t_appraisal_goal goals on goals.m_metric_id=metric.id where goals.id=t_appraisal_goal_id)")
	private String unitDispSymbol;
	@Formula("(select goals.t_appraisal_hdr_id from t_appraisal_goal goals where goals.id=t_appraisal_goal_id)")
	private String appraisalHdrId;
	@Formula("(select fiscalYear.fiscal_code from s_fiscal_year fiscalYear left join t_appraisal_hdr header on header.fy_id=fiscalYear.id left join t_appraisal_goal goals on header.id=goals.t_appraisal_hdr_id where goals.id=133)")
	private String fyCode;
	@Formula("(select header.m_emp_id from t_appraisal_hdr header left join t_appraisal_goal goals on goals.t_appraisal_hdr_id=header.id where goals.id=t_appraisal_goal_id)")
	private String empId;
	@Formula("(select emp.emp_name from m_employee emp left join t_appraisal_hdr header on header.m_emp_id=emp.id left join t_appraisal_goal goals on goals.t_appraisal_hdr_id=header.id where goals.id=t_appraisal_goal_id)")
	private String empName;
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

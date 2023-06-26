package com.ss.smartoffice.soservice.transaction.appraisalservice;

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
@Table(name="t_appraisal_goal")

@Data
public class AppraisalGoal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String priority;
	@Column(name="t_appraisal_hdr_id")
	private String appraisalHdrId;
	private String strategy;
	private String isTechnical;
	private String isOrgGoal="N";
	@Column(name="m_metric_id")
	private String metricId;
	@Formula("(select metric.better from m_metric metric where metric.id=m_metric_id)")
	private String operator;
	@Formula("(select unit.unit_name from m_metric metric LEFT join s_units unit on unit.unit_code=metric.unit_type_id where metric.id=m_metric_id)")
	private String unitName;
	@Formula("(select unit.disp_symbol from m_metric metric LEFT join s_units unit on unit.unit_code=metric.unit_type_id where metric.id=m_metric_id)")
	private String unitDisp;
	@Formula("(select unit.descp from m_metric metric LEFT join s_units unit on unit.unit_code=metric.unit_type_id where metric.id=m_metric_id)")
	private String unitDesc;
	@Formula("(select metric.metric_name from m_metric metric where metric.id=m_metric_id)")
	private String metricName;
	private String goalDesc;
	private String reviewIntervalType;
	private String empOperator;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=emp_operator)")
	private String empOperatorName;
	private String empTarget;
	@Column(name="n1_operator")
	private String n1Operator;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=n1_operator)")
	private String n1OperatorName;
	@Column(name="n1_target")
	private String n1Target;
	@Column(name="n1_target_remarks")
	private String n1TargetRemarks;
	@Column(name="n2_operator")
	private String n2Operator;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=n2_operator)")
	private String n2OperatorName;
	@Column(name="n2_target")
	private String n2Target;
	@Column(name="n2_target_remarks")
	private String n2TargetRemarks;
	private String empAchvmt;
	@Column(name="n1_emp_achvmt")
	private String n1EmpAchvmt;
	@Column(name="n2_emp_achvmt")
	private String n2EmpAchvmt;
	private String empAchvmtRemarks;
	@Column(name="n1_achvmt_remarks")
	private String n1AchvmtRemarks;
	@Column(name="n2_achvmt_remarks")
	private String n2AchvmtRemarks;
	private String empScoreCode;
	private String isEmpAccept="Y";
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=emp_score_code)")
	private String empScoreName;
	@Column(name="n1_score_code")
	private String n1ScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=n1_score_code)")
	private String n1ScoreName;
	@Column(name="n2_score_code")
	private String n2ScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=n2_score_code)")
	private String n2ScoreName;
	private String finalScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=final_score_code)")
	private String finalScoreName;
	private String finalOperator;
	private String finalTarget;
	private String finalAchvmt;
	private String goalTargetStatusCode;
	private String goalAchvmtStatusCode;
	
	private Integer currSkillCount;
	private Integer expectedSkillCount;
	private Integer currSkillScore;
	private Integer expectedSkillScore;
	@Column(name="skill_score_emp")
	private Integer skillScoreFromEmp;
	@Column(name="skill_score_n1")
	private Integer skillScoreFromN1;
	private Integer skillScoreFinal;
	private String isSkillGoal = "N";
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_appraisal_goal_id")
	private List<AppraisalReview> reviews;
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

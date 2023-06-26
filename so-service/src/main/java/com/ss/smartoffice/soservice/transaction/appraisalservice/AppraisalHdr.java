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
import com.ss.smartoffice.shared.model.employee.EmployeeSkill;

import lombok.Data;

@Entity
@Table(name="t_appraisal_hdr")

@Data
public class AppraisalHdr {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_emp_id")
	private String empId;
	private String fyId;
	@Formula("(select fyYear.fiscal_code from s_fiscal_year fyYear WHERE fyYear.id=fy_id)")
	private String fyCode;
	@Formula("(SELECT emp.n1_emp_id from m_employee emp where emp.id=m_emp_id)")
	private String n1EmpId;
	@Formula("(SELECT emp.n2_emp_id from m_employee emp where emp.id=m_emp_id)")
	private String n2EmpId;
	@Column(name="settle_user_group_id")
	private String settleUserGroupId;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id= settle_user_group_id)")
	private String settleUserGroupName;
	
	@Column(name="dir_user_group_id")
	private String dirUserGroupId;
	
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id= dir_user_group_id)")
	private String dirUserGroupName;
	
	private  String dirEmpId;
	@Formula("(select emp.first_name from m_employee emp where emp.id=dir_emp_id)")
	private  String dirEmpName;
	
	@Column(name="settle_user_group_2_id")
	private String settleUserGroup2Id;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id= settle_user_group_2_id)")
	private String settleUserGroup2Name;
	private  String settleEmpId;
	@Formula("(select emp.first_name from m_employee emp where emp.id=settle_emp_id)")
	private  String settleEmpName;
	@Column(name="settle_emp_2_id")
	private  String settleEmp2Id;
	@Formula("(select emp.first_name from m_employee emp where emp.id=settle_emp_2_id)")
	private  String settleEmp2Name;
	private String officeId;
	private String designationId;
	private String appraisalTargetStatusCode;
	private String appraisalAchvmtStatusCode;
	@Formula("(select m.emp_code from m_employee m where m.id=m_emp_id)")
	private String empCode;
	@Formula("(select m.first_name from m_employee m where m.id=m_emp_id)")
	private String empFName;
	@Formula("(select m.last_name from m_employee m where m.id=m_emp_id)")
	private String empLName;
	@Formula("(select m.emp_name from m_employee m where m.id=m_emp_id)")
	private String empName;
	@Formula("(SELECT emp.first_name from m_employee emp LEFT join m_employee emp2 on emp.id=emp2.n1_emp_id where emp2.id=m_emp_id)")
	private String n1EmpFName;
	@Formula("(SELECT emp.last_name from m_employee emp LEFT join m_employee emp2 on emp.id=emp2.n1_emp_id where emp2.id=m_emp_id)")
	private String n1EmpLName;
	@Formula("(SELECT emp.first_name from m_employee emp LEFT join m_employee emp2 on emp.id=emp2.n2_emp_id where emp2.id=m_emp_id)")
	private String n2EmpFName;
	@Formula("(SELECT emp.last_name from m_employee emp LEFT join m_employee emp2 on emp.id=emp2.n2_emp_id where emp2.id=m_emp_id)")
	private String n2EmpLName;
	@Formula("(SELECT office.office_name from m_office office LEFT join m_employee emp on office.id=emp.m_office_id where emp.id=m_emp_id)")
	private String officeName;
	@Formula("(select fiscal.fiscal_code from s_fiscal_year fiscal where fiscal.id=fy_id)")
	private String fyName;
	@Formula("(SELECT emp.desig_name from m_employee emp where emp.id=m_emp_id)")
	private String designationName;
	@Column(name="emp_nxt_pos1")
	private String empNxtPos1;
	@Column(name="emp_nxt_pos2")
	private String empNxtPos2;
	@Column(name="emp_career_move1")
	private String empCareerMove1;
	@Column(name="emp_career_move2")
	private String empCareerMove2;
	@Column(name="n1_nxt_pos1")
	private String n1NxtPos1;
	@Column(name="n1_nxt_pos2")
	private String n1NxtPos2;
	@Column(name="n1_career_pos1")
	private String n1CareerPos1;
	@Column(name="n1_career_pos2")
	private String n1CareerPos2;
	private String empMobility;
	@Column(name="n1_mobility")
	private String n1Mobility;
	private String techScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=tech_score_code)")
	private String techScoreName;
	private String behaveScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=behave_score_code)")
	private String behaveScoreName;
	private String overalScoreCode;
	@Formula("(select config.config_dtl_name from s_config_dtl config where config.config_dtl_code=overal_score_code)")
	private String overalScoreName;
	private String empAreaOfStrength;
	@Column(name="n1_area_of_strength")
	private String n1AreaOfStrength;
	private String empAreaOfDev;
	@Column(name="n1_area_of_dev")
	private String n1AreaOfDev;
	
	
	@Column(name="emp_target_sub_dt")
	private LocalDateTime empTargetSubDt;
	@Column(name="n1_target_review_dt")
	private LocalDateTime n1TargetReviewDt;
	@Column(name="n2_target_review_dt")
	private LocalDateTime n2TargetReviewDt;
	@Column(name="emp_target_accept_dt")
	private LocalDateTime empTargetAcceptDt;
	@Column(name="emp_target_reject_dt")
	private LocalDateTime empTargetRejectDt;
	@Column(name="n1_return_comment")
	private String n1ReturnComment;
	@Column(name="escalat_reason")
	private String escalatReason;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_appraisal_hdr_id")
	private List<AppraisalSkillGoal> skillObjectives;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_appraisal_hdr_id")
	private List<AppraisalGoal> goals;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_appraisal_hdr_id")
	private List<AppraisalDev> devActions;
	
}

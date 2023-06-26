package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_job_plan_emp_skill")

@Scope("prototype")
public class JobPlanEmpSkill {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_plan_emp_id")
	private String tJobPlanEmpId;
	private String isMatched;
	private String mProfileLineId;	
	
	@Formula("(select line.service_id from  m_profile_line line left join t_job_plan_emp_skill skill on skill.m_profile_line_id = line.id where skill.id=m_profile_line_id)")
	private String serviceId;

	@Formula("(select service.service_name from m_service service left join m_profile_line line on service.id = line.service_id left join t_job_plan_emp_skill skill on skill.m_profile_line_id = line.id where skill.id=id)")
	private String serviceName;
	
	@Formula("(select line.product_id from  m_profile_line line left join t_job_plan_emp_skill skill on skill.m_profile_line_id = line.id where skill.id=m_profile_line_id)")
	private String productId;
	@Formula("(select product.material_name from m_material product left join m_profile_line line on product.id = line.product_id left join t_job_plan_emp_skill skill on skill.m_profile_line_id = line.id where skill.id=id)")
	private String productName;
	
	@Formula("(select line.skill_level from  m_profile_line line left join t_job_plan_emp_skill skill on skill.m_profile_line_id = line.id where skill.id=m_profile_line_id)")
	private String skillLevel;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public JobPlanEmpSkill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPlanEmpSkill(Integer id, String tJobPlanEmpId, String isMatched, String mProfileLineId, String serviceId,
			String serviceName, String productId, String productName, String skillLevel, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tJobPlanEmpId = tJobPlanEmpId;
		this.isMatched = isMatched;
		this.mProfileLineId = mProfileLineId;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.productId = productId;
		this.productName = productName;
		this.skillLevel = skillLevel;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "JobPlanEmpSkill [id=" + id + ", tJobPlanEmpId=" + tJobPlanEmpId + ", isMatched=" + isMatched
				+ ", mProfileLineId=" + mProfileLineId + ", serviceId=" + serviceId + ", serviceName=" + serviceName
				+ ", productId=" + productId + ", productName=" + productName + ", skillLevel=" + skillLevel
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettJobPlanEmpId() {
		return tJobPlanEmpId;
	}

	public void settJobPlanEmpId(String tJobPlanEmpId) {
		this.tJobPlanEmpId = tJobPlanEmpId;
	}

	public String getIsMatched() {
		return isMatched;
	}

	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}

	public String getmProfileLineId() {
		return mProfileLineId;
	}

	public void setmProfileLineId(String mProfileLineId) {
		this.mProfileLineId = mProfileLineId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	
	
}

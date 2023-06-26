package com.ss.smartoffice.soservice.transaction.ProfileFinderService;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name=("t_pf_emp_skill"))

@Scope("prototype")
public class MatchedSkill {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="match_employee_id")
	private String matchEmployeeId;
	private String mProfileLineId;
	private String mProductId;
	private String productName;
	private String mServiceId;
	private String serviceName;
	private String skillLevel;
	private String isMatched;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public MatchedSkill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatchedSkill(Integer id, String matchEmployeeId, String mProfileLineId, String mProductId,
			String productName, String mServiceId, String serviceName, String skillLevel, String isMatched,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.matchEmployeeId = matchEmployeeId;
		this.mProfileLineId = mProfileLineId;
		this.mProductId = mProductId;
		this.productName = productName;
		this.mServiceId = mServiceId;
		this.serviceName = serviceName;
		this.skillLevel = skillLevel;
		this.isMatched = isMatched;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMatchEmployeeId() {
		return matchEmployeeId;
	}
	public void setMatchEmployeeId(String matchEmployeeId) {
		this.matchEmployeeId = matchEmployeeId;
	}
	public String getmProfileLineId() {
		return mProfileLineId;
	}
	public void setmProfileLineId(String mProfileLineId) {
		this.mProfileLineId = mProfileLineId;
	}
	public String getmProductId() {
		return mProductId;
	}
	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getmServiceId() {
		return mServiceId;
	}
	public void setmServiceId(String mServiceId) {
		this.mServiceId = mServiceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getIsMatched() {
		return isMatched;
	}
	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
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
	@Override
	public String toString() {
		return "MatchedSkill [id=" + id + ", matchEmployeeId=" + matchEmployeeId + ", mProfileLineId=" + mProfileLineId
				+ ", mProductId=" + mProductId + ", productName=" + productName + ", mServiceId=" + mServiceId
				+ ", serviceName=" + serviceName + ", skillLevel=" + skillLevel + ", isMatched=" + isMatched
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}

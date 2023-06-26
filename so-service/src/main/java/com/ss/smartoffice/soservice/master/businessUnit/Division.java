package com.ss.smartoffice.soservice.master.businessUnit;

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
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="m_division")

@Scope("prototype")
public class Division extends BaseEntity{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_bu_id")
	private Integer mBuId;

	@Formula("(select bu.bu_name FROM m_bu bu where bu.id=m_bu_id)")
	private String mBuisnessName;
	private String divisionName;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_division_id")
	private List<DivisionGood> productNames;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_division_id")
	private List<DivisionService> serviceNames;
	
	private String remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Division() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Division(Integer id, Integer mBuId, String mBuisnessName, String divisionName) {
		super();
		this.id = id;
		this.mBuId = mBuId;
		this.mBuisnessName = mBuisnessName;
		this.divisionName = divisionName;
	}
	public Division(Integer id, Integer mBuId, String mBuisnessName, String divisionName,
			List<DivisionGood> productNames, List<DivisionService> serviceNames, String remarks, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.mBuId = mBuId;
		this.mBuisnessName = mBuisnessName;
		this.divisionName = divisionName;
		this.productNames = productNames;
		this.serviceNames = serviceNames;
		this.remarks = remarks;
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
	public Integer getmBuId() {
		return mBuId;
	}
	public void setmBuId(Integer mBuId) {
		this.mBuId = mBuId;
	}
	public String getmBuisnessName() {
		return mBuisnessName;
	}
	public void setmBuisnessName(String mBuisnessName) {
		this.mBuisnessName = mBuisnessName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public List<DivisionGood> getProductNames() {
		return productNames;
	}
	public void setProductNames(List<DivisionGood> productNames) {
		this.productNames = productNames;
	}
	public List<DivisionService> getServiceNames() {
		return serviceNames;
	}
	public void setServiceNames(List<DivisionService> serviceNames) {
		this.serviceNames = serviceNames;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "Division [id=" + id + ", mBuId=" + mBuId + ", mBuisnessName=" + mBuisnessName + ", divisionName="
				+ divisionName + ", productNames=" + productNames + ", serviceNames=" + serviceNames + ", remarks="
				+ remarks + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	

	



	
}
	

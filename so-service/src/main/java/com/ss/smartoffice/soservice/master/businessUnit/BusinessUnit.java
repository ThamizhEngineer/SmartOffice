package com.ss.smartoffice.soservice.master.businessUnit;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="m_bu")

@Scope("prototype")
public class BusinessUnit extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String buName;
	private String buCode;
	@Formula("(select COUNT(*) from m_division division where division.m_bu_id=id)")
	private String noOfDivisions;
	private String hasProducts;
	private String hasServices;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_bu_id")
	private List<Division> divisions;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public BusinessUnit() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BusinessUnit [id=" + id + ", buName=" + buName + ", buCode=" + buCode + ", noOfDivisions="
				+ noOfDivisions + ", hasProducts=" + hasProducts + ", hasServices=" + hasServices + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", divisions=" + divisions
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	public BusinessUnit(Integer id, String buName, String buCode, String noOfDivisions, String hasProducts,
			String hasServices, String isEnabled, String createdBy, String modifiedBy, List<Division> divisions,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.buName = buName;
		this.buCode = buCode;
		this.noOfDivisions = noOfDivisions;
		this.hasProducts = hasProducts;
		this.hasServices = hasServices;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.divisions = divisions;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBuName() {
		return buName;
	}
	public void setBuName(String buName) {
		this.buName = buName;
	}
	public String getBuCode() {
		return buCode;
	}
	public void setBuCode(String buCode) {
		this.buCode = buCode;
	}
	public String getNoOfDivisions() {
		return noOfDivisions;
	}
	public void setNoOfDivisions(String noOfDivisions) {
		this.noOfDivisions = noOfDivisions;
	}
	public String getHasProducts() {
		return hasProducts;
	}
	public void setHasProducts(String hasProducts) {
		this.hasProducts = hasProducts;
	}
	public String getHasServices() {
		return hasServices;
	}
	public void setHasServices(String hasServices) {
		this.hasServices = hasServices;
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
	public List<Division> getDivisions() {
		return divisions;
	}
	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
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

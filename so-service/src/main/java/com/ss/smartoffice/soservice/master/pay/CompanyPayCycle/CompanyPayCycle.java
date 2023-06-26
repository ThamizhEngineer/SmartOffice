package com.ss.smartoffice.soservice.master.pay.CompanyPayCycle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="m_comp_pay_cycle")

public class CompanyPayCycle extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer fiscalYearId;
	private String payCycleCode;
	private String payCycleType;
	private String isActive;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate startDt;
	 private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="m_comp_pay_cycle_id")
	private List<CompanyPayCycleLines> companyPayCycleLines;
	public CompanyPayCycle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyPayCycle(Integer id, Integer fiscalYearId, String payCycleCode, String payCycleType, String isActive,
			LocalDate startDt, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt, List<CompanyPayCycleLines> companyPayCycleLines) {
		super();
		this.id = id;
		this.fiscalYearId = fiscalYearId;
		this.payCycleCode = payCycleCode;
		this.payCycleType = payCycleType;
		this.isActive = isActive;
		this.startDt = startDt;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.companyPayCycleLines = companyPayCycleLines;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(Integer fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}
	public String getPayCycleCode() {
		return payCycleCode;
	}
	public void setPayCycleCode(String payCycleCode) {
		this.payCycleCode = payCycleCode;
	}
	public String getPayCycleType() {
		return payCycleType;
	}
	public void setPayCycleType(String payCycleType) {
		this.payCycleType = payCycleType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public LocalDate getStartDt() {
		return startDt;
	}
	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
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
	public List<CompanyPayCycleLines> getCompanyPayCycleLines() {
		return companyPayCycleLines;
	}
	public void setCompanyPayCycleLines(List<CompanyPayCycleLines> companyPayCycleLines) {
		this.companyPayCycleLines = companyPayCycleLines;
	}
	@Override
	public String toString() {
		return "CompanyPayCycle [id=" + id + ", fiscalYearId=" + fiscalYearId + ", payCycleCode=" + payCycleCode
				+ ", payCycleType=" + payCycleType + ", isActive=" + isActive + ", startDt=" + startDt + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", companyPayCycleLines=" + companyPayCycleLines + "]";
	}
	
    
}

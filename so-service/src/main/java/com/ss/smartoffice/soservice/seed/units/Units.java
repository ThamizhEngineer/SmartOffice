package com.ss.smartoffice.soservice.seed.units;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Scope("prototype")
@Table(name="s_units")
public class Units {
	@Id
	private String unitCode;
	private String unitName;
	private String dispSymbol;
	private String descp;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Units() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Units(String unitCode, String unitName, String dispSymbol, String descp, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.unitCode = unitCode;
		this.unitName = unitName;
		this.dispSymbol = dispSymbol;
		this.descp = descp;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDispSymbol() {
		return dispSymbol;
	}
	public void setDispSymbol(String dispSymbol) {
		this.dispSymbol = dispSymbol;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
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
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "Units [unitCode=" + unitCode + ", unitName=" + unitName + ", dispSymbol=" + dispSymbol + ", descp="
				+ descp + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}	
	

}

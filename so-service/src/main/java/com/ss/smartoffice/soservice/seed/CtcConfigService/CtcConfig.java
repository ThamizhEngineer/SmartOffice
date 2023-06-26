package com.ss.smartoffice.soservice.seed.CtcConfigService;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="s_ctc_config")

@Scope("prototype")
public class CtcConfig extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fiscalYearId;
	private String fiscalYearCode;
	private String headerConfigCode;
	private String configCode;
	private String configValue;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public CtcConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CtcConfig(int id, String fiscalYearId, String fiscalYearCode, String headerConfigCode, String configCode,
			String configValue, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.fiscalYearId = fiscalYearId;
		this.fiscalYearCode = fiscalYearCode;
		this.headerConfigCode = headerConfigCode;
		this.configCode = configCode;
		this.configValue = configValue;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}
	public String getFiscalYearCode() {
		return fiscalYearCode;
	}
	public void setFiscalYearCode(String fiscalYearCode) {
		this.fiscalYearCode = fiscalYearCode;
	}
	public String getHeaderConfigCode() {
		return headerConfigCode;
	}
	public void setHeaderConfigCode(String headerConfigCode) {
		this.headerConfigCode = headerConfigCode;
	}
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
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
		return "CtcConfig [id=" + id + ", fiscalYearId=" + fiscalYearId + ", fiscalYearCode=" + fiscalYearCode
				+ ", headerConfigCode=" + headerConfigCode + ", configCode=" + configCode + ", configValue="
				+ configValue + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}

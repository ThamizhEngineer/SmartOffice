package com.ss.smartoffice.shared.model;
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
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="s_config_dtl")

@Scope("prototype")
public class Config extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String configDtlCode;
	private String configDtlName;
	private String configDtlValue;
	private String attrib1;
	private String attrib2;
	private String attrib3;
	private String remarks;

	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@Column (name="s_config_header_id")
	private int configHeaderId;
	
	@Formula("(SELECT config.config_header_code FROM s_config_header config WHERE config.id = s_config_header_id)")
	private String configHeaderCode;
	@Formula("(SELECT config.config_header_name from s_config_header config WHERE config.id = s_config_header_id)")
	private String configHeaderName;
	@Formula("(SELECT config.remarks from s_config_header config WHERE config.id = s_config_header_id)")
	private String configHeaderRemarks;
	@Formula("(SELECT config.is_enabled from s_config_header config WHERE config.id = s_config_header_id)")
	private String configHeaderIsEnabled;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Config(int id, int configHeaderId, String configDtlCode, String configDtlName, String configDtlValue,
			String attrib1, String attrib2, String attrib3, String remarks, String isEnabled, String createdBy,
			String modifiedBy, String configHeaderCode, String configHeaderName, String configHeaderRemarks,
			String configHeaderIsEnabled, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.configHeaderId = configHeaderId;
		this.configDtlCode = configDtlCode;
		this.configDtlName = configDtlName;
		this.configDtlValue = configDtlValue;
		this.attrib1 = attrib1;
		this.attrib2 = attrib2;
		this.attrib3 = attrib3;
		this.remarks = remarks;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.configHeaderCode = configHeaderCode;
		this.configHeaderName = configHeaderName;
		this.configHeaderRemarks = configHeaderRemarks;
		this.configHeaderIsEnabled = configHeaderIsEnabled;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConfigHeaderId() {
		return configHeaderId;
	}

	public void setConfigHeaderId(int configHeaderId) {
		this.configHeaderId = configHeaderId;
	}

	public String getConfigDtlCode() {
		return configDtlCode;
	}

	public void setConfigDtlCode(String configDtlCode) {
		this.configDtlCode = configDtlCode;
	}

	public String getConfigDtlName() {
		return configDtlName;
	}

	public void setConfigDtlName(String configDtlName) {
		this.configDtlName = configDtlName;
	}

	public String getConfigDtlValue() {
		return configDtlValue;
	}

	public void setConfigDtlValue(String configDtlValue) {
		this.configDtlValue = configDtlValue;
	}

	public String getAttrib1() {
		return attrib1;
	}

	public void setAttrib1(String attrib1) {
		this.attrib1 = attrib1;
	}

	public String getAttrib2() {
		return attrib2;
	}

	public void setAttrib2(String attrib2) {
		this.attrib2 = attrib2;
	}

	public String getAttrib3() {
		return attrib3;
	}

	public void setAttrib3(String attrib3) {
		this.attrib3 = attrib3;
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

	public String getConfigHeaderCode() {
		return configHeaderCode;
	}

	public void setConfigHeaderCode(String configHeaderCode) {
		this.configHeaderCode = configHeaderCode;
	}

	public String getConfigHeaderName() {
		return configHeaderName;
	}

	public void setConfigHeaderName(String configHeaderName) {
		this.configHeaderName = configHeaderName;
	}

	public String getConfigHeaderRemarks() {
		return configHeaderRemarks;
	}

	public void setConfigHeaderRemarks(String configHeaderRemarks) {
		this.configHeaderRemarks = configHeaderRemarks;
	}

	public String getConfigHeaderIsEnabled() {
		return configHeaderIsEnabled;
	}

	public void setConfigHeaderIsEnabled(String configHeaderIsEnabled) {
		this.configHeaderIsEnabled = configHeaderIsEnabled;
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
		return "Config [id=" + id + ", configHeaderId=" + configHeaderId + ", configDtlCode=" + configDtlCode
				+ ", configDtlName=" + configDtlName + ", configDtlValue=" + configDtlValue + ", attrib1=" + attrib1
				+ ", attrib2=" + attrib2 + ", attrib3=" + attrib3 + ", remarks=" + remarks + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", configHeaderCode=" + configHeaderCode
				+ ", configHeaderName=" + configHeaderName + ", configHeaderRemarks=" + configHeaderRemarks
				+ ", configHeaderIsEnabled=" + configHeaderIsEnabled + ", createdDt=" + createdDt + ", modifiedDt="
				+ modifiedDt + "]";
	}

	
	
	
	

}

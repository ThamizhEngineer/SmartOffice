package com.ss.smartoffice.soservice.transaction.appraisalservice;

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

@Entity
@Table(name="t_appraisal_setting")

@Scope("prototype")
public class AppraisalSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fiscalYearId;
	private String fiscalCode;
	private String minOrgGoalsUm;
	private String minOrgGoalsMm;
	private String minOrgGoalsOthers;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public AppraisalSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppraisalSetting(int id, String fiscalYearId, String fiscalCode, String minOrgGoalsUm, String minOrgGoalsMm,
			String minOrgGoalsOthers, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.fiscalYearId = fiscalYearId;
		this.fiscalCode = fiscalCode;
		this.minOrgGoalsUm = minOrgGoalsUm;
		this.minOrgGoalsMm = minOrgGoalsMm;
		this.minOrgGoalsOthers = minOrgGoalsOthers;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "AppraisalSetting [id=" + id + ", fiscalYearId=" + fiscalYearId + ", fiscalCode=" + fiscalCode
				+ ", minOrgGoalsUm=" + minOrgGoalsUm + ", minOrgGoalsMm=" + minOrgGoalsMm + ", minOrgGoalsOthers="
				+ minOrgGoalsOthers + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + "]";
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

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getMinOrgGoalsUm() {
		return minOrgGoalsUm;
	}

	public void setMinOrgGoalsUm(String minOrgGoalsUm) {
		this.minOrgGoalsUm = minOrgGoalsUm;
	}

	public String getMinOrgGoalsMm() {
		return minOrgGoalsMm;
	}

	public void setMinOrgGoalsMm(String minOrgGoalsMm) {
		this.minOrgGoalsMm = minOrgGoalsMm;
	}

	public String getMinOrgGoalsOthers() {
		return minOrgGoalsOthers;
	}

	public void setMinOrgGoalsOthers(String minOrgGoalsOthers) {
		this.minOrgGoalsOthers = minOrgGoalsOthers;
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

}

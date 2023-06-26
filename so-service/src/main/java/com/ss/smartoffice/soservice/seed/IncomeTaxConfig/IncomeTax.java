package com.ss.smartoffice.soservice.seed.IncomeTaxConfig;
import java.time.LocalDateTime;
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
@Table(name="s_it_config")

@Scope("prototype")
public class IncomeTax extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String ageCode;
	private Long fromAmt;
	private Long toAmt;
	private Float taxPercent;
	private Float eduCessPercent;
	private Float splEduCessPercent;
	@Formula("(SELECT fiscal.id from s_fiscal_year fiscal where fiscal.id= fiscal_year_id )")
	private String fiscalYearId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public IncomeTax() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IncomeTax(int id, String ageCode, Long fromAmt, Long toAmt, Float taxPercent, Float eduCessPercent,
			Float splEduCessPercent, String fiscalYearId, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.ageCode = ageCode;
		this.fromAmt = fromAmt;
		this.toAmt = toAmt;
		this.taxPercent = taxPercent;
		this.eduCessPercent = eduCessPercent;
		this.splEduCessPercent = splEduCessPercent;
		this.fiscalYearId = fiscalYearId;
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
	public String getAgeCode() {
		return ageCode;
	}
	public void setAgeCode(String ageCode) {
		this.ageCode = ageCode;
	}
	public Long getFromAmt() {
		return fromAmt;
	}
	public void setFromAmt(Long fromAmt) {
		this.fromAmt = fromAmt;
	}
	public Long getToAmt() {
		return toAmt;
	}
	public void setToAmt(Long toAmt) {
		this.toAmt = toAmt;
	}
	public Float getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(Float taxPercent) {
		this.taxPercent = taxPercent;
	}
	public Float getEduCessPercent() {
		return eduCessPercent;
	}
	public void setEduCessPercent(Float eduCessPercent) {
		this.eduCessPercent = eduCessPercent;
	}
	public Float getSplEduCessPercent() {
		return splEduCessPercent;
	}
	public void setSplEduCessPercent(Float splEduCessPercent) {
		this.splEduCessPercent = splEduCessPercent;
	}
	public String getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
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
		return "IncomeTax [id=" + id + ", ageCode=" + ageCode + ", fromAmt=" + fromAmt + ", toAmt=" + toAmt
				+ ", taxPercent=" + taxPercent + ", eduCessPercent=" + eduCessPercent + ", splEduCessPercent="
				+ splEduCessPercent + ", fiscalYearId=" + fiscalYearId + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	
}

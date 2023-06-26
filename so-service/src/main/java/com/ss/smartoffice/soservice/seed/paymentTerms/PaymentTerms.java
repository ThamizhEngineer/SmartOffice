package com.ss.smartoffice.soservice.seed.paymentTerms;

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
@Table(name="s_payment_terms")

@Scope("prototype")
public class PaymentTerms {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String ptCode;
	private String ptName;
	private String ptDesc;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public PaymentTerms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentTerms(Integer id, String ptCode, String ptName, String ptDesc, String isEnabled,
			String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.ptCode = ptCode;
		this.ptName = ptName;
		this.ptDesc = ptDesc;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPtCode() {
		return ptCode;
	}

	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public String getPtDesc() {
		return ptDesc;
	}

	public void setPtDesc(String ptDesc) {
		this.ptDesc = ptDesc;
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
		return "paymentTermsService [id=" + id + ", ptCode=" + ptCode + ", ptName=" + ptName + ", ptDesc=" + ptDesc
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

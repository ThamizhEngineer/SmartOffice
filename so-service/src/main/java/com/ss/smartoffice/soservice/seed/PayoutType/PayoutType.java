package com.ss.smartoffice.soservice.seed.PayoutType;
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
@Table(name="s_payout_type")

@Scope("prototype")
public class PayoutType extends BaseEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String payTypeCode;
	private String payTypeName;
	private String payTypeDesc;
	private String isAllowance;
	private String isEnabled;;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PayoutType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayoutType(int id, String payTypeCode, String payTypeName, String payTypeDesc, String isAllowance,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.payTypeCode = payTypeCode;
		this.payTypeName = payTypeName;
		this.payTypeDesc = payTypeDesc;
		this.isAllowance = isAllowance;
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
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getPayTypeDesc() {
		return payTypeDesc;
	}
	public void setPayTypeDesc(String payTypeDesc) {
		this.payTypeDesc = payTypeDesc;
	}
	public String getIsAllowance() {
		return isAllowance;
	}
	public void setIsAllowance(String isAllowance) {
		this.isAllowance = isAllowance;
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
		return "PayoutType [id=" + id + ", payTypeCode=" + payTypeCode + ", payTypeName=" + payTypeName
				+ ", payTypeDesc=" + payTypeDesc + ", isAllowance=" + isAllowance + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	

}

package com.ss.smartoffice.soservice.transaction.PayoutAdjustment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_payout_adjustment")

@Component
@Scope("prototype")
public class PayOutAdjustment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String empCode;
	private String payMonth;
	private String payYear;
	private String payoutCycLineId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDt;
	private float lopDays;
	private String allw_1_code;
	private String allw_1_amount;
	private String allw_2_code;
	private String allw_2_amount;
	private String allw_3_code;
	private String allw_3_amount;
	private String allw_4_code;
	private String allw_4_amount;
	private String allw_5_code;
	private String allw_5_amount;
	private String ded_1_code;
	private String ded_1_amount;
	private String ded_2_code;
	private String ded_2_amount;
	private String ded_3_code;
	private String ded_3_amount;
	private String ded_4_code;
	private String ded_4_amount;
	private String ded_5_code;
	private String ded_5_amount;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public PayOutAdjustment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayOutAdjustment(Integer id, String empCode, String payMonth, String payYear, String payoutCycLineId,
			LocalDate fromDt, LocalDate toDt, float lopDays, String allw_1_code, String allw_1_amount,
			String allw_2_code, String allw_2_amount, String allw_3_code, String allw_3_amount, String allw_4_code,
			String allw_4_amount, String allw_5_code, String allw_5_amount, String ded_1_code, String ded_1_amount,
			String ded_2_code, String ded_2_amount, String ded_3_code, String ded_3_amount, String ded_4_code,
			String ded_4_amount, String ded_5_code, String ded_5_amount, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.empCode = empCode;
		this.payMonth = payMonth;
		this.payYear = payYear;
		this.payoutCycLineId = payoutCycLineId;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.lopDays = lopDays;
		this.allw_1_code = allw_1_code;
		this.allw_1_amount = allw_1_amount;
		this.allw_2_code = allw_2_code;
		this.allw_2_amount = allw_2_amount;
		this.allw_3_code = allw_3_code;
		this.allw_3_amount = allw_3_amount;
		this.allw_4_code = allw_4_code;
		this.allw_4_amount = allw_4_amount;
		this.allw_5_code = allw_5_code;
		this.allw_5_amount = allw_5_amount;
		this.ded_1_code = ded_1_code;
		this.ded_1_amount = ded_1_amount;
		this.ded_2_code = ded_2_code;
		this.ded_2_amount = ded_2_amount;
		this.ded_3_code = ded_3_code;
		this.ded_3_amount = ded_3_amount;
		this.ded_4_code = ded_4_code;
		this.ded_4_amount = ded_4_amount;
		this.ded_5_code = ded_5_code;
		this.ded_5_amount = ded_5_amount;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}

	public String getPayYear() {
		return payYear;
	}

	public void setPayYear(String payYear) {
		this.payYear = payYear;
	}

	public String getPayoutCycLineId() {
		return payoutCycLineId;
	}

	public void setPayoutCycLineId(String payoutCycLineId) {
		this.payoutCycLineId = payoutCycLineId;
	}

	public LocalDate getFromDt() {
		return fromDt;
	}

	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}

	public LocalDate getToDt() {
		return toDt;
	}

	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}

	public float getLopDays() {
		return lopDays;
	}

	public void setLopDays(float lopDays) {
		this.lopDays = lopDays;
	}

	public String getAllw_1_code() {
		return allw_1_code;
	}

	public void setAllw_1_code(String allw_1_code) {
		this.allw_1_code = allw_1_code;
	}

	public String getAllw_1_amount() {
		return allw_1_amount;
	}

	public void setAllw_1_amount(String allw_1_amount) {
		this.allw_1_amount = allw_1_amount;
	}

	public String getAllw_2_code() {
		return allw_2_code;
	}

	public void setAllw_2_code(String allw_2_code) {
		this.allw_2_code = allw_2_code;
	}

	public String getAllw_2_amount() {
		return allw_2_amount;
	}

	public void setAllw_2_amount(String allw_2_amount) {
		this.allw_2_amount = allw_2_amount;
	}

	public String getAllw_3_code() {
		return allw_3_code;
	}

	public void setAllw_3_code(String allw_3_code) {
		this.allw_3_code = allw_3_code;
	}

	public String getAllw_3_amount() {
		return allw_3_amount;
	}

	public void setAllw_3_amount(String allw_3_amount) {
		this.allw_3_amount = allw_3_amount;
	}

	public String getAllw_4_code() {
		return allw_4_code;
	}

	public void setAllw_4_code(String allw_4_code) {
		this.allw_4_code = allw_4_code;
	}

	public String getAllw_4_amount() {
		return allw_4_amount;
	}

	public void setAllw_4_amount(String allw_4_amount) {
		this.allw_4_amount = allw_4_amount;
	}

	public String getAllw_5_code() {
		return allw_5_code;
	}

	public void setAllw_5_code(String allw_5_code) {
		this.allw_5_code = allw_5_code;
	}

	public String getAllw_5_amount() {
		return allw_5_amount;
	}

	public void setAllw_5_amount(String allw_5_amount) {
		this.allw_5_amount = allw_5_amount;
	}

	public String getDed_1_code() {
		return ded_1_code;
	}

	public void setDed_1_code(String ded_1_code) {
		this.ded_1_code = ded_1_code;
	}

	public String getDed_1_amount() {
		return ded_1_amount;
	}

	public void setDed_1_amount(String ded_1_amount) {
		this.ded_1_amount = ded_1_amount;
	}

	public String getDed_2_code() {
		return ded_2_code;
	}

	public void setDed_2_code(String ded_2_code) {
		this.ded_2_code = ded_2_code;
	}

	public String getDed_2_amount() {
		return ded_2_amount;
	}

	public void setDed_2_amount(String ded_2_amount) {
		this.ded_2_amount = ded_2_amount;
	}

	public String getDed_3_code() {
		return ded_3_code;
	}

	public void setDed_3_code(String ded_3_code) {
		this.ded_3_code = ded_3_code;
	}

	public String getDed_3_amount() {
		return ded_3_amount;
	}

	public void setDed_3_amount(String ded_3_amount) {
		this.ded_3_amount = ded_3_amount;
	}

	public String getDed_4_code() {
		return ded_4_code;
	}

	public void setDed_4_code(String ded_4_code) {
		this.ded_4_code = ded_4_code;
	}

	public String getDed_4_amount() {
		return ded_4_amount;
	}

	public void setDed_4_amount(String ded_4_amount) {
		this.ded_4_amount = ded_4_amount;
	}

	public String getDed_5_code() {
		return ded_5_code;
	}

	public void setDed_5_code(String ded_5_code) {
		this.ded_5_code = ded_5_code;
	}

	public String getDed_5_amount() {
		return ded_5_amount;
	}

	public void setDed_5_amount(String ded_5_amount) {
		this.ded_5_amount = ded_5_amount;
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
		return "PayOutAdjustment [id=" + id + ", empCode=" + empCode + ", payMonth=" + payMonth + ", payYear=" + payYear
				+ ", payoutCycLineId=" + payoutCycLineId + ", fromDt=" + fromDt + ", toDt=" + toDt + ", lopDays="
				+ lopDays + ", allw_1_code=" + allw_1_code + ", allw_1_amount=" + allw_1_amount + ", allw_2_code="
				+ allw_2_code + ", allw_2_amount=" + allw_2_amount + ", allw_3_code=" + allw_3_code + ", allw_3_amount="
				+ allw_3_amount + ", allw_4_code=" + allw_4_code + ", allw_4_amount=" + allw_4_amount + ", allw_5_code="
				+ allw_5_code + ", allw_5_amount=" + allw_5_amount + ", ded_1_code=" + ded_1_code + ", ded_1_amount="
				+ ded_1_amount + ", ded_2_code=" + ded_2_code + ", ded_2_amount=" + ded_2_amount + ", ded_3_code="
				+ ded_3_code + ", ded_3_amount=" + ded_3_amount + ", ded_4_code=" + ded_4_code + ", ded_4_amount="
				+ ded_4_amount + ", ded_5_code=" + ded_5_code + ", ded_5_amount=" + ded_5_amount + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + "]";
	}

	
	
}

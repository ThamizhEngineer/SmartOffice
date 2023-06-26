package com.ss.smartoffice.soservice.seed.PayPeriod;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="s_pay_period")

public class PayPeriod extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Integer locationId;
	private String payTypeCode;
	private Integer payOrder;
	private String ppCode;
	@JsonFormat(pattern="yyyy-MM-dd")
	private  LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate payDt;
	private String holidayCount;
	private String remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PayPeriod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayPeriod(int id, Integer locationId, String payTypeCode, Integer payOrder, String ppCode, LocalDate fromDt,
			LocalDate toDt, LocalDate payDt, String holidayCount, String remarks, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.locationId = locationId;
		this.payTypeCode = payTypeCode;
		this.payOrder = payOrder;
		this.ppCode = ppCode;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.payDt = payDt;
		this.holidayCount = holidayCount;
		this.remarks = remarks;
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
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public Integer getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(Integer payOrder) {
		this.payOrder = payOrder;
	}
	public String getPpCode() {
		return ppCode;
	}
	public void setPpCode(String ppCode) {
		this.ppCode = ppCode;
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
	public LocalDate getPayDt() {
		return payDt;
	}
	public void setPayDt(LocalDate payDt) {
		this.payDt = payDt;
	}
	public String getHolidayCount() {
		return holidayCount;
	}
	public void setHolidayCount(String holidayCount) {
		this.holidayCount = holidayCount;
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
		return "PayPeriod [id=" + id + ", locationId=" + locationId + ", payTypeCode=" + payTypeCode + ", payOrder="
				+ payOrder + ", ppCode=" + ppCode + ", fromDt=" + fromDt + ", toDt=" + toDt + ", payDt=" + payDt
				+ ", holidayCount=" + holidayCount + ", remarks=" + remarks + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

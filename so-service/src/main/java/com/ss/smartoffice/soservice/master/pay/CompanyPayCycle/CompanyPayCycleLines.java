package com.ss.smartoffice.soservice.master.pay.CompanyPayCycle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
@Table(name="m_comp_pay_cycle_line")
public class CompanyPayCycleLines extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="m_comp_pay_cycle_id")
	private String compPayCycleId;
	private String compPayCycleLineCode;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate payDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate processedDate;
	 private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private Integer lineOrder;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public CompanyPayCycleLines() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyPayCycleLines(int id, String compPayCycleId, String compPayCycleLineCode, LocalDate fromDt,
			LocalDate toDt, LocalDate payDt, LocalDate processedDate, String isEnabled, String createdBy,
			String modifiedBy, Integer lineOrder, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.compPayCycleId = compPayCycleId;
		this.compPayCycleLineCode = compPayCycleLineCode;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.payDt = payDt;
		this.processedDate = processedDate;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.lineOrder = lineOrder;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompPayCycleId() {
		return compPayCycleId;
	}
	public void setCompPayCycleId(String compPayCycleId) {
		this.compPayCycleId = compPayCycleId;
	}
	public String getCompPayCycleLineCode() {
		return compPayCycleLineCode;
	}
	public void setCompPayCycleLineCode(String compPayCycleLineCode) {
		this.compPayCycleLineCode = compPayCycleLineCode;
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
	public LocalDate getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(LocalDate processedDate) {
		this.processedDate = processedDate;
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
	public Integer getLineOrder() {
		return lineOrder;
	}
	public void setLineOrder(Integer lineOrder) {
		this.lineOrder = lineOrder;
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
		return "CompanyPayCycleLines [id=" + id + ", compPayCycleId=" + compPayCycleId + ", compPayCycleLineCode="
				+ compPayCycleLineCode + ", fromDt=" + fromDt + ", toDt=" + toDt + ", payDt=" + payDt
				+ ", processedDate=" + processedDate + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", lineOrder=" + lineOrder + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	
}

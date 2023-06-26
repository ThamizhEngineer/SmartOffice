package com.ss.smartoffice.soservice.master.pay.GradeCtc;
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
@Table(name="m_grade_ctc_lines")

public class GradeCtcLine extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="m_grade_ctc_id")
	private String gradeCtcId;
	private Integer processOrder;
	private String payType;
	private String head;
	private String payCycleId;
	private String isLumpSum;
	private String unitCost;
	private String formula;
	private String maxLimit;
	private String lineDesc;
	private String calculatedAmt;
	private String hiddenToEmployee;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public GradeCtcLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GradeCtcLine(int id, String gradeCtcId, Integer processOrder, String payType, String head, String payCycleId,
			String isLumpSum, String unitCost, String formula, String maxLimit, String lineDesc, String calculatedAmt,
			String hiddenToEmployee, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.gradeCtcId = gradeCtcId;
		this.processOrder = processOrder;
		this.payType = payType;
		this.head = head;
		this.payCycleId = payCycleId;
		this.isLumpSum = isLumpSum;
		this.unitCost = unitCost;
		this.formula = formula;
		this.maxLimit = maxLimit;
		this.lineDesc = lineDesc;
		this.calculatedAmt = calculatedAmt;
		this.hiddenToEmployee = hiddenToEmployee;
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
	public String getGradeCtcId() {
		return gradeCtcId;
	}
	public void setGradeCtcId(String gradeCtcId) {
		this.gradeCtcId = gradeCtcId;
	}
	public Integer getProcessOrder() {
		return processOrder;
	}
	public void setProcessOrder(Integer processOrder) {
		this.processOrder = processOrder;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getPayCycleId() {
		return payCycleId;
	}
	public void setPayCycleId(String payCycleId) {
		this.payCycleId = payCycleId;
	}
	public String getIsLumpSum() {
		return isLumpSum;
	}
	public void setIsLumpSum(String isLumpSum) {
		this.isLumpSum = isLumpSum;
	}
	public String getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}
	public String getLineDesc() {
		return lineDesc;
	}
	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}
	public String getCalculatedAmt() {
		return calculatedAmt;
	}
	public void setCalculatedAmt(String calculatedAmt) {
		this.calculatedAmt = calculatedAmt;
	}
	public String getHiddenToEmployee() {
		return hiddenToEmployee;
	}
	public void setHiddenToEmployee(String hiddenToEmployee) {
		this.hiddenToEmployee = hiddenToEmployee;
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
		return "GradeCtcLine [id=" + id + ", gradeCtcId=" + gradeCtcId + ", processOrder=" + processOrder + ", payType="
				+ payType + ", head=" + head + ", payCycleId=" + payCycleId + ", isLumpSum=" + isLumpSum + ", unitCost="
				+ unitCost + ", formula=" + formula + ", maxLimit=" + maxLimit + ", lineDesc=" + lineDesc
				+ ", calculatedAmt=" + calculatedAmt + ", hiddenToEmployee=" + hiddenToEmployee + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
}

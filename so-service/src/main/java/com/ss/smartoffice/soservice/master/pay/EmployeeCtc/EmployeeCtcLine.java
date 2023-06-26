package com.ss.smartoffice.soservice.master.pay.EmployeeCtc;

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
@Table(name = "m_emp_ctc_line")

@Scope("prototype")
public class EmployeeCtcLine extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "m_emp_ctc_id")
	private String employeeCtcId;
	private int processOrder;
	private Integer sPayoutTypeId;
	private String head;
	private String payCycleId;
	private String isLumpSum = "Y";
	private String unitCost;
	private String formula;
	private String maxLimit;
	private String lineDesc;
	private String calculatedAmt;
	private String hiddenToEmployee;
	@Formula("(select pay.is_allowance from s_payout_type pay where pay.id=s_payout_type_id)")
	private String isAllowance;
	private String ruleType;
	private String cusType;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public EmployeeCtcLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeCtcLine(int id, String employeeCtcId, int processOrder, Integer sPayoutTypeId, String head,
			String payCycleId, String isLumpSum, String unitCost, String formula, String maxLimit, String lineDesc,
			String calculatedAmt, String hiddenToEmployee, String isAllowance, String ruleType, String cusType,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeeCtcId = employeeCtcId;
		this.processOrder = processOrder;
		this.sPayoutTypeId = sPayoutTypeId;
		this.head = head;
		this.payCycleId = payCycleId;
		this.isLumpSum = isLumpSum;
		this.unitCost = unitCost;
		this.formula = formula;
		this.maxLimit = maxLimit;
		this.lineDesc = lineDesc;
		this.calculatedAmt = calculatedAmt;
		this.hiddenToEmployee = hiddenToEmployee;
		this.isAllowance = isAllowance;
		this.ruleType = ruleType;
		this.cusType = cusType;
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

	public String getEmployeeCtcId() {
		return employeeCtcId;
	}

	public void setEmployeeCtcId(String employeeCtcId) {
		this.employeeCtcId = employeeCtcId;
	}

	public int getProcessOrder() {
		return processOrder;
	}

	public void setProcessOrder(int processOrder) {
		this.processOrder = processOrder;
	}

	public Integer getsPayoutTypeId() {
		return sPayoutTypeId;
	}

	public void setsPayoutTypeId(Integer sPayoutTypeId) {
		this.sPayoutTypeId = sPayoutTypeId;
	}

	public String getIsAllowance() {
		return isAllowance;
	}

	public void setIsAllowance(String isAllowance) {
		this.isAllowance = isAllowance;
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

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
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
		return "EmployeeCtcLine [id=" + id + ", employeeCtcId=" + employeeCtcId + ", processOrder=" + processOrder
				+ ", sPayoutTypeId=" + sPayoutTypeId + ", head=" + head + ", payCycleId=" + payCycleId + ", isLumpSum="
				+ isLumpSum + ", unitCost=" + unitCost + ", formula=" + formula + ", maxLimit=" + maxLimit
				+ ", lineDesc=" + lineDesc + ", calculatedAmt=" + calculatedAmt + ", hiddenToEmployee="
				+ hiddenToEmployee + ", isAllowance=" + isAllowance + ", ruleType=" + ruleType + ", cusType=" + cusType
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

}

package com.ss.smartoffice.shared.model;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.EmployeePayoutLine;

public class EmployeePaySlipLine {
	private String aPayTypeName;
	private String aEarningPeriod;
	private String aCurrentPeriod;
	private String aArrears;
	private String aYtdEarnings;
	private String dPayTypeName;
	private String dCurrentPeriod;
	private String dYtdEarnings;
	public EmployeePaySlipLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setAllowance(EmployeePayoutLine employeePayoutLine) {
		this.aPayTypeName = employeePayoutLine.getdPayTypeName();
		this.aEarningPeriod = CommonUtils.printInLakhs(employeePayoutLine.getPeriodAmt());
		this.aCurrentPeriod = CommonUtils.printInLakhs(employeePayoutLine.getLineAmt());
		this.aArrears = CommonUtils.printInLakhs(employeePayoutLine.getArrearAmt());
		this.aYtdEarnings = CommonUtils.printInLakhs(employeePayoutLine.getYtdAmt());

	}

	public void setDeduction(EmployeePayoutLine employeePayoutLine) {
		this.dPayTypeName = employeePayoutLine.getdPayTypeName();
		this.dCurrentPeriod = CommonUtils.printInLakhs(employeePayoutLine.getLineAmt());
		this.dYtdEarnings = CommonUtils.printInLakhs(employeePayoutLine.getYtdAmt());
	}
	
	public EmployeePaySlipLine(String aPayTypeName, String aEarningPeriod, String aCurrentPeriod, String aArrears,
			String aYtdEarnings, String dPayTypeName, String dCurrentPeriod, String dYtdEarnings) {
		super();
		this.aPayTypeName = aPayTypeName;
		this.aEarningPeriod = aEarningPeriod;
		this.aCurrentPeriod = aCurrentPeriod;
		this.aArrears = aArrears;
		this.aYtdEarnings = aYtdEarnings;
		this.dPayTypeName = dPayTypeName;
		this.dCurrentPeriod = dCurrentPeriod;
		this.dYtdEarnings = dYtdEarnings;
	}
	public String getaPayTypeName() {
		return aPayTypeName;
	}
	public void setaPayTypeName(String aPayTypeName) {
		this.aPayTypeName = aPayTypeName;
	}
	public String getaEarningPeriod() {
		return aEarningPeriod;
	}
	public void setaEarningPeriod(String aEarningPeriod) {
		this.aEarningPeriod = aEarningPeriod;
	}
	public String getaCurrentPeriod() {
		return aCurrentPeriod;
	}
	public void setaCurrentPeriod(String aCurrentPeriod) {
		this.aCurrentPeriod = aCurrentPeriod;
	}
	public String getaArrears() {
		return aArrears;
	}
	public void setaArrears(String aArrears) {
		this.aArrears = aArrears;
	}
	public String getaYtdEarnings() {
		return aYtdEarnings;
	}
	public void setaYtdEarnings(String aYtdEarnings) {
		this.aYtdEarnings = aYtdEarnings;
	}
	public String getdPayTypeName() {
		return dPayTypeName;
	}
	public void setdPayTypeName(String dPayTypeName) {
		this.dPayTypeName = dPayTypeName;
	}
	public String getdCurrentPeriod() {
		return dCurrentPeriod;
	}
	public void setdCurrentPeriod(String dCurrentPeriod) {
		this.dCurrentPeriod = dCurrentPeriod;
	}
	public String getdYtdEarnings() {
		return dYtdEarnings;
	}
	public void setdYtdEarnings(String dYtdEarnings) {
		this.dYtdEarnings = dYtdEarnings;
	}
	@Override
	public String toString() {
		return "EmployeePaySlipLine [aPayTypeName=" + aPayTypeName + ", aEarningPeriod=" + aEarningPeriod
				+ ", aCurrentPeriod=" + aCurrentPeriod + ", aArrears=" + aArrears + ", aYtdEarnings=" + aYtdEarnings
				+ ", dPayTypeName=" + dPayTypeName + ", dCurrentPeriod=" + dCurrentPeriod + ", dYtdEarnings="
				+ dYtdEarnings + "]";
	}
	
	

}

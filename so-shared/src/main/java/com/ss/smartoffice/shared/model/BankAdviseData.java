package com.ss.smartoffice.shared.model;


public class BankAdviseData {
	private String bankName;
	private String ifscCode;
	private String accNo;
	private String accountName;
	private double totalEntitledAmount;
	private String settleRemarks;
	private String expensePurpose;
	private String debitAccountNo;
	public BankAdviseData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankAdviseData(String bankName, String ifscCode, String accNo, String accountName,
			double totalEntitledAmount, String settleRemarks, String expensePurpose, String debitAccountNo) {
		super();
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.accNo = accNo;
		this.accountName = accountName;
		this.totalEntitledAmount = totalEntitledAmount;
		this.settleRemarks = settleRemarks;
		this.expensePurpose = expensePurpose;
		this.debitAccountNo = debitAccountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getTotalEntitledAmount() {
		return totalEntitledAmount;
	}
	public void setTotalEntitledAmount(double totalEntitledAmount) {
		this.totalEntitledAmount = totalEntitledAmount;
	}
	public String getSettleRemarks() {
		return settleRemarks;
	}
	public void setSettleRemarks(String settleRemarks) {
		this.settleRemarks = settleRemarks;
	}
	public String getExpensePurpose() {
		return expensePurpose;
	}
	public void setExpensePurpose(String expensePurpose) {
		this.expensePurpose = expensePurpose;
	}
	public String getDebitAccountNo() {
		return debitAccountNo;
	}
	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}
	@Override
	public String toString() {
		return "BankAdviseData [bankName=" + bankName + ", ifscCode=" + ifscCode + ", accNo=" + accNo + ", accountName="
				+ accountName + ", totalEntitledAmount=" + totalEntitledAmount + ", settleRemarks=" + settleRemarks
				+ ", expensePurpose=" + expensePurpose + ", debitAccountNo=" + debitAccountNo + "]";
	}
	
	
	

}

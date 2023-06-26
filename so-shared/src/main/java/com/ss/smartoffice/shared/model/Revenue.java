package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "v_revenue")
public class Revenue {
	@Id
	@Column(name = "t_invoice_hdr_id", columnDefinition = "int")
	private String invoiceHdrId;
	@Column(name = "m_client_id")
	private String clientId;
	private String clientCode;
	private String clientName;
	@Column(name = "s_country_id", columnDefinition = "int")
	private String countryId;
	private String countryName;
	@Column(name = "t_sale_order_id")
	private String saleOrderId;
	private String soCode;
	@Column(columnDefinition = "text")
	private String jobCodes;
	@Column(name = "m_bu_id", columnDefinition = "int")
	private String buId;
	private String buCode;
	private String buName;
	@Column(name = "m_division_id")
	private String divisionId;
	@Column(columnDefinition = "int")
	private String divisionCode;
	private String divisionName;
	@Column(name = "m_function_id", columnDefinition = "char")
	private String functionId;
	@Column(columnDefinition = "char")
	private String functionName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invoiceDate;
	@Column(columnDefinition = "int")
	private String monthNo;
	private String monthName;
	@Column(columnDefinition = "int")
	private String quarter;
	private String quarterName;
	@Column(columnDefinition = "int")
	private String year;
	private String invoiceCode;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private String invFinalAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private String invPaidAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private String invBalAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private String invOverdueAmt;
	public Revenue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Revenue(String invoiceHdrId, String clientId, String clientCode, String clientName, String countryId,
			String countryName, String saleOrderId, String soCode, String jobCodes, String buId, String buCode,
			String buName, String divisionId, String divisionCode, String divisionName, String functionId,
			String functionName, LocalDateTime invoiceDate, String monthNo, String monthName, String quarter,
			String quarterName, String year, String invoiceCode, String invFinalAmt, String invPaidAmt,
			String invBalAmt, String invOverdueAmt) {
		super();
		this.invoiceHdrId = invoiceHdrId;
		this.clientId = clientId;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.countryId = countryId;
		this.countryName = countryName;
		this.saleOrderId = saleOrderId;
		this.soCode = soCode;
		this.jobCodes = jobCodes;
		this.buId = buId;
		this.buCode = buCode;
		this.buName = buName;
		this.divisionId = divisionId;
		this.divisionCode = divisionCode;
		this.divisionName = divisionName;
		this.functionId = functionId;
		this.functionName = functionName;
		this.invoiceDate = invoiceDate;
		this.monthNo = monthNo;
		this.monthName = monthName;
		this.quarter = quarter;
		this.quarterName = quarterName;
		this.year = year;
		this.invoiceCode = invoiceCode;
		this.invFinalAmt = invFinalAmt;
		this.invPaidAmt = invPaidAmt;
		this.invBalAmt = invBalAmt;
		this.invOverdueAmt = invOverdueAmt;
	}

	@Override
	public String toString() {
		return "Revenue [invoiceHdrId=" + invoiceHdrId + ", clientId=" + clientId + ", clientCode=" + clientCode
				+ ", clientName=" + clientName + ", countryId=" + countryId + ", countryName=" + countryName
				+ ", saleOrderId=" + saleOrderId + ", soCode=" + soCode + ", jobCodes=" + jobCodes + ", buId=" + buId
				+ ", buCode=" + buCode + ", buName=" + buName + ", divisionId=" + divisionId + ", divisionCode="
				+ divisionCode + ", divisionName=" + divisionName + ", functionId=" + functionId + ", functionName="
				+ functionName + ", invoiceDate=" + invoiceDate + ", monthNo=" + monthNo + ", monthName=" + monthName
				+ ", quarter=" + quarter + ", quarterName=" + quarterName + ", year=" + year + ", invoiceCode="
				+ invoiceCode + ", invFinalAmt=" + invFinalAmt + ", invPaidAmt=" + invPaidAmt + ", invBalAmt="
				+ invBalAmt + ", invOverdueAmt=" + invOverdueAmt + "]";
	}
	public String getInvoiceHdrId() {
		return invoiceHdrId;
	}
	public void setInvoiceHdrId(String invoiceHdrId) {
		this.invoiceHdrId = invoiceHdrId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getSaleOrderId() {
		return saleOrderId;
	}
	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	public String getSoCode() {
		return soCode;
	}
	public void setSoCode(String soCode) {
		this.soCode = soCode;
	}
	public String getJobCodes() {
		return jobCodes;
	}
	public void setJobCodes(String jobCodes) {
		this.jobCodes = jobCodes;
	}
	public String getBuId() {
		return buId;
	}
	public void setBuId(String buId) {
		this.buId = buId;
	}
	public String getBuCode() {
		return buCode;
	}
	public void setBuCode(String buCode) {
		this.buCode = buCode;
	}
	public String getBuName() {
		return buName;
	}
	public void setBuName(String buName) {
		this.buName = buName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(String monthNo) {
		this.monthNo = monthNo;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarterName() {
		return quarterName;
	}
	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvFinalAmt() {
		return invFinalAmt;
	}
	public void setInvFinalAmt(String invFinalAmt) {
		this.invFinalAmt = invFinalAmt;
	}
	public String getInvPaidAmt() {
		return invPaidAmt;
	}
	public void setInvPaidAmt(String invPaidAmt) {
		this.invPaidAmt = invPaidAmt;
	}
	public String getInvBalAmt() {
		return invBalAmt;
	}
	public void setInvBalAmt(String invBalAmt) {
		this.invBalAmt = invBalAmt;
	}
	public String getInvOverdueAmt() {
		return invOverdueAmt;
	}
	public void setInvOverdueAmt(String invOverdueAmt) {
		this.invOverdueAmt = invOverdueAmt;
	}
	
}

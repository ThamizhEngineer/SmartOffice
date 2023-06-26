package com.ss.smartoffice.shared.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_order_intake")
public class OrderInTake {

	@Id
	@Column(name = "t_sale_order_id", columnDefinition = "int")
	private String saleOrderId;
	private String soCode;
	@Column(columnDefinition = "datetime")
	private String soDate;
	@Column(columnDefinition = "decimal")
	private String soOrderAmount;
	@Column(columnDefinition = "int")
	private String monthNo;
	private String monthName;
	@Column(columnDefinition = "int")
	private String year;
	@Column(columnDefinition = "text")
	private String jobCodes="";
	private String isVirtualPo;
	private String virtualPoNum;
	@Column(name = "t_client_po_id")
	private String clientPoId;
	private String clientPoCode;
	@Column(columnDefinition = "decimal")
	private String poOrderAmount;
	private String hasGoods;
	private String hasServices;
	@Column(name = "m_bu_id", columnDefinition = "int")
	private String buId="";
	private String buCode="";
	private String buName="";
	@Column(name = "m_division_id", columnDefinition = "int")
	private String divisionId;
	@Column(columnDefinition = "int")
	private String divisionCode="";
	private String divisionName="";
	@Column(name = "m_function_id", columnDefinition = "char")
	private String functionId;
	@Column(columnDefinition = "char")
	private String functionName;
	@Column(name = "m_client_id")
	private String clientId;
	private String clientCode;
	private String clientName;
	@Column(name = "s_country_id", columnDefinition = "int")
	private String countryId;
	private String countryName;
	
	public OrderInTake() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderInTake(String saleOrderId, String soCode, String soDate, String soOrderAmount, String monthNo,
			String monthName, String year, String jobCodes, String isVirtualPo, String virtualPoNum, String clientPoId,
			String clientPoCode, String poOrderAmount, String hasGoods, String hasServices, String buId, String buCode,
			String buName, String divisionId, String divisionCode, String divisionName, String functionId,
			String functionName, String clientId, String clientCode, String clientName, String countryId,
			String countryName) {
		super();
		this.saleOrderId = saleOrderId;
		this.soCode = soCode;
		this.soDate = soDate;
		this.soOrderAmount = soOrderAmount;
		this.monthNo = monthNo;
		this.monthName = monthName;
		this.year = year;
		this.jobCodes = jobCodes;
		this.isVirtualPo = isVirtualPo;
		this.virtualPoNum = virtualPoNum;
		this.clientPoId = clientPoId;
		this.clientPoCode = clientPoCode;
		this.poOrderAmount = poOrderAmount;
		this.hasGoods = hasGoods;
		this.hasServices = hasServices;
		this.buId = buId;
		this.buCode = buCode;
		this.buName = buName;
		this.divisionId = divisionId;
		this.divisionCode = divisionCode;
		this.divisionName = divisionName;
		this.functionId = functionId;
		this.functionName = functionName;
		this.clientId = clientId;
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.countryId = countryId;
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "OrderInTake [saleOrderId=" + saleOrderId + ", soCode=" + soCode + ", soDate=" + soDate
				+ ", soOrderAmount=" + soOrderAmount + ", monthNo=" + monthNo + ", monthName=" + monthName + ", year="
				+ year + ", jobCodes=" + jobCodes + ", isVirtualPo=" + isVirtualPo + ", virtualPoNum=" + virtualPoNum
				+ ", clientPoId=" + clientPoId + ", clientPoCode=" + clientPoCode + ", poOrderAmount=" + poOrderAmount
				+ ", hasGoods=" + hasGoods + ", hasServices=" + hasServices + ", buId=" + buId + ", buCode=" + buCode
				+ ", buName=" + buName + ", divisionId=" + divisionId + ", divisionCode=" + divisionCode
				+ ", divisionName=" + divisionName + ", functionId=" + functionId + ", functionName=" + functionName
				+ ", clientId=" + clientId + ", clientCode=" + clientCode + ", clientName=" + clientName
				+ ", countryId=" + countryId + ", countryName=" + countryName + "]";
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

	public String getSoDate() {
		return soDate;
	}

	public void setSoDate(String soDate) {
		this.soDate = soDate;
	}

	public String getSoOrderAmount() {
		return soOrderAmount;
	}

	public void setSoOrderAmount(String soOrderAmount) {
		this.soOrderAmount = soOrderAmount;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getJobCodes() {
		return jobCodes;
	}

	public void setJobCodes(String jobCodes) {
		this.jobCodes = jobCodes;
	}

	public String getIsVirtualPo() {
		return isVirtualPo;
	}

	public void setIsVirtualPo(String isVirtualPo) {
		this.isVirtualPo = isVirtualPo;
	}

	public String getVirtualPoNum() {
		return virtualPoNum;
	}

	public void setVirtualPoNum(String virtualPoNum) {
		this.virtualPoNum = virtualPoNum;
	}

	public String getClientPoId() {
		return clientPoId;
	}

	public void setClientPoId(String clientPoId) {
		this.clientPoId = clientPoId;
	}

	public String getClientPoCode() {
		return clientPoCode;
	}

	public void setClientPoCode(String clientPoCode) {
		this.clientPoCode = clientPoCode;
	}

	public String getPoOrderAmount() {
		return poOrderAmount;
	}

	public void setPoOrderAmount(String poOrderAmount) {
		this.poOrderAmount = poOrderAmount;
	}

	public String getHasGoods() {
		return hasGoods;
	}

	public void setHasGoods(String hasGoods) {
		this.hasGoods = hasGoods;
	}

	public String getHasServices() {
		return hasServices;
	}

	public void setHasServices(String hasServices) {
		this.hasServices = hasServices;
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

	
}

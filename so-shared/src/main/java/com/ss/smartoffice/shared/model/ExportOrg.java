package com.ss.smartoffice.shared.model;



public class ExportOrg {
	private String fyYearId;
	private String fyYearCode;
	private String empName;
	private String metricName;
	private String operator;
	private String metricValue;
	private String metricCategoryName;
	private String metricLevelCode;
	private String descp;
	private String metricSummary;


	
	public ExportOrg() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ExportOrg(String fyYearCode, String empName, String metricName, String operator, String metricValue,
			String metricCategoryName, String metricLevelCode, String descp, String metricSummary) {
		super();
		this.fyYearCode = fyYearCode;
		this.empName = empName;
		this.metricName = metricName;
		this.operator = operator;
		this.metricValue = metricValue;
		this.metricCategoryName = metricCategoryName;
		this.metricLevelCode = metricLevelCode;
		this.descp = descp;
		this.metricSummary = metricSummary;
	}



	public ExportOrg(String fyYearId, String fyYearCode, String empName, String metricName, String operator,
			String metricValue, String metricCategoryName, String metricLevelCode, String descp, String metricSummary) {
		super();
		this.fyYearId = fyYearId;
		this.fyYearCode = fyYearCode;
		this.empName = empName;
		this.metricName = metricName;
		this.operator = operator;
		this.metricValue = metricValue;
		this.metricCategoryName = metricCategoryName;
		this.metricLevelCode = metricLevelCode;
		this.descp = descp;
		this.metricSummary = metricSummary;
	}



	public String getFyYearId() {
		return fyYearId;
	}



	public void setFyYearId(String fyYearId) {
		this.fyYearId = fyYearId;
	}



	public String getFyYearCode() {
		return fyYearCode;
	}



	public void setFyYearCode(String fyYearCode) {
		this.fyYearCode = fyYearCode;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getMetricName() {
		return metricName;
	}



	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public String getMetricValue() {
		return metricValue;
	}



	public void setMetricValue(String metricValue) {
		this.metricValue = metricValue;
	}



	public String getMetricCategoryName() {
		return metricCategoryName;
	}



	public void setMetricCategoryName(String metricCategoryName) {
		this.metricCategoryName = metricCategoryName;
	}



	public String getMetricLevelCode() {
		return metricLevelCode;
	}



	public void setMetricLevelCode(String metricLevelCode) {
		this.metricLevelCode = metricLevelCode;
	}



	public String getDescp() {
		return descp;
	}



	public void setDescp(String descp) {
		this.descp = descp;
	}



	public String getMetricSummary() {
		return metricSummary;
	}



	public void setMetricSummary(String metricSummary) {
		this.metricSummary = metricSummary;
	}



	@Override
	public String toString() {
		return "ExportOrg [fyYearId=" + fyYearId + ", fyYearCode=" + fyYearCode + ", empName=" + empName
				+ ", metricName=" + metricName + ", operator=" + operator + ", metricValue=" + metricValue
				+ ", metricCategoryName=" + metricCategoryName + ", metricLevelCode=" + metricLevelCode + ", descp="
				+ descp + ", metricSummary=" + metricSummary + "]";
	}



	
	
}

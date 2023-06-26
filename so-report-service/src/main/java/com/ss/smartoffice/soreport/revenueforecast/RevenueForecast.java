package com.ss.smartoffice.soreport.revenueforecast;

public class RevenueForecast {
	
	private String year;
	private String quarterName;
	private String orderIntake;
	private String actualRevenue;
	
	public RevenueForecast() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RevenueForecast [year=" + year + ", quarterName=" + quarterName + ", orderIntake=" + orderIntake
				+ ", actualRevenue=" + actualRevenue + "]";
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarterName() {
		return quarterName;
	}

	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}

	public String getOrderIntake() {
		return orderIntake;
	}

	public void setOrderIntake(String orderIntake) {
		this.orderIntake = orderIntake;
	}

	public String getActualRevenue() {
		return actualRevenue;
	}

	public void setActualRevenue(String actualRevenue) {
		this.actualRevenue = actualRevenue;
	}

	public RevenueForecast(String year, String quarterName, String orderIntake, String actualRevenue) {
		super();
		this.year = year;
		this.quarterName = quarterName;
		this.orderIntake = orderIntake;
		this.actualRevenue = actualRevenue;
	}
	
	

}

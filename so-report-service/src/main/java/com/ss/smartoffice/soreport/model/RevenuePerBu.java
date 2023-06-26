package com.ss.smartoffice.soreport.model;

public class RevenuePerBu {
	
	private String totalRevenue;
	private String buName;
	
	public RevenuePerBu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RevenuePerBu(String totalRevenue, String buName) {
		super();
		this.totalRevenue = totalRevenue;
		this.buName = buName;
	}

	@Override
	public String toString() {
		return "RevenuePerBu [totalRevenue=" + totalRevenue + ", buName=" + buName + "]";
	}

	public String getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}
	
	
	
}

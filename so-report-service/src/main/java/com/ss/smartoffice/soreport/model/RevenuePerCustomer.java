package com.ss.smartoffice.soreport.model;

public class RevenuePerCustomer {

	private String totalRevenue;
	private String clientName;
	
	public RevenuePerCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RevenuePerCustomer [totalRevenue=" + totalRevenue + ", clientName=" + clientName + "]";
	}

	public RevenuePerCustomer(String totalRevenue, String clientName) {
		super();
		this.totalRevenue = totalRevenue;
		this.clientName = clientName;
	}

	public String getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
}

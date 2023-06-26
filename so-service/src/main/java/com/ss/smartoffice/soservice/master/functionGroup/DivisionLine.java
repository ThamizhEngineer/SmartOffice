package com.ss.smartoffice.soservice.master.functionGroup;

public class DivisionLine {

	private String divisionName;

	public DivisionLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DivisionLine(String divisionName) {
		super();
		this.divisionName = divisionName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	@Override
	public String toString() {
		return "DivisionLine [divisionName=" + divisionName + "]";
	}
	
	
}

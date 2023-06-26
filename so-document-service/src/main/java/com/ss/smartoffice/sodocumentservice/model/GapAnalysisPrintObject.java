package com.ss.smartoffice.sodocumentservice.model;

public class GapAnalysisPrintObject {

	private String skill;
	private String marketNeed;
	private String inHouseCapacity;
	private String gap;
	
	public GapAnalysisPrintObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GapAnalysisPrintObject(String skill, String marketNeed, String inHouseCapacity, String gap) {
		super();
		this.skill = skill;
		this.marketNeed = marketNeed;
		this.inHouseCapacity = inHouseCapacity;
		this.gap = gap;
	}

	@Override
	public String toString() {
		return "GapAnalysisPrintObject [skill=" + skill + ", marketNeed=" + marketNeed + ", inHouseCapacity="
				+ inHouseCapacity + ", gap=" + gap + "]";
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getMarketNeed() {
		return marketNeed;
	}

	public void setMarketNeed(String marketNeed) {
		this.marketNeed = marketNeed;
	}

	public String getInHouseCapacity() {
		return inHouseCapacity;
	}

	public void setInHouseCapacity(String inHouseCapacity) {
		this.inHouseCapacity = inHouseCapacity;
	}

	public String getGap() {
		return gap;
	}

	public void setGap(String gap) {
		this.gap = gap;
	}
	
	
	
	
}

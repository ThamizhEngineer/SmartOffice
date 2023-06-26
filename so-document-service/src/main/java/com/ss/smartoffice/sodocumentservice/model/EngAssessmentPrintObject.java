package com.ss.smartoffice.sodocumentservice.model;

import java.util.List;

public class EngAssessmentPrintObject {
	
	private String engineer;
	private List<SkillMatrixResult> has; //change to list
	private List<SkillMatrixResult> lacks;
	private List<SkillMatrixResult> training;
	
	public EngAssessmentPrintObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EngAssessmentPrintObject(String engineer, List<SkillMatrixResult> has, List<SkillMatrixResult> lacks,
			List<SkillMatrixResult> training) {
		super();
		this.engineer = engineer;
		this.has = has;
		this.lacks = lacks;
		this.training = training;
	}

	@Override
	public String toString() {
		return "EngAssessmentPrintObject [engineer=" + engineer + ", has=" + has + ", lacks=" + lacks + ", training="
				+ training + "]";
	}

	public String getEngineer() {
		return engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public List<SkillMatrixResult> getHas() {
		return has;
	}

	public void setHas(List<SkillMatrixResult> has) {
		this.has = has;
	}

	public List<SkillMatrixResult> getLacks() {
		return lacks;
	}

	public void setLacks(List<SkillMatrixResult> lacks) {
		this.lacks = lacks;
	}

	public List<SkillMatrixResult> getTraining() {
		return training;
	}

	public void setTraining(List<SkillMatrixResult> training) {
		this.training = training;
	}

	
}
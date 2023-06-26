package com.ss.smartoffice.soservice.transaction.appraisalservice;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class ResultAnalysisByEmp {
	private String resultAnalysisId;
	private String employeeName;
	private String managerName;
	private String OfficeName;
	private String designationName;
	private String targetEmp;
	private String metricSymbol;
	private String empAcheiv;
	private String managerScore;
	private String managerScoreCode;
	private String managerRemarks;
	private String isEnabled;
	private String createdBy;
	private String createdDt;
}

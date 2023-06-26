package com.ss.smartoffice.shared.model;

public class ExportTestResultView {
	private String testTemplateName; 
	private String applicantName;
	private String testStatus;
	private String totalCorrect; 
	private String totalWrong;
	private String totalUnAttended;
	private String totalCorrectEasy;
	private String totalCorrectMedium;
	private String totalCorrectTough;
	private String score;
	private String testResult;
	
	public ExportTestResultView() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public ExportTestResultView(String testTemplateName, String applicantName, String testStatus, String totalCorrect,
			String totalWrong, String totalUnAttended, String totalCorrectEasy, String totalCorrectMedium,
			String totalCorrectTough, String score, String testResult) {
		super();
		this.testTemplateName = testTemplateName;
		this.applicantName = applicantName;
		this.testStatus = testStatus;
		this.totalCorrect = totalCorrect;
		this.totalWrong = totalWrong;
		this.totalUnAttended = totalUnAttended;
		this.totalCorrectEasy = totalCorrectEasy;
		this.totalCorrectMedium = totalCorrectMedium;
		this.totalCorrectTough = totalCorrectTough;
		this.score = score;
		this.testResult = testResult;
	}


	@Override
	public String toString() {
		return "ExportTestResultView [testTemplateName=" + testTemplateName + ", applicantName=" + applicantName
				+ ", testStatus=" + testStatus + ", totalCorrect=" + totalCorrect + ", totalWrong=" + totalWrong
				+ ", totalUnAttended=" + totalUnAttended + ", totalCorrectEasy=" + totalCorrectEasy
				+ ", totalCorrectMedium=" + totalCorrectMedium + ", totalCorrectTough=" + totalCorrectTough + ", score="
				+ score + ", testResult=" + testResult + "]";
	}

	public String getTestTemplateName() {
		return testTemplateName;
	}

	public void setTestTemplateName(String testTemplateName) {
		this.testTemplateName = testTemplateName;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	public String getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(String totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public String getTotalWrong() {
		return totalWrong;
	}

	public void setTotalWrong(String totalWrong) {
		this.totalWrong = totalWrong;
	}

	public String getTotalUnAttended() {
		return totalUnAttended;
	}

	public void setTotalUnAttended(String totalUnAttended) {
		this.totalUnAttended = totalUnAttended;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}	
	public String getTotalCorrectEasy() {
		return totalCorrectEasy;
	}
	public void setTotalCorrectEasy(String totalCorrectEasy) {
		this.totalCorrectEasy = totalCorrectEasy;
	}
	public String getTotalCorrectMedium() {
		return totalCorrectMedium;
	}
	public void setTotalCorrectMedium(String totalCorrectMedium) {
		this.totalCorrectMedium = totalCorrectMedium;
	}
	public String getTotalCorrectTough() {
		return totalCorrectTough;
	}
	public void setTotalCorrectTough(String totalCorrectTough) {
		this.totalCorrectTough = totalCorrectTough;
	}
	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	
	

}

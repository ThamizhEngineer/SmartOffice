package com.ss.smartoffice.shared.model;


public class ExportTestResults {
	private String incidentId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String expType;
	private String isEligibleForTest;
	private String testStatus;
	private String score;
	private String testResult;
	private String duration;
	
	public ExportTestResults() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExportTestResults(String incidentId, String firstName, String lastName, String email, String mobileNumber,
			String expType, String isEligibleForTest, String testStatus, String score, String testResult,
			String duration) {
		super();
		this.incidentId = incidentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.expType = expType;
		this.isEligibleForTest = isEligibleForTest;
		this.testStatus = testStatus;
		this.score = score;
		this.testResult = testResult;
		this.duration = duration;
	}

//	@Override
//	public String toString() {
//		return "ExportTestResults [incidentId=" + incidentId + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", expType=" + expType
//				+ ", isEligibleForTest=" + isEligibleForTest + ", testStatus=" + testStatus + ", score=" + score
//				+ ", testResult=" + testResult + ", duration=" + duration + "]";
//	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getIsEligibleForTest() {
		return isEligibleForTest;
	}

	public void setIsEligibleForTest(String isEligibleForTest) {
		this.isEligibleForTest = isEligibleForTest;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
	
}

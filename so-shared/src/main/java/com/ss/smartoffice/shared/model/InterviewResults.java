package com.ss.smartoffice.shared.model;

public class InterviewResults {
	private String incidentId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String expType;
	private String isInterviewEligible;
	private String finalInterviewStatus;
	private String finalDecisionRemarks;
	private String firstInterviewerId;
	private String round1Decision;
	private String round1Rating;
	private String round1Remarks;
	private String round1completedDt;
	private String secondInterviewerId;
	private String round2Decision;
	private String round2Rating;
	private String round2Remarks;
	private String round2completedDt;
	private String thirdInterviewerId;
	private String round3Decision;
	private String round3Rating;
	private String round3Remarks;
	private String round3completedDt;
	public InterviewResults() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InterviewResults(String incidentId, String firstName, String lastName, String email, String mobileNumber,
			String expType, String isInterviewEligible, String finalInterviewStatus, String finalDecisionRemarks,
			String firstInterviewerId, String round1Decision, String round1Rating, String round1Remarks,
			String round1completedDt, String secondInterviewerId, String round2Decision, String round2Rating,
			String round2Remarks, String round2completedDt, String thirdInterviewerId, String round3Decision,
			String round3Rating, String round3Remarks, String round3completedDt) {
		super();
		this.incidentId = incidentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.expType = expType;
		this.isInterviewEligible = isInterviewEligible;
		this.finalInterviewStatus = finalInterviewStatus;
		this.finalDecisionRemarks = finalDecisionRemarks;
		this.firstInterviewerId = firstInterviewerId;
		this.round1Decision = round1Decision;
		this.round1Rating = round1Rating;
		this.round1Remarks = round1Remarks;
		this.round1completedDt = round1completedDt;
		this.secondInterviewerId = secondInterviewerId;
		this.round2Decision = round2Decision;
		this.round2Rating = round2Rating;
		this.round2Remarks = round2Remarks;
		this.round2completedDt = round2completedDt;
		this.thirdInterviewerId = thirdInterviewerId;
		this.round3Decision = round3Decision;
		this.round3Rating = round3Rating;
		this.round3Remarks = round3Remarks;
		this.round3completedDt = round3completedDt;
	}
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
	public String getIsInterviewEligible() {
		return isInterviewEligible;
	}
	public void setIsInterviewEligible(String isInterviewEligible) {
		this.isInterviewEligible = isInterviewEligible;
	}
	public String getFinalInterviewStatus() {
		return finalInterviewStatus;
	}
	public void setFinalInterviewStatus(String finalInterviewStatus) {
		this.finalInterviewStatus = finalInterviewStatus;
	}
	public String getFinalDecisionRemarks() {
		return finalDecisionRemarks;
	}
	public void setFinalDecisionRemarks(String finalDecisionRemarks) {
		this.finalDecisionRemarks = finalDecisionRemarks;
	}
	public String getFirstInterviewerId() {
		return firstInterviewerId;
	}
	public void setFirstInterviewerId(String firstInterviewerId) {
		this.firstInterviewerId = firstInterviewerId;
	}
	public String getRound1Decision() {
		return round1Decision;
	}
	public void setRound1Decision(String round1Decision) {
		this.round1Decision = round1Decision;
	}
	public String getRound1Rating() {
		return round1Rating;
	}
	public void setRound1Rating(String round1Rating) {
		this.round1Rating = round1Rating;
	}
	public String getRound1Remarks() {
		return round1Remarks;
	}
	public void setRound1Remarks(String round1Remarks) {
		this.round1Remarks = round1Remarks;
	}
	public String getRound1completedDt() {
		return round1completedDt;
	}
	public void setRound1completedDt(String round1completedDt) {
		this.round1completedDt = round1completedDt;
	}
	public String getSecondInterviewerId() {
		return secondInterviewerId;
	}
	public void setSecondInterviewerId(String secondInterviewerId) {
		this.secondInterviewerId = secondInterviewerId;
	}
	public String getRound2Decision() {
		return round2Decision;
	}
	public void setRound2Decision(String round2Decision) {
		this.round2Decision = round2Decision;
	}
	public String getRound2Rating() {
		return round2Rating;
	}
	public void setRound2Rating(String round2Rating) {
		this.round2Rating = round2Rating;
	}
	public String getRound2Remarks() {
		return round2Remarks;
	}
	public void setRound2Remarks(String round2Remarks) {
		this.round2Remarks = round2Remarks;
	}
	public String getRound2completedDt() {
		return round2completedDt;
	}
	public void setRound2completedDt(String round2completedDt) {
		this.round2completedDt = round2completedDt;
	}
	public String getThirdInterviewerId() {
		return thirdInterviewerId;
	}
	public void setThirdInterviewerId(String thirdInterviewerId) {
		this.thirdInterviewerId = thirdInterviewerId;
	}
	public String getRound3Decision() {
		return round3Decision;
	}
	public void setRound3Decision(String round3Decision) {
		this.round3Decision = round3Decision;
	}
	public String getRound3Rating() {
		return round3Rating;
	}
	public void setRound3Rating(String round3Rating) {
		this.round3Rating = round3Rating;
	}
	public String getRound3Remarks() {
		return round3Remarks;
	}
	public void setRound3Remarks(String round3Remarks) {
		this.round3Remarks = round3Remarks;
	}
	public String getRound3completedDt() {
		return round3completedDt;
	}
	public void setRound3completedDt(String round3completedDt) {
		this.round3completedDt = round3completedDt;
	}
	@Override
	public String toString() {
		return "InterviewResults [incidentId=" + incidentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", expType=" + expType
				+ ", isInterviewEligible=" + isInterviewEligible + ", finalInterviewStatus=" + finalInterviewStatus
				+ ", finalDecisionRemarks=" + finalDecisionRemarks + ", firstInterviewerId=" + firstInterviewerId
				+ ", round1Decision=" + round1Decision + ", round1Rating=" + round1Rating + ", round1Remarks="
				+ round1Remarks + ", round1completedDt=" + round1completedDt + ", secondInterviewerId="
				+ secondInterviewerId + ", round2Decision=" + round2Decision + ", round2Rating=" + round2Rating
				+ ", round2Remarks=" + round2Remarks + ", round2completedDt=" + round2completedDt
				+ ", thirdInterviewerId=" + thirdInterviewerId + ", round3Decision=" + round3Decision
				+ ", round3Rating=" + round3Rating + ", round3Remarks=" + round3Remarks + ", round3completedDt="
				+ round3completedDt + "]";
	}
	

	
}

package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

@Entity

@Builder
@Scope("prototype")
@Table(name="t_incident_test_history")
public class IncidentTestHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="end_time")
	private String endTime;
	@Column(name="invigilator_comments")
	private String invigilatorComments;
	@Column(name="invigilator_id")
	private Integer invigilatorId;
	@Column(name="is_enabled")
	private Character isEnabled;
	@Column(name="no_of_questions")
	private String noOfQuestions;
	private String scoreMedian;
	@Column(name="participant_comment")
	private String participantComment;
	@Column(name="participant_id")
	private Integer participantId; 
	@Column(name = "pass_percentage")
	private String passPercentage;
	@Column(name="start_time")
	private String startTime;
	@Column(name="test_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime testDate; 
	@Column(name="test_status")
	private String testStatus;
	@Column(name="total_correct")
	private String totalCorrect;
	@Column(name="total_un_attended")
	private String totalUnAttended;
	@Column(name="total_wrong")
	private String totalWrong;
	@Column(name="total_result")
	private String totalResult;
	@Column(name="score")
	private String score;
	@Column(name="t_incident_id")
	private Integer incidentId;
	@Formula("(select inc.incident_name from t_incident inc join t_incident_test tt on tt.t_incident_id=inc.id where tt.ID=id)")
	private String incidentName;
	@Column(name="created_by")
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@Column(name="modified_by")
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Transient
	private String remainingTime;
	private String duration;
	@Column(name="incident_tt_id")
	private String incidentTtId;
	@Formula("(select inc.applicant_id from t_incident_applicants inc where inc.id=participant_id)")
	private String applicantId;
	@Formula("(select concat(inc.first_name,' ',inc.last_name) from t_incident_applicants inc where inc.id=participant_id)")
	private String applicantName;
	@Formula("(select tt.m_test_template_id from t_incident_tt tt where tt.id=incident_tt_id)")
	private String testTempLateId;
	@Formula("(select mm.test_template_name from m_test_template mm join t_incident_tt tt on mm.id=tt.m_test_template_id where tt.id=incident_tt_id )")
	private String testTemplateName;
	private String testResult;
	private String totalPositiveMarkObtained;
	private String totalNegativeMarkObtained;
	private String totalCorrectEasy;
	private String totalCorrectMedium;
	private String totalCorrectTough;
	private Integer incidentTestId;
	
	public IncidentTestHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncidentTestHistory(Integer id, String endTime, String invigilatorComments, Integer invigilatorId,
			Character isEnabled, String noOfQuestions, String scoreMedian, String participantComment,
			Integer participantId, String passPercentage, String startTime, LocalDateTime testDate, String testStatus,
			String totalCorrect, String totalUnAttended, String totalWrong, String totalResult, String score,
			Integer incidentId, String incidentName, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt, String remainingTime, String duration, String incidentTtId, String applicantId,
			String applicantName, String testTempLateId, String testTemplateName, String testResult,
			String totalPositiveMarkObtained, String totalNegativeMarkObtained, String totalCorrectEasy,
			String totalCorrectMedium, String totalCorrectTough, Integer incidentTestId) {
		super();
		this.id = id;
		this.endTime = endTime;
		this.invigilatorComments = invigilatorComments;
		this.invigilatorId = invigilatorId;
		this.isEnabled = isEnabled;
		this.noOfQuestions = noOfQuestions;
		this.scoreMedian = scoreMedian;
		this.participantComment = participantComment;
		this.participantId = participantId;
		this.passPercentage = passPercentage;
		this.startTime = startTime;
		this.testDate = testDate;
		this.testStatus = testStatus;
		this.totalCorrect = totalCorrect;
		this.totalUnAttended = totalUnAttended;
		this.totalWrong = totalWrong;
		this.totalResult = totalResult;
		this.score = score;
		this.incidentId = incidentId;
		this.incidentName = incidentName;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.remainingTime = remainingTime;
		this.duration = duration;
		this.incidentTtId = incidentTtId;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.testTempLateId = testTempLateId;
		this.testTemplateName = testTemplateName;
		this.testResult = testResult;
		this.totalPositiveMarkObtained = totalPositiveMarkObtained;
		this.totalNegativeMarkObtained = totalNegativeMarkObtained;
		this.totalCorrectEasy = totalCorrectEasy;
		this.totalCorrectMedium = totalCorrectMedium;
		this.totalCorrectTough = totalCorrectTough;
		this.incidentTestId = incidentTestId;
	}

	@Override
	public String toString() {
		return "IncidentTestHistory [id=" + id + ", endTime=" + endTime + ", invigilatorComments=" + invigilatorComments
				+ ", invigilatorId=" + invigilatorId + ", isEnabled=" + isEnabled + ", noOfQuestions=" + noOfQuestions
				+ ", scoreMedian=" + scoreMedian + ", participantComment=" + participantComment + ", participantId="
				+ participantId + ", passPercentage=" + passPercentage + ", startTime=" + startTime + ", testDate="
				+ testDate + ", testStatus=" + testStatus + ", totalCorrect=" + totalCorrect + ", totalUnAttended="
				+ totalUnAttended + ", totalWrong=" + totalWrong + ", totalResult=" + totalResult + ", score=" + score
				+ ", incidentId=" + incidentId + ", incidentName=" + incidentName + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", remainingTime=" + remainingTime + ", duration=" + duration + ", incidentTtId=" + incidentTtId
				+ ", applicantId=" + applicantId + ", applicantName=" + applicantName + ", testTempLateId="
				+ testTempLateId + ", testTemplateName=" + testTemplateName + ", testResult=" + testResult
				+ ", totalPositiveMarkObtained=" + totalPositiveMarkObtained + ", totalNegativeMarkObtained="
				+ totalNegativeMarkObtained + ", totalCorrectEasy=" + totalCorrectEasy + ", totalCorrectMedium="
				+ totalCorrectMedium + ", totalCorrectTough=" + totalCorrectTough + ", incidentTestId=" + incidentTestId
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getInvigilatorComments() {
		return invigilatorComments;
	}

	public void setInvigilatorComments(String invigilatorComments) {
		this.invigilatorComments = invigilatorComments;
	}

	public Integer getInvigilatorId() {
		return invigilatorId;
	}

	public void setInvigilatorId(Integer invigilatorId) {
		this.invigilatorId = invigilatorId;
	}

	public Character getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Character isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(String noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public String getScoreMedian() {
		return scoreMedian;
	}

	public void setScoreMedian(String scoreMedian) {
		this.scoreMedian = scoreMedian;
	}

	public String getParticipantComment() {
		return participantComment;
	}

	public void setParticipantComment(String participantComment) {
		this.participantComment = participantComment;
	}

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public String getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(String passPercentage) {
		this.passPercentage = passPercentage;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
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

	public String getTotalUnAttended() {
		return totalUnAttended;
	}

	public void setTotalUnAttended(String totalUnAttended) {
		this.totalUnAttended = totalUnAttended;
	}

	public String getTotalWrong() {
		return totalWrong;
	}

	public void setTotalWrong(String totalWrong) {
		this.totalWrong = totalWrong;
	}

	public String getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(String totalResult) {
		this.totalResult = totalResult;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Integer getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(Integer incidentId) {
		this.incidentId = incidentId;
	}

	public String getIncidentName() {
		return incidentName;
	}

	public void setIncidentName(String incidentName) {
		this.incidentName = incidentName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getIncidentTtId() {
		return incidentTtId;
	}

	public void setIncidentTtId(String incidentTtId) {
		this.incidentTtId = incidentTtId;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getTestTempLateId() {
		return testTempLateId;
	}

	public void setTestTempLateId(String testTempLateId) {
		this.testTempLateId = testTempLateId;
	}

	public String getTestTemplateName() {
		return testTemplateName;
	}

	public void setTestTemplateName(String testTemplateName) {
		this.testTemplateName = testTemplateName;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getTotalPositiveMarkObtained() {
		return totalPositiveMarkObtained;
	}

	public void setTotalPositiveMarkObtained(String totalPositiveMarkObtained) {
		this.totalPositiveMarkObtained = totalPositiveMarkObtained;
	}

	public String getTotalNegativeMarkObtained() {
		return totalNegativeMarkObtained;
	}

	public void setTotalNegativeMarkObtained(String totalNegativeMarkObtained) {
		this.totalNegativeMarkObtained = totalNegativeMarkObtained;
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

	public Integer getIncidentTestId() {
		return incidentTestId;
	}

	public void setIncidentTestId(Integer incidentTestId) {
		this.incidentTestId = incidentTestId;
	}
	
	
}

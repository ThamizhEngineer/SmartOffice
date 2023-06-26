package com.ss.smartoffice.sonotificationservice.incidentsummary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name="t_incident_tt_questions")
public class IncidentTestTemplateQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_incident_id")
	private Integer incidentId;
	@Column(name = "t_incident_tt_id")
	private Integer incidentTestTemplateId;
	@Column(name = "m_question_id")
	private Integer questionId;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String remark;
	private String difficultyLevel;
	private Integer testCategoryId;
	private String question;
	@Column(name="option_1")
	private String option1;
	@Column(name="option_2")
	private String option2;
	@Column(name="option_3")
	private String option3;
	@Column(name="option_4")
	private String option4;
	private String correctAnswer;
	
	public IncidentTestTemplateQuestion() {
		super();
	}

	public IncidentTestTemplateQuestion(Integer id, Integer incidentId, Integer incidentTestTemplateId,
			Integer questionId, String isEnabled, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt, String remark, String difficultyLevel, Integer testCategoryId, String question,
			String option1, String option2, String option3, String option4, String correctAnswer) {
		super();
		this.id = id;
		this.incidentId = incidentId;
		this.incidentTestTemplateId = incidentTestTemplateId;
		this.questionId = questionId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.remark = remark;
		this.difficultyLevel = difficultyLevel;
		this.testCategoryId = testCategoryId;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		return "IncidentTestTemplateQuestion [id=" + id + ", incidentId=" + incidentId + ", incidentTestTemplateId="
				+ incidentTestTemplateId + ", questionId=" + questionId + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", remark=" + remark + ", difficultyLevel=" + difficultyLevel + ", testCategoryId=" + testCategoryId
				+ ", question=" + question + ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3
				+ ", option4=" + option4 + ", correctAnswer=" + correctAnswer + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(Integer incidentId) {
		this.incidentId = incidentId;
	}

	public Integer getIncidentTestTemplateId() {
		return incidentTestTemplateId;
	}

	public void setIncidentTestTemplateId(Integer incidentTestTemplateId) {
		this.incidentTestTemplateId = incidentTestTemplateId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Integer getTestCategoryId() {
		return testCategoryId;
	}

	public void setTestCategoryId(Integer testCategoryId) {
		this.testCategoryId = testCategoryId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}

package com.ss.smartoffice.sonotificationservice.incidentsummary;

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

@Entity
@Table(name="t_incident_test_question")

@Scope("prototype")
public class IncidentTestQuestion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_incident_test_id")
	private Integer tIncidentTestId;
	@Column(name="Question")
	private String Question;
	@Column(name="correct_option")
	private String correctOption;
	@Column(name="option_1")
	private String option1;
	@Column(name="option_2")
	private String option2;
	@Column(name="option_3")
	private String option3;
	@Column(name="option_4")
	private String option4;
	@Column(name="user_picked")
	private String userPicked;
	@Column(name="difficulty_level")
	private String difficultyLevel;
	@Column(name="mark_per_question")
	private String markPerQuestion;
	@Column(name="marks_awarded")
	private String marksAwarded;
	@Column(name="negative_marks")
	private String negativeMarks;
	@Column(name="Options_correct")
	private String OptionsCorrect;
	@Column(name="M_question_id")
	private Integer questionId;
	@Transient
	private String testStatus;
	@Transient
	private String remainingTime;
	@Formula("(select it.duration from t_incident_test it where it.id=t_incident_test_id)")
	private String duration;
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
	private Integer questionOrder;
	
	public IncidentTestQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncidentTestQuestion(Integer id, Integer tIncidentTestId, String question, String correctOption,
			String option1, String option2, String option3, String option4, String userPicked, String difficultyLevel,
			String markPerQuestion, String marksAwarded, String negativeMarks, String optionsCorrect,
			Integer questionId, String testStatus, String remainingTime, String duration, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt, Integer questionOrder) {
		super();
		this.id = id;
		this.tIncidentTestId = tIncidentTestId;
		Question = question;
		this.correctOption = correctOption;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.userPicked = userPicked;
		this.difficultyLevel = difficultyLevel;
		this.markPerQuestion = markPerQuestion;
		this.marksAwarded = marksAwarded;
		this.negativeMarks = negativeMarks;
		OptionsCorrect = optionsCorrect;
		this.questionId = questionId;
		this.testStatus = testStatus;
		this.remainingTime = remainingTime;
		this.duration = duration;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.questionOrder = questionOrder;
	}




	@Override
	public String toString() {
		return "IncidentTestQuestion [id=" + id + ", tIncidentTestId=" + tIncidentTestId + ", Question=" + Question
				+ ", correctOption=" + correctOption + ", option1=" + option1 + ", option2=" + option2 + ", option3="
				+ option3 + ", option4=" + option4 + ", userPicked=" + userPicked + ", difficultyLevel="
				+ difficultyLevel + ", markPerQuestion=" + markPerQuestion + ", marksAwarded=" + marksAwarded
				+ ", negativeMarks=" + negativeMarks + ", OptionsCorrect=" + OptionsCorrect + ", questionId="
				+ questionId + ", testStatus=" + testStatus + ", remainingTime=" + remainingTime + ", duration="
				+ duration + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", questionOrder=" + questionOrder + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer gettIncidentTestId() {
		return tIncidentTestId;
	}

	public void settIncidentTestId(Integer tIncidentTestId) {
		this.tIncidentTestId = tIncidentTestId;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
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

	public String getUserPicked() {
		return userPicked;
	}

	public void setUserPicked(String userPicked) {
		this.userPicked = userPicked;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getMarkPerQuestion() {
		return markPerQuestion;
	}

	public void setMarkPerQuestion(String markPerQuestion) {
		this.markPerQuestion = markPerQuestion;
	}

	public String getMarksAwarded() {
		return marksAwarded;
	}

	public void setMarksAwarded(String marksAwarded) {
		this.marksAwarded = marksAwarded;
	}

	public String getNegativeMarks() {
		return negativeMarks;
	}

	public void setNegativeMarks(String negativeMarks) {
		this.negativeMarks = negativeMarks;
	}

	public String getOptionsCorrect() {
		return OptionsCorrect;
	}

	public void setOptionsCorrect(String optionsCorrect) {
		OptionsCorrect = optionsCorrect;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
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

	public Integer getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}
}

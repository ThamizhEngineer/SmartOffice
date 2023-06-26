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

import lombok.Data;

@Entity
@Table(name="t_incident_test_question")

@Scope("prototype")
@Data
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
	private String totalQuestions;
//	new
	@Column(name="m_category_id")
	private String categoryId;
	@Column(name="m_category_name")
	private String categoryName;
	private String positiveMarkObtained;
	private String negativeMarkObtained;
}

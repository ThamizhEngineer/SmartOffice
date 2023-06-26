package com.ss.smartoffice.soservice.transaction.incident;

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

import lombok.Data;

@Entity

@Table(name="t_incident_tt_questions")
@Data
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
//	New
	private String markPerQuestion;
	@Column(name="negative_mark")
	private String negativeMark;
	private String testCategoryName;
	private String passPercentage;
		
}

package com.ss.smartoffice.shared.model;

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
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name = "m_question")
@Data
@NoArgsConstructor
@ToString
public class Question {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		public Integer id;
		@Column(name = "m_test_category_id")
		private String testCategoryId;
		private String questionCode;
		private String difficultyLevel;
		private String question;
		@Column(name = "option_1")
		private String option1;
		@Column(name = "option_2")
		private String option2;
		@Column(name = "option_3")
		private String option3;
		@Column(name = "option_4")
		private String option4;
		private String isCorrect;
		private String isEnabled;
		private String createdBy;
		private String modifiedBy;
		@CreationTimestamp
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createdDt;
		@UpdateTimestamp
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifiedDt;
		private String remarks;
		private String isClean;
		
}

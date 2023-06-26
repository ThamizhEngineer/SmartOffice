package com.ss.smartoffice.soservice.master.testtemplate;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_test_template_catagory")

@Scope("prototype")
public class TestTemplateCatagory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="m_test_template_id")
	private Integer mTestTemplateId;
	
	@Column(name="m_test_catagory_id")
	private Integer mTestCatagoryId;
	@Column(name="difficulty_code")
	private String difficultyCode;
	@Column(name="negative_marking")
	private String negativeMarking;
	@Column(name="marks_per_question")
	private String marksPerQuestion;
	@Column(name="pass_percentage")
	private String passPercentage;
	@Column(name="total_questions")
	private String totalQuestions;
	@Column(name="created_by")
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	@Column(name="modified_by")
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
//	@Column(name="m_test_catagory_name")
	@Formula("(SELECT ct.test_category_name FROM m_test_template_catagory tc JOIN m_test_category ct ON tc.m_test_catagory_id=ct.ID WHERE tc.ID=id)")
	private String mTestCatagoryName;

	
	public TestTemplateCatagory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TestTemplateCatagory(Integer id, Integer mTestTemplateId, Integer mTestCatagoryId, String difficultyCode,
			String negativeMarking, String marksPerQuestion, String passPercentage, String totalQuestions,
			String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate,
			String mTestCatagoryName) {
		super();
		this.id = id;
		this.mTestTemplateId = mTestTemplateId;
		this.mTestCatagoryId = mTestCatagoryId;
		this.difficultyCode = difficultyCode;
		this.negativeMarking = negativeMarking;
		this.marksPerQuestion = marksPerQuestion;
		this.passPercentage = passPercentage;
		this.totalQuestions = totalQuestions;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.mTestCatagoryName = mTestCatagoryName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getmTestTemplateId() {
		return mTestTemplateId;
	}


	public void setmTestTemplateId(Integer mTestTemplateId) {
		this.mTestTemplateId = mTestTemplateId;
	}


	public Integer getmTestCatagoryId() {
		return mTestCatagoryId;
	}


	public void setmTestCatagoryId(Integer mTestCatagoryId) {
		this.mTestCatagoryId = mTestCatagoryId;
	}


	public String getDifficultyCode() {
		return difficultyCode;
	}


	public void setDifficultyCode(String difficultyCode) {
		this.difficultyCode = difficultyCode;
	}


	public String getNegativeMarking() {
		return negativeMarking;
	}


	public void setNegativeMarking(String negativeMarking) {
		this.negativeMarking = negativeMarking;
	}


	public String getMarksPerQuestion() {
		return marksPerQuestion;
	}


	public void setMarksPerQuestion(String marksPerQuestion) {
		this.marksPerQuestion = marksPerQuestion;
	}


	public String getPassPercentage() {
		return passPercentage;
	}


	public void setPassPercentage(String passPercentage) {
		this.passPercentage = passPercentage;
	}


	public String getTotalQuestions() {
		return totalQuestions;
	}


	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getmTestCatagoryName() {
		return mTestCatagoryName;
	}


	public void setmTestCatagoryName(String mTestCatagoryName) {
		this.mTestCatagoryName = mTestCatagoryName;
	}


	@Override
	public String toString() {
		return "TestTemplateCatagory [id=" + id + ", mTestTemplateId=" + mTestTemplateId + ", mTestCatagoryId="
				+ mTestCatagoryId + ", difficultyCode=" + difficultyCode + ", negativeMarking=" + negativeMarking
				+ ", marksPerQuestion=" + marksPerQuestion + ", passPercentage=" + passPercentage + ", totalQuestions="
				+ totalQuestions + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", mTestCatagoryName=" + mTestCatagoryName + "]";
	}


	

	
	

}

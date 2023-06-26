package com.ss.smartoffice.soservice.master.testtemplate;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="m_test_template")

@Scope("prototype")
public class TestTemplate {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="test_template_name")
	private String testTemplateName;
	@Column(name="description")
	private String description;
	@Column(name="duration")
	private String duration;
	
	@Column(name="total_questions")
	private String totalQuestions;
	@Column(name="pass_percentage")
	private String passPercentage;
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
	
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="m_test_template_id")
	private List<TestTemplateCatagory> testTemplateCatagory;
	
	public TestTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestTemplate(Integer id, String testTemplateName, String description, String duration, String totalQuestions,
			String passPercentage, String createdBy, LocalDateTime createdDate, String modifiedBy,
			LocalDateTime modifiedDate, List<TestTemplateCatagory> testTemplateCatagory) {
		super();
		this.id = id;
		this.testTemplateName = testTemplateName;
		this.description = description;
		this.duration = duration;
		this.totalQuestions = totalQuestions;
		this.passPercentage = passPercentage;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.testTemplateCatagory = testTemplateCatagory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestTemplateName() {
		return testTemplateName;
	}

	public void setTestTemplateName(String testTemplateName) {
		this.testTemplateName = testTemplateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public String getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(String passPercentage) {
		this.passPercentage = passPercentage;
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

	public List<TestTemplateCatagory> getTestTemplateCatagory() {
		return testTemplateCatagory;
	}

	public void setTestTemplateCatagory(List<TestTemplateCatagory> testTemplateCatagory) {
		this.testTemplateCatagory = testTemplateCatagory;
	}

	@Override
	public String toString() {
		return "TestTemplate [id=" + id + ", testTemplateName=" + testTemplateName + ", description=" + description
				+ ", duration=" + duration + ", totalQuestions=" + totalQuestions + ", passPercentage=" + passPercentage
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", testTemplateCatagory=" + testTemplateCatagory + "]";
	}

	
		
	

}

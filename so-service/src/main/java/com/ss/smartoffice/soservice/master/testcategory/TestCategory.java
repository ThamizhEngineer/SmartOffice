package com.ss.smartoffice.soservice.master.testcategory;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_test_category")
@Component
public class TestCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String testCategoryName;
	@Formula("(select COUNT(mque.difficulty_level) from m_question mque where mque.difficulty_level='EASY' AND mque.m_test_category_id=id)")
	private String easy;
	@Formula("(select COUNT(mque.difficulty_level) from m_question mque where mque.difficulty_level='MEDIUM' AND mque.m_test_category_id=id)")
	private String medium;
	@Formula("(select COUNT(mque.difficulty_level) from m_question mque where mque.difficulty_level='TOUGH' AND mque.m_test_category_id=id)")
	private String tough;
	@Formula("(select COUNT(mque.difficulty_level) from m_question mque where mque.m_test_category_id=id)")
	private String total;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public TestCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public TestCategory(Integer id, String testCategoryName, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.testCategoryName = testCategoryName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getTestCategoryName() {
		return testCategoryName;
	}



	public void setTestCategoryName(String testCategoryName) {
		this.testCategoryName = testCategoryName;
	}



	public String getEasy() {
		return easy;
	}



	public void setEasy(String easy) {
		this.easy = easy;
	}



	public String getMedium() {
		return medium;
	}



	public void setMedium(String medium) {
		this.medium = medium;
	}



	public String getTough() {
		return tough;
	}



	public void setTough(String tough) {
		this.tough = tough;
	}



	public String getTotal() {
		return total;
	}



	public void setTotal(String total) {
		this.total = total;
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



	public String getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}



	public LocalDateTime getCreatedDt() {
		return createdDt;
	}



	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}



	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}



	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}



	@Override
	public String toString() {
		return "TestCategory [id=" + id + ", testCategoryName=" + testCategoryName + ", easy=" + easy + ", medium="
				+ medium + ", tough=" + tough + ", total=" + total + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}


}

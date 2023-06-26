package com.ss.smartoffice.soservice.transaction.skillmatrix;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_skill_matrix_req_skills")
public class SkillMatrixRequestSkills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String skillMatrixHdrKey;
	private String productId;
	@Formula("(SELECT product.material_name FROM m_material product WHERE product.id=product_id)")
	private String productName;
	private String serviceId;
	@Formula("(select service.service_name from m_service service left join m_material_service mat on service.id = mat.ability_id where mat.id=service_id)")
	private String serviceName;
	private String isEnabled;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private Integer expectedCount=0;
	private Integer availableCount=0;
	private Integer gapCount;

	public SkillMatrixRequestSkills() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillMatrixRequestSkills(Integer id, String skillMatrixHdrKey, String productId, String productName,
			String serviceId, String serviceName, String isEnabled, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt, Integer expectedCount, Integer availableCount, Integer gapCount) {
		super();
		this.id = id;
		this.skillMatrixHdrKey = skillMatrixHdrKey;
		this.productId = productId;
		this.productName = productName;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.isEnabled = isEnabled;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.expectedCount = expectedCount;
		this.availableCount = availableCount;
		this.gapCount = gapCount;
	}

	@Override
	public String toString() {
		return "SkillMatrixRequestSkills [id=" + id + ", skillMatrixHdrKey=" + skillMatrixHdrKey + ", productId="
				+ productId + ", productName=" + productName + ", serviceId=" + serviceId + ", serviceName="
				+ serviceName + ", isEnabled=" + isEnabled + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", expectedCount=" + expectedCount + ", availableCount="
				+ availableCount + ", gapCount=" + gapCount + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillMatrixHdrKey() {
		return skillMatrixHdrKey;
	}

	public void setSkillMatrixHdrKey(String skillMatrixHdrKey) {
		this.skillMatrixHdrKey = skillMatrixHdrKey;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
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

	public Integer getExpectedCount() {
		return expectedCount;
	}

	public void setExpectedCount(Integer expectedCount) {
		this.expectedCount = expectedCount;
	}

	public Integer getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}

	public Integer getGapCount() {
		return gapCount;
	}

	public void setGapCount(Integer gapCount) {
		this.gapCount = gapCount;
	}
	
	
	
}

package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SkillMatrixRequestSkills {
	private Integer id;
	private String skillMatrixHdrKey;
	private String productId;
	private String productName;
	private String serviceId;
	private String serviceName;
	private String isEnabled;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private Integer expectedCount;
	private Integer availableCount;
	private Integer gapCount;

	public SkillMatrixRequestSkills() {
		super();
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

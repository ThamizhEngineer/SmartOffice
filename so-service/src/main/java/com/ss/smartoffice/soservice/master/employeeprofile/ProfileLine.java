package com.ss.smartoffice.soservice.master.employeeprofile;

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
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="m_profile_line")

@Scope("prototype")
public class ProfileLine extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="profile_id")
	private String profileId;
	@Column(name="service_id")
	private String ServiceId;
	@Column(name="product_id")
	private String ProductId;
	private String skillLevel;
	
	@Formula("(select service.service_name from m_service service left join m_material_service mat on service.id = mat.ability_id where mat.id=service_id)")
	private String serviceName;
	
	@Formula("(SELECT product.material_name FROM m_material product WHERE product.id=product_id)")
	private String productName;
	
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ProfileLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProfileLine(int id, String profileId, String serviceId, String productId, String skillLevel,
			String serviceName, String productName, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.profileId = profileId;
		ServiceId = serviceId;
		ProductId = productId;
		this.skillLevel = skillLevel;
		this.serviceName = serviceName;
		this.productName = productName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getServiceId() {
		return ServiceId;
	}
	public void setServiceId(String serviceId) {
		ServiceId = serviceId;
	}
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
		return "ProfileLine [id=" + id + ", profileId=" + profileId + ", ServiceId=" + ServiceId + ", ProductId="
				+ ProductId + ", skillLevel=" + skillLevel + ", serviceName=" + serviceName + ", productName="
				+ productName + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	

}

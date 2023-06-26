package com.ss.smartoffice.soservice.master.manufacturer;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;


@Entity
@Table(name="m_manufacturer")


@Scope("prototype")
public class Manufacturer extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String manufacturerCode;
	private String manufacturerName;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="m_manufacturer_id")    
	private List<ProductFamily> productFamilies ;
	@Formula("(select COUNT(*) from m_product_family productFamily where productFamily.m_manufacturer_id=id)")
	private String productFamilyCount;

	public Manufacturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manufacturer(int id, String manufacturerCode, String manufacturerName, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt, List<ProductFamily> productFamilies,
			String productFamilyCount) {
		super();
		this.id = id;
		this.manufacturerCode = manufacturerCode;
		this.manufacturerName = manufacturerName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.productFamilies = productFamilies;
		this.productFamilyCount = productFamilyCount;
	}

	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", manufacturerCode=" + manufacturerCode + ", manufacturerName="
				+ manufacturerName + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", productFamilies="
				+ productFamilies + ", productFamilyCount=" + productFamilyCount + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
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

	public List<ProductFamily> getProductFamilies() {
		return productFamilies;
	}

	public void setProductFamilies(List<ProductFamily> productFamilies) {
		this.productFamilies = productFamilies;
	}

	public String getProductFamilyCount() {
		return productFamilyCount;
	}

	public void setProductFamilyCount(String productFamilyCount) {
		this.productFamilyCount = productFamilyCount;
	}

	

	
}

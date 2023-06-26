package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_sale_service")

@Scope("prototype")
public class SaleService {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_sale_order_id")
	private String tSaleOrderId;
	private String serviceName;
	private String sacCode;
	private String serviceDescription;
	private String serviceAdditionalNotes;
	private String headCount;
	private String internalRemarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public SaleService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SaleService(Integer id, String tSaleOrderId, String serviceName, String sacCode, String serviceDescription,
			String serviceAdditionalNotes, String headCount, String internalRemarks, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tSaleOrderId = tSaleOrderId;
		this.serviceName = serviceName;
		this.sacCode = sacCode;
		this.serviceDescription = serviceDescription;
		this.serviceAdditionalNotes = serviceAdditionalNotes;
		this.headCount = headCount;
		this.internalRemarks = internalRemarks;
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
	public String gettSaleOrderId() {
		return tSaleOrderId;
	}
	public void settSaleOrderId(String tSaleOrderId) {
		this.tSaleOrderId = tSaleOrderId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSacCode() {
		return sacCode;
	}
	public void setSacCode(String sacCode) {
		this.sacCode = sacCode;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public String getServiceAdditionalNotes() {
		return serviceAdditionalNotes;
	}
	public void setServiceAdditionalNotes(String serviceAdditionalNotes) {
		this.serviceAdditionalNotes = serviceAdditionalNotes;
	}
	public String getHeadCount() {
		return headCount;
	}
	public void setHeadCount(String headCount) {
		this.headCount = headCount;
	}
	public String getInternalRemarks() {
		return internalRemarks;
	}
	public void setInternalRemarks(String internalRemarks) {
		this.internalRemarks = internalRemarks;
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
		return "SaleService [id=" + id + ", tSaleOrderId=" + tSaleOrderId + ", serviceName=" + serviceName
				+ ", sacCode=" + sacCode + ", serviceDescription=" + serviceDescription + ", serviceAdditionalNotes="
				+ serviceAdditionalNotes + ", headCount=" + headCount + ", internalRemarks=" + internalRemarks
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

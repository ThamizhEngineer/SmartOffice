package com.ss.smartoffice.soservice.transaction.model;

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
@Table(name="t_sale_good")

@Scope("prototype")
public class SaleGood {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_sale_order_id")
	private String tSaleOrderId;
	private String materialId;
	@Formula("(select material.material_name from m_material material where material.id=material_id )")
	private String goodsName;
	private String hsnCode;
	private String goodQty;
	private String goodUnitPrice;
	private String goodDueOn;
	private String goodAdditionalNotes;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public SaleGood() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SaleGood(Integer id, String tSaleOrderId, String goodsName, String hsnCode, String goodQty,
			String goodUnitPrice, String goodDueOn, String goodAdditionalNotes, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt, String materialId) {
		super();
		this.id = id;
		this.tSaleOrderId = tSaleOrderId;
		this.materialId=materialId;
		this.goodsName = goodsName;
		this.hsnCode = hsnCode;
		this.goodQty = goodQty;
		this.goodUnitPrice = goodUnitPrice;
		this.goodDueOn = goodDueOn;
		this.goodAdditionalNotes = goodAdditionalNotes;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getGoodQty() {
		return goodQty;
	}
	public void setGoodQty(String goodQty) {
		this.goodQty = goodQty;
	}
	public String getGoodUnitPrice() {
		return goodUnitPrice;
	}
	public void setGoodUnitPrice(String goodUnitPrice) {
		this.goodUnitPrice = goodUnitPrice;
	}
	public String getGoodDueOn() {
		return goodDueOn;
	}
	public void setGoodDueOn(String goodDueOn) {
		this.goodDueOn = goodDueOn;
	}
	public String getGoodAdditionalNotes() {
		return goodAdditionalNotes;
	}
	public void setGoodAdditionalNotes(String goodAdditionalNotes) {
		this.goodAdditionalNotes = goodAdditionalNotes;
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
		return "SaleGood [id=" + id + ", tSaleOrderId=" + tSaleOrderId + ", materialId=" + materialId + ", goodsName="
				+ goodsName + ", hsnCode=" + hsnCode + ", goodQty=" + goodQty + ", goodUnitPrice=" + goodUnitPrice
				+ ", goodDueOn=" + goodDueOn + ", goodAdditionalNotes=" + goodAdditionalNotes + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

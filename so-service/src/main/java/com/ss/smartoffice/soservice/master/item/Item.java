package com.ss.smartoffice.soservice.master.item;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="m_item")

public class Item {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String itemCode;
	private String referenceNumber;
	private String forPurchase;
	private String forSale;
	private String hsnSacCode;
	private String itemName;
	private String itemDesc;
	private float igstRate;
	private float cgstRate;
	private float sgstRate;
	private double unitPrice;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int id, String itemCode, String referenceNumber, String forPurchase, String forSale, String hsnSacCode,
			String itemName, String itemDesc, float igstRate, float cgstRate, float sgstRate, double unitPrice,
			String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.itemCode = itemCode;
		this.referenceNumber = referenceNumber;
		this.forPurchase = forPurchase;
		this.forSale = forSale;
		this.hsnSacCode = hsnSacCode;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.igstRate = igstRate;
		this.cgstRate = cgstRate;
		this.sgstRate = sgstRate;
		this.unitPrice = unitPrice;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getForPurchase() {
		return forPurchase;
	}

	public void setForPurchase(String forPurchase) {
		this.forPurchase = forPurchase;
	}

	public String getForSale() {
		return forSale;
	}

	public void setForSale(String forSale) {
		this.forSale = forSale;
	}

	public String getHsnSacCode() {
		return hsnSacCode;
	}

	public void setHsnSacCode(String hsnSacCode) {
		this.hsnSacCode = hsnSacCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public float getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(float igstRate) {
		this.igstRate = igstRate;
	}

	public float getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(float cgstRate) {
		this.cgstRate = cgstRate;
	}

	public float getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(float sgstRate) {
		this.sgstRate = sgstRate;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemCode=" + itemCode + ", referenceNumber=" + referenceNumber + ", forPurchase="
				+ forPurchase + ", forSale=" + forSale + ", hsnSacCode=" + hsnSacCode + ", itemName=" + itemName
				+ ", itemDesc=" + itemDesc + ", igstRate=" + igstRate + ", cgstRate=" + cgstRate + ", sgstRate="
				+ sgstRate + ", unitPrice=" + unitPrice + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

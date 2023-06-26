package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseClaimBill {
	private Integer id;
	private String expenseClaimId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate billDt;
	private String categoryId;
	private String categoryName;
	private String subCategoryId;
	private String subCategoryName;
	private String merchantId;
	private String merchantName;
	private String isBillable;
	private String billedOnCompanyName;
	private double entitledAmount;
	private double billAmount;
	private String billRemarks;
	private String docId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ExpenseClaimBill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExpenseClaimBill(Integer id, String expenseClaimId, LocalDate billDt, String categoryId, String categoryName,
			String subCategoryId, String subCategoryName, String merchantId, String merchantName, String isBillable,
			String billedOnCompanyName, double entitledAmount, double billAmount, String billRemarks, String docId,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.expenseClaimId = expenseClaimId;
		this.billDt = billDt;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.isBillable = isBillable;
		this.billedOnCompanyName = billedOnCompanyName;
		this.entitledAmount = entitledAmount;
		this.billAmount = billAmount;
		this.billRemarks = billRemarks;
		this.docId = docId;
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
	public String getExpenseClaimId() {
		return expenseClaimId;
	}
	public void setExpenseClaimId(String expenseClaimId) {
		this.expenseClaimId = expenseClaimId;
	}
	public LocalDate getBillDt() {
		return billDt;
	}
	public void setBillDt(LocalDate billDt) {
		this.billDt = billDt;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public double getEntitledAmount() {
		return entitledAmount;
	}
	public void setEntitledAmount(double entitledAmount) {
		this.entitledAmount = entitledAmount;
	}
	public String getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}
	public String getBilledOnCompanyName() {
		return billedOnCompanyName;
	}
	public void setBilledOnCompanyName(String billedOnCompanyName) {
		this.billedOnCompanyName = billedOnCompanyName;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillRemarks() {
		return billRemarks;
	}
	public void setBillRemarks(String billRemarks) {
		this.billRemarks = billRemarks;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
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
		return "ExpenseClaimBill [id=" + id + ", expenseClaimId=" + expenseClaimId + ", billDt=" + billDt
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", subCategoryId=" + subCategoryId
				+ ", subCategoryName=" + subCategoryName + ", merchantId=" + merchantId + ", merchantName="
				+ merchantName + ", isBillable=" + isBillable + ", billedOnCompanyName=" + billedOnCompanyName
				+ ", entitledAmount=" + entitledAmount + ", billAmount=" + billAmount + ", billRemarks=" + billRemarks
				+ ", docId=" + docId + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}

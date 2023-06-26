package com.ss.smartoffice.soservice.transaction.expenseclaim;

import java.time.LocalDate;
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
@Table(name="t_expense_claim_bill")
@Scope("prototype")
public class ExpenseClaimBill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_expense_claim_id")
	private String expenseClaimId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate billDt;
	private String categoryId;
	@Formula("(select cexp.category_name from m_expense_category cexp left join m_sub_expense_category subexp on cexp.id = subexp.category_id where subexp.id=sub_category_id)")
	private String categoryName;
	private String subCategoryId;
	@Formula("(select exp.sub_category_name from m_sub_expense_category exp where exp.id=sub_category_id)")
	private String subCategoryName;	
	@Formula("(select exp.is_billable from m_sub_expense_category exp where exp.id=sub_category_id)")
	private String isExpBillable;	
	private String merchantId;
	@Formula("(select merchant.merchant_name from m_merchant merchant where merchant.id=merchant_id)")
	private String merchantName;
	private String isBillable;
	private String billedOnCompanyName;
	private String billNo;
	private  double entitledAmount;
	private String billStatus;
	
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
			String subCategoryId, String subCategoryName, String isExpBillable, String merchantId, String merchantName,
			String isBillable, String billedOnCompanyName, String billNo, double entitledAmount, String billStatus,
			double billAmount, String billRemarks, String docId, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.expenseClaimId = expenseClaimId;
		this.billDt = billDt;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.isExpBillable = isExpBillable;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.isBillable = isBillable;
		this.billedOnCompanyName = billedOnCompanyName;
		this.billNo = billNo;
		this.entitledAmount = entitledAmount;
		this.billStatus = billStatus;
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
	public String getIsExpBillable() {
		return isExpBillable;
	}
	public void setIsExpBillable(String isExpBillable) {
		this.isExpBillable = isExpBillable;
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
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public double getEntitledAmount() {
		return entitledAmount;
	}
	public void setEntitledAmount(double entitledAmount) {
		this.entitledAmount = entitledAmount;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
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
				+ ", billNo=" + billNo + ", entitledAmount=" + entitledAmount + ", billStatus=" + billStatus
				+ ", billAmount=" + billAmount + ", billRemarks=" + billRemarks + ", docId=" + docId + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}
	
	
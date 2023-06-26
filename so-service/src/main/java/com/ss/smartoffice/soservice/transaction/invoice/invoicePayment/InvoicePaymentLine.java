package com.ss.smartoffice.soservice.transaction.invoice.invoicePayment;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_inv_pmt_line")
public class InvoicePaymentLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "payment_hdr_id")
	private String paymentHdrId;
	private String invoiceHdrId;
	@Formula("(select inv.invoice_date from t_invoice_hdr inv WHERE inv.id=invoice_hdr_id)")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	@Formula("(select inv.invoice_code from t_invoice_hdr inv WHERE inv.id=invoice_hdr_id)")
	private String invoiceCode;
	@Formula("(select inv.ref_invoice_no from t_invoice_hdr inv WHERE inv.id=invoice_hdr_id)")
	private String refInvoiceNo;
	private String paymentNote;
	private Float invoiceAmount = (float) 0;
	private Float dueAmt;
	private Float paidAmt = (float) 0 ;
	private Float adjPmtAmt;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public InvoicePaymentLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvoicePaymentLine(Integer id, String paymentHdrId, String invoiceHdrId, LocalDate date, String invoiceCode,
			String refInvoiceNo, String paymentNote, Float invoiceAmount, Float dueAmt, Float paidAmt, Float adjPmtAmt,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.paymentHdrId = paymentHdrId;
		this.invoiceHdrId = invoiceHdrId;
		this.date = date;
		this.invoiceCode = invoiceCode;
		this.refInvoiceNo = refInvoiceNo;
		this.paymentNote = paymentNote;
		this.invoiceAmount = invoiceAmount;
		this.dueAmt = dueAmt;
		this.paidAmt = paidAmt;
		this.adjPmtAmt = adjPmtAmt;
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
	public String getPaymentHdrId() {
		return paymentHdrId;
	}
	public void setPaymentHdrId(String paymentHdrId) {
		this.paymentHdrId = paymentHdrId;
	}
	public String getInvoiceHdrId() {
		return invoiceHdrId;
	}
	public void setInvoiceHdrId(String invoiceHdrId) {
		this.invoiceHdrId = invoiceHdrId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getRefInvoiceNo() {
		return refInvoiceNo;
	}
	public void setRefInvoiceNo(String refInvoiceNo) {
		this.refInvoiceNo = refInvoiceNo;
	}
	public Float getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(Float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Float getDueAmt() {
		return dueAmt;
	}
	public void setDueAmt(Float dueAmt) {
		this.dueAmt = dueAmt;
	}
	public Float getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(Float paidAmt) {
		this.paidAmt = paidAmt;
	}
	public String getPaymentNote() {
		return paymentNote;
	}
	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}
	public Float getAdjPmtAmt() {
		return adjPmtAmt;
	}
	public void setAdjPmtAmt(Float adjPmtAmt) {
		this.adjPmtAmt = adjPmtAmt;
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
		return "InvoicePaymentLine [id=" + id + ", paymentHdrId=" + paymentHdrId + ", invoiceHdrId=" + invoiceHdrId
				+ ", date=" + date + ", invoiceCode=" + invoiceCode + ", refInvoiceNo=" + refInvoiceNo
				+ ", paymentNote=" + paymentNote + ", invoiceAmount=" + invoiceAmount + ", dueAmt=" + dueAmt
				+ ", paidAmt=" + paidAmt + ", adjPmtAmt=" + adjPmtAmt + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	
	
}

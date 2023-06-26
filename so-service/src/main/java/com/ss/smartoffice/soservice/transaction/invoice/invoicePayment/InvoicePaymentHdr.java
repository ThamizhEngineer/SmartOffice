package com.ss.smartoffice.soservice.transaction.invoice.invoicePayment;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_inv_pmt_hdr")
public class InvoicePaymentHdr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String paymentCode;
	private String clientId;
	@Formula("(select partner.client_name from m_partner partner where partner.id=client_id)")
	private String clientName;
	private String amountReceived;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate paymentDt;
	private String paymentRefNo;
	private String paymentMode;
	private String paymentNote;
	private String docId;
	@Formula("(SELECT sum(line.invoice_amount) from t_inv_pmt_line line where line.payment_hdr_id=id)")
	private Float paymentAmount;
	@Formula("(SELECT sum(line.invoice_amount)-sum(line.paid_amt) from t_inv_pmt_line line where line.payment_hdr_id=id)")
	private Float amountBalance;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="payment_hdr_id")
	private List<InvoicePaymentLine> paymentLine;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public InvoicePaymentHdr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InvoicePaymentHdr(Integer id, String paymentCode, String clientId, String clientName, String amountReceived,
			LocalDate paymentDt, String paymentRefNo, String paymentMode, String paymentNote, String docId,
			Float paymentAmount, Float amountBalance, List<InvoicePaymentLine> paymentLine, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.paymentCode = paymentCode;
		this.clientId = clientId;
		this.clientName = clientName;
		this.amountReceived = amountReceived;
		this.paymentDt = paymentDt;
		this.paymentRefNo = paymentRefNo;
		this.paymentMode = paymentMode;
		this.paymentNote = paymentNote;
		this.docId = docId;
		this.paymentAmount = paymentAmount;
		this.amountBalance = amountBalance;
		this.paymentLine = paymentLine;
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
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
	}
	public LocalDate getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(LocalDate paymentDt) {
		this.paymentDt = paymentDt;
	}
	public String getPaymentRefNo() {
		return paymentRefNo;
	}
	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentNote() {
		return paymentNote;
	}
	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public Float getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Float getAmountBalance() {
		return amountBalance;
	}
	public void setAmountBalance(Float amountBalance) {
		this.amountBalance = amountBalance;
	}
	public List<InvoicePaymentLine> getPaymentLine() {
		return paymentLine;
	}
	public void setPaymentLine(List<InvoicePaymentLine> paymentLine) {
		this.paymentLine = paymentLine;
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
		return "InvoicePaymentHdr [id=" + id + ", paymentCode=" + paymentCode + ", clientId=" + clientId
				+ ", clientName=" + clientName + ", amountReceived=" + amountReceived + ", paymentDt=" + paymentDt
				+ ", paymentRefNo=" + paymentRefNo + ", paymentMode=" + paymentMode + ", paymentNote=" + paymentNote
				+ ", docId=" + docId + ", paymentAmount=" + paymentAmount + ", amountBalance=" + amountBalance
				+ ", paymentLine=" + paymentLine + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

package com.ss.smartoffice.soservice.transaction.clientpo;

import java.math.BigDecimal;
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
@Table(name="t_client_po")

@Scope("prototype")
public class ClientPurchaseOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    private String poRefNo;
    private String poDesc;
    private String clientId;
    @Formula("(select c.client_name from m_partner c where c.id = client_id)") 
    private String clientName; //referred using clientId
    @Formula("(select c.company_name from m_partner c where c.id = client_id)")
    private String companyName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate poDate;
    @Column(name="order_amount",columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal orderAmount;
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
	public ClientPurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientPurchaseOrder(Integer id, String poRefNo, String poDesc, String clientId, String clientName,
			String companyName, LocalDate poDate, BigDecimal orderAmount, String docId, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.poRefNo = poRefNo;
		this.poDesc = poDesc;
		this.clientId = clientId;
		this.clientName = clientName;
		this.companyName = companyName;
		this.poDate = poDate;
		this.orderAmount = orderAmount;
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
	
	public String getPoDesc() {
		return poDesc;
	}
	public void setPoDesc(String poDesc) {
		this.poDesc = poDesc;
	}
	public String getPoRefNo() {
		return poRefNo;
	}
	public void setPoRefNo(String poRefNo) {
		this.poRefNo = poRefNo;
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
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getPoDate() {
		return poDate;
	}
	public void setPoDate(LocalDate poDate) {
		this.poDate = poDate;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
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
		return "ClientPurchaseOrder [id=" + id + ", poRefNo=" + poRefNo + ", poDesc=" + poDesc + ", clientId="
				+ clientId + ", clientName=" + clientName + ", companyName=" + companyName + ", poDate=" + poDate
				+ ", orderAmount=" + orderAmount + ", docId=" + docId + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}

    
}

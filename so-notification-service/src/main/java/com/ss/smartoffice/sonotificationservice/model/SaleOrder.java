package com.ss.smartoffice.sonotificationservice.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;



public class SaleOrder extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String saleOrderCode;
	private String status;
	@Column(name="purchase_order_id")
	private String purchaseOrderId;
	@Column(name="division_id")
	private String divisionId;
	@Column(name="partner_id")
	private String partnerId;
	@Column(name="project_id")
	private String projectId;
	private String gstNo;
//	@Formula("(SELECT purchase.po_code FROM t_purc_order purchase where purchase.id=t_purchase_order_id)")
//	@Transient
//	private List<PurchaseOrder> purchaseOrder;
//	@Transient
//	private List<PartnerGst> partnerGst;
	private String locationId;
	@Formula("(SELECT map.lats FROM m_map_location map where map.id=location_id)")
	private String lats;
	@Formula("(SELECT map.longs FROM m_map_location map where map.id=location_id)")
	private String longs;
	@Formula("(SELECT p.proj_name FROM t_project p where p.id=project_id)")
    private String projectName;
	@Formula("(SELECT pa.client_name FROM m_partner pa where pa.id=partner_id)")
	private String partnerName;
	private String needsGoods;
	private String needsServices;
	private String emailId;
	private String saleOrderStatus;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime submittedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime approvedDt;
	@Column(name="m_2_approved_dt")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime m2ApprovedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime cancelledDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rejectedDt;
	@Column(name="m_2_rejected_dt")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime m2RejectedDt;
    private String approvedRemarks;
    @Column(name="m_2_approved_remarks")
	private String m2ApprovedRemarks;
	private String cancelledRemarks;
	private String rejectedRemarks;
	@Column(name="m_2_rejected_remarks")
	private String m2rejectedRemarks;
	private String managerEmpId;
	@Column(name="manager_2_emp_id")
	private String manager2EmpId;
	private String deliveryTerms;
	private String deliverySchedule;
	private String notificationStatus;
	private LocalDateTime notificationDt;
    private String shippingAddress;
	private String internalRemarks;
	private String paymentTerms;
	private String deleiveryTerms;
	private String deleiverySchedule;
	private String notificationContent;
	
	private String virtualPoNum;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public SaleOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SaleOrder(Integer id, String saleOrderCode, String status, String purchaseOrderId, String divisionId,
			String partnerId, String projectId, String gstNo, String locationId, String lats, String longs,
			String projectName, String partnerName, String needsGoods, String needsServices, String emailId,
			String saleOrderStatus, LocalDateTime submittedDt, LocalDateTime approvedDt, LocalDateTime m2ApprovedDt,
			LocalDateTime cancelledDt, LocalDateTime rejectedDt, LocalDateTime m2RejectedDt, String approvedRemarks,
			String m2ApprovedRemarks, String cancelledRemarks, String rejectedRemarks, String m2rejectedRemarks,
			String managerEmpId, String manager2EmpId, String deliveryTerms, String deliverySchedule,
			String notificationStatus, LocalDateTime notificationDt, String shippingAddress, String internalRemarks,
			String paymentTerms, String deleiveryTerms, String deleiverySchedule, String notificationContent,
			String virtualPoNum, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.saleOrderCode = saleOrderCode;
		this.status = status;
		this.purchaseOrderId = purchaseOrderId;
		this.divisionId = divisionId;
		this.partnerId = partnerId;
		this.projectId = projectId;
		this.gstNo = gstNo;
		this.locationId = locationId;
		this.lats = lats;
		this.longs = longs;
		this.projectName = projectName;
		this.partnerName = partnerName;
		this.needsGoods = needsGoods;
		this.needsServices = needsServices;
		this.emailId = emailId;
		this.saleOrderStatus = saleOrderStatus;
		this.submittedDt = submittedDt;
		this.approvedDt = approvedDt;
		this.m2ApprovedDt = m2ApprovedDt;
		this.cancelledDt = cancelledDt;
		this.rejectedDt = rejectedDt;
		this.m2RejectedDt = m2RejectedDt;
		this.approvedRemarks = approvedRemarks;
		this.m2ApprovedRemarks = m2ApprovedRemarks;
		this.cancelledRemarks = cancelledRemarks;
		this.rejectedRemarks = rejectedRemarks;
		this.m2rejectedRemarks = m2rejectedRemarks;
		this.managerEmpId = managerEmpId;
		this.manager2EmpId = manager2EmpId;
		this.deliveryTerms = deliveryTerms;
		this.deliverySchedule = deliverySchedule;
		this.notificationStatus = notificationStatus;
		this.notificationDt = notificationDt;
		this.shippingAddress = shippingAddress;
		this.internalRemarks = internalRemarks;
		this.paymentTerms = paymentTerms;
		this.deleiveryTerms = deleiveryTerms;
		this.deleiverySchedule = deleiverySchedule;
		this.notificationContent = notificationContent;
		this.virtualPoNum = virtualPoNum;
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
	public String getSaleOrderCode() {
		return saleOrderCode;
	}
	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLats() {
		return lats;
	}
	public void setLats(String lats) {
		this.lats = lats;
	}
	public String getLongs() {
		return longs;
	}
	public void setLongs(String longs) {
		this.longs = longs;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getNeedsGoods() {
		return needsGoods;
	}
	public void setNeedsGoods(String needsGoods) {
		this.needsGoods = needsGoods;
	}
	public String getNeedsServices() {
		return needsServices;
	}
	public void setNeedsServices(String needsServices) {
		this.needsServices = needsServices;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSaleOrderStatus() {
		return saleOrderStatus;
	}
	public void setSaleOrderStatus(String saleOrderStatus) {
		this.saleOrderStatus = saleOrderStatus;
	}
	public LocalDateTime getSubmittedDt() {
		return submittedDt;
	}
	public void setSubmittedDt(LocalDateTime submittedDt) {
		this.submittedDt = submittedDt;
	}
	public LocalDateTime getApprovedDt() {
		return approvedDt;
	}
	public void setApprovedDt(LocalDateTime approvedDt) {
		this.approvedDt = approvedDt;
	}
	public LocalDateTime getM2ApprovedDt() {
		return m2ApprovedDt;
	}
	public void setM2ApprovedDt(LocalDateTime m2ApprovedDt) {
		this.m2ApprovedDt = m2ApprovedDt;
	}
	public LocalDateTime getCancelledDt() {
		return cancelledDt;
	}
	public void setCancelledDt(LocalDateTime cancelledDt) {
		this.cancelledDt = cancelledDt;
	}
	public LocalDateTime getRejectedDt() {
		return rejectedDt;
	}
	public void setRejectedDt(LocalDateTime rejectedDt) {
		this.rejectedDt = rejectedDt;
	}
	public LocalDateTime getM2RejectedDt() {
		return m2RejectedDt;
	}
	public void setM2RejectedDt(LocalDateTime m2RejectedDt) {
		this.m2RejectedDt = m2RejectedDt;
	}
	public String getApprovedRemarks() {
		return approvedRemarks;
	}
	public void setApprovedRemarks(String approvedRemarks) {
		this.approvedRemarks = approvedRemarks;
	}
	public String getM2ApprovedRemarks() {
		return m2ApprovedRemarks;
	}
	public void setM2ApprovedRemarks(String m2ApprovedRemarks) {
		this.m2ApprovedRemarks = m2ApprovedRemarks;
	}
	public String getCancelledRemarks() {
		return cancelledRemarks;
	}
	public void setCancelledRemarks(String cancelledRemarks) {
		this.cancelledRemarks = cancelledRemarks;
	}
	public String getRejectedRemarks() {
		return rejectedRemarks;
	}
	public void setRejectedRemarks(String rejectedRemarks) {
		this.rejectedRemarks = rejectedRemarks;
	}
	public String getM2rejectedRemarks() {
		return m2rejectedRemarks;
	}
	public void setM2rejectedRemarks(String m2rejectedRemarks) {
		this.m2rejectedRemarks = m2rejectedRemarks;
	}
	public String getManagerEmpId() {
		return managerEmpId;
	}
	public void setManagerEmpId(String managerEmpId) {
		this.managerEmpId = managerEmpId;
	}
	public String getManager2EmpId() {
		return manager2EmpId;
	}
	public void setManager2EmpId(String manager2EmpId) {
		this.manager2EmpId = manager2EmpId;
	}
	public String getDeliveryTerms() {
		return deliveryTerms;
	}
	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}
	public String getDeliverySchedule() {
		return deliverySchedule;
	}
	public void setDeliverySchedule(String deliverySchedule) {
		this.deliverySchedule = deliverySchedule;
	}
	public String getNotificationStatus() {
		return notificationStatus;
	}
	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
	public LocalDateTime getNotificationDt() {
		return notificationDt;
	}
	public void setNotificationDt(LocalDateTime notificationDt) {
		this.notificationDt = notificationDt;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getInternalRemarks() {
		return internalRemarks;
	}
	public void setInternalRemarks(String internalRemarks) {
		this.internalRemarks = internalRemarks;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getDeleiveryTerms() {
		return deleiveryTerms;
	}
	public void setDeleiveryTerms(String deleiveryTerms) {
		this.deleiveryTerms = deleiveryTerms;
	}
	public String getDeleiverySchedule() {
		return deleiverySchedule;
	}
	public void setDeleiverySchedule(String deleiverySchedule) {
		this.deleiverySchedule = deleiverySchedule;
	}
	public String getNotificationContent() {
		return notificationContent;
	}
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}
	public String getVirtualPoNum() {
		return virtualPoNum;
	}
	public void setVirtualPoNum(String virtualPoNum) {
		this.virtualPoNum = virtualPoNum;
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
		return "SaleOrder [id=" + id + ", saleOrderCode=" + saleOrderCode + ", status=" + status + ", purchaseOrderId="
				+ purchaseOrderId + ", divisionId=" + divisionId + ", partnerId=" + partnerId + ", projectId="
				+ projectId + ", gstNo=" + gstNo + ", locationId=" + locationId + ", lats=" + lats + ", longs=" + longs
				+ ", projectName=" + projectName + ", partnerName=" + partnerName + ", needsGoods=" + needsGoods
				+ ", needsServices=" + needsServices + ", emailId=" + emailId + ", saleOrderStatus=" + saleOrderStatus
				+ ", submittedDt=" + submittedDt + ", approvedDt=" + approvedDt + ", m2ApprovedDt=" + m2ApprovedDt
				+ ", cancelledDt=" + cancelledDt + ", rejectedDt=" + rejectedDt + ", m2RejectedDt=" + m2RejectedDt
				+ ", approvedRemarks=" + approvedRemarks + ", m2ApprovedRemarks=" + m2ApprovedRemarks
				+ ", cancelledRemarks=" + cancelledRemarks + ", rejectedRemarks=" + rejectedRemarks
				+ ", m2rejectedRemarks=" + m2rejectedRemarks + ", managerEmpId=" + managerEmpId + ", manager2EmpId="
				+ manager2EmpId + ", deliveryTerms=" + deliveryTerms + ", deliverySchedule=" + deliverySchedule
				+ ", notificationStatus=" + notificationStatus + ", notificationDt=" + notificationDt
				+ ", shippingAddress=" + shippingAddress + ", internalRemarks=" + internalRemarks + ", paymentTerms="
				+ paymentTerms + ", deleiveryTerms=" + deleiveryTerms + ", deleiverySchedule=" + deleiverySchedule
				+ ", notificationContent=" + notificationContent + ", virtualPoNum=" + virtualPoNum + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}

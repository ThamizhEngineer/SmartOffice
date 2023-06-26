package com.ss.smartoffice.soauthservice.transaction.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
@Entity
@Table(name = "t_event_business_key")
@Scope("prototype")

public class BusinessKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_event_id")
	private String eventId;
	private String userId;
	private String incidentId;
	private String incidentCode;
	private String employeeId;
	private String internalId;
	private String interviewId;
	private String employeeCode;
	private String tarId;
	private String tarCode;
	private String leaveRequestId;
	private String leaveRequestCode;
	private String expenseClaimId;
	private String expenseClaimCode;
	@Column(name="n1_emp_id")
	private String n1EmpId;
	@Column(name="n2_emp_id")
	private String n2EmpId;
	@Column(name="hr1_user_group_id")
	private String hr1UserGroupId;
	@Column(name="hr2_user_group_id")
	private String hr2UserGroupId;
	@Column(name="hr1_emp_id")
	private String hr1EmpId;
	@Column(name="hr2_emp_id")
	private String hr2EmpId;
	@Column(name="acc1_user_group_id")
	private String acc1UserGroupId;
	@Column(name = "dir_user_group_id")
	private String dirUserGroupId;
	@Column(name="dir_emp_id")
	private String dirEmpId;
	@Column(name="acc2_user_group_id")
	private String acc2UserGroupId;
	@Column(name="acc1_emp_id")
	private String acc1EmpId;
	@Column(name="acc2_emp_id")
	private String acc2EmpId;
	private String uploadPayslipHdrId;
	private String latitudes;
	private String longtitudes;
	private String empPayoutId;
	private String clientId;
	private String customerId;
	private String saleOrderId;
	private String saleOrderCode;
	private String purchaseOrderId;
	private String purchaseOrderCode;
	private String applicantId;
	private String partnerId;
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String key5;
	private String key6;
	private String key7;
	private String key8;
	private String key9;
	private String key10;
	public BusinessKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BusinessKey(Integer id, String eventId, String userId, String incidentId, String incidentCode,
			String employeeId, String internalId, String interviewId, String employeeCode, String tarId, String tarCode,
			String leaveRequestId, String leaveRequestCode, String expenseClaimId, String expenseClaimCode,
			String n1EmpId, String n2EmpId, String hr1UserGroupId, String hr2UserGroupId, String hr1EmpId,
			String hr2EmpId, String acc1UserGroupId, String dirUserGroupId, String dirEmpId, String acc2UserGroupId,
			String acc1EmpId, String acc2EmpId, String uploadPayslipHdrId, String latitudes, String longtitudes,
			String empPayoutId, String clientId, String customerId, String saleOrderId, String saleOrderCode,
			String purchaseOrderId, String purchaseOrderCode, String applicantId, String partnerId, String key1,
			String key2, String key3, String key4, String key5, String key6, String key7, String key8, String key9,
			String key10) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.userId = userId;
		this.incidentId = incidentId;
		this.incidentCode = incidentCode;
		this.employeeId = employeeId;
		this.internalId = internalId;
		this.interviewId = interviewId;
		this.employeeCode = employeeCode;
		this.tarId = tarId;
		this.tarCode = tarCode;
		this.leaveRequestId = leaveRequestId;
		this.leaveRequestCode = leaveRequestCode;
		this.expenseClaimId = expenseClaimId;
		this.expenseClaimCode = expenseClaimCode;
		this.n1EmpId = n1EmpId;
		this.n2EmpId = n2EmpId;
		this.hr1UserGroupId = hr1UserGroupId;
		this.hr2UserGroupId = hr2UserGroupId;
		this.hr1EmpId = hr1EmpId;
		this.hr2EmpId = hr2EmpId;
		this.acc1UserGroupId = acc1UserGroupId;
		this.dirUserGroupId = dirUserGroupId;
		this.dirEmpId = dirEmpId;
		this.acc2UserGroupId = acc2UserGroupId;
		this.acc1EmpId = acc1EmpId;
		this.acc2EmpId = acc2EmpId;
		this.uploadPayslipHdrId = uploadPayslipHdrId;
		this.latitudes = latitudes;
		this.longtitudes = longtitudes;
		this.empPayoutId = empPayoutId;
		this.clientId = clientId;
		this.customerId = customerId;
		this.saleOrderId = saleOrderId;
		this.saleOrderCode = saleOrderCode;
		this.purchaseOrderId = purchaseOrderId;
		this.purchaseOrderCode = purchaseOrderCode;
		this.applicantId = applicantId;
		this.partnerId = partnerId;
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.key4 = key4;
		this.key5 = key5;
		this.key6 = key6;
		this.key7 = key7;
		this.key8 = key8;
		this.key9 = key9;
		this.key10 = key10;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	public String getIncidentCode() {
		return incidentCode;
	}
	public void setIncidentCode(String incidentCode) {
		this.incidentCode = incidentCode;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getInternalId() {
		return internalId;
	}
	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}
	public String getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getTarId() {
		return tarId;
	}
	public void setTarId(String tarId) {
		this.tarId = tarId;
	}
	public String getTarCode() {
		return tarCode;
	}
	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
	public String getLeaveRequestId() {
		return leaveRequestId;
	}
	public void setLeaveRequestId(String leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}
	public String getLeaveRequestCode() {
		return leaveRequestCode;
	}
	public void setLeaveRequestCode(String leaveRequestCode) {
		this.leaveRequestCode = leaveRequestCode;
	}
	public String getExpenseClaimId() {
		return expenseClaimId;
	}
	public void setExpenseClaimId(String expenseClaimId) {
		this.expenseClaimId = expenseClaimId;
	}
	public String getExpenseClaimCode() {
		return expenseClaimCode;
	}
	public void setExpenseClaimCode(String expenseClaimCode) {
		this.expenseClaimCode = expenseClaimCode;
	}
	public String getN1EmpId() {
		return n1EmpId;
	}
	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}
	public String getN2EmpId() {
		return n2EmpId;
	}
	public void setN2EmpId(String n2EmpId) {
		this.n2EmpId = n2EmpId;
	}
	public String getHr1UserGroupId() {
		return hr1UserGroupId;
	}
	public void setHr1UserGroupId(String hr1UserGroupId) {
		this.hr1UserGroupId = hr1UserGroupId;
	}
	public String getHr2UserGroupId() {
		return hr2UserGroupId;
	}
	public void setHr2UserGroupId(String hr2UserGroupId) {
		this.hr2UserGroupId = hr2UserGroupId;
	}
	public String getHr1EmpId() {
		return hr1EmpId;
	}
	public void setHr1EmpId(String hr1EmpId) {
		this.hr1EmpId = hr1EmpId;
	}
	public String getHr2EmpId() {
		return hr2EmpId;
	}
	public void setHr2EmpId(String hr2EmpId) {
		this.hr2EmpId = hr2EmpId;
	}
	public String getAcc1UserGroupId() {
		return acc1UserGroupId;
	}
	public void setAcc1UserGroupId(String acc1UserGroupId) {
		this.acc1UserGroupId = acc1UserGroupId;
	}
	public String getDirUserGroupId() {
		return dirUserGroupId;
	}
	public void setDirUserGroupId(String dirUserGroupId) {
		this.dirUserGroupId = dirUserGroupId;
	}
	public String getDirEmpId() {
		return dirEmpId;
	}
	public void setDirEmpId(String dirEmpId) {
		this.dirEmpId = dirEmpId;
	}
	public String getAcc2UserGroupId() {
		return acc2UserGroupId;
	}
	public void setAcc2UserGroupId(String acc2UserGroupId) {
		this.acc2UserGroupId = acc2UserGroupId;
	}
	public String getAcc1EmpId() {
		return acc1EmpId;
	}
	public void setAcc1EmpId(String acc1EmpId) {
		this.acc1EmpId = acc1EmpId;
	}
	public String getAcc2EmpId() {
		return acc2EmpId;
	}
	public void setAcc2EmpId(String acc2EmpId) {
		this.acc2EmpId = acc2EmpId;
	}
	public String getUploadPayslipHdrId() {
		return uploadPayslipHdrId;
	}
	public void setUploadPayslipHdrId(String uploadPayslipHdrId) {
		this.uploadPayslipHdrId = uploadPayslipHdrId;
	}
	public String getLatitudes() {
		return latitudes;
	}
	public void setLatitudes(String latitudes) {
		this.latitudes = latitudes;
	}
	public String getLongtitudes() {
		return longtitudes;
	}
	public void setLongtitudes(String longtitudes) {
		this.longtitudes = longtitudes;
	}
	public String getEmpPayoutId() {
		return empPayoutId;
	}
	public void setEmpPayoutId(String empPayoutId) {
		this.empPayoutId = empPayoutId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSaleOrderId() {
		return saleOrderId;
	}
	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	public String getSaleOrderCode() {
		return saleOrderCode;
	}
	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}
	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	public String getKey4() {
		return key4;
	}
	public void setKey4(String key4) {
		this.key4 = key4;
	}
	public String getKey5() {
		return key5;
	}
	public void setKey5(String key5) {
		this.key5 = key5;
	}
	public String getKey6() {
		return key6;
	}
	public void setKey6(String key6) {
		this.key6 = key6;
	}
	public String getKey7() {
		return key7;
	}
	public void setKey7(String key7) {
		this.key7 = key7;
	}
	public String getKey8() {
		return key8;
	}
	public void setKey8(String key8) {
		this.key8 = key8;
	}
	public String getKey9() {
		return key9;
	}
	public void setKey9(String key9) {
		this.key9 = key9;
	}
	public String getKey10() {
		return key10;
	}
	public void setKey10(String key10) {
		this.key10 = key10;
	}
	@Override
	public String toString() {
		return "BusinessKey [id=" + id + ", eventId=" + eventId + ", userId=" + userId + ", incidentId=" + incidentId
				+ ", incidentCode=" + incidentCode + ", employeeId=" + employeeId + ", internalId=" + internalId
				+ ", interviewId=" + interviewId + ", employeeCode=" + employeeCode + ", tarId=" + tarId + ", tarCode="
				+ tarCode + ", leaveRequestId=" + leaveRequestId + ", leaveRequestCode=" + leaveRequestCode
				+ ", expenseClaimId=" + expenseClaimId + ", expenseClaimCode=" + expenseClaimCode + ", n1EmpId="
				+ n1EmpId + ", n2EmpId=" + n2EmpId + ", hr1UserGroupId=" + hr1UserGroupId + ", hr2UserGroupId="
				+ hr2UserGroupId + ", hr1EmpId=" + hr1EmpId + ", hr2EmpId=" + hr2EmpId + ", acc1UserGroupId="
				+ acc1UserGroupId + ", dirUserGroupId=" + dirUserGroupId + ", dirEmpId=" + dirEmpId
				+ ", acc2UserGroupId=" + acc2UserGroupId + ", acc1EmpId=" + acc1EmpId + ", acc2EmpId=" + acc2EmpId
				+ ", uploadPayslipHdrId=" + uploadPayslipHdrId + ", latitudes=" + latitudes + ", longtitudes="
				+ longtitudes + ", empPayoutId=" + empPayoutId + ", clientId=" + clientId + ", customerId=" + customerId
				+ ", saleOrderId=" + saleOrderId + ", saleOrderCode=" + saleOrderCode + ", purchaseOrderId="
				+ purchaseOrderId + ", purchaseOrderCode=" + purchaseOrderCode + ", applicantId=" + applicantId
				+ ", partnerId=" + partnerId + ", key1=" + key1 + ", key2=" + key2 + ", key3=" + key3 + ", key4=" + key4
				+ ", key5=" + key5 + ", key6=" + key6 + ", key7=" + key7 + ", key8=" + key8 + ", key9=" + key9
				+ ", key10=" + key10 + "]";
	}
	
}

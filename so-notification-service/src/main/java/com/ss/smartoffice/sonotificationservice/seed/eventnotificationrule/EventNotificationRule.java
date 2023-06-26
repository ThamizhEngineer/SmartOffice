package com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule;

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
@Table(name="s_event_notfn_rule")
@Scope("prototype")
public class EventNotificationRule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="event_name")
	private String eventName;
	@Column(name="entity")
	private String entity;
	private String entityStatus;
	private String isCustom;
	private String knowRecipientDetails;
	private String emailId;
	private String fullName;
	private String isGrpMessage;
	@Column(name="send_sms")
	private String sendSms;
	private String smsMessage;
	@Column(name="send_email")
	private String sendEmail;
	private String smsTopicName;
	private String emailSubject;
	private String emailMessage;
	private String emailTemplateName;
	private String emailTopicName;
	@Column(name="send_in_app_notfn")
	private String sendInAppNotfn;
	private String inAppNotfnMessage;
	private String inAppNotfnTopicName;
	private String recipientUserGpKey;
	private String recipientUserKey;
	private String recipientEmployeeKey;
	private String recipientCustomerKey;
	private String recipientVendorKey;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String sendDash;
	private String dashTopicName;
	public EventNotificationRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventNotificationRule(Integer id, String eventName, String entity, String entityStatus, String isCustom,
			String knowRecipientDetails, String emailId, String fullName, String isGrpMessage, String sendSms,
			String smsMessage, String sendEmail, String smsTopicName, String emailSubject, String emailMessage,
			String emailTemplateName, String emailTopicName, String sendInAppNotfn, String inAppNotfnMessage,
			String inAppNotfnTopicName, String recipientUserGpKey, String recipientUserKey, String recipientEmployeeKey,
			String recipientCustomerKey, String recipientVendorKey, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt, String sendDash,
			String dashTopicName) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.entity = entity;
		this.entityStatus = entityStatus;
		this.isCustom = isCustom;
		this.knowRecipientDetails = knowRecipientDetails;
		this.emailId = emailId;
		this.fullName = fullName;
		this.isGrpMessage = isGrpMessage;
		this.sendSms = sendSms;
		this.smsMessage = smsMessage;
		this.sendEmail = sendEmail;
		this.smsTopicName = smsTopicName;
		this.emailSubject = emailSubject;
		this.emailMessage = emailMessage;
		this.emailTemplateName = emailTemplateName;
		this.emailTopicName = emailTopicName;
		this.sendInAppNotfn = sendInAppNotfn;
		this.inAppNotfnMessage = inAppNotfnMessage;
		this.inAppNotfnTopicName = inAppNotfnTopicName;
		this.recipientUserGpKey = recipientUserGpKey;
		this.recipientUserKey = recipientUserKey;
		this.recipientEmployeeKey = recipientEmployeeKey;
		this.recipientCustomerKey = recipientCustomerKey;
		this.recipientVendorKey = recipientVendorKey;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.sendDash = sendDash;
		this.dashTopicName = dashTopicName;
	}
	
	public EventNotificationRule(Integer id, String eventName, String entity, String sendSms, String sendEmail,
			String sendInAppNotfn) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.entity = entity;
		this.sendSms = sendSms;
		this.sendEmail = sendEmail;
		this.sendInAppNotfn = sendInAppNotfn;
	}
	
	@Override
	public String toString() {
		return "EventNotificationRule [id=" + id + ", eventName=" + eventName + ", entity=" + entity + ", entityStatus=" + entityStatus + ", isCustom="
				+ isCustom + ", knowRecipientDetails=" + knowRecipientDetails + ", emailId=" + emailId + ", fullName="
				+ fullName + ", isGrpMessage=" + isGrpMessage + ", sendSms=" + sendSms + ", smsMessage=" + smsMessage
				+ ", sendEmail=" + sendEmail + ", smsTopicName=" + smsTopicName + ", emailSubject=" + emailSubject
				+ ", emailMessage=" + emailMessage + ", emailTemplateName=" + emailTemplateName + ", emailTopicName="
				+ emailTopicName + ", sendInAppNotfn=" + sendInAppNotfn + ", inAppNotfnMessage=" + inAppNotfnMessage
				+ ", inAppNotfnTopicName=" + inAppNotfnTopicName + ", recipientUserGpKey=" + recipientUserGpKey
				+ ", recipientUserKey=" + recipientUserKey + ", recipientEmployeeKey=" + recipientEmployeeKey
				+ ", recipientCustomerKey=" + recipientCustomerKey + ", recipientVendorKey=" + recipientVendorKey
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + ", sendDash=" + sendDash + ", dashTopicName="
				+ dashTopicName + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public String getEntityStatus() {
		return entityStatus;
	}
	public void setEntityStatus(String entityStatus) {
		this.entityStatus = entityStatus;
	}
	public String getIsCustom() {
		return isCustom;
	}
	public void setIsCustom(String isCustom) {
		this.isCustom = isCustom;
	}
	public String getKnowRecipientDetails() {
		return knowRecipientDetails;
	}
	public void setKnowRecipientDetails(String knowRecipientDetails) {
		this.knowRecipientDetails = knowRecipientDetails;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getIsGrpMessage() {
		return isGrpMessage;
	}
	public void setIsGrpMessage(String isGrpMessage) {
		this.isGrpMessage = isGrpMessage;
	}
	public String getSendSms() {
		return sendSms;
	}
	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	public String getSmsMessage() {
		return smsMessage;
	}
	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getSmsTopicName() {
		return smsTopicName;
	}
	public void setSmsTopicName(String smsTopicName) {
		this.smsTopicName = smsTopicName;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailMessage() {
		return emailMessage;
	}
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}
	public String getEmailTemplateName() {
		return emailTemplateName;
	}
	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}
	public String getEmailTopicName() {
		return emailTopicName;
	}
	public void setEmailTopicName(String emailTopicName) {
		this.emailTopicName = emailTopicName;
	}
	public String getSendInAppNotfn() {
		return sendInAppNotfn;
	}
	public void setSendInAppNotfn(String sendInAppNotfn) {
		this.sendInAppNotfn = sendInAppNotfn;
	}
	public String getInAppNotfnMessage() {
		return inAppNotfnMessage;
	}
	public void setInAppNotfnMessage(String inAppNotfnMessage) {
		this.inAppNotfnMessage = inAppNotfnMessage;
	}
	public String getInAppNotfnTopicName() {
		return inAppNotfnTopicName;
	}
	public void setInAppNotfnTopicName(String inAppNotfnTopicName) {
		this.inAppNotfnTopicName = inAppNotfnTopicName;
	}
	public String getRecipientUserGpKey() {
		return recipientUserGpKey;
	}
	public void setRecipientUserGpKey(String recipientUserGpKey) {
		this.recipientUserGpKey = recipientUserGpKey;
	}
	public String getRecipientUserKey() {
		return recipientUserKey;
	}
	public void setRecipientUserKey(String recipientUserKey) {
		this.recipientUserKey = recipientUserKey;
	}
	public String getRecipientEmployeeKey() {
		return recipientEmployeeKey;
	}
	public void setRecipientEmployeeKey(String recipientEmployeeKey) {
		this.recipientEmployeeKey = recipientEmployeeKey;
	}
	public String getRecipientCustomerKey() {
		return recipientCustomerKey;
	}
	public void setRecipientCustomerKey(String recipientCustomerKey) {
		this.recipientCustomerKey = recipientCustomerKey;
	}
	public String getRecipientVendorKey() {
		return recipientVendorKey;
	}
	public void setRecipientVendorKey(String recipientVendorKey) {
		this.recipientVendorKey = recipientVendorKey;
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
	public String getSendDash() {
		return sendDash;
	}
	public void setSendDash(String sendDash) {
		this.sendDash = sendDash;
	}
	public String getDashTopicName() {
		return dashTopicName;
	}
	public void setDashTopicName(String dashTopicName) {
		this.dashTopicName = dashTopicName;
	}

}

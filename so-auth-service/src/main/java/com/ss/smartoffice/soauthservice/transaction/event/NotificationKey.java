package com.ss.smartoffice.soauthservice.transaction.event;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Entity
@Table(name="t_event_notification_key")
@Scope("prototype")
public class NotificationKey{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_event_id")
	private String eventId;
	private String sendEmail;
	private String emailId;	
	private String emailSubject;
	private String emailTemplateName;
	private String emailMessage;
	private String emailSentDt;
	private String sendSms;
	private String mobileNumber;
	private String smsMessage;
	private String smsSentDt;
	private String sendInAppNotfn;
	private String userId;
	private String inAppNotfnMessage;
	private String inAppNotfnSentDt;
	private String expire;
	private String retryCount;
	private String retryInterval;
	
	public NotificationKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationKey(Integer id, String eventId, String sendEmail, String emailId, String emailSubject,
			String emailTemplateName, String emailMessage, String emailSentDt, String sendSms, String mobileNumber,
			String smsMessage, String smsSentDt, String sendInAppNotfn, String userId, String inAppNotfnMessage,
			String inAppNotfnSentDt, String expire, String retryCount, String retryInterval) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.sendEmail = sendEmail;
		this.emailId = emailId;
		this.emailSubject = emailSubject;
		this.emailTemplateName = emailTemplateName;
		this.emailMessage = emailMessage;
		this.emailSentDt = emailSentDt;
		this.sendSms = sendSms;
		this.mobileNumber = mobileNumber;
		this.smsMessage = smsMessage;
		this.smsSentDt = smsSentDt;
		this.sendInAppNotfn = sendInAppNotfn;
		this.userId = userId;
		this.inAppNotfnMessage = inAppNotfnMessage;
		this.inAppNotfnSentDt = inAppNotfnSentDt;
		this.expire = expire;
		this.retryCount = retryCount;
		this.retryInterval = retryInterval;
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

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	public String getEmailSentDt() {
		return emailSentDt;
	}

	public void setEmailSentDt(String emailSentDt) {
		this.emailSentDt = emailSentDt;
	}

	public String getSendSms() {
		return sendSms;
	}

	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSmsMessage() {
		return smsMessage;
	}

	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	public String getSmsSentDt() {
		return smsSentDt;
	}

	public void setSmsSentDt(String smsSentDt) {
		this.smsSentDt = smsSentDt;
	}

	public String getSendInAppNotfn() {
		return sendInAppNotfn;
	}

	public void setSendInAppNotfn(String sendInAppNotfn) {
		this.sendInAppNotfn = sendInAppNotfn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInAppNotfnMessage() {
		return inAppNotfnMessage;
	}

	public void setInAppNotfnMessage(String inAppNotfnMessage) {
		this.inAppNotfnMessage = inAppNotfnMessage;
	}

	public String getInAppNotfnSentDt() {
		return inAppNotfnSentDt;
	}

	public void setInAppNotfnSentDt(String inAppNotfnSentDt) {
		this.inAppNotfnSentDt = inAppNotfnSentDt;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(String retryCount) {
		this.retryCount = retryCount;
	}

	public String getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(String retryInterval) {
		this.retryInterval = retryInterval;
	}

	@Override
	public String toString() {
		return "NotificationKey [id=" + id + ", eventId=" + eventId + ", sendEmail=" + sendEmail + ", emailId="
				+ emailId + ", emailSubject=" + emailSubject + ", emailTemplateName=" + emailTemplateName
				+ ", emailMessage=" + emailMessage + ", emailSentDt=" + emailSentDt + ", sendSms=" + sendSms
				+ ", mobileNumber=" + mobileNumber + ", smsMessage=" + smsMessage + ", smsSentDt=" + smsSentDt
				+ ", sendInAppNotfn=" + sendInAppNotfn + ", userId=" + userId + ", inAppNotfnMessage="
				+ inAppNotfnMessage + ", inAppNotfnSentDt=" + inAppNotfnSentDt + ", expire=" + expire + ", retryCount="
				+ retryCount + ", retryInterval=" + retryInterval + "]";
	}


	
}

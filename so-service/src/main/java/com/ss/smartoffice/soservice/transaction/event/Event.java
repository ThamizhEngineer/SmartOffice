package com.ss.smartoffice.soservice.transaction.event;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.springframework.stereotype.Component;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@Entity
@Table(name = "t_event")
@Component

public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private EventTypes name;

	@Enumerated(EnumType.STRING)
	private EventCategory category;
	private String entityStatus;
	private String process;
	private String activity;
	private String appToken;
	private String topic;
	private String createdBy;
	@CreationTimestamp
	private LocalDateTime createdDt;
	private String eventDesc;
	private String errorMessage;

	private String errorDesc;

	private String errorCode;
	@Column(name="context_auth_user_id")
	private Integer contextAuthUserId;
	@Transient
	private Map<String, String> keyValues;

//	@CreationTimestamp
//	private LocalDateTime createdDt;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_event_id")
	private List<BusinessKey> businessKeys;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_event_id")

	private List<NotificationKey> notificationKeys;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_event_id")
	private List<Attachment> attachments;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_event_id")
	private List<EventKeyValue> eventKeyValues;

	private String payload;

	public enum EventTypes {
		CheckInEvent, CheckOutEvent, ProxyCheckInEvent, ProxyCheckOutEvent, LeaveApprovalEven, SaleOrderCreationEvent,
		UnResolvedAttendanceEvent, TarAppliedEvent, TarN1ApprovedEvent, TarN1RejectedEvent, TarAcc2ApprovedEvent,
		TarAcc2RejectedEvent, TarCancelledEvent, LeaveAppliedEvent, LeaveN1ApprovedEvent, LeaveN1RejectedEvent,
		LeaveN2ApprovedEvent, LeaveN2RejectedEvent, LeaveCancelledEvent, LeaveSettledEvent, LeaveUpdatedEvent,
		ExpenseAppliedEvent, AnnouncementEvent, ExpenseValidatedEvent, ExpenseValidationRejectedEvent,
		ExpenseN1ApprovedEvent, ExpenseN1RejectedEvent, ExpenseN2ApprovedEvent, ExpenseN2RejectedEvent,
		ExpenseCancelledEvent, ExpenseSettledEvent, ExpenseSettlementRejectedEvent, NewUserEvent,
		EmployeeSalaryCreatedEvent, EmployeeUpdateEvent, EmployeeCreatedEvent, NewUserCreatedEvent,
		EmployeeUpdatedByHrEvent, OfficeChangeEvent, EmployeeOnboardedEvent, EmployeeProfileCompletedEvent,
		EmployeeProfileValidatedEvent, EmployeeSkillValidatedEvent, VendorPoEmailEvent, EmployeePasswordResetEvent,
		EmployeeLeaveApplyingEvent, ManualPaySlipProcessEvent, JobConfirmationEvent, OrderAcknowlegmentProcessorEvent,
		ApplicantAddEvent, TestCompleteEvent, PassEvent, FailEvent, Manager1UpdateEvent, Manager2UpdateEvent,
		Hr1UpdateEvent, ApplicantForgotPwdEvent, EmployeeForgotPwdEvent, NewApplicantEvent, IncidentDirApprovalEvent,
		IncidentHrApprovalEvent, TestCreationEvent,AssignTrainingEvent, Hr2UpdateEvent, SalaryProcessFailureEvent, InitiateAppraisal,
		StartAppraisal, UpdateAppraisal, SubmitAppraisal, N1reviewAppraisal, N1rejectAppraisal, EmpAcceptAppraisalEvent,
		EmpRejectAppraisalEvent, N2reviewAppraisalEvent, DirApprovalAppraisalEvent, InitiateAppraisalAchvmtEvent,
		StartAppraisalAchvmtEvent, SubmitAppraisalAchvmtEvent, N1UpdateAppraisalAchvmtEvent,
		DirApprovalAppraisalAchvmtEvent, SalaryProcessErrorEvent, ScheduleInterviewerEvent, ScheduleInterviewerHrEvent,
		Acc1UpdateEvent, Acc2UpdateEvent, EmployeeConversionEvent, JobRequestDirApprovalEvent,
		JobRequestHRApprovalEvent, ScheduleInterviewEvent,ShiftChangeRequqestEvent,ShiftChangeActionEvent,DayTypeNotSetEvent,
		EmployeeShiftNotSetEvent,EmployeeAttendanceNotEligibleEvent,LeaveNotApprovedEvent,NewPartnerEvent,InvoiceSubmitEvent,InvoiceApproveEvent,InvoiceVoidEvent,
		ExitInterviewCreateEvent,ExitInterviewN1ClearedEvent,ExitInterviewAccClearedEvent,ExitInterviewHrClearedEvent;
	};

	public enum EventCategory {
		BuisnessEvent, NotificationEvent;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventTypes getName() {
		return name;
	}

	public void setName(EventTypes name) {
		this.name = name;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}

	public String getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(String entityStatus) {
		this.entityStatus = entityStatus;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getContextAuthUserId() {
		return contextAuthUserId;
	}

	public void setContextAuthUserId(Integer contextAuthUserId) {
		this.contextAuthUserId = contextAuthUserId;
	}

	public Map<String, String> getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(Map<String, String> keyValues) {
		this.keyValues = keyValues;
	}

	public List<BusinessKey> getBusinessKeys() {
		return businessKeys;
	}

	public void setBusinessKeys(List<BusinessKey> businessKeys) {
		this.businessKeys = businessKeys;
	}

	public List<NotificationKey> getNotificationKeys() {
		return notificationKeys;
	}

	public void setNotificationKeys(List<NotificationKey> notificationKeys) {
		this.notificationKeys = notificationKeys;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<EventKeyValue> getEventKeyValues() {
		return eventKeyValues;
	}

	public void setEventKeyValues(List<EventKeyValue> eventKeyValues) {
		this.eventKeyValues = eventKeyValues;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Event(Integer id, EventTypes name, EventCategory category, String entityStatus, String process,
			String activity, String appToken, String topic, String createdBy, String eventDesc, String errorMessage,
			String errorDesc, String errorCode, Integer contextAuthUserId, Map<String, String> keyValues,
			List<BusinessKey> businessKeys, List<NotificationKey> notificationKeys, List<Attachment> attachments,
			List<EventKeyValue> eventKeyValues, String payload) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.entityStatus = entityStatus;
		this.process = process;
		this.activity = activity;
		this.appToken = appToken;
		this.topic = topic;
		this.createdBy = createdBy;
		this.eventDesc = eventDesc;
		this.errorMessage = errorMessage;
		this.errorDesc = errorDesc;
		this.errorCode = errorCode;
		this.contextAuthUserId = contextAuthUserId;
		this.keyValues = keyValues;
		this.businessKeys = businessKeys;
		this.notificationKeys = notificationKeys;
		this.attachments = attachments;
		this.eventKeyValues = eventKeyValues;
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", category=" + category + ", entityStatus=" + entityStatus
				+ ", process=" + process + ", activity=" + activity + ", appToken=" + appToken + ", topic=" + topic
				+ ", createdBy=" + createdBy + ", eventDesc=" + eventDesc + ", errorMessage=" + errorMessage
				+ ", errorDesc=" + errorDesc + ", errorCode=" + errorCode + ", contextAuthUserId=" + contextAuthUserId
				+ ", keyValues=" + keyValues + ", businessKeys=" + businessKeys + ", notificationKeys="
				+ notificationKeys + ", attachments=" + attachments + ", eventKeyValues=" + eventKeyValues
				+ ", payload=" + payload + "]";
	}

	public Map<String, String> formKeyValues() throws SmartOfficeException {

		Map<String, String> kvs = new HashMap<String, String>();
		if (this.getBusinessKeys() != null && !this.getBusinessKeys().isEmpty()
				&& this.getBusinessKeys().get(0) != null) {
			kvs.put("bk.userId", this.getBusinessKeys().get(0).getUserId());
			kvs.put("bk.employeeId", this.getBusinessKeys().get(0).getEmployeeId());
			kvs.put("bk.internalId", this.getBusinessKeys().get(0).getInternalId());
			kvs.put("bk.employeeCode", this.getBusinessKeys().get(0).getEmployeeCode());
			kvs.put("bk.tarId", this.getBusinessKeys().get(0).getTarId());
			kvs.put("bk.interviewId", this.getBusinessKeys().get(0).getInterviewId());
			kvs.put("bk.tarCode", this.getBusinessKeys().get(0).getTarCode());
			kvs.put("bk.leaveRequestId", this.getBusinessKeys().get(0).getLeaveRequestId());
			kvs.put("bk.leaveRequestCode", this.getBusinessKeys().get(0).getLeaveRequestCode());
			kvs.put("bk.expenseClaimId", this.getBusinessKeys().get(0).getExpenseClaimId());
			kvs.put("bk.expenseClaimCode", this.getBusinessKeys().get(0).getExpenseClaimCode());
			kvs.put("bk.n1EmpId", this.getBusinessKeys().get(0).getN1EmpId());
			kvs.put("bk.n2EmpId", this.getBusinessKeys().get(0).getN2EmpId());
			kvs.put("bk.hr1UserGroupId", this.getBusinessKeys().get(0).getHr1UserGroupId());
			kvs.put("bk.dirUserGroupId", this.getBusinessKeys().get(0).getDirUserGroupId());
			kvs.put("bk.dirEmpId", this.getBusinessKeys().get(0).getDirEmpId());
			kvs.put("bk.hr2UserGroupId", this.getBusinessKeys().get(0).getHr2UserGroupId());
			kvs.put("bk.hr1EmpId", this.getBusinessKeys().get(0).getHr1EmpId());
			kvs.put("bk.hr2EmpId", this.getBusinessKeys().get(0).getHr2EmpId());
			kvs.put("bk.acc1UserGroupId", this.getBusinessKeys().get(0).getAcc1UserGroupId());
			kvs.put("bk.acc2UserGroupId", this.getBusinessKeys().get(0).getAcc2UserGroupId());
			kvs.put("bk.acc1EmpId", this.getBusinessKeys().get(0).getAcc1EmpId());
			kvs.put("bk.acc2EmpId", this.getBusinessKeys().get(0).getAcc2EmpId());
			kvs.put("bk.uploadPayslipHdrId", this.getBusinessKeys().get(0).getUploadPayslipHdrId());
			kvs.put("bk.latitudes", this.getBusinessKeys().get(0).getLatitudes());
			kvs.put("bk.longtitudes", this.getBusinessKeys().get(0).getLongtitudes());
			kvs.put("bk.empPayoutId", this.getBusinessKeys().get(0).getLongtitudes());
			kvs.put("bk.clientId", this.getBusinessKeys().get(0).getClientId());
			kvs.put("bk.customerId", this.getBusinessKeys().get(0).getCustomerId());
			kvs.put("bk.saleOrderId", this.getBusinessKeys().get(0).getSaleOrderId());
			kvs.put("bk.saleOrderCode", this.getBusinessKeys().get(0).getSaleOrderCode());
			kvs.put("bk.purchaseOrderId", this.getBusinessKeys().get(0).getPurchaseOrderId());
			kvs.put("bk.purchaseOrderCode", this.getBusinessKeys().get(0).getPurchaseOrderCode());
			kvs.put("bk.applicantId", this.getBusinessKeys().get(0).getApplicantId());
			kvs.put("bk.partnerId", this.getBusinessKeys().get(0).getPartnerId());
			kvs.put("bk.key1", this.getBusinessKeys().get(0).getKey1());
			kvs.put("bk.key2", this.getBusinessKeys().get(0).getKey2());
			kvs.put("bk.key3", this.getBusinessKeys().get(0).getKey3());
			kvs.put("bk.key4", this.getBusinessKeys().get(0).getKey4());
			kvs.put("bk.key5", this.getBusinessKeys().get(0).getKey5());
			kvs.put("bk.key6", this.getBusinessKeys().get(0).getKey6());
			kvs.put("bk.key7", this.getBusinessKeys().get(0).getKey7());
			kvs.put("bk.key8", this.getBusinessKeys().get(0).getKey8());
			kvs.put("bk.key9", this.getBusinessKeys().get(0).getKey9());
			kvs.put("bk.key10", this.getBusinessKeys().get(0).getKey10());

		}
		if (this.getNotificationKeys() != null && !this.getNotificationKeys().isEmpty()
				&& this.getNotificationKeys().get(0) != null) {
			kvs.put("nk.sendEmail", this.getNotificationKeys().get(0).getSendEmail());
			kvs.put("nk.emailSubject", this.getNotificationKeys().get(0).getEmailSubject());
			kvs.put("nk.emailTemplateName", this.getNotificationKeys().get(0).getEmailTemplateName());
			kvs.put("nk.emailMessage", this.getNotificationKeys().get(0).getEmailMessage());
			kvs.put("nk.emailSentDt", this.getNotificationKeys().get(0).getEmailSentDt());
			kvs.put("nk.sendSms", this.getNotificationKeys().get(0).getSendSms());
			kvs.put("nk.mobileNumber", this.getNotificationKeys().get(0).getMobileNumber());
			kvs.put("nk.smsMessage", this.getNotificationKeys().get(0).getSmsMessage());
			kvs.put("nk.smsSentDt", this.getNotificationKeys().get(0).getSmsSentDt());
			kvs.put("nk.sendInAppNotfn", this.getNotificationKeys().get(0).getSendInAppNotfn());
			kvs.put("nk.userId", this.getNotificationKeys().get(0).getUserId());
			kvs.put("nk.inAppNotfnMessage", this.getNotificationKeys().get(0).getInAppNotfnMessage());
			kvs.put("nk.inAppNotfnSentDt", this.getNotificationKeys().get(0).getInAppNotfnSentDt());
			kvs.put("nk.expire", this.getNotificationKeys().get(0).getExpire());
			kvs.put("nk.retryCount", this.getNotificationKeys().get(0).getRetryCount());
			kvs.put("nk.retryInterval", this.getNotificationKeys().get(0).getRetryCount());

		}
		for (EventKeyValue eventKeyValue : this.getEventKeyValues()) {
			kvs.put(eventKeyValue.getKeyPair(), eventKeyValue.getValue());
		}
		return kvs;

	}

}

package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity

@Table(name = "t_job")

@Scope("prototype")
public class Job extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String jobCode;
	private String jobName;
	private String contactName;
	private int contactPhone;
	private String contactEmail;
	private String mJobTypeId;
	@Formula("(select jobType.job_type_name from m_job_type jobType where jobType.id=m_job_type_id)")
	private String mJobTypeName;

	@Column(name = "proj_id")
	private Integer projId;
	@Formula("(select pro.proj_name from t_project pro where pro.id=proj_id)")
	private String projName;
	@Column(name = "m_site_location_id")
	private String siteLocationId;
	@Formula("(select msl.site_name from m_site_location msl where msl.id = m_site_location_id)")
	private String siteLocationName;
	@Formula("(select mml.lats from m_site_location msl join m_map_location mml on mml.id = msl.map_location_id where msl.id=m_site_location_id)")
	private String lats;
	@Formula("(select mml.longs from m_site_location msl join m_map_location mml on mml.id = msl.map_location_id where msl.id=m_site_location_id)")
	private String longs;
	@Formula("(select mml.loc_name from m_site_location msl join m_map_location mml on mml.id = msl.map_location_id where msl.id=m_site_location_id)")
	private String locationName;

//	Note: These are removed and replaced with the following lats, longs, locationName
//	@Column(name = "map_location_id")
//	private String mapLocationId;
//	@Formula("(select map.loc_name from m_map_location map where map.id=map_location_id)")
//	private String mapLocationName;
//	@Formula("(select map.lats from m_map_location map where map.id=map_location_id)")
//	private String mapJobLats;
//	@Formula("(select map.longs from m_map_location map where map.id=map_location_id)")
//	private String mapJobLongs;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expStartDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expEndDt;
	private String clientConfStatus;
	@Column(name = "partner_id")
	private String partnerId;

	private String endClientId;
	@Formula("(select partner.client_name from m_partner partner where partner.id=partner_id)")
	private String endClientName;

	@Formula("(select partner.client_name from m_partner partner where partner.id=end_client_id)")
	private String clientName;

	@Formula("(select partner.mobile_no  from m_partner partner where partner.id=partner_id)")
	private String clientMobNo;

	@Formula("(select partner.email_id  from m_partner partner where partner.id=partner_id)")
	private String clientEmailId;

	@Transient
	private SaleOrder saleOrder;

	private String shiftId;

	@Formula("(select shift.shift_name from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftName;

	@Formula("(select shift.from_time from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftFromTime;

	@Formula("(select shift.to_time from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftToTime;

	private String saleOrderId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDt;

	private String jobPlanStatus;
	@Formula("(select sale.sale_order_code from t_sale_order sale where sale.id=sale_order_id)")
	private String saleOrderCode;

	@Formula("(select jobType.job_type_name from m_job_type jobType where jobType.id=m_job_type_id)")
	private String jobTypeName;
	private String confirmationContent;
	private String clientRemarks;
	private String jobTrackStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime ptwDt;
	private String noOfMilestones;
	private Integer progress;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime trackProgressDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime momDraftApprovalDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime jobCompleteDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime receivedPtwDt;
	private String receivedPtwReviews;
	private String ptwDocId;
	private String receivedPtwRemarks;
	private String siteSurveyRemarks;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime siteSurveyCompDt;
	private String surveyDocId;
	private Integer clientFeedbackScore;
	private String clientFeedbackRemarks;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime momDraftPreparedDt;
	private String momDraftPreparedDocId;
	private String momDraftPreparedRemarks;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime momDraftApprovedDt;
	private String momDraftApprovedDocId;
	private String momDraftApprovedRemarks;
	private String summary;
	@Column(name = "doc_attached_report_1")
	private String docAttachedReport1;
	@Column(name = "doc_attached_report_2")
	private String docAttachedReport2;
	@Column(name = "doc_attached_report_3")
	private String docAttachedReport3;
	@Column(name = "doc_attached_report_4")
	private String docAttachedReport4;
	@Column(name = "doc_attached_report_5")
	private String docAttachedReport5;
	private String internalFeedback;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private Integer seqNumber = 01;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	// Job Plan Child Table
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobPlanTaskType> jobPlanTaskTypes;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobPlanMaterial> jobPlanMaterials;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobPlanProfile> jobPlanProfiles;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobPlanEmp> jobPlanEmps;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobPlanAssets> jobPlanAssets;
	// Job Child Table
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobTaskType> jobTaskTypes;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobMaterial> jobMaterials;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobEmployee> jobEmployees;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobProfile> jobProfiles;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobAsset> jobAssets;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobTravel> jobTravels;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobAccomodation> jobAccomodations;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobChatHistory> jobChatHistories;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobDoc> jobDocs;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobMilestone> jobMilestones;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_id")
	private List<JobTip> jobTips;

//	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
//	@Fetch(value = FetchMode.SUBSELECT)
//	@JoinColumn(name="job_id")
//	private List<JobTrackBay> jobTrackBays;

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

//	summaries
	public Job(Integer id, String jobCode, String jobName, String mJobTypeId, String mJobTypeName,
			String siteLocationId, String siteLocationName,
			String partnerId, String endClientId, String endClientName, LocalDate startDt, String jobPlanStatus) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.mJobTypeId = mJobTypeId;
		this.mJobTypeName = mJobTypeName;
		this.siteLocationId = siteLocationId;
		this.siteLocationName = siteLocationName;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.endClientName = endClientName;
		this.startDt = startDt;
		this.jobPlanStatus = jobPlanStatus;
	}

	public Job(Integer id, String jobCode, String jobName, String mJobTypeId, String mJobTypeName,
			String partnerId, String endClientId, String endClientName, LocalDate startDt,
			String jobPlanStatus) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.mJobTypeId = mJobTypeId;
		this.mJobTypeName = mJobTypeName;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.endClientName = endClientName;
		this.startDt = startDt;
		this.jobPlanStatus = jobPlanStatus;

	}

	// Constructor For getJobPlan
	public Job(Integer id, String jobCode, String jobName, String mJobTypeId, String siteLocationId,
			String siteLocationName, String partnerId, String endClientId, String endClientName,
			LocalDate startDt, String jobPlanStatus, String jobTypeName, String clientName) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.mJobTypeId = mJobTypeId;
		this.siteLocationId = siteLocationId;
		this.siteLocationName = siteLocationName;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.endClientName = endClientName;
		this.startDt = startDt;
		this.jobPlanStatus = jobPlanStatus;
		this.jobTypeName = jobTypeName;
		this.clientName = clientName;

	}

// Constructor For getJobsForLogistics
	public Job(Integer id, String jobCode, String mJobTypeId, String partnerId,
			String endClientId, LocalDate startDt, String jobPlanStatus, String endClientName, String clientName,
			String jobTypeName, String jobName) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.mJobTypeId = mJobTypeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.startDt = startDt;
		this.jobPlanStatus = jobPlanStatus;
		this.endClientName = endClientName;
		this.clientName = clientName;
		this.jobTypeName = jobTypeName;
		this.jobName = jobName;
	}

// Constructor For getJobsForFeedback
	public Job(Integer id, String jobCode, String jobName, String mJobTypeId, String partnerId,
			String endClientId, String endClientName, String clientName, String jobTypeName) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.mJobTypeId = mJobTypeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.endClientName = endClientName;
		this.clientName = clientName;
		this.jobTypeName = jobTypeName;
	}

	public Job(Integer id, String jobCode, String jobName, String contactName, int contactPhone, String contactEmail,
			String mJobTypeId, String mJobTypeName, Integer projId, String projName, String siteLocationId,
			String siteLocationName, String lats, String longs, String locationName, LocalDate expStartDt,
			LocalDate expEndDt, String clientConfStatus, String partnerId, String endClientId, String endClientName,
			String clientName, String clientMobNo, String clientEmailId, SaleOrder saleOrder, String shiftId,
			String shiftName, String shiftFromTime, String shiftToTime, String saleOrderId, LocalDate startDt,
			LocalDate endDt, String jobPlanStatus, String saleOrderCode, String jobTypeName, String confirmationContent,
			String clientRemarks, String jobTrackStatus, LocalDateTime ptwDt, String noOfMilestones, Integer progress,
			LocalDateTime trackProgressDt, LocalDateTime momDraftApprovalDt, LocalDateTime jobCompleteDt,
			LocalDateTime receivedPtwDt, String receivedPtwReviews, String ptwDocId, String receivedPtwRemarks,
			String siteSurveyRemarks, LocalDateTime siteSurveyCompDt, String surveyDocId, Integer clientFeedbackScore,
			String clientFeedbackRemarks, LocalDateTime momDraftPreparedDt, String momDraftPreparedDocId,
			String momDraftPreparedRemarks, LocalDateTime momDraftApprovedDt, String momDraftApprovedDocId,
			String momDraftApprovedRemarks, String summary, String docAttachedReport1, String docAttachedReport2,
			String docAttachedReport3, String docAttachedReport4, String docAttachedReport5, String internalFeedback,
			String isEnabled, String createdBy, String modifiedBy, Integer seqNumber, LocalDateTime createdDt,
			LocalDateTime modifiedDt, List<JobPlanTaskType> jobPlanTaskTypes, List<JobPlanMaterial> jobPlanMaterials,
			List<JobPlanProfile> jobPlanProfiles, List<JobPlanEmp> jobPlanEmps, List<JobPlanAssets> jobPlanAssets,
			List<JobTaskType> jobTaskTypes, List<JobMaterial> jobMaterials, List<JobEmployee> jobEmployees,
			List<JobProfile> jobProfiles, List<JobAsset> jobAssets, List<JobTravel> jobTravels,
			List<JobAccomodation> jobAccomodations, List<JobChatHistory> jobChatHistories, List<JobDoc> jobDocs,
			List<JobMilestone> jobMilestones, List<JobTip> jobTips) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.mJobTypeId = mJobTypeId;
		this.mJobTypeName = mJobTypeName;
		this.projId = projId;
		this.projName = projName;
		this.siteLocationId = siteLocationId;
		this.siteLocationName = siteLocationName;
		this.lats = lats;
		this.longs = longs;
		this.locationName = locationName;
		this.expStartDt = expStartDt;
		this.expEndDt = expEndDt;
		this.clientConfStatus = clientConfStatus;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.endClientName = endClientName;
		this.clientName = clientName;
		this.clientMobNo = clientMobNo;
		this.clientEmailId = clientEmailId;
		this.saleOrder = saleOrder;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.shiftFromTime = shiftFromTime;
		this.shiftToTime = shiftToTime;
		this.saleOrderId = saleOrderId;
		this.startDt = startDt;
		this.endDt = endDt;
		this.jobPlanStatus = jobPlanStatus;
		this.saleOrderCode = saleOrderCode;
		this.jobTypeName = jobTypeName;
		this.confirmationContent = confirmationContent;
		this.clientRemarks = clientRemarks;
		this.jobTrackStatus = jobTrackStatus;
		this.ptwDt = ptwDt;
		this.noOfMilestones = noOfMilestones;
		this.progress = progress;
		this.trackProgressDt = trackProgressDt;
		this.momDraftApprovalDt = momDraftApprovalDt;
		this.jobCompleteDt = jobCompleteDt;
		this.receivedPtwDt = receivedPtwDt;
		this.receivedPtwReviews = receivedPtwReviews;
		this.ptwDocId = ptwDocId;
		this.receivedPtwRemarks = receivedPtwRemarks;
		this.siteSurveyRemarks = siteSurveyRemarks;
		this.siteSurveyCompDt = siteSurveyCompDt;
		this.surveyDocId = surveyDocId;
		this.clientFeedbackScore = clientFeedbackScore;
		this.clientFeedbackRemarks = clientFeedbackRemarks;
		this.momDraftPreparedDt = momDraftPreparedDt;
		this.momDraftPreparedDocId = momDraftPreparedDocId;
		this.momDraftPreparedRemarks = momDraftPreparedRemarks;
		this.momDraftApprovedDt = momDraftApprovedDt;
		this.momDraftApprovedDocId = momDraftApprovedDocId;
		this.momDraftApprovedRemarks = momDraftApprovedRemarks;
		this.summary = summary;
		this.docAttachedReport1 = docAttachedReport1;
		this.docAttachedReport2 = docAttachedReport2;
		this.docAttachedReport3 = docAttachedReport3;
		this.docAttachedReport4 = docAttachedReport4;
		this.docAttachedReport5 = docAttachedReport5;
		this.internalFeedback = internalFeedback;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.seqNumber = seqNumber;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.jobPlanTaskTypes = jobPlanTaskTypes;
		this.jobPlanMaterials = jobPlanMaterials;
		this.jobPlanProfiles = jobPlanProfiles;
		this.jobPlanEmps = jobPlanEmps;
		this.jobPlanAssets = jobPlanAssets;
		this.jobTaskTypes = jobTaskTypes;
		this.jobMaterials = jobMaterials;
		this.jobEmployees = jobEmployees;
		this.jobProfiles = jobProfiles;
		this.jobAssets = jobAssets;
		this.jobTravels = jobTravels;
		this.jobAccomodations = jobAccomodations;
		this.jobChatHistories = jobChatHistories;
		this.jobDocs = jobDocs;
		this.jobMilestones = jobMilestones;
		this.jobTips = jobTips;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", jobCode=" + jobCode + ", jobName=" + jobName + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", mJobTypeId=" + mJobTypeId
				+ ", mJobTypeName=" + mJobTypeName + ", projId=" + projId + ", projName=" + projName
				+ ", siteLocationId=" + siteLocationId + ", siteLocationName=" + siteLocationName + ", lats=" + lats
				+ ", longs=" + longs + ", locationName=" + locationName + ", expStartDt=" + expStartDt + ", expEndDt="
				+ expEndDt + ", clientConfStatus=" + clientConfStatus + ", partnerId=" + partnerId + ", endClientId="
				+ endClientId + ", endClientName=" + endClientName + ", clientName=" + clientName + ", clientMobNo="
				+ clientMobNo + ", clientEmailId=" + clientEmailId + ", saleOrder=" + saleOrder + ", shiftId=" + shiftId
				+ ", shiftName=" + shiftName + ", shiftFromTime=" + shiftFromTime + ", shiftToTime=" + shiftToTime
				+ ", saleOrderId=" + saleOrderId + ", startDt=" + startDt + ", endDt=" + endDt + ", jobPlanStatus="
				+ jobPlanStatus + ", saleOrderCode=" + saleOrderCode + ", jobTypeName=" + jobTypeName
				+ ", confirmationContent=" + confirmationContent + ", clientRemarks=" + clientRemarks
				+ ", jobTrackStatus=" + jobTrackStatus + ", ptwDt=" + ptwDt + ", noOfMilestones=" + noOfMilestones
				+ ", progress=" + progress + ", trackProgressDt=" + trackProgressDt + ", momDraftApprovalDt="
				+ momDraftApprovalDt + ", jobCompleteDt=" + jobCompleteDt + ", receivedPtwDt=" + receivedPtwDt
				+ ", receivedPtwReviews=" + receivedPtwReviews + ", ptwDocId=" + ptwDocId + ", receivedPtwRemarks="
				+ receivedPtwRemarks + ", siteSurveyRemarks=" + siteSurveyRemarks + ", siteSurveyCompDt="
				+ siteSurveyCompDt + ", surveyDocId=" + surveyDocId + ", clientFeedbackScore=" + clientFeedbackScore
				+ ", clientFeedbackRemarks=" + clientFeedbackRemarks + ", momDraftPreparedDt=" + momDraftPreparedDt
				+ ", momDraftPreparedDocId=" + momDraftPreparedDocId + ", momDraftPreparedRemarks="
				+ momDraftPreparedRemarks + ", momDraftApprovedDt=" + momDraftApprovedDt + ", momDraftApprovedDocId="
				+ momDraftApprovedDocId + ", momDraftApprovedRemarks=" + momDraftApprovedRemarks + ", summary="
				+ summary + ", docAttachedReport1=" + docAttachedReport1 + ", docAttachedReport2=" + docAttachedReport2
				+ ", docAttachedReport3=" + docAttachedReport3 + ", docAttachedReport4=" + docAttachedReport4
				+ ", docAttachedReport5=" + docAttachedReport5 + ", internalFeedback=" + internalFeedback
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", seqNumber=" + seqNumber + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", jobPlanTaskTypes=" + jobPlanTaskTypes + ", jobPlanMaterials=" + jobPlanMaterials
				+ ", jobPlanProfiles=" + jobPlanProfiles + ", jobPlanEmps=" + jobPlanEmps + ", jobPlanAssets="
				+ jobPlanAssets + ", jobTaskTypes=" + jobTaskTypes + ", jobMaterials=" + jobMaterials
				+ ", jobEmployees=" + jobEmployees + ", jobProfiles=" + jobProfiles + ", jobAssets=" + jobAssets
				+ ", jobTravels=" + jobTravels + ", jobAccomodations=" + jobAccomodations + ", jobChatHistories="
				+ jobChatHistories + ", jobDocs=" + jobDocs + ", jobMilestones=" + jobMilestones + ", jobTips="
				+ jobTips + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public int getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getmJobTypeId() {
		return mJobTypeId;
	}

	public void setmJobTypeId(String mJobTypeId) {
		this.mJobTypeId = mJobTypeId;
	}

	public String getmJobTypeName() {
		return mJobTypeName;
	}

	public void setmJobTypeName(String mJobTypeName) {
		this.mJobTypeName = mJobTypeName;
	}

	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getSiteLocationId() {
		return siteLocationId;
	}

	public void setSiteLocationId(String siteLocationId) {
		this.siteLocationId = siteLocationId;
	}

	public String getSiteLocationName() {
		return siteLocationName;
	}

	public void setSiteLocationName(String siteLocationName) {
		this.siteLocationName = siteLocationName;
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public LocalDate getExpStartDt() {
		return expStartDt;
	}

	public void setExpStartDt(LocalDate expStartDt) {
		this.expStartDt = expStartDt;
	}

	public LocalDate getExpEndDt() {
		return expEndDt;
	}

	public void setExpEndDt(LocalDate expEndDt) {
		this.expEndDt = expEndDt;
	}

	public String getClientConfStatus() {
		return clientConfStatus;
	}

	public void setClientConfStatus(String clientConfStatus) {
		this.clientConfStatus = clientConfStatus;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getEndClientId() {
		return endClientId;
	}

	public void setEndClientId(String endClientId) {
		this.endClientId = endClientId;
	}

	public String getEndClientName() {
		return endClientName;
	}

	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMobNo() {
		return clientMobNo;
	}

	public void setClientMobNo(String clientMobNo) {
		this.clientMobNo = clientMobNo;
	}

	public String getClientEmailId() {
		return clientEmailId;
	}

	public void setClientEmailId(String clientEmailId) {
		this.clientEmailId = clientEmailId;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getShiftFromTime() {
		return shiftFromTime;
	}

	public void setShiftFromTime(String shiftFromTime) {
		this.shiftFromTime = shiftFromTime;
	}

	public String getShiftToTime() {
		return shiftToTime;
	}

	public void setShiftToTime(String shiftToTime) {
		this.shiftToTime = shiftToTime;
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public LocalDate getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}

	public LocalDate getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDate endDt) {
		this.endDt = endDt;
	}

	public String getJobPlanStatus() {
		return jobPlanStatus;
	}

	public void setJobPlanStatus(String jobPlanStatus) {
		this.jobPlanStatus = jobPlanStatus;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}

	public String getConfirmationContent() {
		return confirmationContent;
	}

	public void setConfirmationContent(String confirmationContent) {
		this.confirmationContent = confirmationContent;
	}

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public String getJobTrackStatus() {
		return jobTrackStatus;
	}

	public void setJobTrackStatus(String jobTrackStatus) {
		this.jobTrackStatus = jobTrackStatus;
	}

	public LocalDateTime getPtwDt() {
		return ptwDt;
	}

	public void setPtwDt(LocalDateTime ptwDt) {
		this.ptwDt = ptwDt;
	}

	public String getNoOfMilestones() {
		return noOfMilestones;
	}

	public void setNoOfMilestones(String noOfMilestones) {
		this.noOfMilestones = noOfMilestones;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public LocalDateTime getTrackProgressDt() {
		return trackProgressDt;
	}

	public void setTrackProgressDt(LocalDateTime trackProgressDt) {
		this.trackProgressDt = trackProgressDt;
	}

	public LocalDateTime getMomDraftApprovalDt() {
		return momDraftApprovalDt;
	}

	public void setMomDraftApprovalDt(LocalDateTime momDraftApprovalDt) {
		this.momDraftApprovalDt = momDraftApprovalDt;
	}

	public LocalDateTime getJobCompleteDt() {
		return jobCompleteDt;
	}

	public void setJobCompleteDt(LocalDateTime jobCompleteDt) {
		this.jobCompleteDt = jobCompleteDt;
	}

	public LocalDateTime getReceivedPtwDt() {
		return receivedPtwDt;
	}

	public void setReceivedPtwDt(LocalDateTime receivedPtwDt) {
		this.receivedPtwDt = receivedPtwDt;
	}

	public String getReceivedPtwReviews() {
		return receivedPtwReviews;
	}

	public void setReceivedPtwReviews(String receivedPtwReviews) {
		this.receivedPtwReviews = receivedPtwReviews;
	}

	public String getPtwDocId() {
		return ptwDocId;
	}

	public void setPtwDocId(String ptwDocId) {
		this.ptwDocId = ptwDocId;
	}

	public String getReceivedPtwRemarks() {
		return receivedPtwRemarks;
	}

	public void setReceivedPtwRemarks(String receivedPtwRemarks) {
		this.receivedPtwRemarks = receivedPtwRemarks;
	}

	public String getSiteSurveyRemarks() {
		return siteSurveyRemarks;
	}

	public void setSiteSurveyRemarks(String siteSurveyRemarks) {
		this.siteSurveyRemarks = siteSurveyRemarks;
	}

	public LocalDateTime getSiteSurveyCompDt() {
		return siteSurveyCompDt;
	}

	public void setSiteSurveyCompDt(LocalDateTime siteSurveyCompDt) {
		this.siteSurveyCompDt = siteSurveyCompDt;
	}

	public String getSurveyDocId() {
		return surveyDocId;
	}

	public void setSurveyDocId(String surveyDocId) {
		this.surveyDocId = surveyDocId;
	}

	public Integer getClientFeedbackScore() {
		return clientFeedbackScore;
	}

	public void setClientFeedbackScore(Integer clientFeedbackScore) {
		this.clientFeedbackScore = clientFeedbackScore;
	}

	public String getClientFeedbackRemarks() {
		return clientFeedbackRemarks;
	}

	public void setClientFeedbackRemarks(String clientFeedbackRemarks) {
		this.clientFeedbackRemarks = clientFeedbackRemarks;
	}

	public LocalDateTime getMomDraftPreparedDt() {
		return momDraftPreparedDt;
	}

	public void setMomDraftPreparedDt(LocalDateTime momDraftPreparedDt) {
		this.momDraftPreparedDt = momDraftPreparedDt;
	}

	public String getMomDraftPreparedDocId() {
		return momDraftPreparedDocId;
	}

	public void setMomDraftPreparedDocId(String momDraftPreparedDocId) {
		this.momDraftPreparedDocId = momDraftPreparedDocId;
	}

	public String getMomDraftPreparedRemarks() {
		return momDraftPreparedRemarks;
	}

	public void setMomDraftPreparedRemarks(String momDraftPreparedRemarks) {
		this.momDraftPreparedRemarks = momDraftPreparedRemarks;
	}

	public LocalDateTime getMomDraftApprovedDt() {
		return momDraftApprovedDt;
	}

	public void setMomDraftApprovedDt(LocalDateTime momDraftApprovedDt) {
		this.momDraftApprovedDt = momDraftApprovedDt;
	}

	public String getMomDraftApprovedDocId() {
		return momDraftApprovedDocId;
	}

	public void setMomDraftApprovedDocId(String momDraftApprovedDocId) {
		this.momDraftApprovedDocId = momDraftApprovedDocId;
	}

	public String getMomDraftApprovedRemarks() {
		return momDraftApprovedRemarks;
	}

	public void setMomDraftApprovedRemarks(String momDraftApprovedRemarks) {
		this.momDraftApprovedRemarks = momDraftApprovedRemarks;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDocAttachedReport1() {
		return docAttachedReport1;
	}

	public void setDocAttachedReport1(String docAttachedReport1) {
		this.docAttachedReport1 = docAttachedReport1;
	}

	public String getDocAttachedReport2() {
		return docAttachedReport2;
	}

	public void setDocAttachedReport2(String docAttachedReport2) {
		this.docAttachedReport2 = docAttachedReport2;
	}

	public String getDocAttachedReport3() {
		return docAttachedReport3;
	}

	public void setDocAttachedReport3(String docAttachedReport3) {
		this.docAttachedReport3 = docAttachedReport3;
	}

	public String getDocAttachedReport4() {
		return docAttachedReport4;
	}

	public void setDocAttachedReport4(String docAttachedReport4) {
		this.docAttachedReport4 = docAttachedReport4;
	}

	public String getDocAttachedReport5() {
		return docAttachedReport5;
	}

	public void setDocAttachedReport5(String docAttachedReport5) {
		this.docAttachedReport5 = docAttachedReport5;
	}

	public String getInternalFeedback() {
		return internalFeedback;
	}

	public void setInternalFeedback(String internalFeedback) {
		this.internalFeedback = internalFeedback;
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

	public Integer getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
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

	public List<JobPlanTaskType> getJobPlanTaskTypes() {
		return jobPlanTaskTypes;
	}

	public void setJobPlanTaskTypes(List<JobPlanTaskType> jobPlanTaskTypes) {
		this.jobPlanTaskTypes = jobPlanTaskTypes;
	}

	public List<JobPlanMaterial> getJobPlanMaterials() {
		return jobPlanMaterials;
	}

	public void setJobPlanMaterials(List<JobPlanMaterial> jobPlanMaterials) {
		this.jobPlanMaterials = jobPlanMaterials;
	}

	public List<JobPlanProfile> getJobPlanProfiles() {
		return jobPlanProfiles;
	}

	public void setJobPlanProfiles(List<JobPlanProfile> jobPlanProfiles) {
		this.jobPlanProfiles = jobPlanProfiles;
	}

	public List<JobPlanEmp> getJobPlanEmps() {
		return jobPlanEmps;
	}

	public void setJobPlanEmps(List<JobPlanEmp> jobPlanEmps) {
		this.jobPlanEmps = jobPlanEmps;
	}

	public List<JobPlanAssets> getJobPlanAssets() {
		return jobPlanAssets;
	}

	public void setJobPlanAssets(List<JobPlanAssets> jobPlanAssets) {
		this.jobPlanAssets = jobPlanAssets;
	}

	public List<JobTaskType> getJobTaskTypes() {
		return jobTaskTypes;
	}

	public void setJobTaskTypes(List<JobTaskType> jobTaskTypes) {
		this.jobTaskTypes = jobTaskTypes;
	}

	public List<JobMaterial> getJobMaterials() {
		return jobMaterials;
	}

	public void setJobMaterials(List<JobMaterial> jobMaterials) {
		this.jobMaterials = jobMaterials;
	}

	public List<JobEmployee> getJobEmployees() {
		return jobEmployees;
	}

	public void setJobEmployees(List<JobEmployee> jobEmployees) {
		this.jobEmployees = jobEmployees;
	}

	public List<JobProfile> getJobProfiles() {
		return jobProfiles;
	}

	public void setJobProfiles(List<JobProfile> jobProfiles) {
		this.jobProfiles = jobProfiles;
	}

	public List<JobAsset> getJobAssets() {
		return jobAssets;
	}

	public void setJobAssets(List<JobAsset> jobAssets) {
		this.jobAssets = jobAssets;
	}

	public List<JobTravel> getJobTravels() {
		return jobTravels;
	}

	public void setJobTravels(List<JobTravel> jobTravels) {
		this.jobTravels = jobTravels;
	}

	public List<JobAccomodation> getJobAccomodations() {
		return jobAccomodations;
	}

	public void setJobAccomodations(List<JobAccomodation> jobAccomodations) {
		this.jobAccomodations = jobAccomodations;
	}

	public List<JobChatHistory> getJobChatHistories() {
		return jobChatHistories;
	}

	public void setJobChatHistories(List<JobChatHistory> jobChatHistories) {
		this.jobChatHistories = jobChatHistories;
	}

	public List<JobDoc> getJobDocs() {
		return jobDocs;
	}

	public void setJobDocs(List<JobDoc> jobDocs) {
		this.jobDocs = jobDocs;
	}

	public List<JobMilestone> getJobMilestones() {
		return jobMilestones;
	}

	public void setJobMilestones(List<JobMilestone> jobMilestones) {
		this.jobMilestones = jobMilestones;
	}

	public List<JobTip> getJobTips() {
		return jobTips;
	}

	public void setJobTips(List<JobTip> jobTips) {
		this.jobTips = jobTips;
	}

	
}

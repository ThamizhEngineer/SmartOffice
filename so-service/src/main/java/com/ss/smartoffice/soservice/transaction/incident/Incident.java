package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDate;
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
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.model.IncidentApplicant;

import lombok.Data;

@Entity

@Scope("prototype")
@Table(name="t_incident")
@Data
public class Incident {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	// incidentName
	private String vcId;
	//formula - vcCode, summary, vcCount,
	private String incidentCode;
	@Formula("(select vacancy.vr_code from t_vacancy_request vacancy where vacancy.id=vc_id)")
	private String vcCode;
	@Formula("(select vacancy.summary  from t_vacancy_request vacancy where vacancy.id=vc_id)")
	private String vcSummary;
	@Formula("(select vacancy.vacancy_count from t_vacancy_request vacancy where vacancy.id=vc_id)")
	private String vcCount;
	
	private String incidentType;
	@Formula("(SELECT COUNT(ia.vc_id) from t_incident_applicants ia where ia.vc_id=vc_id)")
	private String totalApplnCount;
	@Formula("(SELECT COUNT(ia.vc_id) from t_incident_applicants ia where ia.is_short_listed='Y' AND ia.vc_id=vc_id)")
	private String shortlistedApplnCount;
	private String incidentCreatedEmpId;
	@Formula("(select employee.emp_name from m_employee employee where employee.id=incident_created_emp_id)")
	private String incidentCreatedEmpName;
	private String incidentStatus;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime incidentCreatedDt;
	private String approveUsrGrpId;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id=approve_usr_grp_id)")
	private String approveUsrGrpName;
	private String approverEmpId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime approvedDt;
	@Column(name="hr_1_usr_grp_id")
	private String hr1UsrGrpId;
	@Formula("(select userGroup.user_group_name from s_user_group userGroup where userGroup.id=hr_1_usr_grp_id)")
	private String hr1UsrGrpName;
	@Column(name="hr_2_usr_grp_id")
	private String hr2UsrGrpId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate incidentDate;
	private String mapLocId;
	@Formula("(select map.loc_name from m_map_location map where map.id=map_loc_id)")
	private String locName;
	private String location;
	private String hasTest;
	private String hasInterview;
	private String hasTraining;
	private String hasCertification;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate trStartDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate trEndDt;
	// hasTest, hasInterview
	// hasTraining, hasCertification
	private String isEntryLevel;
	private String isShortListed;
	private String isScheduled;
	private String scheduleEmpId;
	private String isProfessional;

	
	// For Training 
	
//	private String trainingName;
	private String noOfDays;
	private String faculty;
	private String contactNo;
	@Formula("(SELECT COUNT(ia.t_incident_id) from t_incident_applicants ia where ia.t_incident_id=id)")
	private String noOfAttendees;
	@Column(name="skills_aqd")
	private String skillsAqd;

	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Column(name="incident_name")
	private String incidentName;
	@Column(name="incident_desc")
	private String incidentDesc;
	@Column(name="emp_conversion_status")
	private String empConversionStatus;
	@Formula("(SELECT COUNT(ia.t_incident_id) from t_incident_applicants ia where ia.t_incident_id=id and incident_type='Training')")
	private String totalHeadCount;
	@Formula("(SELECT COUNT(ia.t_incident_id) from t_incident_applicants ia where ia.t_incident_id=id and ia.is_attended='Y' and incident_type='Training')")
	private String totalAttendies;
	@Formula("(SELECT COUNT(ia.t_incident_id) from t_incident_applicants ia where ia.t_incident_id=id and ia.certificate_issued='Y' and incident_type='Training')")
	private String certificateIssuedCount;
	private String errorCode;
	private String errorMessage;
	private String regenerationStatus;
	private String handlingGroupId;
	@Formula("(SELECT us.user_group_name from s_user_group us WHERE us.id=handling_group_id)")
	private String handlingGroupName;
	
	private String firstInterviewerId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=first_interviewer_id)")
	private String firstInterviewerName;
	private String secondInterviewerId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=second_interviewer_id)")
	private String secondInterviewerName;
	private String thirdInterviewerId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=third_interviewer_id)")
	private String thirdInterviewerName;	
	private String showScore;	
	@Transient
	private String isEmpApplied;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_incident_id")
	private List<ParticipatingInstitutes> participatingInstitutes;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_incident_id")
	private List<IncidentApplicant> incidentApplicants;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_incident_id")
	public List<IncidentTestTemplate> incidentTestTemplates;
	@Transient
	private List<String> applicantIdsToDelete;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_incident_id")
	private List<IncidentTest>incidentTests;
	

}

package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_incident_applicants")
@Data
@NoArgsConstructor
@ToString
public class IncidentApplicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_incident_id")
	private String incidentId;
	@Formula("(select inc.incident_name from t_incident inc where inc.id=t_incident_id)")
	private String incidentName;
	private String vcId;// jrId
	private String source;
	private String referredByEmpId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=referred_by_emp_id)")
	private String referredByEmpName;

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email; // in logic, email will be used as username while creating an applicant
	private String dob;
	private String gender;
	private String currCompany;
	private String currDesignation;
	private String currExperience;
	private String currLocation;
	private String currSalary;
	private String institute;
	private String course;
	private String passoutYear;
	private String marks;
	private String historyOfArrears;

	private String resumeDocId;
	private String coverLetter;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime applicationDt;
	private String isShortListed;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime shortListedDt;
	private String slByEmpId;
	private String firstInterviewerId;
	private String secondInterviewerId;
	private String thirdInterviewerId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime scheduledInterviewDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime secondInterviewDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime thirdInterviewDt;
	private String interviewId;
	private String applicantId;
	private String employeeId;
	private String isEnabled = "Y";
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Transient
	private String isApplicantScheduled = "N";
	@Column(name = "is_test_eligible")
	private String isEligibleForTest = "N";
	private String testEligibilityStatus;
	// New
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "test_eligible_dt")
	private LocalDateTime testEligibileDt;
	@Column(name = "test_eligible_emp_id")
	private String testEligibleEmpId;
	private String isTestScheduled = "N";
	private String isInterviewScheduled = "N";

	private String isInterviewEligible = "N";
	private String interviewEligibleStatus;
	private String interviewEligibleEmpId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime interviewEligibleDt;

	private String testScheduledStatus = "not-scheduled";
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String testScheduledDt;
	private String testScheduleEmpId;
	private String interviewScheduledStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime interviewScheduledDt;
	@Column(name = "interview_schedule_emp_id")
	private String interviewScheduleEmpid;
	private String finalTestStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime finalTestDt;
	private String finalTestEmpId;
	private String finalInterviewStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime finalInterviewDt;
	private String finalInterviewEmpId;
	private String finalDecision;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime finalDecisionDt;
	private String finalDecisionEmpId;
	private String isAttended = "N";
	private String certificateIssued = "N";
//	@Formula("(select cet.score from t_certificate cet where cet.incident_applicant_id=id)")
	private String score;
//	@Formula("(select cet.cmnts_from_trainer from t_certificate cet where cet.incident_applicant_id=id)")
	private String remarksFromTrainer;
//	@Formula("(select cet.skills_aqd from t_certificate cet where cet.incident_applicant_id=id)")
	private String skillsAcquired;
	@Column(name = "emp_conversion_status")
	private String empConversionStatus;
	@Column(name = "emp_conversion_flag")
	private String empConversionFlag;
	@Formula("(select cet.issued_dt from t_certificate cet where cet.incident_applicant_id=id)")
	private String certificateIssuedDate;
	private String expType;
	private String degreeName;
	@Column(name = "m_employee_code")
	private String employeeCode;
	private String empConversionMessage;
	private String traineeStatus;
	@Formula("(select cet.certificate_doc_id from t_certificate cet where cet.incident_applicant_id=id)")
	private String docId;
	private String errorCode;
	private String errorMessage;
	@Formula("(select aut.user_name from auth_user aut where applicant_id=aut.applicant_id)")
	private String userName;
	@Formula("(select aut.password from auth_user aut where applicant_id=aut.applicant_id)")
	private String password;
	@Transient
	private String isClean;
	@Transient
	private String cleanMessage;
	private String isReallocate = "N";
	private String reallocateMessage = "not-started";
 
 
 

}

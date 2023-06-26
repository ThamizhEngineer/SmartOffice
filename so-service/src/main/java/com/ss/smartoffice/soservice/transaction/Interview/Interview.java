package com.ss.smartoffice.soservice.transaction.Interview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import com.ss.smartoffice.shared.model.IncidentApplicant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_interview")
@Data
@NoArgsConstructor
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String incidentId;
	@Formula("(select inc.incident_name from t_incident inc where inc.id=incident_id)")
	private String incidentName;
	private String applicantId;
	private String vcId;
	@Formula("(SELECT vc.vr_code from t_vacancy_request vc WHERE vc.id=vc_id)")
	private String vcCode;
	@Formula("(SELECT vc.summary from t_vacancy_request vc WHERE vc.id=vc_id)")
	private String vcSummary;
	@Formula("(select inapp.first_name from t_incident_applicants inapp WHERE inapp.id=applicant_id )")
	private String applicantName; 
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_interview_id")
	private List<InterviewRound> interviewRound = new ArrayList<InterviewRound>();
	@Formula("(select inapp.resume_doc_id from t_incident_applicants inapp WHERE inapp.id=applicant_id )")
	private String resumeDocId;
	@Formula("(select inapp.cover_letter from t_incident_applicants inapp WHERE inapp.id=applicant_id )")
	private String coverLetter;
	private String finalDecision;
	private String finalDecisionRemarks;
	private String finalDecisionEmpId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=final_decision_emp_id)")
	private String finalDecisionEmpName;
	private String firstInterviewerId;
	@Formula("(select indetail.decision from t_interview_round indetail where indetail.round_order=0 And indetail.t_interview_id=id)")
	private String firstInterviewerStatus;
	private String secondInterviewerId;
	@Formula("(select indetail.decision from t_interview_round indetail where indetail.round_order=1 And indetail.t_interview_id=id)")
	private String secondInterviewerStatus;
	private String thirdInterviewerId;
	@Formula("(select indetail.decision from t_interview_round indetail where indetail.round_order=2 And indetail.t_interview_id=id)")
	private String thirdInterviewerStatus;
	private String isEnabled = "Y";
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
 
 
	@Override
	public String toString() {
		return "Interview [id=" + id + ", incidentId=" + incidentId + ", incidentName=" + incidentName
				+ ", applicantId=" + applicantId + ", vcId=" + vcId + ", vcCode=" + vcCode + ", vcSummary=" + vcSummary
				+ ", applicantName=" + applicantName + ", interviewRound="
				+ interviewRound + ", resumeDocId=" + resumeDocId + ", coverLetter=" + coverLetter + ", finalDecision="
				+ finalDecision + ", finalDecisionRemarks=" + finalDecisionRemarks + ", finalDecisionEmpId="
				+ finalDecisionEmpId + ", finalDecisionEmpName=" + finalDecisionEmpName + ", firstInterviewerId="
				+ firstInterviewerId + ", firstInterviewerStatus=" + firstInterviewerStatus + ", secondInterviewerId="
				+ secondInterviewerId + ", secondInterviewerStatus=" + secondInterviewerStatus + ", thirdInterviewerId="
				+ thirdInterviewerId + ", thirdInterviewerStatus=" + thirdInterviewerStatus + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
}

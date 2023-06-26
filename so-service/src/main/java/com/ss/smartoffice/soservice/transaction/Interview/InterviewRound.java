package com.ss.smartoffice.soservice.transaction.Interview;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.model.Question;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_interview_round")
@Data
@NoArgsConstructor
@ToString
public class InterviewRound {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_interview_id")
	private String interviewId;
	private String roundName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime roundDateTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime roundCompletedDt;
	private String interviewEmpId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=interview_emp_id)")	
	private String interviewEmpName;
	@Formula("(select app.id from t_incident_applicants app join t_interview i on app.id=i.applicant_id where i.id=t_interview_id)")
	private String applicantId;
	@Formula("(select concat(app.first_name,' ', app.last_name) from t_incident_applicants app join t_interview i on app.id=i.applicant_id where i.id=t_interview_id)")
	private String applicantName;	
	private String decision;
	@Formula("(select i.final_decision from t_interview i  where i.id=t_interview_id)")
	private String finalDecision;
	private Integer roundOrder;
	private String rating;
	private String remarks;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
 


	public InterviewRound(String interviewId, String roundName, LocalDateTime roundDateTime, String interviewEmpId, Integer roundOrder, String createdBy) {
		super();
		this.interviewId = interviewId;
		this.roundName = roundName;
		this.roundDateTime = roundDateTime;
		this.interviewEmpId = interviewEmpId;
		this.roundOrder = roundOrder;
		this.decision = "PENDING";
		this.createdBy = createdBy; 
		this.isEnabled = "Y";
	}


	
}

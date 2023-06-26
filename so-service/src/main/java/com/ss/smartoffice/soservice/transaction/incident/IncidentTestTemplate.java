package com.ss.smartoffice.soservice.transaction.incident;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity

@Table(name="t_incident_tt")
@Data
public class IncidentTestTemplate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_incident_id")
	private Integer incidentId;
	@Column(name = "m_test_template_id")
	private Integer testTemplateId;
	@Formula("(SELECT tt.test_template_name from m_test_template tt where tt.id=m_test_template_id)")
	private String testTemplateName;
	@Formula("(SELECT tt.duration from m_test_template tt where tt.id=m_test_template_id)")
	private String duration;
	@Formula("(SELECT tt.description from m_test_template tt where tt.id=m_test_template_id)")
	private String description;
	@Formula("(SELECT tt.total_questions from m_test_template tt where tt.id=m_test_template_id)")
	private String totalQuestions;
	@Column(name = "pass_percentage")
	private String passPercentage;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String remark;
	private String creationFlag="Y";
	private String creationStatus;
	private String creationMessage;
	@Formula("(SELECT COUNT(ia.is_test_scheduled) from t_incident_applicants ia where ia.t_incident_id=t_incident_id and ia.is_test_scheduled='Y')")
	private String scheduled;
	@Formula("(SELECT COUNT(tt.test_status) from t_incident_test tt where tt.t_incident_id=t_incident_id and tt.test_status='In-Progress')")
	private String currentlyAttending;
	@Formula("(SELECT COUNT(ia.test_scheduled_status) from t_incident_applicants ia where ia.t_incident_id=t_incident_id and ia.test_scheduled_status='COMPLETED')")
	private String completed;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_incident_tt_id")
	private List<IncidentTestTemplateQuestion> incidentApplicantTemplateQuestions= new ArrayList<IncidentTestTemplateQuestion>();
}

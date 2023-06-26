package com.ss.smartoffice.soservice.transaction.incident;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Scope;
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


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity

@Scope("prototype")
@Table(name="t_incident_test")
@Data
public class IncidentTest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="end_time")
	private String endTime;
	@Column(name="invigilator_comments")
	private String invigilatorComments;
	@Column(name="invigilator_id")
	private Integer invigilatorId;
	@Column(name="is_enabled")
	private Character isEnabled;
	@Column(name="no_of_questions")
	private String noOfQuestions;
	private String scoreMedian;
	@Column(name="participant_comment")
	private String participantComment;
	@Column(name="participant_id")
	private Integer participantId; 
	@Column(name = "pass_percentage")
	private String passPercentage;
	@Column(name="start_time")
	private String startTime;
	@Column(name="test_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime testDate; 
	@Column(name="test_status")
	private String testStatus;
	@Column(name="total_correct")
	private String totalCorrect;
	@Column(name="total_un_attended")
	private String totalUnAttended;
	@Column(name="total_wrong")
	private String totalWrong;
	@Column(name="total_result")
	private String totalResult;
	@Column(name="score")
	private String score;
	@Column(name="t_incident_id")
	private Integer incidentId;
	@Formula("(select inc.incident_type from t_incident inc where inc.id=t_incident_id)")
	private String incidentType;
	@Formula("(select inc.show_score from t_incident inc where inc.id=t_incident_id)")
	private String showScore;
	@Formula("(select inc.incident_name from t_incident inc join t_incident_test tt on tt.t_incident_id=inc.id where tt.ID=id)")
	private String incidentName;
	@Column(name="created_by")
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@Column(name="modified_by")
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Transient
	private String remainingTime;
	private String duration;
	@Column(name="incident_tt_id")
	private String incidentTtId;
	@Formula("(select inc.applicant_id from t_incident_applicants inc where inc.id=participant_id)")
	private String applicantId;
	@Formula("(select concat(inc.first_name,' ',inc.last_name) from t_incident_applicants inc where inc.id=participant_id)")
	private String applicantName;
	@Formula("(select tt.m_test_template_id from t_incident_tt tt where tt.id=incident_tt_id)")
	private String testTempLateId;
	@Formula("(select mm.test_template_name from m_test_template mm join t_incident_tt tt on mm.id=tt.m_test_template_id where tt.id=incident_tt_id )")
	private String testTemplateName;
	private String testResult;
	private String totalPositiveMarkObtained;
	private String totalNegativeMarkObtained;
	private String totalCorrectEasy;
	private String totalCorrectMedium;
	private String totalCorrectTough;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_incident_test_id")
	private List<IncidentTestQuestion> incidentTestQuestions;
	
}

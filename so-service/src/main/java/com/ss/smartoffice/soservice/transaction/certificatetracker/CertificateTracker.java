package com.ss.smartoffice.soservice.transaction.certificatetracker;
 
import java.time.LocalDateTime; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat; 

import lombok.Data;
@Entity
@Table(name="t_certificate")
@Data
public class CertificateTracker {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String incidentApplicantId;
	private String certificateCode;
	private String fname;
	private String lname;
	private String location;
	private String score;
	private String email;
	private String mobNum;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime issuedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expiryDate;
	private String certTemplateId;
	private String certificateDocId;
	private String skillsAqd;
	private String cmntsFromTrainer;
	private String isExpired;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public CertificateTracker() {
		super(); 
	} 
	
	
	
}

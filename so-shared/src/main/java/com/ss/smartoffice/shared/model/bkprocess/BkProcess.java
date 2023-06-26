package com.ss.smartoffice.shared.model.bkprocess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity

@Scope("prototype")
@Table(name="t_bk_process")
@Data
public class BkProcess {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String bkProcessTypeId;
	private String inputReferenceId;
	@Transient
	private String inputPayload;
	private String processCode;
	private String processStatus;
	@Transient
	private Map<String, String> additionalInfo;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expectedEndDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expiryDt;
	private String isExpired;
	private String reference;
	private String lastMessageUpdated;
	private String isError;
	private String errorMessage;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
}

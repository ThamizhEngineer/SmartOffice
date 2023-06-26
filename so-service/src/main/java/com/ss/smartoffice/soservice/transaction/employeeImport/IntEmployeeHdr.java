package com.ss.smartoffice.soservice.transaction.employeeImport;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity

@Table(name = "i_employee_hdr")
public class IntEmployeeHdr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String uploadDocId;
	private LocalDateTime uploadDate;
	private LocalDateTime processDate;
	private String overWritten;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String isError;
	private String errorMessage;
	private String status;
	
}

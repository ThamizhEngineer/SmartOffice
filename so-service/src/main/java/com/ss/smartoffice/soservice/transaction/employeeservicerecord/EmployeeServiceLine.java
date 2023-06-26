package com.ss.smartoffice.soservice.transaction.employeeservicerecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import lombok.Data;

@Entity
@Table(name="t_esr_line")
@Data
@Scope("prototype")
public class EmployeeServiceLine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_esr_hdr_id")
	private String EsrHdrId;
	private String busKey;
	private String busValue;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String createdDt;
	private String modifiedDt;
	

}

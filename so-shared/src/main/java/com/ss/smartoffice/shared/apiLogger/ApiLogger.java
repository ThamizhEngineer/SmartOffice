package com.ss.smartoffice.shared.apiLogger;

import java.time.LocalDateTime;
import java.time.Month;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "l_api")
@Data
public class ApiLogger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp; 
	private String url;
	private String userId;
	private String operationType;
	private String payload;
	private Integer month;
	private Integer year;
}

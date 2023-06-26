package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TestCategory {
	private Integer id;
	private String testCategoryName;
	private String easy;
	private String medium;
	private String tough;
	private String total;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime createdDt;
	private LocalDateTime modifiedDt;
}

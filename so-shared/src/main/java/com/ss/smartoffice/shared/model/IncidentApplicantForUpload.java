package com.ss.smartoffice.shared.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"FIRST_NAME","LAST_NAME","MOBILE_NUMBER","EMAIL","DOB","COLLEGE","COURSE","DEGREE",
	"EXPERIENCE_TYPE","CURRENT_COMPANY","CURRENT_DESIGNATION","CURRENT_EXPERIENCE","CURRENT_SALARY"})	
public class IncidentApplicantForUpload {
	@JsonProperty("FIRST_NAME")
	public String firstName;
	@JsonProperty("LAST_NAME")
	public String lastName;
	@JsonProperty("MOBILE_NUMBER")
	public String mobileNumber;
	@JsonProperty("EMAIL")
	public String email;
	@JsonProperty("DOB")
	public String dob;
	@JsonProperty("COLLEGE")
	public String college;
	@JsonProperty("COURSE")
	public String course;
	@JsonProperty("DEGREE")
	public String degree;
	@JsonProperty("EXPERIENCE_TYPE")
	public String exprienceType;
	@JsonProperty("CURRENT_COMPANY")
	public String currCompany;
	@JsonProperty("CURRENT_DESIGNATION")
	public String currDesignation;
	@JsonProperty("CURRENT_EXPERIENCE")
	public String currExprience;
	@JsonProperty("CURRENT_SALARY")
	public String currSalary;
	public String isClean;
	public String remarks;
}

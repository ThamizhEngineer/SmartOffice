package com.ss.smartoffice.shared.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"DATE","HOLIDAY_NAME","IS_RESTRICTED_HOLIDAY","REMARKS"})	
public class OfficeCalenderForUpload {
	@JsonProperty("DATE")
	private String calDate;
	@JsonProperty("HOLIDAY_NAME")
	private String holidayName;
	@JsonProperty("IS_RESTRICTED_HOLIDAY")
	private String isRestrictedHoliday;
	@JsonProperty("REMARKS")
	private String remarks;
}

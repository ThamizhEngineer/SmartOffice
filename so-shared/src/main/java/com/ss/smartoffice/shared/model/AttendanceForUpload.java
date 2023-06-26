package com.ss.smartoffice.shared.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"empCode","shiftCode","year","month","date","attendaceStatus","firstSession","secoundSession","checkIn","checkOut","inLat","inLong","outLat","outLong","remarks","proxyEmpCode"})	
public class AttendanceForUpload {

	@JsonProperty("empCode")	
	private String empCode;
	@JsonProperty("shiftCode")
	private String shiftCode;
	@JsonProperty("year")
	private String year;
	@JsonProperty("month")
	private String month;
	@JsonProperty("date")
	private String date;
	@JsonProperty("attendaceStatus")
	private String attendaceStatus;
	@JsonProperty("firstSession")
	private String firstSession;
	@JsonProperty("secoundSession")
	private String secoundSession;
	@JsonProperty("checkIn")
	private String checkIn;
	@JsonProperty("checkOut")
	private String checkOut;
	@JsonProperty("inLat")
	private String inLat;
	@JsonProperty("inLong")
	private String inLong;
	@JsonProperty("outLat")
	private String outLat;
	@JsonProperty("outLong")
	private String outLong;
	@JsonProperty("remarks")
	private String remarks;
	@JsonProperty("proxyEmpCode")
	private String proxyEmpCode;
	@Transient
	private String docId;

}

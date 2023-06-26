package com.ss.smartoffice.soservice.transaction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Entity
@Table(name="s_manual_upload_map")

@Component
public class ManualUploadMap {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String mapType;
	private String payoutTypeId;
	private String csvLabel;
	private String csvValue;
	private String grouping;
	private String csvPeriodValue;
	private String csvArrearValue;
	private String csvYtdValue;
	private String leaveValueOpening;
	private String leaveValueAccured;
	private String leaveValueAvailed;
	private String leaveValueBalance;
	private String payoutColumnName;
	public ManualUploadMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManualUploadMap(Integer id, String mapType, String payoutTypeId, String csvLabel, String csvValue,
			String grouping, String csvPeriodValue, String csvArrearValue, String csvYtdValue, String leaveValueOpening,
			String leaveValueAccured, String leaveValueAvailed, String leaveValueBalance, String payoutColumnName) {
		super();
		this.id = id;
		this.mapType = mapType;
		this.payoutTypeId = payoutTypeId;
		this.csvLabel = csvLabel;
		this.csvValue = csvValue;
		this.grouping = grouping;
		this.csvPeriodValue = csvPeriodValue;
		this.csvArrearValue = csvArrearValue;
		this.csvYtdValue = csvYtdValue;
		this.leaveValueOpening = leaveValueOpening;
		this.leaveValueAccured = leaveValueAccured;
		this.leaveValueAvailed = leaveValueAvailed;
		this.leaveValueBalance = leaveValueBalance;
		this.payoutColumnName = payoutColumnName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMapType() {
		return mapType;
	}
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
	public String getPayoutTypeId() {
		return payoutTypeId;
	}
	public void setPayoutTypeId(String payoutTypeId) {
		this.payoutTypeId = payoutTypeId;
	}
	public String getCsvLabel() {
		return csvLabel;
	}
	public void setCsvLabel(String csvLabel) {
		this.csvLabel = csvLabel;
	}
	public String getCsvValue() {
		return csvValue;
	}
	public void setCsvValue(String csvValue) {
		this.csvValue = csvValue;
	}
	public String getGrouping() {
		return grouping;
	}
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	public String getCsvPeriodValue() {
		return csvPeriodValue;
	}
	public void setCsvPeriodValue(String csvPeriodValue) {
		this.csvPeriodValue = csvPeriodValue;
	}
	public String getCsvArrearValue() {
		return csvArrearValue;
	}
	public void setCsvArrearValue(String csvArrearValue) {
		this.csvArrearValue = csvArrearValue;
	}
	public String getCsvYtdValue() {
		return csvYtdValue;
	}
	public void setCsvYtdValue(String csvYtdValue) {
		this.csvYtdValue = csvYtdValue;
	}
	public String getLeaveValueOpening() {
		return leaveValueOpening;
	}
	public void setLeaveValueOpening(String leaveValueOpening) {
		this.leaveValueOpening = leaveValueOpening;
	}
	public String getLeaveValueAccured() {
		return leaveValueAccured;
	}
	public void setLeaveValueAccured(String leaveValueAccured) {
		this.leaveValueAccured = leaveValueAccured;
	}
	public String getLeaveValueAvailed() {
		return leaveValueAvailed;
	}
	public void setLeaveValueAvailed(String leaveValueAvailed) {
		this.leaveValueAvailed = leaveValueAvailed;
	}
	public String getLeaveValueBalance() {
		return leaveValueBalance;
	}
	public void setLeaveValueBalance(String leaveValueBalance) {
		this.leaveValueBalance = leaveValueBalance;
	}
	public String getPayoutColumnName() {
		return payoutColumnName;
	}
	public void setPayoutColumnName(String payoutColumnName) {
		this.payoutColumnName = payoutColumnName;
	}
	@Override
	public String toString() {
		return "ManualUploadMap [id=" + id + ", mapType=" + mapType + ", payoutTypeId=" + payoutTypeId + ", csvLabel="
				+ csvLabel + ", csvValue=" + csvValue + ", grouping=" + grouping + ", csvPeriodValue=" + csvPeriodValue
				+ ", csvArrearValue=" + csvArrearValue + ", csvYtdValue=" + csvYtdValue + ", leaveValueOpening="
				+ leaveValueOpening + ", leaveValueAccured=" + leaveValueAccured + ", leaveValueAvailed="
				+ leaveValueAvailed + ", leaveValueBalance=" + leaveValueBalance + ", payoutColumnName="
				+ payoutColumnName + "]";
	}
	
	

}

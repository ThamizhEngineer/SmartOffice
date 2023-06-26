package com.ss.smartoffice.shared.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "v_payable")
public class Payable {
	@Id
	@Column(name = "po_id", columnDefinition = "int")
	private String poId;
	private String poCode;
	private int vendorId;
	private String vendorCode;
	private String vendorName;
	@Column(name = "s_country_id", columnDefinition = "int")
	private String countryId;
	private String countryName;
	@Column(columnDefinition = "text")
	private String materialName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate poDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dueDt;
	@Column(columnDefinition = "int")
	private String monthNo;
	private String monthName;
	@Column(columnDefinition = "int")
	private String quarter;
	private String quarterName;
	@Column(columnDefinition = "int")
	private String year;
	@Column(columnDefinition="double", precision=20, scale=4)
	private String poAmt;
	@Column(columnDefinition="double", precision=20, scale=4)
	private String poPaidAmt;
	@Column(columnDefinition="double", precision=20, scale=4)
	private String totalDueAmt;
	@Column(columnDefinition="double", precision=20, scale=4)
	private String totalOverdueAmt;
	public Payable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payable(String poId, String poCode, int vendorId, String vendorCode, String vendorName, String countryId,
			String countryName, String materialName, LocalDate poDt, LocalDateTime dueDt, String monthNo,
			String monthName, String quarter, String quarterName, String year, String poAmt, String poPaidAmt,
			String totalDueAmt, String totalOverdueAmt) {
		super();
		this.poId = poId;
		this.poCode = poCode;
		this.vendorId = vendorId;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.countryId = countryId;
		this.countryName = countryName;
		this.materialName = materialName;
		this.poDt = poDt;
		this.dueDt = dueDt;
		this.monthNo = monthNo;
		this.monthName = monthName;
		this.quarter = quarter;
		this.quarterName = quarterName;
		this.year = year;
		this.poAmt = poAmt;
		this.poPaidAmt = poPaidAmt;
		this.totalDueAmt = totalDueAmt;
		this.totalOverdueAmt = totalOverdueAmt;
	}

	public String getPoId() {
		return poId;
	}
	public void setPoId(String poId) {
		this.poId = poId;
	}
	public String getPoCode() {
		return poCode;
	}
	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public LocalDate getPoDt() {
		return poDt;
	}
	public void setPoDt(LocalDate poDt) {
		this.poDt = poDt;
	}
	public LocalDateTime getDueDt() {
		return dueDt;
	}
	public void setDueDt(LocalDateTime dueDt) {
		this.dueDt = dueDt;
	}
	public String getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(String monthNo) {
		this.monthNo = monthNo;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarterName() {
		return quarterName;
	}
	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPoPaidAmt() {
		return poPaidAmt;
	}
	public void setPoPaidAmt(String poPaidAmt) {
		this.poPaidAmt = poPaidAmt;
	}
	
	public String getPoAmt() {
		return poAmt;
	}
	public void setPoAmt(String poAmt) {
		this.poAmt = poAmt;
	}
	public String getTotalDueAmt() {
		return totalDueAmt;
	}
	public void setTotalDueAmt(String totalDueAmt) {
		this.totalDueAmt = totalDueAmt;
	}
	public String getTotalOverdueAmt() {
		return totalOverdueAmt;
	}
	public void setTotalOverdueAmt(String totalOverdueAmt) {
		this.totalOverdueAmt = totalOverdueAmt;
	}
	@Override
	public String toString() {
		return "Payable [poId=" + poId + ", poCode=" + poCode + ", vendorId=" + vendorId + ", vendorCode=" + vendorCode
				+ ", vendorName=" + vendorName + ", countryId=" + countryId + ", countryName=" + countryName
				+ ", materialName=" + materialName + ", poDt=" + poDt + ", dueDt=" + dueDt + ", monthNo=" + monthNo
				+ ", monthName=" + monthName + ", quarter=" + quarter + ", quarterName=" + quarterName + ", year="
				+ year + ", poAmt=" + poAmt + ", poPaidAmt=" + poPaidAmt + ", totalDueAmt=" + totalDueAmt
				+ ", totalOverdueAmt=" + totalOverdueAmt + "]";
	}
	
	
}

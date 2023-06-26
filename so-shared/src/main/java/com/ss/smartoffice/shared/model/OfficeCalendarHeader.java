package com.ss.smartoffice.shared.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Entity
@Table(name="m_office_cal_hdr")

@Scope("prototype")
public class OfficeCalendarHeader {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String officeId;
	@Formula("(SELECT ofice.office_name FROM m_office ofice WHERE ofice.id = office_id)")
	private String officeName;
	private String calYearId;
	@Formula("(SELECT cal.cal_code from s_cal_year cal where cal.id=cal_year_id)")
	private String calYearCode;
	@Formula("(SELECT cal.from_dt from s_cal_year cal where cal.id=cal_year_id)")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@Formula("(SELECT cal.to_dt from s_cal_year cal where cal.id=cal_year_id)")
	private String toDt;
	private String calStatus;
	@Transient
	private Integer totalCalendarDays;
	@Formula("(SELECT COUNT(*) from m_office_cal oc where oc.cal_year_id=cal_year_id AND oc.office_id=office_id AND oc.day_type='weekday')")
	private Integer weekdayCount;
	@Formula("(SELECT COUNT(*) from m_office_cal oc where oc.cal_year_id=cal_year_id AND oc.office_id=office_id AND oc.day_type='holiday')")
	private Integer holidayCount;
	@Formula("(SELECT COUNT(*) from m_office_cal oc where oc.cal_year_id=cal_year_id AND oc.office_id=office_id AND oc.day_type='weekend')")
	private Integer weekendCount;
	@Transient
	private Integer totalCalculatedDays;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public OfficeCalendarHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getCalYearId() {
		return calYearId;
	}
	public void setCalYearId(String calYearId) {
		this.calYearId = calYearId;
	}
	public String getCalYearCode() {
		return calYearCode;
	}
	public void setCalYearCode(String calYearCode) {
		this.calYearCode = calYearCode;
	}
	public LocalDate getFromDt() {
		return fromDt;
	}
	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getCalStatus() {
		return calStatus;
	}
	public void setCalStatus(String calStatus) {
		this.calStatus = calStatus;
	}
	public Integer getTotalCalendarDays() {
		return totalCalendarDays = fromDt.lengthOfYear();
	}
	public void setTotalCalendarDays(Integer totalCalendarDays) {
		this.totalCalendarDays = totalCalendarDays;
	}
	public Integer getWeekdayCount() {
		return weekdayCount;
	}
	public void setWeekdayCount(Integer weekdayCount) {
		this.weekdayCount = weekdayCount;
	}
	public Integer getHolidayCount() {
		return holidayCount;
	}
	public void setHolidayCount(Integer holidayCount) {
		this.holidayCount = holidayCount;
	}
	public Integer getWeekendCount() {
		return weekendCount;
	}
	public void setWeekendCount(Integer weekendCount) {
		this.weekendCount = weekendCount;
	}
	public Integer getTotalCalculatedDays() {
		return totalCalculatedDays = weekdayCount + weekendCount + holidayCount;
	}
	public void setTotalCalculatedDays(Integer totalCalculatedDays) {
		this.totalCalculatedDays = totalCalculatedDays;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "OfficeCalendarHeader [id=" + id + ", officeId=" + officeId + ", officeName=" + officeName
				+ ", calYearId=" + calYearId + ", calYearCode=" + calYearCode + ", fromDt=" + fromDt + ", toDt=" + toDt
				+ ", calStatus=" + calStatus + ", totalCalendarDays=" + totalCalendarDays + ", weekdayCount="
				+ weekdayCount + ", holidayCount=" + holidayCount + ", weekendCount=" + weekendCount
				+ ", totalCalculatedDays=" + totalCalculatedDays + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	
	
	
}

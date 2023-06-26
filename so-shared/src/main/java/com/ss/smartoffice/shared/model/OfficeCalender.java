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
@Table(name="m_office_cal")

@Scope("prototype")
@Data
public class OfficeCalender {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String officeId;
	@Formula("(SELECT ofice.office_name FROM m_office ofice WHERE ofice.id = office_id)")
	private String officeName;
	@Formula("(SELECT ofice.shift_id FROM m_office ofice WHERE ofice.id = office_id)")
	private String officeShiftId;
	@Formula("(select shift.Shift_name from m_attendance_shift shift left join m_office office on shift.id = office.shift_id where office.id = office_id)")
	private String officeShiftName;
	@Formula("(select shift.Shift_code from m_attendance_shift shift left join m_office office on shift.id = office.shift_id where office.id = office_id)")
	private String officeShiftCode;
	private String calYearId;
	@Formula("(SELECT cal.cal_code from s_cal_year cal where cal.id=cal_year_id)")
	private String calYearCode;
	@Formula("(SELECT cal.from_dt from s_cal_year cal where cal.id=cal_year_id)")
	private String fromDt;
	@Formula("(SELECT cal.to_dt from s_cal_year cal where cal.id=cal_year_id)")
	private String toDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate calDate;
	@Formula("(SELECT DATE_FORMAT(cal.cal_date, '%Y') from m_office_cal cal where cal.id=id)")
	private String year;
	@Formula("(SELECT DATE_FORMAT(cal.cal_date, '%m') from m_office_cal cal where cal.id=id)")
	private String month;
	@Formula("(SELECT DATE_FORMAT(cal.cal_date, '%W') from m_office_cal cal where cal.id=id)")
	private String day;
	private String dayType;
	private String holidayName;
	private String isRestrictedHoliday;
	private String remarks;
	private String updateReason;
	private String payrollCompleted;
	@Transient
	private String weekdayCount;
	@Transient
	private String holidayCount;
	@Transient
	private String weekendCount;
	@Formula("(SELECT DATE_FORMAT(cal.cal_date, '%M') from m_office_cal cal where cal.id=id)")
	private String summaryMonth;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Transient
	private List<String> multiDelete;
}

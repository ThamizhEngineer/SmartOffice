package com.ss.smartoffice.soservice.master.shift;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="m_attendance_shift")

@Data
public class AttendanceShift {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String shiftCode;
	private String ShiftName;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime fromTime;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime midTime;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime toTime;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime attendanceExpTime;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
}

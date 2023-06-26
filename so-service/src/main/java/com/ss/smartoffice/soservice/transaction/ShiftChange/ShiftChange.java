package com.ss.smartoffice.soservice.transaction.ShiftChange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity

@Scope("prototype")
@Table(name="t_shift_change")
@Data
public class ShiftChange {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String empId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=emp_id)")
	private String empName;
	@Formula("(select emp.n1_emp_id from m_employee emp where emp.id=emp_id)")
	private String n1Id;
	@Formula("(select emp.n2_emp_id from m_employee emp where emp.id=emp_id)")
	private String n2Id;
	@Formula("(select emp.hr_1_usr_grp_id from m_employee emp where emp.id=emp_id)")
	private String hr1GroupId;
	@Formula("(select emp.hr_1_usr_grp_id from m_employee emp where emp.id=emp_id)")
	private String currShiftId;
	@Formula("(SELECT shift.Shift_name from m_employee emp LEFT join m_attendance_shift shift on shift.id=emp.shift_id where emp.id=emp_id)")
	private String currShiftName;	
	private String newShiftId;
	@Formula("(select shift.Shift_name from m_attendance_shift shift where shift.id=new_shift_id)")
	private String newShiftName;
	@Formula("(SELECT shift.from_time from m_attendance_shift shift where shift.id=new_shift_id)")
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime fromTime;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	private String isComplete="N";
	private String status;
	private String empReason;
	private String remark;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
}

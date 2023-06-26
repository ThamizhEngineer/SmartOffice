package com.ss.smartoffice.soservice.master.offices;

import java.time.LocalDateTime;

import javax.persistence.Column;
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

@Entity
@Table(name = "m_office")

@Scope("prototype")
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String officeName;
	private String officeCode;
	private String description;
	private String countryCode;
	private String employeeId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=employee_id)")
	private String empName;
	@Column(name = "s_country_id")
	private Integer countryId;
	private String shiftId;
	@Formula("(select shift.shift_name from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftName;
	@Formula("(select shift.from_time from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftFromTime;
	@Formula("(select shift.to_time from m_attendance_shift shift where shift.id=shift_id)")
	private String shiftToTime;
	private String officeAbbreviation;
	private String lastEmpSequence;
	private String mapLocId;
	@Formula("(select map.loc_name from m_map_location map where map.id=map_loc_id)")
	private String locName;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public Office() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Office(int id, String officeName,String officeCode, String description, String countryCode, String employeeId, String empName,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.officeName = officeName;
		this.officeCode = officeCode;
		this.description = description;
		this.countryCode = countryCode;
		this.employeeId = employeeId;
		this.empName = empName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	public Office(int id, String officeName, String officeCode, String description, String countryCode,
			String employeeId, String empName, Integer countryId, String shiftId, String shiftName,
			String shiftFromTime, String shiftToTime, String officeAbbreviation, String lastEmpSequence,
			String mapLocId, String locName, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.officeName = officeName;
		this.officeCode = officeCode;
		this.description = description;
		this.countryCode = countryCode;
		this.employeeId = employeeId;
		this.empName = empName;
		this.countryId = countryId;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.shiftFromTime = shiftFromTime;
		this.shiftToTime = shiftToTime;
		this.officeAbbreviation = officeAbbreviation;
		this.lastEmpSequence = lastEmpSequence;
		this.mapLocId = mapLocId;
		this.locName = locName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getShiftFromTime() {
		return shiftFromTime;
	}

	public void setShiftFromTime(String shiftFromTime) {
		this.shiftFromTime = shiftFromTime;
	}

	public String getShiftToTime() {
		return shiftToTime;
	}

	public void setShiftToTime(String shiftToTime) {
		this.shiftToTime = shiftToTime;
	}

	public String getMapLocId() {
		return mapLocId;
	}

	public void setMapLocId(String mapLocId) {
		this.mapLocId = mapLocId;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getOfficeAbbreviation() {
		return officeAbbreviation;
	}

	public void setOfficeAbbreviation(String officeAbbreviation) {
		this.officeAbbreviation = officeAbbreviation;
	}

	public String getLastEmpSequence() {
		return lastEmpSequence;
	}

	public void setLastEmpSequence(String lastEmpSequence) {
		this.lastEmpSequence = lastEmpSequence;
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
		return "Office [id=" + id + ", officeName=" + officeName + ", description=" + description + ", countryCode="
				+ countryCode + ", employeeId=" + employeeId + ", empName=" + empName + ", countryId=" + countryId
				+ ", shiftId=" + shiftId + ", shiftName=" + shiftName + ", shiftFromTime=" + shiftFromTime
				+ ", shiftToTime=" + shiftToTime + ", officeAbbreviation=" + officeAbbreviation + ", lastEmpSequence="
				+ lastEmpSequence + ", mapLocId=" + mapLocId + ", locName=" + locName + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

}

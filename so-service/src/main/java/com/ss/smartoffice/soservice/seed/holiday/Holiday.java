package com.ss.smartoffice.soservice.seed.holiday;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="s_holiday")

@Scope("prototype")
public class Holiday extends BaseEntity{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String holidayName;
@JsonFormat(pattern="yyyy-MM-dd")
private LocalDate holidayDt;
private String firstSession;
private String secondSession;
private String restrictedHoliday;
private int locationId;
private String isAdhoc;
private String isEnabled;
private String createdBy;
private String modifiedBy;
@CreationTimestamp
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private LocalDateTime createdDt;
@UpdateTimestamp
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
private LocalDateTime modifiedDt;
@Formula("(SELECT year(h.holiday_dt) FROM s_holiday h where h.id=id)")
private String holidayYear;
public Holiday() {
	super();
	// TODO Auto-generated constructor stub
}
public Holiday(int id, String holidayName, LocalDate holidayDt, String firstSession, String secondSession,
		String restrictedHoliday, int locationId, String isAdhoc, String isEnabled, String createdBy, String modifiedBy,
		LocalDateTime createdDt, LocalDateTime modifiedDt, String holidayYear) {
	super();
	this.id = id;
	this.holidayName = holidayName;
	this.holidayDt = holidayDt;
	this.firstSession = firstSession;
	this.secondSession = secondSession;
	this.restrictedHoliday = restrictedHoliday;
	this.locationId = locationId;
	this.isAdhoc = isAdhoc;
	this.isEnabled = isEnabled;
	this.createdBy = createdBy;
	this.modifiedBy = modifiedBy;
	this.createdDt = createdDt;
	this.modifiedDt = modifiedDt;
	this.holidayYear = holidayYear;
}
@Override
public String toString() {
	return "Holiday [id=" + id + ", holidayName=" + holidayName + ", holidayDt=" + holidayDt + ", firstSession="
			+ firstSession + ", secondSession=" + secondSession + ", restrictedHoliday=" + restrictedHoliday
			+ ", locationId=" + locationId + ", isAdhoc=" + isAdhoc + ", isEnabled=" + isEnabled + ", createdBy="
			+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
			+ ", holidayYear=" + holidayYear + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getHolidayName() {
	return holidayName;
}
public void setHolidayName(String holidayName) {
	this.holidayName = holidayName;
}
public LocalDate getHolidayDt() {
	return holidayDt;
}
public void setHolidayDt(LocalDate holidayDt) {
	this.holidayDt = holidayDt;
}
public String getFirstSession() {
	return firstSession;
}
public void setFirstSession(String firstSession) {
	this.firstSession = firstSession;
}
public String getSecondSession() {
	return secondSession;
}
public void setSecondSession(String secondSession) {
	this.secondSession = secondSession;
}
public String getRestrictedHoliday() {
	return restrictedHoliday;
}
public void setRestrictedHoliday(String restrictedHoliday) {
	this.restrictedHoliday = restrictedHoliday;
}
public int getLocationId() {
	return locationId;
}
public void setLocationId(int locationId) {
	this.locationId = locationId;
}
public String getIsAdhoc() {
	return isAdhoc;
}
public void setIsAdhoc(String isAdhoc) {
	this.isAdhoc = isAdhoc;
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
public String getHolidayYear() {
	return holidayYear;
}
public void setHolidayYear(String holidayYear) {
	this.holidayYear = holidayYear;
}

}

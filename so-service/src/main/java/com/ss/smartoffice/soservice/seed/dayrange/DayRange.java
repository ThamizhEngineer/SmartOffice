package com.ss.smartoffice.soservice.seed.dayrange;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="s_day_range")
@Scope("prototype")
public class DayRange {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String dayRangeName;
	private Integer fromDay;
	private Integer toDay;
	private Double stayExpenseAmount;
	private Double travelExpenseAmount;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public DayRange() {
		super();
		}
	public DayRange(Integer id, String dayRangeName, Integer fromDay, Integer toDay, Double stayExpenseAmount,
			Double travelExpenseAmount, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.dayRangeName = dayRangeName;
		this.fromDay = fromDay;
		this.toDay = toDay;
		this.stayExpenseAmount = stayExpenseAmount;
		this.travelExpenseAmount = travelExpenseAmount;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDayRangeName() {
		return dayRangeName;
	}
	public void setDayRangeName(String dayRangeName) {
		this.dayRangeName = dayRangeName;
	}
	public Integer getFromDay() {
		return fromDay;
	}
	public void setFromDay(Integer fromDay) {
		this.fromDay = fromDay;
	}
	public Integer getToDay() {
		return toDay;
	}
	public void setToDay(Integer toDay) {
		this.toDay = toDay;
	}
	public Double getStayExpenseAmount() {
		return stayExpenseAmount;
	}
	public void setStayExpenseAmount(Double stayExpenseAmount) {
		this.stayExpenseAmount = stayExpenseAmount;
	}
	public Double getTravelExpenseAmount() {
		return travelExpenseAmount;
	}
	public void setTravelExpenseAmount(Double travelExpenseAmount) {
		this.travelExpenseAmount = travelExpenseAmount;
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
		return "DayRange [id=" + id + ", dayRangeName=" + dayRangeName + ", fromDay=" + fromDay + ", toDay=" + toDay
				+ ", stayExpenseAmount=" + stayExpenseAmount + ", travelExpenseAmount=" + travelExpenseAmount
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}

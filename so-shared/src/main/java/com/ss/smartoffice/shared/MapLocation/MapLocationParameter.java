package com.ss.smartoffice.shared.MapLocation;

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
@Table(name=("t_map_location_parameter"))

@Scope("prototype")
public class MapLocationParameter {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String address;
	private Integer limitParameter;
	private String locName;
	private String countryCode;
	private String status;
	private String isCompleted;
	private Float lats;
	private Float longs;
	private String isError;	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public MapLocationParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapLocationParameter(Integer id, String address, Integer limitParameter, String locName, String countryCode,
			String status, String isCompleted, Float lats, Float longs, String isError, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.address = address;
		this.limitParameter = limitParameter;
		this.locName = locName;
		this.countryCode = countryCode;
		this.status = status;
		this.isCompleted = isCompleted;
		this.lats = lats;
		this.longs = longs;
		this.isError = isError;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "MapLocationParameter [id=" + id + ", address=" + address + ", limitParameter=" + limitParameter
				+ ", locName=" + locName + ", countryCode=" + countryCode + ", status=" + status + ", isCompleted="
				+ isCompleted + ", lats=" + lats + ", longs=" + longs + ", isError=" + isError + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLimitParameter() {
		return limitParameter;
	}

	public void setLimitParameter(Integer limitParameter) {
		this.limitParameter = limitParameter;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Float getLats() {
		return lats;
	}

	public void setLats(Float lats) {
		this.lats = lats;
	}

	public Float getLongs() {
		return longs;
	}

	public void setLongs(Float longs) {
		this.longs = longs;
	}

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
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

	
	
}

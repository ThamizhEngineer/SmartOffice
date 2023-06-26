package com.ss.smartoffice.shared.MapLocation;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name=("m_map_location"))

public class MapLocation {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String locName;
	private String address;
	private float lats;
	private float longs;
	private String locType;
	private String country;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public MapLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MapLocation(Integer id, String locName, String address, float lats, float longs, String locType,
			String country) {
		super();
		this.id = id;
		this.locName = locName;
		this.address = address;
		this.lats = lats;
		this.longs = longs;
		this.locType = locType;
		this.country = country;
		
	}

	public MapLocation(Integer id, String locName, String address, float lats, float longs, String locType,
			String country, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.locName = locName;
		this.address = address;
		this.lats = lats;
		this.longs = longs;
		this.locType = locType;
		this.country = country;
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
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getLats() {
		return lats;
	}
	public void setLats(float lats) {
		this.lats = lats;
	}
	public float getLongs() {
		return longs;
	}
	public void setLongs(float longs) {
		this.longs = longs;
	}
	public String getLocType() {
		return locType;
	}
	public void setLocType(String locType) {
		this.locType = locType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
		return "MapLocation [id=" + id + ", locName=" + locName + ", address=" + address + ", lats=" + lats + ", longs="
				+ longs + ", locType=" + locType + ", country=" + country + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	
}

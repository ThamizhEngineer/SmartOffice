package com.ss.smartoffice.soservice.master.sitelocation;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_site_location")

public class SiteLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String country;
	private String endClientId;
	@Formula("(select mp.client_name from m_partner mp where mp.id=end_client_id)")
	private String endClientName;
	private String siteName;
	private String siteAddress;
	private String contactName;
	private String mobileNumber;
	private String remarks;
	private String mapLocationId;
	@Formula("(select mml.loc_name from m_map_location mml where mml.id=map_location_id)")
	private String mapLocationName;
	@Formula("(select mml.lats from m_map_location mml where mml.id=map_location_id)")
	private String lats;
	@Formula("(select mml.longs from m_map_location mml where mml.id=map_location_id)")
	private String longs;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	
	public SiteLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SiteLocation(int id, String country, String endClientId, String endClientName, String siteName,
			String siteAddress, String contactName, String mobileNumber, String remarks, String mapLocationId,
			String mapLocationName, String lats, String longs, String createdBy, LocalDateTime createdDate,
			String modifiedBy, LocalDateTime modifiedDate) {
		super();
		this.id = id;
		this.country = country;
		this.endClientId = endClientId;
		this.endClientName = endClientName;
		this.siteName = siteName;
		this.siteAddress = siteAddress;
		this.contactName = contactName;
		this.mobileNumber = mobileNumber;
		this.remarks = remarks;
		this.mapLocationId = mapLocationId;
		this.mapLocationName = mapLocationName;
		this.lats = lats;
		this.longs = longs;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "SiteLocation [id=" + id + ", country=" + country + ", endClientId=" + endClientId + ", endClientName="
				+ endClientName + ", siteName=" + siteName + ", siteAddress=" + siteAddress + ", contactName="
				+ contactName + ", mobileNumber=" + mobileNumber + ", remarks=" + remarks + ", mapLocationId="
				+ mapLocationId + ", mapLocationName=" + mapLocationName + ", lats=" + lats + ", longs=" + longs
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEndClientId() {
		return endClientId;
	}

	public void setEndClientId(String endClientId) {
		this.endClientId = endClientId;
	}

	public String getEndClientName() {
		return endClientName;
	}

	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMapLocationId() {
		return mapLocationId;
	}

	public void setMapLocationId(String mapLocationId) {
		this.mapLocationId = mapLocationId;
	}

	public String getMapLocationName() {
		return mapLocationName;
	}

	public void setMapLocationName(String mapLocationName) {
		this.mapLocationName = mapLocationName;
	}

	public String getLats() {
		return lats;
	}

	public void setLats(String lats) {
		this.lats = lats;
	}

	public String getLongs() {
		return longs;
	}

	public void setLongs(String longs) {
		this.longs = longs;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	
}

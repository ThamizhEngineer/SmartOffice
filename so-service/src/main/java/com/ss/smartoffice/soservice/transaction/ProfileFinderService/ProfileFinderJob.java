package com.ss.smartoffice.soservice.transaction.ProfileFinderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = ("t_pf_job"))

@Scope("prototype")
public class ProfileFinderJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mProfileId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	private String mapLocationId;
	private String status;
	private String remarks;
	private String mEmployeeId;
	private String mDepartmentId;
	private String mBuId;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "profile_finder_id")
	private List<MatchedEmployee> matchedEmployees;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ProfileFinderJob() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProfileFinderJob(Integer id, String mProfileId, LocalDate fromDt, LocalDate toDt, String mapLocationId,
			String status, String remarks, String mEmployeeId, String mDepartmentId, String mBuId,
			List<MatchedEmployee> matchedEmployees, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.mProfileId = mProfileId;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.mapLocationId = mapLocationId;
		this.status = status;
		this.remarks = remarks;
		this.mEmployeeId = mEmployeeId;
		this.mDepartmentId = mDepartmentId;
		this.mBuId = mBuId;
		this.matchedEmployees = matchedEmployees;
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
	public String getmProfileId() {
		return mProfileId;
	}
	public void setmProfileId(String mProfileId) {
		this.mProfileId = mProfileId;
	}
	public LocalDate getFromDt() {
		return fromDt;
	}
	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}
	public LocalDate getToDt() {
		return toDt;
	}
	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}
	public String getMapLocationId() {
		return mapLocationId;
	}
	public void setMapLocationId(String mapLocationId) {
		this.mapLocationId = mapLocationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getmEmployeeId() {
		return mEmployeeId;
	}
	public void setmEmployeeId(String mEmployeeId) {
		this.mEmployeeId = mEmployeeId;
	}
	public String getmDepartmentId() {
		return mDepartmentId;
	}
	public void setmDepartmentId(String mDepartmentId) {
		this.mDepartmentId = mDepartmentId;
	}
	public String getmBuId() {
		return mBuId;
	}
	public void setmBuId(String mBuId) {
		this.mBuId = mBuId;
	}
	public List<MatchedEmployee> getMatchedEmployees() {
		return matchedEmployees;
	}
	public void setMatchedEmployees(List<MatchedEmployee> matchedEmployees) {
		this.matchedEmployees = matchedEmployees;
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
		return "ProfileFinderJob [id=" + id + ", mProfileId=" + mProfileId + ", fromDt=" + fromDt + ", toDt=" + toDt
				+ ", mapLocationId=" + mapLocationId + ", status=" + status + ", remarks=" + remarks + ", mEmployeeId="
				+ mEmployeeId + ", mDepartmentId=" + mDepartmentId + ", mBuId=" + mBuId + ", matchedEmployees="
				+ matchedEmployees + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	

}

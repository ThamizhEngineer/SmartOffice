package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
@Table(name="t_job_travel")

@Scope("prototype")
public class JobTravel {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private String tJobId;
	private String tJobEmpId;
	private String travelType;
	private String travelNumber;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime travelTime;
	private String pnrNo;
	private String boardingPoint;
	private String droppingAt;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public JobTravel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobTravel(Integer id, String tJobId, String tJobEmpId, String travelType, String travelNumber,
			LocalDateTime travelTime, String pnrNo, String boardingPoint, String droppingAt, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tJobId = tJobId;
		this.tJobEmpId = tJobEmpId;
		this.travelType = travelType;
		this.travelNumber = travelNumber;
		this.travelTime = travelTime;
		this.pnrNo = pnrNo;
		this.boardingPoint = boardingPoint;
		this.droppingAt = droppingAt;
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
	public String gettJobId() {
		return tJobId;
	}
	public void settJobId(String tJobId) {
		this.tJobId = tJobId;
	}
	public String gettJobEmpId() {
		return tJobEmpId;
	}
	public void settJobEmpId(String tJobEmpId) {
		this.tJobEmpId = tJobEmpId;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getTravelNumber() {
		return travelNumber;
	}
	public void setTravelNumber(String travelNumber) {
		this.travelNumber = travelNumber;
	}
	public LocalDateTime getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(LocalDateTime travelTime) {
		this.travelTime = travelTime;
	}
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDroppingAt() {
		return droppingAt;
	}
	public void setDroppingAt(String droppingAt) {
		this.droppingAt = droppingAt;
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
		return "JobTravel [id=" + id + ", tJobId=" + tJobId + ", tJobEmpId=" + tJobEmpId + ", travelType=" + travelType
				+ ", travelNumber=" + travelNumber + ", travelTime=" + travelTime + ", pnrNo=" + pnrNo
				+ ", boardingPoint=" + boardingPoint + ", droppingAt=" + droppingAt + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

	
}

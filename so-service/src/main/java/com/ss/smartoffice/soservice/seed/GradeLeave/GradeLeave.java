package com.ss.smartoffice.soservice.seed.GradeLeave;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="s_grade_leave")

public class GradeLeave extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String gradeId;
	private String leaveTypeId;
	private String fiscalYearId;
	private String openingLeaveBalance;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public GradeLeave() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GradeLeave(int id, String gradeId, String leaveTypeId, String fiscalYearId, String openingLeaveBalance,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.gradeId = gradeId;
		this.leaveTypeId = leaveTypeId;
		this.fiscalYearId = fiscalYearId;
		this.openingLeaveBalance = openingLeaveBalance;
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
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}
	public String getOpeningLeaveBalance() {
		return openingLeaveBalance;
	}
	public void setOpeningLeaveBalance(String openingLeaveBalance) {
		this.openingLeaveBalance = openingLeaveBalance;
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
		return "GradeLeave [id=" + id + ", gradeId=" + gradeId + ", leaveTypeId=" + leaveTypeId + ", fiscalYearId="
				+ fiscalYearId + ", openingLeaveBalance=" + openingLeaveBalance + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	

}

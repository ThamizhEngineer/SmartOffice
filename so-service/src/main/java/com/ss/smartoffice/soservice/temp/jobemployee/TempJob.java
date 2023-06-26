package com.ss.smartoffice.soservice.temp.jobemployee;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="temp_job")
@SecondaryTable(name="temp_job_emp")
public class TempJob {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String jobName;
	private String jobCode;
	private String officeId;
	@Formula("(select off.office_name from m_office off where off.id=office_id)")
	private String officeName;
	private String jobActive;
	@OneToMany(cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="job_id")
	private List<TempJobEmp> tempJobEmps;
	private String isEnabled;
	private String  createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public TempJob() {
		super();
	}
	
	public TempJob(Integer id, String jobName, String jobCode, String officeId, String officeName, String jobActive,
			List<TempJobEmp> tempJobEmps, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.jobCode = jobCode;
		this.officeId = officeId;
		this.officeName = officeName;
		this.jobActive = jobActive;
		this.tempJobEmps = tempJobEmps;
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
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public List<TempJobEmp> getTempJobEmps() {
		return tempJobEmps;
	}
	public void setTempJobEmps(List<TempJobEmp> tempJobEmps) {
		this.tempJobEmps = tempJobEmps;
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
	


	public String getJobActive() {
		return jobActive;
	}

	public void setJobActive(String jobActive) {
		this.jobActive = jobActive;
	}

	@Override
	public String toString() {
		return "TempJob [id=" + id + ", jobName=" + jobName + ", jobCode=" + jobCode + ", officeId=" + officeId
				+ ", officeName=" + officeName + ", jobActive=" + jobActive + ", tempJobEmps=" + tempJobEmps
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}

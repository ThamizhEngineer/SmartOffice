package com.ss.smartoffice.shared.model.employee;
import java.time.LocalDate;
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
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="m_emp_academic_acheiv")

@Scope("prototype")
public class AcademicAcheiv  extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_employee_id")
	private int employeeId;
	private String organization;
	private String trainDetails;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public AcademicAcheiv() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AcademicAcheiv(Integer id, int employeeId, String organization, String trainDetails, LocalDate fromDt,
			LocalDate toDt, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.organization = organization;
		this.trainDetails = trainDetails;
		this.fromDt = fromDt;
		this.toDt = toDt;
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getTrainDetails() {
		return trainDetails;
	}
	public void setTrainDetails(String trainDetails) {
		this.trainDetails = trainDetails;
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
		return "AcademicAcheiv [id=" + id + ", employeeId=" + employeeId + ", organization=" + organization
				+ ", trainDetails=" + trainDetails + ", fromDt=" + fromDt + ", toDt=" + toDt + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}

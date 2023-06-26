package com.ss.smartoffice.shared.model;

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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity

@Table(name="t_org_obj_header")
public class OrgHeader {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String fyYearId;
	@Formula("(SELECT fiscal.fiscal_code from s_fiscal_year fiscal where fiscal.id=fy_year_id)")
	private String fyYearCode;
	private String empId;
	@Formula("(select emp.first_name from m_employee emp where emp.id=emp_id)")
	private String empName;
	
	
	private String status;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="t_org_obj_header_id")
	private List<OrgLine> orgLines;

	public OrgHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrgHeader(Integer id, String fyYearId, String fyYearCode, String empId, String empName, String status,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			List<OrgLine> orgLines) {
		super();
		this.id = id;
		this.fyYearId = fyYearId;
		this.fyYearCode = fyYearCode;
		this.empId = empId;
		this.empName = empName;
		this.status = status;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.orgLines = orgLines;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFyYearId() {
		return fyYearId;
	}

	public void setFyYearId(String fyYearId) {
		this.fyYearId = fyYearId;
	}

	public String getFyYearCode() {
		return fyYearCode;
	}

	public void setFyYearCode(String fyYearCode) {
		this.fyYearCode = fyYearCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<OrgLine> getOrgLines() {
		return orgLines;
	}

	public void setOrgLines(List<OrgLine> orgLines) {
		this.orgLines = orgLines;
	}

	@Override
	public String toString() {
		return "OrgHeader [id=" + id + ", fyYearId=" + fyYearId + ", fyYearCode=" + fyYearCode + ", empId=" + empId
				+ ", empName=" + empName + ", status=" + status + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", orgLines=" + orgLines + "]";
	}

	
	
}

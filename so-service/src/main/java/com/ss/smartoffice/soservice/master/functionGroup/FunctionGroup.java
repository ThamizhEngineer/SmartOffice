package com.ss.smartoffice.soservice.master.functionGroup;

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
@Table(name="m_fun_grp")

@Scope("prototype")
public class FunctionGroup {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String funGrpName;
	private String funGrpCode;
	private String remarks;
	private String deliveryType;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="fun_grp_id")
	private List<Function> functions;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public FunctionGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FunctionGroup(Integer id, String funGrpName, String funGrpCode, String remarks, String deliveryType,
			List<Function> functions, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.funGrpName = funGrpName;
		this.funGrpCode = funGrpCode;
		this.remarks = remarks;
		this.deliveryType = deliveryType;
		this.functions = functions;
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
	public String getFunGrpName() {
		return funGrpName;
	}
	public void setFunGrpName(String funGrpName) {
		this.funGrpName = funGrpName;
	}
	public String getFunGrpCode() {
		return funGrpCode;
	}
	public void setFunGrpCode(String funGrpCode) {
		this.funGrpCode = funGrpCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
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
		return "FunctionGroup [id=" + id + ", funGrpName=" + funGrpName + ", funGrpCode=" + funGrpCode + ", remarks="
				+ remarks + ", deliveryType=" + deliveryType + ", functions=" + functions + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	

}

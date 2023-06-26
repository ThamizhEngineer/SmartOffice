package com.ss.smartoffice.soservice.master.functionGroup;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_fun")

@Scope("prototype")
public class Function {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "fun_grp_id")
	private String funGrpId;
	@Formula("(select mfg.delivery_type from m_fun_grp mfg where mfg.id=fun_grp_id)")
	private String deliveryType;
	@Formula("(select mfg.fun_grp_name from m_fun_grp mfg where mfg.id=fun_grp_id)")
	private String funGrpName;
	@Formula("(select mfg.remarks from m_fun_grp mfg where mfg.id=fun_grp_id)")
	private String funGrpDesc;
	private String funCode;
	private String funName;
	private String remarks;
	@Transient
	private List<DivisionLine> divisionLines;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Function() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Function(Integer id, String funGrpId, String deliveryType, String funGrpName, String funGrpDesc,
			String funCode, String funName, String remarks, List<DivisionLine> divisionLines, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.funGrpId = funGrpId;
		this.deliveryType = deliveryType;
		this.funGrpName = funGrpName;
		this.funGrpDesc = funGrpDesc;
		this.funCode = funCode;
		this.funName = funName;
		this.remarks = remarks;
		this.divisionLines = divisionLines;
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
	public String getFunGrpId() {
		return funGrpId;
	}
	public void setFunGrpId(String funGrpId) {
		this.funGrpId = funGrpId;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getFunGrpName() {
		return funGrpName;
	}
	public void setFunGrpName(String funGrpName) {
		this.funGrpName = funGrpName;
	}
	public String getFunGrpDesc() {
		return funGrpDesc;
	}
	public void setFunGrpDesc(String funGrpDesc) {
		this.funGrpDesc = funGrpDesc;
	}
	public String getFunCode() {
		return funCode;
	}
	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<DivisionLine> getDivisionLines() {
		return divisionLines;
	}
	public void setDivisionLines(List<DivisionLine> divisionLines) {
		this.divisionLines = divisionLines;
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
		return "Function [id=" + id + ", funGrpId=" + funGrpId + ", deliveryType=" + deliveryType + ", funGrpName="
				+ funGrpName + ", funGrpDesc=" + funGrpDesc + ", funCode=" + funCode + ", funName=" + funName
				+ ", remarks=" + remarks + ", divisionLines=" + divisionLines + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
}

package com.ss.smartoffice.soservice.master.businessUnit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_division_good")

@Scope("prototype")
public class DivisionGood {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String mGoodsId;
	@Column(name="m_division_id")
	private String mDivisionId;
	@Formula("(select mf.material_code from m_material mf where mf.id = m_goods_id)")
	private String materialCode;
	@Formula("(select mf.material_name from m_material mf where mf.id = m_goods_id)")
	private String materialName;
	private String divisionName;
	@Formula("(select COUNT(*) from m_material goods where goods.id=m_goods_id)")
	private String goodsHandled;
	private String remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public DivisionGood() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getmGoodsId() {
		return mGoodsId;
	}
	public void setmGoodsId(String mGoodsId) {
		this.mGoodsId = mGoodsId;
	}
	public String getmDivisionId() {
		return mDivisionId;
	}
	public void setmDivisionId(String mDivisionId) {
		this.mDivisionId = mDivisionId;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getGoodsHandled() {
		return goodsHandled;
	}
	public void setGoodsHandled(String goodsHandled) {
		this.goodsHandled = goodsHandled;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "DivisionGood [id=" + id + ", mGoodsId=" + mGoodsId + ", mDivisionId=" + mDivisionId + ", materialCode="
				+ materialCode + ", materialName=" + materialName + ", divisionName=" + divisionName + ", goodsHandled="
				+ goodsHandled + ", remarks=" + remarks + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}

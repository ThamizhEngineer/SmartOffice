package com.ss.smartoffice.shared.model.currency;

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
@Table(name="s_exchange_rate")

@Scope("prototype")
public class ExchangeRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "from_curr_id")
	private String fromCurrId;
	@Column(name = "to_curr_id")
	private String toCurrId;
	private float exchangeValue = 1;
	private LocalDateTime lastModified;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;		
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ExchangeRate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExchangeRate(Integer id, String fromCurrId, String toCurrId, float exchangeValue, LocalDateTime lastModified,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.fromCurrId = fromCurrId;
		this.toCurrId = toCurrId;
		this.exchangeValue = exchangeValue;
		this.lastModified = lastModified;
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
	public String getFromCurrId() {
		return fromCurrId;
	}
	public void setFromCurrId(String fromCurrId) {
		this.fromCurrId = fromCurrId;
	}
	public String getToCurrId() {
		return toCurrId;
	}
	public void setToCurrId(String toCurrId) {
		this.toCurrId = toCurrId;
	}
	public float getExchangeValue() {
		return exchangeValue;
	}
	public void setExchangeValue(float exchangeValue) {
		this.exchangeValue = exchangeValue;
	}
	public LocalDateTime getLastModified() {
		return lastModified;
	}
	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
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
		return "ExchangeRate [id=" + id + ", fromCurrId=" + fromCurrId + ", toCurrId=" + toCurrId + ", exchangeValue="
				+ exchangeValue + ", lastModified=" + lastModified + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ "]";
	}
	
	

}

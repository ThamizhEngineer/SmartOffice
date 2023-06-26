package com.ss.smartoffice.shared.model.currency;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="s_currency")

@Scope("prototype")
public class Currency {
	
	@Id
	private String id;
	private String currCode;
	private String currName;
	private String currSymbol;
	private String notes;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "from_curr_id")
	private List<ExchangeRate> fromExchangeRates;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "to_curr_id")
	private List<ExchangeRate> toExchangeRates;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;		
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Currency(String id, String currCode, String currName, String currSymbol, String notes,
			List<ExchangeRate> fromExchangeRates, List<ExchangeRate> toExchangeRates, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.currCode = currCode;
		this.currName = currName;
		this.currSymbol = currSymbol;
		this.notes = notes;
		this.fromExchangeRates = fromExchangeRates;
		this.toExchangeRates = toExchangeRates;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getCurrName() {
		return currName;
	}
	public void setCurrName(String currName) {
		this.currName = currName;
	}
	public String getCurrSymbol() {
		return currSymbol;
	}
	public void setCurrSymbol(String currSymbol) {
		this.currSymbol = currSymbol;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public List<ExchangeRate> getFromExchangeRates() {
		return fromExchangeRates;
	}
	public void setFromExchangeRates(List<ExchangeRate> fromExchangeRates) {
		this.fromExchangeRates = fromExchangeRates;
	}
	public List<ExchangeRate> getToExchangeRates() {
		return toExchangeRates;
	}
	public void setToExchangeRates(List<ExchangeRate> toExchangeRates) {
		this.toExchangeRates = toExchangeRates;
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
		return "Currency [id=" + id + ", currCode=" + currCode + ", currName=" + currName + ", currSymbol=" + currSymbol
				+ ", notes=" + notes + ", fromExchangeRates=" + fromExchangeRates + ", toExchangeRates="
				+ toExchangeRates + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
}

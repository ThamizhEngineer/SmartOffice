package com.ss.smartoffice.shared.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Entity
@Table(name="s_sequence")
@Component
public class Sequence {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String sequenceCode;
	private String sequenceName;
	@Column(name="sequence_pattern")
	private String sequencePattern;
	private Integer startValue;
	private Integer currentValue;
	private String prefixValue;
	private Integer endValue;
	@Column(name="alpha1_value")
	private String alpha1Value;
	@Column(name="alpha2_value")
	private String alpha2Value;
	@Transient
	private String output;
	
	@Column(name="curr_yy")
	private String currYy;
	@Column(name="curr_mm")
	private String currMm;
	@Column(name="curr_a1")
	private String currA1;
	@Column(name="curr_a2")
	private String currA2;
	@Column(name="curr_seq")
	private String currSeq;
	@Column(name="start_a1")
	private String startA1;
	@Column(name="end_a1")
	private String endA1;
	@Column(name="start_a2")
	private String startA2;
	@Column(name="end_a2")
	private String endA2;
	@Column(name="start_seq")
	private String startSeq;
	@Column(name="end_seq")
	private String endSeq;
	
	public Sequence() {
		super();
	}

	public String getOutput() {
		return sequenceCode+currSeq;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	

	public String getSequenceCode() {
		return sequenceCode;
	}

	public void setSequenceCode(String sequenceCode) {
		this.sequenceCode = sequenceCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public Integer getStartValue() {
		return startValue;
	}

	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public String getPrefixValue() {
		return prefixValue;
	}

	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}

	
	public Integer getEndValue() {
		return endValue;
	}

	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}

	public String getAlpha1Value() {
		return alpha1Value;
	}

	public void setAlpha1Value(String alpha1Value) {
		this.alpha1Value = alpha1Value;
	}

	public String getAlpha2Value() {
		return alpha2Value;
	}

	public void setAlpha2Value(String alpha2Value) {
		this.alpha2Value = alpha2Value;
	}
	

	public String getCurrYy() {
		return currYy;
	}

	public void setCurrYy(String currYy) {
		this.currYy = currYy;
	}

	public String getCurrMm() {
		return currMm;
	}

	public void setCurrMm(String currMm) {
		this.currMm = currMm;
	}

	public String getCurrA1() {
		return currA1;
	}

	public void setCurrA1(String currA1) {
		this.currA1 = currA1;
	}

	public String getCurrA2() {
		return currA2;
	}

	public void setCurrA2(String currA2) {
		this.currA2 = currA2;
	}

	public String getCurrSeq() {
		return currSeq;
	}

	public void setCurrSeq(String currSeq) {
		this.currSeq = currSeq;
	}

	public String getStartA1() {
		return startA1;
	}

	public void setStartA1(String startA1) {
		this.startA1 = startA1;
	}

	public String getEndA1() {
		return endA1;
	}

	public void setEndA1(String endA1) {
		this.endA1 = endA1;
	}

	public String getStartA2() {
		return startA2;
	}

	public void setStartA2(String startA2) {
		this.startA2 = startA2;
	}

	public String getEndA2() {
		return endA2;
	}

	public void setEndA2(String endA2) {
		this.endA2 = endA2;
	}

	public String getStartSeq() {
		return startSeq;
	}

	public void setStartSeq(String startSeq) {
		this.startSeq = startSeq;
	}

	public String getEndSeq() {
		return endSeq;
	}

	public void setEndSeq(String endSeq) {
		this.endSeq = endSeq;
	}
	

	public String getSequencePattern() {
		return sequencePattern;
	}

	public void setSequencePattern(String sequencePattern) {
		this.sequencePattern = sequencePattern;
	}

	@Override
	public String toString() {
		return "Sequence [id=" + id + ", sequenceCode=" + sequenceCode + ", sequenceName=" + sequenceName
				+ ", sequencePattern=" + sequencePattern + ", startValue=" + startValue + ", currentValue="
				+ currentValue + ", prefixValue=" + prefixValue + ", endValue=" + endValue + ", alpha1Value="
				+ alpha1Value + ", alpha2Value=" + alpha2Value + ", output=" + output + ", currYy=" + currYy
				+ ", currMm=" + currMm + ", currA1=" + currA1 + ", currA2=" + currA2 + ", currSeq=" + currSeq
				+ ", startA1=" + startA1 + ", endA1=" + endA1 + ", startA2=" + startA2 + ", endA2=" + endA2
				+ ", startSeq=" + startSeq + ", endSeq=" + endSeq + "]";
	}


	
	

	
}

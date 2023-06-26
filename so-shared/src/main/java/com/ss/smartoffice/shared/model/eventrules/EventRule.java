package com.ss.smartoffice.shared.model.eventrules;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="s_event_rule")
@Component
public class EventRule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String process;
	private String activity;
	private String eventType;
	private String eventCategory;
	private String eventDesc;
	private String topic;
	private String action;
	private String createdBy;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate createdDt;
	public EventRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventRule(Integer id, String process, String activity, String eventType, String eventCategory,
			String eventDesc, String topic, String action, String createdBy, LocalDate createdDt) {
		super();
		this.id = id;
		this.process = process;
		this.activity = activity;
		this.eventType = eventType;
		this.eventCategory = eventCategory;
		this.eventDesc = eventDesc;
		this.topic = topic;
		this.action = action;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDate getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
	}
	@Override
	public String toString() {
		return "EventRule [id=" + id + ", process=" + process + ", activity=" + activity + ", eventType=" + eventType
				+ ", eventCategory=" + eventCategory + ", eventDesc=" + eventDesc + ", topic=" + topic + ", action="
				+ action + ", createdBy=" + createdBy + ", createdDt=" + createdDt + "]";
	}
	
	
}

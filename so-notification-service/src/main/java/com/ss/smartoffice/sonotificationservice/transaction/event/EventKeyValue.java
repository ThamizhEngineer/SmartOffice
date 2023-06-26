package com.ss.smartoffice.sonotificationservice.transaction.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="t_event_KV")
@Component
public class EventKeyValue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_event_id")
	private String eventId;
	private String keyPair;
	private String value;
	public EventKeyValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventKeyValue(Integer id, String eventId, String keyPair, String value) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.keyPair = keyPair;
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getKeyPair() {
		return keyPair;
	}
	public void setKeyPair(String keyPair) {
		this.keyPair = keyPair;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "EventKeyValue [id=" + id + ", eventId=" + eventId + ", keyPair=" + keyPair + ", value=" + value + "]";
	}
	
}

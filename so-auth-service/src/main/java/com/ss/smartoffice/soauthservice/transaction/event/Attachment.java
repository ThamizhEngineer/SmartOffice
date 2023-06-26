package com.ss.smartoffice.soauthservice.transaction.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="t_event_attmnt")
@Component
public class Attachment {
	
	@Id
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_event_id")
	private String tEventId;
	private String docId1;
	private String docId2;
	private String docId3;

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(Integer id, String tEventId, String docId1, String docId2, String docId3) {
		super();
		this.id = id;
		this.tEventId = tEventId;
		this.docId1 = docId1;
		this.docId2 = docId2;
		this.docId3 = docId3;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettEventId() {
		return tEventId;
	}

	public void settEventId(String tEventId) {
		this.tEventId = tEventId;
	}

	public String getDocId1() {
		return docId1;
	}

	public void setDocId1(String docId1) {
		this.docId1 = docId1;
	}

	public String getDocId2() {
		return docId2;
	}

	public void setDocId2(String docId2) {
		this.docId2 = docId2;
	}

	public String getDocId3() {
		return docId3;
	}

	public void setDocId3(String docId3) {
		this.docId3 = docId3;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", tEventId=" + tEventId + ", docId1=" + docId1 + ", docId2=" + docId2
				+ ", docId3=" + docId3 + "]";
	}
	
	
	
}

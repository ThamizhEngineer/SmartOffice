package com.ss.smartoffice.shared.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user_metadata")

public class Metadata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "auth_user_id")
	private String authUserId;
	private String osVersion;

	private String imei;
	private String make;
	private String modelNo;
	private String firebaseId;
	private String macAddr;

	public Metadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Metadata(Integer id, String authUserId, String osVersion, String imei, String make, String modelNo,
			String firebaseId, String macAddr) {
		super();
		this.id = id;
		this.authUserId = authUserId;
		this.osVersion = osVersion;
		this.imei = imei;
		this.make = make;
		this.modelNo = modelNo;
		this.firebaseId = firebaseId;
		this.macAddr = macAddr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(String authUserId) {
		this.authUserId = authUserId;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getFirebaseId() {
		return firebaseId;
	}

	public void setFirebaseId(String firebaseId) {
		this.firebaseId = firebaseId;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	@Override
	public String toString() {
		return "Metadata [id=" + id + ", authUserId=" + authUserId + ", osVersion=" + osVersion + ", imei=" + imei
				+ ", make=" + make + ", modelNo=" + modelNo + ", firebaseId=" + firebaseId + ", macAddr=" + macAddr
				+ "]";
	}

}

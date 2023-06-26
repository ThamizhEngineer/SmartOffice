package com.ss.smartoffice.healthcheck.model;



public class HealthCheck {
	
	private String type;
	private String code;
	private String description;
	private String status;
	private String message;
	private String lastCheckedDate;
	
	public HealthCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HealthCheck(String type, String code, String description, String status, String message,
			String lastCheckedDate) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
		this.status = status;
		this.message = message;
		this.lastCheckedDate = lastCheckedDate;
	}

	@Override
	public String toString() {
		return "HealthCheck [type=" + type + ", code=" + code + ", description=" + description + ", status=" + status
				+ ", message=" + message + ", lastCheckedDate=" + lastCheckedDate + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLastCheckedDate() {
		return lastCheckedDate;
	}

	public void setLastCheckedDate(String lastCheckedDate) {
		this.lastCheckedDate = lastCheckedDate;
	}

	

	

}

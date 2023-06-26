package com.ss.smartoffice.shared.model;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class SmartOfficeException extends RuntimeException {

	private HttpStatus status;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
   private LocalDateTime timestamp;
   private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3638644700151949362L;

	public SmartOfficeException(String message) {
		super(message);
		this.message = message;
		this.timestamp = LocalDateTime.now();
		this.status = HttpStatus.EXPECTATION_FAILED;
	}


	public SmartOfficeException(Exception e) {
		super(e.getMessage());
		this.message = e.getMessage();
		this.timestamp = LocalDateTime.now();
		this.status = HttpStatus.EXPECTATION_FAILED;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

package com.ss.smartoffice.shared.model;

public class SimpleResponse {
	String result;
	String message;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public SimpleResponse(String result) {
		super();
		this.result = result;
		this.message = "Your Request processed successfully";
	}
	
	public SimpleResponse(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	

}

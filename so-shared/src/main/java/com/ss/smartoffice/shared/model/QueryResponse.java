package com.ss.smartoffice.shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryResponse {
	public List<Map<String, Object>> response= new ArrayList<Map<String,Object>>();
	public String responseStatus;
	public String errorCode;
	public String errorDescription;
	public int pageNo;
	public int pageSize;
	public long totalPageSize;
	public String contextUrl;
	
	public QueryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryResponse(List<Map<String, Object>> response, String responseStatus, String errorCode,
			String errorDescription, int pageNo, int pageSize, long totalPageSize, String contextUrl) {
		super();
		this.response = response;
		this.responseStatus = responseStatus;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalPageSize = totalPageSize;
		this.contextUrl = contextUrl;
	}

	@Override
	public String toString() {
		return "QueryResponse [response=" + response + ", responseStatus=" + responseStatus + ", errorCode=" + errorCode
				+ ", errorDescription=" + errorDescription + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalPageSize=" + totalPageSize + ", contextUrl=" + contextUrl + "]";
	}

	public List<Map<String, Object>> getResponse() {
		return response;
	}

	public void setResponse(List<Map<String, Object>> response) {
		this.response = response;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(long totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public String getContextUrl() {
		return contextUrl;
	}

	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}

		
	
}

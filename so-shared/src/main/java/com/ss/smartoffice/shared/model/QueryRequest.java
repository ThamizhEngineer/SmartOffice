package com.ss.smartoffice.shared.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryRequest {
	public String purpose;
	public String entity;
	public String query;
	public int pageNo;
	public int pageSize;
	public HashMap<String, String> filterCriteria;
	
	public QueryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "QueryRequest [purpose=" + purpose + ", entity=" + entity + ", query=" + query + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", filterCriteria=" + filterCriteria + "]";
	}

	public QueryRequest(String purpose, String entity, String query, int pageNo, int pageSize,
			HashMap<String, String> filterCriteria) {
		super();
		this.purpose = purpose;
		this.entity = entity;
		this.query = query;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.filterCriteria = filterCriteria;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	public HashMap<String, String> getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(HashMap<String, String> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	
	
	
}

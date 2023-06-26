package com.ss.smartoffice.soservice.transaction.minioAPIservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Album")
public class Album {
	 private String url;
	 private String description;
	 private String location;
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Album(String url, String description, String location) {
		super();
		this.url = url;
		this.description = description;
		this.location = location;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Album [url=" + url + ", description=" + description + ", location=" + location + "]";
	}
	
	 
	 

}

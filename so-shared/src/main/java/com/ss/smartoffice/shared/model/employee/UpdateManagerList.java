package com.ss.smartoffice.shared.model.employee;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class UpdateManagerList {
private String existingId;
private String m1Id;
private String m2Id;
private List<String>m1List;
private List<String>m2List;


// hr manager swap
private String hr1Id;
private String hr2Id;
private List<String> hr1List;
private List<String> hr2List;

//accounts manager swap

private String isAcc1Id;
private String isAcc2Id;
private List<String>acc1List;
private List<String>acc2List;

public UpdateManagerList() {
	super();
	// TODO Auto-generated constructor stub
}

public UpdateManagerList(String existingId, String m1Id, String m2Id, List<String> m1List, List<String> m2List,
		String hr1Id, String hr2Id, List<String> hr1List, List<String> hr2List, String isAcc1Id, String isAcc2Id,
		List<String> acc1List, List<String> acc2List) {
	super();
	this.existingId = existingId;
	this.m1Id = m1Id;
	this.m2Id = m2Id;
	this.m1List = m1List;
	this.m2List = m2List;
	this.hr1Id = hr1Id;
	this.hr2Id = hr2Id;
	this.hr1List = hr1List;
	this.hr2List = hr2List;
	this.isAcc1Id = isAcc1Id;
	this.isAcc2Id = isAcc2Id;
	this.acc1List = acc1List;
	this.acc2List = acc2List;
}

public String getExistingId() {
	return existingId;
}

public void setExistingId(String existingId) {
	this.existingId = existingId;
}

public String getM1Id() {
	return m1Id;
}

public void setM1Id(String m1Id) {
	this.m1Id = m1Id;
}

public String getM2Id() {
	return m2Id;
}

public void setM2Id(String m2Id) {
	this.m2Id = m2Id;
}

public List<String> getM1List() {
	return m1List;
}

public void setM1List(List<String> m1List) {
	this.m1List = m1List;
}

public List<String> getM2List() {
	return m2List;
}

public void setM2List(List<String> m2List) {
	this.m2List = m2List;
}

public String getHr1Id() {
	return hr1Id;
}

public void setHr1Id(String hr1Id) {
	this.hr1Id = hr1Id;
}

public String getHr2Id() {
	return hr2Id;
}

public void setHr2Id(String hr2Id) {
	this.hr2Id = hr2Id;
}

public List<String> getHr1List() {
	return hr1List;
}

public void setHr1List(List<String> hr1List) {
	this.hr1List = hr1List;
}

public List<String> getHr2List() {
	return hr2List;
}

public void setHr2List(List<String> hr2List) {
	this.hr2List = hr2List;
}

public String getIsAcc1Id() {
	return isAcc1Id;
}

public void setIsAcc1Id(String isAcc1Id) {
	this.isAcc1Id = isAcc1Id;
}

public String getIsAcc2Id() {
	return isAcc2Id;
}

public void setIsAcc2Id(String isAcc2Id) {
	this.isAcc2Id = isAcc2Id;
}

public List<String> getAcc1List() {
	return acc1List;
}

public void setAcc1List(List<String> acc1List) {
	this.acc1List = acc1List;
}

public List<String> getAcc2List() {
	return acc2List;
}

public void setAcc2List(List<String> acc2List) {
	this.acc2List = acc2List;
}

@Override
public String toString() {
	return "UpdateManagerList [existingId=" + existingId + ", m1Id=" + m1Id + ", m2Id=" + m2Id + ", m1List=" + m1List
			+ ", m2List=" + m2List + ", hr1Id=" + hr1Id + ", hr2Id=" + hr2Id + ", hr1List=" + hr1List + ", hr2List="
			+ hr2List + ", isAcc1Id=" + isAcc1Id + ", isAcc2Id=" + isAcc2Id + ", acc1List=" + acc1List + ", acc2List="
			+ acc2List + "]";
}

}

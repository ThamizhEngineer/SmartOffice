package com.ss.smartoffice.soservice.transaction.skillmatrix;

public class RowSpanObject {
	
	private String deptId;
	private long rowSpan;
	
	public RowSpanObject(String deptId, long rowSpan) {
		super();
		this.deptId = deptId;
		this.rowSpan = rowSpan;
	}

	@Override
	public String toString() {
		return "RowSpanObject [deptId=" + deptId + ", rowSpan=" + rowSpan + "]";
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public long getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(long rowSpan) {
		this.rowSpan = rowSpan;
	}
	
	
	
}

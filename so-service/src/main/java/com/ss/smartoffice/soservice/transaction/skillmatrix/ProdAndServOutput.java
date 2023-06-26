package com.ss.smartoffice.soservice.transaction.skillmatrix;

public class ProdAndServOutput {

	private int mProductId;
	private int mProfileId;
	private long availabeCount;
	
	public ProdAndServOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdAndServOutput(int mProductId, int mProfileId, long availabeCount) {
		super();
		this.mProductId = mProductId;
		this.mProfileId = mProfileId;
		this.availabeCount = availabeCount;
	}

	@Override
	public String toString() {
		return "ProdAndServOutput [mProductId=" + mProductId + ", mProfileId=" + mProfileId + ", availabeCount="
				+ availabeCount + "]";
	}

	public int getmProductId() {
		return mProductId;
	}

	public void setmProductId(int mProductId) {
		this.mProductId = mProductId;
	}

	public int getmProfileId() {
		return mProfileId;
	}

	public void setmProfileId(int mProfileId) {
		this.mProfileId = mProfileId;
	}

	public long getAvailabeCount() {
		return availabeCount;
	}

	public void setAvailabeCount(long availabeCount) {
		this.availabeCount = availabeCount;
	}
	
	
}

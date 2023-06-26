package com.ss.smartoffice.soservice.transaction.skillmatrix;

public class GapOutput {

	private String productId;
	private String serviceId;
	private long gapCount;

	public GapOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GapOutput(String productId, String serviceId, long gapCount) {
		super();
		this.productId = productId;
		this.serviceId = serviceId;
		this.gapCount = gapCount;
	}

	@Override
	public String toString() {
		return "GapOutput [productId=" + productId + ", serviceId=" + serviceId + ", gapCount=" + gapCount + "]";
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public long getGapCount() {
		return gapCount;
	}

	public void setGapCount(long gapCount) {
		this.gapCount = gapCount;
	}

}

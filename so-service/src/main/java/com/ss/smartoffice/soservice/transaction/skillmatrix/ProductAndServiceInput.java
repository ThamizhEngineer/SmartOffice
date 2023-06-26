package com.ss.smartoffice.soservice.transaction.skillmatrix;

public class ProductAndServiceInput {

	private String productId;
	private String serviceId;
	private String expectedCount;

	public ProductAndServiceInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAndServiceInput(String productId, String serviceId, String expectedCount) {
		super();
		this.productId = productId;
		this.serviceId = serviceId;
		this.expectedCount = expectedCount;
	}

	@Override
	public String toString() {
		return "ProductAndServiceInput [productId=" + productId + ", serviceId=" + serviceId + ", expectedCount="
				+ expectedCount + "]";
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

	public String getExpectedCount() {
		return expectedCount;
	}

	public void setExpectedCount(String expectedCount) {
		this.expectedCount = expectedCount;
	}

}

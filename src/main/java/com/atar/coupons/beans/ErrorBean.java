package com.atar.coupons.beans;

public class ErrorBean {

	//-----------------------properties---------------------------
	
	private int errorNumber;
	private String errorMessage; 
	private String errorName;

	
	//----------------------constructors--------------------------
	
	public ErrorBean(int errorNumber, String errorMessage, String errorName) {
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
		this.errorName = errorName;
	}

	
	//----------------------getters & setters --------------------

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
		
		
	
	
	
	
}

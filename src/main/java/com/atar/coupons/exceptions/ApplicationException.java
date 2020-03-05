package com.atar.coupons.exceptions;

import com.atar.coupons.enums.ErrorTypes;

public class ApplicationException extends Exception {

	//----------------------properties-----------------------------

//	private static final long serialVersionUID = IL;
	
	private ErrorTypes errorType;
	

	//----------------------constructors-----------------------------
	
	public ApplicationException(Exception e, ErrorTypes errorType, String message) {
		super (message, e);
		this.errorType = errorType;
	}
	
	
	public ApplicationException(ErrorTypes errorType, String message) {
		super (message);
		this.errorType = errorType;
	}
	
	
	//-------------------------Methods-----------------------------
	
	
	
	public ErrorTypes getErrorType() {
		return errorType;
	}
	
}

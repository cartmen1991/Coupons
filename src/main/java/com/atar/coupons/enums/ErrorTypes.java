package com.atar.coupons.enums;

public enum ErrorTypes {
	
	GENERAL_ERROR(600, "GENERAL ERROR", "General error occured", true), 
	
	LOGIN_INVALID(601, "LOGIN INVALID", "Login details are invalid", true),
	FAIL_TO_GENERATE_ID(602, "FAIL TO GENERATE ID", "Id wasn't generated", true), 
	
	ADDRESS_IS_INVALID(603, "ADDRESS IS INVALID", "The address is invalid", false),
	AMOUNT_OF_ITEMS_IS_INVALID(604, "AMOUNT OF ITEMS IS INVALID", "The amount of items is invalid", false), 
	AMOUNT_OF_KIDS_IS_INVALID(605, "AMOUNT OF KIDS IS INVALID", "The amount of kids is invalid", false),
	COMPANY_ID_IS_INVALID(606, "COMPANY ID IS INVALID", "The id of the company is invalid", false), 
	CONPANY_IS_INVALID(607, "CONPANY IS INVALID", "The company is invalid", false),
	COUPON_ID_IS_INVALID(608, "COUPON ID IS INVALID", "The id of the coupon is invalid", false), 
	CUSTOMER_ID_IS_INVALID(609, "CUSTOMER ID IS INVALID", "The id of the customer is invalid", false), 
	DETAILS_ARE_INVALID(610, "DETAILS ARE_INVALID", "The details inserted are invalid", false), 
	DATE_IS_INVALID(611, "DATE IS INVALID", "The date is invalid", false),
	DATES_ARE_INVALID(612, "DATES ARE INVALID", "The dates are invalid", false), 
	EMAIL_IS_INVALID(613, "EMAIL IS INVALID", "The E-Mail is invalid", false),
	PHONE_IS_INVALID(614, "PHONE IS INVALID", "The phone number is invalid", false), 
	PASSWORD_IS_INVALID(615, "PASSWORD IS INVALID", "The password is invalid", false), 
	PRICE_IS_INVALID(616, "PRICE IS INVALID", "The price is invalid", false), 
	TITLE_IS_INVALID(617, "TITLE IS INVALID", "The title is invalid", false), 
	TOTAL_PRICE_IS_INVALID(618, "TOTAL PRICE IS_INVALID", "The total proce is invalid", false),
	TYPE_IS_INVALID(619, "TYPE IS INVALID", "The type is invalid", false), 
	UNITS_ARE_INVALID(620, "UNITS ARE INVALID", "The units are invalid", false), 
	USERNAME_IS_INVALID(621, "USERNAME IS INVALID", "The user name is invalid", false), 
	USER_IS_INVALID(622, "USER IS INVALID", "The user is invalid", false), 
	PURCHASE_IS_INVALID(623, "PURCHASE IS INVALID", "The purchase is invalid", false),
	
	
	ALREADY_DELETED(640, "ALREADY DELETED", "What you searched is not exist so probably it has been deleted", false),
	ALREADY_UPDATED(641, "ALREADY UPDATED", "What you wish to update is already updated", false),
	ALREADY_CREATED(642, "ALREADY CREATED", "What you wish to create is already exist", false),
	
	THERE_ARE_NO_COMPANIES(650, "THERE ARE NO COMPANIES", "There are no companies at all", false), 
	THERE_ARE_NO_COUPONS(651, "THERE ARE NO COUPONS", "There are no coupons at all", false), 
	THERE_ARE_NO_CUSTOMERS(652, "THERE ARE NO CUSTOMERS", "There are no customers at all", false), 
	THERE_ARE_NO_PURCHASES(653, "THERE ARE NO PURCHASES", "There are no purchases at all", false), 
	THERE_ARE_NO_USERS(654, "THERE ARE NO USERS", "There are no users at all", false), 
	 
	COMPANY_NAME_IS_UNAVAILABLE(660, "COMPANY NAME IS UNAVAILABLE", "This detail is unavailable for this type of user", true), 
	COMPANY_IS_NOT_EXIST(661, "COMPANY IS NOT EXIST", "The company is not exist, check again the details inserted", true), 
	USER_IS_NOT_EXIST(662, "USER IS NOT EXIST", "The user is not exist", true), 
	INVALID_CREDENTIALS(663, "INVALID CREDENTIALS", "The detalis are unavailable for this type of user", true), 
	WRONG_CUSTOMER_ID_INSERTED(664, "WRONG CUSTOMER ID INSERTED", "The id of customer is invalid", true), 

	FAILED_TO_CREATE_CUSTOMER(670, "FAILED TO CREATE CUSTOMER", "Creation of customer failed", true), 
	FAILED_TO_DELETE_PURCHASE(671, "FAILED TO DELETE PURCHASE", "Failed to delete purchase", true), 
	FAILED_TO_DELETE_USERS(672, "FAILED TO DELETE USERS", "Failed to delete users", true), 
	FAILED_TO_GET_COUPONS(673, "FAILED TO GET COUPONS", "Failed to get coupons", true),
	FAILED_TO_CREATE_COMPANY(674, "FAILED TO CREATE COMPANY", "Failed to create company", true), 
	FAILED_TO_UPDATE_CUSTOMER(675, "FAILED TO UPDATE CUSTOMER", "Failed to update customer", true), 
	FAILED_TO_GET_CUSTOMER, 
	FAILED_TO_CREATE_USER, 
	FAILED_TO_UPDATE_USER, 
	FAILED_TO_GET_USERS, 
	FAILED_TO_GET_USER, 
	FAILED_TO_DELETE_COMPANY, 
	FAILED_TO_UPDATE_COMPANY, 
	FAILED_TO_GET_COMPANIES, 
	FAILED_TO_GET_COMPANY, 
	FAILED_TO_CREATE_PURCHASE, 
	FAILED_TO_UPDATE_PURCHASE, 
	NOT_ENOUGHTH_ITEMS_IN_STOCK, 
	FAILED_TO_GET_PURCHASES, 
	FAILED_TO_GET_PURCHASE, 
	FAILED_TO_CREATE_COUPON, 
	FAILED_TO_UPDATE_COUPON, 
	FAILED_TO_GET_COUPON, 
	;

	// --------------------------------------- Properties ----------------------------------------------------
	
	private int errorNumber;
	private String errorName;
	private String errorMessage;
	private boolean isShowStackTrace;
	
	
	// ----------------------------------- Constructors ------------------------------------------------------
	
	private ErrorTypes(int errorNumber, String errorName, String internalMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		this.errorName = errorName;
		this.errorMessage = internalMessage;
		this.isShowStackTrace = isShowStackTrace;
	}
	
	private ErrorTypes(int errorNumber, String internalMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
	}

	private ErrorTypes() {
	}
	
	
	//----------------------------------- Getters ------------------------------------------------------------
	
	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public void setShowStackTrace(boolean isShowStackTrace) {
		this.isShowStackTrace = isShowStackTrace;
	}
		
	
	
}

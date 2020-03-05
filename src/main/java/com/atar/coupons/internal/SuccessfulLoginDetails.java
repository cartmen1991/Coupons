package com.atar.coupons.internal;

import com.atar.coupons.enums.UserTypes;

public class SuccessfulLoginDetails {

	//----------------------properties-----------------------------
	private int token;
	private UserTypes userType;
	
	
	//----------------------constructors-----------------------------
	
	public SuccessfulLoginDetails(int token, UserTypes userType) {
		this.token = token;
		this.userType = userType;
	}
	
	public SuccessfulLoginDetails() {
	}



	//----------------------Getters&Setters-----------------------------
	public int getToken() {
		return token;
	}


	public void setToken(int token) {
		this.token = token;
	}


	public UserTypes getUserType() {
		return userType;
	}


	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	
	//----------------------to String-----------------------------

	@Override
	public String toString() {
		return "SuccessfulLoginDetails [token=" + token + ", userType=" + userType + "]";
	}
	
		
	
	
	
	
}

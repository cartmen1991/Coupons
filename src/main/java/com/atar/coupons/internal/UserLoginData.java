package com.atar.coupons.internal;

import com.atar.coupons.enums.UserTypes;

public class UserLoginData {

	private long id;
	private UserTypes userType;
	private long companyId;
	
	
	public UserLoginData(long id, UserTypes userType, long companyId) {
		this(id, userType);
		this.id = id;
		this.userType = userType;
		this.companyId = companyId;
	}
	
	public UserLoginData(long id, UserTypes userType) {
		this.id = id;
		this.userType = userType;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public UserTypes getUserType() {
		return userType;
	}


	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	
	
	
	
}

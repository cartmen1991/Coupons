package com.atar.coupons.beans;

public class UserSignUpDetails {

	public String userName;
	public String password;
	public String repeatPassword;

	public UserSignUpDetails(String userName, String password, String repeatPassword) {
		this.userName = userName;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}

	public UserSignUpDetails() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Override
	public String toString() {
		return "UserSignUpDetails [userName=" + userName + ", password=" + password + ", repeatPassword="
				+ repeatPassword + "]";
	}




}

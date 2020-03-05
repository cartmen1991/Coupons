package com.atar.coupons.beans;

import com.atar.coupons.enums.MartialStatus;

public class SignUpDetails {

	public String email;

	public String phone;
	public String address;
	public int amountOfKids;
	public MartialStatus status;
	public UserSignUpDetails userSignUpDetails;
	
	
	public SignUpDetails(String email, String phone, String address, int amountOfKids, MartialStatus status, UserSignUpDetails userSignUpDetails) {
		
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.amountOfKids = amountOfKids;
		this.status = status;
		this.userSignUpDetails = userSignUpDetails;
	}
	
	
	public SignUpDetails() {
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getAmountOfKids() {
		return amountOfKids;
	}


	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}


	public UserSignUpDetails getUserSignUpDetails() {
		return userSignUpDetails;
	}


	public void setUserSignUpDetails(UserSignUpDetails userSignUpDetails) {
		this.userSignUpDetails = userSignUpDetails;
	}

	

	public MartialStatus getStatus() {
		return status;
	}


	public void setStatus(MartialStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "SignUpDetails [email=" + email + ", phone=" + phone + ", address=" + address + ", amountOfKids="
				+ amountOfKids + ", status=" + status + ", userSignUpDetails=" + userSignUpDetails + "]";
	}



	
	
	
	
	
 

}

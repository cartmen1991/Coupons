package com.atar.coupons.beans;

import com.atar.coupons.enums.MartialStatus;

public class CustomerDetails {


	public Long id;
	public String userName;
	public String address;
	public String email;
	public String phone;
	public MartialStatus status;
	public int amountOfKids;


	public CustomerDetails(Long id, String userName, String address, String email, String phone, MartialStatus status, int amountOfKids) {
		
		this.id = id;
		this.userName = userName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.amountOfKids = amountOfKids;
	}


	public CustomerDetails() {
	
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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




	public MartialStatus getStatus() {
		return status;
	}


	public void setStatus(MartialStatus status) {
		this.status = status;
	}


	public int getAmountOfKids() {
		return amountOfKids;
	}


	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
	}


	@Override
	public String toString() {
		return "CustomerDetails [id=" + id + ", userName=" + userName + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", status=" + status + ", amountOfKids=" + amountOfKids + "]";
	}




}

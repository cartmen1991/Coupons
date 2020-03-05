package com.atar.coupons.beans;

import com.atar.coupons.enums.CompanyType;

public class CompanyDetails {
	
	private long id;
	private String companyName;
	private CompanyType type;
	private String email;
	private String phone;
	private String address;
	
	
	public CompanyDetails(long id, String companyName, CompanyType type, String email, String phone, String address) {
		this(companyName, type, email, phone, address);
		this.id = id;
		}

		public CompanyDetails(String companyName, CompanyType type, String email, String phone, String address) {
		this.companyName = companyName;
		this.type = type;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public CompanyDetails() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyType getType() {
		return type;
	}

	public void setType(CompanyType type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "CompanyDetails [id=" + id + ", companyName=" + companyName + ", type=" + type + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + "]";
	}
	
	
	 
	
	
	
}

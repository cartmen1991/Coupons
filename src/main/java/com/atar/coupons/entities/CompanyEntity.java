package com.atar.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.atar.coupons.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Companies")
public class CompanyEntity {


	//----------------------properties-----------------------------

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "COMPANY_NAME", unique = true, nullable = false)
	private String companyName;

	@Column(name = "COMPANY_TYPE", nullable = false)
	private CompanyType type;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "PHONE", unique = true, nullable = false)
	private String phone;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="company")
	private List<UserEntity> users;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="company")
	List<CouponEntity> coupons;

	//----------------------constructors-----------------------------


	public CompanyEntity(long companyID, String companyName, CompanyType type, String address, String phone, String email) {
		this(companyName, type, address, phone, email);
		this.id = companyID;
	}

	public CompanyEntity(String companyName, CompanyType type, String address, String phone, String email) {
		super();
		this.companyName = companyName;
		this.type = type;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public CompanyEntity() {
	}



	//----------------------Getters&Setters-----------------------------



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompayName() {
		return companyName;
	}

	public void setCompayName(String compayName) {
		this.companyName = compayName;
	}

	public CompanyType getType() {
		return type;
	}

	public void setType(CompanyType type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<CouponEntity> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponEntity> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "CompanyEntity [id=" + id + ", companyName=" + companyName + ", type=" + type + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", users=" + users + ", coupons=" + coupons + "]";
	}


	

	


}

package com.atar.coupons.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.atar.coupons.enums.UserTypes;

@Entity
@Table(name="Users")
public class UserEntity implements Serializable {


	//----------------------properties-----------------------------
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	
	@Column(name = "TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserTypes type;

	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String userName;

	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	@ManyToOne
	private CompanyEntity company;
	
	

	//----------------------constructors-----------------------------

	public UserEntity(long userId, UserTypes type, String userName, String password, CompanyEntity company, CustomerEntity customer) {
		this(type, userName, password, company, customer);
		this.id = userId;
	}

	public UserEntity(UserTypes type, String userName, String password, CompanyEntity company, CustomerEntity  customer) {
		super();
		this.type = type;
		this.userName = userName;
		this.password = password;
		this.company = company;
//		this.customer = customer;
		
	}

	public UserEntity() {
	}

	//----------------------Getters&Setters-----------------------------

	public long getId() {
		return id;
	}

	public void setId(long userId) {
		this.id = userId;
	}

	public UserTypes getType() {
		return type;
	}

	public void setType(UserTypes type) {
		this.type = type;
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

	public CompanyEntity getCompany() {
		return company;
	}

	
	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

//	public CustomerEntity getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(CustomerEntity customer) {
//		this.customer = customer;
//	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", type=" + type + ", userName=" + userName + ", password=" + password
				+ ", company=" + company + "]";
	}


	
	
	


	


	
	
}

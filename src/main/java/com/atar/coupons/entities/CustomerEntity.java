package com.atar.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.atar.coupons.enums.MartialStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="Customers")
public class CustomerEntity {

	//----------------------properties-----------------------------
//
	@Id
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@MapsId
	private UserEntity user;

	@Column(name = "AMOUNT_OF_KIDS", nullable = true)
	private int amountOfKids;
	
	@Column(name = "MARTIAL_STATUS", nullable = true)
	private MartialStatus status;
	
	@Column(name = "ADDRESS", nullable = false)	
	private String address;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "PHONE", nullable = true)
	private String phone;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="customer")
	private List<PurchaseEntity> purchases;
	
	//----------------------constructors-----------------------------

	public CustomerEntity(UserEntity user, int amountOfKids, MartialStatus status, String address, String email, String phone) {
		this(amountOfKids, status, address, email, phone);
		this.user = user;
	}

	public CustomerEntity(int amountOfKids, MartialStatus status, String address, String email, String phone) {
		super();
		this.amountOfKids = amountOfKids;
		this.status = status;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public CustomerEntity() {
	}

	//----------------------Getters&Setters-----------------------------

	
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}


	public int getAmountOfKids() {
		return amountOfKids;
	}

	public void setAmountOfKids(int amountOfKids) {
		this.amountOfKids = amountOfKids;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	public MartialStatus getStatus() {
		return status;
	}

	public void setStatus(MartialStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", user=" + user + ", amountOfKids=" + amountOfKids + ", status=" + status
				+ ", address=" + address + ", email=" + email + ", phone=" + phone + ", purchases=" + purchases + "]";
	}

	

	
	

	

}

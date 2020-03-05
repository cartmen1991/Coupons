package com.atar.coupons.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="Purchases")
public class PurchaseEntity {

	//----------------------properties-----------------------------

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch=FetchType.EAGER)
	private CouponEntity coupon;
	
	@Column(name = "AMOUNT_OF_ITEMS", nullable = false)
	private int amountOfItems;

	@Column(name = "TOTAL_PRICE", nullable = false)
	private float totalPrice;
	
	@Column(name = "TIMESTAMP", nullable = false)
	private Timestamp timestamp;
	
	@Column(name = "CONSUMER_TYPE", nullable = false)
	private UserTypes type;
	
	@ManyToOne
	private CustomerEntity customer;
	
	
	
	//----------------------constructors-----------------------------

	public PurchaseEntity(long purchaseId, CouponEntity coupon, int amountOfItems, float totalPrice, Timestamp timestamp, UserTypes type) {
		this(coupon, amountOfItems, totalPrice, timestamp, type);
		this.id = purchaseId;
	}

	public PurchaseEntity(CouponEntity coupon, int amountOfItems, float totalPrice, Timestamp timestamp, UserTypes type) {
		super();
		this.coupon = coupon;
		this.amountOfItems = amountOfItems;
		this.totalPrice = totalPrice;
		this.timestamp = timestamp;
		this.type = type;
	}

	public PurchaseEntity() {
	}


	//----------------------Getters&Setters-----------------------------

	public long getId() {
		return id;
	}

	public void setId(long purchaseId) {
		this.id = purchaseId;
	}

	public CouponEntity getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponEntity coupon) {
		this.coupon = coupon;
	}

	public int getAmountOfItems() {
		return amountOfItems;
	}

	public void setAmountOfItems(int amountOfItems) {
		this.amountOfItems = amountOfItems;
	}

	@JsonSerialize(using=JsonTimestampSerializer.class)
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public UserTypes getType() {
		return type;
	}

	public void setType(UserTypes type) {
		this.type = type;
	}
	
	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "PurchaseEntity [id=" + id + ", coupon=" + coupon + ", amountOfItems=" + amountOfItems + ", totalPrice="
				+ totalPrice + ", timestamp=" + timestamp + ", type=" + type + ", customer=" + customer + ", getId()="
				+ getId() + ", getCoupon()=" + getCoupon() + ", getAmountOfItems()=" + getAmountOfItems()
				+ ", getTimestamp()=" + getTimestamp() + ", getTotalPrice()=" + getTotalPrice() + ", getType()="
				+ getType() + ", getCustomer()=" + getCustomer() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


	


}

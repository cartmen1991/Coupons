package com.atar.coupons.beans;

import java.sql.Timestamp;
import java.util.Date;

import com.atar.coupons.enums.CouponType;

public class Coupon {

	public Long id;
	public String title;
	public CouponType type;
	public float price;
	public int unitsInStock;
	public Timestamp startDate;
	public Timestamp expiryDate;

	
	public Coupon(Long id, String title, CouponType type, float price, int unitsInStock, Timestamp startDate,
			Timestamp expiryDate) {
		this(title, type, price, unitsInStock, startDate, expiryDate);
		this.id = id;
			}
	
	public Coupon(String title, CouponType type, float price, int unitsInStock, Timestamp startDate,
			Timestamp expiryDate) {
		this.title = title;
		this.type = type;
		this.price = price;
		this.unitsInStock = unitsInStock;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
	}
	
	public Coupon() {
		}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", type=" + type + ", price=" + price
				+ ", unitsInStock=" + unitsInStock + ", startDate=" + startDate + ", expiryDate=" + expiryDate + "]";
	}
	
	
	
	
	
	
	
	

}

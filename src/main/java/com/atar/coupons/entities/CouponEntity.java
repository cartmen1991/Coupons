package com.atar.coupons.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.atar.coupons.enums.CouponType;
import com.atar.coupons.utils.JsonTimestampSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="Coupons")
public class CouponEntity {


	//----------------------properties-----------------------------

	@Id
	@GeneratedValue
	private long id;
	
	
	@ManyToOne
	private CompanyEntity company;
	
	@Column(name = "TITLE", unique = true, nullable = false)
	private String title;
	
	@Column(name = "TYPE", nullable = false)
	private CouponType type;
	
	@Column(name = "PRICE", nullable = false)
	private Float price;
	
	@Column(name = "UNITS_IN_STOCK", nullable = false)
	private int unitsInStock;

	@Column(name = "START_DATE", nullable = false)
	private Timestamp startDate;
	
	@Column(name = "EXPIRY_DATE", nullable = true)
	private Timestamp expiryDate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="coupon")
	private List<PurchaseEntity> purchases;

	//----------------------constructors-----------------------------


	public CouponEntity(long couponId, String couponTitle, CouponType type, Float price, int unitsInStock, Timestamp startDate, Timestamp endDate) {
		this(couponTitle, type, price, unitsInStock, startDate, endDate);
		this.id = couponId;
	}

	public CouponEntity(String couponTitle, CouponType type, Float price, int unitsInStock, Timestamp startDate, Timestamp endDate) {
		super();
		this.title = couponTitle;
		this.type = type;
		this.price = price;
		this.unitsInStock = unitsInStock;
		this.startDate = startDate;
		this.expiryDate = endDate;
	}

	public CouponEntity(){
	}


	//----------------------Getters&Setters-----------------------------



	public long getId() {
		return id;
	}

	public void setId(long couponId) {
		this.id = couponId;
	}

	public CouponType getType() {
		return type;
	}
	
	public void setType(CouponType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String couponTitle) {
		this.title = couponTitle;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@JsonSerialize(using=JsonTimestampSerializer.class)
	public  Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using=JsonTimestampSerializer.class)
	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp endDate) {
		this.expiryDate = endDate;
	}
	
	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}
	
	

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "CouponEntity [id=" + id + ", company=" + company + ", title=" + title + ", type=" + type + ", price="
				+ price + ", unitsInStock=" + unitsInStock + ", startDate=" + startDate + ", expiryDate=" + expiryDate
				+ ", purchases=" + purchases + "]";
	}



	
	
	
}

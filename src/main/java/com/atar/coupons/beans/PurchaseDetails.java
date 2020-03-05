package com.atar.coupons.beans;

public class PurchaseDetails {

	public Long couponId;
	public int purchaseAmount;
	
	
	public PurchaseDetails(Long couponId, int purchaseAmount) {
		
		this.couponId = couponId;
		this.purchaseAmount = purchaseAmount;
	}
	
	public PurchaseDetails() {
		
	}

	
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public int getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	@Override
	public String toString() {
		return "PurchaseDetails [couponId=" + couponId + ", purchaseAmount=" + purchaseAmount + "]";
	}
	
	
	
	
	
}

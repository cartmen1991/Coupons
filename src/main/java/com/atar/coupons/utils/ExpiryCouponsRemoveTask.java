package com.atar.coupons.utils;

import java.util.Calendar;

import com.atar.coupons.logic.CouponsController;


public class ExpiryCouponsRemoveTask extends TimerTask{

private CouponsController couponsController;
	
	public ExpiryCouponsRemoveTask(CouponsController couponController) {
		
		this.couponsController = couponController;
	}

	@Override
	public void run() {
		try {
			couponsController.removeExpiredCoupon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Calendar getTimeToRemoveExpCoupon() {
		
		Calendar midnight = Calendar.getInstance();
		
		midnight.set(Calendar.HOUR_OF_DAY, 00);
		midnight.set(Calendar.MINUTE, 00);
		midnight.set(Calendar.SECOND, 00);
		
		return midnight;
	}
	
	public static long getDayInMillsec() {
		long dayInMilliseconds = 86400000;
		
		return dayInMilliseconds;
	}
	
	
	
}

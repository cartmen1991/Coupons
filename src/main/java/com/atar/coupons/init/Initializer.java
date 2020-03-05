package com.atar.coupons.init;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import com.atar.coupons.logic.CouponsController;
import com.atar.coupons.utils.ExpiryCouponsRemoveTask;

	@Component
	public class Initializer {

		@Autowired
		private CouponsController couponsController;
		
		@PostConstruct
		public void init() {
			
			
			TimerTask removeExpiryTask = new ExpiryCouponsRemoveTask(couponsController);
			Timer timer = new Timer();
			
			Calendar timeToRemoveExpiredCoupons = ExpiryCouponsRemoveTask.getTimeToRemoveExpCoupon();	
			long dayInMilliseconds = ExpiryCouponsRemoveTask.getDayInMillsec();
			
			timer.schedule(removeExpiryTask, timeToRemoveExpiredCoupons.getTime(), dayInMilliseconds);
			
		}
}

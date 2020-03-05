package com.atar.coupons.api;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atar.coupons.beans.Coupon;
import com.atar.coupons.entities.CouponEntity;
import com.atar.coupons.enums.CouponType;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.logic.CouponsController;


@RestController
@RequestMapping("/coupons")
public class CouponsApi {


	//		---  instance to logic level ---		
	@Autowired
	private CouponsController couponsController;




	//		---------------------------
	//		         C R U D
	//		----------------------------


	//  URL   http://localhost:8080/coupons
	@PostMapping
	public void createCoupon(@RequestBody Coupon couponDetails, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		this.couponsController.createCoupon(couponDetails, userLoginData);
		System.out.println("testing print - create");
	}


	//  URL   http://localhost:8080/coupons
	@PutMapping
	public void updateCoupon(@RequestBody CouponEntity coupon, HttpServletRequest request) throws ApplicationException {
		this.couponsController.updateCoupon(coupon);
		System.out.println("testing print - update");
	}


	//  URL   http://localhost:8080/coupons/10
	@GetMapping("{couponId}")
	public CouponEntity getCoupon(@PathVariable("couponId") long id) throws ApplicationException {
		System.out.println("testing print - get one");
		return this.couponsController.getCoupon(id);
	}


	//  URL   http://localhost:8080/coupons/10
	@DeleteMapping("{couponId}")
	public void deleteCoupon (@PathVariable("couponId") long id) throws ApplicationException {
		this.couponsController.deleteCoupon(id);
		System.out.println("testing print - delete");
	}


	//  URL   http://localhost:8080/coupons
	@GetMapping
	public List<CouponEntity> getAllCoupons() throws ApplicationException {
		System.out.println("testing print - get all");
		return this.couponsController.getAllCoupons();
	}


	//  URL   http://localhost:8080/coupons/byCompany?companyId=00&token=00
	@GetMapping("/byCompany")
	public List<CouponEntity> getCouponsByCompany(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return this.couponsController.getCouponsByCompany(userLoginData.getCompanyId());
	}
	

	//  URL   http://localhost:8080/coupons/byMaxPrice?maxPrice=00&token=00
	@GetMapping("/byMaxPrice")
	public List<CouponEntity> getCouponsByPrice(@RequestParam("maxPrice") float maxPrice) throws ApplicationException {
		return this.couponsController.getCouponsByPrice(maxPrice);
	}
	
	//  URL   http://localhost:8080/coupons/byTitle?title="00"&token=00
	@GetMapping("/byTitle")
	public List<CouponEntity> getCouponsByTitle(@RequestParam("title") String title) throws ApplicationException {
		return this.couponsController.getCouponsByTitle(title);
	}
	
	//  URL   http://localhost:8080/coupons/byType?type="00"&token=00
	@GetMapping("/byType")
	public List<CouponEntity> getCouponsByType(@RequestParam("type") CouponType type) throws ApplicationException {
		return this.couponsController.getCouponsByType(type);
	}
	
	

	

}

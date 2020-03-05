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

import com.atar.coupons.beans.PurchaseDetails;
import com.atar.coupons.entities.PurchaseEntity;
import com.atar.coupons.enums.CouponType;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.logic.CouponsController;
import com.atar.coupons.logic.PurchasesController;


@RestController
@RequestMapping("/purchases")
public class PurchasesApi {




	//		---  instance to logic level ---		
	@Autowired
	private PurchasesController purchasesController;

	@Autowired
	private CouponsController couponsController;
	
	
	//		---------------------------
	//		         C R U D
	//		----------------------------


	//  URL   http://localhost:8080/purchases
	@PostMapping
	public void createPurchase(@RequestBody PurchaseDetails purchaseDetails, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		this.purchasesController.createPurchase(purchaseDetails, userLoginData);

		System.out.println("testing print - create");
	}


	//  URL   http://localhost:8080/purchases
	@PutMapping
	public void updatePurchase(@RequestBody PurchaseEntity purchase, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		this.purchasesController.updatePurchase(purchase);
		System.out.println("testing print - update");
	}


	//  URL   http://localhost:8080/purchases/10
	@GetMapping("{purchaseId}")
	public PurchaseEntity getPurchase(@PathVariable("purchaseId") long id) throws ApplicationException {
		System.out.println("testing print - get one");
		return this.purchasesController.getPurchase(id);
	}


	//  URL   http://localhost:8080/purchases/10
	@DeleteMapping("{purchaseId}")
	public void deletePurchase (@PathVariable("purchaseId") long id) throws ApplicationException {
		this.purchasesController.deletePurchase(id);
		System.out.println("testing print - delete");
	}


	//  URL   http://localhost:8080/purchases
	@GetMapping
	public List<PurchaseEntity> getAllPurchases() throws ApplicationException {
		System.out.println("testing print - get all");
		return this.purchasesController.getAllPurchases();
	}


	//  URL   http://localhost:8080/purchases/allCompanyPurchases?companyId=00&token=1234
	@GetMapping("/allCompanyPurchases")
	public List<PurchaseEntity> getAllCompanyPurchases(@RequestParam("companyId") long companyId) throws ApplicationException {
		return this.purchasesController.getAllCompanyPurchases(companyId);
	}


	//  URL   http://localhost:8080/purchases/allCustomerPurchases?customerId=00&token=1234
	@GetMapping("/allCustomerPurchases")
	public List<PurchaseEntity> getAllCustomerPurchases(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		Long customerId = userLoginData.getId();
		return this.purchasesController.getAllCustomerPurchases(customerId);
	}

	//  URL   http://localhost:8080/purchases/getPurchasesByType?type=00&token=1234
	@GetMapping("/allPurchasesByType")
	public List<PurchaseEntity> getPurchasesByType(@RequestParam("type") CouponType type) throws ApplicationException {
		return this.purchasesController.getPurchasesByCouponType(type);
	}

	//  URL   http://localhost:8080/purchases/allPurchasesByMaxPrice?maxPrice=00&token=1234
	@GetMapping("/allPurchasesByMaxPrice")
	public List<PurchaseEntity> getAllPurchasesByMaxPrice(@RequestParam("maxPrice") float price) throws ApplicationException {
		return this.purchasesController.getAllPurchasesByMaxPrice(price);
	}



}

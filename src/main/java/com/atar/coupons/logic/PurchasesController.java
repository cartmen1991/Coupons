package com.atar.coupons.logic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atar.coupons.beans.PurchaseDetails;
import com.atar.coupons.dao.ICouponsDao;
import com.atar.coupons.dao.ICustomersDao;
import com.atar.coupons.dao.IPurchasesDao;
import com.atar.coupons.entities.CompanyEntity;
import com.atar.coupons.entities.CouponEntity;
import com.atar.coupons.entities.CustomerEntity;
import com.atar.coupons.entities.PurchaseEntity;
import com.atar.coupons.enums.CouponType;
import com.atar.coupons.enums.ErrorTypes;
import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.internal.UserLoginDetails;

@Controller
public class PurchasesController {




	@Autowired
	private IPurchasesDao purchasesDao;

	@Autowired
	private ICouponsDao couponsDao;

	@Autowired
	private ICustomersDao customersDao;

	//---------------------------create--------------------------------------------------------------------------


	public void createPurchase(PurchaseDetails purchaseDetails, UserLoginData data) throws ApplicationException {

		CouponEntity dataBaseCoupon = this.couponsDao.findById(purchaseDetails.getCouponId()).get();
		PurchaseEntity purchase = new PurchaseEntity();

		purchase.setCoupon(dataBaseCoupon);
		purchase.setType(UserTypes.CUSTOMER);
		
		CustomerEntity customer = this.customersDao.findById(data.getId());
		
		purchase.setCustomer(customer);
		purchase.setAmountOfItems(purchaseDetails.getPurchaseAmount());

		//set total price
		float priceOfOneCoupon = dataBaseCoupon.getPrice();
		float totalPrice = priceOfOneCoupon * purchaseDetails.getPurchaseAmount();
		purchase.setTotalPrice(totalPrice);


		// setting the timestamp 
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		purchase.setTimestamp(timestamp);

		int currentUnitsInStock = dataBaseCoupon.getUnitsInStock();

		// making validations for the purchase details
		createPurchaseValidation(purchase, currentUnitsInStock);

		// if all details are valid, create the purchase in DB
		try {

			this.purchasesDao.save(purchase);

			//set units in stock to the coupon 
			dataBaseCoupon.setUnitsInStock(currentUnitsInStock - purchase.getAmountOfItems());
			this.couponsDao.save(dataBaseCoupon);

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_CREATE_PURCHASE, "Failed t create purchase");
		}

	}





	//---------------------------delete--------------------------------------------------------------------------


	public void deletePurchase(long purchaseId) throws ApplicationException {

		// checking if purchase is exist or already been deleted
		if (!this.purchasesDao.existsById(purchaseId)) {
			throw new ApplicationException(ErrorTypes.ALREADY_DELETED,"Purchase has already been deleted");
		}

		//  deleting the purchase
		try {
			this.purchasesDao.deleteById(purchaseId);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_DELETE_PURCHASE, "Failed t create purchase");
		}
	}



	//---------------------------update--------------------------------------------------------------------------


	public void updatePurchase(PurchaseEntity purchase) throws ApplicationException {


		//preparing for setting units in stock in coupon
		CouponEntity coupon = couponsDao.findById(purchase.getCoupon().getId()).get();
		int currentUnitsInStock = coupon.getUnitsInStock();

		int originalAmountOfItems = purchasesDao.findById(purchase.getId()).get().getAmountOfItems();
		int amountOfAddedItems = purchase.getAmountOfItems() - originalAmountOfItems;

		//set total price
		float priceOfOneCoupon = coupon.getPrice();
		float totalPrice = priceOfOneCoupon * purchase.getAmountOfItems();
		purchase.setTotalPrice(totalPrice);

		// setting the timestamp 
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		purchase.setTimestamp(timestamp);

		// making validations for the purchase details
		updatePurchaseValidation(purchase, currentUnitsInStock);

		// if all details are valid, update the purchase in DB
		try {
			this.purchasesDao.save(purchase);

			//set units in stock to the coupon 
			coupon.setUnitsInStock(currentUnitsInStock - amountOfAddedItems);
			this.couponsDao.save(coupon);

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_UPDATE_PURCHASE, "Failed to update the purchase");
		}


	}





	//---------------------------get one purchase-------------------------------------------------------------------------

	public PurchaseEntity getPurchase (long purchaseId) throws ApplicationException {

		if (!this.purchasesDao.existsById(purchaseId)) {
			throw new ApplicationException(ErrorTypes.PURCHASE_IS_INVALID, "Purchase doesn't exists.");
		}

		// getting the purchase
		try {
			return this.purchasesDao.findById(purchaseId).get();
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_PURCHASE, "Failed to get the purchase");
		}

	}





	//---------------------------get all purchases-------------------------------------------------------------------------



	public List<PurchaseEntity> getAllPurchases() throws ApplicationException {

		try {
			//Preparing a list of Purchases from DB
			List<PurchaseEntity> purchases = (List<PurchaseEntity>) this.purchasesDao.findAll();

			//throw an exception in case there are no purchases		
			if (purchases.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_PURCHASES, "There are no purchases at all");
			}

			//else, returning with relevant list of all purchases
			return purchases;	

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_PURCHASES, "Failed to get the purchases");
		}
	}




	//---------------------------validations--------------------------------------------------------------------------


	//-----------main functions-----------------	


	private void createPurchaseValidation(PurchaseEntity purchase, int unitsInStock) throws ApplicationException {

		validateAmountOfItems(purchase.getAmountOfItems(), unitsInStock);

	}



	private void updatePurchaseValidation(PurchaseEntity purchase, int unitsInStock) throws ApplicationException {

		validateAmountOfItems(purchase.getAmountOfItems(), unitsInStock);

	}



	//-------------------amount of items -----------------
	private void validateAmountOfItems(int amountOfItems, int unitsInStock) throws ApplicationException {

		if(amountOfItems < 1) {
			throw new ApplicationException(ErrorTypes.AMOUNT_OF_ITEMS_IS_INVALID, "The amount of items inserted is invalid");
		}

		if(amountOfItems > unitsInStock) {
			throw new ApplicationException(ErrorTypes.NOT_ENOUGHTH_ITEMS_IN_STOCK, "The amount of items requested is too big."
					+ " Currently there are only " + unitsInStock + " units from the requested coupon.");
		}
	}







	//	//--------------------------additions---------------------------------


	//---------------------------get purchases by company--------------------------------------------------------------------------

	public List<PurchaseEntity> getAllCompanyPurchases(long companyId) throws ApplicationException {

		try {
			CompanyEntity company = new CompanyEntity();
			company.setId(companyId);

			return this.purchasesDao.companyPurchases(company);

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_PURCHASES, "Failed to get purchases");
		} }


	//---------------------------get purchases by customer--------------------------------------------------------------------------

	public List<PurchaseEntity> getAllCustomerPurchases(long customerId) throws ApplicationException {

		try {
			return this.purchasesDao.getPurchasesByCustomerId(customerId);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_PURCHASES, "Failed to get purchases");
		}
	}


	//---------------------------get purchases by coupon type--------------------------------------------------------------------------

	public List<PurchaseEntity> getPurchasesByCouponType(CouponType type) throws ApplicationException {

		try {
			return this.purchasesDao.findByCouponType(type);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_PURCHASES, "Failed to get purchases");
		}
	}



	//---------------------------get purchases by max price--------------------------------------------------------------------------

	public List<PurchaseEntity> getAllPurchasesByMaxPrice(float price) throws ApplicationException {

		try {
			return this.purchasesDao.findByTotalPriceLessThan(price);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_PURCHASES, "Failed to get purchases");
		}
	}




}

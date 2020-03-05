package com.atar.coupons.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atar.coupons.beans.Coupon;
import com.atar.coupons.dao.ICompaniesDao;
import com.atar.coupons.dao.ICouponsDao;
import com.atar.coupons.entities.CompanyEntity;
import com.atar.coupons.entities.CouponEntity;
import com.atar.coupons.enums.CouponType;
import com.atar.coupons.enums.ErrorTypes;
import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;


@Controller
public class CouponsController {



	@Autowired
	private ICouponsDao couponsDao;

	@Autowired
	private ICompaniesDao companiesDao;

	@Autowired
	private PurchasesController purchasesController;

	@Autowired
	private CompaniesController companiesController;


	//---------------------------create--------------------------------------------------------------------------


	public void createCoupon(Coupon couponDetails, UserLoginData userLoginData) throws ApplicationException  {

		try {

			CouponEntity coupon = new CouponEntity();
			CompanyEntity company = this.companiesController.getCompany(userLoginData.getCompanyId());

			coupon.setCompany(company);
			coupon.setTitle(couponDetails.getTitle());
			coupon.setType(couponDetails.getType());
			coupon.setPrice(couponDetails.getPrice());
			coupon.setUnitsInStock(couponDetails.getUnitsInStock());
			//			coupon.setStartDate(couponDetails.getStartDate());
			//			coupon.setExpiryDate(couponDetails.getExpiryDate());



			// setting start date		
			Date date = new Date();
			Timestamp timestamp1 = new Timestamp(date.getTime());
			coupon.setStartDate(timestamp1);

			// setting default expiration date to one year from creating
			date.setYear(date.getYear()+1);
			Timestamp timestamp2 = new Timestamp(date.getTime());
			coupon.setExpiryDate(timestamp2);

			// making validations for the coupon details
			createCouponValdation(coupon);

			this.couponsDao.save(coupon);

			//		//if user is company, validating the right id
			//		if(userLoginData.getUserType().equals(UserTypes.COMPANY)) {
			//			coupon.getCompany().setId(userLoginData.getCompanyId());
			//		}
			//		
			//		//setting the company properly 
			//		CompanyEntity company = this.companiesController.getCompany(coupon.getCompany().getId());
			//		coupon.setCompany(company);
			//
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_CREATE_COUPON, "Failed to create coupon");
		}
	}




	//---------------------------delete--------------------------------------------------------------------------



	public void deleteCoupon(long couponId) throws ApplicationException {

		// checking if coupon is exist or already been deleted
		if (!couponsDao.existsById(couponId)) {
			throw new ApplicationException(ErrorTypes.ALREADY_DELETED,"Coupon has already been deleted");
		}


		// if not, deleting the coupon
		try {
			this.couponsDao.deleteById(couponId);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_CREATE_COUPON, "Failed to create coupon");
		}

	}






	//---------------------------update--------------------------------------------------------------------------


	public void updateCoupon(CouponEntity coupon) throws ApplicationException {

		// making validations for the coupon details
		createCouponValdation(coupon);

		// if all details are valid, update the coupon in DB
		try {
			this.couponsDao.save(coupon);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_UPDATE_COUPON, "Failed to update coupon");
		}
	}




	//---------------------------get one coupon-------------------------------------------------------------------------


	public CouponEntity getCoupon(long couponId) throws ApplicationException {

		//getting the coupon
		try {
			return this.couponsDao.findById(couponId).get();
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COUPON, "Failed to get coupon");
		}

	}



	//---------------------------get all coupons-------------------------------------------------------------------------


	public List<CouponEntity> getAllCoupons() throws ApplicationException {

		try {
			//Preparing a list of coupons from the DB
			List<CouponEntity> coupons = (List<CouponEntity>) this.couponsDao.findAll();

			//throw an exception in case there are no coupons		
			if (coupons.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_COUPONS, "There are no coupons at all");
			}

			//else, returning with relevant list of all coupons
			return coupons;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COUPONS, "Failed to get coupons");
		}
	}




	//---------------------------get coupons by company id--------------------------------------------------------------------------

	public List<CouponEntity> getCouponsByCompany(long companyId)  throws ApplicationException{

		try {
			if (!companiesDao.existsById(companyId)) {
				throw new ApplicationException(ErrorTypes.COMPANY_IS_NOT_EXIST, "Company doesn't exists.");
			}
			return this.couponsDao.getCouponsByCompanyId(companyId);

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COUPONS, "Failed to get coupons");
		}
	}

	//---------------------------get coupons by maximum price--------------------------------------------------------------------------

	public List<CouponEntity> getCouponsByPrice(float maxPrice) throws ApplicationException {

		try {
			//Preparing a list of Coupons from DB
			List<CouponEntity> coupons = this.couponsDao.getCouponsByPriceLessThan(maxPrice);

			//throw an exception in case there are no coupons		
			if (coupons.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_COUPONS, "There are no coupons at all for this condition");
			}

			//else, returning with relevant list of all coupons
			return coupons;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COUPONS, "Failed to get coupons");
		}
	}


	//---------------------------get coupons by title--------------------------------------------------------------------------

	public List<CouponEntity> getCouponsByTitle(String title)  throws ApplicationException{
		try {
			//Preparing a list of Coupons from DB
			List<CouponEntity> coupons = this.couponsDao.getCouponsByTitle(title);

			//throw an exception in case there are no coupons		
			if (coupons.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_COUPONS, "There are no coupons at all for this condition");
			}

			//else, returning with relevant list of all coupons
			return coupons;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COUPONS, "Failed to get coupons");
		}

	}


	//---------------------------get coupons by type--------------------------------------------------------------------------

	public List<CouponEntity> getCouponsByType(CouponType type)  throws ApplicationException{
		try {
			//Preparing a list of Coupons from DB
			List<CouponEntity> coupons = this.couponsDao.getCouponsByType(type);

			//throw an exception in case there are no coupons		
			if (coupons.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_COUPONS, "There are no coupons at all for this condition");
			}

			//else, returning with relevant list of all coupons
			return coupons;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COUPONS, "Failed to get coupons");
		}

	}



	//---------------------------remove expired coupons--------------------------------------------------------------------------

	public void removeExpiredCoupon() throws ApplicationException {
		Date today = new Date(Calendar.getInstance().getTime().getTime());

		try {
			couponsDao.deleteByExpiryDateLessThan(today);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.GENERAL_ERROR, "couldn't remove the expired coupons");
		}
	}



	//---------------------------validations--------------------------------------------------------------------------


	//-----------main function-----------------

	private void createCouponValdation(CouponEntity coupon) throws ApplicationException {

		//validation of title
		isTitleValid(coupon.getTitle());

		//validation of price
		isPriceValid(coupon.getPrice());

		//validation of units in stock
		isUnitsInStockValid(coupon.getUnitsInStock());

		//validation of dates
		isDatesValid(coupon.getStartDate(), coupon.getExpiryDate());
	}


	//----------------sub functions-----------------------------------------------


	//-----------title--------------------

	private void isTitleValid(String title) throws ApplicationException  {

		// at least 2 letters for title, and making sure its not null
		if(title.length() < 2) {
			throw new ApplicationException(ErrorTypes.TITLE_IS_INVALID, "The title is not valid");
		}
	}


	//-----------price--------------------

	private void isPriceValid(float price) throws ApplicationException {

		if(price < 0) {
			throw new ApplicationException(ErrorTypes.PRICE_IS_INVALID, "The price is not valid");
		}
	}



	//-----------units in stock--------------------
	private void isUnitsInStockValid(int unitsInStock) throws ApplicationException {
		// validating its not null
		if(unitsInStock < 0) {
			throw new ApplicationException(ErrorTypes.UNITS_ARE_INVALID, "The amount of units in stock is not valid");
		}
	}


	//-----------dates--------------------

	private void isDatesValid(Timestamp startDate, Timestamp expiryDate) throws ApplicationException {

		// validating its not null
		if(startDate.equals(null)) {
			throw new ApplicationException(ErrorTypes.DATE_IS_INVALID, "The starting date is not valid");
		}

		// validating the dates are in correct order
		if(startDate.after(expiryDate)) {
			throw new ApplicationException(ErrorTypes.DATE_IS_INVALID, "The end date cannot be before starting date");
		}

	}






}
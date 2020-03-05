package com.atar.coupons.logic;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atar.coupons.beans.CompanyDetails;
import com.atar.coupons.beans.CustomerDetails;
import com.atar.coupons.dao.ICompaniesDao;
import com.atar.coupons.entities.CompanyEntity;
import com.atar.coupons.entities.CustomerEntity;
import com.atar.coupons.enums.ErrorTypes;
import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;

@Controller
public class CompaniesController {


	@Autowired
	private ICompaniesDao companiesDao;

	@Autowired
	private UsersController usersController;

	@Autowired
	private CouponsController couponsController;

	//---------------------------create--------------------------------------------------------------------------


	public void createCompany(CompanyEntity company) throws ApplicationException {

		try {

			// making validations for the company details
		validateCompanyDetails(company);

		// if all details are valid, create the company in DB
			this.companiesDao.save(company);

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_CREATE_COMPANY, "Failed to create company");
		}

	}


	//---------------------------delete--------------------------------------------------------------------------


	public void deleteCompany(long companyId) throws ApplicationException {

		// checking if company is exist or already been deleted
		if (!companiesDao.existsById(companyId)) {
			throw new ApplicationException(ErrorTypes.ALREADY_DELETED,"Company has already been deleted");
		}

		// if not, deleting the company
		try {
			this.companiesDao.deleteById(companyId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_DELETE_COMPANY, "failed to delete company");
		}
	}



	//---------------------------update--------------------------------------------------------------------------


	public void updateCompany(CompanyEntity company, UserLoginData userLoginData) throws ApplicationException {

		// making validations for the company details
		validateCompanyDetails(company);

		// if all OK updating the company
		try {
			
			this.companiesDao.save(company);
			
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_UPDATE_COMPANY, "Failed to update company");
		}
	}




	//---------------------------get one company-------------------------------------------------------------------------


	public CompanyEntity getCompany (long companyId) throws ApplicationException {

		// getting the company
		try {
			return this.companiesDao.findById(companyId).get();
			 
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COMPANY, "failed to get company");
		}
	}



	//---------------------------get all companies-------------------------------------------------------------------------


	public List<CompanyEntity> getAllCompanies() throws ApplicationException {

		try {
			//Preparing a list from the Companies in DB
			List<CompanyEntity> companies = (List<CompanyEntity>) this.companiesDao.findAll();

			//throw an exception in case there are no companies		
			if (companies.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_COMPANIES, "There are no companies at all");
			}

			//else, returning with relevant list of all companies
			return companies;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_COMPANIES, "failed to get companies");
		}
	}





	//---------------------------validations--------------------------------------------------------------------------


	//-----------main function-----------------

	private void validateCompanyDetails(CompanyEntity company) throws ApplicationException {

		//validation of phone number
		validatePhone(company.getPhone());

		//validation of e-mail 
		validateEmail(company.getEmail());

	}

	//------------phone--------------------

	private void validatePhone(String phone) throws ApplicationException {

		if(phone.length() > 15 || phone.length() < 9) {
			throw new ApplicationException(ErrorTypes.PHONE_IS_INVALID, "Phone number is invalid");

		}
	}


	//------------e-mail--------------------

	private void validateEmail(String email) throws ApplicationException {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		if(!email.matches(regex)) {
			throw new ApplicationException(ErrorTypes.EMAIL_IS_INVALID, "Password is invalid");
		}

	}

	
	//----------------------------------------extra----------------------------------------------------

	public CompanyDetails getMyDetails(long companyId) throws ApplicationException {
		
		CompanyDetails dataBaseCompanyDetails = new CompanyDetails();
		CompanyEntity company = this.companiesDao.findById(companyId).get();
	
		dataBaseCompanyDetails.setId(company.getId());
		dataBaseCompanyDetails.setCompanyName(company.getCompanyName());
		dataBaseCompanyDetails.setType(company.getType());
		dataBaseCompanyDetails.setAddress(company.getAddress());
		dataBaseCompanyDetails.setEmail(company.getEmail());
		dataBaseCompanyDetails.setPhone(company.getPhone());
		
		return dataBaseCompanyDetails;
	}

}

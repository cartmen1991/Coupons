package com.atar.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atar.coupons.beans.CustomerDetails;
import com.atar.coupons.dao.ICustomersDao;
import com.atar.coupons.dao.IUsersDao;
import com.atar.coupons.entities.CustomerEntity;
import com.atar.coupons.beans.SignUpDetails;
import com.atar.coupons.entities.UserEntity;
import com.atar.coupons.enums.ErrorTypes;
import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;

@Controller
public class CustomersController {


	@Autowired
	private ICustomersDao customersDao;


	@Autowired
	private IUsersDao usersDao;

	@Autowired
	private UsersController usersController;

	//---------------------------create--------------------------------------------------------------------------


	public void createCustomer(SignUpDetails signUpDetails) throws ApplicationException {

		CustomerEntity customer = new CustomerEntity();
		UserEntity user = new UserEntity();

		user.setUserName(signUpDetails.getUserSignUpDetails().getUserName());
		user.setPassword(signUpDetails.getUserSignUpDetails().getPassword());
		user.setType(UserTypes.CUSTOMER);
		
		customer.setUser(user);
		customer.setAddress(signUpDetails.getAddress());
		customer.setPhone(signUpDetails.getPhone());
		customer.setEmail(signUpDetails.getEmail());
		customer.setAmountOfKids(signUpDetails.getAmountOfKids());
		customer.setStatus(signUpDetails.getStatus());


		// making validations for the customer details
		validateCustomerDetails(customer);

		//		validateCustomerUser(customer);

		// if all details are valid, create the customer in DB
		try {
			this.customersDao.save(customer);
		}catch (Exception e) {
			
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_CREATE_CUSTOMER, "Customer couldn't be created.");
		}
	}





	//---------------------------delete--------------------------------------------------------------------------


	public void deleteCustomer(long customerId) throws ApplicationException {

		// checking if customer is exist or already been deleted
		if (!customersDao.existsById(customerId)) {
			throw new ApplicationException(ErrorTypes.ALREADY_DELETED,"Customer has already been deleted or not exist");
		}

		try {
			//deleting the customer
			this.customersDao.deleteById(customerId);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.ALREADY_DELETED, "Customer already deleted or we failed to delete it" );
		}

	}



	//---------------------------update--------------------------------------------------------------------------


	public void updateCustomer(CustomerEntity customer, long userId) throws ApplicationException {

		if (!this.customersDao.existsById(customer.getId())) {
			throw new ApplicationException(ErrorTypes.CUSTOMER_ID_IS_INVALID, "Customer doesn't exists.");
		}

		// making validations for the customer details
		validateCustomerDetails(customer);


		//validating User object of customer
		//		this.usersController.updateUserValidations(customer.getUser());

		try {
			// if all details are valid, update the customer in DB
			this.customersDao.save(customer);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_UPDATE_CUSTOMER, ErrorTypes.FAILED_TO_UPDATE_CUSTOMER.getErrorMessage());
		}

	}



	//---------------------------get one customer-------------------------------------------------------------------------


	public CustomerEntity getCustomer(long customerId, UserLoginData userLoginData) throws ApplicationException {

		//validating user is admin
		if (!userLoginData.getUserType().equals(UserTypes.ADMIN)) {
			throw new ApplicationException(ErrorTypes.INVALID_CREDENTIALS, "Invalid credentials.");
		}

		try {
			//getting the customer
			return this.customersDao.findById(customerId);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_CUSTOMER, "Failed to get customer");
		}
	}



	//---------------------------get all customers-------------------------------------------------------------------------


	public List<CustomerEntity> getAllCustomers() throws ApplicationException {

		try {
			//Preparing a list all customers in DB
			List<CustomerEntity> customers = (List<CustomerEntity>) this.customersDao.findAll();

			//throw an exception in case there are no customers		
			if (customers.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_CUSTOMERS, "There are no customers at all");
			}

			//else, returning with relevant list of all customers
			return customers;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.THERE_ARE_NO_CUSTOMERS, "There are no customers at all");
		}

	}



	//---------------------------validations--------------------------------------------------------------------------


	//-----------main function-----------------

	private void validateCustomerDetails(CustomerEntity customer) throws ApplicationException {

		//validating the amount of kids
		validateAmountOfKids(customer.getAmountOfKids());

		//validation of phone number
		validatePhone(customer.getPhone());

		//validation of e-mail 
		validateEmail(customer.getEmail());

	}

	//-----------amount of kids-----------------

	private void validateAmountOfKids(int amountOfKids) throws ApplicationException {

		//valid normal amount of kids. avoiding under-zero types (and people like Goel Ratzon) 
		if (amountOfKids < 0 || amountOfKids > 30) {
			throw new ApplicationException(ErrorTypes.AMOUNT_OF_KIDS_IS_INVALID, "The amount of kids is invalid");
		}
	}


	//------------e-mail--------------------

	private void validateEmail(String email) throws ApplicationException {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!email.matches(regex)) {
			throw new ApplicationException(ErrorTypes.PASSWORD_IS_INVALID, "Password is invalid");
		}

	}


	//------------phone--------------------

	private void validatePhone(String phone) throws ApplicationException {

		if(phone.length() > 15 || phone.length() < 9) {
			throw new ApplicationException(ErrorTypes.PHONE_IS_INVALID, "Phone number is invalid");
		}
	}

	//---------------------------validate Customer User--------------------------------------------------------------------------

	private void validateCustomerUser(CustomerEntity customer) throws ApplicationException {

		UserEntity user = customer.getUser();

		if(!this.usersDao.existsById(user.getId())) {
			throw new ApplicationException(ErrorTypes.USER_IS_INVALID, "User doesn't exists");
		}

	}





	public CustomerDetails getMyDetails(Long customerId) throws ApplicationException {

		CustomerDetails dataBaseCustomerDetails = new CustomerDetails();
		CustomerEntity customer = this.customersDao.findById(customerId).get();
	
		dataBaseCustomerDetails.setId(customer.getId());
		dataBaseCustomerDetails.setAddress(customer.getAddress());
		dataBaseCustomerDetails.setAmountOfKids(customer.getAmountOfKids());
		dataBaseCustomerDetails.setEmail(customer.getEmail());
		dataBaseCustomerDetails.setStatus(customer.getStatus());
		dataBaseCustomerDetails.setPhone(customer.getPhone());
		dataBaseCustomerDetails.setUserName(customer.getUser().getUserName());
		
		
		
		return dataBaseCustomerDetails;
	}
				


}

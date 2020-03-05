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
import org.springframework.web.bind.annotation.RestController;

import com.atar.coupons.beans.CustomerDetails;
import com.atar.coupons.entities.CustomerEntity;
import com.atar.coupons.beans.SignUpDetails;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.logic.CustomersController;


@RestController
@RequestMapping("/customers")
public class CustomersApi {



	//		---  instance to logic level ---		
	@Autowired
	private CustomersController customersController;


	//		---------------------------
	//		         C R U D
	//		----------------------------


	//  URL   http://localhost:8080/customers
	@PostMapping
	public void createCustomer(@RequestBody SignUpDetails signUpDetails) throws ApplicationException {
		this.customersController.createCustomer(signUpDetails);
		System.out.println("testing print - create");
	}


	//  URL   http://localhost:8080/customers
	@PutMapping
	public void updateCustomer(@RequestBody CustomerEntity customer, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		this.customersController.updateCustomer(customer, userLoginData.getId());
		System.out.println("testing print - update");
	}

	
	//  URL   http://localhost:8080/customers/10
	@GetMapping("{customerId}")
	public CustomerEntity getCustomer(@PathVariable("customerId") long id, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		return this.customersController.getCustomer(id, userLoginData);
	}

	
	//  URL   http://localhost:8080/customers/10
	@DeleteMapping("{customerId}")
	public void deleteCustomer (@PathVariable("customerId") long id, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		this.customersController.deleteCustomer(id);
	}

	
	//  URL   http://localhost:8080/customers
	@GetMapping
	public List<CustomerEntity> getAllCustomers() throws ApplicationException {
		return this.customersController.getAllCustomers();
	}
	
//  URL   http://localhost:8080/customers/myDetails
	@GetMapping("/myDetails")
	public CustomerDetails getMyDetails(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		Long customerId = userLoginData.getId();
		return this.customersController.getMyDetails(customerId);	
	}
	
	
}

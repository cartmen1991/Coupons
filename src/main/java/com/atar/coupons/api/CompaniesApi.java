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

import com.atar.coupons.beans.CompanyDetails;
import com.atar.coupons.beans.CustomerDetails;
import com.atar.coupons.entities.CompanyEntity;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.logic.CompaniesController;


@RestController
@RequestMapping("/companies")
public class CompaniesApi {


	//		---  instance to logic level ---		
	@Autowired
	private CompaniesController companiesController;



	//		---------------------------
	//		         C R U D
	//		----------------------------

	
	//  URL   http://localhost:8080/companies
	@PostMapping
	public void createCompany(@RequestBody CompanyEntity company) throws ApplicationException {
		this.companiesController.createCompany(company);
		System.out.println("testing print - create");
	}

	
	//  URL   http://localhost:8080/companies
	@PutMapping
	public void updateCompany(@RequestBody CompanyEntity company, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		this.companiesController.updateCompany(company, userLoginData);
		System.out.println("testing print - update");
	}

	
	//  URL   http://localhost:8080/companies/10
	@GetMapping("{companyId}")
	public CompanyEntity getCompany(@PathVariable("companyId") long id) throws ApplicationException {
		System.out.println("testing print - get one");
		return this.companiesController.getCompany(id);
	}

	
	//  URL   http://localhost:8080/companies/10
	@DeleteMapping("{companyId}")
	public void deleteCompany (@PathVariable("companyId") long id) throws ApplicationException {
		this.companiesController.deleteCompany(id);
		System.out.println("testing print - delete");
	}

	
	//  URL   http://localhost:8080/companies
	@GetMapping
	public List<CompanyEntity> getAllCompanies() throws ApplicationException {
		System.out.println("testing print - get all");
		return this.companiesController.getAllCompanies();
	}
	
//  URL   http://localhost:8080/companies/myDetails
	@GetMapping("/myDetails")
	public CompanyDetails getMyDetails(HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		long companyId = userLoginData.getCompanyId();
		return this.companiesController.getMyDetails(companyId);	
	}
	
}

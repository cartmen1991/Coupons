package com.atar.coupons;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.logic.CompaniesController;
import com.atar.coupons.logic.CouponsController;
import com.atar.coupons.logic.CustomersController;
import com.atar.coupons.logic.PurchasesController;
import com.atar.coupons.logic.UsersController;
//import com.mysql.cj.util.TimeUtil;

public class Tester {

	public static void main(String[] args) throws Exception {

		
		
		//-------------------DATE tools---------------------
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Timestamp timestamp2 = new Timestamp(122, 12, 23, 15, 30, 05, 00);
		
		
		
		//--------------instances to controllers-----------------------
		CustomersController customersController = new CustomersController();
		CompaniesController companiesController = new CompaniesController();
		CouponsController couponsController = new CouponsController();
		UsersController usersController = new UsersController();
		PurchasesController purchasesController = new PurchasesController();

		String x = "    yo ssi    ";
		System.out.println(x.trim());
		
//------------------------------------------------------------------------------------
//		Notes:
//			1. the testers divided by CRUD.
//			2. don't forget to hide back tests as comment after finish using it.
//------------------------------------------------------------------------------------
		
		

		//-----------------tester for CREATE functions ------------------------------------------------------------------------

		
		
//		Company company = new Company("pokemon", "gmaes", "japan", "050-5678912", "pikachu@gmail.com");
//		User user = new User(UserTypes.COMPANY, "mamfgdfga", "12Ae5434", (long) 1);
//		Customer customer = new Customer(user, 3, true, "ramat-gan", "digimon@gmail.com", "09-1231234");
//		Coupon coupon = new Coupon(2, "coffeeshops", 355, timestamp, timestamp2);
//		Purchase purchase = new Purchase(12, 13, 14, timestamp);				
//		
//		companiesController.createCompany(company);
//		usersController.createUser(user);
//		customersController.createCustomer(customer);
//		couponsController.createCoupon(coupon);
//		purchasesController.addPurchase(purchase);
//		
		
		
		
		
		
		
		//-----------------tester for DELETE functions ------------------------------------------------------------------------

		
//		Company company = companiesController.getCompany(3);
//		Customer customer = customersController.getCustomer(11);
//		Coupon coupon = couponsController.getCoupon(13);
//		User user = usersController.getUser(19);
//		Purchase purchase = purchasesController.getPurchase(23);
//		
//		companiesController.deleteCompany(7);
//		usersController.deleteUser(user.getID());
//		customersController.deleteCustomer(customer.getUser().getID());
//		couponsController.deleteCoupon(coupon.getID());
//		purchasesController.deletePurchase(purchase.getID());
		
		
				
		
		
		
		
		
		//-----------------tester for GET + UPDATE functions ------------------------------------------------------------------------
		
		
		
//		Company company =  companiesController.getCompany(29);
//		Customer customer = customersController.getCustomer(1);
//		Coupon coupon = couponsController.getCoupon(12);
//		User user = usersController.getUser(10);
//		Purchase purchase = purchasesController.getPurchase(2);
//
//		company.setAddress("afuuuula");
//		customer.setAmountOfKids(4);
//		coupon.setPrice(55);
//		user.setName("samimati");
//		purchase.setAmountOfItems(88);
//
//		companiesController.updateCompany(company);
//		customersController.updateCustomer(customer);
//		couponsController.updateCoupon(coupon);
//		usersController.updateUser(user);
//		purchasesController.updatePurchase(purchase);
//		
		
		
		
		
		
		
		//-----------------tester for GET ALL functions ------------------------------------------------------------------------


//				List<Company>companies = companiesController.getAllCompanies();
//				List<Coupon>coupons = couponsController.getAllCoupons();
//				List<Customer>customers = customersController.getAllCustomers();
//				List<User>users = usersController.getAllUsers();
//				List<Purchase>purchases = purchasesController.getAllPurchases();
//		
//				for(Company a : companies) {
//					System.out.println(a.getAddress());
//					System.out.println(a.getEmail());
//					System.out.println(a.getName());
//					System.out.println(a.getPhone());
//					System.out.println(a.getType());
//					System.out.println(a.getID());
//					System.out.println("\n");
//				}
//				
//				for(Coupon b : coupons) {
//	    			System.out.println(b.getPrice());
//					System.out.println(b.getTitle());
//				    System.out.println(b.getCompanyID());
//					System.out.println(b.getStartDate());
//					System.out.println(b.getEndDate());
//					System.out.println(b.getID());
//					System.out.println("\n");
//				}
//				
//				for(Customer c : customers) {
//					System.out.println(c.getAddress());
//					System.out.println(c.getEmail());
//					System.out.println(c.getAmountOfKids());
//					System.out.println(c.getPhone());
//					System.out.println(c.getIsMarried());
//					System.out.println("\n");
//				}
//				
//				for(User d : users) {
//					System.out.println(d.getCompanyID());
//					System.out.println(d.getPassword());
//					System.out.println(d.getName());
//					System.out.println(d.getType());
//					System.out.println(d.getID());
//					System.out.println("\n");
//				}
//				
//				for(Purchase e : purchases) {
//					System.out.println(e.getAmountOfItems());
//					System.out.println(e.getCouponID());
//					System.out.println(e.getCustomerID());
//					System.out.println(e.getTimestamp());
//					System.out.println(e.getID());
//					System.out.println("\n");
//				}
//				









	}
}

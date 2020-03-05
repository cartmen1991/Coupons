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

import com.atar.coupons.entities.UserEntity;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.SuccessfulLoginDetails;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.internal.UserLoginDetails;
import com.atar.coupons.logic.UsersController;


@RestController
@RequestMapping("/users")
public class UsersApi {


	//	---  instance to logic level ---		
	@Autowired
	private UsersController usersController;



	//  URL   http://localhost:8080/users/login
	@PostMapping("/login")
	public SuccessfulLoginDetails login(@RequestBody UserLoginDetails userLoginDetails) throws ApplicationException {
		System.out.println("testing print - login");
		return this.usersController.login(userLoginDetails);
	}


	//	---------------------------
	//	         C R U D
	//	----------------------------


	//  URL   http://localhost:8080/users
	@PostMapping
	public void createUser(@RequestBody UserEntity user) throws ApplicationException {
		this.usersController.createUser(user);
		System.out.println("testing print - create");
	}

	
	//  URL   http://localhost:8080/users
	@PutMapping
	public void updateUser(@RequestBody UserEntity user, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		this.usersController.updateUser(user, userLoginData.getId());
		System.out.println("testing print - update");
	}

	
	//  URL   http://localhost:8080/users/10
	@GetMapping("{userId}")
	public UserEntity getUser(@PathVariable("userId") long id, HttpServletRequest request) throws ApplicationException {
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		System.out.println("testing print - get one");
		return this.usersController.getUser(id, userLoginData.getId());
	}
	

	//  URL   http://localhost:8080/users/10
	@DeleteMapping("{userId}")
	public void deleteUser (@PathVariable("userId") long id, HttpServletRequest request) throws ApplicationException {
		this.usersController.deleteUser(id);
		System.out.println("testing print - delete");
	}

	//  URL   http://localhost:8080/users
	@GetMapping
	public List<UserEntity> getAllUsers() throws ApplicationException {
		System.out.println("testing print - get all");
		return this.usersController.getAllUsers();
	}
	
	
	//  URL   http://localhost:8080/users/myDetails
	@GetMapping("/myDetails")
	public UserEntity getMyDetails(HttpServletRequest request) throws ApplicationException {
		UserLoginData userData = (UserLoginData) request.getAttribute("userLoginData");
		return this.usersController.getMyDetails(userData.getId());	
	}
	
	
	

}

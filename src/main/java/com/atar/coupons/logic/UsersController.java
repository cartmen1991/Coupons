package com.atar.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atar.coupons.dao.IUsersDao;
import com.atar.coupons.entities.UserEntity;
import com.atar.coupons.enums.ErrorTypes;
import com.atar.coupons.enums.UserTypes;
import com.atar.coupons.exceptions.ApplicationException;
import com.atar.coupons.internal.SuccessfulLoginDetails;
import com.atar.coupons.internal.UserLoginData;
import com.atar.coupons.internal.UserLoginDetails;


@Controller
public class UsersController {


	@Autowired
	private IUsersDao usersDao;

	@Autowired
	private CacheController cacheController;

	@Autowired
	CustomersController customersController; 

	private static final String ENCRYPTION_TOKEN_SALT = "12412411352+ASAECASCDAC-#@$#@$#$";


	//---------------------------create--------------------------------------------------------------------------


	public void createUser(UserEntity user) throws ApplicationException{

		// making validations for the user details
		createUserValidations(user);

		// hashing the password
//		user.setPassword(String.valueOf(user.getPassword().hashCode()));

		// if all details are valid, create the user in DB
		try {
			this.usersDao.save(user);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_CREATE_USER, "Failed to create user");
		}

	}




	//---------------------------delete--------------------------------------------------------------------------


	public void deleteUser(long userId) throws ApplicationException{

		// checking if user is exist or already been deleted
		if (!this.usersDao.existsById(userId)) {
			throw new ApplicationException(ErrorTypes.ALREADY_DELETED,"User has already been deleted or doesn't exist");
		}

		// deleting the user
		try {
			this.usersDao.deleteById(userId);	
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_DELETE_USERS, "Failed to delete the user");
		}
	}



	//---------------------------update--------------------------------------------------------------------------


	public void updateUser(UserEntity user, long userId) throws ApplicationException{

		if (!this.usersDao.existsById(userId)) {
			throw new ApplicationException(ErrorTypes.USER_IS_NOT_EXIST, "User doesn't exists.");
		}

		// making validations for the user details
		updateUserValidations(user);
		

//		//hashing the password
//		user.setPassword(String.valueOf(user.getPassword().hashCode()));

		// if all details are valid, update the user in DB
		try {
			this.usersDao.save(user);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_UPDATE_USER, "Failed to update user");
		}

	}




	//---------------------------get one user-------------------------------------------------------------------------

	public UserEntity getUser(long userId, long clientId) throws ApplicationException{

		try {
			UserEntity user = this.usersDao.findById(clientId);

//			validating user is admin
			if (!user.getType().equals(UserTypes.ADMIN)) {
				throw new ApplicationException(ErrorTypes.INVALID_CREDENTIALS, "Invalid credentials.");
			}

			return this.usersDao.findById(userId);

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_USER, "Failed to get user");
		}

	}




	//---------------------------get all users-------------------------------------------------------------------------


	public List<UserEntity> getAllUsers() throws ApplicationException {
		try {
			//Preparing the list with DB values through Dao
			List<UserEntity> users = (List<UserEntity>) this.usersDao.findAll();

			//throw an exception in case there are no users		
			if (users.size() == 0) {
				throw new ApplicationException(ErrorTypes.THERE_ARE_NO_USERS, "There are no users at all");
			}

			//else, returning with relevant list of all users
			return users;

		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.FAILED_TO_GET_USERS, "Failed to get users");
		}
	}


	//---------------------------my details-------------------------------------------------------------------------

	public UserEntity getMyDetails(long id) throws ApplicationException{

		try {
			return this.usersDao.findById(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.DETAILS_ARE_INVALID, "Failed to get the details");
		}
	}




	//--------------------------- login -------------------------------------------------------------------------

	public SuccessfulLoginDetails login(UserLoginDetails userLoginDetails) throws ApplicationException {
	
		UserEntity user;
//		userLoginDetails.setPassword(String.valueOf(userLoginDetails.getPassword().hashCode()));
		try {
			user = usersDao.findByUserNameAndPassword(userLoginDetails.getUserName(), userLoginDetails.getPassword());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorTypes.GENERAL_ERROR, "Server error, please try again later");
		}
		
		if (user == null) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "invalid username or password");
		}

		UserLoginData userLoginData;

		if (user.getType().equals(UserTypes.COMPANY)) {
			userLoginData = new UserLoginData(user.getId(), user.getType(), user.getCompany().getId());
		}

		else {
			userLoginData = new UserLoginData(user.getId(), user.getType());

		}

		int token = generateToken(userLoginDetails);
				
		cacheController.put(String.valueOf(token), userLoginData);

		return new SuccessfulLoginDetails(token, userLoginData.getUserType());

	}



	//--------------------------- generate token -------------------------------------------------------------------------

	private int generateToken(UserLoginDetails userLoginDetails) {

		// preparing an evil curse
		String text = userLoginDetails.getUserName() + Calendar.getInstance().getTime().toString() + ENCRYPTION_TOKEN_SALT;

		// send it back as the an integer
		return text.hashCode();
	}

	
	
	//---------------------------validations--------------------------------------------------------------------------


	//-----------main functions-----------------

	public void createUserValidations(UserEntity user) throws ApplicationException {

		// validation of name
		validateName(user.getUserName());

		//validation of password 
		validatePassword(user.getPassword());

		//validation of type
		validateType(user.getType());
	}

	
	public void updateUserValidations(UserEntity user) throws ApplicationException {

		//validation of password 
		validatePassword(user.getPassword());

		//validation of type
		validateType(user.getType());
	}



	//------------name validation--------------------

	private void validateName(String name) throws ApplicationException {

		if(this.usersDao.existsByUserName(name)) {
			throw new ApplicationException(ErrorTypes.USERNAME_IS_INVALID, ErrorTypes.USERNAME_IS_INVALID.getErrorMessage());
		}
	}


	//------------type validation--------------------

	private void validateType(UserTypes type) throws ApplicationException {

		//validating that type is either company or a customer
		if(type != UserTypes.ADMIN && type != UserTypes.COMPANY && type != UserTypes.CUSTOMER) {
			throw new ApplicationException(ErrorTypes.TYPE_IS_INVALID, ErrorTypes.TYPE_IS_INVALID.getErrorMessage());
		}
	}


	//------------password validations-------------------

	private void validatePassword(String password) throws ApplicationException {

		//validating password is 8 to 40 letter, and contains a digit, a capital letter and a regular letter.

		validateLowerCaseLetter(password); 

		validateUpperCaseLetter(password);

		validateNumber(password);

		validateLength(password);

	}


	//--------------------------------------------------------------------
	private void validateLowerCaseLetter(String password) throws ApplicationException {

		boolean hasLowerCaseLetter = false;

		for (int index = 0; index < password.length(); index++) {
			char temp = password.charAt(index);
			if(temp >= 'a' && temp <= 'z') {
				hasLowerCaseLetter = true;
			}
		}
		if(!hasLowerCaseLetter) {
			throw new ApplicationException(ErrorTypes.PASSWORD_IS_INVALID, "The password need to contain a lower case letter");
		}
	}

	//--------------------------------------------------------------------
	private void validateUpperCaseLetter(String password) throws ApplicationException {

		boolean hasUpperCaseLetter = false;

		for (int index = 0; index < password.length(); index++) {
			char temp = password.charAt(index);
			if(temp >= 'A' && temp <= 'Z') {
				hasUpperCaseLetter = true;
			}
		}
		if(!hasUpperCaseLetter) {
			throw new ApplicationException(ErrorTypes.PASSWORD_IS_INVALID, "The password need to contain an upper case letter");
		}
	}



	//--------------------------------------------------------------------
	private void validateNumber(String password) throws ApplicationException {

		boolean hasNumber = false;

		for (int index = 0; index < password.length(); index++) {
			char temp = password.charAt(index);
			if(temp >= '0' && temp <= '9') {
				hasNumber = true;
			}
		}
		if(!hasNumber) {
			throw new ApplicationException(ErrorTypes.PASSWORD_IS_INVALID, "The password need to contain a number");
		}
	}



	//--------------------------------------------------------------------
	private void validateLength(String password) throws ApplicationException {

		if(password.length() < 8 && password.length() > 20) {
			throw new ApplicationException(ErrorTypes.PASSWORD_IS_INVALID, "The password length should be between 8 to 20 digits");
		}
	}

	
	

	//-------------------------------------------------- hash code and equals--------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usersDao == null) ? 0 : usersDao.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersController other = (UsersController) obj;
		if (usersDao == null) {
			if (other.usersDao != null)
				return false;
		} else if (!usersDao.equals(other.usersDao))
			return false;
		return true;
	}

}
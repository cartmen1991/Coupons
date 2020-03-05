package com.atar.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atar.coupons.entities.UserEntity;


public interface IUsersDao extends CrudRepository<UserEntity, Long>{

	public UserEntity findByUserNameAndPassword (String userName, String password);

	public UserEntity findById (long userId);

	public boolean existsByUserName(String userName);


}




//
//	@Query("DELETE u FROM UserEntity u WHERE u.company=:company")
//	public void deleteCompanyUsers(long companyId);
//
//
//
//	@Query("SELECT u FROM UserEntity u WHERE u.userName=:name AND u.password=:password")
//	List<UserEntity>fetchUsers(@Param("name") String name, @Param("password") String password);
//













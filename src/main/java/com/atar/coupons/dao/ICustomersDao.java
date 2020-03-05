package com.atar.coupons.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.atar.coupons.entities.CustomerEntity;
import com.atar.coupons.exceptions.ApplicationException;

public interface ICustomersDao extends CrudRepository<CustomerEntity, Long>{

	public CustomerEntity findById(long id);
	//	public boolean existByCustomerId(long customerId);


}

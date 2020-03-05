
package com.atar.coupons.dao;


import org.springframework.data.repository.CrudRepository;

import com.atar.coupons.entities.CompanyEntity;


public interface ICompaniesDao extends CrudRepository<CompanyEntity, Long> {

	}


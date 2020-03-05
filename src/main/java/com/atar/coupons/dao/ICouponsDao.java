package com.atar.coupons.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;

import com.atar.coupons.entities.CouponEntity;
import com.atar.coupons.enums.CouponType;

public interface ICouponsDao extends CrudRepository<CouponEntity, Long>{

	public List<CouponEntity> getCouponsByCompanyId(long companyId);

	public List<CouponEntity> getCouponsByPriceLessThan(float maxPrice);

	public List<CouponEntity> getCouponsByTitle(String title);
	
	public List<CouponEntity> getCouponsByType(CouponType type);

	public void deleteByExpiryDateLessThan(Date today);
}

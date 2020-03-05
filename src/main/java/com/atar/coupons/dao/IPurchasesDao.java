package com.atar.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atar.coupons.entities.CompanyEntity;
import com.atar.coupons.entities.CouponEntity;
import com.atar.coupons.entities.PurchaseEntity;
import com.atar.coupons.enums.CouponType;

public interface IPurchasesDao extends CrudRepository<PurchaseEntity, Long>{
	
	@Query ("SELECT p FROM PurchaseEntity p WHERE p.coupon IN(SELECT c FROM CouponEntity c WHERE c.company =:companyId)")
	public List<PurchaseEntity> companyPurchases(@Param("companyId") CompanyEntity company);

	public List<PurchaseEntity> getPurchasesByCustomerId(long customerId);
	
	public List<PurchaseEntity> findByCouponType(CouponType type);

	public List<PurchaseEntity> findByTotalPriceLessThan(float price);
}

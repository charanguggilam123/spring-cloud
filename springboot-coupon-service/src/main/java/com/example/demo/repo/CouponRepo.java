package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {
	
	Optional<Coupon> findByCode(String code);

}

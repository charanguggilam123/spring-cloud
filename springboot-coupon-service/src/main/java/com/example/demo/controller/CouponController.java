package com.example.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Coupon;
import com.example.demo.repo.CouponRepo;

@RestController
@RequestMapping("/coupon-api")
public class CouponController {
	
	@Autowired
	private CouponRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(CouponController.class);
	
	@GetMapping("/{code}")
	public ResponseEntity<Coupon> fetchCouponByCode(@PathVariable String code) {
		Optional<Coupon> resp = repo.findByCode(code);
		LOG.info("fetching coupon for code: {}",code);
		if(resp.isPresent())
			return ResponseEntity.ok().header("cache-age", "1440").body(resp.get());
		LOG.info("No coupon found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}

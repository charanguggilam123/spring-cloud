package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.Coupon;
import com.example.demo.domain.Product;
import com.example.demo.repo.ProductRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
//	@RateLimiter(name = "rest-call-coupon-service")
	@CircuitBreaker(name = "rest-call-coupon-service-cb",fallbackMethod = "fallback")
	@Retry(name = "coupon-service-retry")
	public Product addProduct(Product productInput) {
		System.out.println("Invoking....");
		Coupon coupon = getCouponDetails(productInput.getCouponCode());
		LOG.info("coupon retrieved: {}",coupon);
		if(coupon!=null)
			productInput.setSellingPrice(productInput.getPrice().subtract(coupon.getDiscount()));
		else
			productInput.setSellingPrice(productInput.getPrice());
		LOG.info("product persisting: {}",productInput);
		return repo.save(productInput);
		
	}
	
	public Product getProduct(long id) {
		LOG.info("fetching product {}",id);
		Optional<Product> resp = repo.findById(id);
		if(resp.isPresent()) {
			LOG.info("product fetched {}",resp.get());
			return resp.get();
		}else
			throw new EntityNotFoundException("No entity found");
	}
	
	public Product fallback(Product productInput,Exception ex) {
		
		throw new RuntimeException(ex.getMessage());
		
	}
	
	private Coupon getCouponDetails(String code) {
		return restTemplate.getForEntity("http://SPRINGBOOT-COUPON-SERVICE/coupon-api/"+code, Coupon.class).getBody();
	}

}

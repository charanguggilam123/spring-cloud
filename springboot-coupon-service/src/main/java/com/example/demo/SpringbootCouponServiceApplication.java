package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Coupon;
import com.example.demo.repo.CouponRepo;

@SpringBootApplication
public class SpringbootCouponServiceApplication implements CommandLineRunner {

	@Autowired
	private CouponRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCouponServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println( LocalDate.now().plus(3, ChronoUnit.HOURS));
		Coupon coupon = new Coupon("MAX50", BigDecimal.valueOf(50), LocalDate.now().plusMonths(1));
		Coupon coupon2 = new Coupon("FLASH10", BigDecimal.valueOf(10), LocalDate.now().plus(1, ChronoUnit.DAYS));
		Coupon coupon3 = new Coupon("FESTIVE30", BigDecimal.valueOf(50), LocalDate.now().plusDays(3));
		repo.saveAll(List.of(coupon, coupon2, coupon3));

	}

}

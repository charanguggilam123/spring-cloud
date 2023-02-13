package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product-api")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {

		return service.addProduct(product);

	}

	@GetMapping("/get/{id}")
	public Product getProductDetails(@PathVariable("id") long productId) {

		return service.getProduct(productId);

	}

}

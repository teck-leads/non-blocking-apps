package com.techleads.app.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProductService {

	@Autowired
	private RetrieveProduct retrieveProduct;
	@Autowired
	private ReviewProduct reviewProduct;

	public void productService(String id) {
		Instant start = Instant.now();
		
		String productInfo = retrieveProduct.productInfo(id);// blocking call
		String reviewProudctResult =reviewProduct.reviewProudct(id);// blocking call
		
		System.out.println(productInfo);
		
		System.out.println(reviewProudctResult);
		
		Instant finish = Instant.now();
		System.out.println("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());


	}

}

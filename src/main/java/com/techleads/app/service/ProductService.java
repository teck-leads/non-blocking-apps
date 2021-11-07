package com.techleads.app.service;

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

		
		String productInfo = retrieveProduct.productInfo(id);// blocking call
		String reviewProudctResult =reviewProduct.reviewProudct(id);// blocking call
		
		System.out.println(productInfo);
		
		System.out.println(reviewProudctResult);


	}

}

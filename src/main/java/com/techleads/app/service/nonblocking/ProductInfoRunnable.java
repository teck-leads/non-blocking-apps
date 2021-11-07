package com.techleads.app.service.nonblocking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.service.RetrieveProduct;

import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
public class ProductInfoRunnable implements Runnable {

	@Autowired
	private RetrieveProduct retrieveProduct;
	
	private String id;
	private String productInfo;

	public String getProductInfo() {
		return productInfo;
		
	}

	public ProductInfoRunnable(String id) {
		this.id = id;
		this.retrieveProduct=new RetrieveProduct();
	}

	@Override
	public void run() {

		productInfo = retrieveProduct.productInfo(id);
	}

}

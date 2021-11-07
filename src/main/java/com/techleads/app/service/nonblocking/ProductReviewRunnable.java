package com.techleads.app.service.nonblocking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.service.ReviewProduct;

import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
public class ProductReviewRunnable implements Runnable {
	@Autowired
	private ReviewProduct reviewProduct;

	private String id;
	private String reviewProudctResult;
	

	public ProductReviewRunnable(String id) {
		this.id = id;
		this.reviewProduct=new ReviewProduct(); 
	}

	@Override
	public void run() {
		reviewProudctResult = reviewProduct.reviewProudct(id);
	}

	public String getReviewProudctResult() {
		return reviewProudctResult;
	}

}

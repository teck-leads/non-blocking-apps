package com.techleads.app.service.nonblocking;

import java.time.Duration;
import java.time.Instant;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class NonBlockingProductService {



	public void productService(String id) {

		Instant start = Instant.now();
//		Runnable productInfoRunnable = new ProductInfoRunnable(id);
		

//		Runnable revieProductRunnable = new ProductReviewRunnable(id);
		
		ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(id);
		Thread productInfoTD = new Thread(productInfoRunnable);
		ProductReviewRunnable revieProductRunnable = new ProductReviewRunnable(id);
		Thread productReviewTD = new Thread(revieProductRunnable);

		productInfoTD.start();
		productReviewTD.start();
		try {
			productInfoTD.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			productReviewTD.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		String productInfo = productInfoRunnable.getProductInfo();
		System.out.println(productInfo);
		String reviewProudctResult = revieProductRunnable.getReviewProudctResult();
		System.out.println(reviewProudctResult);
		
		Instant finish = Instant.now();
		System.out.println("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());


	}

}

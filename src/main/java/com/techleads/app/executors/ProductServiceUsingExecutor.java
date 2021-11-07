package com.techleads.app.executors;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.service.RetrieveProduct;
import com.techleads.app.service.ReviewProduct;
import com.techleads.app.service.nonblocking.ProductInfoRunnable;
import com.techleads.app.service.nonblocking.ProductReviewRunnable;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProductServiceUsingExecutor {
	@Autowired
	private RetrieveProduct retrieveProduct;
	@Autowired
	private ReviewProduct reviewProduct;
	

	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


	public void productService(String id) {

		Instant start = Instant.now();
		
		Future<String> productInfoFuture = executorService.submit(()->retrieveProduct.productInfo(id));
		Future<String> reviewProudctResultFuture = executorService.submit(()->reviewProduct.reviewProudct(id));
		
//		String productInfo = retrieveProduct.productInfo(id);// blocking call
//		String reviewProudctResult =reviewProduct.reviewProudct(id);// blocking call
		
		try {
//			System.out.println(productInfoFuture.get());
			try {
				System.out.println(productInfoFuture.get(2, TimeUnit.SECONDS));
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(reviewProudctResultFuture.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		Instant finish = Instant.now();
		System.out.println("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());


	}

}

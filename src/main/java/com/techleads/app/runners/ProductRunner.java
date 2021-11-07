package com.techleads.app.runners;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.techleads.app.executors.ProductServiceUsingExecutor;
import com.techleads.app.service.nonblocking.NonBlockingProductService;
@Component
public class ProductRunner implements CommandLineRunner {
	
//	@Autowired
//	private ProductService service;
//	@Autowired
//	private NonBlockingProductService service; //Time Elapsed in Milliseconds: 3, 2, 4
	
	@Autowired
	private ProductServiceUsingExecutor service; //Time Elapsed in Milliseconds: 4

	StopWatch watch = new StopWatch();
	
	@Override
	public void run(String... args) throws Exception {
		String id="1002";
		service.productService(id);

	}

}

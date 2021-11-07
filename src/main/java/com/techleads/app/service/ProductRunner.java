package com.techleads.app.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.techleads.app.service.nonblocking.NonBlockingProductService;
@Component
public class ProductRunner implements CommandLineRunner {
	
//	@Autowired
//	private ProductService service;
	@Autowired
	private NonBlockingProductService service;
	StopWatch watch = new StopWatch();
	
	@Override
	public void run(String... args) throws Exception {
		String id="1002";
		Instant start = Instant.now();
		service.productService(id);
		Instant finish = Instant.now();
		System.out.println("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());

	}

}

package com.techleads.app.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class RetrieveProduct {

	public String productInfo(String id) {
		try {
			TimeUnit.MICROSECONDS.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (id + " Idea Pad Slim 5, Intel 7th Gen, 16GB Ram");
	}

}

package com.techleads.app.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class StringTransformExample {


	public static void main(String[] args) {
		Instant start = Instant.now();
		List<String> resultList=new ArrayList<>();
		List<String> names=DataSet.namesList();
		names.forEach(name->{
			String newValue=addNameLengthTransform(name);
			resultList.add(newValue);
		});
		Instant finish = Instant.now();
		log.info("Final Result "+resultList);
		log.info("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());
		

	}
	
	private static String addNameLengthTransform(String name) {
		try {
			TimeUnit.MILLISECONDS.sleep(500);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return name.length()+" "+name;
	}

}

package com.techleads.app.parrallelstreams;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.techleads.app.service.DataSet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelStringTransformExample {

	public List<String> stringTransform(List<String> namesList) {
		List<String> resultList = namesList.parallelStream()
//				.map(name->addNameLengthTransform(name))
				.map(this::addNameLengthTransform)
//				.sequential()
				.collect(Collectors.toList());
		return resultList;
	}

	public List<String> stringTransform_1(List<String> namesList, boolean isParallel) {
		Stream<String> stream = namesList.stream();

		if (isParallel) {
			stream.parallel();
		}
		return stream.map(this::addNameLengthTransform).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		Instant start = Instant.now();
		List<String> names = DataSet.namesList();
		List<String> resultList = null;
		try {
			ParallelStringTransformExample ste = new ParallelStringTransformExample();
			resultList = ste.stringTransform(names);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Instant finish = Instant.now();
		log.info("Final Result " + resultList);
		log.info("Time Elapsed in Milliseconds: " + Duration.between(start, finish).toMillis());

	}

	public String addNameLengthTransform(String name) {
		try {
			TimeUnit.MILLISECONDS.sleep(500);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return name.length() + "-" + name;
	}

}

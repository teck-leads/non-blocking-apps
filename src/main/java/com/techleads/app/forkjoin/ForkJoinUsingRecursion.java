package com.techleads.app.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import com.techleads.app.service.DataSet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> inputList;

	public ForkJoinUsingRecursion(List<String> inputList) {
		super();
		this.inputList = inputList;
	}

	public static void main(String[] args) {
		Instant start = Instant.now();
		List<String> resultList = new ArrayList<>();
		List<String> names = DataSet.namesList();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinUsingRecursion fjur = new ForkJoinUsingRecursion(names);

		resultList = forkJoinPool.invoke(fjur);
		Instant finish = Instant.now();
		log.info("Final Result " + resultList);
		log.info("Time Elapsed in Milliseconds: " + Duration.between(start, finish).toMillis());

	}

	private static String addNameLengthTransform(String name) {
		try {
			TimeUnit.MILLISECONDS.sleep(500);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return name.length() + " " + name;
	}

	@Override
	protected List<String> compute() {

		if (inputList.size() <= 1) {
			List<String> resultList = new ArrayList<>();
			inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
			return resultList;
		}

		int midPoint = (inputList.size() / 2);
		ForkJoinTask<List<String>> leftInputList = new ForkJoinUsingRecursion(inputList.subList(0, midPoint)).fork();
		inputList = inputList.subList(midPoint, inputList.size());
		List<String> rightResult = compute();// recursion happens
		List<String> leftResult = leftInputList.join();
		leftResult.addAll(rightResult);
		return leftResult;
	}

}

package com.techleads.app.parrallelstreams;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.techleads.app.service.DataSet;

import lombok.extern.slf4j.Slf4j;
@Slf4j
class ParallelStringTransformExampleTest {
	
	ParallelStringTransformExample pst=new ParallelStringTransformExample();

	@Test
	void testStringTransform() {
		//given
		List<String> namesList = DataSet.namesList();
		//when 
		Instant start = Instant.now();
		List<String> stringTransform = pst.stringTransform(namesList);
		Instant finish = Instant.now();
		log.info("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());
		
		//then
		assertEquals(7, stringTransform.size());
		
		stringTransform.forEach(name->{
			assertTrue(name.contains("-"));
		});
		
	}
	
	@ParameterizedTest
	@ValueSource(booleans = {false, true})
	void testStringTransform_1(boolean isParallel) {
		//given
		List<String> namesList = DataSet.namesList();
		//when 
		Instant start = Instant.now();
		List<String> stringTransform = pst.stringTransform_1(namesList, isParallel);
		Instant finish = Instant.now();
		log.info("Time Elapsed in Milliseconds: "+Duration.between(start, finish).toMillis());
		
		//then
		assertEquals(7, stringTransform.size());
		
		stringTransform.forEach(name->{
			assertTrue(name.contains("-"));
		});
		
	}

}

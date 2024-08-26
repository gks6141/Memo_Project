package com.memo;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class lamda {

	@Test
	void 메소드레퍼런스() {
		List<String> fruits = List.of("apple", "banana", "cherry");
		fruits = fruits
		.stream()
		.map(String::toUpperCase) //element -> element.toUpperCase
		.collect(Collectors.toList());
		
		log.info("%%% {}", fruits);
		
	}
}

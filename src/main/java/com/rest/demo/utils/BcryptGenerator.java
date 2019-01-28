package com.rest.demo.utils;

import java.util.stream.Stream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGenerator {

	public static void main(String[] args) {
		String password = "123456";
		Stream.of(true).forEach(System.out::println);
		for (int i = 0; i < 5; i++) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
			System.out.println(passwordEncoder.encode(password));
		}
	}

}

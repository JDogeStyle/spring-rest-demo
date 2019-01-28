package com.rest.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String saludo() {
		return "Hello World!! " + LocalDateTime.now();
	}

}

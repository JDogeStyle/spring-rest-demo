package com.rest.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.model.Login;
import com.rest.demo.service.IUsuarioService;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {
	private final IUsuarioService usuarioService;
	private final PasswordEncoder passwordEncoder;
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
	public LoginController(IUsuarioService usuarioService, PasswordEncoder passwordEncoder,
			InMemoryUserDetailsManager inMemoryUserDetailsManager) {
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
		this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
	}
	
	@PostMapping(path = "/signin", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> signin(@RequestBody @Valid Login login) {
		return usuarioService.buscarxUsername(login.getUsername())
			.map(u -> ResponseEntity.ok(passwordEncoder.matches(login.getPassword(), u.getPassword())))
			.orElse(ResponseEntity.ok(inMemoryUserDetailsManager.userExists(login.getUsername())));
	}
	
}

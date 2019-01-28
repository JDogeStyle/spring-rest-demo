package com.rest.demo.service;

import java.util.Optional;

import com.rest.demo.model.Usuario;

public interface IUsuarioService extends ICrudService<Usuario, Long> {
	
	public boolean existexUsername(String username, String password);
	public Optional<Usuario> buscarxUsername(String username);
	
}

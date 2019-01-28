package com.rest.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.demo.model.Usuario;
import com.rest.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	private final UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public Usuario save(Usuario entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Usuario> findByID(Long id) {
		return repository.findById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public boolean exists(Long id) {
		return repository.existsById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean existexUsername(String username, String password) {
		return repository.existsByUsernameAndPassword(username, password);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Optional<Usuario> buscarxUsername(String username) {
		return repository.findByUsername(username);
	}

}

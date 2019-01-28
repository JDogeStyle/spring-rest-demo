package com.rest.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.demo.model.Role;
import com.rest.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {
	private final RoleRepository repository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public Role save(Role entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Role> findByID(Long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public boolean exists(Long id) {
		return repository.existsById(id);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Role> obtenerRolesxUsuario(Long id) {
		return repository.findRoleByUserID(id);
	}

}

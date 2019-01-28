package com.rest.demo.service;

import java.util.List;

import com.rest.demo.model.Role;

public interface IRoleService extends ICrudService<Role, Long> {
	
	public List<Role> obtenerRolesxUsuario(Long id);

}

package com.rest.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final IUsuarioService usuarioService;
	private final IRoleService roleService;
	
	@Autowired
	public UserDetailsServiceImpl(IUsuarioService usuarioService, IRoleService roleService) {
		this.usuarioService = usuarioService;
		this.roleService = roleService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioService.buscarxUsername(username)
			.map(user -> 
				User.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(authorities(username))
				.disabled(!user.isEstado())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.build()
			)
			.orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));
	}

	private List<GrantedAuthority> authorities(String username) {
		return roleService.obtenerRolesxUsuario(username).stream()
			.map(r -> new SimpleGrantedAuthority("ROLE_" + r.getDescripcion()))
			.collect(Collectors.toList());
	}

}

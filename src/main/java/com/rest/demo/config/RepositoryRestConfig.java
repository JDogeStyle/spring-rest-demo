package com.rest.demo.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import com.rest.demo.model.Categoria;
import com.rest.demo.model.Producto;
import com.rest.demo.model.Role;
import com.rest.demo.model.Usuario;

@Component
public class RepositoryRestConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Producto.class, Categoria.class, Usuario.class, Role.class);
	}
	
}

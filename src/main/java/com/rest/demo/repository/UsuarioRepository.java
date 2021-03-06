package com.rest.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.rest.demo.model.Usuario;

@RepositoryRestResource(path = "usuario", itemResourceRel = "usuario", collectionResourceRel = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@RestResource(path = "exists", rel = "exists")
	public boolean existsByUsernameAndPassword(@Param("uid") String username, @Param("pwd") String password);
	
	@RestResource(path = "name", rel = "name")
	public Optional<Usuario> findByUsername(@Param("uid") String username);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void delete(Usuario entity);
	
}

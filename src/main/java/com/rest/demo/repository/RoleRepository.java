package com.rest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.rest.demo.model.Role;

@RepositoryRestResource(path = "rol", itemResourceRel = "rol", collectionResourceRel = "roles")
public interface RoleRepository extends JpaRepository<Role, Long> {

	@RestResource(path = "roles", rel = "roles")
	@Query("SELECT r FROM Role r JOIN FETCH r.usuarios u WHERE u.username=:uid")
	public List<Role> findRoleByUsername(@Param("uid") String username);
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends Role> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void delete(Role entity);

}

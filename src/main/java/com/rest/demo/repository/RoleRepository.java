package com.rest.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rest.demo.model.Role;

@RepositoryRestResource(path = "rol", itemResourceRel = "rol", collectionResourceRel = "roles")
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r JOIN FETCH r.usuarios u WHERE u.idusuario=:id")
	public List<Role> findRoleByUserID(@Param("id") Long id);

}

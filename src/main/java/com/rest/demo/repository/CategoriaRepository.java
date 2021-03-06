package com.rest.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.rest.demo.model.Categoria;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@RepositoryRestResource(path = "categoria", itemResourceRel = "categoria", collectionResourceRel = "categorias")
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	@RestResource(path = "descripcion", rel = "descripcion")
	public Optional<Categoria> findByDescripcionIgnoreCaseContaining(@Param("desc") String desc);
	
	@Query("SELECT DISTINCT c FROM Categoria c LEFT JOIN FETCH c.productos p WHERE p.codpro = :id")
	public Optional<Categoria> findByProductoId(@Param("id") Long id);
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends Categoria> S save(S entity);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void deleteById(Long id);

}

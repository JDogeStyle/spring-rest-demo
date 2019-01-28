package com.rest.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.rest.demo.model.Producto;

@RepositoryRestResource(path = "producto", itemResourceRel = "producto", collectionResourceRel = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	@Query("SELECT p FROM Producto p JOIN FETCH p.categoria")
	public List<Producto> findByFetchAll();
	
	@Query(
		value = "SELECT p FROM Producto p JOIN FETCH p.categoria",
		countQuery = "SELECT COUNT(p) FROM Producto p JOIN p.categoria"
	)
	public Page<Producto> findByPageAll(Pageable pageable);
	
	@Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE p.codpro = :id")
	public Optional<Producto> findByCodpro(@Param("id") Long id);
	
	@Query(
		value = "SELECT p FROM Producto p JOIN FETCH p.categoria c WHERE c.codcat = :id",
		countQuery = "SELECT COUNT(p) FROM Producto p JOIN p.categoria c WHERE c.codcat = :id"
	)
	public Page<Producto> findByCategoriaId(@Param("id") Long id, Pageable pageable);
	
	@RestResource(path = "descripcion", rel = "descripcion")
	public Optional<Producto> findByDescripcionIgnoreCaseContaining(@Param("desc") String desc);
	
	@Query("SELECT p FROM Producto p WHERE p.cantidad BETWEEN ?1 AND ?2")
	public List<Producto> findByCantidadBetween(int menor, int mayor);
	
	@Query("SELECT p FROM Producto p WHERE p.precio >= :precio")
	public List<Producto> findByPrecioGreaterThan(@Param("precio") Double precio);
	
	@Modifying
	@Query("UPDATE Producto p SET p.descripcion = :desc WHERE p.codpro = :id")
	public int updateByDescripcion(@Param("desc") String desc, @Param("id") Long id);
	
}

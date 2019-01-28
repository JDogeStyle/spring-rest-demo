package com.rest.demo.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rest.demo.model.Categoria;

public interface ICategoriaService extends ICrudService<Categoria, Long> {
	
	public Page<Categoria> findAll(Pageable pageable);
	public Optional<Categoria> buscarxDescripcion(String desc);
	public Optional<Categoria> buscarxProducto(Long id);
	public Categoria actualizarParcial(Map<String, Object> prop, Long id);
	
}

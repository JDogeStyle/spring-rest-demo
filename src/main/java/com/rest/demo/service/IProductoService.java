package com.rest.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rest.demo.model.Producto;

public interface IProductoService extends ICrudService<Producto, Long> {
	
	public Page<Producto> listarProducto(Pageable pageable);
	public Optional<Producto> buscarxDescripcion(String desc);
	public Page<Producto> buscarxCategoria(Long id, Pageable pageable);
	public List<Producto> buscarxCantidad(int menor, int mayor);
	public List<Producto> buscarxPrecio(Double precio);
	public Producto actualizarParcial(Map<String, Object> prop, Long id);
	
}

package com.rest.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.demo.model.Producto;
import com.rest.demo.repository.ProductoRepository;
import com.rest.demo.utils.ConvertMapToEntity;

@Service
public class ProductoServiceImpl implements IProductoService {
	private final ProductoRepository repository;
	
	@Autowired
	public ProductoServiceImpl(ProductoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Producto> findAll() {
		return repository.findByFetchAll();
	}
	
	@Transactional
	@Override
	public Producto save(Producto entity) {
		return repository.save(entity);
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public boolean exists(Long id) {
		return repository.existsById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Optional<Producto> buscarxDescripcion(String desc) {
		return repository.findByDescripcionIgnoreCaseContaining(desc);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Producto> buscarxCantidad(int menor, int mayor) {
		return repository.findByCantidadBetween(menor, mayor);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Producto> buscarxPrecio(Double precio) {
		return repository.findByPrecioGreaterThan(precio);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Optional<Producto> findByID(Long id) {
		return repository.findByCodpro(id);
	}

	@Transactional
	@Override
	public Producto actualizarParcial(Map<String, Object> prop, Long id) {
		return findByID(id)
			.map(p -> {
				p = ConvertMapToEntity.convert(prop, p);
				return repository.save(p);
			})
			.orElseThrow(ResourceNotFoundException::new);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<Producto> buscarxCategoria(Long id, Pageable pageable) {
		return repository.findByCategoriaId(id, pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Producto> listarProducto(Pageable pageable) {
		return repository.findByPageAll(pageable);
	}
	
}

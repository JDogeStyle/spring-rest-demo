package com.rest.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rest.demo.exception.ResourceNotFoundException;
import com.rest.demo.model.Producto;
import com.rest.demo.resource.ProductoResourceAssembler;
import com.rest.demo.service.ICategoriaService;
import com.rest.demo.service.IProductoService;

@RepositoryRestController
public class ProductoController {
	private final IProductoService productoService;
	private final ICategoriaService categoriaService;
	private final ProductoResourceAssembler resourceAssembler;
	
	@Autowired
	public ProductoController(IProductoService productoService, ICategoriaService categoriaService,
		ProductoResourceAssembler resourceAssembler) {
		this.productoService = productoService;
		this.categoriaService = categoriaService;
		this.resourceAssembler = resourceAssembler;
	}

	@GetMapping(path = "/producto", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> getProductos(Pageable pageable, PagedResourcesAssembler<Producto> pageAssembler) {
		return Optional.ofNullable(productoService.listarProducto(pageable))
			.map(pro -> pageAssembler.toResource(pro, resourceAssembler))
			.map(ResponseEntity.ok()::body)
			.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping(path = "/producto/{id}", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> getProducto(@PathVariable("id") Long id) {
		return productoService.findByID(id)
			.map(resourceAssembler::toResource)
			.map(ResponseEntity.ok()::body)
			.orElseThrow(ResourceNotFoundException::new);
	}
	
	@GetMapping(path = "/categoria/{id}/producto", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> getProductoxCategoria(@PathVariable("id") Long id, Pageable pageable, PagedResourcesAssembler<Producto> pageAssembler) {
		return categoriaService.findByID(id)
			.map(p -> productoService.buscarxCategoria(id, pageable))
			.map(p -> pageAssembler.toResource(p, resourceAssembler))
			.map(ResponseEntity.ok()::body)
			.orElseThrow(ResourceNotFoundException::new);
	}
	
}

package com.rest.demo.controller;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.demo.exception.ResourceNotFoundException;
import com.rest.demo.model.Categoria;
import com.rest.demo.resource.CategoriaResource;
import com.rest.demo.resource.CategoriaResourceAssembler;
import com.rest.demo.service.ICategoriaService;
import com.rest.demo.service.IProductoService;

@RepositoryRestController
public class CategoriaController {
	private final IProductoService productoService;
	private final ICategoriaService categoriaService;
	private final CategoriaResourceAssembler resourceAssembler;
	
	@Autowired
	public CategoriaController(IProductoService productoService, ICategoriaService categoriaService,
			CategoriaResourceAssembler resourceAssembler) {
		this.productoService = productoService;
		this.categoriaService = categoriaService;
		this.resourceAssembler = resourceAssembler;
	}
	
	@GetMapping(path = "/categoria", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> getCategorias(Pageable pageable, PagedResourcesAssembler<Categoria> pageAssembler) {
		return Optional.ofNullable(categoriaService.findAll(pageable))
			.map(cat -> pageAssembler.toResource(cat, resourceAssembler))
			.map(ResponseEntity.ok()::body)
			.orElse(ResponseEntity.noContent().build());
	}

	@GetMapping(path = "/categoria/{id}", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> getCategoria(@PathVariable("id") Long id) {
		return categoriaService.findByID(id)
			.map(resourceAssembler::toResource)
			.map(ResponseEntity.ok()::body)
			.orElseThrow(ResourceNotFoundException::new);
	}
	
	@GetMapping(path = "/producto/{id}/categoria", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> getCategoriaxProducto(@PathVariable("id") Long id) {
		return productoService.findByID(id)
			.map(c -> categoriaService.buscarxProducto(id))
			.orElseThrow(ResourceNotFoundException::new)
			.map(resourceAssembler::toResource)
			.map(ResponseEntity.ok()::body)
			.orElseThrow(ResourceNotFoundException::new);
	}
	
	@PostMapping(path = "/categoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> createCategoria(@Valid @RequestBody Categoria cat) {
		return categoriaService.buscarxDescripcion(cat.getDescripcion())
			.map(p -> ResponseEntity.status(HttpStatus.CONFLICT).build())
			.orElseGet(() -> {
				CategoriaResource resource = resourceAssembler.toResource(categoriaService.save(cat));
				URI location = URI.create(resource.getLink(Link.REL_SELF).getHref());
				return ResponseEntity.created(location).body(resource);
			});
	}
	
	@PutMapping(path = "/categoria/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> updateCategoria(@PathVariable("id") Long id, @Valid @RequestBody Categoria cat) {
		return categoriaService.findByID(id)
			.map(c -> categoriaService.save(new Categoria(id, cat.getDescripcion())))
			.map(resourceAssembler::toResource)
			.map(resource -> ResponseEntity.created(URI.create(resource.getLink(Link.REL_SELF).getHref())).body(resource))
			.orElseThrow(ResourceNotFoundException::new);
	}
	
	@PatchMapping(path = "/categoria/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_UTF8_VALUE)
	public ResponseEntity<?> replaceCategoria(@PathVariable("id") Long id, @RequestBody Map<String, Object> prop) {
		return categoriaService.findByID(id)
			.map(c -> categoriaService.actualizarParcial(prop, id))
			.map(resourceAssembler::toResource)
			.map(resource -> ResponseEntity.created(URI.create(resource.getLink(Link.REL_SELF).getHref())).body(resource))
			.orElseThrow(ResourceNotFoundException::new);
	}
	
	@DeleteMapping(path = "/categoria/{id}", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteCategoria(@PathVariable("id") Long id) { 
		return categoriaService.findByID(id)
			.map(c -> {
				categoriaService.deleteById(id);
				return ResponseEntity.noContent().build();
			})
			.orElseThrow(ResourceNotFoundException::new);
    }
	
}

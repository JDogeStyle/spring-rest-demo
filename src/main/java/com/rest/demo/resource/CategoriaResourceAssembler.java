package com.rest.demo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rest.demo.controller.CategoriaController;
import com.rest.demo.controller.ProductoController;
import com.rest.demo.model.Categoria;

@Component
public class CategoriaResourceAssembler extends ResourceAssemblerSupport<Categoria, CategoriaResource> {

	public CategoriaResourceAssembler() {
		super(CategoriaController.class, CategoriaResource.class);
	}

	@Override
	public CategoriaResource toResource(Categoria entity) {
		CategoriaResource resource = instantiateResource(entity);
		resource.add(linkTo(methodOn(CategoriaController.class).getCategoria(entity.getCodcat())).withSelfRel());
		resource.add(linkTo(methodOn(CategoriaController.class).getCategorias(null, null)).withRel("categorias"));
		resource.add(linkTo(methodOn(ProductoController.class).getProductoxCategoria(entity.getCodcat(), null, null)).withRel("productos"));
		resource.setCategoria(entity);
		return resource;
	}
	
	public Resources<CategoriaResource> toResourcesLink(List<Categoria> entities) {
		return new Resources<>(toResources(entities), linkTo(methodOn(CategoriaController.class).getCategorias(null, null)).withRel("categorias"));
	}
	
}

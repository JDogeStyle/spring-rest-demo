package com.rest.demo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rest.demo.controller.CategoriaController;
import com.rest.demo.controller.ProductoController;
import com.rest.demo.model.Producto;

@Component
public class ProductoResourceAssembler extends ResourceAssemblerSupport<Producto, ProductoResource> {

	public ProductoResourceAssembler() {
		super(ProductoController.class, ProductoResource.class);
	}

	@Override
	public ProductoResource toResource(Producto entity) {
		ProductoResource resource = instantiateResource(entity);
		resource.add(linkTo(methodOn(ProductoController.class).getProducto(entity.getCodpro())).withSelfRel());
		resource.add(linkTo(methodOn(ProductoController.class).getProductos(null, null)).withRel("productos"));
		resource.add(linkTo(methodOn(CategoriaController.class).getCategoriaxProducto(entity.getCodpro())).withRel("categoria"));
		resource.setProducto(entity);
		return resource;
	}
	
	public Resources<ProductoResource> toResourcesLink(List<Producto> entities) {
		return new Resources<>(toResources(entities), linkTo(methodOn(ProductoController.class).getProductos(null, null)).withRel("productos"));
	}

}

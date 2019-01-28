package com.rest.demo.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rest.demo.model.Producto;

@Relation(value = "producto", collectionRelation = "productos")
public class ProductoResource extends ResourceSupport {
	
	@JsonUnwrapped
	private Producto producto;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}

package com.rest.demo.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rest.demo.model.Categoria;

@Relation(value = "categoria", collectionRelation = "categorias")
public class CategoriaResource extends ResourceSupport {
	
	@JsonUnwrapped
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}

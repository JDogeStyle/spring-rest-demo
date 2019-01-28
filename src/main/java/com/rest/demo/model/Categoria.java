package com.rest.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="categoria")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long codcat;
	
	@NotNull
	@Size(min=3, max=40)
	@Column(nullable=false, length=45, unique=true)
	private String descripcion;

	//bi-directional many-to-one association to Producto
	@JsonIgnore
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;

	public Categoria() {
	}
	
	public Categoria(Long codcat, @NotNull @Size(min = 3, max = 40) String descripcion) {
		this.codcat = codcat;
		this.descripcion = descripcion;
	}

	public Long getCodcat() {
		return this.codcat;
	}

	public void setCodcat(Long codcat) {
		this.codcat = codcat;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategoria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoria(null);

		return producto;
	}

	@Override
	public String toString() {
		return "Categoria [codcat=" + codcat + ", descripcion=" + descripcion + "]";
	}
}
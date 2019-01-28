package com.rest.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="producto")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long codpro;
	
	@NotNull
	@PositiveOrZero
	@Max(900)
	@Column(nullable=false)
	private int cantidad;
	
	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45, unique=true)
	private String descripcion;
	
	@NotNull
	@FutureOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(nullable=false)
	private LocalDate fecha;

	@NotNull
	@Digits(integer=3, fraction=2)
	@DecimalMax(value="500.00", inclusive=true)
	@DecimalMin(value="1.00", inclusive=true)
	@Column(nullable=false)
	private double precio;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codcat", nullable=false)
	private Categoria categoria;

	public Producto() {
	}
	
	public Producto(Long codpro, @NotNull @Max(99) @Min(1) int cantidad,
			@NotNull @Size(min = 3, max = 40) String descripcion, @NotNull LocalDate fecha,
			@NotNull @DecimalMax(value = "300.00", inclusive = true) @DecimalMin(value = "1.00", inclusive = true) double precio,
			Categoria categoria) {
		this.codpro = codpro;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Long getCodpro() {
		return this.codpro;
	}

	public void setCodpro(Long codpro) {
		this.codpro = codpro;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
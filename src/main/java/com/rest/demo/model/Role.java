package com.rest.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="roles")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long idrol;
	
	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45, unique=true)
	private String descripcion;

	//bi-directional many-to-many association to Usuario
	@JsonIgnore
	@ManyToMany(mappedBy="roles")
	private List<Usuario> usuarios;

	public Role() {
	}

	public Long getIdrol() {
		return this.idrol;
	}

	public void setIdrol(Long idrol) {
		this.idrol = idrol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
package com.rest.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long idusuario;

	@Column(nullable=false, length=45, unique=true)
	private String username;

	@Column(nullable=false, length=8, unique=true)
	private String dni;

	@Column(nullable=false, length=45)
	private String materno;

	@Column(nullable=false, length=45)
	private String nombre;
	
	@JsonIgnore
	@Column(nullable=false, length=60)
	private String password;

	@Column(nullable=false, length=45)
	private String paterno;

	@Column(nullable=false, length=45, unique=true)
	private String telefono;
	
	@Column(nullable=false)
	private boolean estado;
	
	//bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="user_roles"
		, joinColumns={
			@JoinColumn(name="idusuario", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idrol", nullable=false)
			}
		)
	private List<Role> roles;

	public Usuario() {
	}
	
	public Usuario(Usuario user) {
		this.idusuario = user.idusuario;
		this.username = user.username;
		this.password = user.password;
		this.nombre = user.nombre;
		this.dni = user.dni;
		this.paterno = user.paterno;
		this.materno = user.materno;
		this.telefono = user.telefono;
		this.estado = user.estado;
		this.roles = user.roles;
	}

	public Long getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
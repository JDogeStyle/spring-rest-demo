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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="usuario")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(11);

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long idusuario;

	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45, unique=true)
	private String username;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min=60, max=60)
	@Column(nullable=false, length=60)
	private String password;

	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45)
	private String nombre;

	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45)
	private String paterno;

	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45)
	private String materno;

	@NotNull
	@Size(min=3, max=45)
	@Column(nullable=false, length=45, unique=true)
	private String telefono;
	
	@NotNull
	@Pattern(regexp="^\\d{8}$")
	@Column(nullable=false, length=8, unique=true)
	private String dni;

	@NotNull
	@Column(nullable=false)
	private boolean estado = true;
	
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
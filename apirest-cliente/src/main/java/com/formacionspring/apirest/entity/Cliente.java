//POSTMAN SE USA PARA PROBAR APIS


package com.formacionspring.apirest.entity;
//spring.jpa.hibernate.ddl-auto= create-drop cuando se cierre el servicio se borra la tabla
import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //esta clase tiene la estructura de la tabla
@Table(name = "clientes")  //esta es la tabla que llevaria la informacion de los entity
						   //es para nombrar la tabla o mejor dicho renombrarla
public class Cliente implements Serializable{  //cuando tiene la palabra implements es una interface cuando vemos
											   // esta palabra , se usa para generar
											   //un id unico para cada tabla
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // se usa para volver el id autoincremental
	private long id;
	//se puede pasar de la siguiente manera para el nombre de columnas
	@Column(name = "nombre")//el column sirve para cambiar el nombre de la columna
	private String nombre;//afecta el @column afecta a la siguiente linea de codigo
	private String apellido;
	private String email;
	private int telefono;
	@Column(name ="create_at") //en la base de datos se cambiaria a este nombre
	private Date createAt;
	
	
	//para que funciones completamente como entity es obligatorio que tenga
	//los setters y getters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

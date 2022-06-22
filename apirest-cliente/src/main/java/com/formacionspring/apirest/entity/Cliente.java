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
	//el column sirve para cambiar el nombre de la columna
	@Column(nullable = false)  //no deja pasar el campo en blanco a la bd
	private String nombre;//afecta el @column afecta a la siguiente linea de codigo
	@Column(nullable = false)//este campo lo dejamos igual para no pasar en blanco
	private String apellido;
	@Column(nullable =false,unique =true) //no deja registrar dos correos iguales y tampoco en blanco
	private String email;
	private int telefono;
	@Column(name ="create_at") //en la base de datos se cambiaria a este nombre
	private Date createAt;//este es el campo de la fecha
	
	//para poder subir imagenes
	//se crean los setters and getters
	//crear la carpeta uploads
	//luego nos vamos al controlador
	//en el controler se hace el postmapping con la revision de errores
	private String imagen;
	
	
	
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
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
}

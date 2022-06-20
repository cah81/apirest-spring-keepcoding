package com.formacionspring.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.apirest.entity.Cliente;

//aqui se especifican las funciones que se van hacer con la base de datos
//en este caso se esta haciendo un crud
//dao significa Data access Object
//al heredarlo pide dos datos la clase cliente y el tipo que se declaro el id dentro de la clase
//ESTO SERIA UN REPOSITORIO DE FUNCIONES LA CLASE CRUD REPOSITORY CONTIENE
//FUNCIONES BASICAS QUE NOS PERMITE HACER UN CRUD SOBRE UNA BASE DE DATOS
//esto se puede implementar gracias al JPA

@Repository  //se le debe indicar a spring que es un repositorio

public interface ClienteDao extends CrudRepository<Cliente, Long>{ // aqui se indica el entity y el tipo
	//de id <Entity, tipo Id>
	
	

}

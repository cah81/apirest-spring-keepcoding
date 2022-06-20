package com.formacionspring.apirest;

/*
 * 1.creamos la entidad le dimos todas las anotaciones representa base datos en java
2.Creamos un repositorio que tiene las funciones para manipular la bbdd
3.para ello creamos una interface en este caso se aplica son la entidad que se tiene se heredad de CrudRepository
4.Se implmeneta el servicio a atraves de una interface se crea un metodo abstracto
5.se implementa en la clase se le dice con anotacion que es un servicio
6.se hace la inyeccion de dependencia del dao que tiene las funciones para el crud
7.
 * 
 * 
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApirestClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestClienteApplication.class, args);
	}

}

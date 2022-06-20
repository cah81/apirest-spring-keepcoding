package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.entity.Cliente;

//para los servicios siempre se implementan interface
// y se declaran los metodos
public interface ClienteService {
	
	//se coloca asi por que es abstracto no se define con llaves ya que no
	//se sabe como funciona adentro se hace asi por que en la definicion de 
	//interfaces siempre tiene que tener metodos abstractos
	
	
	//metodo para mostrar todos los cliented
	public List<Cliente> mostrarTodos();
	//metodo para mostrar cliente por id
	public Cliente mostrarporId(Long id);
	//metodo para guardar cliente
	public Cliente guardar(Cliente cliente);
	//metodo para borrar cliente
	public void borrar(Long id);
	

}

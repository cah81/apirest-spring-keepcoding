package com.formacionspring.apirest.service;
//una vez se crea la interface del servicio
// se crea esta clase donde se va a implementar el servicio
//a spring hay que decirle que esto es un servicio

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.repository.ClienteDao;

//aqui vamos a implementar la interface de servicio creada
//al principio marca roja por que hay implementar el metodo abstracto que esta
//solo declarado , si implemento una interfaces es obligatorio implementar 
//el metodo que se declaro
@Service
public class ClientServiceImpl implements ClienteService {

	//llamo a clienteDao se puede implmentar por que ya tiene la anotacion component
	//se puede implmentar en cualquier lado dentro del contenedor principal
	@Autowired
	
	private ClienteDao clienteDao;
	
	
	
	
	
	//para mejorar rendimiento a 
	//la hora de consultas
	//aqui debemos listar todos los clientes que tenga para ello usamos del repositorio
	//dao que tiene las funciones para la base de datos
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> mostrarTodos() {
		// TODO Auto-generated method stub
		
		//cast se hace una revalidacion de lo que se va retornar
		//en este caso vamos retornar de tipo cliente
		return (List<Cliente>) clienteDao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Cliente mostrarporId(Long id) {
		
		return clienteDao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Cliente guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}


	@Override
	@Transactional
	public void borrar(Long id) {
		 clienteDao.deleteById(id);
		
	}

}

package com.formacionspring.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.service.ClienteService;

//primer paso se le indica a spring que era un restcontroller
@RestController
@RequestMapping("/api") //por sobre todas la urls debe pasarse esta pagina
public class ClienteRestController {

	//implementamos un metodo para mostrar y con un getmapping
	//le decimos a donde se va a conectar
	@Autowired
	//implementamos el servivio
	private ClienteService servicio;
	
	//para definir donde se va mostrar le pasamos
	//@getmapping
	//sepueden pasar varia paginas para una misma funcion
	//@GetMapping("/clientes")
	
	
	//metodo para mostrar todos los clientes
	
	@GetMapping({"/clientes","/"})// al poner la barra sola 
	//solo coloca se coloca en el navegador localhost:8087
	// o tambien localhost:8087/clientes
	public List<Cliente> index(){
		return servicio.mostrarTodos();
		//los resultados los devuelve en formato json al ser un 
		//servicio REST
	}
	//metodo para mostrar un cliente por id
	@GetMapping("/clientes/{id}") //la informacion se recibe por la barra de direcciones
	public Cliente show(@PathVariable long id) { // se usa el pathvariable y el tipo id y el
		//numero de id se pasa desde el url aqui
		return servicio.mostrarporId(id); // 
	}
	//metodo para crear un nuevo cliente
	//se le envia a la pagina clientes a traves del requestbody la informacion del objeto cliente
	@PostMapping("/clientes")
	
	public Cliente create(@RequestBody Cliente cliente) { //para pasar la informacion a traves de una clase usamos el requestbody
		return servicio.guardar(cliente);
	}
	//se debe pasar el cuerpo modificado y el id correspondiente
	//para actualizar un dato en especifico
	@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente,@PathVariable Long id) {
		//buscar al registro por id y guardo el objeto en clienteupdate
		Cliente clienteUpdate = servicio.mostrarporId(id);
		//aqui actualizo los datos de clienteupdate por el modelo
		//recibido en requestbody
		clienteUpdate.setNombre (cliente.getNombre());
		clienteUpdate.setApellido (cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreateAt(cliente.getCreateAt());
		
		//guardo y retorno los datos actualizados
		return servicio.guardar(clienteUpdate) ;
		
	}
	
	//pasamos el id del navegador  a traves del path variable
	@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		servicio.borrar(id);
	}
	//si quiero que me indique el objeto que se esta borrando
	
	@DeleteMapping("/clientes2/{id}")
	public Cliente delete2(@PathVariable Long id) {
		Cliente clienteDelete = servicio.mostrarporId(id);
		servicio.borrar(id);
		return clienteDelete;
	}
	
	//solucion del profesor
	
	@DeleteMapping("/clientes3/{id}")
	public Cliente delete3(@PathVariable Long id) {
		Cliente clienteBorrado = servicio.mostrarporId(id);
		servicio.borrar(id);
		return clienteBorrado;
	}
	
}

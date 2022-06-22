package com.formacionspring.apirest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
//	@GetMapping("/clientes/{id}") //la informacion se recibe por la barra de direcciones
//	public Cliente show(@PathVariable long id) { // se usa el pathvariable y el tipo id y el
		//numero de id se pasa desde el url aqui
//		return servicio.mostrarporId(id); // 
//	}
	//metodo para crear un nuevo cliente
	//se le envia a la pagina clientes a traves del requestbody la informacion del objeto cliente
	@PostMapping("/clientes")
	//este codigo no mostraba el resultado de la operacion
	//public Cliente create(@RequestBody Cliente cliente) { //para pasar la informacion a traves de una clase usamos el requestbody
	//	return servicio.guardar(cliente);
	//==============================================================
	//con este codigo indica si fue creado correctamente o tiene errores
	//==============================================================
	//aqui debe devolver un ResponseEntity
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		//el ? es por que no se sabe si devuelve el error o el objeto
        Cliente clienteNew = null;
        Map<String,Object>  response = new HashMap<>(); //usamos para traer mensaje, error
        try {
        	 clienteNew =  servicio.guardar(cliente);
	} catch (DataAccessException e) {
		response.put("mensaje", "Error al realizar en base de datos");
        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
        response.put("mensaje","El cliente ha sido creado con éxito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//se debe pasar el cuerpo modificado y el id correspondiente
	//para actualizar un dato en especifico
	/*@PutMapping("/clientes/{id}")
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
		
	}*/
	
	@PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente
            ,@PathVariable Long id) {

        Cliente clienteUpdate =  servicio.mostrarporId(id);
        Map<String,Object>  response = new HashMap<>();


        if(clienteUpdate == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {


            clienteUpdate.setNombre(cliente.getNombre());
            clienteUpdate.setApellido(cliente.getApellido());
            clienteUpdate.setEmail(cliente.getEmail());
            clienteUpdate.setTelefono(cliente.getTelefono());
            clienteUpdate.setCreateAt(cliente.getCreateAt());

            //guardo y retorno los datos actualizados
            servicio.guardar(clienteUpdate);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido actualizado con éxito");
        response.put("cliente", clienteUpdate);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }
	
	
	
	
	
	
	//pasamos el id del navegador  a traves del path variable
	//@DeleteMapping("/clientes/{id}")
	//public void delete(@PathVariable Long id) {
	//	servicio.borrar(id);
	//}
	//si quiero que me indique el objeto que se esta borrando
	
	@DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Cliente clienteBorrado = servicio.mostrarporId(id);
        Map<String,Object>  response = new HashMap<>();

        if(clienteBorrado == null) {
            response.put("mensaje","No existe el registro con id:"+id);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try {

            servicio.borrar(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido eliminado con éxito");
        response.put("cliente", clienteBorrado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);


    }
	//como vamos a subir un arhivo es crear un archivo nuevo por eso usamos esta anotacion
	@PostMapping("/clientes/uploads")
	//para archivos se maneja una anotacion especial llamada @RequestParam
	//multipartfile es el manejador de archivos en java
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") long id){
		Map<String,Object>  response = new HashMap<>();
		Cliente cliente = servicio.mostrarporId(id); //busco al cliente con el id
		//pregunto si esta vacio
		if(!archivo.isEmpty()) {
			//guardamos el nombre del archivo en esta variable
			//debido a que no nos permite grabar archivos con el mismo nombre le vamos agregar
			//una clase especial que genera id random para las imagenes
			//String nombreArchivo = archivo.getOriginalFilename();
			//se remplaza por este codigo a continuacion:
			
			String nombreArchivo =  UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
			//se guarda el archivo en esta ruta
			 //guardamos la ruta completa uploads/nombredelaimagen lo guardamos en
            //una variable de tipo path que es de java.io
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				//copiamos el archivo fisico a la ruta que definimos en Path
				Files.copy(archivo.getInputStream(), rutaArchivo);
			}catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			 //guardamos el nombre de la imagen
				cliente.setImagen(nombreArchivo);
	            //registramos en base de datos
	            servicio.guardar(cliente);
	            response.put("cliente", cliente);
	            response.put("mensaje","Imagen subida correctamente :"+nombreArchivo);
			}
		
		    //en el postman para enviar en el key se coloca archivo y el id que fueron los 
			//que definimos al principio de este codigo se coloca en  form-data  la que tiene 
			//archivo se coloca tipo File y en value se sube la foto y en id se coloca uno existente
		    //para agregar imagenes mas pesadas se agregaron lineas de codigo en el properties
		    //en el postman se observan errores si envias archivos con el mismo nombre
			//por lo que podemos borrar los archivos que se colocan en uploads
		
		
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
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
	
	//con manejo de errores
	//metodo para mostrar un cliente por id
	@GetMapping("/clientes/{id}")
	//el responsse es para enviar mas mensajes devuelve cualquier objeto
    public ResponseEntity<?> show(@PathVariable long id) {
    //el ResponseEntity es una clase especial para manejo de errores
        Cliente cliente = null;
        Map<String,Object>  response = new HashMap<>();

        try {

            cliente = servicio.mostrarporId(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(cliente == null) {
            response.put("mensaje", "El cliente con ID: "+id+" no existe en la base de datos");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
    }
			
}

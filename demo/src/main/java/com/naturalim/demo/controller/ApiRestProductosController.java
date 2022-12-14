package com.naturalim.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.naturalim.demo.service.IProductosService;
import com.naturalim.demo.controlador.dto.ProductosDTO;
import com.naturalim.demo.models.Productos;
import com.naturalim.demo.models.Response;


@RestController
@RequestMapping("/api")
public class ApiRestProductosController {
	
	@Autowired
	private IProductosService IpService;
	
	
	@GetMapping("/listar")
	public Response<Productos> listarProductos(){				
		Response<Productos> resProductos = IpService.ObtenerListaProductos();
		return resProductos;
	}
	
	
	@PutMapping("/crear")
	public Response<Productos> crearProductos(@RequestBody ProductosDTO productosDto){	
		Response<Productos> response = new Response<>();
		
		try {
			response = IpService.CrearProductosAPI(productosDto);			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("¡Se produjo un error al intentar crear el producto!");
			response.setMensajeError(e.getMessage());
		}		
		return response;
	}
	
	@PutMapping("/actualizar/{id}")
	public Response<Productos> actualizarProductos(@RequestBody ProductosDTO productos, @PathVariable int id){	
		Response<Productos> response = new Response<>();
		try {
			productos.setIdProducto(IpService.EditarProductos(id).getData().getIdProducto());
			response = IpService.CrearProductosAPI(productos);			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("¡Se produjo un error al intentar actualizar el producto alimentario!");
			response.setMensajeError(e.getMessage());
		}
		
		return response;
	} 
	
	@DeleteMapping("/eliminar/{idProductosEliminar}")
	public Response<Productos> eliminarProductos(@PathVariable int idProductosEliminar){		
		Response<Productos> response = IpService.EliminarProductos(idProductosEliminar);	
		return response;
	}
}
package com.naturalim.demo.service;

import org.springframework.web.multipart.MultipartFile;
import com.naturalim.demo.controlador.dto.ProductosDTO;
import com.naturalim.demo.models.Productos;
import com.naturalim.demo.models.Response;

public interface IProductosService {
	
	public Response<Productos> ObtenerListaProductos();
	
	public Response<Productos> CrearProductos(Productos productos, MultipartFile fileProductos );
	
	public Response<Productos> CrearProductosAPI(ProductosDTO productos);
	
	public Response<Productos> EditarProductos(Integer id);
	
	public Response<Productos> EliminarProductos(Integer id);


}

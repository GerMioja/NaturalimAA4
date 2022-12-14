package com.naturalim.demo.service;

import com.naturalim.demo.models.Response;
import com.naturalim.demo.models.Rol;


public interface IRolService {
	
	public Response<Rol> ObtenerListaRol();
	
	public Response<Rol> CrearRol(Rol rol);
	
	public Response<Rol> EditarRol(Long id);
}

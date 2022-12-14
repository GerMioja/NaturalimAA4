package com.naturalim.demo.service;

import com.naturalim.demo.models.Sugerencias;
import com.naturalim.demo.models.Response;

public interface ISugerenciasService {
	
	public Response<Sugerencias> ObtenerListaSugerencias();
	
	public Response<Sugerencias> CrearSugerencias(Sugerencias sugerencias);
	
	public Response<Sugerencias> EditarSugerencias(Integer id);
	
	public Response<Sugerencias> EliminarSugerencias(Integer id);
}

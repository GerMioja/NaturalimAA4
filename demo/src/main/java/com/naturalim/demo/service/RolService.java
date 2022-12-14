package com.naturalim.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.naturalim.demo.interfaces.IRol;
import com.naturalim.demo.models.Response;
import com.naturalim.demo.models.Rol;


@Component
public class RolService implements IRolService {
	@Autowired
	private IRol rolRepository;
	
	public Response<Rol>ObtenerListaRol(){
		
		Response<Rol> response = new Response<>();
		try {
			response.setEstado(true);
			response.setListData((List<Rol>) rolRepository.findAll());
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}
	
	@Override
	public Response<Rol> CrearRol(Rol rol) {
		
		Response<Rol> response = new Response<>();

		try {
			Rol p = rolRepository.save(rol);
			response.setEstado(true);
			response.setData(p);
			response.setListData((List<Rol>) rolRepository.findAll());
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}
	
	@Override
	public Response<Rol> EditarRol(Long id) {
		Response<Rol> response = new Response<>();

		try {
			Optional<Rol> p = rolRepository.findById(id);

			response.setEstado(true);
			response.setData(p.get());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}
}

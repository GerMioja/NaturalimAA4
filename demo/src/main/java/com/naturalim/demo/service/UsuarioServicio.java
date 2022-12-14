package com.naturalim.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.naturalim.demo.controlador.dto.UsuarioRegistroDTO;
import com.naturalim.demo.models.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}

package com.naturalim.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.naturalim.demo.models.Response;
import com.naturalim.demo.models.Rol;
import com.naturalim.demo.service.IRolService;

@Controller
@RequestMapping("/app")
@SessionAttributes("rol")
public class RolController {
	@Value("${titlePage.param}")
	private String titlePage;
	
	@Value("${mensaje.param}")
	private String mensaje;
	
	@Autowired
	private IRolService IpService;
	
	@GetMapping("/rol")
	public String ListarRoles(Model model) {
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("mensaje", mensaje);
		Response<Rol> response = IpService.ObtenerListaRol();
		
		if (response.getEstado()) {
			model.addAttribute("ListaRol", response.getListData());
			return "rol";
		} else {
			model.addAttribute("errores", response.getMensajeError());
			return "errores";
		}
	}
	
	@GetMapping("/rform")
	public String FormularioRol(Model model) {

		Rol rol = new Rol();

		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Naturalim - Registro de Sugerencias");

		model.addAttribute("rol", rol);

		return "rolForm";
	}
	
	@PostMapping("/rNuevoform")
	public String CrearFormulario(@Validated Rol rol, BindingResult br, Model model, SessionStatus status) {

		if (br.hasErrors()) {
			
			return "rolForm";
		}
		model.addAttribute("titlePage", titlePage);
		
		Response<Rol> response = IpService.CrearRol(rol);

		if (response.getEstado()) {
			model.addAttribute("titulo", "Roles en Naturalim");
			model.addAttribute("ListaRol", response.getListData());

			status.setComplete();
			return "rol";
		} else {
			model.addAttribute("errores", response.getMensajeError());

			status.setComplete();
			return "errores";
		}
	}
	
	@GetMapping("/rEditar/{id}")
	public String editarRol(@PathVariable long id, Model model) {
		
		model.addAttribute("titlePage", titlePage);
		Response<Rol> response = IpService.EditarRol(id);

		if (response.getEstado()) {		
			model.addAttribute("rol", response.getData());
			model.addAttribute("titulo", "Naturalim - Edicion de Rol");
			return "rolForm";
		} else {
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());

			return "errores";
		}
	}
}

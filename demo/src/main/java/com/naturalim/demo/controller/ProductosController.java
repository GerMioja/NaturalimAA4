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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.naturalim.demo.models.Productos;
import com.naturalim.demo.models.Response;
import com.naturalim.demo.service.IProductosService;

@Controller
@RequestMapping("/app")
@SessionAttributes("productos")
public class ProductosController {
	
	@Value("${titlePage.param}")
	private String titlePage;
	
	@Value("${mensaje.param}")
	private String mensaje;
	
	@Autowired 
	private IProductosService IpService;
	
	@GetMapping("/productos")
	public String ListarProductos(Model model) {
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("mensaje", mensaje);
		Response<Productos> response = IpService.ObtenerListaProductos();
		
		if (response.getEstado()) {
			model.addAttribute("titulo", "Productos Naturalim");
			model.addAttribute("ListaProductos", response.getListData());
			return "productos";
		} else {
			model.addAttribute("titulo", "Spring Framework - Control de Errores");
			model.addAttribute("errores", response.getMensajeError());
			return "errores";
		}
	}
	
	@GetMapping("/form")
	public String FormularioProductos(Model model) {

		Productos productos = new Productos();

		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Naturalim - Registro de Productos");

		model.addAttribute("productos", productos);

		return "productosForm";
	}
	
	@PostMapping("/Nuevoform")
	public String CrearFormulario(@Validated Productos productos, BindingResult br, Model model,@RequestParam("fileImagen") MultipartFile imgProducto, SessionStatus status) {

		if (br.hasErrors()) {
			return "productosForm";
		}
		
		if (imgProducto.isEmpty() && productos.getUriImagen() == null) {
			model.addAttribute("titlePage", titlePage);
			return "productosForm";
		}
		
		model.addAttribute("titlePage", titlePage);
		Response<Productos> response = IpService.CrearProductos(productos, imgProducto);

		if (response.getEstado()) {
			model.addAttribute("ListaProductos", response.getListData());
			model.addAttribute("respuesta", response.getMensaje());

			status.setComplete();
			return "productos";
		} else {
			
			if (response.getMensaje().equals("IMG-ERROR")) {
				return "productosForm";
			}else {
				model.addAttribute("respuesta", response.getMensaje());
				model.addAttribute("errores", response.getMensajeError());
	
				status.setComplete();
				return "errores";
			}
		}
	}
	
	@GetMapping("/Editar/{id}")
	public String editarProductos(@PathVariable int id, Model model) {
		
		model.addAttribute("titlePage", titlePage);
		Response<Productos> response = IpService.EditarProductos(id);

		if (response.getEstado()) {
			model.addAttribute("titulo", "Naturalim - "+response.getMensaje());			
			model.addAttribute("productos", response.getData());
			return "productosForm";
		} else {
			model.addAttribute("titulo", "Naturalim - Control de Errores");
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());

			return "errores";
		}
	}
	
	@GetMapping("/Eliminar/{id}")
	public String eliminarProductos(@PathVariable int id, Model model) {
		
		model.addAttribute("titlePage", titlePage);
		Response<Productos> response = IpService.EliminarProductos(id);

		if (response.getEstado()) {
			model.addAttribute("titulo", "Naturalim - "+response.getMensaje());	
			model.addAttribute("respuesta", response.getMensaje());
			return "redirect:/app/productos";
		} else {
			model.addAttribute("titulo", "Naturalim - Control de Errores");
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());

			return "errores";
		}		
	}
	
}

package com.naturalim.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class HomeController {
	
	@Value("${title.param}")
	private String title;
	
	@Value("${mensaje.param}")
	private String mensaje;
	
	@Value("${slogan.param}")
	private String slogan;
	
	@Value("${nosotros.param}")
	private String nosotros;
	
	@Value("${objetivo.param}")
	private String objetivo;
	
	@Value("${vision.param}")
	private String vision;
	
	@Value("${mision.param}")
	private String mision;
	
	@GetMapping("/home")
	public String Home(Model model) {
		
		model.addAttribute("title", title);
		model.addAttribute("slogan", slogan);
		model.addAttribute("nosotros", nosotros);
		model.addAttribute("objetivo", objetivo);
		model.addAttribute("mision", mision);
		model.addAttribute("vision", vision);
		model.addAttribute("mensaje", mensaje);
				
		return "home";
	}

}
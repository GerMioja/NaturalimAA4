package com.naturalim.demo.service;

import org.springframework.web.multipart.MultipartFile;
import com.naturalim.demo.models.ResponseFile;

public interface IFileGeneric {
	
	public ResponseFile guardarFile(MultipartFile genericFile);		
	public ResponseFile guardarFileAPI(String fileBase64,String nombreExtImagen);	
	public ResponseFile eliminarFile(String nombreFile);
	
}

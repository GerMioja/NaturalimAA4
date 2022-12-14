package com.naturalim.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sugerencias")
public class Sugerencias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSugerencia;
	private String contenido;
	private String fecha;
	private String estado;
	
	
	public int getIdSugerencia() {
		return idSugerencia;
	}
	public void setIdSugerencia(int idSugerencia) {
		this.idSugerencia = idSugerencia;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

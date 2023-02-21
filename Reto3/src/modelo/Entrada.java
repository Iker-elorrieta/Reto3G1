package modelo;

import java.util.Date;
import java.util.Objects;

public class Entrada {
	
	
	private String cdEntrada;
	private Sesion sesion;
	private float precio;
	private Date fecha;
	private String hora;
	
	@Override
	public String toString() {
		return cdEntrada + "#" + sesion.toString() + "#" + precio
				+ "@";
	}
	public String getCdEntrada() {
		return cdEntrada;
	}
	public void setCdEntrada(String cdEntrada) {
		this.cdEntrada = cdEntrada;
	}
	
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date date) {
		this.fecha = date;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return Objects.equals(cdEntrada, other.cdEntrada);
	}
	
	
}

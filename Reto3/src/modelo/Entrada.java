package modelo;

import java.sql.Date;
import java.util.Objects;

public class Entrada {
	String cdEntrada;
	Date fecha;
	Pelicula  película;
	String horarios;
	Sala sala;
	float precio;
	
	public String getCdEntrada() {
		return cdEntrada;
	}
	public void setCdEntrada(String cdEntrada) {
		this.cdEntrada = cdEntrada;
	}
	public Date getFecha() {
		return fecha;
	}
	
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Pelicula getPelícula() {
		return película;
	}
	public void setPelícula(Pelicula película) {
		this.película = película;
	}
	public String getHorarios() {
		return horarios;
	}
	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
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
	@Override
	public String toString() {
		return cdEntrada + "#" + fecha + "#" + película + "#" + horarios + "#" + sala + "#" + precio + "@";
	}
	
}

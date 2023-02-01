package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Sesion {
	String idSesion;
	Pelicula pelicula;
	Date fecha;
	Hora hora;
	
	@Override
	public String toString() {
		
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");   
		return  idSesion + "#" + pelicula + "#" + dt.format(fecha) + "#" + hora + "@";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		return Objects.equals(idSesion, other.idSesion);
	}
	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setHora(Hora hora) {
		this.hora = hora;
	}
	public Hora getHora() {
		return hora;
	}
	

}

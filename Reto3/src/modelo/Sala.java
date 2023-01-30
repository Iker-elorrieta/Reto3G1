package modelo;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

public class Sala {
	String nomSala;
	String cdSala; 
	Pelicula[] peliculas;
	Date[] fechas; 

	
	public String getNomSala() {
		return nomSala;
	}
	public void setNomSala(String nomSala) {
		this.nomSala = nomSala;
	}
	
	public String getCdSala() {
		return cdSala;
	}
	public void setCdSala(String cdSala) {
		this.cdSala = cdSala;
	}
	public Pelicula[] getPelicula() {
		return peliculas;
	}
	public void setPelicula(Pelicula[] peliculas) {
		this.peliculas = peliculas;
	}
	public Date[] getFecha() {
		return fechas;
	}
	public void setFecha(Date[] fechas) {
		this.fechas = fechas;
	}
	@Override
	public String toString() {
		return  cdSala+ "#" + nomSala + "#" + Arrays.toString(peliculas)+ "#" + Arrays.toString(fechas) + "@";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return Objects.equals(cdSala, other.cdSala);
	}

}

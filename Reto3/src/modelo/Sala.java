package modelo;

import java.util.Objects;

public class Sala {
	String nomSala;
	String cdSala; 
	Pelicula pelicula;
	String fecha; 
	String hora; 
	
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
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
		Sala other = (Sala) obj;
		return Objects.equals(cdSala, other.cdSala) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(hora, other.hora) && Objects.equals(nomSala, other.nomSala);
	}

}

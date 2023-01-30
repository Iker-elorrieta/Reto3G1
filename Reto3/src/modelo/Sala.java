package modelo;

public class Sala {
	private String nomSala;
	private String cdSala; 
	private Pelicula[] peliculas;
	private String fecha; 
	private String hora; 
	
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
	public void setPeliculas(Pelicula[] pelicula) {
		this.peliculas = pelicula;
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

}

package modelo;

import java.util.Objects;

public class Pelicula {
	private float precio;
	private String cdPel;
	private String nombre;
	private int duracion;
	private String genero; 
	
	public String getCdPel() {
		return cdPel;
	}

	public void setCdPel(String cdPel) {
		this.cdPel = cdPel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duración) {
		this.duracion = duración;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
		Pelicula other = (Pelicula) obj;
		return Objects.equals(cdPel, other.cdPel);
	}

	@Override
	public String toString() {
		return  cdPel+ "#" +nombre+ "#" +duracion+ "#" +genero+ "#" +precio+ "@";
	}

	

}

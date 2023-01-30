package modelo;

import java.util.Objects;

public class Pelicula {
	private float precio;
	private String cdPel;
	private String nombre;
	private float duración;
	private String género; 
	
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

	public float getDuración() {
		return duración;
	}

	public void setDuración(float duración) {
		this.duración = duración;
	}

	public String getGénero() {
		return género;
	}

	public void setGénero(String género) {
		this.género = género;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cdPel, duración, género, nombre, precio);
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
	

}

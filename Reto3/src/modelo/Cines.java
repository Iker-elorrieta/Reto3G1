package modelo;

import java.util.Objects;

public class Cines {

	@Override
	public int hashCode() {
		return Objects.hash(cod_cine);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cines other = (Cines) obj;
		return Objects.equals(cod_cine, other.cod_cine);
	}
	@Override
	public String toString() {
		return  cod_cine + "#" + nombre_cine +"@";
	}
	public String getCod_cine() {
		return cod_cine;
	}
	public void setCod_cine(String cod_cine) {
		this.cod_cine = cod_cine;
	}
	public String getNombre_cine() {
		return nombre_cine;
	}
	public void setNombre_cine(String nombre_cine) {
		this.nombre_cine = nombre_cine;
	}
	
	private String cod_cine;
	private String nombre_cine;
	
	
	
	
	
	
	
	
	
	
	
}

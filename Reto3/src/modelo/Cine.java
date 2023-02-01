package modelo;

import java.util.Objects;

public class Cine {
	private String cdCine;
	private String nombre;
	private Sala[] salas;
	

	@Override
	public int hashCode() {
		return Objects.hash(cdCine);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cine other = (Cine) obj;
		return Objects.equals(cdCine, other.cdCine);
	}
	@Override
	public String toString() {
		
		String cdSalas = "";
		if (salas != null) {
			for (int i=0;i < salas.length;i++) {
				cdSalas+=salas[i].getCdSala()+", ";
			}
		}
		
		return  cdCine + "#" + nombre + "#" + cdSalas+"@";
	}
	public String getCod_cine() {
		return cdCine;
	}
	public void setCod_cine(String cdCine) {
		this.cdCine = cdCine;
	}
	public String getNombre_cine() {
		return nombre;
	}
	public void setNombre_cine(String nombre) {
		this.nombre = nombre;
	}
	public Sala[] getSalas() {
		return salas;
	}
	public void setSalas(Sala[] salas) {
		this.salas = salas;
	}
	
	
	
	
	
	
	
	
	
	
	
}

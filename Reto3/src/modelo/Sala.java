package modelo;

import java.util.Arrays;
import java.util.Objects;

public class Sala {
	String nomSala;
	String cdSala; 
	Sesion[] sesiones;
	

	
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

	
	@Override
	public String toString() {
		return  cdSala+ "#" + nomSala + "#" + Arrays.toString(sesiones)+ "@";
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

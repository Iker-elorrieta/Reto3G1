package modelo;


import java.util.Objects;

public class Entrada {
	String cdEntrada;
	Sesion[] sesiones;
	float precio;
	
	public String getCdEntrada() {
		return cdEntrada;
	}
	public void setCdEntrada(String cdEntrada) {
		this.cdEntrada = cdEntrada;
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
	
	
}

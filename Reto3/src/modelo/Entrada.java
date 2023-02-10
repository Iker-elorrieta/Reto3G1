package modelo;


import java.util.Arrays;
import java.util.Objects;

public class Entrada {
	@Override
	public String toString() {
		return cdEntrada + "#" + Arrays.toString(sesiones) + "#" + precio
				+ "@";
	}
	private String cdEntrada;
	private Sesion[] sesiones;
	private float precio;
	
	public String getCdEntrada() {
		return cdEntrada;
	}
	public void setCdEntrada(String cdEntrada) {
		this.cdEntrada = cdEntrada;
	}
	
	public Sesion[] getSesiones() {
		return sesiones;
	}
	public void setSesiones(Sesion[] sesiones) {
		this.sesiones = sesiones;
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
		Entrada other = (Entrada) obj;
		return Objects.equals(cdEntrada, other.cdEntrada);
	}
	
	
}

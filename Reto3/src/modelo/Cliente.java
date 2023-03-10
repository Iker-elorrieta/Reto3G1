package modelo;

import java.util.Objects;

public class Cliente {
	String dni;
	String nombre;
	String apellido_1;
	String apellido_2;
	char  sexo;
	String contrasena;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido_1() {
		return apellido_1;
	}
	public void setApellido_1(String apellido_1) {
		this.apellido_1 = apellido_1;
	}
	public String getApellido_2() {
		return apellido_2;
	}
	public void setApellido_2(String apellido_2) {
		this.apellido_2 = apellido_2;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}
	
	@Override
	public String toString() {
		return dni + "•" + nombre + "•" + apellido_1 + "•"+ apellido_2 + "•" + sexo + "•" + contrasena + "∇";
	}
	

}

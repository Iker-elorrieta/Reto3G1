package modelo;

import java.util.Objects;

public class Cliente {
	private String dni;
	private String nombre;
	private String apellido_1;
	private String apellido_2;
	private char  sexo;
	private String contraseña;
	
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
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellido_1, apellido_2, contraseña, dni, nombre, sexo);
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

}

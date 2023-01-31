package test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sala;

class TestObjetos {

	@Test
	void testPelicula() {
		String resul="A1#Pepe#120.0#Comedia#5.0@";
		Pelicula pelicula1 =new Pelicula();
		pelicula1.setCdPel("A1");
		pelicula1.setNombre("Pepe");
		pelicula1.setDuracion(120);
		pelicula1.setGenero("Comedia");
		pelicula1.setPrecio(5);
		Pelicula pelicula2= new Pelicula();
		pelicula2.setCdPel("A4");
		pelicula1.equals(pelicula2);
		pelicula2.setCdPel(pelicula1.getCdPel());
		pelicula2.setNombre(pelicula1.getNombre());
		pelicula2.setDuracion(pelicula1.getDuracion());
		pelicula2.setGenero(pelicula1.getGenero());
		pelicula2.setPrecio(pelicula1.getPrecio());
		pelicula2.equals(pelicula1);
		pelicula1.equals(resul);
		assertEquals(resul,pelicula1.toString());
		resul=null;
		pelicula1.equals(resul);
		pelicula2=pelicula1;
		pelicula1.equals(pelicula2);
		
	}
	@Test
	void testCliente() {
		String resul="485673D•Pepe•Garcia•Rodriguez•H•Elorrieta00∇";
		Cliente cliente1 =new Cliente();
		cliente1.setDni("485673D");
		cliente1.setNombre("Pepe");
		cliente1.setApellido_1("Garcia");
		cliente1.setApellido_2("Rodriguez");
		cliente1.setSexo('H');
		cliente1.setContrasena("Elorrieta00");
		Cliente cliente2= new Cliente();
		cliente2.setDni("472346B");
		cliente1.equals(cliente2);
		cliente2.setDni(cliente1.getDni());
		cliente2.setNombre(cliente1.getNombre());
		cliente2.setApellido_1(cliente1.getApellido_1());
		cliente2.setApellido_2(cliente1.getApellido_2());
		cliente2.setSexo(cliente1.getSexo());
		cliente2.setContrasena(cliente1.getContrasena());
		cliente2.equals(cliente1);
		cliente1.equals(resul);
		assertEquals(resul,cliente1.toString());
		resul=null;
		cliente1.equals(resul);
		cliente2=cliente1;
		cliente1.equals(cliente2);
		
	}
	
	@Test
	void testEntrada() {
		String resul="43D•Pepe•Garcia•Rodriguez•H•Elorrieta00∇";
		Pelicula[] peliculas =new Pelicula[2];
		String[] horas = {"20:30","8:30"};
		Sala sla = new Sala();
		Date fecha = null;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2023);
		fecha = cal.getTime();
		Entrada entrada1 =new Entrada();
		entrada1.setCdEntrada("43D");
		entrada1.setFecha(fecha);
		entrada1.setPelícula(peliculas);
		entrada1.setHorarios(horas);
		entrada1.setSala(sla);
		entrada1.setPrecio(6);
		Entrada entrada2= new Entrada();
		entrada2.setCdEntrada("26g");
		entrada1.equals(entrada2);
		entrada2.setCdEntrada(entrada1.getCdEntrada());
		entrada2.setFecha(entrada1.getFecha());
		entrada2.setApellido_1(entrada1.getApellido_1());
		entrada2.setApellido_2(entrada1.getApellido_2());
		entrada2.setSexo(entrada1.getSexo());
		entrada2.setContrasena(entrada1.getContrasena());
		entrada2.equals(entrada1);
		entrada1.equals(resul);
		assertEquals(resul,entrada1.toString());
		resul=null;
		entrada1.equals(resul);
		entrada2=entrada1;
		entrada1.equals(entrada2);
		
	}

}

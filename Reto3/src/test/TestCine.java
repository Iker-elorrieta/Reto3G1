	package test;




import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Cine;

import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;


class TestCine {
	
	 Sesion sesion = new Sesion();
	 Sesion[] sesiones = {sesion}; 
	   Sala sala = new Sala();
	    Sala[] salas = {sala};
	Cine cine1 =new Cine();
			Cine cine2= new Cine(); 
		 String resul = "C01#Elorrieta#[CE-S01#Sala 1#[CES01001#Ac001#Terminator#120.0#Accion#5.0@#20/01/2023#09:56@]@]@";

	 
		
		
	@Test
	void testToString() {	
		
			sala.setCdSala("CE-S01");
			sala.setNomSala("Sala 1");
			sala.setSesiones(sesiones);
	
			Pelicula pelicula =new Pelicula();
			pelicula.setCdPel("Ac001");
			pelicula.setNombre("Terminator");
			pelicula.setGenero("Accion");
			pelicula.setDuracion(120);
			pelicula.setPrecio(5);
			Date fecha = null;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.YEAR, 2023);
			cal.set(Calendar.HOUR, 9);
			cal.set(Calendar.MINUTE, 56);
			fecha = cal.getTime();
		
			sesion.setIdSesion("CES01001");
			sesion.setPelicula(pelicula);
			sesion.setFecha(fecha);
		cine1.setCod_cine("C01");
		cine1.setNombre_cine("Elorrieta");
		cine1.setSalas(salas);
		
	
		assertEquals(resul,cine1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
	
	
		cine2.setCod_cine(cine1.getCod_cine());
		cine2.setNombre_cine(cine1.getNombre_cine());
		cine2.setSalas(cine1.getSalas());
		
		
		
	}
	
	@Test
	void testEquals() {
		
		cine2.equals(cine1);
		cine1.equals(resul);
		cine2.setCod_cine("C02");
		cine1.equals(cine2);
	
		cine1.equals(resul);
	resul=null;
	cine1.equals(resul);
	cine2=cine1;
	cine1.equals(cine2);
	
	
	}
		
		
	}
	
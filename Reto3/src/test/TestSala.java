package test;




import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;


import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;


class TestSala {
	
	  Sesion sesion = new Sesion();
	  Sesion[] sesiones = {sesion};
		Sala sala1 =new Sala();
			Sala sala2= new Sala(); 
		static String resul = "CE-01#Sala 1#[CE-01-0001#Ac001#Terminator#120#Accion#5.0@#20-01-2023#09:56@]@";
	
	@Test
	void testToString() {
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
			
			sesion.setIdSesion("CE-01-0001");
			sesion.setPelicula(pelicula);
			sesion.setFecha(fecha);
		
		sala1.setCdSala("CE-01");
		sala1.setNomSala("Sala 1");
		sala1.setSesiones(sesiones);
	
		assertEquals(resul,sala1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
	
	
		sala2.setCdSala(sala1.getCdSala());
		sala2.setNomSala(sala1.getNomSala());
		sala2.setSesiones(sala1.getSesiones());
		
		
		
	}
	
	@Test
	void testEquals() {
		
		sala2.equals(sala1);
		sala1.equals(resul);
	sala2.setCdSala("CE-S02");
	sala1.equals(sala2);
	
	sala1.equals(resul);
	resul=null;
	sala1.equals(resul);
	sala2=sala1;
	sala1.equals(sala2);
	
	
	}
		
		
	}
	
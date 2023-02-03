package test;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;


class TestSala {
	
	 static Sesion sesion = new Sesion();
	 static Sesion[] sesiones = {sesion};
		Sala sala1 =new Sala();
			Sala sala2= new Sala(); 
		static String resul = "CE-S01#Sala 1#[CES01001#Ac001#Terminator#120.0#Accion#5.0@#20/01/2023#09:00@]@";
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			
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
			fecha = cal.getTime();
			Time hora = new Time(0);
			hora.setTime(28800000  );
			sesion.setIdSesion("CES01001");
			sesion.setPelicula(pelicula);
			sesion.setFecha(fecha);
			sesion.setHora(hora);
			
	
		
		}
	@Test
	void testToString() {
		
		sala1.setCdSala("CE-S01");
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
	
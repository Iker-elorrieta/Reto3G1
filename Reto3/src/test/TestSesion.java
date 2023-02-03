package test;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import modelo.Pelicula;
import modelo.Sesion;


class TestSesion {
	static Date fecha = null;
	static Pelicula pelicula =new Pelicula();
	static Calendar cal = Calendar.getInstance();
	static Time hora = new Time(0);
	  Sesion sesion1 = new Sesion();
	  Sesion sesion2 = new Sesion();
	
		 String resul = "CES01001#Ac001#Terminator#120.0#Accion#5.0@#20/01/2023#09:00@";
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			
			
			pelicula.setCdPel("Ac001");
			pelicula.setNombre("Terminator");
			pelicula.setGenero("Accion");
			pelicula.setDuracion(120);
			pelicula.setPrecio(5);
			
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.YEAR, 2023);
			fecha = cal.getTime();
			
			hora.setTime(28800000);
			
			
			
	
		
		}
	@Test
	void testToString() {
		
		sesion1.setIdSesion("CES01001");
		sesion1.setPelicula(pelicula);
		sesion1.setFecha(fecha);
		sesion1.setHora(hora);
		
		assertEquals(resul,sesion1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
	
	
		sesion2.setIdSesion(sesion1.getIdSesion());
		sesion2.setPelicula(sesion1.getPelicula());
		sesion2.setFecha(sesion1.getFecha());
		sesion2.setHora(sesion1.getHora());
		
		
		
	}
	
	@Test
	void testEquals() {
		
		sesion2.equals(sesion1);
		sesion1.equals(resul);
		sesion2.setIdSesion("CES01002");
		sesion1.equals(sesion2);
	
		sesion1.equals(resul);
	resul=null;
	sesion1.equals(resul);
	sesion2=sesion1;
	sesion1.equals(sesion2);
	
	
	}
		
		
	}
	
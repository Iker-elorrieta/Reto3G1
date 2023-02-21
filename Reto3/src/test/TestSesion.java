package test;




import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;


import modelo.Pelicula;
import modelo.Sesion;


class TestSesion {
	 Date fecha = null;
	 Pelicula pelicula =new Pelicula();
	 Calendar cal = Calendar.getInstance();
	
	  Sesion sesion1 = new Sesion();
	  Sesion sesion2 = new Sesion();
	
		 String resul = "CE-01-0001#AC001#Terminator#120#Accion#5.0@#2023-01-20#09:11@";
		
	@Test
	void testToString() {	pelicula.setCdPel("AC001");
			pelicula.setNombre("Terminator");
			pelicula.setGenero("Accion");
			pelicula.setDuracion(120);
			pelicula.setPrecio(5);
			
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.YEAR, 2023);
		
			fecha = cal.getTime();
		
		sesion1.setIdSesion("CE-01-0001");
		sesion1.setPelicula(pelicula);
		sesion1.setFecha(fecha);
		sesion1.setHora("09:11");
		
		
		assertEquals(resul,sesion1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
	
	
		sesion2.setIdSesion(sesion1.getIdSesion());
		sesion2.setPelicula(sesion1.getPelicula());
		sesion2.setFecha(sesion1.getFecha());
		
		
		
		
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
	
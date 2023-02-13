package test;




import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Calendar;
import java.util.Date;


import org.junit.jupiter.api.Test;

import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;


class TestEntrada {
	
	  Sesion sesion = new Sesion();
	  Sesion[] sesiones = {sesion};
		Entrada entrada1 = new Entrada();
		Entrada entrada2 = new Entrada();
		 String resul = "CETCK001#[CE-01-0001#AC001#Terminator#120#Accion#5.0@#2023-01-20#09:11@]#23.0@";
		
	@Test
	void testToString() {	
			Pelicula pelicula =new Pelicula();
			pelicula.setCdPel("AC001");
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
			
		
		
			sesion.setIdSesion("CE-01-0001");
			sesion.setPelicula(pelicula);
			sesion.setFecha(fecha);
			sesion.setHora("09:11");
			
		
		entrada1.setCdEntrada("CETCK001");
		entrada1.setSesiones(sesiones);
		entrada1.setPrecio(23);
	
		assertEquals(resul,entrada1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
	
	
		entrada2.setCdEntrada(entrada1.getCdEntrada());
		entrada2.setSesiones(entrada1.getSesiones());
		entrada2.setPrecio(entrada1.getPrecio());
		
		
		
	}
	
	@Test
	void testEquals() {
		
		entrada2.equals(entrada1);
		entrada1.equals(resul);
		entrada2.setCdEntrada("CETCK002");
		entrada1.equals(entrada2);
	
		entrada1.equals(resul);
		resul=null;
		entrada1.equals(resul);
		entrada2=entrada1;
		entrada1.equals(entrada2);
	
	
	}
		
		
	}
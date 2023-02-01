package test;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Cliente;
import modelo.Hora;
import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;


class TestSala {
	
	 static Sesion sesion = new Sesion();
	 static Sesion[] sesiones = {sesion};
		Sala sala1 =new Sala();
			Sala sala2= new Sala(); 
		static String resul = "CE-S01#Sala 1#"+sesiones;
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			
			Pelicula pelicula =new Pelicula();
			Date fecha = null;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.YEAR, 2023);
			fecha = cal.getTime();
			Hora hora = new Hora();
			hora.setHoras(12);
			hora.setMins(25);
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
		
	
	
		cliente2.setDni(cliente1.getDni());
		cliente2.setNombre(cliente1.getNombre());
		cliente2.setApellido_1(cliente1.getApellido_1());
		cliente2.setApellido_2(cliente1.getApellido_2());
		cliente2.setSexo(cliente1.getSexo());
		cliente2.setContrasena(cliente1.getContrasena());
		
		
		
	}
	
	@Test
	void testEquals() {
		
	cliente2.equals(cliente1);
	cliente1.equals(resul);
	cliente2.setDni("472346B");
	cliente1.equals(cliente2);
	
	cliente1.equals(resul);
	resul=null;
	cliente1.equals(resul);
	cliente2=cliente1;
	cliente1.equals(cliente2);
	
	
	}
		
		
	}
	
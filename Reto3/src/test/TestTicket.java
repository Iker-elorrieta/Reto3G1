package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;
import modelo.Ticket;

class TestTicket {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	Metodos metodos = new Metodos();
		Cliente cliente= new Cliente();
	  Sesion sesion = new Sesion();
	  Sesion[] sesiones = {sesion};
	  Entrada entrada = new Entrada();
		Entrada[] entradas = {entrada};
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
		
		 String resul = "[1,23.0,22759228S•david•lopez•criado•h•12345∇,[CETCK001#CE-01-0001#AC001#Terminator#120#Accion#5.0@#2023-01-20#09:11@#23.0@]]";
		
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
			
		cliente = metodos.usuariosArray()[0];
		
			sesion.setIdSesion("CE-01-0001");
			sesion.setPelicula(pelicula);
			sesion.setFecha(fecha);
			sesion.setHora("09:11");
			
		
		entrada.setCdEntrada("CETCK001");
		entrada.setSesion(sesion);
		entrada.setPrecio(23);
	
		ticket1.setCdTicket(01);
		ticket1.setEntradas(entradas);
		ticket1.setCosteTotal(23);
		ticket1.setCliente(cliente);
		
		assertEquals(resul,ticket1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
	
		ticket2.setCdTicket(ticket1.getCdTicket());
		ticket2.setEntradas(ticket1.getEntradas());
		ticket2.setCosteTotal(ticket1.getCosteTotal());
		ticket2.setCliente(ticket1.getCliente());
		
	
		
		
		
	}
	
	@Test
	void testEquals() {
		
		ticket2.equals(ticket1);
		ticket1.equals(resul);
		ticket2.setCdTicket(02);
		ticket1.equals(ticket2);
	
		ticket1.equals(resul);
		resul=null;
		ticket1.equals(resul);
		ticket2=ticket1;
		ticket1.equals(ticket2);
	
	
	}
		
		
	
}

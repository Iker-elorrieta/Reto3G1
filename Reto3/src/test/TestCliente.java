package test;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Cliente;


class TestCliente {
	String resul="485673D•Pepe•Garcia•Rodriguez•H•Elorrieta00∇";
		Cliente cliente1 =new Cliente();
		Cliente cliente2= new Cliente();
	@Test
	void testToString() {
		
		cliente1.setDni("485673D");
		cliente1.setNombre("Pepe");
		cliente1.setApellido_1("Garcia");
		cliente1.setApellido_2("Rodriguez");
		cliente1.setSexo('H');
		cliente1.setContrasena("Elorrieta00");
		assertEquals(resul,cliente1.toString());
		
	
		
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
	
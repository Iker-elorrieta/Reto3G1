package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Hora;

class TestHora {
String resul="12:30";
		Hora hora1 =new Hora();
		Hora hora2= new Hora();
	@Test
	void testToString() {
		
		hora1.setHoras(12);
		hora1.setMins(30);
		
		assertEquals(resul,hora1.toString());
		
	
		
	}
	@Test
	void testGetterSetter() {
		
		hora2.setHoras(hora1.getHoras());
		hora2.setMins(hora1.getMins());
		
		
		
		
	}
	
	@Test
	void testEquals() {
		
		hora2.equals(hora1);
		hora1.equals(resul);
		
		resul=null;
		hora1.equals(resul);
		hora2=hora1;
		hora1.equals(hora2);
		
	
	
		
		
		
	}
	
}

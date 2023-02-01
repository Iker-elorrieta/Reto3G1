package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;

class MetodosTest {
	Metodos metodos=new Metodos();

	@Test
	void testCuantosCines() {
		Cine[] cine= metodos.cuantosCines();
		
		assertEquals("C01#Cines Elorrieta#null@", cine[0].toString());
		
	}

}

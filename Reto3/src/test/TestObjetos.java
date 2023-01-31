package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Pelicula;

class TestObjetos {

	@Test
	void testPelicula() {
		String resul="A1#Pepe#120.0#Comedia#5.0@";
		Pelicula pelicula1 =new Pelicula();
		pelicula1.setCdPel("A1");
		pelicula1.setNombre("Pepe");
		pelicula1.setDuracion(120);
		pelicula1.setGenero("Comedia");
		pelicula1.setPrecio(5);
		Pelicula pelicula2= new Pelicula();
		pelicula2.setCdPel("A4");
		pelicula1.equals(pelicula2);
		pelicula2.setCdPel(pelicula1.getCdPel());
		pelicula2.setNombre(pelicula1.getNombre());
		pelicula2.setDuracion(pelicula1.getDuracion());
		pelicula2.setGenero(pelicula1.getGenero());
		pelicula2.setPrecio(pelicula1.getPrecio());
		pelicula2.equals(pelicula1);
		pelicula1.equals(resul);
		assertEquals(resul,pelicula1.toString());
		resul=null;
		pelicula1.equals(resul);
		pelicula2=pelicula1;
		pelicula2.equals(pelicula2);
		
	}

}

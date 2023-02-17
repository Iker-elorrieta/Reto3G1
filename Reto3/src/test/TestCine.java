package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.Cine;

import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;

class TestCine {

	Sesion sesion = new Sesion();
	Sesion[] sesiones = { sesion };
	Sala sala = new Sala();
	Sala[] salas = { sala };
	Cine cine1 = new Cine();
	Cine cine2 = new Cine();
	String resul = "CE#Elorrieta#[CE-01#Sala 1#[CE-01-0001#AC001#Terminator#120#Accion#5.0@#2023-01-20#09:11@]@]@";

	@Test
	void testToString() {

		sala.setCdSala("CE-01");
		sala.setNomSala("Sala 1");
		sala.setSesiones(sesiones);

		Pelicula pelicula = new Pelicula();
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
		cine1.setCod_cine("CE");
		cine1.setNombre_cine("Elorrieta");
		cine1.setSalas(salas);

		assertEquals(resul, cine1.toString());

	}

	@Test
	void testGetterSetter() {

		cine2.setCod_cine(cine1.getCod_cine());
		cine2.setNombre_cine(cine1.getNombre_cine());
		cine2.setSalas(cine1.getSalas());

	}

	@Test
	void testEquals() {

		cine2.equals(cine1);
		cine1.equals(resul);
		cine2.setCod_cine("C02");
		cine1.equals(cine2);

		cine1.equals(resul);
		resul = null;
		cine1.equals(resul);
		cine2 = cine1;
		cine1.equals(cine2);

	}

}

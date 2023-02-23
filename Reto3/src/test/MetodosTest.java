package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;

class MetodosTest {

	/*
	  final String sConexion = "jdbc:mysql://10.5.14.202:3306/cines"; final String
	  user = "cliente"; final String contra = "Contraseña33#";
	*/
	
	final String sConexion = "jdbc:mysql://localhost:3306/cines";
	final String user = "root";
	final String contra = "";
	 
	Metodos metodos = new Metodos();

	@Test
	void testCuantosCines() {
		Cine[] cines = metodos.cuantosCines();

		String resul = cines[0].toString();

		assertEquals(resul, cines[0].toString());
	}

	@Test
	void testUsuariosArray() {
		Cliente[] clientes = metodos.usuariosArray();

		assertEquals("22759228S•david•lopez•criado•h•12345∇", clientes[0].toString());

	}

	@Test
	void testValidarUsers() {
		Cliente[] clientes = metodos.usuariosArray();
		String usur = "22759228S";
		String passw = "12345";
		boolean resul = metodos.validarUsers(clientes, usur, passw);
		assertTrue(resul);
		passw = "1234";
		resul = metodos.validarUsers(clientes, usur, passw);
		assertFalse(resul);
	}

	@Test
	void testValidarDNI() {
		
		String text = "79009471D";

		boolean resul = metodos.validarDni(text);
		assertEquals(true, resul);
		text = "22757228S";
		resul = metodos.validarDni(text);
		assertEquals(false, resul);
		text = "2275228S";
		resul = metodos.validarDni(text);
		assertEquals(false, resul);

	}

	@Test
	void testRegistrarUsuario() {

		String dni = "45894650J";
		String nombre = "Sergio";
		String apell1 = "Galera";
		String apell2 = "Frias";
		String sexoCB = "H";
		String passw = "12345";

		Cliente[] usuarios = metodos.usuariosArray();

		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i].getDni().equals(dni)) {
				try {
					Connection conexion = DriverManager.getConnection(sConexion, user, contra);

					PreparedStatement st = conexion.prepareStatement("delete from clientes where dni='" + dni + "';");
					st.executeUpdate();

					st.close();
					conexion.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		usuarios = metodos.registrarUsuario(dni, nombre, apell1, apell2, sexoCB, passw);
		usuarios = metodos.usuariosArray();
		assertEquals(dni, usuarios[1].getDni());
	}

	@Test
	void testCargarPeliculas() {
		Cine[] cines = metodos.cuantosCines();
		Cine cine = cines[0];
		String resul = cine.toString();
		assertEquals(resul, cines[0].toString());

	}

	// En este test Tenemos en String, dada una pelicula y su fecha que nos saque la
	// sesion y su hora y sala.
	@Test
	void testHorarioSesiones() {
		Cine[] cines = metodos.cuantosCines();

		Pelicula[] peliculas = metodos.cargarPeliculas(cines[0]);

		Calendar cal = Calendar.getInstance();
		Date fecha = null;
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.YEAR, 2023);

		fecha = cal.getTime();
		String[] horas = metodos.horarioSesiones(peliculas[0], cines[0], fecha);
		String horasStr = "";
		for (int i = 0; i < horas.length; i++) {
			horasStr += horas[i] + ",";
		}
		horasStr = "[" + horasStr + "]";

		String resul = horasStr;

		assertEquals(resul, horasStr);

	}

	@Test
	void testQueSesion() {
		Cine[] cines = metodos.cuantosCines();

		Pelicula[] peliculas = metodos.cargarPeliculas(cines[0]);
		Pelicula pelicula = peliculas[0];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.YEAR, 2023);
		Date fecha1 = (Date) cal.getTime();
		String sala = cines[0].getSalas()[0].getNomSala();
		String hora = cines[0].getSalas()[0].getSesiones()[0].getHora();
		Sesion sesion = metodos.queSesion(cines[0], sala, fecha1, hora, pelicula);
		String resul = sesion.toString();
		assertEquals(resul, sesion.toString());

	}

	@Test
	void testEsVacio() {

		assertTrue(metodos.esVacio(""));
		assertFalse(metodos.esVacio("Hola"));

	}

	@Test
	void testSiguienteEntrada() {
		Entrada[] entradas = new Entrada[2];

		entradas = metodos.siguienteEntrada(entradas);
		Entrada[] entradas2 = entradas;
		assertEquals(entradas, entradas2);
	}

	@Test
	void testSacarPrecio() {

		Cine[] cines = metodos.cuantosCines();
		Cine cine = cines[0];
		Pelicula[] peliculas = metodos.cargarPeliculas(cine);
		Pelicula pelicula = peliculas[0];
		float precio = metodos.sacarPrecio(pelicula);

		assertEquals(precio, metodos.sacarPrecio(pelicula), 0.1);
	}

	@Test
	void testSacarDescuento() {

		Cine[] cines = metodos.cuantosCines();
		Pelicula[] peliculas = metodos.cargarPeliculas(cines[0]);
		Pelicula pelicula = peliculas[0];
		float precio = metodos.calcularDescuento(pelicula.getPrecio(), (float) 1);

		assertEquals(precio, metodos.calcularDescuento(pelicula.getPrecio(), (float) 1), 0.1);
	}

	@Test
	void testFechasPeliculas() {

		Cine[] cines = metodos.cuantosCines();
		Pelicula[] peliculas = metodos.cargarPeliculas(cines[0]);
		Pelicula pelicula = peliculas[0];
		Date[] fechas = metodos.fechasPelicula(cines[0], pelicula);
		Date[] resul = fechas;
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

		assertEquals(dt.format(resul[0]), dt.format(fechas[0]));

	}

	@Test
	void testCalcularDescuento() {
		float precioSinDesc = 4;
		float tm = 1;
		float resul = metodos.calcularDescuento(precioSinDesc, tm);
		assertEquals(resul, metodos.calcularDescuento(precioSinDesc, tm));
	}

	@Test
	void testNombreUsuario() {
		Cliente[] usuarios = metodos.usuariosArray();
		String nombre = metodos.nombreUsuario(usuarios, "45994361L");
		assertEquals("clemen", nombre);
	}

	@Test
	void testNuevaEntrada() {
		Cine[] cines = metodos.cuantosCines();
		Sesion sesion = cines[0].getSalas()[0].getSesiones()[0];
		Entrada entrada = metodos.nuevaEntrada(sesion, 1);
		assertEquals(entrada.toString(), metodos.nuevaEntrada(sesion, 1).toString());
	}

	@Test
	void testSalaConFechaYPelicula() {
		Cine[] cines = metodos.cuantosCines();
		Sesion sesion = cines[0].getSalas()[0].getSesiones()[0];
		String cineYsala = metodos.salaConFechaYPelicula(sesion, cines);
		assertEquals(cineYsala, metodos.salaConFechaYPelicula(sesion, cines));
	}
	
	@Test
	void testImprimirFactura() {
		Cine[] cines = metodos.cuantosCines();
		Sesion sesion = cines[0].getSalas()[0].getSesiones()[0];
		Entrada entrada = metodos.nuevaEntrada(sesion, 1);
		Entrada[] entradas= {entrada};
		Cliente[] clientes = metodos.usuariosArray();
		String dni = "79009471D";
		String[] salayCine= {"Cines Elorrieta - Sala 1"};
		float total =(float) 32.00;
		metodos.imprimirFactura(entradas, clientes, dni, salayCine, total);
		File file = new File("factura.txt");
		BufferedReader fichero;
		String contenidoTxt="";
	
		try {
			fichero = new BufferedReader(new FileReader(file));
			String linea;
			
			while((linea = fichero.readLine())!=null)
			{
			contenidoTxt+= linea +"\n";
			}
			
			fichero.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		String comparador=contenidoTxt;
		assertEquals(contenidoTxt, comparador);
		}
	
	@Test
	void testCompraRealizada() {
		Cine[] cines = metodos.cuantosCines();
		Sesion sesion = cines[0].getSalas()[0].getSesiones()[0];
		Entrada entrada = metodos.nuevaEntrada(sesion, 1);
		Entrada[] entradas= {entrada};
		String texto="";
		String tickets = null;
		String dni = "79009471D";
		
		float total =(float) 32.00;
		metodos.compraRealizada(entradas, dni, total);
		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
						ResultSet registro = comando.executeQuery("select * From ticket where coste_total=32.00 and dni='79009471D';");
						while (registro.next()) {
							texto+= registro.getString(1)+" , "+registro.getString(2)+" , "+registro.getString(3)+"\n";
						}
						tickets = texto;
			registro.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(tickets,texto);
	}
}

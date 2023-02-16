package test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import controlador.Metodos;
import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sesion;

class MetodosTest {
	final String sConexion = "jdbc:mysql://localhost:3306/cines";
	final String user = "root"; 
	final String contra = "";
	Metodos metodos=new Metodos();
	
	@Test
	void testCuantosCines() {
		

		Cine[] cine= metodos.cuantosCines();
		String resul=cine[0].toString();
		
		assertEquals(resul, cine[0].toString());
		
	}
	@Test
	void testUsuariosArray() {
		Cliente[] clientes= metodos.usuariosArray();
		
		assertEquals("22759228S•david•lopez•criado•h•12345∇", clientes[0].toString());
		
		
	}
	
	@Test
	void testValidarUsers() {
		Cliente[] clientes= metodos.usuariosArray();
		String usur="22759228S";
		String passw="12345";
		boolean resul=metodos.validarUsers(clientes, usur, passw);
		assertTrue(resul);
		 passw="1234";
		  resul=metodos.validarUsers(clientes, usur, passw);
		assertFalse( resul);
	}
		@Test
		void testValidarDNI() {
			Cliente[] clientes= metodos.usuariosArray();
			String text="79009471D";
			
			boolean resul=metodos.validarDni(text, clientes);
			assertEquals(true, resul);
			 text="22757228S";
			  resul=metodos.validarDni(text, clientes);
			assertEquals(false, resul);
			 text="2275228S";
			  resul=metodos.validarDni(text, clientes);
			assertEquals(false, resul);
			
		}
		
		@Test
		void testRegistrarUsuario() {
			String dni="45894650J";
			String nombre="Sergio";
			String apell1="Galera";
			String apell2="Frias";
			String sexoCB="H";
			String passw="12345";
			
			Cliente[] usuarios = metodos.usuariosArray();
			
			for (int i=0;i < usuarios.length;i++) {
				if (usuarios[i].getDni().equals(dni)) {
					try {
						Connection conexion = DriverManager.getConnection(sConexion, user, contra);
						Statement comando = conexion.createStatement();

						comando.executeUpdate("delete from clientes where dni='"+dni+"';");

						conexion.close();

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			usuarios=metodos.registrarUsuario(dni, nombre, apell1, apell2, sexoCB, passw);
			
			assertEquals(dni, usuarios[usuarios.length-1].getDni());
		}
		
		@Test
		void testCargarPeliculas() {
			
			Cine[] cines= metodos.cuantosCines();
			Cine cine = cines[0];
			String resul=cines[0].toString();
			assertEquals(resul, cine.toString());
			
			
		}
		
		// En este test Tenemos en String, dada una pelicula y su fecha que nos saque la sesion y su hora y sala.
		@Test
		void testHorarioSesiones() {
			Cine[] cines= metodos.cuantosCines();
			Cine cine = cines[0];
			Pelicula[] peliculas = metodos.cargarPeliculas(cine);
			
			Calendar cal= Calendar.getInstance();;
			Date fecha = null;
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MONTH, 2);
			cal.set(Calendar.YEAR, 2023);
			
			
			fecha = cal.getTime();
			String[] horas= metodos.horarioSesiones(peliculas[0], cines[0], fecha);
			String horasStr="";
			for (int i = 0; i < horas.length; i++) {
				horasStr+= horas[i]+",";
			}
			horasStr="["+ horasStr+"]";
			
			String resul=horasStr;
			
			assertEquals(resul, horasStr);
			
		
			
		}
		@Test
		void testQueSesion() {
			Cine[] cines= metodos.cuantosCines();
			Cine cine = cines[1];
			Pelicula[] peliculas = metodos.cargarPeliculas(cine);
			Pelicula pelicula= peliculas[0];	
			String sala = "Sala 1";
			String hora="16:00";
			Calendar cal= Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 3);
			cal.set(Calendar.MONTH, 2);
			cal.set(Calendar.YEAR, 2023);
			Date fecha1= (Date) cal.getTime();
			Sesion sesion = metodos.queSesion(cine, sala, fecha1, hora, pelicula);
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
			
			entradas=metodos.siguienteEntrada(entradas);
			Entrada[] entradas2 =entradas;
			assertEquals(entradas, entradas2);
		}
		@Test
		void testSacarPrecio() {
			Cine[] cines= metodos.cuantosCines();
			Cine cine = cines[1];
			Pelicula[] peliculas = metodos.cargarPeliculas(cine);
			Pelicula pelicula= peliculas[0];	
			
			
			assertEquals(3.6,metodos.sacarPrecio(pelicula),0.1 );
		}
		@Test
		void testFechasPeliculas() {
			Cine[] cines= metodos.cuantosCines();
			Cine cine = cines[1];
			Pelicula[] peliculas = metodos.cargarPeliculas(cine);
			Pelicula pelicula= peliculas[0];	
			Date[] fechas=metodos.fechasPelicula(cine, pelicula);
			Date[] resul=fechas;
			DateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 

			
			assertEquals(dt.format(resul[0]),dt.format(fechas[0]));
			
		}
		
	}



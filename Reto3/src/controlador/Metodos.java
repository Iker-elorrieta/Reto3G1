package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import modelo.Cine;
import modelo.Cliente;
import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;

public class Metodos {
	// declaro la base de datos remota junto al usuario y contraseña de mysql que he
	// creado allí.
	final String sConexion = "jdbc:mysql://10.5.14.202:3306/cines";
	final String user = "cliente";
	final String contra = "Contraseña33#";

	// Aqui lee los datos de la tabla cines de la BD y la mete en una array que
	// devuelve.
	public Cine[] cuantosCines() {
		// TODO Auto-generated method stub
		Cine[] cines = new Cine[0];

		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM cines;");

			while (registro.next() == true) {
				Cine cine = new Cine();
				cine.setCod_cine(registro.getString("cod_cine"));
				cine.setNombre_cine(registro.getString("nombre_cine"));
				cine = cuantasSalas(cine);
				Cine[] arrayNuevo = new Cine[cines.length + 1];
				for (int i = 0; i < cines.length; i++) {
					arrayNuevo[i] = cines[i];
				}
				arrayNuevo[cines.length] = cine;
				cines = arrayNuevo;
			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cines;
	}

	// Aqui lee cuantas salas hay por cine y las devuelve.
	public Cine cuantasSalas(Cine cines) {
		// TODO Auto-generated method stub

		Sala[] salas = new Sala[0];

		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando
					.executeQuery("SELECT * FROM salas where cod_cine='" + cines.getCod_cine() + "';");

			while (registro.next() == true) {
				Sala sala = new Sala();
				sala.setCdSala(registro.getString("cod_sala"));
				sala.setNomSala(registro.getString("nombre_sala"));
				sala = cuantasSesiones(sala);
				Sala[] arrayNuevo = new Sala[salas.length + 1];
				for (int i = 0; i < salas.length; i++) {
					arrayNuevo[i] = salas[i];
				}
				arrayNuevo[salas.length] = sala;
				salas = arrayNuevo;
				cines.setSalas(salas);
			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cines;
	}

	// Aqui la funcion que le las sesiones por cada sala.
	public Sala cuantasSesiones(Sala sala) {

		Sesion[] sesiones = new Sesion[0];
		Calendar cal = Calendar.getInstance();
		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando
					.executeQuery("SELECT * FROM sesiones where cod_sala='" + sala.getCdSala() + "';");

			while (registro.next() == true) {

				Sesion sesion = new Sesion();
				sesion.setIdSesion(registro.getString("cod_sesion"));

				String fecha = String.valueOf(registro.getDate("fecha"));
				cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(fecha.split("-")[0]));
				cal.set(Calendar.MONTH, Integer.valueOf(fecha.split("-")[1]));
				cal.set(Calendar.YEAR, Integer.valueOf(fecha.split("-")[2]));

				String hora = registro.getString("hora");
				cal.set(Calendar.HOUR, Integer.valueOf(hora.split(":")[0]));
				cal.set(Calendar.MINUTE, Integer.valueOf(hora.split(":")[1]));

				sesion.setFecha(cal.getTime());
				sesion = cuantasPeliculas(sesion);

				Sesion[] arrayNuevo = new Sesion[sesiones.length + 1];
				for (int i = 0; i < sesiones.length; i++) {
					arrayNuevo[i] = sesiones[i];
				}
				arrayNuevo[sesiones.length] = sesion;
				sesiones = arrayNuevo;
				sala.setSesiones(sesiones);
			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sala;
	}

	// Aqui se lee que pelicula hay para cada sesion.
	public Sesion cuantasPeliculas(Sesion sesion) {

		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();

			ResultSet registro = comando
					.executeQuery("select * from peliculas where cod_pelicula='" + sesion.getIdSesion() + "';");

			while (registro.next() == true) {

				Pelicula pelicula = new Pelicula();
				pelicula.setCdPel(registro.getString("cod_pelicula"));
				pelicula.setNombre(registro.getString("nom_peli"));
				pelicula.setGenero(registro.getString("genero"));
				pelicula.setDuracion(registro.getInt("duracion"));
				pelicula.setPrecio(registro.getFloat("coste"));

				sesion.setPelicula(pelicula);
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sesion;
	}

	// Aqui lee cuantos clientes y los devuelve.
	public Cliente[] usuariosArray() {
		Cliente[] usuario = new Cliente[0];
		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM clientes;");

			while (registro.next() == true) {

				Cliente cliente = new Cliente();
				cliente.setDni(registro.getString("dni"));
				cliente.setNombre(registro.getString("nombre"));
				cliente.setApellido_1(registro.getString("apellido_1"));
				cliente.setApellido_2(registro.getString("apellido_2"));
				cliente.setSexo(registro.getString("sexo").charAt(0));
				cliente.setContrasena(registro.getString("passw"));

				Cliente[] arrayNuevo = new Cliente[usuario.length + 1];
				for (int i = 0; i < usuario.length; i++) {
					arrayNuevo[i] = usuario[i];
				}
				arrayNuevo[usuario.length] = cliente;
				usuario = arrayNuevo;
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;

	}

	// Aqui se valida el login.
	public boolean validarUsers(Cliente[] usuario, String user, String passw) {
		// TODO Auto-generated method stub
		boolean correcto = false;

		for (int i = 0; i < usuario.length; i++) {
			if (usuario[i].getDni().equals(user) && usuario[i].getContrasena().equals(passw)) {
				correcto = true;
			}
		}
		return correcto;
	}

	// Aqui se valida el dni cuando se registra un nuevo usuario.
	public boolean validarDni(String text, Cliente[] usuarios) {
		// TODO Auto-generated method stub
		boolean correcto = true;

		if (text.length() != 9) {
			correcto = false;
		} else {
			int dni_num = Integer.valueOf(text.substring(0, (text.length() - 1)));
			int comp = dni_num % 23;
			String Letras = "T;R;W;A;G;M;Y;F;P;D;X;B;N;J;Z;S;Q;V;H;L;C;K;E;";
			String[] Letra = Letras.split(";");
			if (String.valueOf(text.charAt(8)).equals(Letra[comp])) {
				correcto = true;
			} else
				correcto = false;
		}

		for (int i = 0; i < usuarios.length; i++) {
			if (text.equals(usuarios[i].getDni()))
				correcto = false;
		}

		return correcto;
	}

	// Aqui se registran en la base de datos los nuevos usuarios.
	public Cliente[] registrarUsuario(String dni, String nombre, String apell1, String apell2, String sexoCB,
			String passw) {
		// TODO Auto-generated method stub

		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			comando.executeUpdate("Insert into clientes values ('" + dni + "', '" + apell1 + "', '" + dni + "', '"
					+ apell2 + "', '" + sexoCB + "', '" + passw + "');");

			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Aqui se hace una query para que devuelva el nuevo array de clientes.
		Cliente[] usuario = usuariosArray();

		return usuario;
	}

	public boolean esVacio(String texto) {
		boolean estaVacio = false;

		if (texto.equals(""))
			estaVacio = true;

		return estaVacio;
	}

	// Aqui se declaran todas las peliculas que tiene el cine para poder hacer
	// comparaciones en las vistas.
	public Pelicula[] todasLasPeliculas(Cine[] cines) {
		// TODO Auto-generated method stub
		Pelicula[] peliculas = null;
		for (int cont1 = 0; cont1 < cines.length; cont1++) {
			peliculas = new Pelicula[0];
			try {
				Connection conexion = DriverManager.getConnection(sConexion, user, contra);
				Statement comando = conexion.createStatement();
				for (int i = 0; i < cines[cont1].getSalas().length; i++) {
					ResultSet registro = comando
							.executeQuery("select distinct cod_pelicula from sesiones where cod_sala='"
									+ cines[cont1].getSalas()[i].getCdSala() + "';");

					while (registro.next() == true) {

						Pelicula pelicula = new Pelicula();
						pelicula.setCdPel(registro.getString("cod_pelicula"));
						pelicula.setNombre(registro.getString("nom_peli"));
						pelicula.setGenero(registro.getString("genero"));
						pelicula.setDuracion(registro.getInt("duracion"));
						pelicula.setPrecio(registro.getFloat("coste"));

						Pelicula[] arrayNuevo = new Pelicula[peliculas.length + 1];
						for (int cont2 = 0; cont2 < peliculas.length; cont2++) {
							arrayNuevo[cont2] = peliculas[cont2];
						}
						arrayNuevo[peliculas.length] = pelicula;
						peliculas = arrayNuevo;

					}
				}
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return peliculas;
	}

}

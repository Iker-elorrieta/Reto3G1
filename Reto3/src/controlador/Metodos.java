package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class Metodos {

	// declaro la base de datos remota junto al usuario y contraseña de mysql que he
	// creado allí.

	final String sConexion = "jdbc:mysql://10.5.14.202:3306/cines";
	final String user = "cliente";
	final String contra = "Contraseña33#";

	/*
	 * final String sConexion = "jdbc:mysql://localhost:3306/cines"; final String
	 * user = "root"; final String contra = "";
	 */

	final String codCine = "cod_cine";
	final String nombreCine = "nombre_cine";
	final String codSala = "cod_sala";
	final String nombreSala = "nombre_sala";
	final String codSesion = "cod_sesion";
	final String fechaSesion = "fecha";
	final String horaSesion = "hora";
	final String codPelicula = "cod_pelicula";
	final String nombrePelicula = "nom_peli";
	final String generoPelicula = "genero";
	final String duracionPelicula = "duracion";
	final String costePelicula = "coste";
	final String dniCliente = "dni";
	final String nombreCliente = "nombre";
	final String ApeCliente1 = "apellido_1";
	final String ApeCliente2 = "apellido_2";
	final String sexoCliente = "sexo";
	final String contrasenaCliente = "passw";
	final String tablaCines = "cines";
	final String tablaSalas = "salas";
	final String tablaSesiones = "sesiones";
	final String tablaEntradas = "entradas";
	final String tablaTickets = "ticket";
	final String costeTotalConDescuento = "coste_total";
	final String codigoTicket = "cod_ticket";
	// Aqui lee los datos de la tabla cines de la BD y la mete en una array que
	// devuelve

	public Cine[] cuantosCines() {
		// TODO Auto-generated method stub
		Cine[] cines = new Cine[0];

		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM " + tablaCines + ";");

			while (registro.next() == true) {
				Cine cine = new Cine();
				cine.setCod_cine(registro.getString(codCine));
				cine.setNombre_cine(registro.getString(nombreCine));
				cine = cuantasSalas(cine, conexion);
				Cine[] arrayNuevo = new Cine[cines.length + 1];
				for (int i = 0; i < cines.length; i++) {
					arrayNuevo[i] = cines[i];
				}
				arrayNuevo[cines.length] = cine;
				cines = arrayNuevo;
			}
			registro.close();
			comando.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cines;
	}

	// Aqui lee cuantas salas hay por cine y las devuelve.
	public Cine cuantasSalas(Cine cines, Connection conexion) {
		// TODO Auto-generated method stub

		Sala[] salas = new Sala[0];

		try {
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery(
					"SELECT * FROM " + tablaSalas + " where " + codCine + "='" + cines.getCod_cine() + "';");

			while (registro.next() == true) {
				Sala sala = new Sala();
				sala.setCdSala(registro.getString(codSala));
				sala.setNomSala(registro.getString(nombreSala));
				sala = cuantasSesiones(sala, conexion);
				Sala[] arrayNuevo = new Sala[salas.length + 1];
				for (int i = 0; i < salas.length; i++) {
					arrayNuevo[i] = salas[i];
				}
				arrayNuevo[salas.length] = sala;
				salas = arrayNuevo;
				cines.setSalas(salas);
			}

			registro.close();
			comando.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cines;
	}

	// Aqui la funcion que le las sesiones por cada sala.
	public Sala cuantasSesiones(Sala sala, Connection conexion) {

		Sesion[] sesiones = new Sesion[0];
		Calendar cal = Calendar.getInstance();
		try {
			Statement comando = conexion.createStatement();

			ResultSet registro = comando.executeQuery(
					"SELECT * FROM sesiones where " + codSala + "='" + sala.getCdSala() + "' order by fecha, hora;");

			while (registro.next() == true) {

				Sesion sesion = new Sesion();
				sesion.setIdSesion(registro.getString(codSesion));

				String fecha = String.valueOf(registro.getDate(fechaSesion));
				cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(fecha.split("-")[2]));
				cal.set(Calendar.MONTH, Integer.valueOf(fecha.split("-")[1]) - 1);
				cal.set(Calendar.YEAR, Integer.valueOf(fecha.split("-")[0]));

				String hora = registro.getString(horaSesion);
				sesion.setHora(hora);

				sesion.setFecha(cal.getTime());
				sesion = cuantasPeliculas(sesion, registro.getString(codPelicula), conexion);

				Sesion[] arrayNuevo = new Sesion[sesiones.length + 1];
				for (int i = 0; i < sesiones.length; i++) {
					arrayNuevo[i] = sesiones[i];
				}
				arrayNuevo[sesiones.length] = sesion;
				sesiones = arrayNuevo;
				sala.setSesiones(sesiones);
			}

			registro.close();
			comando.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sala;
	}

	// Aqui se lee que pelicula hay para cada sesion.
	public Sesion cuantasPeliculas(Sesion sesion, String cod_pelicula, Connection conexion) {

		try {

			Statement comando = conexion.createStatement();

			ResultSet registro = comando
					.executeQuery("select * from peliculas where " + codPelicula + "='" + cod_pelicula + "';");

			while (registro.next() == true) {

				Pelicula pelicula = new Pelicula();
				pelicula.setCdPel(registro.getString(codPelicula));
				pelicula.setNombre(registro.getString(nombrePelicula));
				pelicula.setGenero(registro.getString(generoPelicula));
				pelicula.setDuracion(registro.getInt(duracionPelicula));
				pelicula.setPrecio(registro.getFloat(costePelicula));

				sesion.setPelicula(pelicula);
			}

			registro.close();
			comando.close();
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
			ResultSet registro = comando
					.executeQuery("SELECT dni, nombre, apellido_1, apellido_2, sexo, passw FROM clientes;");

			while (registro.next() == true) {

				Cliente cliente = new Cliente();
				cliente.setDni(registro.getString(dniCliente));
				cliente.setNombre(registro.getString(nombreCliente));
				cliente.setApellido_1(registro.getString(ApeCliente1));
				cliente.setApellido_2(registro.getString(ApeCliente2));
				cliente.setSexo(registro.getString(sexoCliente).charAt(0));
				cliente.setContrasena(registro.getString(contrasenaCliente));

				Cliente[] arrayNuevo = new Cliente[usuario.length + 1];
				for (int i = 0; i < usuario.length; i++) {
					arrayNuevo[i] = usuario[i];
				}
				arrayNuevo[usuario.length] = cliente;
				usuario = arrayNuevo;
			}

			registro.close();
			comando.close();
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

		return correcto;
	}

	// Aqui se registran en la base de datos los nuevos usuarios.
	public Cliente[] registrarUsuario(String dni, String nombre, String apell1, String apell2, String sexoCB,
			String passw) {
		// TODO Auto-generated method stub

		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();

			comando.executeUpdate("Insert into clientes values ('" + dni + "', '" + nombre + "', '" + apell1 + "', '"
					+ apell2 + "', '" + sexoCB.charAt(0) + "', '" + passw + "');");

			comando.close();
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

	public Pelicula[] cargarPeliculas(Cine cine) {
		// TODO Auto-generated method stub

		Pelicula[] peliculas = new Pelicula[0];
		for (int contSalas = 0; contSalas < cine.getSalas().length; contSalas++) {
			for (int contSesiones = 0; contSesiones < cine.getSalas()[contSalas].getSesiones().length; contSesiones++) {

				Pelicula[] arrayNuevo = new Pelicula[peliculas.length + 1];

				for (int i = 0; i < peliculas.length; i++) {
					arrayNuevo[i] = peliculas[i];

				}
				boolean estaEnELArray = false;

				for (int nuevasPelis = 0; nuevasPelis < arrayNuevo.length; nuevasPelis++) {

					if (arrayNuevo[nuevasPelis] != null) {
						if (arrayNuevo[nuevasPelis].getCdPel().equals(
								cine.getSalas()[contSalas].getSesiones()[contSesiones].getPelicula().getCdPel())) {
							estaEnELArray = true;
						}
					}
				}

				if (!estaEnELArray) {
					arrayNuevo[arrayNuevo.length - 1] = cine.getSalas()[contSalas].getSesiones()[contSesiones]
							.getPelicula();
					peliculas = arrayNuevo;
				}

			}
		}

		return peliculas;
	}

	public String[] horarioSesiones(Pelicula pelicula, Cine cine, Date fecha) {
		String[] horas = new String[0];

		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String horaS = "";
		String salaS = "";
		for (int salasN = 0; salasN < cine.getSalas().length; salasN++) {
			for (int sesionesN = 0; sesionesN < cine.getSalas()[salasN].getSesiones().length; sesionesN++) {

				if (dt.format(cine.getSalas()[salasN].getSesiones()[sesionesN].getFecha()).equals(dt.format(fecha))
						&& cine.getSalas()[salasN].getSesiones()[sesionesN].getPelicula().getCdPel()
								.equals(pelicula.getCdPel())) {

					String[] arrayNuevo = new String[horas.length + 1];
					for (int i = 0; i < horas.length; i++) {
						arrayNuevo[i] = horas[i];
					}

					horaS = cine.getSalas()[salasN].getSesiones()[sesionesN].getHora();
					salaS = cine.getSalas()[salasN].getNomSala();
					arrayNuevo[arrayNuevo.length - 1] = horaS + " - " + salaS;
					horas = arrayNuevo;
				}
			}
		}

		return horas;
	}

	public Entrada[] siguienteEntrada(Entrada[] entradas_compradas) {
		// TODO Auto-generated method stub
		Entrada[] nuevoArray = new Entrada[entradas_compradas.length + 1];

		for (int i = 0; i < entradas_compradas.length; i++) {
			nuevoArray[i] = entradas_compradas[i];
		}

		entradas_compradas = nuevoArray;

		return entradas_compradas;
	}

	public Sesion queSesion(Cine cine, String nombre_sala, Date fecha, String hora, Pelicula pelicula) {
		// TODO Auto-generated method stub
		Sesion sesionF = new Sesion();
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

		for (int salasN = 0; salasN < cine.getSalas().length; salasN++) {
			for (int sesionesN = 0; sesionesN < cine.getSalas()[salasN].getSesiones().length; sesionesN++) {
				Sesion sesion = cine.getSalas()[salasN].getSesiones()[sesionesN];

				if (sesion.getPelicula().getCdPel().equals(pelicula.getCdPel())
						&& dt.format(sesion.getFecha()).equals(dt.format(fecha)) && sesion.getHora().equals(hora)
						&& cine.getSalas()[salasN].getNomSala().equals(nombre_sala))
					sesionF = cine.getSalas()[salasN].getSesiones()[sesionesN];
			}
		}

		return sesionF;
	}

	public Entrada nuevaEntrada(Sesion sesion, int num_entrada) {
		// TODO Auto-generated method stub

		Entrada entrada = new Entrada();
		entrada.setCdEntrada(String.valueOf(num_entrada));
		entrada.setFecha(sesion.getFecha());
		entrada.setHora(sesion.getHora());
		entrada.setPrecio(sesion.getPelicula().getPrecio());
		entrada.setSesion(sesion);

		return entrada;
	}

	public float sacarPrecio(Pelicula pelicula) {
		// TODO Auto-generated method stub
		return pelicula.getPrecio();
	}

	// Cuando podamos restringir las fechas del DatePicker usaremos este metodo para
	// habilitar las fechas resultantes,
	// por ahora no se usa
	public Date[] fechasPelicula(Cine cine, Pelicula pelicula) {
		// TODO Auto-generated method stub

		Date[] fechasPelicula = new Date[0];
		for (int nSalas = 0; nSalas < cine.getSalas().length; nSalas++) {
			Sesion[] sesiones = cine.getSalas()[nSalas].getSesiones();
			for (int i = 0; i < sesiones.length; i++) {
				if (sesiones[i].getPelicula().getCdPel().equals(pelicula.getCdPel())) {

					Date[] arrayTemp = new Date[fechasPelicula.length + 1];
					for (int nFechas = 0; nFechas < fechasPelicula.length; nFechas++)
						arrayTemp[nFechas] = fechasPelicula[nFechas];

					arrayTemp[arrayTemp.length - 1] = sesiones[i].getFecha();
					fechasPelicula = arrayTemp;
				}

			}
		}
		return fechasPelicula;
	}

	public float calcularDescuento(float costeTotSinDescuento, float length) {

		float aDescontar = (float) costeTotSinDescuento * (length / 10);
		costeTotSinDescuento -= aDescontar;

		return costeTotSinDescuento;

	}

	public String nombreUsuario(Cliente[] clientes, String dni) {
		String nombre = "";

		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i].getDni().equals(dni))
				nombre = clientes[i].getNombre();
		}

		return nombre;
	}

	// Aqui se devuelve en que sala de que cine se da la sesion
	public String salaConFechaYPelicula(Sesion sesion, Cine[] cines) {
		String nombreSalayCine = "";

		for (int nCines = 0; nCines < cines.length; nCines++) {
			for (int nSalas = 0; nSalas < cines[nCines].getSalas().length; nSalas++) {

				for (int nSesiones = 0; nSesiones < cines[nCines].getSalas()[nSalas]
						.getSesiones().length; nSesiones++) {

					Sesion sesionAcomparar = cines[nCines].getSalas()[nSalas].getSesiones()[nSesiones];

					if (sesionAcomparar.getIdSesion().equals(sesion.getIdSesion())) {
						nombreSalayCine = cines[nCines].getNombre_cine() + " - "
								+ cines[nCines].getSalas()[nSalas].getNomSala();
					}
				}
			}
		}

		return nombreSalayCine;
	}

	public void imprimirFactura(Entrada[] entradasArray, Cliente[] usuarios, String dni_usuario, String[] cinesYSalas,
			float descontado) {
		// TODO Auto-generated method stub
		String nombre = nombreUsuario(usuarios, dni_usuario);
		Calendar cal = Calendar.getInstance();
		File file = new File("factura.txt");
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		BufferedWriter fichero;

		try {
			fichero = new BufferedWriter(new FileWriter(file));
			fichero.write("Hola " + nombre + ", a continuacion te imprimimos la informacion pertinante a la compra:\n");
			fichero.write("\n");
			fichero.write("Num_Entrada\t" + "Pelicula\t" + "Cine - Sala\t\t\t\t\t" + "Dia\t\t\t" + "Hora\t" + "Precio\t"
					+ "Fecha compra\n");
			for (int i = 0; i < entradasArray.length; i++) {
				fichero.write("\t" + entradasArray[i].getCdEntrada() + "\t\t"
						+ entradasArray[i].getSesion().getPelicula().getNombre() + "\t" + cinesYSalas[i] + "\t"
						+ dt.format(entradasArray[i].getSesion().getFecha()) + "\t" + entradasArray[i].getHora() + "\t"
						+ entradasArray[i].getPrecio() + "€\t" + dt.format(cal.getTime()) + "\n");
			}
			fichero.write(
					"------------------------------------------------------------------------------------------------------\n");
			fichero.write("Como has comprado " + entradasArray.length + " entradas, te hemos hecho un descuento del "
					+ entradasArray.length + "0%\n");
			fichero.write("El coste final es: " + descontado + "€");
			fichero.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void compraRealizada(Entrada[] entradasArray, String dni_usuario, float descontado) {
		// TODO Auto-generated method stub
		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			PreparedStatement st = conexion.prepareStatement("insert into " + tablaTickets + " (" + costeTotalConDescuento + ", " + dniCliente
							+ ") values ('" + descontado + "', '" + dni_usuario + "');");
			st.executeUpdate();
			
			DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
			
			ResultSet registro = comando.executeQuery("select MAX(" + codigoTicket + ") as cod_ticket from ticket;");
			if (registro.next()) {
				
				int codTicket=registro.getInt(codigoTicket);
				for (int i = 0; i < entradasArray.length; i++) {
					
					comando.executeUpdate("insert into " + tablaEntradas + " (" + fechaSesion + ", " + horaSesion + ", "
							+ costePelicula + ", " + codSesion + ", " + codigoTicket + ") values ('"
							+ dt.format(entradasArray[i].getFecha()) + "', '" + entradasArray[i].getHora() + "', '"
							+ entradasArray[i].getPrecio() + "', '" + entradasArray[i].getSesion().getIdSesion()
							+ "', '" + codTicket + "');");
					
				}
			}
			
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] cifra(String sinCifrar) throws Exception {
		final byte[] bytes = sinCifrar.getBytes("UTF-8");
		final Cipher aes = obtieneCipher(true);
		final byte[] cifrado = aes.doFinal(bytes);
		return cifrado;
	}

	public String descifra(byte[] cifrado) throws Exception {
		final Cipher aes = obtieneCipher(false);
		final byte[] bytes = aes.doFinal(cifrado);
		final String sinCifrar = new String(bytes, "UTF-8");
		return sinCifrar;
	}

	private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
		final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
		final MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(frase.getBytes("UTF-8"));
		final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

		final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		if (paraCifrar) {
			aes.init(Cipher.ENCRYPT_MODE, key);
		} else {
			aes.init(Cipher.DECRYPT_MODE, key);
		}

		return aes;
	}
	
	
}

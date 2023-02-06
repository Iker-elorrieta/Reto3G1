package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Cine;
import modelo.Cliente;
import modelo.Sala;

public class Metodos {
	// declaro la base de datos remota junto al usuario y contraseña de mysql que he
	// creado allí.
	final String sConexion = "jdbc:mysql://10.5.14.202:3306/cines";
	final String user = "cliente"; 
	final String contra="Contraseña33#";
	// Aqui lee los datos de la tabla cines de la BD y la mete en una array que devuelve


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

	public Cine[] cuantasSalas(Cine[] cines) {
		// TODO Auto-generated method stub

		Sala[] salas = new Sala[0];

		for (int cont = 0; cont < cines.length; cont++) {
			try {
				Connection conexion = DriverManager.getConnection(sConexion, user, contra);
				Statement comando = conexion.createStatement();
				ResultSet registro = comando.executeQuery(
						"SELECT * FROM salas where cod_cine=(select cod_cine from cines where nombre_cine='"
								+ cines[cont].getNombre_cine() + "');");

				while (registro.next() == true) {
					Sala sala = new Sala();
					sala.setCdSala(registro.getString("cod_sala"));
					sala.setNomSala("nombre_sala");

					Sala[] arrayNuevo = new Sala[salas.length + 1];
					for (int i = 0; i < salas.length; i++) {
						arrayNuevo[i] = salas[i];
					}
					arrayNuevo[salas.length] = sala;
					salas = arrayNuevo;
					cines[cont].setSalas(salas);
				}
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cines;
	}

	public boolean validarDni(String text) {
		// TODO Auto-generated method stub
		boolean correcto=false;
		
		if(text.length() == 9) {
			correcto=true;
		}
		else
			correcto=false;
		
		
		return correcto;	
	}

	public Cliente[] registrarUsuario(String dni, String nombre, String apell1, String apell2, String sexoCB, String passw) {
		// TODO Auto-generated method stub
		
		try {
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			comando.executeUpdate("Insert into clientes values ('"+dni+"', '"+apell1+"', '"+dni+"', '"+apell2+"', '"+sexoCB+"', '"+passw+"');");
			
			conexion.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Cliente[] usuario = usuariosArray();
		
		return usuario;
	}
	
	public boolean esVacio(String texto) {
		boolean estaVacio=false;
		
		if(texto==null || texto=="")
			estaVacio=true;
		
		return estaVacio;
	}

}

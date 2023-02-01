package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Cine;

public class Metodos {
	
	final String sConexion="jdbc:mysql://10.5.14.202:3306/cines";
	final String user = "cliente";
	final String contra="Contraseña33#";
	
	
	
	

	public Cine[] cuantosCines() {
		// TODO Auto-generated method stub
		Cine[] cines= new Cine[0];
		
		
		try {
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM cines;");
			
			while (registro.next() == true) {
				Cine cine = new Cine();
				cine.setCod_cine(registro.getString("cod_cine"));
				cine.setNombre_cine(registro.getString("nombre_cine"));
				
				
				Cine[] arrayNuevo = new Cine[cines.length+1];
				for(int i =0;i<cines.length;i++)
				{
					arrayNuevo[i]=cines[i];
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
	
	
	
	
	
	
	
	
	
	
	
	
	
}

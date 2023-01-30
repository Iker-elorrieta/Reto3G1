package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Cines;

public class Metodos {
	
	final String sConexion="jdbc:mysql://localhost:3306/cines";
	final String user = "root";
	final String contra="";
	
	public void bienvSleep() {
		
		try {
			Thread.sleep(3000);
		} catch (Exception f) {
			f.printStackTrace();
		}
		
	}

	public Cines[] cuantosCines() {
		// TODO Auto-generated method stub
		Cines[] cines= new Cines[0];
		
		
		try {
			
			Connection conexion = DriverManager.getConnection(sConexion, user, contra);
			Statement comando = conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM cines;");
			
			while (registro.next() == true) {
				Cines cine = new Cines();
				
				
				
				
				Cines[] arrayNuevo = new Cines[cines.length+1];
				for(int i =0;i<cines.length;i++)
				{
					arrayNuevo[i]=cines[i];
				}
				arrayNuevo[cines.length] = cine;
				cines = arrayNuevo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cines;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

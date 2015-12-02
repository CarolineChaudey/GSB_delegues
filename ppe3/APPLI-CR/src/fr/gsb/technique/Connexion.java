package fr.gsb.technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import fr.gsb.entites.Delegue;

// A FERMER
public class Connexion {
	
	private static Connexion connexion = null;
	private static String dbURL = "jdbc:mysql://localhost:3306/GsbCRSlamV2015";
	private static Connection idConnection = null;
	
	private Connexion(){
		super();
		System.out.println("Connexion :: Connexion()");
	}
	
	public static Connexion getConnexion(){
		System.out.println("Connexion :: getConnexion()");
		if(connexion == null){
			connexion = new Connexion();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				idConnection = DriverManager.getConnection(dbURL, "root", "azerty");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Erreur de connexion");
			}		
		}
		
		return connexion;
	}
	
	public static void fermerConnexion(){
		System.out.println("Connexion :: fermerConnexion()");
		   try {
			idConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getIdConnection() {
		return idConnection;
	}
	
}

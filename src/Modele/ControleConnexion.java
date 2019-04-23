package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ControleConnexion {
	
	private static Connection laConnexion;
	static Statement statement;
	
	public ControleConnexion() {
		
		laConnexion = null;
		
	     try {
	         Class.forName("org.sqlite.JDBC");
	         System.out.println("Driver trouvé");
	         laConnexion = DriverManager.getConnection("jdbc:sqlite:sqlite/db/dbArtistes.db");
	         System.out.println("Connexion effectué");
	    
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      
	   }
	public  Connection getConnexion() {
		
		return laConnexion;
	}
	
	public static void fermerSession(){
		try{
			
			if (laConnexion!=null && !laConnexion.isClosed()) {
				laConnexion.close();
			}
		
		}
		catch(SQLException sqle){
	
		}
	}

	
		
}




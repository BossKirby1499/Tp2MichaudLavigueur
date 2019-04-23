package Vue;

import java.awt.Checkbox;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Modele.Albums;
import Modele.Artistes;
import Modele.ControleConnexion;

public class ModeleArtistes extends AbstractTableModel{
	
	private ArrayList<Artistes> lesDonnees;
	private final String[] lesTitres = {"Numéro", "Nom", "Membre"};
	ControleConnexion connexion;
	public  ModeleArtistes() {
		this.lesDonnees = obtenirArtistes();
	}
	@Override
	public int getColumnCount() {
		
		return lesTitres.length;
	}

	@Override
	public int getRowCount() {
		
		 return lesDonnees.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0 :
			return lesDonnees.get(rowIndex).getId();
		case 1 :
			return lesDonnees.get(rowIndex).getNom();
			
		case 2 :
			return lesDonnees.get(rowIndex).getMembre();


		default :	
			return null;
			
		}
	}
	public ArrayList<Artistes> getDonnees(){
		
		return lesDonnees;
	}
	public Class<?> getColumnClass(int columnIndex){
		switch (columnIndex) {
		   case 0: return int.class;
		   case 1: return String.class;
		   case 2: return boolean.class;

		   default: throw new IllegalArgumentException(" index de colonne invalide: "+columnIndex);
	 }
	}
	@Override
	public String getColumnName(int column) {
		
		return lesTitres[column];
	}
	
	public void ajouterArtistes(Artistes artiste) {
		lesDonnees.add(artiste);
		fireTableRowsInserted(lesDonnees.size() -1, lesDonnees.size() -1);
		
		}
	public ArrayList<Artistes> obtenirArtistes(){
		 lesDonnees= new ArrayList<Artistes>();
		try {
		
	   String requete = "SELECT * FROM Artistes";
		 connexion = new ControleConnexion();
       Statement statement = connexion.getConnexion().createStatement();
       ResultSet jeuResult = statement.executeQuery(requete);
       
        while(jeuResult.next()) {
        	
        	Artistes artistes = new Artistes(jeuResult.getInt("id"),jeuResult.getString("nom"),jeuResult.getBoolean("membre"),jeuResult.getString("photo"));
        	System.out.println(artistes.getNom());
        	lesDonnees.add(artistes);
        	
       }
        
		}catch(Exception e){
        	
        }

	return lesDonnees;
	
	}
	public ArrayList<Albums> obtenirAlbums(int row) {
		ArrayList<Albums> al = new ArrayList<Albums>();
		
	try {
		
		int id =getDonnees().get(row).getId();
		
		   String requete = "SELECT * FROM Albums WHERE numArtiste = ?";
			
		   PreparedStatement decla = connexion.getConnexion().prepareStatement(requete);
			
			decla.setInt(1,id);
			ResultSet result = decla.executeQuery();
			
	       
	        while(result.next()) {
	        	
	        	Albums albums = new Albums(result.getInt("idNumero"),result.getString("titre"),result.getString("genre"),result.getString("photo"),result.getInt("annee"));
	        	
	        	al.add(albums);
	        	
	       }
	        
			}catch(Exception e){
	        	
	        }
	return  al;
	}
	public void ajouterArtiste(Artistes artiste) {
		lesDonnees.add(artiste);
		fireTableRowsInserted(lesDonnees.size() -1, lesDonnees.size() -1);
		//notifie qu’une insertion en fin de liste a eu lieu.
		}

}

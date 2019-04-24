package Modele;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Artistes {
	
	int id;
	String nom;
	boolean membre;
	String photo;
	
	public Artistes(int id,String nom,boolean membre,String photo) {
		
		this.id = id;
		this.nom = nom;
		this.membre = membre;
		this.photo = photo;
	}
	public Artistes(int id) {
		
		this.id = id;
		this.nom = "";
		this.membre = false;
		this.photo = "defaut.png";
	}
	public int getId() {
		
		return id;
		
	}
	public String getNom() {
		
		return nom;
	}
	
	public boolean getMembre() {
		
		return membre;
	}
	
	public String getPhoto() {
		
		return photo;
		
	} 
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setMembre(boolean membre) {
		this.membre = membre;
	}



}

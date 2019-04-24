package Modele;

public class Albums {
	int id;
	String titre;
	String genre;
	String photo;
	int annee;
	int idArtiste;
	
	public Albums(int id,String titre,String genre,String photo,int annee) {
		
		this.id = id;
		this.titre = titre;
		this.genre = genre;
		this.photo = photo;
		this.annee = annee;
	}
	public Albums(int id) {
		
		this.id = id;
		this.titre = "";
		this.genre = "";
		this.photo = "album.png";
		this.annee = 0;
		this.idArtiste = 0;
	}
	public int getId() {
		
		return id;
	}
	public String getTitre() {
		
		return titre;
	}
	
	public String getGenre() {
		
		return genre;
	}
	
	public String getPhoto() {
		
		return photo;
		
	} 
	public int getAnnee() {
		
		return annee;
		
	} 
	@Override
	public String toString() {
		
		return Integer.toString(getAnnee())+" - "+getTitre();
	}

}

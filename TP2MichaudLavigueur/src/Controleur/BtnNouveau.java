package Controleur;

import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;

import Modele.Albums;
import Modele.Artistes;
import Modele.ControleConnexion;
import Vue.*;

public class BtnNouveau implements ActionListener{
	
	JButton btn;
	JTable table;
	JTextField num;
	JTextField nom;
	JCheckBox checkBox;
	DefaultListModel<Albums> liste;
	Image img;
	JLabel lblAlbums;
	JLabel lblArtiste;
	public BtnNouveau(JButton btn,JTable table,JTextField num,JTextField nom,JCheckBox checkBox, DefaultListModel<Albums> liste, Image imageArtiste,JLabel lblArtiste,JLabel lblAlbums) {
		this.btn = btn;
		this.table = table;
		this.num = num;
		this.nom = nom;
		this.checkBox = checkBox;
		this.liste = liste;
		this.img = imageArtiste;
		this.lblArtiste =lblArtiste;
		this.lblAlbums = lblAlbums;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btn) {
		
			
			try {
				
				String requete = "INSERT INTO  Artistes VALUES (?,?,?,?)";   
				Artistes artiste = new Artistes(Integer.parseInt(num.getText()),nom.getText(),checkBox.isSelected(),"defaut.png");
				ControleConnexion connexion = new ControleConnexion();
				PreparedStatement decl = connexion.getConnexion().prepareStatement(requete);
				
				decl.setInt(1, artiste.getId());
				decl.setString(2, artiste.getNom());
				decl.setBoolean(3, artiste.getMembre());
				decl.setString(4,artiste.getPhoto());
				
				decl.executeUpdate();
				
				  ((ModeleArtistes) table.getModel()).ajouterArtiste(artiste);
				}catch(Exception ex){
				        	System.out.println("Erreur"+ex);
			    }

		}else {
			int row = table.getModel().getRowCount()-1;
			int numero = (int) table.getModel().getValueAt(row,0)+1;
			num.setText(Integer.toString(numero));
			nom.setText("");
			checkBox.setSelected(false);
			liste.clear();
			img = new ImageIcon(Main.class.getResource("defaut.png")).getImage().getScaledInstance(130, 140, Image.SCALE_SMOOTH);
			lblArtiste.setIcon(new ImageIcon(img));
			lblAlbums.setIcon(new ImageIcon(img));
			table.clearSelection();
			
		}
	}

}

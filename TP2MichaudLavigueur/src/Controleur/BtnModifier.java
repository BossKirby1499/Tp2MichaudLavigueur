package Controleur;

import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Modele.Albums;
import Modele.Artistes;
import Modele.ControleConnexion;
import Vue.*;


public class BtnModifier implements ActionListener{



		
		JButton btnModifier;
		JTable table;
		JTextField num;
		JTextField nom;
		JCheckBox checkBox;
		DefaultListModel<Albums> liste;
		Image img;
		JLabel lblArtiste;
		ControleConnexion connexion;
		
		public BtnModifier(JButton btnModifier,JTable table,JTextField num,JTextField nom,JCheckBox checkBox, DefaultListModel<Albums> liste, Image imageArtiste,JLabel lblArtiste) {
			this.btnModifier = btnModifier;
			this.table = table;
			this.num = num;
			this.nom = nom;
			this.checkBox = checkBox;
			this.liste = liste;
			this.img = imageArtiste;
			this.lblArtiste =lblArtiste;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnModifier) {
			
				
				try {
					
					String requete = "UPDATE artistes SET Nom = ?, membre = ? WHERE id = ?";    
					 connexion = new ControleConnexion();
					PreparedStatement decl1 = connexion.getConnexion().prepareStatement(requete);
					int indiceRow = table.getSelectedRow();
					int temp = ((ModeleArtistes) table.getModel()).getDonnees().get(indiceRow).getId();
					((ModeleArtistes)table.getModel()).getDonnees().get(indiceRow).setNom(nom.getText());
					((ModeleArtistes)table.getModel()).getDonnees().get(indiceRow).setMembre(checkBox.isSelected());
					
					decl1.setString(1, nom.getText());
					decl1.setBoolean(2, checkBox.isSelected());
					decl1.setInt(3, temp);
				
					
					decl1.executeUpdate();
					
					((ModeleArtistes) table.getModel()).modifierArtistes(indiceRow,((ModeleArtistes)table.getModel()).getDonnees().get(indiceRow));
					
					}catch(Exception ex){
					        	System.out.println("Erreur"+ex);
				    }

			}else {
				
				int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cet artiste ?",
						"Vérification de la supression", JOptionPane.YES_NO_OPTION);
				System.out.println("R\u00E9ponse : " + reponse);
				
				if (reponse == 0) {
					if(liste.isEmpty()) {
				String request = "DELETE FROM artistes WHERE id = ?";
				int row = table.getSelectedRow();
				connexion  = new ControleConnexion();
				PreparedStatement decl1;
				try {
				decl1 = connexion.getConnexion().prepareStatement(request);
					
						
				
				int temp = ((ModeleArtistes) table.getModel()).getDonnees().get(row).getId();
				decl1.setInt(1, temp);
				decl1.executeUpdate();
				((ModeleArtistes) table.getModel()).supprimerEmploye(row);
		
				
				}catch (SQLException e1) {
					
					
				}
				}else{
					JOptionPane.showMessageDialog(null,"Vous ne pouvez pas supprimer un artiste avec des albums");
				}
				
			}
		}

	}
}
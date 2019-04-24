package Controleur;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Modele.Albums;
import Vue.Main;

public class ListListener implements ListSelectionListener{
	
	JList liste;
	JLabel albums;
	Image imageAlbums;
	
	public ListListener(JList liste,JLabel albums) {
		
		this.liste = liste;
		this.albums = albums;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		if(liste.getSelectedIndex() != -1) {
	
		Albums album = (Albums) liste.getModel().getElementAt(liste.getSelectedIndex());
		System.out.println(album.getPhoto());
		imageAlbums = new ImageIcon(Main.class.getResource(album.getPhoto())).getImage().getScaledInstance(100, 110, Image.SCALE_SMOOTH);
		albums.setIcon(new ImageIcon(imageAlbums));
		albums.setPreferredSize(new Dimension(120,90));
		}
	}

}

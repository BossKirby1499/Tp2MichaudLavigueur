package Vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import Controleur.BtnNouveau;
import Modele.Albums;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class Main extends JFrame {
	public Main() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 638, 520);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnRecherch = new JButton("Recherche");
		btnRecherch.setBounds(356, 52, 99, 31);
		panel.add(btnRecherch);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(479, 221, 106, 31);
		panel.add(btnModifier);
		
		JButton btnRemplacer = new JButton("Remplacer");
		btnRemplacer.setBounds(21, 243, 99, 31);
		panel.add(btnRemplacer);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(479, 179, 106, 31);
		panel.add(btnAjouter);
		
	  btnNouveau = new JButton("Nouveau");
	
		btnNouveau.setBounds(479, 137, 106, 31);
		panel.add(btnNouveau);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnQuitter.setBounds(479, 52, 106, 31);
		panel.add(btnQuitter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(479, 263, 106, 31);
		panel.add(btnSupprimer);
		
		JLabel lblRechercherUnArtiste = new JLabel("Rechercher un artiste");
		lblRechercherUnArtiste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRechercherUnArtiste.setBounds(21, 22, 143, 14);
		panel.add(lblRechercherUnArtiste);
		
		textField_artiste = new JTextField();
		textField_artiste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_artiste.setBounds(21, 50, 313, 31);
		panel.add(textField_artiste);
		textField_artiste.setColumns(10);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblArtistes.setBounds(21, 102, 143, 27);
		panel.add(lblArtistes);
		
		JLabel lblInformations = new JLabel("Informations");
		lblInformations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblInformations.setBounds(21, 340, 143, 27);
		panel.add(lblInformations);
		
		JLabel lblNumro = new JLabel("Num\u00E9ro");
		lblNumro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumro.setBounds(21, 400, 143, 14);
		panel.add(lblNumro);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNom.setBounds(21, 428, 143, 14);
		panel.add(lblNom);
		
		JLabel lblMembre = new JLabel("Membre");
		lblMembre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMembre.setBounds(21, 456, 143, 14);
		panel.add(lblMembre);
		
		textField_Num = new JTextField();
		textField_Num.setBounds(93, 399, 153, 20);
		panel.add(textField_Num);
		textField_Num.setColumns(10);
		
		textField_Nom = new JTextField();
		textField_Nom.setColumns(10);
		textField_Nom.setBounds(93, 427, 153, 20);
		panel.add(textField_Nom);
		
		JCheckBox checkBox_Membre = new JCheckBox("");
		checkBox_Membre.setBounds(93, 454, 97, 23);
		panel.add(checkBox_Membre);
		
		
		
		 modele = new ModeleArtistes();
		 
		table = new JTable(modele);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 table.getColumnModel().getColumn(0).setCellRenderer(new RendererGras());
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int numLigne;
				numLigne = table.getSelectedRow();
				int num = (int) modele.getValueAt(numLigne, 0);
				textField_Num.setText(Integer.toString(num));
				textField_Nom.setText((String)modele.getValueAt(numLigne, 1));
				
				if((boolean) modele.getValueAt(numLigne, 2)) {
					
					checkBox_Membre.setSelected(true);
				}else {
					checkBox_Membre.setSelected(false);
				}
				
				albums = modele.obtenirAlbums(numLigne);
				model.clear();
				for (Albums albums2 : albums) {
					model.addElement(albums2);
					System.out.println(albums2.toString());
				}
				System.out.println(modele.getDonnees().get(numLigne).getPhoto());
				
				imageArtiste = new ImageIcon(Main.class.getResource(modele.getDonnees().get(numLigne).getPhoto())).getImage().getScaledInstance(190, 150, Image.SCALE_SMOOTH);
				
				 lblArtiste.setIcon(new ImageIcon (imageArtiste));
				 lblArtiste.setPreferredSize(new Dimension(170,140));
				
			}
		});
		ascensseur = new JScrollPane(table);
		ascensseur.setBounds(170, 120, 300, 200);
		panel.add(ascensseur);
		
		img = new JPanel();
		img.setBounds(21, 137, 99, 95);
		lblArtiste = new JLabel();
		img.add(lblArtiste);
		panel.add(img);
		
		JPanel panel_image_album = new JPanel();
		panel_image_album.setBounds(513, 400, 99, 95);
		panel.add(panel_image_album);
	
	  model = new DefaultListModel();

		liste = new JList<Albums>(model);
		
		liste.setBounds(300, 350, 150, 170);
		pane = new JScrollPane(liste);
		panel.add(liste);
		
		BtnNouveau nouveau = new BtnNouveau(btnAjouter, table, textField_Num, textField_Nom, checkBox_Membre,model,imageArtiste,lblArtiste);
		btnNouveau.addActionListener(nouveau);
		btnAjouter.addActionListener(nouveau);
	}

    private static final long serialVersionUID = 1L;
    Frame j = new Frame();
    private JTextField textField_artiste;
    private JTextField textField_Num;
    public  JTextField textField_Nom;
    public JTable table;
    private JScrollPane ascensseur;
    private ModeleArtistes modele;
    private ArrayList<Albums> albums = new ArrayList<Albums>();
    JList liste;
   JScrollPane pane;
    DefaultListModel model;
    JPanel img;
   Image imageArtiste;
   JLabel lblArtiste;
   public static JButton btnNouveau;
    // Constructeur de la classe
public void multiLayout() {

    j.setLayout(new BorderLayout());

}

    public static void main( String[] args ) {
        Main j = new Main();
        j.multiLayout();
        j.setVisible(true);
        j.setSize (620, 580) ;
        j.setTitle ("TP2-Antoine Michaud et David Lavigueur") ;
        j.setResizable(false);
    }
}
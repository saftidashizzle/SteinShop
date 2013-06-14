package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MitarbeiterMenuPanel extends JPanel {
	private JButton logoutButton;
	private JButton artikelAnlegenButton;
	private JButton mengeAendernButton; 
	private JButton artikelLoeschenButton;
	private JButton mitarbeiterLoeschenButton;
	private JButton artikelProtokollButton;
	private JButton mitarbeiterRegistrierenButton;
	
	public MitarbeiterMenuPanel() {
		super();
		this.setLayout(new GridLayout(7,1));
		
		artikelAnlegenButton = new JButton("Neuen Artikel anlegen");
		this.add(artikelAnlegenButton);
		
		mengeAendernButton = new JButton("Artikelmenge �ndern");
		this.add(mengeAendernButton);
		
		artikelLoeschenButton = new JButton("Artikel l�schen");
		this.add(artikelLoeschenButton);
		
		mitarbeiterRegistrierenButton = new JButton("Mitarbeiter registrieren");
		this.add(mitarbeiterRegistrierenButton);
		
		mitarbeiterLoeschenButton = new JButton("Mitarbeiter l�schen");
		this.add(mitarbeiterLoeschenButton);
		
		artikelProtokollButton = new JButton("Artikelmengenverlauf f�r bestimmten Artikel anzeigen");
		this.add(artikelProtokollButton);
		
		logoutButton = new JButton("Logout");
		this.add(logoutButton);

	}
	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
}

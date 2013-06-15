package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MitarbeiterMenuPanel extends JPanel {

	private static final long serialVersionUID = 4773311197532823467L;
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
		
		mengeAendernButton = new JButton("Artikelmenge ändern");
		this.add(mengeAendernButton);
		
		artikelLoeschenButton = new JButton("Artikel löschen");
		this.add(artikelLoeschenButton);
		
		mitarbeiterRegistrierenButton = new JButton("Mitarbeiter registrieren");
		this.add(mitarbeiterRegistrierenButton);
		
		mitarbeiterLoeschenButton = new JButton("Mitarbeiter löschen");
		this.add(mitarbeiterLoeschenButton);
		
		artikelProtokollButton = new JButton("Artikelmengenverlauf für bestimmten Artikel anzeigen");
		this.add(artikelProtokollButton);
		
		logoutButton = new JButton("Logout");
		this.add(logoutButton);

	}
	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
	public void addActionListenerNewArt(ActionListener a) {
		this.artikelAnlegenButton.addActionListener(a);
	}
	public void addActionListenerArtMeng(ActionListener a) {
		this.mengeAendernButton.addActionListener(a);
	}
	public void addActionListenerDelArt(ActionListener a) {
		this.artikelLoeschenButton.addActionListener(a);
	}
	public void addActionListenerMitReg(ActionListener a) {
		this.mitarbeiterRegistrierenButton.addActionListener(a);
	}
	public void addActionListenerUsrDel(ActionListener a) {
		this.mitarbeiterLoeschenButton.addActionListener(a);
	}
}

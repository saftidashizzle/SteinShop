package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MitarbeiterMenuPanel extends JPanel {
	/**
	 * Klasse um das MitarbeiterMenü darzustellen
	 */
	private static final long serialVersionUID = 4773311197532823467L;
	private JButton logoutButton;
	private JButton artikelAnlegenButton;
	private JButton mengeAendernButton; 
	private JButton artikelLoeschenButton;
	private JButton mitarbeiterLoeschenButton;
	private JButton artikelProtokollButton;
	private JButton mitarbeiterRegistrierenButton;
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
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
	/**
	 * Fügt einen ActionListener zum Logout Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Artikel anlegen Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerNewArt(ActionListener a) {
		this.artikelAnlegenButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Menge ändern Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerArtMeng(ActionListener a) {
		this.mengeAendernButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Artikel löschen Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerDelArt(ActionListener a) {
		this.artikelLoeschenButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Mitarbeiter Registrieren Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerMitReg(ActionListener a) {
		this.mitarbeiterRegistrierenButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Mitarbeiter löschen Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerUsrDel(ActionListener a) {
		this.mitarbeiterLoeschenButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Artikel Protokoll Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerProtokoll(ActionListener a) {
		this.artikelProtokollButton.addActionListener(a);
	}
}

package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ArtikelPanel extends JPanel {
	JPanel artikelListe;
	public ArtikelPanel() {
		super();
		
		this.setLayout(new BorderLayout());
		
		this.add(new JLabel("Artikelliste"), BorderLayout.NORTH);
	
		artikelListe = new JPanel();
		artikelListe.setLayout(new GridLayout(2,5));	
		this.add(artikelListe, BorderLayout.CENTER);
		
		// Artikelliste erstellen
		artikelListe.add(new JLabel("Nummer"));
		artikelListe.add(new JLabel("Name"));
		artikelListe.add(new JLabel("Anzahl"));
		artikelListe.add(new JLabel("Einzelpreis"));
		artikelListe.add(new JLabel("Packungsgröße"));
		
		// Artikel laden
		artikelListe.add(new JLabel("2"));
		artikelListe.add(new JLabel("ZWEISTEIN"));
		artikelListe.add(new JLabel("10"));
		artikelListe.add(new JLabel("10"));
		artikelListe.add(new JLabel("1"));
		
		
		this.add(artikelListe, BorderLayout.NORTH);
		
	}
}

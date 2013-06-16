package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.local.valueobjects.Artikel;


public class ArtikelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 953509472024081560L;
	JPanel artikelListe;
	JPanel titelZeile;
	List<Artikel> liste;
	public ArtikelPanel(List<Artikel> liste) {
		super();
		this.setLayout(new BorderLayout());
		
		
		artikelListe = new JPanel();
		titelZeile = new JPanel();
		
		artikelListe.setLayout(new GridLayout(liste.size(),5));	
		titelZeile.setLayout(new GridLayout(1,5));
		
		// Artikelliste erstellen
		titelZeile.add(new JLabel("Nummer"));
		titelZeile.add(new JLabel("Name"));
		titelZeile.add(new JLabel("Anzahl"));
		titelZeile.add(new JLabel("Einzelpreis"));
		titelZeile.add(new JLabel("Packungsgröße"));

		// Artikel laden
		fill(liste);
//		for (Artikel a : liste){
//			artikelListe.add(new JLabel("" + a.getNummer()));
//			artikelListe.add(new JLabel(a.getName()));
//			artikelListe.add(new JLabel("" + a.getMenge()));
//			artikelListe.add(new JLabel("" + a.getPreis()));
//			artikelListe.add(new JLabel("PACKUNGSGROESSE"));
//		}
		
		
//		artikelListe.add(new JLabel("2"));
//		artikelListe.add(new JLabel("ZWEISTEIN"));
//		artikelListe.add(new JLabel("10"));
//		artikelListe.add(new JLabel("10"));
//		artikelListe.add(new JLabel("1"));
		
		this.add(titelZeile, BorderLayout.NORTH);
		this.add(artikelListe, BorderLayout.CENTER);
	}
	public void fill(List<Artikel> liste) {
		Iterator<Artikel> it = liste.iterator();
		while  (it.hasNext()) {
			Artikel a = it.next();
			artikelListe.add(new JLabel("" + a.getNummer()));
			artikelListe.add(new JLabel(a.getName()));
			artikelListe.add(new JLabel("" + a.getMenge()));
			artikelListe.add(new JLabel("" + a.getPreis()));
			artikelListe.add(new JLabel("" + a.getPackungsgroesse()));
		}
	}
}

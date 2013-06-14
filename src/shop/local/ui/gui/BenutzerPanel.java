package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.User;


public class BenutzerPanel extends JPanel {
	JPanel benutzerListe;
	JPanel titelZeile;
	public BenutzerPanel(List<User> liste) {
		super();
		
		this.setLayout(new BorderLayout());
		
		
		benutzerListe = new JPanel();
		titelZeile = new JPanel();
		
		benutzerListe.setLayout(new GridLayout(liste.size(),2));	
		titelZeile.setLayout(new GridLayout(1,5));
		
		// Artikelliste erstellen
		titelZeile.add(new JLabel("Nummer"));
		titelZeile.add(new JLabel("Name"));
		titelZeile.add(new JLabel("Adresse"));


		// Artikel laden
//		Iterator<Artikel> it = liste.iterator();
//		while  (it.hasNext()) {
//			Artikel a = it.next();
//			benutzerListe.add(new JLabel("" + a.getNummer()));
//			benutzerListe.add(new JLabel(a.getName()));
//			benutzerListe.add(new JLabel("" + a.getMenge()));
//			benutzerListe.add(new JLabel("" + a.getPreis()));
//			benutzerListe.add(new JLabel("PACKUNGSGROESSE"));
//		}
		for (User u : liste){
			benutzerListe.add(new JLabel("" + u.getNummer()));
			benutzerListe.add(new JLabel(u.getName()));
			if (u instanceof Kunde) {
				benutzerListe.add(new JLabel(u.getAdresse()));
			} else { 
					benutzerListe.add(new JLabel(" "));
			}
		}
		
		
//		artikelListe.add(new JLabel("2"));
//		artikelListe.add(new JLabel("ZWEISTEIN"));
//		artikelListe.add(new JLabel("10"));
//		artikelListe.add(new JLabel("10"));
//		artikelListe.add(new JLabel("1"));
		this.add(titelZeile, BorderLayout.NORTH);
		this.add(benutzerListe, BorderLayout.CENTER);
	}

}
package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarenkorbPanel extends JPanel {
	JPanel artikelListe;
	JPanel kassenZeile;
	public WarenkorbPanel() {
		super();
		
		this.setLayout(new BorderLayout());
		
		this.add(new JLabel("Warenkorb"), BorderLayout.NORTH);
	
		artikelListe = new JPanel();
		artikelListe.setLayout(new GridLayout(5,3));	
		this.add(artikelListe, BorderLayout.CENTER);
		
		// Artikelliste erstellen
		artikelListe.add(new JLabel("Name"));
		artikelListe.add(new JLabel("Anzahl"));
		artikelListe.add(new JLabel("Preis"));
		
		// Artikel laden
		artikelListe.add(new JLabel("Artikel"));
		artikelListe.add(new JLabel("10"));
		artikelListe.add(new JLabel("10*10=100"));
		
		
		kassenZeile = new JPanel();
		kassenZeile.setLayout(new FlowLayout());		
		kassenZeile.add(new JLabel("Endpreis: 100"));
		kassenZeile.add(new JButton("Kasse"));
		this.add(kassenZeile, BorderLayout.SOUTH);
		this.add(artikelListe, BorderLayout.NORTH);
		
	}
}

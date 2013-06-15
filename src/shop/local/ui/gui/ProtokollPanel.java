package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import shop.local.valueobjects.Ereignis;

public class ProtokollPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2332524247825957602L;

	public ProtokollPanel(List<Ereignis> liste) {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JPanel titelZeile = new JPanel();
		add(titelZeile, BorderLayout.NORTH);
		titelZeile.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel_1 = new JLabel("Datum");
		titelZeile.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User");
		titelZeile.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Artikel");
		titelZeile.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Anzahl");
		titelZeile.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Aktion");
		titelZeile.add(lblNewLabel);
		
		JPanel ereignisListe = new JPanel();
		ereignisListe.setLayout(new GridLayout(liste.size(), 5));
		
//		JScrollPane ereignisListe = new JScrollPane();
//		ereignisListe.setLayout(new ScrollPaneLayout());				
		for (Ereignis a : liste){
			ereignisListe.add(new JLabel(a.getDate().toString()));
			ereignisListe.add(new JLabel(a.getUser().getName()));
			ereignisListe.add(new JLabel(a.getArtikel().getName()));
			ereignisListe.add(new JLabel("" + a.getMenge()));
			ereignisListe.add(new JLabel(a.getAktion()));
		}
		add(ereignisListe, BorderLayout.CENTER);
	}

}

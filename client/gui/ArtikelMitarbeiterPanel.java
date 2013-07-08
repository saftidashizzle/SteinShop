package gui;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import valueobjects.Artikel;

public class ArtikelMitarbeiterPanel extends ArtikelPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7837851496937539934L;
	public ArtikelMitarbeiterPanel(List<Artikel> liste) {
		super(liste);
	}
	public void fill(List<Artikel> liste) {
		String[] columnNames = {"Nummer",
                "Name",
                "Anzahl",
                "Einzelpreis",
                "Packungsgröße"};
		
		data = new Object[liste.size()][5];
		int i = 0;
		for (Artikel a:liste) {
			String[] row = { ""  + a.getNummer(), a.getName(), "" + a.getMenge(), "" + a.getPreis(), "" + a.getPackungsgroesse() };
			data[i++] = row;
		}
		i = 0;
		model = new ArtikelTableModell(data, columnNames);
	
		
		artikelListe = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1824987424405259505L;

			public boolean isCellEditable(int x, int y) {
				if (y==0) {
					return false;
				} else {
					return true;
				}
			}
		};
		artikelListe.setAutoCreateRowSorter(true);
		artikelScroll = new JScrollPane(artikelListe);
	}
}

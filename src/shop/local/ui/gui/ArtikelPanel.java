package shop.local.ui.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import shop.local.valueobjects.Artikel;


public class ArtikelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 953509472024081560L;

	JScrollPane artikelScroll;
	JTable artikelListe;
	Object[][] data;
	TableModel model;
	public ArtikelPanel(List<Artikel> liste) {
		super();
		this.setLayout(new GridLayout(1,1));
				
		// Artikel laden
		fill(liste);
		this.add(artikelScroll);
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
		
		artikelListe = new JTable(model);
		artikelScroll = new JScrollPane(artikelListe);
	}
}
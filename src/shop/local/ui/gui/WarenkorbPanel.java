package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Warenkorb;

public class WarenkorbPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4781761849520709380L;
	JTable artikelListe;
	JScrollPane artikelScroll;
	JPanel kassenZeile;
	Object[][] data;
	TableModel model;
	public WarenkorbPanel(Warenkorb warenkorb) {
		super();
		
		this.setLayout(new BorderLayout ());
//		
//		artikelListe = new JPanel();
//		
//		// Artikelliste erstellen
//		artikelZeile = new JPanel();
//		artikelZeile.setLayout(new GridLayout(2,3));
//		artikelZeile.add(new JLabel(" "));
//		artikelZeile.add(new JLabel("Warenkorb"));
//		artikelZeile.add(new JLabel(" "));
//		artikelZeile.add(new JLabel("Name"));
//		artikelZeile.add(new JLabel("Anzahl"));
//		artikelZeile.add(new JLabel("Gesamtpreis"));
//
//		this.add(artikelZeile, BorderLayout.NORTH);
		fill(warenkorb);
		
		kassenZeile = new JPanel();
		kassenZeile.setLayout(new FlowLayout());	
		kassenZeile.add(new JButton("Kasse"));
		this.add(kassenZeile, BorderLayout.SOUTH);		
	}
	public void fill(Warenkorb w) {
		String[] columnNames = {"Name",
                "Anzahl",
                "Einzelpreis",
                "Gesamtpreis"};
		HashMap<Artikel, Integer> warenkorb = w.getInhalt();
		//wenn warenkorb leer ist
		if(warenkorb.isEmpty()){
			artikelListe.add(new JLabel("Warenkorb ist leer."));
		} else {
		//die einzelnen Elemente aus der HashMap durchgehen und ausgeben
			data = new Object[warenkorb.size()+1][4];
			int i = 0;
			double preis = 0;
			for (Artikel key:warenkorb.keySet()) {
				String[] row = { ""  + key.getName(), "" + warenkorb.get(key), "" + key.getPreis(), "" + key.getPreis()*warenkorb.get(key) };
				preis = preis + key.getPreis()*warenkorb.get(key);
				data[i++] = row;
			}
			String[] row = { " ", " ", " ", "" + preis };
			data[i] = row;
			i = 0;
		}
		model = new DefaultTableModel(data, columnNames);
        artikelListe = new JTable(model) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -9187534973180919697L;

			public boolean isCellEditable(int x, int y) {
                return false;
            }
        };
		artikelScroll = new JScrollPane(artikelListe);
		this.add(artikelScroll, BorderLayout.CENTER);
	}
}

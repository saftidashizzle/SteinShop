package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import valueobjects.Artikel;
import valueobjects.Warenkorb;

public class WarenkorbPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4781761849520709380L;
	JTable warenkorbListe;
	JScrollPane artikelScroll;
	JPanel kassenZeile;
	Object[][] data;
	JButton kassenButton;
	TableModel model;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 * @param warenkorb
	 */
	public WarenkorbPanel(Warenkorb warenkorb) {
		super();
		
		this.setLayout(new BorderLayout ());

		fill(warenkorb);
		
		kassenZeile = new JPanel();
		kassenZeile.setLayout(new FlowLayout());
		kassenButton = new JButton("Kasse");
		kassenZeile.add(kassenButton);
		this.add(kassenZeile, BorderLayout.SOUTH);		
	}
	/**
	 * Methode, die den Warenkorb befüllt
	 * @param w
	 */
	public void fill(Warenkorb w) {
		String[] columnNames = {"Nummer",
				"Name",
                "Anzahl",
                "Einzelpreis",
                "Gesamtpreis"};
		HashMap<Artikel, Integer> warenkorb = w.getInhalt();
		//wenn warenkorb leer ist
		if(warenkorb.isEmpty()){
			String [] row = { "Keine ", "Artikel ", "im ", "Warenkorb ", "KAUF WAS!" };
			data = new Object[1][5];
			data [0] = row;
		} else {
		//die einzelnen Elemente aus der HashMap durchgehen und ausgeben
			data = new Object[warenkorb.size()+1][5];
			int i = 0;
			double preis = 0;
			for (Artikel key:warenkorb.keySet()) {
				String[] row = { ""  + key.getNummer(), ""  + key.getName(), "" + warenkorb.get(key), "" + key.getPreis(), "" + key.getPreis()*warenkorb.get(key) };
				preis = preis + key.getPreis()*warenkorb.get(key);
				data[i++] = row;
			}
			String[] row = {" ", " ", " ", " ", "" + preis };
			data[i] = row;
			i = 0;
		}
		model = new WarenkorbTableModell(data, columnNames);
        warenkorbListe = new JTable(model) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -9187534973180919697L;

			public boolean isCellEditable(int x, int y) {
                return false;
            }
        };
		artikelScroll = new JScrollPane(warenkorbListe);
		this.add(artikelScroll, BorderLayout.CENTER);
	}
	/**
	 * Fügt einen ActionListener zum Kassen Button hinzu
	 * @param a
	 */
	public void addActionListenerZurKasse(ActionListener a) {
		this.kassenButton.addActionListener(a);
	}
}

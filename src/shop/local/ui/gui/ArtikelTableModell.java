package shop.local.ui.gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;

public class ArtikelTableModell extends DefaultTableModel {
	Object[][] data;
	int rowCount;	
	
	public ArtikelTableModell(Object[][] data, String[] columnNames) {
		super(data, columnNames);
		rowCount = data.length;
		
	}
	/*
	 * Ziel: Ein Table Modell f�r: Artikel, Benutzer, Ereignisse, Warenkorb
	 * 
	 * Mehrere updateDAtaVector funktionen: je nachdem was f�r ein Typ von Liste �bergeben wird
	 * 
	 */
	public void updateDataVector(List<Artikel> liste) {
			
			String[] columnNames = {"Nummer",
	                "Name",
	                "Anzahl",
	                "Einzelpreis",
	                "Packungsgr��e"};
			data = new Object[liste.size()][5];
			int i = 0;
			for (Artikel a:liste) {
				String[] row = { ""  + a.getNummer(), a.getName(), "" + a.getMenge(), "" + a.getPreis(), "" + a.getPackungsgroesse() };
				data[i++] = row;
			}
			i = 0;
			
			setDataVector(data, columnNames);
	}
}

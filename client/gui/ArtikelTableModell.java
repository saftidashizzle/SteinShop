package gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import valueobjects.Artikel;

public class ArtikelTableModell extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8059390534653512407L;
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

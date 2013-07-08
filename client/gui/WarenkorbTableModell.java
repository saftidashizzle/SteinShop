package gui;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import valueobjects.Artikel;

public class WarenkorbTableModell extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812836455861466744L;
	Object[][] data;
	int rowCount;
	
	public WarenkorbTableModell(Object[][] data, String[] columnNames) {
		super(data, columnNames);
		rowCount = data.length;
		
	}
	public void updateDataVector(HashMap<Artikel, Integer> warenkorb) {
		
		String[] columnNames = {"Nummer",
                "Name",
                "Einzelpreis",
                "Anzahl",
                "Gesamtpreis"};
		
		data = new Object[warenkorb.size()+1][5];
		int i =0;
		double preis = 0;
		for(Artikel key : ( warenkorb).keySet()){
			String[] row = {"" + key.getNummer(), key.getName(), "" + key.getPreis(), "" + warenkorb.get(key), "" + key.getPreis()*warenkorb.get(key) };
			data[i++] = row;
			preis = preis + key.getPreis()*warenkorb.get(key);
		}
		
		String[] row = {" ", " ", " ", " ", "" + preis };
		data[i] = row;
		i = 0;
		setDataVector(data, columnNames);
	}
}


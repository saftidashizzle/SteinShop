package shop.local.ui.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Warenkorb;

public class WarenkorbTableModell extends DefaultTableModel {
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
		
		data = new Object[warenkorb.size()][5];
		int i =0;
		for(Artikel key : ( warenkorb).keySet()){
			String[] row = {"" + key.getNummer(), key.getName(), "" + key.getPreis(), "" + warenkorb.get(key), "" + key.getPreis()*warenkorb.get(key) };
			data[i++] = row;
		}
		setDataVector(data, columnNames);
	}
}


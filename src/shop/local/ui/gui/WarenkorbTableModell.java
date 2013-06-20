package shop.local.ui.gui;

import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Warenkorb;

public class WarenkorbTableModell extends DefaultTableModel {
	JTable artikelListe;
	Object[][] data;
	int rowCount;
	
	public WarenkorbTableModell(Object[][] data, String[] columnNames) {
		super(data, columnNames);
		rowCount = data.length;
		
	}
	public void updateDataVector(List<Artikel> liste) {
		
		String[] columnNames = {"Nummer",
                "Name",
                "Einzelpreis",
                "Anzahl",
                "Gesamtpreis"};
		
		data = new Object[liste.size()][5];
		int i = 0;
		for (Artikel a:liste) {
			String[] row = { ""  + a.getNummer(), a.getName(), "" + a.getPreis(), "" + w.getAnzahl(), "" + a.getPackungsgroesse() };
			data[i++] = row;
		}
		i = 0;
		
		setDataVector(data, columnNames);
	}
}
}

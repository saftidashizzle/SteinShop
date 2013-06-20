package shop.local.ui.gui;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.User;


public class BenutzerTableModell extends DefaultTableModel {
	Object[][] data;
	int rowCount;	
	
	public BenutzerTableModell(Object[][] data, String[] columnNames) {
		super(data, columnNames);
		rowCount = data.length;
		
	}
	
	public void updateDataVector(List<User> liste) {
			
			String[] columnNames = {"Name",
	                "Passwort",
	                "Nummer",
	                "Anrede",
	                "Vor und Nachname"};
			data = new Object[liste.size()][5];
			int i = 0;
			for (User u:liste) {
				String[] row = { ""  + u.getName(), "" + u.getPasswort(), "" + u.getNummer(), "" + u.getAnrede(), "" + u.getVorUndZuName() };
				data[i++] = row;
			}
			i = 0;
			
			setDataVector(data, columnNames);
	}
}

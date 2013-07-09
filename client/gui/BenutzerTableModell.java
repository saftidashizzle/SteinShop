package gui;

import javax.swing.table.DefaultTableModel;


public class BenutzerTableModell extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7597577756791690514L;
	Object[][] data;
	int rowCount;	
	
	public BenutzerTableModell(Object[][] data, String[] columnNames) {
		super(data, columnNames);
		rowCount = data.length;
		
	}
	
	public void updateDataVector(Object[][] liste) {
			
			String[] columnNames = {"Nummer",
	                "Name",
	                "Anrede",
	                "Vor und Nachname"
	                };			
			setDataVector(liste, columnNames);
	}
}

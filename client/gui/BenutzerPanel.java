package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;


public class BenutzerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8891187975556008249L;
	JTable benutzerListe;
	JScrollPane benutzerScroll;
    TableModel model;
    
	public BenutzerPanel(Object[][] liste) {
		super();
		this.setLayout(new GridLayout(1,1));

		// Artikel laden
		fill(liste);
		this.add(benutzerScroll);
	}
	public void fill(Object[][] liste) {
		String[] columnNames = {"Name",
                "Passwort",
                "Nummer",
                "Anrede",
                "Vor und Nachname"};
		
        model = new BenutzerTableModell(liste, columnNames);
		benutzerListe = new JTable(model);
		
		benutzerListe.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
	            		if(e.getValueIsAdjusting()) return;
	            	        int row = benutzerListe.getSelectedRow()+1;
	            	        System.out.println("Selected row: "+row);
	        }            
        });
		
		benutzerScroll = new JScrollPane(benutzerListe);
		
		
	}

}
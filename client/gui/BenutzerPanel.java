package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    
    /**
     * Setzen des Layouts und befüllen mit Inhalt
     * @param liste
     */
	public BenutzerPanel(Object[][] liste) {
		super();
		this.setLayout(new GridLayout(1,1));

		// Artikel laden
		fill(liste);
		this.add(benutzerScroll);
	}
	/**
	 * Methode, zum befüllen einer Liste
	 * @param liste
	 */
	public void fill(Object[][] liste) {
		String[] columnNames = {"Nummer",
                "Name",
                "Anrede",
                "Vor und Nachname"};
		
        model = new BenutzerTableModell(liste, columnNames);
		benutzerListe = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4378437356611858223L;

			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		benutzerListe.setAutoCreateRowSorter(true);

		

		benutzerScroll = new JScrollPane(benutzerListe);
		
	}
	/**
	 * Fügt einen SelectionListener zur Benutzerliste hinzu
	 * @param a
	 */
	public void addListSelectionListener(ListSelectionListener a) {
		benutzerListe.getSelectionModel().addListSelectionListener(a);

	}

}
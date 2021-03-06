package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import valueobjects.Ereignis;

public class ProtokollPanel extends JPanel {
	/**
	 * Klasse um die EreignisListe anzuzeigen
	 */
	
	JTable ereignisListe;
	JScrollPane ereignisScroll;
	Object[][] data;
    TableModel model;
	private static final long serialVersionUID = 2332524247825957602L;
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 * @param liste Ereignisliste
	 */
	public ProtokollPanel(List<Ereignis> liste) {
		super();
		setLayout(new GridLayout(1, 1));
		fill(liste);				
		this.add(ereignisScroll);
	}
	/**
	 * Methode zum bef�llen der Tabelle
	 * @param liste die Ereignisliste
	 */
	public void fill(List<Ereignis> liste) {
		String[] columnNames = {"Datum",
                "Artikel",
                "Anzahl",
                "Aktion"};
		
		data = new Object[liste.size()][4];
		int i = 0;
		for (Ereignis a:liste) {
			String[] row = { ""  + a.getDate(), a.getArtikel().getName(), "" + a.getMenge(), "" + a.getAktion() };
			data[i++] = row;
		}
		i = 0;
		
        model = new DefaultTableModel(data, columnNames);
        ereignisListe = new JTable(model) {
			private static final long serialVersionUID = -9187534973180919697L;

			public boolean isCellEditable(int x, int y) {
                return false;
            }
        };
		ereignisListe.setAutoCreateRowSorter(true);
		ereignisScroll = new JScrollPane(ereignisListe);
	}

}

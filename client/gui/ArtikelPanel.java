package gui;

import java.awt.GridLayout;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import valueobjects.Artikel;


public class ArtikelPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 953509472024081560L;

	JScrollPane artikelScroll;
	JTable artikelListe;
	Object[][] data;
	TableModel model;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 * @param liste
	 */
	public ArtikelPanel(List<Artikel> liste) {
		super();
		this.setLayout(new GridLayout(1,1));
				
		// Artikel laden
		fill(liste);
		this.add(artikelScroll);
	}
	/**
	 * Methode, die eine liste befüllt
	 * @param liste
	 */
	public void fill(List<Artikel> liste) {
		String[] columnNames = {"Nummer",
                "Name",
                "Anzahl",
                "Einzelpreis",
                "Packungsgröße"};
		
		data = new Object[liste.size()][5];
		int i = 0;
		for (Artikel a:liste) {
			String[] row = { ""  + a.getNummer(), a.getName(), "" + a.getMenge(), "" + a.getPreis(), "" + a.getPackungsgroesse() };
			data[i++] = row;
		}
		i = 0;
		model = new ArtikelTableModell(data, columnNames);
	
		
		artikelListe = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1824987424405259505L;

			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		artikelListe.setAutoCreateRowSorter(true);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( model );
		sorter.setComparator( 0, new Comparator<String>() {
			  public int compare( String s1, String s2 )
			  {
			    int i1 = Integer.parseInt( s1 );
			    int i2 = Integer.parseInt( s2 );
			    if(i1>i2) {
			    	return 1;
			    } else if (i2==i1) {
			    	return 0;
			    } else {
			    	return -1;
			    }
			  }
			} );
		artikelListe.setRowSorter(sorter);
		artikelScroll = new JScrollPane(artikelListe);
	}
	/**
	 * Fügt einen SelectionListener der Liste hinzu
	 * @param a
	 */
	public void addListSelectionListener(ListSelectionListener a) {
		artikelListe.getSelectionModel().addListSelectionListener(a);
	}
}
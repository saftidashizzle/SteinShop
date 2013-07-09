package gui;

import java.util.Comparator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import valueobjects.Artikel;

public class ArtikelMitarbeiterPanel extends ArtikelPanel {

	/**
	 * Panel für die Artikelliste spezifisch für einen Mitarbeiter.
	 * Eigentlich überflüssig, da es sich noch nicht von dem, für den Kunden
	 * unterscheidet. Jedoch benötigten wir eine eigene Klasse, da beim Kunden
	 * im Ausblick der ArtikelTable nicht editierbar sein sollte, während
	 * der Mitarbeiter darüber die Werte verändern kann.
	 */
	private static final long serialVersionUID = -7837851496937539934L;
	/**
	 * Konstruktor, der die Artikelliste übergeben bekommt. Und diese an die Elternklasse ArtikelPanel weitergibt.
	 * @param liste
	 */
	public ArtikelMitarbeiterPanel(List<Artikel> liste) {
		super(liste);
	}
	/**
	 * Methode die, die Tabelle befüllt.
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
				// das ist für editierung direkt im table und bestätigung mittels enter, haben wir allerdings doch nicht implementiert, 
				/*
				if (y==0) {
					return false;
				} else {
					return true;
				}*/
				return false;
			}
		};
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
}

package shop.local.ui.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import shop.local.valueobjects.User;


public class BenutzerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8891187975556008249L;
	JTable benutzerListe;
	JScrollPane benutzerScroll;
	Object[][] data;
    TableModel model;
    
	public BenutzerPanel(List<User> liste) {
		super();
		this.setLayout(new GridLayout(1,1));

		// Artikel laden
		fill(liste);
		this.add(benutzerScroll);
	}
	public void fill(List<User> liste) {
		String[] columnNames = {"Name",
                "Passwort",
                "Nummer",
                "Anrede",
                "Vor und Nachname"};
		data = new Object[liste.size()][5];
		int i = 0;
		for (User u:liste) {
			String[] row = { "" + u.getNummer(), ""  + u.getName(), "" + u.getNummer(), "" + u.getAnrede(), "" + u.getVorUndZuName() };
			data[i++] = row;
		}
		i = 0;
		
        model = new BenutzerTableModell(data, columnNames);
		benutzerListe = new JTable(model);
//		{
//            /**
//			 * 
//			 */
//			private static final long serialVersionUID = -9187534973180919697L;
//
//			public boolean isCellEditable(int x, int y) {
//                return false;
//            }
//        };

		
		benutzerScroll = new JScrollPane(benutzerListe);

	}

}
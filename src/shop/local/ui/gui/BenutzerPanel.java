package shop.local.ui.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
		String[] columnNames = {"Nummer",
                "Name",
                "Adresse"
        };
		data = new Object[liste.size()][3];
		int i = 0;
		for (User a:liste) {
			String[] row = { ""  + a.getNummer(), a.getName(), "" + a.getAdresse() };
			data[i++] = row;
		}
		i = 0;
		
        model = new DefaultTableModel(data, columnNames);
		benutzerListe = new JTable(model) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -9187534973180919697L;

			public boolean isCellEditable(int x, int y) {
                return false;
            }
        };

//		benutzerListe = new JTable(data, columnNames);
		
		benutzerScroll = new JScrollPane(benutzerListe);

	}

}
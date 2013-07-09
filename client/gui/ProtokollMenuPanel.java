package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

import valueobjects.Artikel;
/**
 * Panel f�r das Men�, was man bei der Anzeige vom Artikelmengeverlauf ben�tigt.
 */
public class ProtokollMenuPanel extends JPanel {
	
	private static final long serialVersionUID = -1550997639417571062L;
	private JButton backButton;
	private JScrollPane artikelScroll;
	JTable artikelListe;
	private Object[][] data;
	private ArtikelTableModell model;
	/**
	 * Konstruktor: Setzen des Layouts und bef�llen mit Inhalt.
	 * @param liste Liste von Artikeln
	 */
	public ProtokollMenuPanel(List<Artikel> liste) {
		super();
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(3,1));
		String[] columnNames = {"Nummer",
                "Name",
                "Anzahl"};
		
		data = new Object[liste.size()][3];
		int i = 0;
		for (Artikel a:liste) {
			String[] row = { ""  + a.getNummer(), a.getName(), "" + a.getMenge()};
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
		artikelScroll = new JScrollPane(artikelListe);
		this.add(artikelScroll);

		this.add(new JLabel("W�hlen sie einen Artikel aus."));
		
		backButton = new JButton("Zur�ck zum Men�");
		this.add(backButton);
		
	}
	/**
	 * F�gt einen SelectionListener zur Artikelliste hinzu
	 * @param a SelectionListener
	 */
	public void addListSelectionListener(ListSelectionListener a) {
		artikelListe.getSelectionModel().addListSelectionListener(a);
	}
	/**
	 * F�gt einen ActionListener zum Back Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
}

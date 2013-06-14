package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProtokollPanel extends JPanel {
	public ProtokollPanel() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JPanel titelZeile = new JPanel();
		add(titelZeile, BorderLayout.NORTH);
		titelZeile.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel_1 = new JLabel("Datum");
		titelZeile.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User");
		titelZeile.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Anzahl");
		titelZeile.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Aktion");
		titelZeile.add(lblNewLabel);
		
		
		//JScrollPane ereignisListe = new JScrollPane();
		// ereignisListe.setLayout(new ScrollPaneLayout())
		JPanel ereignisListe = new JPanel();
		add(ereignisListe, BorderLayout.CENTER);
		ereignisListe.setLayout(new GridLayout(1, 4, 0, 0));
	}

}

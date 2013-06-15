package shop.local.ui.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewArtPanel extends JPanel {
	private JTextField artikelNameTextfield;
	private JTextField mengeTextfield;
	private JTextField preisTextfield;
	private JTextField sizeTextfield;
	private JButton okButton;
	public NewArtPanel() {
		super();
		this.setLayout(new GridLayout(5, 2));
		
		// Inhalt hinzufügen
		this.add(new JLabel("Name des Artikels: "));
		artikelNameTextfield = new JTextField();
		this.add(artikelNameTextfield);
		this.add(new JLabel("Menge: "));
		mengeTextfield = new JTextField();
		this.add(mengeTextfield);
		this.add(new JLabel("Preis: "));
		preisTextfield = new JTextField();
		this.add(preisTextfield);
		this.add(new JLabel("Packungsgröße: "));
		sizeTextfield = new JTextField();
		this.add(sizeTextfield);
		
		okButton = new JButton("Artikel erstellen");
		this.add(okButton);
	}
}

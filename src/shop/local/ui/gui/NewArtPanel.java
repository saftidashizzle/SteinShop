package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewArtPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7268405076864774172L;
	private JTextField artikelNameTextfield;
	private JTextField mengeTextfield;
	private JTextField preisTextfield;
	private JTextField sizeTextfield;
	private JButton okButton;
	private JButton backButton;
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
		sizeTextfield = new JTextField("1");
		this.add(sizeTextfield);
		
		okButton = new JButton("Artikel erstellen");
		this.add(okButton);
		backButton = new JButton("Zurück");
		this.add(backButton);
	}
	/**
	 * Fügt einen ActionListener zu dem Back Button hinzu
	 * @param a
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zu dem Okay Button hinzu
	 * @param a
	 */
	public void addActionListenerOK(ActionListener a) {
		this.okButton.addActionListener(a);
	}
	public String getArtikelName() {
		return this.artikelNameTextfield.getText();
	}
	public int getMenge() {
		return Integer.parseInt(this.mengeTextfield.getText());
	}
	public double getPreis() {
		return Double.parseDouble(this.preisTextfield.getText());
	}
	public int getPackungsgroesse() {
		return Integer.parseInt(this.sizeTextfield.getText());
	}
}

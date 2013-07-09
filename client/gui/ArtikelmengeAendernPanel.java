package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtikelmengeAendernPanel extends JPanel {
	/**
	 * Panel für Artikelmenge Ändern Dialog.
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	JTextField artnrTextfield;
	private JTextField mengeTextfield;
	private JButton okButton;
	private JButton backButton;
	/**
	 * Setzen des Layout und befüllen mit Inhalt
	 */
	public ArtikelmengeAendernPanel() {
		super();
		this.setLayout(new GridLayout(3, 2));
		
		// Inhalt hinzufügen
		this.add(new JLabel("Artikelnr: "));
		artnrTextfield = new JTextField();
		this.add(artnrTextfield);
		
		this.add(new JLabel("Um wieviel verändern?: "));
		mengeTextfield = new JTextField();
		this.add(mengeTextfield);
		
		okButton = new JButton("Artikelmenge Ändern");
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
	/**
	 * Methode, die die ArtikelNummer aus dem artnrTextfield zurückgibt
	 * @return zurückgegebene Nummer
	 */
	public int getNummer() {
		return Integer.parseInt(this.artnrTextfield.getText());
	}
	/**
	 * Methode, die den inhalt des mengeTextfield zurückgibt
	 * @return zurückgegebene Menge
	 */
	public int getMenge() {
		return Integer.parseInt(this.mengeTextfield.getText());
	}
	/**
	 * Methode, zum setzen/vorgeben der Artikelnummer im artnrTextfield
	 * @param valueAt der String der gesetzt wird
	 */
	public void setArtikelNummerTextfield(String valueAt) {
		this.artnrTextfield.setText(valueAt);
	}
	/**
	 * Methode, zum setzen des Inhalts von mengeTextfield
	 * @param valueAt der String der gesetzt wird
	 */
	public void setArtikelMengeTextfield(String valueAt) {
		this.mengeTextfield.setText(valueAt);		
	}
}

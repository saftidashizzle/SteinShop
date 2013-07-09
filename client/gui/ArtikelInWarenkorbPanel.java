
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtikelInWarenkorbPanel extends JPanel {
	/**
	 * Panel für Artikel in Warenkorb legen Dialog.
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	JTextField artnrTextfield;
	private JTextField mengeTextfield;
	private JButton okButton;
	private JButton backButton;
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public ArtikelInWarenkorbPanel() {
		super();
		this.setLayout(new GridLayout(3, 2));
		
		// Inhalt hinzufügen
		this.add(new JLabel("Artikelnr: "));
		artnrTextfield = new JTextField();
		this.add(artnrTextfield);
		
		this.add(new JLabel("Menge: "));
		mengeTextfield = new JTextField();
		this.add(mengeTextfield);
		
		okButton = new JButton("Artikel in Warenkorb");
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
	 * Methode, die eine Artikelnummer zurückgibt
	 * @return zurückgegebene Artikelnummer
	 */
	public int getArtikelNummer() {
		return Integer.parseInt(this.artnrTextfield.getText());
	}
	/**
	 * Methode, die eine Menge zurückgibt
	 * @return zurückgegebene Menge
	 */
	public int getMenge() {
		return Integer.parseInt(this.mengeTextfield.getText());
	}
	/**
	 * Methode, zum setzen/vorgeben der Artikelnummer
	 * @param nr 
	 */
	public void setArtikelNummerTextfield(String nr) {
		this.artnrTextfield.setText(nr);
	}
	/**
	 * Methode, zum setzen/vorgeben der Menge
	 * @param m
	 */
	public void setArtikelMengeTextfield(String m) {
		this.mengeTextfield.setText(m);
	}
	
}

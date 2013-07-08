package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtikelmengeImWarenkorbPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	private JTextField artnrTextfield;
	private JTextField mengeTextfield;
	private JButton okButton;
	private JButton backButton;
	public ArtikelmengeImWarenkorbPanel() {
		super();
		this.setLayout(new GridLayout(3, 2));
		
		// Inhalt hinzufügen
		this.add(new JLabel("Artikelnr: "));
		artnrTextfield = new JTextField();
		this.add(artnrTextfield);
		
		this.add(new JLabel("Um wieviel verändern?: "));
		mengeTextfield = new JTextField();
		this.add(mengeTextfield);
		
		okButton = new JButton("Artikelmenge im Warenkorb Ändern");
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
	public int getArtikelNummer() {
		return Integer.parseInt(this.artnrTextfield.getText());
	}
	public int getMenge() {
		return Integer.parseInt(this.mengeTextfield.getText());
	}
	public void setArtikelNummerTextfield(String nr) {
		this.artnrTextfield.setText(nr);
	}
	public void setArtikelMengeTextfield(String m) {
		this.mengeTextfield.setText(m);
	}
}

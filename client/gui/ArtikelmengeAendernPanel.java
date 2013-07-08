package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtikelmengeAendernPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	private JTextField artnrTextfield;
	private JTextField mengeTextfield;
	private JButton okButton;
	private JButton backButton;
	public ArtikelmengeAendernPanel() {
		super();
		this.setLayout(new GridLayout(3, 2));
		
		// Inhalt hinzuf�gen
		this.add(new JLabel("Artikelnr: "));
		artnrTextfield = new JTextField();
		this.add(artnrTextfield);
		
		this.add(new JLabel("Um wieviel ver�ndern?: "));
		mengeTextfield = new JTextField();
		this.add(mengeTextfield);
		
		okButton = new JButton("Artikelmenge �ndern");
		this.add(okButton);
		backButton = new JButton("Zur�ck");
		this.add(backButton);
	}
	/**
	 * F�gt einen ActionListener zu dem Back Button hinzu
	 * @param a
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
	/**
	 * F�gt einen ActionListener zu dem Okay Button hinzu
	 * @param a
	 */
	public void addActionListenerOK(ActionListener a) {
		this.okButton.addActionListener(a);
	}
	public int getNummer() {
		return Integer.parseInt(this.artnrTextfield.getText());
	}
	public int getMenge() {
		return Integer.parseInt(this.mengeTextfield.getText());
	}
	public void setArtikelNummerTextfield(String valueAt) {
		this.artnrTextfield.setText(valueAt);
	}
	public void setArtikelMengeTextfield(String valueAt) {
		this.mengeTextfield.setText(valueAt);		
	}
}

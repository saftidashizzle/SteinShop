package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtikelLoeschenPanel extends JPanel {
	/**
	 * Panel f�r Artikel l�schen Dialog.
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	JTextField artnrTextfield;
	private JButton okButton;
	private JButton backButton;
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 */
	public ArtikelLoeschenPanel() {
		super();
		this.setLayout(new GridLayout(2, 2));
		
		// Inhalt hinzuf�gen
		this.add(new JLabel("Artikelnr: "));
		artnrTextfield = new JTextField();
		this.add(artnrTextfield);
		
		okButton = new JButton("Artikel l�schen");
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
	/**
	 * Methode, die die Artikelnummer aus dem artnrTextfield zur�ckgibt
	 * @return zur�ckgegebene Artikelnummer
	 */
	public int getArtikelNummer() {
		return Integer.parseInt(this.artnrTextfield.getText());
	}
	/**
	 * Methode, zum setzen/vorgeben der Artikelnummer im artnrTextfield
	 * @param valueAt
	 */
	public void setArtikelNummerTextfield(String valueAt) {
		this.artnrTextfield.setText(valueAt);
	}
}

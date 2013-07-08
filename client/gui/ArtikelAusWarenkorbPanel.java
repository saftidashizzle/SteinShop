
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtikelAusWarenkorbPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	private JTextField artnrTextfield;
	private JButton okButton;
	private JButton backButton;
	public ArtikelAusWarenkorbPanel() {
		super();
		this.setLayout(new GridLayout(2, 2));
		
		// Inhalt hinzuf�gen
		this.add(new JLabel("Artikelnr: "));
		artnrTextfield = new JTextField();
		this.add(artnrTextfield);
		
		okButton = new JButton("Artikel aus dem Warenkorb l�schen");
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
	public int getArtikelNummer() {
		return Integer.parseInt(this.artnrTextfield.getText());
	}
	public void setArtikelNummerTextfield(String arg0) {
		this.artnrTextfield.setText(arg0);
	}
}

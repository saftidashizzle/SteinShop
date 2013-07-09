package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewArtPanel extends JPanel {
	/**
	 * Panel f�r neuen Artikel anlegen.
	 */
	private static final long serialVersionUID = -7268405076864774172L;
	JTextField artikelNameTextfield;
	private JTextField mengeTextfield;
	private JTextField preisTextfield;
	private JTextField sizeTextfield;
	private JButton okButton;
	private JButton backButton;
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 */
	public NewArtPanel() {
		super();
		this.setLayout(new GridLayout(5, 2));
		
		// Inhalt hinzuf�gen
		this.add(new JLabel("Name des Artikels: "));
		artikelNameTextfield = new JTextField();
		this.add(artikelNameTextfield);
		this.add(new JLabel("Menge: "));
		mengeTextfield = new JTextField();
		this.add(mengeTextfield);
		this.add(new JLabel("Preis: "));
		preisTextfield = new JTextField();
		this.add(preisTextfield);
		this.add(new JLabel("Packungsgr��e: "));
		sizeTextfield = new JTextField("1");
		this.add(sizeTextfield);
		
		okButton = new JButton("Artikel erstellen");
		this.add(okButton);
		backButton = new JButton("Zur�ck");
		this.add(backButton);
	}
	/**
	 * F�gt einen ActionListener zu dem Back Button hinzu
	 * @param a Actionlistener
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
	/**
	 * F�gt einen ActionListener zu dem Okay Button hinzu
	 * @param a Actionlistener
	 */
	public void addActionListenerOK(ActionListener a) {
		this.okButton.addActionListener(a);
	}
	/**
	 * Methode, die einen Artikelnamen zur�ckgibt
	 * @return zur�ckgegebener Artikelname
	 */
	public String getArtikelName() {
		return this.artikelNameTextfield.getText();
	}
	/**
	 * Methode, die die Menge aus mengeTextfield zur�ckgibt
	 * @return zur�ckgegebene Menge
	 */
	public int getMenge() {
		return Integer.parseInt(this.mengeTextfield.getText());
	}
	/**
	 * Methode, die den Preis aus preisTextfield zur�ckgibt
	 * @return zur�ckgegebener Preis
	 */
	public double getPreis() {
		return Double.parseDouble(this.preisTextfield.getText());
	}
	/**
	 * Methode, die die Packungsgroesse aus sizeTextfield zur�ckgibt
	 * @return zur�ckgegebene Packungsgroesse
	 */
	public int getPackungsgroesse() {
		return Integer.parseInt(this.sizeTextfield.getText());
	}
}

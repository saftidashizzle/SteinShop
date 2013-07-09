package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserLoeschenPanel extends JPanel {
	/**
	 * Panel für den Dialog um User zu löschen
	 */
	private static final long serialVersionUID = -2662697469208559962L;
	JTextField usernrTextfield;
	private JButton okButton;
	private JButton backButton;
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public UserLoeschenPanel() {
		super();
		this.setLayout(new GridLayout(2, 2));
		
		// Inhalt hinzufügen
		this.add(new JLabel("Usernr: "));
		usernrTextfield = new JTextField();
		this.add(usernrTextfield);
		
		okButton = new JButton("User löschen");
		this.add(okButton);
		backButton = new JButton("Zurück");
		this.add(backButton);
	}
	/**
	 * Fügt einen ActionListener zu dem Back Button hinzu
	 * @param a Actionlistener
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zu dem Okay Button hinzu
	 * @param a Actionlistener
	 */
	public void addActionListenerOK(ActionListener a) {
		this.okButton.addActionListener(a);
	}
	/**
	 * Methode, die die Nummer aus usernrTextfield zurückgibt
	 * @return zurückgegebene Nummer
	 */
	public int getUserNummer() {
		return Integer.parseInt(this.usernrTextfield.getText());
	}
	/**
	 * Methode, zum setzen/vorgeben der Artikelnummer in usernrTextfield
	 * @param valueAt
	 */
	public void setArtikelNummerTextfield(String valueAt) {
		this.usernrTextfield.setText(valueAt);
	}
}

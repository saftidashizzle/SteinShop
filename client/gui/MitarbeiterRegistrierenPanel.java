package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MitarbeiterRegistrierenPanel extends JPanel {
	/**
	 * 
	 */
	private JButton regButton;
	private JButton backButton;
	private JTextField userNameTextfield;
	private JPasswordField pw1Textfield;
	private JPasswordField pw2Textfield;
	private JComboBox<String> dropdownmenu;
	private JTextField nameTextfield;
	private static final long serialVersionUID = -286039067067091624L;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public MitarbeiterRegistrierenPanel() {
		super();
		this.setLayout(new GridLayout(6, 2));
		
		// Inhalt hinzufügen
		this.add(new JLabel("Benutzername: "));
		userNameTextfield = new JTextField();
		this.add(userNameTextfield);
		this.add(new JLabel("Passwort: "));
		pw1Textfield = new JPasswordField();
		this.add(pw1Textfield);
		this.add(new JLabel("Passwort wiederholen: "));
		pw2Textfield = new JPasswordField();
		this.add(pw2Textfield);
		this.add(new JLabel("Anrede: "));
		String anreden[] = {"Herr", "Frau", "Etwas"};
		dropdownmenu = new JComboBox<String>(anreden);
		this.add(dropdownmenu);
		this.add(new JLabel("Name: "));
		nameTextfield = new JTextField();
		this.add(nameTextfield);
		regButton = new JButton("Registrieren");
		this.add(regButton);
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
	 * Fügt einen ActionListener zu dieser Komponente (ergo, dem registrier Button) hinzu
	 * @param a
	 */
	public void addActionListenerReg(ActionListener a) {
		this.regButton.addActionListener(a);
	}
	/**
	 * Methode, die einen Usernamen zurückgibt
	 * @return zurückgegebener Username
	 */
	public String getUserName() {
		return this.userNameTextfield.getText();
	}
	/**
	 * Methode, die ein Passwort zurückgibt
	 * @return zurückgegebenes Passwort
	 */
	public char[] getPw1() {
		return this.pw1Textfield.getPassword();
	}
	/**
	 * Methode, die ein PAsswort zurückgibt
	 * @return zurückgegebenes Passwort
	 */
	public char[] getPw2() {
		return this.pw2Textfield.getPassword();
	}
	/**
	 * Methode, die die Anrede zurückgibt
	 * @return zurückgegebene Anrede
	 */
	public String getAnrede() {
		return (String) this.dropdownmenu.getSelectedItem();
	}
	/**
	 * Methode, die einen Namen zurückgibt
	 * @return zurückgegebener Name
	 */
	public String getName() {
		return this.nameTextfield.getText();
	}
}

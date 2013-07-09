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
	 * Klasse f�r ein Panel um Mitarbeiter zu Registrieren
	 */
	private JButton regButton;
	private JButton backButton;
	private JPasswordField pw1Textfield;
	JTextField userNameTextfield;
	private JPasswordField pw2Textfield;
	private JComboBox<String> dropdownmenu;
	private JTextField nameTextfield;
	private static final long serialVersionUID = -286039067067091624L;
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 */
	public MitarbeiterRegistrierenPanel() {
		super();
		this.setLayout(new GridLayout(6, 2));
		
		// Inhalt hinzuf�gen
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
		backButton = new JButton("Zur�ck");
		this.add(backButton);
		
	}
	/**
	 * F�gt einen ActionListener zu dem Back Button hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}	
	/**
	 * F�gt einen ActionListener zu dieser Komponente (ergo, dem registrier Button) hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerReg(ActionListener a) {
		this.regButton.addActionListener(a);
	}
	/**
	 * Methode, die einen Usernamen zur�ckgibt
	 * @return zur�ckgegebener Username
	 */
	public String getUserName() {
		return this.userNameTextfield.getText();
	}
	/**
	 * Methode, die das Passwort aus pw1Textfield zur�ckgibt
	 * @return zur�ckgegebenes Passwort
	 */
	public char[] getPw1() {
		return this.pw1Textfield.getPassword();
	}
	/**
	 * Methode, die das Passwort aus pw2Textfield zur�ckgibt
	 * @return zur�ckgegebenes Passwort
	 */
	public char[] getPw2() {
		return this.pw2Textfield.getPassword();
	}
	/**
	 * Methode, die die Anrede zur�ckgibt
	 * @return zur�ckgegebene Anrede
	 */
	public String getAnrede() {
		return (String) this.dropdownmenu.getSelectedItem();
	}
	/**
	 * Methode, die den Namen zur�ckgibt
	 * @return zur�ckgegebener Name
	 */
	public String getName() {
		return this.nameTextfield.getText();
	}
}

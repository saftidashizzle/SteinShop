package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegPanel extends JPanel {
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
	private JTextField strTextfield;
	private JTextField plzTextfield;
	private JTextField ortTextfield;
	private JTextField landTextfield;
	private static final long serialVersionUID = -286039067067091624L;

	public RegPanel() {
		super();
		this.setLayout(new GridLayout(10, 2));
		
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
		this.add(new JLabel("Straße u. Nr: "));
		strTextfield = new JTextField();
		this.add(strTextfield);
		this.add(new JLabel("PLZ: "));
		plzTextfield = new JTextField();
		this.add(plzTextfield);
		this.add(new JLabel("Ort: "));
		ortTextfield = new JTextField();
		this.add(ortTextfield);
		this.add(new JLabel("Land: "));
		landTextfield = new JTextField();
		this.add(landTextfield);
		regButton = new JButton("Registrieren");
		this.add(regButton);
		backButton = new JButton("Zurück");
		this.add(backButton);
		
	}
	
	/**
	 * Fügt einen ActionListener zu dieser Komponente (ergo, dem registrier Button) hinzu
	 * @param a
	 */
	public void addActionListenerReg(ActionListener a) {
		this.regButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zu dieser Komponente (ergo, dem back Button) hinzu
	 * @param a
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
	public String getUserName() {
		return this.userNameTextfield.getText();
	}
	public char[] getPw1() {
		return this.pw1Textfield.getPassword();
	}
	public char[] getPw2() {
		return this.pw2Textfield.getPassword();
	}
	public String getAnrede() {
		return (String) this.dropdownmenu.getSelectedItem();
	}
	public String getName() {
		return this.nameTextfield.getText();
	}
	public String getStr() {
		return this.strTextfield.getText();
	}
	public String getPlz() {
		return this.plzTextfield.getText();
	}
	public String getOrt() {
		return this.ortTextfield.getText();
	}
	public String getLand() {
		return this.landTextfield.getText();
	}

}

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
	 * Panel f�r das Registrieren eines neuen Benutzers.
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
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 */
	public RegPanel() {
		super();
		this.setLayout(new GridLayout(10, 2));
		
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
		this.add(new JLabel("Stra�e u. Nr: "));
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
		backButton = new JButton("Zur�ck");
		this.add(backButton);
		
	}
	
	/**
	 * F�gt einen ActionListener zu dieser Komponente (ergo, dem registrier Button) hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerReg(ActionListener a) {
		this.regButton.addActionListener(a);
	}
	/**
	 * F�gt einen ActionListener zu dieser Komponente (ergo, dem back Button) hinzu
	 * @param a ActionListener
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
	/**
	 * Methode, die den Namen aus userNameTextfield zur�ckgibt
	 * @return zur�ckgegebener NAme
	 */
	public String getUserName() {
		return this.userNameTextfield.getText();
	}
	/**
	 * Methode, die das Passwort aus pw1Textfield zur�ckgibt (char[])
	 * @return zur�ckgegebenes Passwort
	 */
	public char[] getPw1() {
		return this.pw1Textfield.getPassword();
	}
	/**
	 * Methode, die das PAsswort aus pw2Textfield zur�ckgibt (char[])
	 * @return zur�ckgegebenes Passwort
	 */
	public char[] getPw2() {
		return this.pw2Textfield.getPassword();
	}
	/**
	 * Methode, die die Anrede aus dropdownmenu zur�ckgibt
	 * @return zur�ckgegebene Anrede
	 */
	public String getAnrede() {
		return (String) this.dropdownmenu.getSelectedItem();
	}
	/**
	 * Methode, die den Namen aus nameTextfield zur�ckgibt
	 * @return zur�ckgegebener Name
	 */
	public String getName() {
		return this.nameTextfield.getText();
	}
	/**
	 * Methode, die die Strasse aus strTextfield zur�ckgibt
	 * @return zur�ckgegebene Strasse
	 */
	public String getStr() {
		return this.strTextfield.getText();
	}
	/**
	 * Methode, die die PLZ aus plzTextfield zur�ckgibt
	 * @return zur�ckgegebene PLZ
	 */
	public String getPlz() {
		return this.plzTextfield.getText();
	}
	/**
	 * Methode, die den Ort aus ortTextfield zur�ckgibt
	 * @return zur�ckgegebener Ort
	 */
	public String getOrt() {
		return this.ortTextfield.getText();
	}
	/**
	 * Methode, die das Land aus landTextfield zur�ckgibt
	 * @return zur�ckgegebenes Land
	 */
	public String getLand() {
		return this.landTextfield.getText();
	}

}

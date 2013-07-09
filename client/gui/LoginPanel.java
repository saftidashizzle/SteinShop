package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Login Panel: Enthält Textfeld für Username und Passwort, sowie Login Button
 * 
 */

public class LoginPanel extends JPanel {
	
	private JButton loginButton;
	private JTextField userTextfield;
	private JPasswordField pwTextfield;
	private JButton regButton;
	private static final long serialVersionUID = -286039067067091624L;
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public LoginPanel() {
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 200));
		loginButton = new JButton("Login");
		regButton = new JButton("Registrieren");
		pwTextfield = new JPasswordField();
		userTextfield = new JTextField();
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(2, 3));
		this.add(pane);
		
		pane.add(new JLabel("User: "));
		pane.add(userTextfield);
		pane.add(loginButton);
		pane.add(new JLabel("Passwort: "));
		pane.add(pwTextfield);
		pane.add(regButton);
		
			
		setVisible(true);
	}
	/**
	 * Fügt einen ActionListener zu dieser Komponente (dem loginButton) hinzu
	 * @param a Actionlistener
	 */
	public void addActionListenerLogin(ActionListener a) {
		this.loginButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zu dieser Komponente (dem regButton) hinzu
	 * @param a Actionlistener
	 */
	public void addActionListenerRegistieren(ActionListener a) {
		this.regButton.addActionListener(a);
	}
	/**
	 * Getter für Usernamen
	 * @return String Username
	 */
	public String getUserName() {
		return this.userTextfield.getText();
	}
	/**
	 * Getter für Passwort
	 * @return String Passwort
	 */
	public char[] getPasswort() {
		return this.pwTextfield.getPassword();
	}
}

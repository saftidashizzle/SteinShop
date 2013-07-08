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
//	private JButton btnOk;
//	private JButton btnCancel;
	private static final long serialVersionUID = -286039067067091624L;

	public LoginPanel() {
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 200));
		loginButton = new JButton("Login");
		regButton = new JButton("Registrieren");
		pwTextfield = new JPasswordField("123");
		userTextfield = new JTextField("Kunde");
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(2, 3));
		this.add(pane);
		
		pane.add(new JLabel("User: "));
		
		pane.add(userTextfield);
		pane.add(loginButton);
		pane.add(new JLabel("Passwort: "));
		pane.add(pwTextfield);
		pane.add(regButton);
		

			
//			//BoxLayout für das this einstellen
//			this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
//			
//			//JLabels und JTextfields zur Eingabe
//			JLabel lblUser = new JLabel("Login:");
//			JTextField txtUser = new JTextField("User");
//			JLabel lblPass = new JLabel("Password:");
//			JPasswordField txtPass = new JPasswordField("pass");
//			
//			this.add(lblUser);
//			
//			this.add(Box.createRigidArea(new Dimension(0,5)));
//			
//			this.add(txtUser);
//			
//			this.add(
//			Box.createRigidArea(new Dimension(0,5)));
//			
//			this.add(lblPass);
//			
//			this.add(
//			Box.createRigidArea(new Dimension(0,5)));
//			
//			this.add(txtPass);
//			
//			/*
//			* Buttonbereich des JFrames einstellen
//			*/
//			
//			//JPanel für die Buttons
//			JPanel buttonPanel = new JPanel();
//			
//			//BoxLayout für das buttonPanel einstellen
//			buttonPanel.setLayout(
//			new BoxLayout(
//			buttonPanel,BoxLayout.LINE_AXIS));
//			
//			//oben, links, unten, rechts
//			buttonPanel.setBorder(
//			BorderFactory.createEmptyBorder(5,0,5,0));
//			
//			//JButtons erstellen
//			btnOk = new JButton("Ok");
//			btnCancel = new JButton("Cancel");
//			
//			buttonPanel.add(
//			Box.createHorizontalGlue());
//			
//			buttonPanel.add(btnOk);
//			
//			buttonPanel.add(
//			Box.createRigidArea(
//			new Dimension(5,0)));
//			
//			buttonPanel.add(btnCancel);
//			
//			setSize(220,160);
//			setLocation(50,50);
		
			
			setVisible(true);
	}
	/**
	 * Fügt einen ActionListener zu dieser Komponente (dem loginButton) hinzu
	 * @param a
	 */
	public void addActionListenerLogin(ActionListener a) {
		this.loginButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zu dieser Komponente (dem regButton) hinzu
	 * @param a
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

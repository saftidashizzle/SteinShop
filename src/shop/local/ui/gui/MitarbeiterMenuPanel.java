package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MitarbeiterMenuPanel extends JPanel {
	private JButton logoutButton;
	public MitarbeiterMenuPanel() {
		super();
		this.setLayout(new GridLayout(7,1));
		this.add(new JButton("Neuen Artikel anlegen"));
		this.add(new JButton("Artikelmenge ändern"));
		this.add(new JButton("Artikel löschen"));
		this.add(new JButton("Artikel löschen"));
		this.add(new JButton("Mitarbeiter löschen"));
		this.add(new JButton("Artikelmengenverlauf für bestimmten Artikel anzeigen"));
		logoutButton = new JButton("Logout");
		this.add(logoutButton);

	}
	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
}

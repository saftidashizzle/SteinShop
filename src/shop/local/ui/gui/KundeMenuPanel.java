package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KundeMenuPanel extends JPanel {
	private JButton logoutButton;
	public KundeMenuPanel(){
		super();
		this.setLayout(new GridLayout(6,1));
		
		this.add(new JButton("Artikelmenge im Warenkorb ändern"));
		this.add(new JButton("Artikel nach Namen ordnen"));
		this.add(new JButton("Artikel nach Nummern ordnen"));
		this.add(new JButton("Artikel aus Warenkorb entfernen"));
		this.add(new JButton("Warenkorb leeren"));
		
		logoutButton = new JButton("Logout");
		this.add(logoutButton);
		/*	w) Zum Warenkorb
			m) Artikelmenge im Warenkorb ändern
			n) Artikel nach Namen ordnen
			f) Artikel nach Nummern ordnen
			c) Artikel in Warenkorb legen
			d) Artikel aus Warenkorb
			e) Warenkorb leeren
			k) Zur Kasse
			a) Ausloggen

		 */
		
	}

	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
}

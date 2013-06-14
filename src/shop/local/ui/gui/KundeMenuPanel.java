package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KundeMenuPanel extends JPanel {
	private JButton logoutButton;
	private JButton artikelInWarenkorbButton;
	private JButton artikelNachNamenButton;
	private JButton artikelNachNummernButton;
	private JButton artikelEntfernenButton;
	private JButton warenkorbLeerenButton;
	private JButton Button;
	public KundeMenuPanel(){
		super();
		this.setLayout(new GridLayout(6,1));
		
		artikelInWarenkorbButton = new JButton("Artikelmenge im Warenkorb ändern");
		this.add(artikelInWarenkorbButton);
		
		artikelNachNamenButton = new JButton("Artikel nach Namen ordnen");
		this.add(artikelNachNamenButton);
		
		artikelNachNummernButton = new JButton("Artikel nach Nummern ordnen"); 
		this.add(artikelNachNummernButton);
		
		artikelEntfernenButton = new JButton("Artikel aus Warenkorb entfernen"); 
		this.add(artikelEntfernenButton);
		
		warenkorbLeerenButton = new JButton("Warenkorb leeren");
		this.add(warenkorbLeerenButton);
		
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

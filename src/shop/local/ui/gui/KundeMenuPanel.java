package shop.local.ui.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KundeMenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1550997639417571062L;
	private JButton logoutButton;
	private JButton artikelInWarenkorbButton;
	private JButton artikelNachNamenButton;
	private JButton artikelNachNummernButton;
	private JButton artikelEntfernenButton;
	private JButton warenkorbLeerenButton;
	private JButton artikelmengeAendernButton;
	public KundeMenuPanel(){
		super();
		this.setLayout(new GridLayout(7,1));
		
		artikelInWarenkorbButton = new JButton("Artikel in Warenkorb legen");
		this.add(artikelInWarenkorbButton);
		
		artikelmengeAendernButton = new JButton("Artikelmenge im Warenkorb �ndern");
		this.add(artikelmengeAendernButton);
		
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
		
	}

	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
}

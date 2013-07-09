package gui;

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
	private JButton artikelEntfernenButton;
	private JButton warenkorbLeerenButton;
	private JButton artikelmengeAendernButton;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public KundeMenuPanel(){
		super();
		this.setLayout(new GridLayout(7,1));
		
		artikelInWarenkorbButton = new JButton("Artikel in Warenkorb legen");
		this.add(artikelInWarenkorbButton);
		
		artikelmengeAendernButton = new JButton("Artikelmenge im Warenkorb ändern");
		this.add(artikelmengeAendernButton);
		
		artikelEntfernenButton = new JButton("Artikel aus Warenkorb entfernen"); 
		this.add(artikelEntfernenButton);
		
		warenkorbLeerenButton = new JButton("Warenkorb leeren");
		this.add(warenkorbLeerenButton);
		
		logoutButton = new JButton("Logout");
		this.add(logoutButton);
		
	}
	/**
	 * Fügt einen ActionListener zum Logout Button hinzu
	 * @param a
	 */
	public void addActionListenerLogout(ActionListener a) {
		this.logoutButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Artikel in Warenkorb Button hinzu
	 * @param a
	 */
	public void addActionListenerArtInW(ActionListener a) {
		this.artikelInWarenkorbButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Artikelmenge ändern Button hinzu
	 * @param a
	 */
	public void addActionListenerArtMenge(ActionListener a) {
		this.artikelmengeAendernButton.addActionListener(a);		
	}
	/**
	 * Fügt einen ActionListener zum Artikel entfernen Button hinzu
	 * @param a
	 */
	public void addActionListenerArtAusW(ActionListener a) {
		this.artikelEntfernenButton.addActionListener(a);
	}
	/**
	 * Fügt einen ActionListener zum Warenkorb leeren Button hinzu
	 * @param a
	 */
	public void addActionListenerWarenkorbLeeren(ActionListener a) {
		this.warenkorbLeerenButton.addActionListener(a);
	}
}

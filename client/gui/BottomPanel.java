package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2918283340094782157L;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public BottomPanel() {
		super();
		
		this.add(new JLabel("<html><u>AGB</u></html>"));
		this.add(new JLabel("Über uns"));
		this.add(new JLabel("Impressum"));
		this.add(new JLabel("Versandkosten"));
		
	}
}



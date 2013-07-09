package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlatzhalterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7766035177249609479L;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public PlatzhalterPanel() {
		super();
		this.add(new JLabel("PLATZHALTER"));
	}
}

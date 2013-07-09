package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1987746672341738130L;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 */
	public TopPanel() {
		super();
		this.add(new JLabel(new ImageIcon("Bild.png")));
	}
}

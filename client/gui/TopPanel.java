package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	/**
	 * TopPanel, enth�llt nur das Titelbild.
	 */
	private static final long serialVersionUID = 1987746672341738130L;
	/**
	 * bef�llen mit Inhalt
	 */
	public TopPanel() {
		super();
		this.add(new JLabel(new ImageIcon("Bild.png")));
	}
}

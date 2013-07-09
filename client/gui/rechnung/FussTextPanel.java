package gui.rechnung;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class FussTextPanel extends JLabel {
	private JButton backButton;
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 */
	private static final long serialVersionUID = -4907253899870936333L;
	public FussTextPanel() {
		super();
		this.setLayout(new FlowLayout());
		this.add(new JLabel("Vielen Dank f�r Ihren Einkauf im SteinShop."));
		backButton = new JButton("Zur�ck");
		this.add(backButton);
		this.setVisible(true);
	}
}

package shop.local.ui.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	public BottomPanel() {
		super();
		this.add(new JLabel("AGB"));
		this.add(new JLabel("Über uns"));
		this.add(new JLabel("Impressum"));
		this.add(new JLabel("Versandkosten"));
	}
}



package gui.rechnung;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnschriftPanel extends JPanel {

	/**
	 * Setzen des Layout und befüllen mit Inhalt
	 * @param anrede
	 * @param vorZuname
	 * @param adresse
	 * @param datum
	 */
	private static final long serialVersionUID = -3762360456096814592L;
	
	public AnschriftPanel(String anrede, String vorZuname, String adresse, Date datum){
//		JPanel pane = new JPanel();
		this.setLayout(new GridLayout(1, 2));
		JPanel anschrift = new JPanel();
		anschrift.setLayout(new GridLayout(3,1));
		anschrift.add(new JLabel(anrede));
		anschrift.add(new JLabel(vorZuname));
		anschrift.add(new JLabel(adresse));
		this.add(anschrift);
		this.add(new JLabel("" + datum));
	}
}

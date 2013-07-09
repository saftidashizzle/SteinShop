package gui.rechnung;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import valueobjects.Artikel;
import valueobjects.Warenkorb;

public class GekauftListePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 336606700357886974L;
	
	public GekauftListePanel(Warenkorb warenkorb){
		this.setLayout(new GridLayout(warenkorb.getInhalt().size()+1,5));
		this.add(new JLabel("Nr"));
		this.add(new JLabel("Name"));
		this.add(new JLabel("Anzahl"));
		this.add(new JLabel("Einzelbetrag"));
		this.add(new JLabel("Gesamtbetrag"));
		HashMap<Artikel, Integer> wkorb = warenkorb.getInhalt();
		for (Artikel a : wkorb.keySet()) {
			this.add(new JLabel("" + a.getNummer()));
			this.add(new JLabel("" + a.getName()));
			this.add(new JLabel("" + wkorb.get(a)));
			this.add(new JLabel("" + a.getPreis()));
			this.add(new JLabel("" + wkorb.get(a)*a.getPreis()));
		}
	}
}

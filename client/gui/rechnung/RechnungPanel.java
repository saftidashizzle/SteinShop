package gui.rechnung;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import valueobjects.Rechnung;

public class RechnungPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3010974526025246317L;
	private AnschriftPanel anschriftPanel;
	public RechnungListePanel rechnungListePanel;
	
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 * @param rechnung
	 */
	public RechnungPanel(Rechnung rechnung) {
		this.setLayout(new BorderLayout(10,10));
		System.out.println("" + rechnung.getAdresse());
		anschriftPanel = new AnschriftPanel(rechnung.getAnrede(), rechnung.getVorZuname(), rechnung.getAdresse(), rechnung.getDatum());
		this.add(anschriftPanel, BorderLayout.NORTH);
		rechnungListePanel = new RechnungListePanel(rechnung);
		this.add(rechnungListePanel, BorderLayout.CENTER);
	}
}

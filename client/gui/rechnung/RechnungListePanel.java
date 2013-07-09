package gui.rechnung;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import valueobjects.Rechnung;
import javax.swing.SwingConstants;

public class RechnungListePanel extends JPanel {
	private JButton backButton;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2802210553999627458L;
	/**
	 * Setzen des Layouts und befüllen mit Inhalt
	 * @param rechnung
	 */
	public RechnungListePanel(Rechnung rechnung){
		this.setLayout(new BorderLayout(10,10));
		this.add(new JLabel("Rechnung"), BorderLayout.NORTH);
		System.out.println(rechnung.getWarenkorb().getInhalt());
		JPanel gekauftListePanel = new GekauftListePanel(rechnung.getWarenkorb());
		this.add(gekauftListePanel, BorderLayout.CENTER);
		JPanel gesamtBetragPanel = new JPanel();
		gesamtBetragPanel.setLayout(new GridLayout(1,3));
		backButton = new JButton("Weiter Einkaufen");
		gesamtBetragPanel.add(backButton);
		JLabel label_1 = new JLabel("Gesamtsumme: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		gesamtBetragPanel.add(label_1);
		JLabel label = new JLabel(""+ rechnung.getGesamtbetrag());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		gesamtBetragPanel.add(label);
		
		this.add(gesamtBetragPanel, BorderLayout.SOUTH);
	}
	/**
	 * Fügt einen ActionListener zu dem Back Button hinzu
	 * @param a
	 */
	public void addActionListenerBack(ActionListener a) {
		this.backButton.addActionListener(a);
	}
}

package gui;


import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import valueobjects.Artikel;
import valueobjects.Ereignis;

public class MitarbeiterPanel extends JPanel {
    /**
	 * Panel um das MitarbeiterPanel darzustellen, dass ein TabbedPane ist
	 *  und 3 weitere Panel mit Tables enth�lt.
	 */
	ArtikelMitarbeiterPanel artikelPanel;
	BenutzerPanel benutzerPanel;
	public JTabbedPane tabbedPane;
	private static final long serialVersionUID = -8768799264538074055L;
	/**
	 * Setzen des Layouts und bef�llen mit Inhalt
	 * @param artikelListe Die Liste von Artikeln
	 * @param benutzerListe Die Liste der Benutzer, ohne Passwort
	 * @param protokoll Die Liste von Ereignissen
	 */
	public MitarbeiterPanel(List<Artikel> artikelListe, Object[][] benutzerListe, List<Ereignis> protokoll) {
        super(new GridLayout(1, 1));
        
        tabbedPane = new JTabbedPane();
        
        artikelPanel = new ArtikelMitarbeiterPanel(artikelListe);
        tabbedPane.addTab("Artikelliste", artikelPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        benutzerPanel = new BenutzerPanel(benutzerListe);
        tabbedPane.addTab("Benutzerliste", benutzerPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        ProtokollPanel protokollPanel = new ProtokollPanel(protokoll);
        tabbedPane.addTab("Ereignisliste", protokollPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
	/**
	 * Methode, die die Artikelliste aktualisiert
	 * @param liste neue artikelListe
	 */
	public void updateArtikelListe(List<Artikel> liste) {
		ArtikelTableModell atm = (ArtikelTableModell) artikelPanel.artikelListe.getModel();
		atm.updateDataVector(liste);
	}
	/**
	 * Methode, die die Artikelliste aktualisiert
	 * @param liste neue daten f�r user
	 */
	public void updateUserListe(Object[][] userListe) {
		BenutzerTableModell atm = (BenutzerTableModell) benutzerPanel.benutzerListe.getModel();
		atm.updateDataVector(userListe);
	}
}

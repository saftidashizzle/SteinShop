package gui;


import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.User;

public class MitarbeiterPanel extends JPanel {
    /**
	 * 
	 */
	ArtikelPanel artikelPanel;
	BenutzerPanel benutzerPanel;
	public JTabbedPane tabbedPane;
	private static final long serialVersionUID = -8768799264538074055L;

	public MitarbeiterPanel(List<Artikel> artikelListe, Object[][] benutzerListe, List<Ereignis> protokoll) {
        super(new GridLayout(1, 1));
        
        tabbedPane = new JTabbedPane();
        
        artikelPanel = new ArtikelPanel(artikelListe);
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
	public void updateArtikelListe(List<Artikel> liste) {
		ArtikelTableModell atm = (ArtikelTableModell) artikelPanel.artikelListe.getModel();
		atm.updateDataVector(liste);
	}
	public void updateUserListe(Object[][] userListe) {
		BenutzerTableModell atm = (BenutzerTableModell) benutzerPanel.benutzerListe.getModel();
		atm.updateDataVector(userListe);
	}
}

package shop.local.ui.gui;


import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.User;

public class MitarbeiterPanel extends JPanel {
    public MitarbeiterPanel(List<Artikel> artikelListe, List<User> benutzerListe) {
        super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
//        ImageIcon icon = createImageIcon("images/middle.gif");
        
        ArtikelPanel artikelPanel = new ArtikelPanel(artikelListe);
        tabbedPane.addTab("Artikelliste", artikelPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        BenutzerPanel benutzerPanel = new BenutzerPanel(benutzerListe);
        tabbedPane.addTab("Benutzerliste", benutzerPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        ProtokollPanel protokollPanel = new ProtokollPanel();
        tabbedPane.addTab("Ereignisliste", protokollPanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
//    protected JComponent makeTextPanel(String text) {
//        JPanel panel = new JPanel(false);
//        JLabel filler = new JLabel(text);
//        filler.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new GridLayout(1, 1));
//        panel.add(filler);
//        return panel;
//    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
}

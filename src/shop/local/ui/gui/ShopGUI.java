package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.local.domain.ShopVerwaltung;
import shop.local.domain.exceptions.InkorrekteRegWerteException;
import shop.local.domain.exceptions.LoginFehlgeschlagenException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.User;


public class ShopGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5711673086933143461L;
	ShopVerwaltung shopVer;
	User aktuellerBenutzer;
	
	// fuer die menue leiste
	private MenuItem menuItemQuit;
	// Start
	private LoginPanel loginPanel;
	private RegPanel regPanel;
	// Menues
	private MitarbeiterMenuPanel mitarbeiterMenuPanel;
	private KundeMenuPanel kundeMenuPanel;
	// Top und Bot
	private TopPanel topPanel;
	private BottomPanel botPanel;
	// East
	private WarenkorbPanel warenkorbPanel;
	// Center
	private ArtikelPanel artikelPanel;
	private MitarbeiterPanel mitarbeiterPanel;
	// West, Mitarbeitermenue
	private NewArtPanel newArtPanel;
	private ArtikelmengeAendernPanel artMengPanel;
	private ArtikelLoeschenPanel artDelPanel;
	private MitarbeiterRegistrierenPanel mitRegPanel;
	private UserLoeschenPanel usrDelPanel;
	// West, Kundenmenue
	private ArtikelInWarenkorbPanel artInWPanel;
	private ArtikelmengeImWarenkorbPanel artMengeInWPanel;
	private ArtikelAusWarenkorbPanel artAusWPanel;
	
	// Listen
	private List<Artikel> artikelListe;
	private List<User> userListe;
	private List<Ereignis> ereignisListe;
	// Grobe RichtungsPanels
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
    private CardLayout cardLayout = new CardLayout();

	
	// Textbereich für Lognachrichten
	
	public ShopGUI() {
		super("SteinShop");
		shopVer = new ShopVerwaltung();
		aktuellerBenutzer = null;
		try {
			shopVer.ladeDaten();
			artikelListe = shopVer.gibAlleArtikel();
			userListe = shopVer.gibAlleUser();
			ereignisListe = shopVer.gibProtokollListe();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Fenstergröße einstellen
		this.setSize(800, 600);
		
		// Initialisieren der Komponenten des Fensters
		this.initComponents();
		
		// Initialisieren der Listener für die interaktiven Komponenten
		// (Versehen von Buttons, Menüeinträgen etc. mit "Leben")
		this.initListeners();
		
		// Optimale Anordnung bewirken
		this.pack();
		
		// Zuletzt schalten wir den Frame auf "sichtbar"
		this.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShopGUI shop = new ShopGUI();
		try {
			shop.run();
		}
		catch (Exception e) {
			System.out.println("Fehler bei der Eingabe");
			e.printStackTrace();
		}		
	}
	/**
	 * Methode, die in der Main am Anfang ausgeführt wird und das ganze Programm zum Laufen bringt.
	 * @throws IOException
	 */
	public void run() {
		try {
			
			//gibMenue(); wuerde das anfangsmenue liefern
			// shopVer.speichereDaten();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialisieren aller Komponenten im Fenster
	 */
	public void initComponents() {
		// Menüleiste programmieren: Für unsere Anwendung bauen wir zwei Untermenüs "Datei" und "Garage"
		// Beachtet: Ein MenuBar-Objekt besteht aus Menu-Objekten, und jedes Menu-Objekt besteht aus MenuItem-Objekten
		MenuBar menubar = new MenuBar();
		
		// "Datei"-Menü zusammenbauen
		Menu menuDatei = new Menu("Datei");
		// "Beenden"-Menüeintrag zusammenbauen
		menuItemQuit = new MenuItem("Beenden");
		// Menüeintrag zum Menü hinzufügen
		menuDatei.add(menuItemQuit);
		menuDatei.addSeparator();
		// Menü zur MenuBar hinzufügen
		menubar.add(menuDatei);
		
		// Menüleiste zum Frame hinzufügen
		this.setMenuBar(menubar);
		
		// Wir initialisieren nun das Layout des Fensters: Damit strukturieren wir
		// das Fenster und können Unterbereiche definieren. In diesem Beispiel
		// verwende ich ein GridLayout mit zwei Spalten, die das Fenster vertikal aufteilen sollen
		this.setLayout(new BorderLayout(10,10));		
		
		// CardLayout erstellen fuer CENTER
		centerPanel = new JPanel();
		centerPanel.setLayout(cardLayout);
		// login panel erstellen und hinzufuegen
		loginPanel = new LoginPanel();
		centerPanel.add(loginPanel, "loginPanel");
		// mitarbeiterPanel (tabs) erstellen und hinzufuegen
		mitarbeiterPanel = new MitarbeiterPanel(artikelListe, userListe, ereignisListe);
		mitarbeiterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.add(mitarbeiterPanel, "mitarbeiterPanel");
		// ArtikelPanel fuer Kunde erstellen und hinzufuegen
		artikelPanel = new ArtikelPanel(artikelListe);
		artikelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.add(artikelPanel, "artikelPanel");		
		// CenterPanel hinzufügen
		this.add(centerPanel, BorderLayout.CENTER);
		

		// CardLayout erstellen fuer EAST
		eastPanel = new JPanel();
		eastPanel.setLayout(cardLayout);
		// Registrierpanel erstellen und hinzufügen
		regPanel = new RegPanel();
		eastPanel.add(regPanel, "regPanel");
		// Warenkorb Panel erstellen und hinzufügen
		warenkorbPanel = new WarenkorbPanel();
		warenkorbPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		eastPanel.add(warenkorbPanel, "warenkorbPanel");
		// EastPanel hinzufügen
		this.add(eastPanel, BorderLayout.EAST);

		
		
		//CardLayout erstellen fuer WEST
		westPanel = new JPanel();
		westPanel.setLayout(cardLayout);
		// KundenMenu erstellen und hinzufügen
		kundeMenuPanel = new KundeMenuPanel();
		kundeMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(kundeMenuPanel, "kundeMenu");
		// Mitarbeiter Menü erstellen und hinzufügen
		mitarbeiterMenuPanel = new MitarbeiterMenuPanel();
		mitarbeiterMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(mitarbeiterMenuPanel, "mitarbeiterMenu");
		
		// ****** Kundenmenue ********
		// Panel für neuen Artikel erstellen
		newArtPanel = new NewArtPanel();
		westPanel.add(newArtPanel, "newArtPanel");
		// Panel für Artikelmenge ändern erstellen
		artMengPanel = new ArtikelmengeAendernPanel();
		westPanel.add(artMengPanel, "artMengPanel");
		// Panel für Artikel löschen erstellen
		artDelPanel = new ArtikelLoeschenPanel();
		westPanel.add(artDelPanel, "artDelPanel");
		// Panel für Mitarbeiter Registrieren
		mitRegPanel = new MitarbeiterRegistrierenPanel();
		westPanel.add(mitRegPanel, "mitRegPanel");
		//Panel für User löschen
		usrDelPanel = new UserLoeschenPanel();
		westPanel.add(usrDelPanel, "usrDelPanel");
		
		// ******* Mitarbeitermenue *******
		// Panel für Artikel in Warenkorb
		artInWPanel = new ArtikelInWarenkorbPanel();
		westPanel.add(artInWPanel, "artInWPanel");
		// Panel für Artikelmenge ändern
		artMengeInWPanel = new ArtikelmengeImWarenkorbPanel();
		westPanel.add(artMengeInWPanel, "artMengeInWPanel");
		// Panel für Artikel aus Warenkorb
		artAusWPanel = new ArtikelAusWarenkorbPanel();
		westPanel.add(artAusWPanel, "artAusWPanel");
		
		
		// WestPanel hinzufügen
		this.add(westPanel, BorderLayout.WEST);
		westPanel.setVisible(false);
		
		// Top Panel und Bot Panel erstellen und hinzufügen
		topPanel = new TopPanel();
		this.add(topPanel, BorderLayout.NORTH);
		botPanel = new BottomPanel();
		this.add(botPanel, BorderLayout.SOUTH);
	}
	/**
	 * Initialisieren und registrieren aller Listener und Event-Handler
	 */
	public void initListeners() {
		// Diese Listener definiere ich als anonyme Klassen!
		// Das bedeutet, dass ihre Klassendefinition quasi einfach so in
		// dieser Methode steht. Damit vermeide ich, für jede Kleinigkeit
		// und jeden Listener eine einzelne Klasse schreiben zu müssen.
		
		// Diese Selbst-Referenz führe ich ein, um innerhalb der Listener-Implementierungen
		// auf die GUI zugreifen zu können. Alternativ könnte man auch jedes mal "GarageGUI.this" schreiben!
		final ShopGUI frame = this;
		
		// Quit-Listener für das "Datei"-Menü
		ActionListener listenerQuit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// Anwendung beenden
				frame.setVisible(false);
				frame.dispose();
			}
		};
		menuItemQuit.addActionListener(listenerQuit);
		
		// Event Listener für Login Button
		ActionListener listenerLogin = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				List<User> UserListe = shopVer.gibAlleUser();
				String name = loginPanel.getUserName();
				String pw = loginPanel.getPasswort();
				try {
					aktuellerBenutzer = userLogin(UserListe, name, pw);
					if (aktuellerBenutzer instanceof Kunde) {
						frame.cardLayout.show(westPanel, "kundeMenu");
						frame.cardLayout.show(centerPanel, "artikelPanel");
						frame.cardLayout.show(eastPanel, "warenkorbPanel");
						frame.westPanel.setVisible(true);						
					} else if(aktuellerBenutzer instanceof Mitarbeiter) {
						frame.westPanel.setVisible(true);
						frame.eastPanel.setVisible(false);
						frame.cardLayout.show(westPanel, "mitarbeiterMenu");
						frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
					}
					frame.pack();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		};
		loginPanel.addActionListenerLogin(listenerLogin);
	
		// Event Listener für Registrier Button von regPanel
		ActionListener listenerReg = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(regPanel.getPw1().equals(regPanel.getPw2())) {
					try {
						frame.shopVer.fuegeUserEin(regPanel.getUserName(), regPanel.getPw1(), regPanel.getAnrede(), regPanel.getName(), regPanel.getStr(), Integer.parseInt(regPanel.getPlz()), regPanel.getOrt(), regPanel.getLand());
						System.out.println("Benutzer erstellt.");
						} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (InkorrekteRegWerteException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Passwort stimmt nicht überein.");
				}
			}
		};
		regPanel.addActionListenerReg(listenerReg);
		
		// Event Listener für Logout Button 
		ActionListener listenerLogout = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				aktuellerBenutzer = null;
				frame.eastPanel.setVisible(true);
				frame.cardLayout.show(eastPanel, "regPanel");
				frame.cardLayout.show(centerPanel, "loginPanel");
				frame.westPanel.setVisible(false);
				frame.pack();
			}
		};
		kundeMenuPanel.addActionListenerLogout(listenerLogout);
		mitarbeiterMenuPanel.addActionListenerLogout(listenerLogout);
		
		// Listener für Kunden und Mitarbeitermenü initialisieren
		initListenerMitarbeiter();
		initListenerKunde();
	}
	/**
	 * Methode um alle Listener für das Kunden Menü zu initialisieren
	 */
	public void initListenerKunde() {
		final ShopGUI frame = this;
//	 	Listener für Zurück Button
		ActionListener listenerBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "kundeMenu");
				frame.pack();
			}
		};
		artInWPanel.addActionListenerBack(listenerBack);
		artMengeInWPanel.addActionListenerBack(listenerBack);
		artAusWPanel.addActionListenerBack(listenerBack);
		// Listener für Artikel in Warenkorb Button
		ActionListener listenerArtikelInWarenkorb = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artInWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtInW(listenerArtikelInWarenkorb);
		
		// Listener für Artikelmenge im Warenkorb ändern
		ActionListener listenerArtikelmenge = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artMengeInWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtMenge(listenerArtikelmenge);
		
		// Listener für Artikel aus Warenkorb entfernen
		ActionListener listenerArtikelAusWarenkorb = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artAusWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtAusW(listenerArtikelAusWarenkorb);
		
	}
	
	/**
	 * Methode um alle Listener für das Mitarbeiter Menü zu initialisieren
	 */
	public void initListenerMitarbeiter() {
		final ShopGUI frame = this;
//	 	Listener für Zurück Button
		ActionListener listenerBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "mitarbeiterMenu");
				frame.pack();
			}
		};
		newArtPanel.addActionListenerBack(listenerBack);
		artMengPanel.addActionListenerBack(listenerBack);
		artDelPanel.addActionListenerBack(listenerBack);
		mitRegPanel.addActionListenerBack(listenerBack);
		usrDelPanel.addActionListenerBack(listenerBack);
		// 	Listener für neuen Artikel anlegen
		ActionListener listenerNewArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "newArtPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerNewArt(listenerNewArt);
		// Listener für neuen Artikel anlegen (OK Button)
		ActionListener listenerNewArtOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String titel = newArtPanel.getArtikelName();
				double d = newArtPanel.getPreis();
				User akteur = aktuellerBenutzer;
				int menge = newArtPanel.getMenge();
				int packungsGroesse = newArtPanel.getPackungsgroesse();
				try {
					if (packungsGroesse <= 1) {
						shopVer.fuegeArtikelEin(titel, d, akteur, menge);
					} else {
						shopVer.fuegeArtikelEin(titel, d, akteur, menge, packungsGroesse);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.cardLayout.previous(westPanel);
				frame.getContentPane().invalidate();
				frame.getContentPane().revalidate();
				frame.pack();
			}
		};
		newArtPanel.addActionListenerOK(listenerNewArtOK);
//	 	Listener für Artikelmenge ändern
		ActionListener listenerArtMeng = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artMengPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerArtMeng(listenerArtMeng);
		// Listener für Artikel loeschen
		ActionListener listenerDelArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artDelPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerDelArt(listenerDelArt);
		// Listener für Mitarbeiter Registrieren
		ActionListener listenerMitReg = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "mitRegPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerMitReg(listenerMitReg);
		// Listener für User loeschen
		ActionListener listenerUsrDel = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "usrDelPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerUsrDel(listenerUsrDel);
	}
	
	private User userLogin(List<User> liste, String name, String passwort) throws LoginFehlgeschlagenException{
		Iterator<User> it = liste.iterator();
		while  (it.hasNext()) {
			User user = it.next();
			if(user.getName().equals(name) && (user.getPasswort().equals(passwort))){
				return user;
			}
		}
		throw new LoginFehlgeschlagenException();
	}
}

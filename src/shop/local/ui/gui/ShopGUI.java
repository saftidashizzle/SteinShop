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
	private LoginPanel loginPanel;
	private MitarbeiterMenuPanel mitarbeiterMenuPanel;
	private KundeMenuPanel kundeMenuPanel;
	private RegPanel regPanel;
	private TopPanel topPanel;
	private BottomPanel botPanel;
	private WarenkorbPanel warenkorbPanel;
	private ArtikelPanel artikelPanel;
	private MitarbeiterPanel mitarbeiterPanel;
	private NewArtPanel newArtPanel;
	private List<Artikel> artikelListe;
	private List<User> userListe;
	private List<Ereignis> ereignisListe;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
    private CardLayout cardLayout = new CardLayout();

	
	// Textbereich f�r Lognachrichten
	
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
		
		// Fenstergr��e einstellen
		this.setSize(1024, 768);
		
		// Initialisieren der Komponenten des Fensters
		this.initComponents();
		
		// Initialisieren der Listener f�r die interaktiven Komponenten
		// (Versehen von Buttons, Men�eintr�gen etc. mit "Leben")
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
	 * Methode, die in der Main am Anfang ausgef�hrt wird und das ganze Programm zum Laufen bringt.
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
		// Men�leiste programmieren: F�r unsere Anwendung bauen wir zwei Untermen�s "Datei" und "Garage"
		// Beachtet: Ein MenuBar-Objekt besteht aus Menu-Objekten, und jedes Menu-Objekt besteht aus MenuItem-Objekten
		MenuBar menubar = new MenuBar();
		
		// "Datei"-Men� zusammenbauen
		Menu menuDatei = new Menu("Datei");
		// "Beenden"-Men�eintrag zusammenbauen
		menuItemQuit = new MenuItem("Beenden");
		// Men�eintrag zum Men� hinzuf�gen
		menuDatei.add(menuItemQuit);
		menuDatei.addSeparator();
		// Men� zur MenuBar hinzuf�gen
		menubar.add(menuDatei);
		
		// Men�leiste zum Frame hinzuf�gen
		this.setMenuBar(menubar);
		
		// Wir initialisieren nun das Layout des Fensters: Damit strukturieren wir
		// das Fenster und k�nnen Unterbereiche definieren. In diesem Beispiel
		// verwende ich ein GridLayout mit zwei Spalten, die das Fenster vertikal aufteilen sollen
		this.setLayout(new BorderLayout(10,10));		
		
		// CardLayout erstellen fuer CENTER
		centerPanel = new JPanel();
		centerPanel.setLayout(cardLayout);
		// login panel erstellen und hinzufuegen
		loginPanel = new LoginPanel();
		centerPanel.add(loginPanel);
		// mitarbeiterPanel (tabs) erstellen und hinzufuegen
		mitarbeiterPanel = new MitarbeiterPanel(artikelListe, userListe, ereignisListe);
		mitarbeiterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.add(mitarbeiterPanel);
		// ArtikelPanel fuer Kunde erstellen und hinzufuegen
		artikelPanel = new ArtikelPanel(artikelListe);
		artikelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.add(artikelPanel);		
		// CenterPanel hinzuf�gen
		this.add(centerPanel, BorderLayout.CENTER);
		

		// CardLayout erstellen fuer EAST
		eastPanel = new JPanel();
		eastPanel.setLayout(cardLayout);
		// Registrierpanel erstellen und hinzuf�gen
		regPanel = new RegPanel();
		eastPanel.add(regPanel);
		// Warenkorb Panel erstellen und hinzuf�gen
		warenkorbPanel = new WarenkorbPanel();
		warenkorbPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		eastPanel.add(warenkorbPanel);
		// EastPanel hinzuf�gen
		this.add(eastPanel, BorderLayout.EAST);

		
		
		//CardLayout erstellen fuer WEST
		westPanel = new JPanel();
		westPanel.setLayout(cardLayout);
		// KundenMenu erstellen und hinzuf�gen
		kundeMenuPanel = new KundeMenuPanel();
		kundeMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(kundeMenuPanel);
		// Mitarbeiter Men� erstellen und hinzuf�gen
		mitarbeiterMenuPanel = new MitarbeiterMenuPanel();
		mitarbeiterMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(mitarbeiterMenuPanel);
		// WestPanel hinzuf�gen
		this.add(westPanel, BorderLayout.WEST);
		westPanel.setVisible(false);
		


		
		// Top Panel und Bot Panel erstellen und hinzuf�gen
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
		// dieser Methode steht. Damit vermeide ich, f�r jede Kleinigkeit
		// und jeden Listener eine einzelne Klasse schreiben zu m�ssen.
		
		// Diese Selbst-Referenz f�hre ich ein, um innerhalb der Listener-Implementierungen
		// auf die GUI zugreifen zu k�nnen. Alternativ k�nnte man auch jedes mal "GarageGUI.this" schreiben!
		final ShopGUI frame = this;
		
		// Quit-Listener f�r das "Datei"-Men�
		ActionListener listenerQuit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// Anwendung beenden
				frame.setVisible(false);
				frame.dispose();
			}
		};
		menuItemQuit.addActionListener(listenerQuit);
		
		// Event Listener f�r Login Button
		ActionListener listenerLogin = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				List<User> UserListe = shopVer.gibAlleUser();
				String name = loginPanel.getUserName();
				String pw = loginPanel.getPasswort();
				try {
					aktuellerBenutzer = userLogin(UserListe, name, pw);
					if (aktuellerBenutzer instanceof Kunde) {
						frame.cardLayout.last(centerPanel);
						frame.westPanel.setVisible(true);						
					} else if(aktuellerBenutzer instanceof Mitarbeiter) {
						frame.westPanel.setVisible(true);
						frame.eastPanel.setVisible(false);
						frame.cardLayout.next(westPanel);
						frame.cardLayout.next(centerPanel);
					}
					frame.getContentPane().invalidate();
					frame.getContentPane().validate();
					frame.pack();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		};
		loginPanel.addActionListenerLogin(listenerLogin);
	
		// Event Listener f�r Registrier Button von regPanel
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
					System.out.println("Passwort stimmt nicht �berein.");
				}
			}
		};
		regPanel.addActionListenerReg(listenerReg);
		
		// Event Listener f�r Logout Button 
		ActionListener listenerLogout = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				aktuellerBenutzer = null;
				frame.eastPanel.setVisible(true);
				frame.cardLayout.first(eastPanel);
				frame.cardLayout.first(centerPanel);
				frame.westPanel.setVisible(false);
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();
				frame.pack();
			}
		};
		kundeMenuPanel.addActionListenerLogout(listenerLogout);
		mitarbeiterMenuPanel.addActionListenerLogout(listenerLogout);
		// Listener f�r Kunden und Mitarbeitermen� initialisieren
		initListenerMitarbeiter();
	}
//	public void initListenerKunde() {
	//	// Listener f�r Artikel in Warenkorb Button
	//	ActionListener listenerArtikelInWarenkorb = new ActionListener() {
	//		@Override
	//		public void actionPerformed(ActionEvent ae) {
	//		
	//	};
	//	kundeMenuPanel..addActionListenerLogout(listenerLogout);
//	}
	
	/**
	 * Methode um alle Listener f�r das Mitarbeiter Men� zu initialisieren
	 */
	public void initListenerMitarbeiter() {
		final ShopGUI frame = this;
		// 	Listener f�r neuen Artikel anlegen
		ActionListener listenerNewArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.getContentPane().remove(mitarbeiterMenuPanel);
				newArtPanel = new NewArtPanel();
				frame.add(newArtPanel, BorderLayout.CENTER);
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerNewArt(listenerNewArt);
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

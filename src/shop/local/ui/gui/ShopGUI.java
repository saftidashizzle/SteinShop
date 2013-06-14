package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import shop.local.domain.ShopVerwaltung;
import shop.local.domain.exceptions.InkorrekteRegWerteException;
import shop.local.domain.exceptions.LoginFehlgeschlagenException;
import shop.local.valueobjects.Artikel;
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
	
	// Zusammengesetzte Komponente zum Hinzufügen eines Kfz
	// private AddKfzComponent addkfzcomp;
	// Textbereich für Lognachrichten
	
	public ShopGUI() {
		super("SteinShop");
		shopVer = new ShopVerwaltung();
		aktuellerBenutzer = null;
		
		// Fenstergröße einstellen
		this.setSize(800, 600);
		
		// Initialisieren der Komponenten des Fensters
		this.initComponents();
		
		// Initialisieren der Listener für die interaktiven Komponenten
		// (Versehen von Buttons, Menüeinträgen etc. mit "Leben")
		this.initListeners();
		
		// Zuletzt schalten wir den Frame auf "sichtbar"
		this.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			shopVer.ladeDaten();
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
		this.setLayout(new BorderLayout());		
		
		loginPanel = new LoginPanel();
		this.add(loginPanel, BorderLayout.CENTER);
		regPanel = new RegPanel();
		this.add(regPanel, BorderLayout.EAST);
		
		kundeMenuPanel = new KundeMenuPanel();
		warenkorbPanel = new WarenkorbPanel();
		artikelPanel = new ArtikelPanel();
		artikelLaden();
		mitarbeiterMenuPanel = new MitarbeiterMenuPanel();
		
		
		topPanel = new TopPanel();
		this.add(topPanel, BorderLayout.NORTH);
		botPanel = new BottomPanel();
		this.add(botPanel, BorderLayout.SOUTH);
	}
	private void artikelLaden() {
		List<Artikel> liste = shopVer.gibAlleArtikel();
		Iterator<Artikel> it = liste.iterator();
		while  (it.hasNext()) {
			Artikel a = it.next();
			artikelPanel.artikelListe.add(new JLabel("" + a.getNummer()));
			artikelPanel.artikelListe.add(new JLabel(a.getName()));
			artikelPanel.artikelListe.add(new JLabel("" + a.getMenge()));
			artikelPanel.artikelListe.add(new JLabel("" + a.getPreis()));
			artikelPanel.artikelListe.add(new JLabel("PACKUNGSGROESSE"));
		}
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
						frame.getContentPane().remove(loginPanel);
						frame.getContentPane().remove(regPanel);
						frame.add(kundeMenuPanel, BorderLayout.WEST);
						frame.add(artikelPanel, BorderLayout.CENTER);
						frame.add(warenkorbPanel, BorderLayout.EAST);
					} else if(aktuellerBenutzer instanceof Mitarbeiter) {
						frame.getContentPane().remove(loginPanel);
						frame.getContentPane().remove(regPanel);
						frame.add(mitarbeiterMenuPanel, BorderLayout.WEST);
						frame.add(artikelPanel, BorderLayout.CENTER);
					}
					frame.getContentPane().invalidate();
					frame.getContentPane().validate();
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InkorrekteRegWerteException e) {
						// TODO Auto-generated catch block
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
						
						if (aktuellerBenutzer instanceof Kunde) {
							aktuellerBenutzer = null;
							frame.getContentPane().remove(kundeMenuPanel);
							frame.getContentPane().remove(warenkorbPanel);
							frame.getContentPane().remove(artikelPanel);
							frame.add(loginPanel, BorderLayout.CENTER);
							frame.add(regPanel, BorderLayout.EAST);
						} else {
							aktuellerBenutzer = null;
							frame.getContentPane().remove(mitarbeiterMenuPanel);
							frame.getContentPane().remove(artikelPanel);
							frame.add(loginPanel, BorderLayout.CENTER);
							frame.add(regPanel, BorderLayout.EAST);
						}
						frame.getContentPane().invalidate();
						frame.getContentPane().validate();
					}
				};
				kundeMenuPanel.addActionListenerLogout(listenerLogout);
				mitarbeiterMenuPanel.addActionListenerLogout(listenerLogout);			
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

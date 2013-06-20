package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import shop.local.domain.EreignisVerwaltung;
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
	Kunde kunde = null;
	
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
	private ArtikelProtokollPanel protokollPanel;
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

	
	// Textbereich f�r Lognachrichten
	
	public ShopGUI(String s) {
		super(s);
		shopVer = new ShopVerwaltung();
		// beim Start des Programms auf null gesetzt, durch login wird Benutzer hier gespeichert
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
		this.setSize(800, 600);
		
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
		ShopGUI shop = new ShopGUI("SteinShop");
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
//		menuDatei.addSeparator();
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
		centerPanel.add(loginPanel, "loginPanel");
		// mitarbeiterPanel (tabs) erstellen und hinzufuegen
		mitarbeiterPanel = new MitarbeiterPanel(artikelListe, userListe, ereignisListe);
		mitarbeiterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.add(mitarbeiterPanel, "mitarbeiterPanel");
		// ArtikelPanel fuer Kunde erstellen und hinzufuegen
		artikelPanel = new ArtikelPanel(artikelListe);
		artikelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.add(artikelPanel, "artikelPanel");	
		
		// CenterPanel hinzuf�gen
		this.add(centerPanel, BorderLayout.CENTER);
		

		// CardLayout erstellen fuer EAST
		eastPanel = new JPanel();
		eastPanel.setLayout(cardLayout);
		// Registrierpanel erstellen und hinzuf�gen
		regPanel = new RegPanel();
		eastPanel.add(regPanel, "regPanel");
		// EastPanel hinzuf�gen
		this.add(eastPanel, BorderLayout.EAST);

		
		
		//CardLayout erstellen fuer WEST
		westPanel = new JPanel();
		westPanel.setLayout(cardLayout);
		// KundenMenu erstellen und hinzuf�gen
		kundeMenuPanel = new KundeMenuPanel();
		kundeMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(kundeMenuPanel, "kundeMenu");
		// Mitarbeiter Men� erstellen und hinzuf�gen
		mitarbeiterMenuPanel = new MitarbeiterMenuPanel();
		mitarbeiterMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.add(mitarbeiterMenuPanel, "mitarbeiterMenu");
		
		// ****** Kundenmenue ********
		// Panel f�r neuen Artikel erstellen
		newArtPanel = new NewArtPanel();
		westPanel.add(newArtPanel, "newArtPanel");
		// Panel f�r Artikelmenge �ndern erstellen
		artMengPanel = new ArtikelmengeAendernPanel();
		westPanel.add(artMengPanel, "artMengPanel");
		// Panel f�r Artikel l�schen erstellen
		artDelPanel = new ArtikelLoeschenPanel();
		westPanel.add(artDelPanel, "artDelPanel");
		// Panel f�r Mitarbeiter Registrieren
		mitRegPanel = new MitarbeiterRegistrierenPanel();
		westPanel.add(mitRegPanel, "mitRegPanel");
		//Panel f�r User l�schen
		usrDelPanel = new UserLoeschenPanel();
		westPanel.add(usrDelPanel, "usrDelPanel");
		
		// ******* Mitarbeitermenue *******
		// Panel f�r Artikel in Warenkorb
		artInWPanel = new ArtikelInWarenkorbPanel();
		westPanel.add(artInWPanel, "artInWPanel");
		// Panel f�r Artikelmenge �ndern
		artMengeInWPanel = new ArtikelmengeImWarenkorbPanel();
		westPanel.add(artMengeInWPanel, "artMengeInWPanel");
		// Panel f�r Artikel aus Warenkorb
		artAusWPanel = new ArtikelAusWarenkorbPanel();
		westPanel.add(artAusWPanel, "artAusWPanel");
		
		
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
						kunde = (Kunde)aktuellerBenutzer;
						frame.cardLayout.show(westPanel, "kundeMenu");
						frame.cardLayout.show(centerPanel, "artikelPanel");
						// Warenkorb Panel erstellen und hinzuf�gen
						warenkorbPanel = new WarenkorbPanel(kunde.getWarenkorb());
						warenkorbPanel.setBorder(BorderFactory.createLineBorder(Color.black));

						// Listener f�r zur Kasse Button
						ActionListener listenerZurKasse = new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae) {
								try {
									shopVer.rechnungErstellen((Kunde)aktuellerBenutzer);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						};
						warenkorbPanel.addActionListenerZurKasse(listenerZurKasse);
						eastPanel.add(warenkorbPanel, "warenkorbPanel");
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
					e.printStackTrace();
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
				kunde = null;
				frame.eastPanel.setVisible(true);
				frame.cardLayout.show(eastPanel, "regPanel");
				frame.cardLayout.show(centerPanel, "loginPanel");
				frame.westPanel.setVisible(false);
				frame.pack();
			}
		};
		kundeMenuPanel.addActionListenerLogout(listenerLogout);
		mitarbeiterMenuPanel.addActionListenerLogout(listenerLogout);
		
		// Listener f�r Kunden und Mitarbeitermen� initialisieren
		initListenerMitarbeiter();
		initListenerKunde();
	}
	/**
	 * Methode um alle Listener f�r das Kunden Men� zu initialisieren
	 */
	public void initListenerKunde() {
		final ShopGUI frame = this;
//	 	Listener f�r Zur�ck Button
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
		// Listener f�r Artikel in Warenkorb Button
		ActionListener listenerArtikelInWarenkorb = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artInWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtInW(listenerArtikelInWarenkorb);
		// Listener f�r Artikel in Warenkorb Button (okay Button)
		ActionListener listenerArtikelInWarenkorbOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.artikelInWarenkorb(artInWPanel.getArtikelNummer(), artInWPanel.getMenge(), aktuellerBenutzer);
					HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);
					frame.cardLayout.show(westPanel, "kundeMenu");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		artInWPanel.addActionListenerOK(listenerArtikelInWarenkorbOK);
		// Listener f�r Artikelmenge im Warenkorb �ndern
		ActionListener listenerArtikelmenge = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artMengeInWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtMenge(listenerArtikelmenge);
		// Listener f�r Artikelmenge im Warenkorb �ndern Button (okay Button)
		ActionListener listenerArtikelmengeOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.artikelMengeImWarenkorbAendern(artMengeInWPanel.getArtikelNummer(), artMengeInWPanel.getMenge(), (Kunde)aktuellerBenutzer);
					frame.cardLayout.show(westPanel, "kundeMenu");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		artMengeInWPanel.addActionListenerOK(listenerArtikelmengeOK);
		// Listener f�r Artikel aus Warenkorb entfernen
		ActionListener listenerArtikelAusWarenkorb = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artAusWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtAusW(listenerArtikelAusWarenkorb);
		// Listener f�r Artikel aus Warenkorb entfernen Button (okay Button)
		ActionListener listenerArtikelAusWarenkorbOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.artikelAusWarenkorb(artAusWPanel.getArtikelNummer(), (Kunde)aktuellerBenutzer);					
					HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);
					frame.cardLayout.show(westPanel, "kundeMenu");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		artAusWPanel.addActionListenerOK(listenerArtikelAusWarenkorbOK);
		// Listener f�r Warenkorb leeren
		ActionListener listenerWarenkorbLeeren = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.warenkorbLeeren((Kunde)aktuellerBenutzer);
					HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		kundeMenuPanel.addActionListenerWarenkorbLeeren(listenerWarenkorbLeeren);
		// Listener f�r Artikel nach Namen ordnen
		ActionListener listenerArtikelNamen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.artikelNachNamenOrdnen();
					artikelListe = shopVer.gibAlleArtikel();
					ArtikelTableModell atm = (ArtikelTableModell) artikelPanel.artikelListe.getModel();
					atm.updateDataVector(artikelListe);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		kundeMenuPanel.addActionListenerArtikelNamen(listenerArtikelNamen);
		// Listener f�r Artikel nach Nummern ordnen
		ActionListener listenerArtikelNummern = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.artikelNachZahlenOrdnen();
					artikelListe = shopVer.gibAlleArtikel();
					ArtikelTableModell atm = (ArtikelTableModell) artikelPanel.artikelListe.getModel();
					atm.updateDataVector(artikelListe);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		kundeMenuPanel.addActionListenerArtikelNummer(listenerArtikelNummern);
	}
	
	/**
	 * Methode um alle Listener f�r das Mitarbeiter Men� zu initialisieren
	 */
	public void initListenerMitarbeiter() {
		final ShopGUI frame = this;
//	 	Listener f�r Zur�ck Button
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
		// 	Listener f�r neuen Artikel anlegen
		ActionListener listenerNewArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "newArtPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerNewArt(listenerNewArt);
		// Listener f�r neuen Artikel anlegen (OK Button)
		ActionListener listenerNewArtOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String titel = newArtPanel.getArtikelName();
				double d = newArtPanel.getPreis();
				int menge = newArtPanel.getMenge();
				int packungsGroesse = newArtPanel.getPackungsgroesse();
				try {
					if (packungsGroesse <= 1) {
						shopVer.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
						artikelListe = shopVer.gibAlleArtikel();	
						mitarbeiterPanel.updateArtikelListe(artikelListe);
					} else {
						shopVer.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
						artikelListe = shopVer.gibAlleArtikel();
						mitarbeiterPanel.updateArtikelListe(artikelListe);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.cardLayout.show(westPanel, "mitarbeiterMenu");
				frame.pack();
			}
		};
		newArtPanel.addActionListenerOK(listenerNewArtOK);
		// Listener f�r Artikelmenge �ndern
		ActionListener listenerArtMeng = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artMengPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerArtMeng(listenerArtMeng);
		// Listener f�r Artikelmenge �ndern (okay button)
		ActionListener listenerArtMengOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int nummer = artMengPanel.getNummer();
				int anzahl = artMengPanel.getMenge();
				try {
					shopVer.mengeAendern(nummer, anzahl, aktuellerBenutzer);
					artikelListe = shopVer.gibAlleArtikel();
					mitarbeiterPanel.updateArtikelListe(artikelListe);
					frame.cardLayout.show(westPanel, "mitarbeiterMenu");
					frame.pack();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		artMengPanel.addActionListenerOK(listenerArtMengOK);
		// Listener f�r Artikel loeschen
		ActionListener listenerDelArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artDelPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerDelArt(listenerDelArt);
		// Listener f�r Artikel loeschen (okay button)
		ActionListener listenerArtDelOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					shopVer.loescheArtikel(artDelPanel.getArtikelNummer(), aktuellerBenutzer);
					artikelListe = shopVer.gibAlleArtikel();
					mitarbeiterPanel.updateArtikelListe(artikelListe);
					frame.cardLayout.show(westPanel, "mitarbeiterMenu");
					frame.pack();	
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		artDelPanel.addActionListenerOK(listenerArtDelOK);
		// Listener f�r Mitarbeiter Registrieren
		ActionListener listenerMitReg = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "mitRegPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerMitReg(listenerMitReg);
		// Listener f�r Mitarbeiter Registrieren (okay button)
		ActionListener listenerMitRegOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					String name = mitRegPanel.getUserName();
					String pw1 = mitRegPanel.getPw1();
					String pw2 = mitRegPanel.getPw2();
					String anrede = mitRegPanel.getAnrede();
					String vorUndZuName = mitRegPanel.getName();
					if (pw1.equals(pw2)) {
						shopVer.fuegeUserEin(name, pw1, anrede, vorUndZuName);
						frame.cardLayout.show(westPanel, "kundeMenu");
						frame.pack();
					} else {
						JOptionPane.showMessageDialog(null, "Passwort stimmt nicht �berein."); 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		mitRegPanel.addActionListenerReg(listenerMitRegOK);
		// Listener f�r User loeschen
		ActionListener listenerUsrDel = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "usrDelPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerUsrDel(listenerUsrDel);
		// Listener f�r User loeschen (okay button)
		ActionListener listenerUsrDelOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int userNr = usrDelPanel.getUserNummer();
				try {
					shopVer.loescheUser(userNr, aktuellerBenutzer);
					frame.cardLayout.show(westPanel, "kundeMenu");
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		usrDelPanel.addActionListenerOK(listenerUsrDelOK);
		// Listener f�r Artikelmengenverlauf anzeigen
		ActionListener listenerArtMengVer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//TODO gew�nschte ArtikelID einlesen und �bergeben
				Artikel a = artikelListe.get(0);
				List<Ereignis> artikelVerlauf = shopVer.erVer.gibEreignisseNachArtikelUndTagen(a);
				protokollPanel = new ArtikelProtokollPanel(artikelVerlauf);
				protokollPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				centerPanel.add(protokollPanel, "protokollPanel");
				frame.cardLayout.show(centerPanel, "protokollPanel");
				//TODO Artikelmengenverlauf anzeigen
			}
		};
		mitarbeiterMenuPanel.addActionListenerProtokoll(listenerArtMengVer);
	}
	/**
	 * Methode f�r Userlogin
	 * @param liste Userliste
	 * @param name Eingegebener Name
	 * @param passwort eingegebenes Passwort
	 * @return liefert den angemeldeten User
	 * @throws LoginFehlgeschlagenException
	 */
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
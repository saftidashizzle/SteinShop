package gui;

import gui.rechnung.RechnungPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.ClientInterfaceImpl;
import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.Kunde;
import valueobjects.MehrfachArtikel;
import valueobjects.Mitarbeiter;
import valueobjects.Rechnung;
import valueobjects.User;
import domain.comperatorArtikelName;
import domain.exceptions.ArtikelAngabenInkorrektException;
import domain.exceptions.ArtikelMengeInkorrektException;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNichtVerfuegbarException;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.UserIstSchonEingeloggtException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;


public class ShopGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5711673086933143461L;
//	ShopVerwaltung shopVer;
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
	private ProtokollMenuPanel protokollMenuPanel;
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
	private List<Ereignis> ereignisListe;
	private Object[][] userListe;
	// Grobe RichtungsPanels
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
    private CardLayout cardLayout = new CardLayout();
    
    private ClientInterfaceImpl connection;

	
	// Textbereich für Lognachrichten
	
	public ShopGUI(String s) {
		super(s);
		aktuellerBenutzer = null;
		try {
			connection = new ClientInterfaceImpl();
			
			try {
				connection.connectToServer();
			} catch (Exception e) {
				// hier kein message dialog weil die exceptions keine message definiert haben
				e.printStackTrace();
			}
			
			artikelListe = connection.gibAlleArtikel();
			userListe = connection.gibAlleUser();
			ereignisListe = connection.gibProtokollListe();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
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
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ShopGUI shop = new ShopGUI("Stone Lounge");		
	}
	/**
	 * Initialisieren aller Komponenten im Fenster
	 */
	public void initComponents() {
		// Menüleiste 
		MenuBar menubar = new MenuBar();
		
		// "Datei"-Menü zusammenbauen
		Menu menuDatei = new Menu("Datei");
		// "Beenden"-Menüeintrag zusammenbauen
		menuItemQuit = new MenuItem("Beenden");
		// Menüeintrag zum Menü hinzufügen
		menuDatei.add(menuItemQuit);
		// Menü zur MenuBar hinzufügen
		menubar.add(menuDatei);
		
		// Menüleiste zum Frame hinzufügen
		this.setMenuBar(menubar);
		
		// Wir initialisieren nun das Layout des Fensters: Damit strukturieren wir
		// das Fenster und können Unterbereiche definieren. In diesem Beispiel
		// verwende ich ein GridLayout mit zwei Spalten, die das Fenster vertikal aufteilen sollen
		getContentPane().setLayout(new BorderLayout(10,10));		
		
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
		// Registrierpanel erstellen und hinzufügen
		regPanel = new RegPanel();
		centerPanel.add(regPanel, "regPanel");
		
		// CenterPanel hinzufügen
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		

		// CardLayout erstellen fuer EAST
		eastPanel = new JPanel();
		eastPanel.setLayout(cardLayout);
		// EastPanel hinzufügen
		getContentPane().add(eastPanel, BorderLayout.EAST);

		
		
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
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setVisible(false);
		this.pack();
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
				try {
					connection.logout();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				frame.dispose();
			}
		};
		menuItemQuit.addActionListener(listenerQuit);
		
		// WINDOW LISTENER
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				super.windowClosing(e);
				try {
					connection.logout();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});


		// Back Button für Registrier Panel
		ActionListener listenerBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(centerPanel, "loginPanel");
				frame.pack();
			}
		};		
		regPanel.addActionListenerBack(listenerBack);

		// Event Listener für Login Button
		ActionListener listenerLogin = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String name = loginPanel.getUserName();
				char[] pw = loginPanel.getPasswort();
				try {
					aktuellerBenutzer = connection.userLogin(name, pw);
					if (aktuellerBenutzer instanceof Kunde) {
						// Top Panel und Bot Panel erstellen und hinzufügen
						topPanel = new TopPanel();
						frame.getContentPane().add(topPanel, BorderLayout.NORTH);
						
						// Bottom Panel 
						botPanel = new BottomPanel();
						frame.getContentPane().add(botPanel, BorderLayout.SOUTH);
						kunde = (Kunde)aktuellerBenutzer;
						frame.cardLayout.show(westPanel, "kundeMenu");
						frame.cardLayout.show(centerPanel, "artikelPanel");
						// Warenkorb Panel erstellen und hinzufügen
						warenkorbPanel = new WarenkorbPanel(kunde.getWarenkorb());
						warenkorbPanel.setBorder(BorderFactory.createLineBorder(Color.black));
						
						// Listener für zur Kasse Button
						ActionListener listenerZurKasse = new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae) {
								try {
									Rechnung re = connection.rechnungErstellen(kunde);
									RechnungPanel rechnungPanel = new RechnungPanel(re);
									centerPanel.add(rechnungPanel, "rechnungPanel");
									frame.cardLayout.show(centerPanel, "rechnungPanel");
									connection.warenkorbLeeren(kunde);
									kunde = (Kunde)connection.getUser();
									HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
									WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
									wtm.updateDataVector(warenkorbListe);			
									frame.pack();
									// Listener für zur Kasse Button
									ActionListener listenerBack = new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent ae) {
											frame.cardLayout.show(centerPanel, "artikelPanel");
											frame.pack();
										}
									};
									rechnungPanel.rechnungListePanel.addActionListenerBack(listenerBack);
									
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e.getMessage()); 
									e.printStackTrace();
								}
							}
						};
						warenkorbPanel.addActionListenerZurKasse(listenerZurKasse);
						eastPanel.add(warenkorbPanel, "warenkorbPanel");
						frame.cardLayout.show(eastPanel, "warenkorbPanel");
						frame.westPanel.setVisible(true);		
						frame.eastPanel.setVisible(true);	
						frame.pack();

					} else if(aktuellerBenutzer instanceof Mitarbeiter) {
						// Top Panel und Bot Panel erstellen und hinzufügen
						topPanel = new TopPanel();
						frame.getContentPane().add(topPanel, BorderLayout.NORTH);
						
						// Bottom Panel 
						botPanel = new BottomPanel();
						frame.getContentPane().add(botPanel, BorderLayout.SOUTH);
						// protokollMenu wird erstellt
						protokollMenuPanel = new ProtokollMenuPanel(connection.gibAlleArtikel());
						protokollMenuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
						eastPanel.add(protokollMenuPanel, "protokollMenuPanel");
						
						// Selection Listener für ProtokolMenuPanel
						ListSelectionListener listSelectProtokollMenuPanel = new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
				        		if(e.getValueIsAdjusting()) return;
				        	        int row = protokollMenuPanel.artikelListe.getSelectedRow();
				        	        Artikel a = null;
									try {
										a = connection.findArtikelByNumber(Integer.parseInt((String) protokollMenuPanel.artikelListe.getValueAt(row, 0)));
									} catch (NumberFormatException
											| ArtikelNichtVerfuegbarException e1) {
										JOptionPane.showMessageDialog(null, e); 
										e1.printStackTrace();
									}
									protokollPanel.drawChart(connection.gibEreignisseNachArtikelUndTagen(a), a.getName());
				        	        frame.repaint();
				        	        frame.revalidate();
				        	        
					        }            
				        };
				        protokollMenuPanel.addListSelectionListener(listSelectProtokollMenuPanel);
				        ActionListener listenerBack = new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae) {
								frame.cardLayout.show(westPanel, "mitarbeiterMenu");
								frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
								eastPanel.setVisible(false);
								westPanel.setVisible(true);
								frame.pack();
							}
						};
						protokollMenuPanel.addActionListenerBack(listenerBack);
						
						frame.westPanel.setVisible(true);
						frame.eastPanel.setVisible(false);
						frame.cardLayout.show(westPanel, "mitarbeiterMenu");
						frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
						frame.pack();
					}
					frame.pack();
				} catch (LoginFehlgeschlagenException e) {
					JOptionPane.showMessageDialog(null, e); 
					e.printStackTrace();
				} catch (UserIstSchonEingeloggtException e) {
					JOptionPane.showMessageDialog(null, e); 
					e.printStackTrace();
				}
			}
		};
		loginPanel.addActionListenerLogin(listenerLogin);
	
		// Event Listener für Registrieren Button
		ActionListener listenerRegistrieren = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(centerPanel, "regPanel");
				frame.pack();
			}
		};
		loginPanel.addActionListenerRegistieren(listenerRegistrieren);
		
		// Event Listener für Registrier Button (OKAY) von regPanel
		ActionListener listenerReg = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(Arrays.equals(regPanel.getPw1(),regPanel.getPw2())) {
					try {
						frame.connection.fuegeUserEin(regPanel.getUserName(), regPanel.getPw1(), regPanel.getAnrede(), regPanel.getName(), regPanel.getStr(), Integer.parseInt(regPanel.getPlz()), regPanel.getOrt(), regPanel.getLand());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, e.getMessage()); 
							e.printStackTrace();
					} catch (InkorrekteRegWerteException e) {
						JOptionPane.showMessageDialog(null, e.getMessage()); 
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Passwort stimmt nicht überein.");
				}
				frame.cardLayout.show(centerPanel, "loginPanel");
				frame.pack();			}
		};
		regPanel.addActionListenerReg(listenerReg);
		
		// Event Listener für Logout Button 
		ActionListener listenerLogout = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				aktuellerBenutzer = null;
				kunde = null;
				connection.userLogout();
				frame.topPanel.setVisible(false);
				frame.botPanel.setVisible(false);
				frame.eastPanel.setVisible(false);
				frame.cardLayout.show(centerPanel, "loginPanel");
				frame.westPanel.setVisible(false);
				// Vorgefüllte textFelder löschen
    	        usrDelPanel.setArtikelNummerTextfield(null);
    	        artDelPanel.setArtikelNummerTextfield(null);
    	        artMengPanel.setArtikelNummerTextfield(null);
    	        artMengPanel.setArtikelMengeTextfield(null);
    	        artAusWPanel.setArtikelNummerTextfield(null);
    	        artMengeInWPanel.setArtikelNummerTextfield(null);
    	        artMengeInWPanel.setArtikelMengeTextfield(null);
    	        artInWPanel.setArtikelNummerTextfield(null);
    	        artInWPanel.setArtikelMengeTextfield(null);
    	        usrDelPanel.setArtikelNummerTextfield(null);
				
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
		// Listener für Artikel in Warenkorb Button (okay Button)
		ActionListener listenerArtikelInWarenkorbOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					kunde = connection.artikelInWarenkorb(artInWPanel.getArtikelNummer(), artInWPanel.getMenge(), (Kunde)aktuellerBenutzer);
					HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);					
					frame.cardLayout.show(westPanel, "kundeMenu");
				} catch (ArtikelNichtVerfuegbarException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (ArtikelMengeReichtNichtException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (WarenkorbExceedsArtikelbestandException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		};
		artInWPanel.addActionListenerOK(listenerArtikelInWarenkorbOK);
		// Selection Listener für ArtikelInWarenkorb Panel
		ListSelectionListener listSelectArtInW = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
        		if(e.getValueIsAdjusting()) return;
        	        int row = artikelPanel.artikelListe.getSelectedRow();
        	        String artikelNr = (String) artikelPanel.artikelListe.getValueAt(row, 0);
        	        artInWPanel.setArtikelNummerTextfield("" + artikelNr);
        	        try {
						if (connection.findArtikelByNumber(Integer.parseInt(artikelNr)) instanceof MehrfachArtikel) {
						    artInWPanel.setArtikelMengeTextfield((String) artikelPanel.artikelListe.getValueAt(row, 4));
						} else {
							artInWPanel.setArtikelMengeTextfield("1");
						}
					} catch (ArtikelNichtVerfuegbarException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()); 
						e1.printStackTrace();
					}	            	        	
	        }            
        };
		artikelPanel.addListSelectionListener(listSelectArtInW);
		// Listener für Artikelmenge im Warenkorb ändern
		ActionListener listenerArtikelmenge = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artMengeInWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtMenge(listenerArtikelmenge);
		// Listener für Artikelmenge im Warenkorb ändern Button (okay Button)
		ActionListener listenerArtikelmengeOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					HashMap<Artikel, Integer> warenkorbListe = connection.artikelMengeImWarenkorbAendern(artMengeInWPanel.getArtikelNummer(), artMengeInWPanel.getMenge(), (Kunde)aktuellerBenutzer);					
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);
					frame.cardLayout.show(westPanel, "kundeMenu");
				} catch (ArtikelNichtVerfuegbarException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (MitarbeiterNichtVorhandenException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		};
		artMengeInWPanel.addActionListenerOK(listenerArtikelmengeOK);
		// Selection Listener für Artikelmenge im Warenkorb Ändern Panel
		ListSelectionListener listSelectArtMeng = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
        		if(e.getValueIsAdjusting()) return;
        			int row = artikelPanel.artikelListe.getSelectedRow();
        	        String artikelNr = (String) artikelPanel.artikelListe.getValueAt(row, 0);
            		try {
						if (connection.findArtikelByNumber(Integer.parseInt(artikelNr)) instanceof MehrfachArtikel) {
							artMengeInWPanel.setArtikelNummerTextfield(artikelNr);
							artMengeInWPanel.setArtikelMengeTextfield((String) artikelPanel.artikelListe.getValueAt(row, 4));
						} else {
							artMengeInWPanel.setArtikelNummerTextfield(artikelNr);
							artMengeInWPanel.setArtikelMengeTextfield("1");
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()); 
						e1.printStackTrace();
					} catch (ArtikelNichtVerfuegbarException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}
	        }            
        };
        artikelPanel.addListSelectionListener(listSelectArtMeng);
		// Listener für Artikel aus Warenkorb entfernen
		ActionListener listenerArtikelAusWarenkorb = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "artAusWPanel");
			}
		};
		kundeMenuPanel.addActionListenerArtAusW(listenerArtikelAusWarenkorb);
		// Listener für Artikel aus Warenkorb entfernen Button (okay Button)
		ActionListener listenerArtikelAusWarenkorbOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					kunde = connection.artikelAusWarenkorb(artAusWPanel.getArtikelNummer(), (Kunde)aktuellerBenutzer);					
					HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);
					frame.cardLayout.show(westPanel, "kundeMenu");
				} catch (ArtikelMengeReichtNichtException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (ArtikelNichtVerfuegbarException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (MitarbeiterNichtVorhandenException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (WarenkorbIstLeerException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				}
			}
		};
		artAusWPanel.addActionListenerOK(listenerArtikelAusWarenkorbOK);
		// Selection Listener für Artikel aus Warenkorb Entfernen Panel
		ListSelectionListener listSelectArtAusW = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
            		if(e.getValueIsAdjusting()) return;
            	        int row = artikelPanel.artikelListe.getSelectedRow();
            	        artAusWPanel.setArtikelNummerTextfield((String) artikelPanel.artikelListe.getValueAt(row, 0));
        }            
        };
        artikelPanel.addListSelectionListener(listSelectArtAusW);
		// Listener für Warenkorb leeren
		ActionListener listenerWarenkorbLeeren = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					kunde = connection.warenkorbLeeren((Kunde)aktuellerBenutzer);
					HashMap<Artikel, Integer> warenkorbListe = kunde.getWarenkorb().getInhalt();
					WarenkorbTableModell wtm = (WarenkorbTableModell) warenkorbPanel.warenkorbListe.getModel();
					wtm.updateDataVector(warenkorbListe);
				} catch (WarenkorbIstLeerException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch (MitarbeiterNichtVorhandenException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				}
			}
		};
		kundeMenuPanel.addActionListenerWarenkorbLeeren(listenerWarenkorbLeeren);
	}
	protected List<Artikel> artikelNachNamenOrdnen(List<Artikel> artikelListe) {
		Collections.sort(artikelListe, new comperatorArtikelName());
		return artikelListe;
	}
	/**
	 * Methode um alle Listener für das Mitarbeiter Menü zu initialisieren
	 */
	public void initListenerMitarbeiter() {
		final ShopGUI frame = this;
//	 	Listener für Zurück Button
		ActionListener listenerBackMitarbeiter = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(westPanel, "mitarbeiterMenu");
				frame.pack();
			}
		};
		newArtPanel.addActionListenerBack(listenerBackMitarbeiter);
		artMengPanel.addActionListenerBack(listenerBackMitarbeiter);
		artDelPanel.addActionListenerBack(listenerBackMitarbeiter);
		mitRegPanel.addActionListenerBack(listenerBackMitarbeiter);
		usrDelPanel.addActionListenerBack(listenerBackMitarbeiter);
		// 	Listener für neuen Artikel anlegen
		ActionListener listenerNewArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
				frame.mitarbeiterPanel.tabbedPane.setSelectedIndex(0);
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
				int menge = newArtPanel.getMenge();
				int packungsGroesse = newArtPanel.getPackungsgroesse();
				try {
					if (packungsGroesse <= 1) {
						connection.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
						artikelListe = connection.gibAlleArtikel();	
						mitarbeiterPanel.updateArtikelListe(artikelListe);
					} else {
						connection.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
						artikelListe = connection.gibAlleArtikel();
						mitarbeiterPanel.updateArtikelListe(artikelListe);
					}
				} catch (ArtikelAngabenInkorrektException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				}
				frame.cardLayout.show(westPanel, "mitarbeiterMenu");
				frame.pack();
			}
		};
		newArtPanel.addActionListenerOK(listenerNewArtOK);
		// Listener für Artikelmenge ändern
		ActionListener listenerArtMeng = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
				frame.mitarbeiterPanel.tabbedPane.setSelectedIndex(0);
				frame.cardLayout.show(westPanel, "artMengPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerArtMeng(listenerArtMeng);
		// Listener für Artikelmenge ändern (okay button)
		ActionListener listenerArtMengOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int nummer = artMengPanel.getNummer();
				int anzahl = artMengPanel.getMenge();
				try {
					connection.mengeAendern(nummer, anzahl, aktuellerBenutzer);
					artikelListe = connection.gibAlleArtikel();
					mitarbeiterPanel.updateArtikelListe(artikelListe);
					frame.cardLayout.show(westPanel, "mitarbeiterMenu");
					frame.pack();
				} catch(ArtikelMengeInkorrektException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				} catch(ArtikelNichtVerfuegbarException e) {
					JOptionPane.showMessageDialog(null, e.getMessage()); 
					e.printStackTrace();
				}
			}
		};
		artMengPanel.addActionListenerOK(listenerArtMengOK);
		// Selection Listener für Artikelmenge Ändern Panel
		// TODO Funktioniert komischerweise nicht
		ListSelectionListener listSelectArtMenge = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
        		if(e.getValueIsAdjusting()) return;
        	        int row = artikelPanel.artikelListe.getSelectedRow();
        	        artMengPanel.setArtikelNummerTextfield((String) artikelPanel.artikelListe.getValueAt(row, 0));
        	        artMengPanel.setArtikelMengeTextfield((String) artikelPanel.artikelListe.getValueAt(row, 3));
	        }            
        };
        artikelPanel.addListSelectionListener(listSelectArtMenge);
		// Listener für Artikel loeschen
		ActionListener listenerDelArt = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
				frame.mitarbeiterPanel.tabbedPane.setSelectedIndex(0);
				frame.cardLayout.show(westPanel, "artDelPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerDelArt(listenerDelArt);
		// Listener für Artikel loeschen (okay button)
		ActionListener listenerArtDelOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					connection.loescheArtikel(artDelPanel.getArtikelNummer(), aktuellerBenutzer);
					artikelListe = connection.gibAlleArtikel();
					mitarbeiterPanel.updateArtikelListe(artikelListe);
					frame.cardLayout.show(westPanel, "mitarbeiterMenu");
					frame.pack();	
				} catch(ArtikelNichtVerfuegbarException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		};
		artDelPanel.addActionListenerOK(listenerArtDelOK);
		// Selection Listener für Artikel löschen Panel
		// TODO Funktioniert komischerweise nicht
		ListSelectionListener listSelectArtDel = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
	    		if(e.getValueIsAdjusting()) return;
	    	        int row = artikelPanel.artikelListe.getSelectedRow();
	    	        artDelPanel.setArtikelNummerTextfield((String) artikelPanel.artikelListe.getValueAt(row, 0));
	        	}            
	        };
        artikelPanel.addListSelectionListener(listSelectArtDel);
		// Listener für Mitarbeiter Registrieren
		ActionListener listenerMitReg = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {				
				frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
				frame.mitarbeiterPanel.tabbedPane.setSelectedIndex(1);
				frame.cardLayout.show(westPanel, "mitRegPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerMitReg(listenerMitReg);
		// Listener für Mitarbeiter Registrieren (okay button)
		ActionListener listenerMitRegOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					String name = mitRegPanel.getUserName();
					char[] pw1 = mitRegPanel.getPw1();
					char[] pw2 = mitRegPanel.getPw2();
					String anrede = mitRegPanel.getAnrede();
					String vorUndZuName = mitRegPanel.getName();
					if (Arrays.equals(pw1,pw2)) {
						connection.fuegeUserEin(name, pw1, anrede, vorUndZuName);
						userListe = connection.gibAlleUser();	
						mitarbeiterPanel.updateUserListe(userListe);
						frame.cardLayout.show(westPanel, "mitarbeiterMenu");
						frame.pack();
					} else {
						JOptionPane.showMessageDialog(null, "Passwort stimmt nicht überein."); 
					}
				} catch (InkorrekteRegWerteException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e); 
				}
			}
		};
		mitRegPanel.addActionListenerReg(listenerMitRegOK);
		// Listener für User loeschen
		ActionListener listenerUsrDel = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				frame.cardLayout.show(centerPanel, "mitarbeiterPanel");
				frame.mitarbeiterPanel.tabbedPane.setSelectedIndex(1);
				frame.cardLayout.show(westPanel, "usrDelPanel");
				frame.pack();
			}
		};
		mitarbeiterMenuPanel.addActionListenerUsrDel(listenerUsrDel);
		// Listener für User loeschen (okay button)
		ActionListener listenerUsrDelOK = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int userNr = usrDelPanel.getUserNummer();
				try {
					connection.loescheUser(userNr, aktuellerBenutzer);
					userListe = connection.gibAlleUser();	
					mitarbeiterPanel.updateUserListe(userListe);
					frame.cardLayout.show(westPanel, "mitarbeiterMenu");
					frame.pack();
				} catch (MitarbeiterNichtVorhandenException e) {
					JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
			}
		};
		usrDelPanel.addActionListenerOK(listenerUsrDelOK);
		// Selection Listener für User löschen Panel
		ListSelectionListener listSelectUsrDel = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
        		if(e.getValueIsAdjusting()) return;
        	        int row = mitarbeiterPanel.benutzerPanel.benutzerListe.getSelectedRow();
        	        usrDelPanel.setArtikelNummerTextfield((String) mitarbeiterPanel.benutzerPanel.benutzerListe.getValueAt(row, 0));
	        }            
        };
        mitarbeiterPanel.benutzerPanel.addListSelectionListener(listSelectUsrDel);
		// Listener für Artikelmengenverlauf anzeigen
		ActionListener listenerArtMengVer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Artikel a = artikelListe.get(0);
				List<Ereignis> artikelVerlauf = connection.gibEreignisseNachArtikelUndTagen(a);
				protokollPanel = new ArtikelProtokollPanel(artikelVerlauf,a.getName());
				protokollPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				centerPanel.add(protokollPanel, "protokollPanel");
				frame.cardLayout.show(centerPanel, "protokollPanel");
				frame.cardLayout.show(eastPanel, "protokollMenuPanel");
				frame.westPanel.setVisible(false);
				frame.eastPanel.setVisible(true);
			}
		};
		mitarbeiterMenuPanel.addActionListenerProtokoll(listenerArtMengVer);
		
	}
}
package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import de.hsb.fuhrpark.fahrzeuge.Kfz;
import de.hsb.fuhrpark.ui.gui.AddKfzComponent;

import shop.local.domain.ShopVerwaltung;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.User;


public class ShopGUI extends JFrame {
	
	ShopVerwaltung shopVer;
	User aktuellerBenutzer;
	
	// fuer die menue leiste
	private MenuItem menuItemQuit;
	
	// Graphische Auflistung der Kfz-Objekte in der Garage
	private JList<Artikel> artikellist;
	// Zusammengesetzte Komponente zum Hinzuf�gen eines Kfz
	// private AddKfzComponent addkfzcomp;
	// Textbereich f�r Lognachrichten
	private JTextArea logarea;	
	
	public ShopGUI() {
		super("SteinShop");
		shopVer = new ShopVerwaltung();
		aktuellerBenutzer = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// Fenstergr��e einstellen
		this.setSize(640, 480);
		
		// Initialisieren der Komponenten des Fensters
		this.initComponents();
		
		// Initialisieren der Listener f�r die interaktiven Komponenten
		// (Versehen von Buttons, Men�eintr�gen etc. mit "Leben")
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
	 * Methode, die in der Main am Anfang ausgef�hrt wird und das ganze Programm zum Laufen bringt.
	 * @throws IOException
	 */
	public void run() {
		try {
			shopVer.ladeDaten();
			//gibMenue(); wuerde das anfangsmenue liefern
			shopVer.speichereDaten();
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
		this.setLayout(new BorderLayout());
		
		
		
		
		
		// Linkes Unterfenster: Hier kommt eine Liste der Kfz-Objekte in der Garage hin
		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new BorderLayout());
		TitledBorder tborder = BorderFactory.createTitledBorder("Kfz in der Garage");
		leftpanel.setBorder(tborder);
		
		// Listen in Java Swing sind parametrisierte Objekte der Klasse JList
		kfzlist = new JList<Kfz>();
		
		// Wir verpacken die Liste in eine JScrollPane, die uns erm�glicht, zu scrollen (duh!)
		// Die JList geben wir der Scrollpane als Parameter mit
		JScrollPane leftscroll = new JScrollPane(kfzlist);
		leftscroll.setAutoscrolls(true);
		
		// JList-Objekte geh�ren zu den Swing-Komponenten, die strikt auf das Model-View-Controller-Konzept gepolt sind,
		// deshalb liegt der Datensatz, den eine JList anzeigen soll, in einer anderen Klasse gekapselt, dem "ListModel".
		// Das werden wir an dieser Stelle noch nicht f�llen, das kommt sp�ter!
		
		// Scrollpane mit JList zum linken Unterfenster hinzuf�gen
		leftpanel.add(leftscroll);
		
		// Hinzuf�gen zum Hauptfenster
		this.add(leftpanel);
		
		// Rechtes Unterfenster: Wird nochmals aufgeteilt in zwei Bereiche f�r eine selbstgebaute
		// Komponente, mit der Kfz hinzugef�gt werden k�nnen, und einen Textbereich �hnlich eines Logs
		JPanel rightpanel = new JPanel();
		rightpanel.setLayout(new BorderLayout());
		
		// Oben kommt unsere eigene Komponente hin
		addkfzcomp = new AddKfzComponent();
		rightpanel.add(addkfzcomp, BorderLayout.NORTH);
		
		// Darunter die Textarea
		logarea = new JTextArea();
		// Definiere Eigenschaften der Textarea: Nicht editierbar, deaktiviert, etc.
		TitledBorder logborder = BorderFactory.createTitledBorder("Log");
		logarea.setBorder(logborder);
		logarea.setEditable(false);
		logarea.setFocusable(false);
		logarea.setBackground(null);
		// Auto-scrolling, wenn neuer Text dazukommt
		DefaultCaret caret = (DefaultCaret) logarea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		// Wir kapseln auch die Textarea in eine JScrollPane
		JScrollPane rightscroll = new JScrollPane(logarea);
		rightpanel.add(rightscroll, BorderLayout.CENTER);
		
		// Hinzuf�gen zum Hauptfenster
		this.add(rightpanel);
	}
}

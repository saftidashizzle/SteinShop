package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import valueobjects.Kunde;
import valueobjects.User;
import domain.ShopVerwaltung;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNichtVerfuegbarException;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
/**
 * ShopUi: Klasse die das Konsolen Interface für den Shop bereitstellt.
 * 
 * @author philipp, kevin, jan
 *
 */

public class ShopCUI {
	private ShopVerwaltung shopVer;
	private	User aktuellerBenutzer;
	private String eingabe;
	private BufferedReader in;


	public ShopCUI() { // Konstruktor
		shopVer = new ShopVerwaltung();
		aktuellerBenutzer = null;
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		ShopCUI shop = new ShopCUI();
//		shop.shopVer.fuegeArtikelEin("EINSTEIN", 1.99, null, 12);
//		shop.shopVer.fuegeArtikelEin("SECHSSTEIN", 9.99, null, 48, 6);
		
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
//			char[] pw = {'1','2','3'};
//			shopVer.fuegeUserEin("Kunde", pw, "Herr", "Axel Schweiss","Elbenweg 3", 13337, "Bruchtal", "Mittelerde");
//			shopVer.fuegeUserEin("Mitarbeiter", pw, "Herr", "Voll iDiot");
			gibMenue();
			shopVer.speichereDaten();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Methode die Unterscheidet ob man eingeloggt ist oder nicht und dementsprechend das entsprechende Menue darstellt
	 * @throws IOException
	 */
	private void gibMenue() throws IOException {
		do{
			if(!(aktuellerBenutzer == null)){
				eingeloggt();
			} else {
				menueNichtEingeloggt();
			}
		} while (!eingabe.equals("q"));
	}
	/**
	 * Methode die das Menue fuer nicht angemeldete User zeigt.
	 * @throws IOException
	 */
	private void menueNichtEingeloggt() throws IOException {
		System.out.println("e) Einloggen\n" +
				"r) Registriere Kunden Account\n" +
				"q) Beenden");
		eingabe = liesEingabe();
		if(eingabe.equals("e")){
			System.out.println("Dein Benutzername?");
			String name = liesEingabe();
			System.out.println("Dein Passwort?");	
			String passwort = liesEingabe();
			try {
				aktuellerBenutzer = userLogin(name, passwort.toCharArray());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		} else if (eingabe.equals("r")) {
			try {
				userRegistrieren();
			} catch (Exception e) {
				System.out.println(e);
			}					
		}
	}
	/**
	 * Methode die, die Willkommensnachricht ausgibt und und je nachdem ob der angemeldete Benutzer
	 * Kunde oder Mitarbeiter ist, entscheidet welches Menue sie anzeigt.
	 * @throws IOException
	 */
	private void eingeloggt() throws IOException {
		System.out.println("Herzlich Willkommen im Stein-Shop\n" +
		"wir wünschen einen angenehmen Aufenthalt\n" +
		"und ein steinhartes Kauferlebnis.\n");
		do {
			System.out.println("Artikelliste:");
			gibArtikellisteAus(); 
			if(!(aktuellerBenutzer instanceof Kunde)) {
				menueMitarbeiter();
			} else {
				menueKunde();
			}
		} while (!eingabe.equals("a"));
		aktuellerBenutzer = null;
	}
	/** Methode mit der sich ein neuer Kunde registrieren kann
	 * 
	 * @throws InkorrekteRegWerteException
	 * @throws IOException
	 */

	public void userRegistrieren() throws InkorrekteRegWerteException, IOException {
		System.out.println("Waehle deinen Benutzernamen:");
		String name = liesEingabe();
		String passwort;
		String passwort1;
		do {
			System.out.println("Waehle dein Passwort:");
			passwort = liesEingabe();
			System.out.println("Eingabe Wiederholen:");
			passwort1 = liesEingabe();
		} while (!passwort.equals(passwort1));
		System.out.println("Anrede:");
		String anrede = liesEingabe();
		System.out.println("Vor- und Zu-Name:");
		String vorUndZuName = liesEingabe();
		System.out.println("Straße und Hausnr:");
		String strasse = liesEingabe();
		System.out.println("Postleitzahl");
		int plz = Integer.parseInt(liesEingabe());
		System.out.println("Ort:");
		String ort = liesEingabe();
		System.out.println("Land:");
		String land = liesEingabe();
		this.shopVer.fuegeUserEin(name, passwort.toCharArray(), anrede, vorUndZuName, strasse, plz, ort, land);
	}
	/**
	 * Methode die, alle Elemente der Artikelliste (siehe artikel.toString()) in der Konsole ausgibt.
	 * @param liste
	 */
	private void gibArtikellisteAus() {
		shopVer.gibArtikellisteAus();
	}
	/** Methode die den eingeloggten Benutzer zurück gibt. Es wird die vorher abgerufene Userliste, sowie ein String Name
	 * und ein String Passwort übergeben.
	 * 
	 * @param liste Ausgelesene Liste aller Benutzer
	 * @param name Benutzername der Person, die sich einloggen möchte
	 * @param passwort Passwort der Person, die sich einloggen möchte
	 * @return gibt das Benutzerobjekt zurück, wenn der Login geklappt hat, ansonsten null
	 */
	private User userLogin(String name, char[] passwort) throws LoginFehlgeschlagenException{
		return shopVer.userLogin(name, passwort);
	}
	/**
	 * Methode, die ein Mitarbeiter-spezifisches Menue ausgibt.
	 * @throws IOException
	 */
	public void menueMitarbeiter() throws IOException {
		System.out.println("n) neuen Artikel anlegen \n" +
				"m) Artikelmenge aendern\n" +
				"l) Artikel löschen\n" +
				"u) Alle Benutzer anzeigen\n" +
				"r) Neuen Mitarbeiter registrieren\n" +
				"d) Mitarbeiter löschen\n" + 
				"p) Protokoll anzeigen\n" +
				"c) Artikelmengenverlauf der letzten 30 Tage anzeigen lassen\n" + 
				"a) Ausloggen");
		eingabe = liesEingabe();
		switch(eingabe) {
			case "n": 	
				neuenArtikelAnlegen();
				break;
			case "m":
				artikelmengeAendern();
				break;
			case "l":
				artikelLoeschen();
				break;
			case "u":
				shopVer.gibBenutzerlisteAus();
				break;
			case "r": 
				mitarbeiterErstellen();
				break;
			case "d":
				benutzerLoeschen();
				break;
			case "p":
				shopVer.gibProtokoll();
				break;
			case "c":
				System.out.println("Welchen Artikel?");
				int artID = Integer.parseInt(liesEingabe());
				try {
					shopVer.einkaufsVerlauf(artID);
				} catch (ArtikelNichtVerfuegbarException e) {
					System.out.println(e);
				}
				break;
			case "a":
				System.out.println("Auf Wiedersehen!");
				break;
			default: System.out.println("Falsche Eingabe.");
		}
	}
	/** Methode um einen Artikel zu löschen
	 * 
	 * @throws IOException
	 */
	private void artikelLoeschen() throws IOException {
		System.out.println("Welchen Artikel willst du löschen?");
		int artID = Integer.parseInt(liesEingabe());
		try{
			shopVer.loescheArtikel(artID, aktuellerBenutzer);
		}
		catch(Exception e) {
			System.out.println(e);				
		}
	}

	/** Methode um einen User zu löschen
	 * 
	 * @throws IOException
	 */
	private void benutzerLoeschen() throws IOException {
		System.out.println("Welchen Mitarbeiter willst du löschen?");
		int userNr = Integer.parseInt(liesEingabe());
		try{
			shopVer.loescheUser(userNr, aktuellerBenutzer);
		}
		catch(Exception e) {
			System.out.println(e);				
		}
	}

	private void mitarbeiterErstellen() throws IOException {
		System.out.println("Waehle deinen Benutzernamen:");
		String benutzername = liesEingabe();
		String passwort;
		String passwort1;
		do {
			System.out.println("Waehle dein Passwort:");
			passwort = liesEingabe();
			System.out.println("Eingabe Wiederholen:");
			passwort1 = liesEingabe();
		} while (!passwort.equals(passwort1));
		System.out.println("Anrede:");
		String anrede = liesEingabe();
		System.out.println("Vor- und Zu-Name:");
		String vorUndZuName = liesEingabe();
		try{
			this.shopVer.fuegeUserEin(benutzername, passwort.toCharArray(), anrede, vorUndZuName);
		}
		catch(Exception e) {
			System.out.println(e);				
		}
	}
	private void artikelmengeAendern() throws IOException {
		System.out.println("Artikelliste:");
		gibArtikellisteAus();
		System.out.println("Artikelnummer des zu ändernden Artikel eingeben.");
		eingabe = liesEingabe();
		int nummer = Integer.parseInt(eingabe);
		System.out.println("Wieviele moechtest du hinzufügen?");
		eingabe = liesEingabe();
		int anzahl = Integer.parseInt(eingabe);
		try{
			shopVer.mengeAendern(nummer, anzahl, aktuellerBenutzer);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Artikel wurden hinzugefügt!");
	}
	private void neuenArtikelAnlegen() throws IOException {
		System.out.println("Moechtes du einen Mehrfachartikel speichern? (j für ja und n für nein)");
		String mehrfach = liesEingabe();
		int p = 0;
		System.out.println("Name des Artikels: ");
		String name = liesEingabe();
		System.out.println("Menge: ");
		eingabe = liesEingabe();
		int menge = Integer.parseInt(eingabe);
		System.out.println("Preis: ");
		eingabe = liesEingabe();
		double preis = Double.parseDouble(eingabe);
		try {		
			if (mehrfach.equals("j")) {
				System.out.println("Bitte gib die Portionsgröße ein.");
				String portion = liesEingabe();
				p = Integer.parseInt(portion);
				shopVer.fuegeArtikelEin(name, preis, aktuellerBenutzer, menge, p); // mehrfachartikel
			} else if (mehrfach.equals("n")) {
				shopVer.fuegeArtikelEin(name, preis, aktuellerBenutzer, menge);
	
			} else {
				throw new IOException("Bitte entscheide dich für ja oder nein.");
			}
			System.out.println("wird angelegt!");			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * Methode, die ein Kunden-spezifisches Menue ausgibt.
	 * @throws IOException
	 */
	public void menueKunde() throws IOException{
		System.out.println("w) Zum Warenkorb\n" +
				"m) Artikelmenge im Warenkorb ändern\n" +
				//"b) Artikelbeschreibung aufrufen\n" +
				"n) Artikel nach Namen ordnen\n" +
				"f) Artikel nach Nummern ordnen\n" +
				"c) Artikel in Warenkorb legen\n" +
				"d) Artikel aus Warenkorb\n" +
				"e) Warenkorb leeren\n" +
				"k) Zur Kasse\n" +
				"a) Ausloggen");
		
			eingabe = liesEingabe();
		switch(eingabe) {
			case "w": 
				shopVer.getWarenkorbInhalt(aktuellerBenutzer);
				//gibArtikellisteAus();
				break;
			case "m": 
				System.out.println("Von welchem Artikel möchtest du die Menge ändern?");
				eingabe = liesEingabe();
				int artID = Integer.parseInt(eingabe);
				System.out.println("Wieviel möchtest du hinzufügen oder abziehen?");
				eingabe = liesEingabe();
				int menge = Integer.parseInt(eingabe);
				try {
					shopVer.artikelMengeImWarenkorbAendern(artID, menge, (Kunde)aktuellerBenutzer);
				} catch (Exception e) {
					System.out.println(e);
				}
				
				//gibArtikellisteAus();
				break;
//			case "b": 
//				System.out.println("Welchen Artikel?");
//				eingabe = liesEingabe();
//				int artID = Integer.parseInt(eingabe);
//				artikelBeschreibung(artID);
//				break;
			case "n": shopVer.artikelNachNamenOrdnen();
					break;
			case "f": shopVer.artikelNachZahlenOrdnen();
					break;
			case "c": 
				System.out.println("Welchen Artikel kaufen?");
				eingabe = liesEingabe();
				artID = Integer.parseInt(eingabe);
				System.out.println("Wieviele davon?");
				eingabe = liesEingabe();
				menge = Integer.parseInt(eingabe);
				try {
					shopVer.artikelInWarenkorb(artID, menge,(Kunde) aktuellerBenutzer);			
				} catch (ArtikelMengeReichtNichtException | ArtikelNichtVerfuegbarException | WarenkorbExceedsArtikelbestandException e) {
					System.out.println(e);
				}
				break;
			case "d": 
				System.out.println("Welchen Artikel entfernen?");
				eingabe = liesEingabe();
				artID = Integer.parseInt(eingabe);
				try {
					shopVer.artikelAusWarenkorb(artID, (Kunde)aktuellerBenutzer);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case "e":
				try {
					shopVer.warenkorbLeeren((Kunde)aktuellerBenutzer);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case "k": zurKasse();
				break;
			case "p":
				shopVer.gibProtokoll();
				break;
			case "a":
				System.out.println("Auf Wiedersehen!");
				break;
			default: System.out.println("Falsche Eingabe.");
		}
		
	}
	/** Methode, die die Rechnung ausgibt. 
	 * 
	 */
	public void zurKasse(){
		try {
			shopVer.rechnungErstellen((Kunde)aktuellerBenutzer);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/** Methode die eine Eingabe einliest. Ersetzt in.readLine();
	 * 
	 * @return Gibt in.readLine(); zurück.
	 * @throws IOException
	 */
	private String liesEingabe() throws IOException {
		
		return in.readLine();
	}
}

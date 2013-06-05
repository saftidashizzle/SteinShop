package shop.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.ShopVerwaltung;
import shop.local.domain.exceptions.InkorrekteRegWerteException;
import shop.local.domain.exceptions.LoginFehlgeschlagenException;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.User;
/**
 * ShopUi: Klasse die das Konsolen Interface f�r den Shop bereitstellt.
 * 
 * @author philipp, kevin, jan
 *
 */

public class ShopUi {
	private ShopVerwaltung shopVer;
	private	User aktuellerBenutzer;
	private String eingabe;
	private BufferedReader in;


	public ShopUi() { // Konstruktor
		shopVer = new ShopVerwaltung();
		aktuellerBenutzer = null;
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		ShopUi shop = new ShopUi();
//		shop.shopVer.fuegeArtikelEin("EINSTEIN", 1.99, null, 12);
//		shop.shopVer.fuegeArtikelEin("ZWEISTEIN", 2.99, null, 1);
//		shop.shopVer.fuegeArtikelEin("DREISTEIN", 3.99, null, 1);
//		shop.shopVer.fuegeArtikelEin("SECHSSTEIN", 9.99, null, 48, 6);

		
//		shop.shopVer.fuegeUserEin("Kunde", "123", "Herr", "Axel Schweiss","Elbenweg 3", 1337, "Bruchtal", "Mittelerde");
//		shop.shopVer.fuegeUserEin("Mitarbeiter", "123", "Herr", "Voll iDiot");
//		shop.shopVer.fuegeUserEin("Rupert", "123", "Herr", "Rupert Tunnichtgut", "Haufenweg 2", 7353, "Hodenhausen", "DA WO ES STINKT");		
		
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
	public void run() throws IOException {
		try {
			shopVer.ladeDaten();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		List<User> UserListe = shopVer.gibAlleUser();
		do{
			if(!(aktuellerBenutzer == null)){
				System.out.println("Herzlich Willkommen im Stein-Shop\n" +
				"wir w�nschen einen angenehmen Aufenthalt\n" +
				"und ein steinhartes Kauferlebnis.\n");
				do {
					System.out.println("Artikelliste:");
					gibArtikellisteAus();
					if(aktuellerBenutzer.getAdresse()==null) {
						menueMitarbeiter();
					} else {
						menueKunde();
					}
		
				} while (!eingabe.equals("a"));
				aktuellerBenutzer = null;
			} else {
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
						aktuellerBenutzer = userLogin(UserListe, name, passwort);
					} catch (Exception e) {
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
		} while (!eingabe.equals("q"));
		try {
			shopVer.speichereDaten();
		} catch(Exception e) {
			System.out.println("Fehler beim speichern der Daten.");
			e.printStackTrace();
		}
	}
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
		System.out.println("Stra�e und Hausnr:");
		String strasse = liesEingabe();
		System.out.println("Postleitzahl");
		int plz = Integer.parseInt(liesEingabe());
		System.out.println("Ort:");
		String ort = liesEingabe();
		System.out.println("Land:");
		String land = liesEingabe();
		this.shopVer.fuegeUserEin(name, passwort, anrede, vorUndZuName, strasse, plz, ort, land);
	}
	
	/**
	 * Methode die, alle Elemente der Artikelliste (siehe artikel.toString()) in der Konsole ausgibt.
	 * @param liste
	 */
	private void gibArtikellisteAus() {
		shopVer.gibArtikellisteAus();
	}
	/** Methode die den eingeloggten Benutzer zur�ck gibt. Es wird die vorher abgerufene Userliste, sowie ein String Name
	 * und ein String Passwort �bergeben.
	 * 
	 * @param liste Ausgelesene Liste aller Benutzer
	 * @param name Benutzername der Person, die sich einloggen m�chte
	 * @param passwort Passwort der Person, die sich einloggen m�chte
	 * @return gibt das Benutzerobjekt zur�ck, wenn der Login geklappt hat, ansonsten null
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
	
	/**
	 * Methode, die ein Mitarbeiter-spezifisches Menue ausgibt.
	 * @throws IOException
	 */
	public void menueMitarbeiter() throws IOException {
		System.out.println("n) neuen Artikel anlegen \n" +
				"m) Artikelmenge aendern\n" +
				"u) Alle Benutzer anzeigen\n" +
				"r) Neuen Mitarbeiter registrieren\n" +
				"d) Mitarbeiter l�schen\n" + 
				"p) Protokoll anzeigen\n" + 
				"a) Ausloggen");
		eingabe = liesEingabe();
		switch(eingabe) {
			case "n": 	
				System.out.println("Moechtes du einen Mehrfachartikel speichern? (y f�r ja und n f�r nein");
				String mehrfach = liesEingabe();
				int p = 0;
				if (mehrfach.equals("y")) {
					System.out.println("Bitte gib die Portionsgr��e ein.");
					String portion = liesEingabe();
					p = Integer.parseInt(portion);
				}
				System.out.println("Name des Artikels: ");
				String name = liesEingabe();
				System.out.println("Menge: ");
				eingabe = liesEingabe();
				int menge = Integer.parseInt(eingabe);
				System.out.println("Preis: ");
				eingabe = liesEingabe();
				double preis = Double.parseDouble(eingabe);
				System.out.println("wird angelegt!");
				try {					
					if (p!=0) {
						shopVer.fuegeArtikelEin(name, preis, aktuellerBenutzer, menge, p); // mehrfachartikel
					} else if (mehrfach.equals("n")) {
						shopVer.fuegeArtikelEin(name, preis, aktuellerBenutzer, menge);
					} 
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			case "m":
				System.out.println("Artikelliste:");
				gibArtikellisteAus();
				System.out.println("Artikelnummer des zu �ndernden Artikel eingeben.");
				eingabe = liesEingabe();
				int nummer = Integer.parseInt(eingabe);
				System.out.println("Wieviele moechtest du hinzuf�gen?");
				eingabe = liesEingabe();
				int anzahl = Integer.parseInt(eingabe);
				try{
					shopVer.mengeAendern(nummer, anzahl, aktuellerBenutzer);
				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("Artikel wurden hinzugef�gt!");
				break;
			case "u":
				shopVer.gibBenutzerlisteAus();
				break;
			case "r": 
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
					this.shopVer.fuegeUserEin(benutzername, passwort, anrede, vorUndZuName);
				}
				catch(Exception e) {
					System.out.println(e);				
				}
				break;
			case "d":
				System.out.println("Welchen Mitarbeiter willst du l�schen?");
				int userName = Integer.parseInt(liesEingabe());
				try{
					shopVer.loescheUser(userName, aktuellerBenutzer);
				}
				catch(Exception e) {
					System.out.println(e);				
				}
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
	/**
	 * Methode, die ein Kunden-spezifisches Menue ausgibt.
	 * @throws IOException
	 */

	public void menueKunde() throws IOException{
		System.out.println("w) Zum Warenkorb\n" +
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
				int artID = Integer.parseInt(eingabe);
				System.out.println("Wieviele davon?");
				eingabe = liesEingabe();
				int menge = Integer.parseInt(eingabe);
				try {
					shopVer.artikelInWarenkorb(artID, menge, aktuellerBenutzer);			
				} catch (Exception e) {
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
	 * @return Gibt in.readLine(); zur�ck.
	 * @throws IOException
	 */
	private String liesEingabe() throws IOException {
		
		return in.readLine();
	}
}

package shop.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.ShopVerwaltung;
import shop.local.valueobjects.Artikel;
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
		shop.shopVer.fuegeArtikelEin("EINSTEIN", 1, 42);
		shop.shopVer.fuegeArtikelEin("ZWEISTEIN", 2, 11);
		shop.shopVer.fuegeArtikelEin("DREISTEIN", 3, 1);
		
		shop.shopVer.fuegeUserEin("Kunde", "123", false);
		shop.shopVer.fuegeUserEin("Mitarbeiter", "123", true);
		shop.shopVer.fuegeUserEin("Rupert", "123");

		
		
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
		List<Artikel> ArtikelListe = shopVer.gibAlleArtikel();
		List<User> UserListe = shopVer.gibAlleUser();
		do{
			if(!(aktuellerBenutzer == null)){
				System.out.println("Herzlich Willkommen im Stein-Shop\n" +
				"wir w�nschen einen angenehmen Aufenthalt\n" +
				"und ein steinhartes Kauferlebnis.\n");
				do {
					System.out.println("Artikelliste:");
					gibArtikellisteAus(ArtikelListe);
					if(aktuellerBenutzer.getRang()) {
						menueMitarbeiter();
					} else {
						menueKunde();
					}
		
				} while (!eingabe.equals("q"));
			}
			else{
				System.out.println("zum einloggen e) eingeben, zum beenden q)");
				eingabe = liesEingabe();
				if(eingabe.equals("e")){
					System.out.println("Dein Benutzername?");
					String name = liesEingabe();
					System.out.println("Dein Passwort?");	
					String passwort = liesEingabe();
					// System.out.println("User: " + name + "PW: " + passwort);
					aktuellerBenutzer = userLogin(UserListe, name, passwort);
					// eingeloggt=userLogin(UserListe, name, passwort);
					
				}			
			}
		} while (!eingabe.equals("q"));		
	}
	
	
	/**
	 * Methode die, alle Elemente der Artikelliste (siehe artikel.toString()) in der Konsole ausgibt.
	 * @param liste
	 */
	private void gibArtikellisteAus(List<Artikel> liste) {
		if(liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<Artikel> it = liste.iterator();
			while (it.hasNext()) {
				Artikel artikel = it.next();
				System.out.println(artikel.toString());
			}			
		}
		System.out.println(" ");
	}
	/** Methode die den eingeloggten Benutzer zur�ck gibt. Es wird die vorher abgerufene Userliste, sowie ein String Name
	 * und ein String Passwort �bergeben.
	 * 
	 * @param liste Ausgelesene Liste aller Benutzer
	 * @param name Benutzername der Person, die sich einloggen m�chte
	 * @param passwort Passwort der Person, die sich einloggen m�chte
	 * @return gibt das Benutzerobjekt zur�ck, wenn der Login geklappt hat, ansonsten null
	 */
	
	private User userLogin(List<User> liste, String name, String passwort) {
		Iterator<User> it = liste.iterator();
		while  (it.hasNext()) {
			User user = it.next();
			if(user.getName().equals(name) && (user.getPasswort().equals(passwort))){
				return user;
			}
		}
		System.out.println("hat nicht geklappt");
		return null;
		// hier throw new PersonExistiertNichtException anstatt return null
	}
	
	/**
	 * Methode, die ein Mitarbeiter-spezifisches Menue ausgibt.
	 * @throws IOException
	 */
	public void menueMitarbeiter() throws IOException {
		System.out.println("n) neuen Artikel anlegen \n" +
				"m) Artikelmenge aendern\n" +
				"q) Beenden");
		eingabe = liesEingabe();
		switch(eingabe) {
			case "n": 	
				System.out.println("Name des Artikels: ");
				String name = liesEingabe();
				System.out.println("Nummer: ");
				eingabe = liesEingabe();
				int nr = Integer.parseInt(eingabe);
				System.out.println("Menge: ");
				eingabe = liesEingabe();
				int menge = Integer.parseInt(eingabe);
				System.out.println("wird angelegt!");
				shopVer.fuegeArtikelEin(name, nr, menge);
				break;
			case "m":
				System.out.println("Artikelnummer des zu �ndernden Artikel eingeben.");
				eingabe = liesEingabe();
				int nummer = Integer.parseInt(eingabe);
				System.out.println("Wieviele moechtest du hinzuf�gen?");
				eingabe = liesEingabe();
				int anzahl = Integer.parseInt(eingabe);
				shopVer.mengeAendern(nummer, anzahl);
				break;
			default: System.out.println("Falsche Eingabe.");
		}
//		if (eingabe.equals("n")) {	
//			System.out.println("Name des Artikels: ");
//			String name = liesEingabe();
//			System.out.println("Nummer: ");
//			eingabe = liesEingabe();
//			int nr = Integer.parseInt(eingabe);
//			System.out.println("Menge: ");
//			eingabe = liesEingabe();
//			int menge = Integer.parseInt(eingabe);
//			System.out.println("wird angelegt!");
//			shopVer.fuegeArtikelEin(name, nr, menge);
//			
//		} 		
	}
	/**
	 * Methode, die ein Kunden-spezifisches Menue ausgibt.
	 * @throws IOException
	 */

	public void menueKunde() throws IOException{
		System.out.println("w) Zum Warenkorb\n" +
				"b) Artikelbeschreibung aufrufen\n" +
				"c) Artikel Kaufen\n" +
				"k) Zur Kasse\n" +
				"q) Beenden");
		
			eingabe = liesEingabe();
		switch(eingabe) {
			case "w": zumWarenkorb();
				List<Artikel> Warenkorb = shopVer.gibWarenkorb();
				gibArtikellisteAus(Warenkorb);
				break;
			case "b": 
				System.out.println("Welchen Artikel?");
				eingabe = liesEingabe();
				int artID = Integer.parseInt(eingabe);
				artikelBeschreibung(artID);
				break;
			case "c": 
				System.out.println("Welchen Artikel kaufen?");
				eingabe = liesEingabe();
				artID = Integer.parseInt(eingabe);
				System.out.println("Wieviele davon?");
				eingabe = liesEingabe();
				int menge = Integer.parseInt(eingabe);
				shopVer.artikelInWarenkorb(artID, menge);
				break;
			case "k": zurKasse();
				break;
			case "q": break;
				default: System.out.println("Falsche Eingabe.");
		}
		
	}
	/** Methode, die den Inhalt des aktuellen Warenkorb ausgibt. 
	 * 
	 */
	public void zumWarenkorb(){
		System.out.println("Warenkorb:");
	}
	/** Methode, die zu einer bestimmten ArtikelID die passende Artikelbeschreibung ausgibt.
	 * 
	 * @param artID
	 */
	
	public void artikelBeschreibung(int artID){
		
	}

	/** Methode, die die Rechnung ausgibt. 
	 * 
	 */
	public void zurKasse(){
		System.out.println("Kasse: ");
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

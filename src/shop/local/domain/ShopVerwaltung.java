package shop.local.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.ArtikelMengeReichtNichtException;
import shop.local.domain.exceptions.ArtikelNichtVerfuegbarException;
import shop.local.domain.exceptions.WarenkorbExceedsArtikelbestandException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.MehrfachArtikel;
import shop.local.valueobjects.Rechnung;
import shop.local.valueobjects.User;
import shop.local.valueobjects.Warenkorb;



public class ShopVerwaltung {

	private ArtikelVerwaltung artVer;
	private UserVerwaltung userVer;
	private WarenkorbVerwaltung warkoVer;
	public EreignisVerwaltung erVer;
	int jahrestag = 0;
	
	
	public ShopVerwaltung() {
		artVer = new ArtikelVerwaltung();
		userVer = new UserVerwaltung();
		warkoVer = new WarenkorbVerwaltung();
		erVer = new EreignisVerwaltung();
	}
	/**
	 * Methode die einen neuen Artikel einfügt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzufügenden Artikels.
	 * @param d Preis des einzufügenden Artikels.
	 * @param akteur User der die Aktion durchgeführt hat.
	 * @param menge Menge des einzufügenden Artikels
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge)  { // hier fehlt ArtikelExistiertBereitsException
		try {
			Artikel a = artVer.einfuegen(titel, d, menge);
			erVer.ereignisEinfuegen(akteur, jahrestag, a, a.getMenge(), "Neuer Artikel erstellt.");
		} catch(Exception e) {
			
		}	
	}
	/**
	 * Methode die einen neuen MehrfachArtikel einfügt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzufügenden Artikels.
	 * @param d Preis des einzufügenden Artikels.
	 * @param akteur User der die Aktion durchgeführt hat.
	 * @param menge Menge des einzufügenden Artikels
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge, int packungsGroesse)  { // hier fehlt ArtikelExistiertBereitsException
		try {
			MehrfachArtikel a = artVer.einfuegen(titel, d, menge, packungsGroesse);
			erVer.ereignisEinfuegen(akteur, jahrestag, a, a.getMenge(), "Neuer Mehrfach-Artikel erstellt.");
		} catch(Exception e) {
			
		}	
	}
	/**
	 * Methode um einen User einzufügen
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName)  { // hier fehlt ArtikelExistiertBereitsException
		userVer.einfuegen(name, passwort, anrede, vorUndZuName);	
	}
	/**
	 * Methode um einen User einzufügen.
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land)  { // hier fehlt ArtikelExistiertBereitsException
		userVer.einfuegen(name, passwort, anrede, vorUndZuName, strasse, plz, ort, land);	
	}
	/**
	 * Methode die einen neuen Artikel in der Warenkorb ein und den Auftrag an die Warenkorbverwaltung weiterreicht.
	 * @param menge 
	 * @param titel Titel des einzufügenden Artikels.
	 * @param nummer Nummer des einzufügenden Artikels.
	 * @param menge Menge des einzufügenden Artikels
	 * @throws ArtikelNummerFalsch 
	 */
	public void artikelInWarenkorb(int artID, int menge, User akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		// überprüfe: sind schon mehr in warenkorb als im bestand?
		try {		
			warkoVer.artikelInWarenkorb(a, menge, akteur);
		} catch(Exception e) {
			e.printStackTrace();
		}	
		erVer.ereignisEinfuegen(akteur, jahrestag, a, menge, "in den Warenkorb getan."); // hier liegt der fehler
	}
	/**
	 * Methode die einen artikel einliest und an die warenkorb verwaltung durchreicht
	 * @param artID Artikel ID des zu entfernenden Artikels
	 */

	public void artikelAusWarenkorb(int artID, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		warkoVer.artikelAusWarenkorb(a, akteur);
		erVer.ereignisEinfuegen(akteur, jahrestag, a, a.getMenge(), "Artikel " + a.getName() + " aus dem Warenkorb entfernt.");
	}
	/**
	 * Methode die, die Warenkorb Liste löscht.
	 * @return Die Artikelliste.
	 */

	public void warenkorbLeeren(Kunde akteur){
		
		akteur.getWarenkorb().leeren();
		
	}
	/** 
	 * Methode um den Warenkorb einzukaufen.
	 * @param User aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException 
	 */
	public void rechnungErstellen(Kunde akteur) throws ArtikelNichtVerfuegbarException{
		// key == Artikel
		HashMap<Artikel, Integer> warenkorb = akteur.getWarenkorb().getInhalt();
		if(warenkorb.isEmpty()){
			System.out.println("Warenkorb ist leer.");
		} else {
			// Artikelmenge im Artikelbestand verringern
			for(Artikel key : warenkorb.keySet()) {
				// System.out.println("Artikel: " + key.getName() + "Zahl: " + warenkorb.get(key));
				artVer.setArtikelMenge(key.getNummer(), (~warenkorb.get(key))+1);
				erVer.ereignisEinfuegen(akteur, jahrestag, key, warenkorb.get(key), "Artikel gekauft. (Rechnung wurde erstellt)");
		    }
		}		
		Rechnung rechnung = new Rechnung(akteur, akteur.getWarenkorb(), 0);
		warenkorbLeeren(akteur);
	}
	
	/** 
	 * Methode um die Artikelliste zurückzugeben.
	 * @return Die Artikelliste.
	 */
	public List<Artikel> gibAlleArtikel(){
		return artVer.getArtikelBestand();
	}
	/**
	 * Methode um die Benutzerliste zurückzugeben.
	 * @return Die Benutzerliste.
	 */
	public List<User> gibAlleUser(){
		return userVer.getUserBestand();
	}
	
	/**
	 * Methode um den Warenkorb Inhaltzurückzugeben.
	 * @return Die Benutzerliste.
	 */
	public Warenkorb gibWarenkorb(Kunde user){
		return user.getWarenkorb();
	}	
	/**
	 * Methode die das Protokoll ausgiebt
	 * @return
	 */
	public void gibProtokoll() {
		erVer.gibProtokollAus();
	}
	/**
	 * Methode die einer Liste ändert
	 * @param nummer
	 * @param anzahl
	 * @param akteur
	 */
	public void mengeAendern(int nummer, int anzahl, User akteur) {
		artVer.setArtikelMenge(nummer, anzahl);
		
		// hier wird nach dem artikelmenge aendern, mit der nummer das artikel objekt herausgesucht
		List<Artikel> liste2 = artVer.getArtikelBestand();
		Iterator<Artikel> it = liste2.iterator();
		Artikel derWars = null;
		while  (it.hasNext()) {
			Artikel artikel = it.next();
			if(artikel.getNummer() == nummer){
				derWars = artikel;
			}
		}		
		// user  heraussuchen, jahrestag bestimmen
		// artikel heraussuchen und anzahl bestimmen
		// ich muss hier in die klasse die artikelliste bekommen aus der artikelverwaltung und
		// es muss auch übergeben werden, wer die änderung vollzogen hat, am besten gleich als objekt
		// am besten auch den artikel gleich als objekt übergeben
		
		// aus nummer und anzahl muss ich den rest herausfinden
		erVer.ereignisEinfuegen(akteur, jahrestag, derWars, anzahl, "Bestandsanzahl geändert.");
	}
	/**
	 * Methode die, die Artikelliste Nach Namen ordnet
	 */
	public void artikelNachNamenOrdnen() {
		Collections.sort(gibAlleArtikel(), new comperatorArtikelName()); 
	}
	/**
	 * Methode die, die Artikelliste Nach Zahlen ordnet
	 */
	public void artikelNachZahlenOrdnen() { 
		Collections.sort(gibAlleArtikel(), new comperatorArtikelNummer());
	}
	/**
	 * Methode die einen User löscht
	 * @param userName
	 * @param aktuellerBenutzer
	 */
	public void loescheUser(int userName, User aktuellerBenutzer) {
		userVer.loescheUser(userName, aktuellerBenutzer);
	}
	/**
	 * Methode die, die Artikelliste ausgiebt
	 */
	public void gibArtikellisteAus() {
		artVer.gibArtikellisteAus();		
	}
	public void gibBenutzerlisteAus() {
		userVer.gibBenutzerlisteAus();
	}
	/**
	 * Methode die den WarenkorbInhalt vom User ausgeben soll
	 * @param user
	 */
	public void getWarenkorbInhalt(User user){
		warkoVer.getWarenkorbInhalt(user);
	}
}

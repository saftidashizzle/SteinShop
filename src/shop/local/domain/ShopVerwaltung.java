package shop.local.domain;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.ArtikelAngabenInkorrektException;
import shop.local.domain.exceptions.ArtikelMengeInkorrektException;
import shop.local.domain.exceptions.ArtikelMengeReichtNichtException;
import shop.local.domain.exceptions.ArtikelNichtVerfuegbarException;
import shop.local.domain.exceptions.InkorrekteRegWerteException;
import shop.local.domain.exceptions.MitarbeiterNichtVorhandenException;
import shop.local.domain.exceptions.WarenkorbExceedsArtikelbestandException;
import shop.local.domain.exceptions.WarenkorbIstLeerException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
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
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge)  throws ArtikelAngabenInkorrektException{ // hier fehlt ArtikelExistiertBereitsException
			Artikel a = artVer.einfuegen(titel, d, menge);
			erVer.ereignisEinfuegen(akteur, a, a.getMenge(), "Neuer Artikel erstellt.");
	}
	/**
	 * Methode die einen neuen MehrfachArtikel einfügt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzufügenden Artikels.
	 * @param d Preis des einzufügenden Artikels.
	 * @param akteur User der die Aktion durchgeführt hat.
	 * @param menge Menge des einzufügenden Artikels
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
			MehrfachArtikel a = artVer.einfuegen(titel, d, menge, packungsGroesse);
			erVer.ereignisEinfuegen(akteur, a, a.getMenge(), "Neuer Mehrfach-Artikel erstellt.");
	}
	/**
	 * Methode um einen User einzufügen
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName)  throws InkorrekteRegWerteException{
		userVer.einfuegen(name, passwort, anrede, vorUndZuName);	
	}
	/**
	 * Methode um einen User einzufügen.
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) throws InkorrekteRegWerteException { 
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
		erVer.ereignisEinfuegen(akteur, a, menge, "in den Warenkorb getan."); // hier liegt der fehler
	}
	/**
	 * Methode die einen artikel einliest und an die warenkorb verwaltung durchreicht
	 * @param artID Artikel ID des zu entfernenden Artikels
	 */

	public void artikelAusWarenkorb(int artID, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		warkoVer.artikelAusWarenkorb(a, akteur);
		erVer.ereignisEinfuegen(akteur, a, a.getMenge(), "Artikel " + a.getName() + " aus dem Warenkorb entfernt.");
	}
	/**
	 * Methode die, die Warenkorb Liste löscht.
	 * @return Die Artikelliste.
	 * @throws WarenkorbIstLeerException 
	 */

	public void warenkorbLeeren(Kunde akteur) throws WarenkorbIstLeerException{
		
		akteur.getWarenkorb().leeren();
		
	}
	/** 
	 * Methode um den Warenkorb einzukaufen.
	 * @param User aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException 
	 */
	public void rechnungErstellen(Kunde akteur) throws ArtikelNichtVerfuegbarException, WarenkorbIstLeerException, ArtikelMengeInkorrektException{
		// key == Artikel
		HashMap<Artikel, Integer> warenkorb = akteur.getWarenkorb().getInhalt();
		if(warenkorb.isEmpty()){
			throw new WarenkorbIstLeerException();
		} else {
			// Artikelmenge im Artikelbestand verringern
			for(Artikel key : warenkorb.keySet()) {
				// System.out.println("Artikel: " + key.getName() + "Zahl: " + warenkorb.get(key));
				artVer.setArtikelMenge(key.getNummer(), (warenkorb.get(key)*-(1))); 

				erVer.ereignisEinfuegen(akteur, key, warenkorb.get(key), "Artikel gekauft. (Rechnung wurde erstellt)");
		    }
		}		
		Rechnung rechnung = new Rechnung(akteur, akteur.getWarenkorb(), new Date());
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
	 * @throws ArtikelNichtVerfuegbarException 
	 */
	public void mengeAendern(int nummer, int anzahl, User akteur) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException{
		Artikel derWars = artVer.findArtikelByNumber(nummer);
		if (derWars != null) {
			artVer.setArtikelMenge(nummer, anzahl);		
	
			// aus nummer und anzahl muss ich den rest herausfinden
			erVer.ereignisEinfuegen(akteur, derWars, anzahl, "Bestandsanzahl geändert.");
		}
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
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException{
		userVer.loescheUser(userNr, aktuellerBenutzer);
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
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		artVer.ladeDaten(); //funktioniert
		userVer.ladeDaten(); // user objekte
		erVer.ladeDaten();			
	
	}
	public void speichereDaten() throws FileNotFoundException, IOException {
		artVer.schreibeDaten();
		userVer.schreibeDaten2(); //user objekte
//		userVer.schreibeDaten(); // user verwaltung
		erVer.schreibeDaten();
	}
	/**
	 * Methode die Artikelmenge im Warenkorb ändert
	 * @param artID
	 * @param menge bei positiv: hinzufügen, negativ verringern
	 * @param akteur
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void artikelMengeImWarenkorbAendern(int artID, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException {			
		Artikel a = artVer.findArtikelByNumber(artID);
		warkoVer.setArtikelMenge(a, menge, akteur);
	}
	public void einkaufsVerlauf(int artID, int anzahlTage) throws ArtikelNichtVerfuegbarException {
		// gibt die artikelmenge des artikels zurueck
		Artikel a = artVer.findArtikelByNumber(artID);
		// die soll ich verwenden und bauen
		List<Ereignis> liste = erVer.gibEreignisseNachArtikelUndTagen(a, anzahlTage); // Liste von Ereignissen
		if(liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<Ereignis> it = liste.iterator();
			while (it.hasNext()) {
				Ereignis ereignis = it.next();
				System.out.println(ereignis.toString());
			}			
		}
		System.out.println(" ");
	}
	/**
	 * Methode um einen Artikel aus dem Bestand zu löschen
	 * @param artID
	 * @param User aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void loescheArtikel(int artID, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException{
		Artikel a = artVer.findArtikelByNumber(artID);
		erVer.ereignisEinfuegen(aktuellerBenutzer, a, a.getMenge(), "Artikel gelöscht.");
		artVer.loescheArtikel(a);		
	}
}
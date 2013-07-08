package domain;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.Kunde;
import valueobjects.MehrfachArtikel;
import valueobjects.Rechnung;
import valueobjects.User;
import valueobjects.Warenkorb;
import domain.exceptions.ArtikelAngabenInkorrektException;
import domain.exceptions.ArtikelMengeInkorrektException;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNichtVerfuegbarException;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;



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
	public void fuegeUserEin(String name, char[] passwort, String anrede, String vorUndZuName)  throws InkorrekteRegWerteException{
		userVer.einfuegen(name, passwort, anrede, vorUndZuName);	
	}
	/**
	 * Methode um einen User einzufügen.
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, char[] passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) throws InkorrekteRegWerteException { 
		userVer.einfuegen(name, passwort, anrede, vorUndZuName, strasse, plz, ort, land);	
	}
	/**
	 * Methode die einen neuen Artikel in der Warenkorb ein und den Auftrag an die Warenkorbverwaltung weiterreicht.
	 * @param menge 
	 * @param titel Titel des einzufügenden Artikels.
	 * @param nummer Nummer des einzufügenden Artikels.
	 * @param menge Menge des einzufügenden Artikels
	 * @return 
	 * @throws ArtikelNummerFalsch 
	 */
	public Kunde artikelInWarenkorb(int artID, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		// überprüfe: sind schon mehr in warenkorb als im bestand?
		try {
			 return warkoVer.artikelInWarenkorb(a, menge, (Kunde)userVer.findUserByNumber(akteur.getNummer()));
//			 userVer.setUser(k);
		} catch(Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	/**
	 * Methode die einen artikel einliest und an die warenkorb verwaltung durchreicht
	 * @param artID Artikel ID des zu entfernenden Artikels
	 * @return 
	 * @throws MitarbeiterNichtVorhandenException 
	 * @throws WarenkorbIstLeerException 
	 */

	public Kunde artikelAusWarenkorb(int artID, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, MitarbeiterNichtVorhandenException, WarenkorbIstLeerException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		Kunde kunde = warkoVer.artikelAusWarenkorb(a, (Kunde)userVer.findUserByNumber(akteur.getNummer()));
		erVer.ereignisEinfuegen(akteur, a, a.getMenge(), "Artikel " + a.getName() + " aus dem Warenkorb entfernt.");
		return kunde;
	}
	/**
	 * Methode die, die Warenkorb Liste löscht.
	 * @return Die Artikelliste.
	 * @throws WarenkorbIstLeerException 
	 * @throws MitarbeiterNichtVorhandenException 
	 */

	public Kunde warenkorbLeeren(Kunde akteur) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException{
		akteur = (Kunde)userVer.findUserByNumber(akteur.getNummer());
		akteur.getWarenkorb().leeren();
		return akteur;
		
	}
	public Artikel findArtikelByNumber(int artID) throws ArtikelNichtVerfuegbarException {
		return artVer.findArtikelByNumber(artID);
	}
	/** 
	 * Methode um den Warenkorb einzukaufen.
	 * @param User aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException 
	 * @throws MitarbeiterNichtVorhandenException 
	 */
	public void rechnungErstellen(Kunde akteur) throws ArtikelNichtVerfuegbarException, WarenkorbIstLeerException, ArtikelMengeInkorrektException, MitarbeiterNichtVorhandenException{
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
	 * Methode die das Protokoll ausgiebt
	 * @return
	 */
	public List<Ereignis> gibProtokollListe() {
		return erVer.gibProtokollListe();
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
	 * @return 
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws MitarbeiterNichtVorhandenException 
	 */
	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artID, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException {			
		Artikel a = artVer.findArtikelByNumber(artID);
		return warkoVer.setArtikelMenge(a, menge, (Kunde)userVer.findUserByNumber(akteur.getNummer()));
	}
	public void einkaufsVerlauf(int artID) throws ArtikelNichtVerfuegbarException {
		Artikel a = artVer.findArtikelByNumber(artID);
		// die soll ich verwenden und bauen
		List<Ereignis> liste = erVer.gibEreignisseNachArtikelUndTagen(a); // Liste von Ereignissen
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
	public User userLogin(String name, char[] passwort) throws LoginFehlgeschlagenException {
		return userVer.userLogin(name, passwort);
	}
}
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
import domain.exceptions.ArtikelNurInEinheitenVerf�gbarException;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;

/**
 * Klasse zur allgemeinen ShopVerwaltung. beinhaltet die Logik und besitzt die ArtikelVerwaltung, UserVerwaltung, WarenkorbVerwaltung und EreignisVerwaltung.
 *
 */

public class ShopVerwaltung {
	private ArtikelVerwaltung artVer;
	private UserVerwaltung userVer;
	private WarenkorbVerwaltung warkoVer;
	public EreignisVerwaltung erVer;
	
	/**
	 *  Erstellt die Unterwaltungselemente.
	 */
	public ShopVerwaltung() {
		artVer = new ArtikelVerwaltung();
		userVer = new UserVerwaltung();
		warkoVer = new WarenkorbVerwaltung();
		erVer = new EreignisVerwaltung();
	}
	/**
	 * Methode die einen neuen Artikel einf�gt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzuf�genden Artikels.
	 * @param d Preis des einzuf�genden Artikels.
	 * @param akteur User der die Aktion durchgef�hrt hat.
	 * @param menge Menge des einzuf�genden Artikels
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge)  throws ArtikelAngabenInkorrektException{ // hier fehlt ArtikelExistiertBereitsException
			Artikel a = artVer.einfuegen(titel, d, menge);
			erVer.ereignisEinfuegen(akteur, a, a.getMenge(), "Neuer Artikel erstellt.");
	}
	/**
	 * Methode die einen neuen MehrfachArtikel einf�gt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzuf�genden Artikels.
	 * @param d Preis des einzuf�genden Artikels.
	 * @param akteur User der die Aktion durchgef�hrt hat.
	 * @param menge Menge des einzuf�genden Artikels
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
			MehrfachArtikel a = artVer.einfuegen(titel, d, menge, packungsGroesse);
			erVer.ereignisEinfuegen(akteur, a, a.getMenge(), "Neuer Mehrfach-Artikel erstellt.");
	}
	/**
	 * Methode um einen User einzuf�gen
	 * @param name Name des einzuf�genden Users.
	 * @param passwort Passwort des einzuf�genden User.
	 */
	public void fuegeUserEin(String name, char[] passwort, String anrede, String vorUndZuName)  throws InkorrekteRegWerteException{
		userVer.einfuegen(name, passwort, anrede, vorUndZuName);	
	}
	/**
	 * Methode um einen User einzuf�gen.
	 * @param name Name des einzuf�genden Users.
	 * @param passwort Passwort des einzuf�genden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, char[] passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) throws InkorrekteRegWerteException { 
		userVer.einfuegen(name, passwort, anrede, vorUndZuName, strasse, plz, ort, land);	
	}
	/**
	 * Methode die einen neuen Artikel in der Warenkorb ein und den Auftrag an die Warenkorbverwaltung weiterreicht.
	 * @param menge 
	 * @param titel Titel des einzuf�genden Artikels.
	 * @param nummer Nummer des einzuf�genden Artikels.
	 * @param menge Menge des einzuf�genden Artikels
	 * @return 
	 * @throws MitarbeiterNichtVorhandenException 
	 * @throws ArtikelNurInEinheitenVerf�gbarException 
	 * @throws ArtikelNummerFalsch 
	 */
	public Kunde artikelInWarenkorb(int artID, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerf�gbarException, MitarbeiterNichtVorhandenException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		// �berpr�fe: sind schon mehr in warenkorb als im bestand?
		Kunde k = warkoVer.artikelInWarenkorb(a, menge, (Kunde)userVer.findUserByNumber(akteur.getNummer()));
		return k;
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
	 * Methode die, die Warenkorb Liste l�scht.
	 * @return Die Artikelliste.
	 * @throws WarenkorbIstLeerException 
	 * @throws MitarbeiterNichtVorhandenException 
	 */

	public Kunde warenkorbLeeren(Kunde akteur) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException{
		akteur = (Kunde)userVer.findUserByNumber(akteur.getNummer());
		akteur.getWarenkorb().leeren();
		return akteur;
		
	}
	/**
	 * Methode, um einen Artikel nach der Nummer zu suchen
	 * @param artID
	 * @return Artikel, mit "der" Nummer
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public Artikel findArtikelByNumber(int artID) throws ArtikelNichtVerfuegbarException {
		return artVer.findArtikelByNumber(artID);
	}
	/** 
	 * Methode um den Warenkorb einzukaufen.
	 * @param User aktuellerBenutzer
	 * @return 
	 * @throws ArtikelNichtVerfuegbarException 
	 * @throws MitarbeiterNichtVorhandenException 
	 */
	public Rechnung rechnungErstellen(Kunde akteur) throws ArtikelNichtVerfuegbarException, WarenkorbIstLeerException, ArtikelMengeInkorrektException, MitarbeiterNichtVorhandenException{
		// key == Artikel
		akteur = (Kunde)userVer.findUserByNumber(akteur.getNummer());
		HashMap<Artikel, Integer> warenkorb = akteur.getWarenkorb().getInhalt();
		System.out.println(warenkorb);
		if(warenkorb.isEmpty()){
			throw new WarenkorbIstLeerException();
		} else {
			// Artikelmenge im Artikelbestand verringern
			for(Artikel key : warenkorb.keySet()) {
				artVer.setArtikelMenge(key.getNummer(), (warenkorb.get(key)*-(1))); 

				erVer.ereignisEinfuegen(akteur, key, warenkorb.get(key), "Artikel gekauft. (Rechnung wurde erstellt)");
		    }
		}
		Rechnung rechnung = new Rechnung(akteur, akteur.getWarenkorb(), new Date());
//		warenkorbLeeren(akteur);
		return rechnung;
	}
	
	/** 
	 * Methode um die Artikelliste zur�ckzugeben.
	 * @return Die Artikelliste.
	 */
	public List<Artikel> gibAlleArtikel(){
		return artVer.getArtikelBestand();
	}
	/**
	 * Methode um die Benutzerliste zur�ckzugeben.
	 * @return Die Benutzerliste.
	 */
	public List<User> gibAlleUser(){
		return userVer.getUserBestand();
	}
	
	/**
	 * Methode um den Warenkorb Inhaltzur�ckzugeben.
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
	 * Methode die einer Liste �ndert
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
			erVer.ereignisEinfuegen(akteur, derWars, anzahl, "Bestandsanzahl ge�ndert.");
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
	 * Methode die einen User l�scht
	 * @param userName
	 * @param aktuellerBenutzer
	 */
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException{
		userVer.loescheUser(userNr, aktuellerBenutzer);
	}
	/**
	 * Methode die, die Artikelliste ausgibt
	 */
	public void gibArtikellisteAus() {
		artVer.gibArtikellisteAus();		
	}
	/**
	 * Methode, die die Benutzerliste ausgibt
	 */
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
	/**
	 * Methode, die die ladeDaten Methoden der unteren Verwaltungen aufruft
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		artVer.ladeDaten(); //funktioniert
		userVer.ladeDaten(); // user objekte
		erVer.ladeDaten();			
	
	}
	/**
	 * Methode, die die speichereDaten Methoden der unteren Verwaltungen aufruft
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void speichereDaten() throws FileNotFoundException, IOException {
		artVer.schreibeDaten();
		userVer.schreibeDaten2(); //user objekte
		erVer.schreibeDaten();
	}
	/**
	 * Methode die Artikelmenge im Warenkorb �ndert
	 * @param artID
	 * @param menge bei positiv: hinzuf�gen, negativ verringern
	 * @param akteur
	 * @return 
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws MitarbeiterNichtVorhandenException 
	 */
	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artID, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException {			
		Artikel a = artVer.findArtikelByNumber(artID);
		return warkoVer.setArtikelMenge(a, menge, (Kunde)userVer.findUserByNumber(akteur.getNummer()));
	}
	/**
	 * Methode, die den Mengenverlauf eines Artikels ausgibt
	 * @param artID
	 * @throws ArtikelNichtVerfuegbarException
	 */
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
	 * Methode um einen Artikel aus dem Bestand zu l�schen
	 * @param artID
	 * @param User aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void loescheArtikel(int artID, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException{
		Artikel a = artVer.findArtikelByNumber(artID);
		erVer.ereignisEinfuegen(aktuellerBenutzer, a, a.getMenge(), "Artikel gel�scht.");
		artVer.loescheArtikel(a);		
	}
	/**
	 * Methode, um sich einzuloggen
	 * @param name
	 * @param passwort
	 * @return
	 * @throws LoginFehlgeschlagenException
	 */
	public User userLogin(String name, char[] passwort) throws LoginFehlgeschlagenException {
		return userVer.userLogin(name, passwort);
	}
}
package shop.local.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.ArtikelMengeReichtNichtException;
import shop.local.domain.exceptions.ArtikelNichtVerfuegbarException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
import shop.local.valueobjects.Kunde;
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
	 * Methode die einen neuen Artikel einf�gt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzuf�genden Artikels.
	 * @param nummer Nummer des einzuf�genden Artikels.
	 * @param aktuellerBenutzer 
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur)  { // hier fehlt ArtikelExistiertBereitsException
		artVer.einfuegen(titel, d);
		//erVer.ereignisEinfuegen(akteur, jahrestag, artVer.getArtikelBestand().get(artVer.getArtikelBestand().size()), menge, "Artikel zum Bestand hinzugefuegt.");
	}
	/**
	 * Methode die einen neuen Artikel einf�gt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzuf�genden Artikels.
	 * @param nummer Nummer des einzuf�genden Artikels.
	 * @param menge Menge des einzuf�genden Artikels
	 * @param aktuellerBenutzer 
	 */
	public void fuegeArtikelEin(String titel, double d, User akteur, int menge)  { // hier fehlt ArtikelExistiertBereitsException
		artVer.einfuegen(titel, d, menge);
		//erVer.ereignisEinfuegen(akteur, jahrestag, artVer.getArtikelBestand().get(artVer.getArtikelBestand().size()), menge, "Artikel zum Bestand hinzugefuegt.");
	}
	/**
	 * Methode um einen User einzuf�gen
	 * @param name Name des einzuf�genden Users.
	 * @param passwort Passwort des einzuf�genden User.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName)  { // hier fehlt ArtikelExistiertBereitsException
		userVer.einfuegen(name, passwort, anrede, vorUndZuName);	
	}
	/**
	 * Methode um einen User einzuf�gen.
	 * @param name Name des einzuf�genden Users.
	 * @param passwort Passwort des einzuf�genden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land)  { // hier fehlt ArtikelExistiertBereitsException
		userVer.einfuegen(name, passwort, anrede, vorUndZuName, strasse, plz, ort, land);	
	}
	/**
	 * Methode die einen neuen Artikel in der Warenkorb ein und den Auftrag an die Warenkorbverwaltung weiterreicht.
	 * @param menge 
	 * @param titel Titel des einzuf�genden Artikels.
	 * @param nummer Nummer des einzuf�genden Artikels.
	 * @param menge Menge des einzuf�genden Artikels
	 * @throws ArtikelNummerFalsch 
	 */
	public void artikelInWarenkorb(int artID, int menge, User akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		warkoVer.artikelInWarenkorb(a, menge, akteur);
	}
	/**
	 * Methode die einen artikel einliest und an die warenkorb verwaltung durchreicht
	 * @param artID Artikel ID des zu entfernenden Artikels
	 */

	public void artikelAusWarenkorb(int artID, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {	
		Artikel a = artVer.findArtikelByNumber(artID);
		warkoVer.artikelAusWarenkorb(a, akteur);
	}
	/**
	 * Methode die, die Warenkorb Liste l�scht.
	 * @return Die Artikelliste.
	 */

	public void warenkorbLeeren(Kunde akteur){
		akteur.getWarenkorb().leeren();
	}
	/** 
	 * Methode um den Warenkorb einzukaufen.
	 * @param User aktuellerBenutzer
	 */
	public void rechnungErstellen(Kunde akteur){
		Rechnung rechnung = new Rechnung(akteur, akteur.getWarenkorb(), 0);
		warenkorbLeeren(akteur);
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
	public List<Ereignis> gibProtokoll() {
		return erVer.gibProtokoll();
	}
	/**
	 * Methode die einer Liste �ndert
	 * @param nummer
	 * @param anzahl
	 * @param akteur
	 */
	public void mengeAendern(int nummer, int anzahl, User akteur) {
		artVer.setArtikelMenge(nummer, anzahl);
		// was hat er gemacht?
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
		// es muss auch �bergeben werden, wer die �nderung vollzogen hat, am besten gleich als objekt
		// am besten auch den artikel gleich als objekt �bergeben
		
		// aus nummer und anzahl muss ich den rest herausfinden
		erVer.ereignisEinfuegen(akteur, jahrestag, derWars, anzahl, "Bestandsanzahl ge�ndert.");
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
	public void loescheUser(int userName, User aktuellerBenutzer) {
		userVer.loescheUser(userName, aktuellerBenutzer);
	}
	/**
	 * Methode die, die Artikelliste ausgiebt
	 */
	public void gibArtikellisteAus() {
		artVer.gibArtikellisteAus();		
	}
	/**
	 * Methode die den WarenkorbInhalt vom User ausgeben soll
	 * @param user
	 */
	public void getWarenkorbInhalt(User user){
		warkoVer.getWarenkorbInhalt(user);
	}
}
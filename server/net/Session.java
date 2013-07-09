package net;

import java.util.HashMap;
import java.util.List;

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.Kunde;
import valueobjects.Rechnung;
import valueobjects.User;

import commons.ClientInterface;
import commons.SessionInterface;

import de.root1.simon.annotation.SimonRemote;
import domain.exceptions.ArtikelAngabenInkorrektException;
import domain.exceptions.ArtikelMengeInkorrektException;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNichtVerfuegbarException;
import domain.exceptions.ArtikelNurInEinheitenVerfügbarException;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.UserIstSchonEingeloggtException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;
/**
 * Klasse für eine Session
 *
 */
@SimonRemote(value={SessionInterface.class})
public class Session implements SessionInterface {

	private static final long serialVersionUID = -1186074865944845302L;
	private ClientInterface client;
	private ServerInterfaceImpl server;
	/**
	 * Erstellen und füllen (mit den übergebenen Angaben) 
	 * der Client und Server Variable.
	 * @param client Client der Session
	 * @param server Server der Session
	 */
	public Session(ClientInterface client, ServerInterfaceImpl server) {
		this.client = client;
		this.server = server;
	}
	/**
	 * Entfernt Sich aus der Serverliste.
	 */
	@Override
	public void unreferenced() {
		server.removeSession(this);
	}
	/**
	 * Gibt sein eigenes Clientojekt zurück.
	 * @return this.client
	 */
	@Override
	public ClientInterface getClient() {
		return this.client;
	}
	/**
	 * Gibt Alle User aus der Serverliste zurück.
	 * vom server in dieser Liste.
	 * @return server.gibAlleUser() Methode die angemeldete User vom Server ausgibt
	 */
	@Override
	public Object[][] gibAlleUser() {
		return server.gibAlleUser();
	}
	/**
	 * Ruft die userlogin methode vom Server auf und gibt das Ergebnis zurück.
	 * @param name Name für den Login
	 * @param pw Passwort für den Login
	 * @throws LoginFehlgeschlagenException
	 * @throws UserIstSchonEingeloggtException
	 * @return User gibt das Userobjekt zurück
	 */
	@Override
	public User userLogin(String name, char[] pw) throws LoginFehlgeschlagenException, UserIstSchonEingeloggtException {
		return server.userLogin(name, pw);
	}
	/**
	 * Ruft die gibAlleArtikel Methode des Servers auf und gibt das Ergebnis weiter.
	 * @return List gibt Liste alle Artikel vom Server zurück
	 */
	@Override
	public List<Artikel> gibAlleArtikel() {
		return server.gibAlleArtikel();
	}
	/**
	 * Ruft die gibProtokollliste Methode des Servers auf,
	 * und gibt die Protokollliste der Ereignisse zurück.
	 * @return List Protokollliste
	 */
	@Override
	public List<Ereignis> gibProtokollListe() {
		return server.gibProtokollListe();
	}
	/**
	 * Ruft die rechnungErstellen Methode des Servers auf
	 * und gibt die erstellte Rechnung zurück
	 * @return Rechnung erstellte Rechnung vom Server
	 */
	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, WarenkorbIstLeerException, ArtikelMengeInkorrektException, MitarbeiterNichtVorhandenException {
		return server.rechnungErstellen(aktuellerBenutzer);
	}
	/**
	 * Methode zum erstellen eines Users.
	 * @param String userName
	 * @param char[] pw1 Passwort
	 * @param String anrede
	 * @param String name
	 * @param String str
	 * @param int parseInt Hausnummer
	 * @param String ort
	 * @param String land
	 * @throws InkorrekteRegWerteException
	 */
	@Override
	public void fuegeUserEin(String userName, char[] pw1, String anrede,
			String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException {
		server.fuegeUserEin(userName,pw1,anrede,name,str,parseInt,ort,land);		
	}
	/**
	 * Ruft die Methode artikelInWarenKorb vom Server auf.
	 * @param artikelNummer Nr vom Artikel der in den Warenkorb soll
	 * @param menge Gewünschte Anzahl
	 * @param aktueller Benutzer
	 * @return Kunde gibt das geänderte Kundenobjekt zurück
	 * @throws MitarbeiterNichtVorhandenException 
	 * @throws ArtikelNurInEinheitenVerfügbarException 
	 */
	@Override
	public Kunde artikelInWarenkorb(int artikelNummer, int menge, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerfügbarException, MitarbeiterNichtVorhandenException {
		return server.artikelInWarenkorb(artikelNummer,menge,aktuellerBenutzer);
	}
	/**
	 * @param artikelNummer Nr vom Artikel der in den Warenkorb soll
	 * @param menge Gewünschte Anzahl
	 * @param aktueller Benutzer
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws MitarbeiterNichtVorhandenException
	 * @return HashMap<Artikel, Integer> gibt den geänderten Warenkorb zurück.
	 */
	@Override
	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException {
		return server.artikelMengeImWarenkorbAendern(artikelNummer, menge, aktuellerBenutzer);
		
	}
	/**
	 * Ruft die Methode auf dem Server auf die einen Artikel aus dem Warenkorb nimmt.
	 * @param artikelNummer Nummer des zu löschenden Nutzers
	 * @param aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws ArtikelMengeReichtNichtException
	 * @throws MitarbeiterNichtVorhandenException
	 * @throws WarenkorbIstLeerException
	 * @return Kunde gibt das geänderte Kundenobjekt zurück
	 */
	@Override
	public Kunde artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, MitarbeiterNichtVorhandenException, WarenkorbIstLeerException {
		return server.artikelAusWarenkorb(artikelNummer, aktuellerBenutzer);
	}
	/**
	 * Die Methode vom Server wird aufgerufen die den Inhalt des Warenkorbs des übergebenen Users löscht.
	 * @param aktuellerBenuzer
	 * @throws WarenkorbIstLeerException
	 * @throws MitarbeiterNichtVorhandenException
	 * @return Kunde gibt das geänderte Kundenobjekt zurück
	 */
	@Override
	public Kunde warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException {
		return server.warenkorbLeeren(aktuellerBenutzer);
		
	}
	/**
	 * Ruft die Methode des Servers auf die einen Artikel in die Liste auf dem Server schreibt. (ohne Packungsgröße)
	 * @param titel
	 * @param d Preis des Artikel
	 * @param aktuellerBenutzer
	 * @param menge
	 * @throws ArtikelAngabenInkorrektException
	 */
	@Override
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException {
		server.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
		
	}
	/**
	 * Ruft die Methode des Servers auf die einen Artikel in die Liste auf dem Server schreibt. (mit Packungsgröße)
	 * @param titel
	 * @param d Preis des Artikel
	 * @param aktuellerBenutzer
	 * @param menge
	 * @param packungsGroesse
	 * @throws ArtikelAngabenInkorrektException
	 */
	@Override
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
		server.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
		
	}
	/**
	 * Ruft Methode mengeAendern vom Server auf.
	 * @param nummer Nr des Artikels
	 * @param anzahl Anzahl des Artikels
	 * @param aktuellerBenutzer
	 * @throws ArtikelMengeInkorrektException
	 * @throws ArtikelNichtVerfuegbarException
	 */
	@Override
	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException {
		server.mengeAendern(nummer, anzahl, aktuellerBenutzer);
	}
	/**
	 * Ruft Methode loescheArtikel des Servers auf.
	 * @param artikelNummer Nr des zu löschenden Artikels
	 * @param aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException
	 */
	@Override
	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		server.loescheArtikel(artikelNummer, aktuellerBenutzer);
	}
	/**
	 * Ruft die Methode fuegeUserEin vom Server auf.
	 * @param name
	 * @param pw1 Passwort
	 * @param anrede
	 * @param vorUndZunName
	 * @throws InkorrekteRegWerteException
	 */
	@Override
	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException {
		server.fuegeUserEin(name, pw1, anrede, vorUndZuName);
		
	}
	/**
	 * Ruft die Methode loescheUser vom Server auf.
	 * @param userNr Nr des zu löschenden Benutzers
	 * @param aktuellerBenutzer
	 * @throws MitarbeiterNichtVorhandenException
	 */
	@Override
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException {
		server.loescheUser(userNr,aktuellerBenutzer);
		
	}
	/**
	 * Ruft die Methode gibEreignisseNachArtikelUndTagen vom Server auf.
	 * @param a Artikel für den dies Aufgerufen werden soll
	 * @return List Ereignisse
	 */
	@Override
	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
		// TODO Auto-generated method stub
		return server.gibEreignisseNachArtikelUndTagen(a);
	}
	/**
	 * Ruft die Methode findArtikelByNumber vom Server auf.
	 * @param artikelNr Nr des Artikels nach dem gesucht wird
	 * @throws ArtikelNichtVerfuegbarException
	 * @return Artikel gefundener Artikel
	 */
	@Override
	public Artikel findArtikelByNumber(int artikelNr) throws ArtikelNichtVerfuegbarException {
		return server.findArtikelByNumber(artikelNr);
	}
	/**
	 * Ruft die Methode userLogout vom client auf.
	 */
	@Override
	public void userLogout() {
		client.userLogout();
	}

}

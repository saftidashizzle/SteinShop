package net;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.Kunde;
import valueobjects.Rechnung;
import valueobjects.User;

import commons.ClientInterface;
import commons.ServerInterface;
import commons.SessionInterface;

import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;
import domain.exceptions.ArtikelAngabenInkorrektException;
import domain.exceptions.ArtikelMengeInkorrektException;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNichtVerfuegbarException;
import domain.exceptions.ArtikelNurInEinheitenVerf�gbarException;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.UserIstSchonEingeloggtException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;

/**
 * Klasse ClientinterfaceImpl: Implementierung eines Clients
 *
 */
@SimonRemote(value = {ClientInterface.class})
public class ClientInterfaceImpl implements ClientInterface {
	
	private static final long serialVersionUID = -7272521996636160840L;
		
	private Lookup lookup;
	private ServerInterface server;
	private SessionInterface session;
	private User aktuellerBenutzer;
	
	private boolean safeLogout = false;
	
	public ClientInterfaceImpl() {
	}
	/**
	 * Methode um zum server zu connecten
	 * @throws UnknownHostException UnknownHostException
	 * @throws LookupFailedException LookupFailedException
	 * @throws EstablishConnectionFailed EstablishConnectionFailed
	 */
	public void connectToServer() throws UnknownHostException, LookupFailedException, EstablishConnectionFailed{
		// Lookup f�r den Server einrichten
		lookup = Simon.createNameLookup("127.0.0.1", 4753);
		// Server-Objekt aufsuchen
		server = (ServerInterface) lookup.lookup("myServer");
		// Client auf dem Server anmelden und Session empfangen
		session = server.login(this);		
	}
	/**
	 * Methode um sich vom Server wieder abzumelden
	 * @throws FileNotFoundException Datei nicht gefunden
	 * @throws IOException Falsche Eingabe
	 */
	public void logout() throws FileNotFoundException, IOException{
		safeLogout = true;
		// Lookup-Bindung wieder l�sen
		server.safe();
		lookup.release(server);
	}
	/**
	 * Methode die den SafeLogout �berpr�ft
	 */
	@Override
	public void unreferenced() {
		if(!safeLogout){
			// TODO Fensterwarnung draus machen
		    JOptionPane.showMessageDialog(null,"Die Verbindung zum Server wurde unerwartet getrennt.");
//			area.append("Die Verbindung zum Server wurde unerwartet getrennt.\n");
		}
	}
	/**
	 * Methode um alle Benutzer zur�ckzugeben
	 * @return Object[][] Alle Benutzer als 2 Dimensionales Array , ohne Passwoerter
	 */
	public Object[][] gibAlleUser() {
		return session.gibAlleUser();
	}
	/**
	 * Methode f�r Userlogin auf dem Server
	 * @param name Benutzername
	 * @param pw Passwort
	 * @return gibt den Aktuellen eingeloggten Benutzer zur�ck
	 * @throws LoginFehlgeschlagenException Login hat nicht geklappt
	 * @throws UserIstSchonEingeloggtException User ist noch eingeloggt
	 */
	public User userLogin(String name, char[] pw) throws LoginFehlgeschlagenException, UserIstSchonEingeloggtException {
		aktuellerBenutzer = session.userLogin(name,pw);
		return aktuellerBenutzer;
	}
	/**
	 * Methode um den aktuell eingeloggten Benutzer zu bekommen
	 */
	public User getUser() {
		return aktuellerBenutzer;
	}
	/**
	 * Methode die die Artikelliste vom Server holz
	 * @return List<Artikel> die Artikelliste
	 */
	public List<Artikel> gibAlleArtikel() {
		return session.gibAlleArtikel();
	}
	/**
	 * Methode die die Ereignisliste vom Server holt
	 * @return List<Ereignis> die Ereignisliste
	 */
	public List<Ereignis> gibProtokollListe() {
		return session.gibProtokollListe();
	}
	/**
	 * Methode um ein rechnungsobjekt zu bekommen
	 * @param aktuellerBenutzer der Nutzer um den es geht
	 * @return die Rechnung
	 * @throws ArtikelNichtVerfuegbarException 
	 * @throws WarenkorbIstLeerException Warenkorb ist leer
	 * @throws ArtikelMengeInkorrektException 
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, WarenkorbIstLeerException, ArtikelMengeInkorrektException, MitarbeiterNichtVorhandenException {
		return session.rechnungErstellen(aktuellerBenutzer);
	}
	/**
	 * Methode um einen User einzuf�gen
	 * @param userName der Username
	 * @param pw1 das Passwort
	 * @param anrede die Anrede
	 * @param name der Name
	 * @param str die Strasse
	 * @param parseInt die Postleitzahl
	 * @param ort der Ort
	 * @param land das Land
	 * @throws InkorrekteRegWerteException Postleitzahl war falsch
	 */
	public void fuegeUserEin(String userName, char[] pw1, String anrede,String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException {
		session.fuegeUserEin(userName,pw1,anrede,name,str,parseInt,ort,land);
	}
	/**
	 * Methode um Artikel in Warenkorb zu tun
	 * @param artikelNummer die ArtikelNummer
	 * @param menge die Menge des Artikels
	 * @param aktuellerBenutzer der eingeloggte Benutzer
	 * @return gibt den Kunden mit aktualisierten Warenkorb zur�ck
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws ArtikelMengeReichtNichtException
	 * @throws WarenkorbExceedsArtikelbestandException
	 * @throws MitarbeiterNichtVorhandenException 
	 * @throws ArtikelNurInEinheitenVerf�gbarException 
	 */
	public Kunde artikelInWarenkorb(int artikelNummer, int menge, Kunde aktuellerBenutzer
			) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerf�gbarException, MitarbeiterNichtVorhandenException {
		return session.artikelInWarenkorb(artikelNummer, menge, aktuellerBenutzer);
	}
	/**
	 * Methode um die Menge eines Artikels im WArenkorb zu �ndern
	 * @param artikelNummer Die Nummer des Artikels
	 * @param menge Um wieviel er ver�ndert werden soll
	 * @param aktuellerBenutzer der eingeloggte Benutzer
	 * @return den neuen Warenkorb
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException {
		return session.artikelMengeImWarenkorbAendern(artikelNummer, menge, aktuellerBenutzer);
	}
	/**
	 * Methode um einen Artikel aus dem Warenkorb zu entfernen
	 * @param artikelNummer die Nummer des zu entfernenden Artikels
	 * @param aktuellerBenutzer der aktuell eingeloggte Benutzer
	 * @return der aktualisierte Kunde
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws ArtikelMengeReichtNichtException
	 * @throws MitarbeiterNichtVorhandenException
	 * @throws WarenkorbIstLeerException
	 */
	public Kunde artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, MitarbeiterNichtVorhandenException, WarenkorbIstLeerException {
		return session.artikelAusWarenkorb(artikelNummer, aktuellerBenutzer);
	}
	/**
	 * Methode um den Warenkorb zu leeren
	 * @param aktuellerBenutzer der eingeloggte Benutzer
	 * @return der aktualisierte Kunde
	 * @throws WarenkorbIstLeerException
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public Kunde warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException {
		return session.warenkorbLeeren(aktuellerBenutzer);		
	}
	/**
	 * Methode um einen Artikel neu zu erstellen
	 * @param titel Name des Artikels
	 * @param d Preis des Artikels
	 * @param aktuellerBenutzer der eingeloggte Mitarbeiter
	 * @param menge die gew�nschte Menge
	 * @throws ArtikelAngabenInkorrektException
	 */
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException {
		session.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
		
	}
	/**
	 * Methode um einen Mehrfachartikel neu zu erstellen
	 * @param titel Name des Artikels
	 * @param d Preis des Artikels
	 * @param aktuellerBenutzer aktuell eingeloggte Benutzer
	 * @param menge Menge des Artikels
	 * @param packungsGroesse Gr��e, in dessen Einheiten sich der Artikel nur verkaufen l�sst
	 * @throws ArtikelAngabenInkorrektException
	 */
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
		session.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
	}
	/**
	 * Methode um die Menge des Artikels zu �ndern
	 * @param nummer die nr des artikels
	 * @param anzahl um wieviel er ver�ndert werden soll
	 * @param aktuellerBenutzer der eingeloggte benutzer
	 * @throws ArtikelMengeInkorrektException
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException {
		session.mengeAendern(nummer, anzahl, aktuellerBenutzer);
	}
	/**
	 * Methode um einen Artikel zu l�schen
	 * @param artikelNummer Nr des Artikels
	 * @param aktuellerBenutzer Der eingeloggte Benutzer
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		session.loescheArtikel(artikelNummer, aktuellerBenutzer);
	}
	/**
	 * Methode um einen neuen Benutzer einzuf�gen
	 * @param name Name
	 * @param pw1 Passwort
	 * @param anrede Anrede
	 * @param vorUndZuName Name
	 * @throws InkorrekteRegWerteException
	 */
	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException {
		session.fuegeUserEin(name, pw1, anrede, vorUndZuName);
	}
	/**
	 * Methode um den User zu l�schen
	 * @param userNr die nr des users
	 * @param aktuellerBenutzer der eingeloggte Benutzer
	 * @throws MitarbeiterNichtVorhandenException 
	 */
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException {
		session.loescheUser(userNr,aktuellerBenutzer);
	}
	/**
	 * Methode um eine Ereignisliste zur�ckzugeben
	 * @param a Der Artikel um den es geht
	 * @return Die Liste mit den Ereignissen betreffend a
	 */
	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
		return session.gibEreignisseNachArtikelUndTagen(a);
	}
	/**
	 * Methode um einen Artikel anhand der Nummer zur�ckzugeben
	 * @param artikelNr die Artikelnummer
	 * @return der Artikel
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public Artikel findArtikelByNumber(int artikelNr) throws ArtikelNichtVerfuegbarException {
		return session.findArtikelByNumber(artikelNr);
	}
	/**
	 * Methode um sich auszuloggen, aktuellerBenutzer wird auf null gesetzt
	 */
	public void userLogout() {
		aktuellerBenutzer = null;
	}
}
package net;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.Kunde;
import valueobjects.Rechnung;
import valueobjects.User;

import commons.ClientInterface;
import commons.ServerInterface;
import commons.SessionInterface;

import de.root1.simon.Registry;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.NameBindingException;
import domain.ShopVerwaltung;
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
 * Klasse für das ServerInterface.
 *
 */
@SimonRemote(value = {ServerInterface.class})
public class ServerInterfaceImpl implements ServerInterface {

	private Registry registry;
	private List<SessionInterface> sessions;
	private ShopVerwaltung shopVer;
	/**
	 * Initialisieren eines Session Elements und einer Shopverwaltung.
	 */
	public ServerInterfaceImpl() {
		sessions = new ArrayList<SessionInterface>();
		shopVer = new ShopVerwaltung();
		try {
			shopVer.ladeDaten();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Methode, zum Starten des Servers.
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws NameBindingException
	 */
	public void startServer() throws UnknownHostException, IOException, NameBindingException{
		// Registry erstellen
		registry = Simon.createRegistry(4753);
		// Registry an das ServerInterface-Objekt binden und Namen vergeben
		registry.bind("myServer", this);
	}
	/**
	 * Methode, zum Stoppen des Servers.
	 */
	public void stopServer(){
		if(registry != null){
			// Bindung von der Registry lösen
			registry.unbind("myServer");
			// Registry stoppen
			registry.stop();
		}
	}
	/**
	 * Methode die eine neue Session erstellt, in die sessions Liste einträgt
	 * und diese zurück gibt.
	 * @param client ClientInterface
	 * @return session gibt die erstellte session zurück
	 */
	@Override
	public SessionInterface login(ClientInterface client) {
		System.out.println("Hallo, Client #" + sessions.size() + "!" );
		// Session für diesen Client erstellen
		Session session = new Session(client, this);
		// Wir tragen die Session in die Liste aller Sessions ein
		sessions.add(session);
		
		// Wir geben die erstellte Session zurück
		return session;
	}
	/**
	 * Methode, zum entfernen der Session.
	 * @param session zu entfernende Session
	 */
	public void removeSession(Session session) {
		sessions.remove(session);
	}
	/**
	 * Methode, die eine abgeleitete Userliste ohne Passwort erstellt.
	 * @return Object[][] Userobject (alle User)
	 */
	public Object[][] gibAlleUser() {
		List<User> userListe = shopVer.gibAlleUser();
		Object[][] data = new Object[userListe.size()][5];
		int i = 0;
		for (User u:userListe) {
			String[] row = { "" + u.getNummer(), "" + u.getName(), "" + u.getAnrede(), "" + u.getVorUndZuName() };
			data[i++] = row;
		}
		i = 0;
		// hier gehen wir die Liste durch, lesen alles aus bis auf das Passwort und zeigen das an
		return data;
	}
	/**
	 * Methode, für den Userlogin.
	 * @param name
	 * @param pw
	 * @return User gibt das komplette User Objekt zurück
	 * @throws LoginFehlgeschlagenException
	 * @throws UserIstSchonEingeloggtException
	 */
	public User userLogin(String name, char[] pw) throws LoginFehlgeschlagenException, UserIstSchonEingeloggtException {
		User user = null;
		user = shopVer.userLogin(name, pw);
		for (SessionInterface session : sessions){
			ClientInterface client = session.getClient();
			User u = client.getUser();
//			User u = (Kunde)shopVer.findUserByNumber(user.getNummer());
			if (u!=null && user.getNummer()==u.getNummer()) {
				throw new UserIstSchonEingeloggtException();
			}
		}
			
		return user;
	}
	/**
	 * Methode, zum aufrufen der Artikelliste.
	 * @return List Artikelliste
	 */
	public List<Artikel> gibAlleArtikel() {
		return shopVer.gibAlleArtikel();
	}
	/**
	 * Methode, zum aufrufen der Protokollliste.
	 * @return List Protokollliste
	 */
	public List<Ereignis> gibProtokollListe() {
		return shopVer.gibProtokollListe();
	}
	/**
	 * Methode, zum Rechnung erstellen.
	 * @param aktuellerBenutzer
	 * @return Rechnung
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws WarenkorbIstLeerException
	 * @throws ArtikelMengeInkorrektException
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, WarenkorbIstLeerException, ArtikelMengeInkorrektException, MitarbeiterNichtVorhandenException {
		return shopVer.rechnungErstellen(aktuellerBenutzer);
	}
	/**
	 * Methode, zum User einfügen.
	 * @param userName
	 * @param pw1 Passwort
	 * @param anrede
	 * @param name
	 * @param str
	 * @param parseInt Hausnummer
	 * @param ort
	 * @param land
	 * @throws InkorrekteRegWerteException
	 */
	public void fuegeUserEin(String userName, char[] pw1, String anrede,
			String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException {
		shopVer.fuegeUserEin(userName,pw1,anrede,name,str,parseInt,ort,land);
		
	}
	/**
	 * Methode, um Artikel in Warenkorb zu legen.
	 * @param artikelNummer Nr des Artikels 
	 * @param menge Menge
	 * @param akteur der übergebene Kunde
	 * @return user der zurückgegebene Kunde
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws ArtikelMengeReichtNichtException
	 * @throws WarenkorbExceedsArtikelbestandException
	 * @throws MitarbeiterNichtVorhandenException 
	 * @throws ArtikelNurInEinheitenVerfügbarException 
	 */
	public Kunde artikelInWarenkorb(int artikelNummer, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerfügbarException, MitarbeiterNichtVorhandenException {
		Kunde user = shopVer.artikelInWarenkorb(artikelNummer,menge,akteur);
		return user;
		
	}
	/**
	 * Methode, um die Artikelmenge im Warenkorb zu ändern.
	 * @param artikelNummer
	 * @param menge
	 * @param aktuellerBenutzer
	 * @return geänderte Artikelmenge, von wem, bei wem
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException {
		return shopVer.artikelMengeImWarenkorbAendern(artikelNummer, menge, aktuellerBenutzer);
		
	}
	/**
	 * Methode, um Artikel aus Warenkorb zu entfernen.
	 * @param artikelNummer Nr des zuentfernenden Artikels
	 * @param aktuellerBenutzer
	 * @return Artikel, der bei wem rausgenommen wird
	 * @throws ArtikelNichtVerfuegbarException
	 * @throws ArtikelMengeReichtNichtException
	 * @throws MitarbeiterNichtVorhandenException
	 * @throws WarenkorbIstLeerException
	 */
	public Kunde artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, MitarbeiterNichtVorhandenException, WarenkorbIstLeerException {
		return shopVer.artikelAusWarenkorb(artikelNummer, aktuellerBenutzer);
	}
	/**
	 * Methode, die den Warenkorb leert.
	 * @param aktuellerBenutzer
	 * @return Warenkorb leeren, bei wem
	 * @throws WarenkorbIstLeerException
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public Kunde warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException {
		return shopVer.warenkorbLeeren(aktuellerBenutzer);		
	}
	/**
	 * Methode, um einen Artikel einzufügen
	 * @param titel
	 * @param d Preis des Artikels (Typ double)
	 * @param aktuellerBenutzer
	 * @param menge
	 * @throws ArtikelAngabenInkorrektException
	 */
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException {
		shopVer.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
		
	}
	/**
	 * Methode, um einen Mehrfachartikel einzufügen.
	 * @param titel
	 * @param d Preis des Artikels (Typ double)
	 * @param aktuellerBenutzer
	 * @param menge
	 * @param packungsGroesse
	 * @throws ArtikelAngabenInkorrektException
	 */
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
		shopVer.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
		
	}
	/**
	 * Methode, um die Artikelmenge zu ändern.
	 * @param nummer Des Artikel
	 * @param anzahl Anzahl die geändert werden soll
	 * @param aktuellerBenutzer
	 * @throws ArtikelMengeInkorrektException
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException {
		shopVer.mengeAendern(nummer, anzahl, aktuellerBenutzer);
		
	}
	/**
	 * Methode, um einen Artikel zu löschen.
	 * @param artikelNummer Nr vom Artikel der gelöscht werden muss
	 * @param aktuellerBenutzer
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		shopVer.loescheArtikel(artikelNummer, aktuellerBenutzer);
	}
	/**
	 * Methode, um einen User hinzuzufügen.
	 * @param name
	 * @param pw1 Passwort
	 * @param anrede
	 * @param vorUndZuName
	 * @throws InkorrekteRegWerteException
	 */
	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException {
		shopVer.fuegeUserEin(name, pw1, anrede, vorUndZuName);
		
	}
	/**
	 * Methode, um einen User zu löschen.
	 * @param userNr Nr des Users der gelöscht werden soll
	 * @param aktuellerBenutzer
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException {
		shopVer.loescheUser(userNr,aktuellerBenutzer);
		
	}
	/**
	 * Methode, um die Ereignisse auszugeben.
	 * @param a Artikel für Ergebnissausgabe
	 * @return List Ereignisse
	 */
	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
		return shopVer.erVer.gibEreignisseNachArtikelUndTagen(a);
	}
	/**
	 * Methode, um einen Artikel nach Nummer zu suchen.
	 * @param artikelNr Nr des zu suchenden Artikels
	 * @return Artikel gesuchter Artikel
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public Artikel findArtikelByNumber(int artikelNr) throws ArtikelNichtVerfuegbarException {
		return shopVer.findArtikelByNumber(artikelNr);
	}
	/**
	 *  Methode zum speichern.
	 *  @throws FileNotFoundException
	 *  @throws IOException
	 */
	@Override
	public void safe() throws FileNotFoundException, IOException {
		shopVer.speichereDaten();
	}
}

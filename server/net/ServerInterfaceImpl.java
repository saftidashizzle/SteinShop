package net;

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
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.UserIstSchonEingeloggtException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;

@SimonRemote(value = {ServerInterface.class})
public class ServerInterfaceImpl implements ServerInterface {

	private Registry registry;
	private List<SessionInterface> sessions;
	private ShopVerwaltung shopVer;

	public ServerInterfaceImpl() {
		sessions = new ArrayList<SessionInterface>();
		shopVer = new ShopVerwaltung();
		try {
			shopVer.ladeDaten();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startServer() throws UnknownHostException, IOException, NameBindingException{
		// Registry erstellen
		registry = Simon.createRegistry(4753);
		// Registry an das ServerInterface-Objekt binden und Namen vergeben
		registry.bind("myServer", this);
	}
	
	public void stopServer(){
		if(registry != null){
			// Bindung von der Registry l�sen
			registry.unbind("myServer");
			// Registry stoppen
			registry.stop();
		}
	}

	@Override
	public SessionInterface login(ClientInterface client) {
		System.out.println("Hallo, Client #" + sessions.size() + "!" );
		// Session f�r diesen Client erstellen
		Session session = new Session(client, this);
		// Wir tragen die Session in die Liste aller Sessions ein
		sessions.add(session);
		
		// Wir geben die erstellte Session zur�ck
		return session;
	}

	public void broadcastMessage(String message) {
		for (SessionInterface session : sessions){
			ClientInterface client = session.getClient();
			client.receiveMessage(message);
		}
	}

	public void removeSession(Session session) {
		sessions.remove(session);
		
		this.broadcastMessage("Ein Benutzer ist weg.\n");
	}

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

	public User userLogin(String name, char[] pw) throws LoginFehlgeschlagenException, UserIstSchonEingeloggtException {
		User user = null;
		user = shopVer.userLogin(name, pw);
		for (SessionInterface session : sessions){
			ClientInterface client = session.getClient();
			User u = client.getUser();
			System.out.println("u: " + u + "\n user: " + user);
			if (u!=null && user.getNummer()==u.getNummer()) {
				throw new UserIstSchonEingeloggtException();
			}
		}
			
		return user;
	}

	public List<Artikel> gibAlleArtikel() {
		return shopVer.gibAlleArtikel();
	}

	public List<Ereignis> gibProtokollListe() {
		return shopVer.gibProtokollListe();
	}

	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer) {
		// TODO Auto-generated method stub
		return null;
	}

	public void fuegeUserEin(String userName, char[] pw1, String anrede,
			String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException {
		shopVer.fuegeUserEin(userName,pw1,anrede,name,str,parseInt,ort,land);
		
	}

	public HashMap<Artikel, Integer> artikelInWarenkorb(int artikelNummer, int menge, Kunde akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException {
		return shopVer.artikelInWarenkorb(artikelNummer,menge,akteur);
		
	}

	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		return shopVer.artikelMengeImWarenkorbAendern(artikelNummer, menge, aktuellerBenutzer);
		
	}

	public void artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {
		shopVer.artikelAusWarenkorb(artikelNummer, aktuellerBenutzer);
	}

	public void warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException {
		shopVer.warenkorbLeeren(aktuellerBenutzer);		
	}

	public void artikelNachNamenOrdnen() {
		shopVer.artikelNachNamenOrdnen();		
	}

	public void artikelNachZahlenOrdnen() {
		shopVer.artikelNachZahlenOrdnen();		
	}

	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException {
		shopVer.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
		
	}

	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
		shopVer.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
		
	}

	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException {
		shopVer.mengeAendern(nummer, anzahl, aktuellerBenutzer);
		
	}

	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		shopVer.loescheArtikel(artikelNummer, aktuellerBenutzer);
	}

	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException {
		shopVer.fuegeUserEin(name, pw1, anrede, vorUndZuName);
		
	}

	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException {
		shopVer.loescheUser(userNr,aktuellerBenutzer);
		
	}

	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
		return shopVer.erVer.gibEreignisseNachArtikelUndTagen(a);
	}
}
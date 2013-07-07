package net;



import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;

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
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;

@SimonRemote(value = {ClientInterface.class})
public class ClientInterfaceImpl implements ClientInterface {
	
	private static final long serialVersionUID = -7272521996636160840L;
	
	private JTextArea area;
	
	private Lookup lookup;
	private ServerInterface server;
	private SessionInterface session;
	
	private boolean safeLogout = false;
	
	public ClientInterfaceImpl() {
	}
	
	public void connectToServer() throws UnknownHostException, LookupFailedException, EstablishConnectionFailed{
		// Lookup für den Server einrichten
		lookup = Simon.createNameLookup("127.0.0.1", 4753);
		// Server-Objekt aufsuchen
		server = (ServerInterface) lookup.lookup("myServer");
		// Client auf dem Server anmelden und Session empfangen
		session = server.login(this);		
	}
	
	public void sendMessage(){
		session.sendMessage("Hallo!\n");
	}
	
	public void logout(){
		safeLogout = true;
		// Lookup-Bindung wieder lösen
		lookup.release(server);
	}

	@Override
	public void unreferenced() {
		if(!safeLogout){
			area.append("Die Verbindung zum Server wurde unerwartet getrennt.\n");
		}
	}

	@Override
	public void receiveMessage(String message) {
		area.append(message);
	}

	public Object[][] gibAlleUser() {
		return session.gibAlleUser();
	}

	public User userLogin(String name, char[] pw) {
		return session.userLogin(name,pw);
	}

	public List<Artikel> gibAlleArtikel() {
		return session.gibAlleArtikel();
	}

	public List<Ereignis> gibProtokollListe() {
		return session.gibProtokollListe();
	}

	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer) {
		return session.rechnungErstellen(aktuellerBenutzer);
	}

	public void fuegeUserEin(String userName, char[] pw1, String anrede,String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException {
		session.fuegeUserEin(userName,pw1,anrede,name,str,parseInt,ort,land);
	}

	public HashMap<Artikel, Integer> artikelInWarenkorb(int artikelNummer, int menge, Kunde aktuellerBenutzer
			) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException {
		return session.artikelInWarenkorb(artikelNummer, menge, aktuellerBenutzer);
	}

	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		return session.artikelMengeImWarenkorbAendern(artikelNummer, menge, aktuellerBenutzer);
	}

	public void artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {
		session.artikelAusWarenkorb(artikelNummer, aktuellerBenutzer);
	}

	public void warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException {
		session.warenkorbLeeren(aktuellerBenutzer);		
	}

	public void artikelNachNamenOrdnen() {
		session.artikelNachNamenOrdnen();		
	}

	public void artikelNachZahlenOrdnen() {
		session.artikelNachZahlenOrdnen();
	}

	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException {
		session.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
		
	}

	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
		session.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
		
	}

	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException {
		session.mengeAendern(nummer, anzahl, aktuellerBenutzer);
		
	}

	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		session.loescheArtikel(artikelNummer, aktuellerBenutzer);
		
	}

	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException {
		session.fuegeUserEin(name, pw1, anrede, vorUndZuName);
		
	}

	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException {
		session.loescheUser(userNr,aktuellerBenutzer);
		
	}

	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
		// TODO Auto-generated method stub
		return session.gibEreignisseNachArtikelUndTagen(a);
	}

//	@Override
//	public User getUser() {
//		return this.user;
//	}
//
//	public void setUser(User user) {
//		this.user=user;
//		
//	}
}
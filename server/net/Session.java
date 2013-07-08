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
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
import domain.exceptions.UserIstSchonEingeloggtException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;

@SimonRemote(value={SessionInterface.class})
public class Session implements SessionInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1186074865944845302L;
	private ClientInterface client;
	private ServerInterfaceImpl server;

	public Session(ClientInterface client, ServerInterfaceImpl server) {
		this.client = client;
		this.server = server;
	}
	@Override
	public void unreferenced() {
		server.removeSession(this);
	}
	@Override
	public ClientInterface getClient() {
		return this.client;
	}
	@Override
	public Object[][] gibAlleUser() {
		return server.gibAlleUser();
	}
	@Override
	public User userLogin(String name, char[] pw) throws LoginFehlgeschlagenException, UserIstSchonEingeloggtException {
		return server.userLogin(name, pw);
	}
	@Override
	public List<Artikel> gibAlleArtikel() {
		return server.gibAlleArtikel();
	}
	@Override
	public List<Ereignis> gibProtokollListe() {
		return server.gibProtokollListe();
	}
	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer) {
		return server.rechnungErstellen(aktuellerBenutzer);
	}
	@Override
	public void fuegeUserEin(String userName, char[] pw1, String anrede,
			String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException {
		server.fuegeUserEin(userName,pw1,anrede,name,str,parseInt,ort,land);		
	}
	@Override
	public Kunde artikelInWarenkorb(int artikelNummer, int menge, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException {
		return server.artikelInWarenkorb(artikelNummer,menge,aktuellerBenutzer);
	}
	@Override
	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException {
		return server.artikelMengeImWarenkorbAendern(artikelNummer, menge, aktuellerBenutzer);
		
	}
	@Override
	public Kunde artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, MitarbeiterNichtVorhandenException, WarenkorbIstLeerException {
		return server.artikelAusWarenkorb(artikelNummer, aktuellerBenutzer);
	}
	@Override
	public Kunde warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException {
		return server.warenkorbLeeren(aktuellerBenutzer);
		
	}
	@Override
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException {
		server.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge);
		
	}
	@Override
	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException {
		server.fuegeArtikelEin(titel, d, aktuellerBenutzer, menge, packungsGroesse);
		
	}
	@Override
	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException {
		server.mengeAendern(nummer, anzahl, aktuellerBenutzer);
	}
	@Override
	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException {
		server.loescheArtikel(artikelNummer, aktuellerBenutzer);
	}
	@Override
	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException {
		server.fuegeUserEin(name, pw1, anrede, vorUndZuName);
		
	}
	@Override
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException {
		server.loescheUser(userNr,aktuellerBenutzer);
		
	}
	@Override
	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
		// TODO Auto-generated method stub
		return server.gibEreignisseNachArtikelUndTagen(a);
	}
	@Override
	public Artikel findArtikelByNumber(int artikelNr) throws ArtikelNichtVerfuegbarException {
		return server.findArtikelByNumber(artikelNr);
	}
	@Override
	public void gibBenutzerWeiter(User aktuellerBenutzer) {
		server.gibBenutzerWeiter(aktuellerBenutzer);
	}
	@Override
	public void userLogout() {
		client.userLogout();
	}

}

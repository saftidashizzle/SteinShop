package commons;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.Kunde;
import valueobjects.Rechnung;
import valueobjects.User;
import de.root1.simon.SimonUnreferenced;
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

public interface SessionInterface extends Serializable, SimonUnreferenced {
	
	public ClientInterface getClient();

	public Object[][] gibAlleUser();

	public User userLogin(String name, char[] pw) throws LoginFehlgeschlagenException, UserIstSchonEingeloggtException;

	public List<Artikel> gibAlleArtikel();

	public List<Ereignis> gibProtokollListe();

	public Rechnung rechnungErstellen(Kunde aktuellerBenutzer);

	public void fuegeUserEin(String userName, char[] pw1, String anrede,
			String name, String str, int parseInt, String ort, String land) throws InkorrekteRegWerteException;

	public Kunde artikelInWarenkorb(int artikelNummer, int menge,Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException;

	public HashMap<Artikel, Integer> artikelMengeImWarenkorbAendern(int artikelNummer, int menge,
			Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, MitarbeiterNichtVorhandenException;

	public Kunde artikelAusWarenkorb(int artikelNummer, Kunde aktuellerBenutzer) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException, MitarbeiterNichtVorhandenException, WarenkorbIstLeerException;

	public Kunde warenkorbLeeren(Kunde aktuellerBenutzer) throws WarenkorbIstLeerException, MitarbeiterNichtVorhandenException;

	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge) throws ArtikelAngabenInkorrektException;

	public void fuegeArtikelEin(String titel, double d, User aktuellerBenutzer,
			int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException;

	public void mengeAendern(int nummer, int anzahl, User aktuellerBenutzer) throws ArtikelMengeInkorrektException, ArtikelNichtVerfuegbarException;

	public void loescheArtikel(int artikelNummer, User aktuellerBenutzer) throws ArtikelNichtVerfuegbarException;

	public void fuegeUserEin(String name, char[] pw1, String anrede,
			String vorUndZuName) throws InkorrekteRegWerteException;

	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException;

	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a);

	public Artikel findArtikelByNumber(int artikelNr) throws ArtikelNichtVerfuegbarException;

	public void gibBenutzerWeiter(User aktuellerBenutzer);

	public void userLogout();
	
}

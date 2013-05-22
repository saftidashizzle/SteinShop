package shop.local.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.ArtikelMengeReichtNichtException;
import shop.local.domain.exceptions.ArtikelNichtVerfuegbarException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
import shop.local.valueobjects.Rechnung;
import shop.local.valueobjects.User;



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
	 * Methode die einen neuen Artikel einfügt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzufügenden Artikels.
	 * @param nummer Nummer des einzufügenden Artikels.
	 * @param menge Menge des einzufügenden Artikels
	 * @param aktuellerBenutzer 
	 */
	public void fuegeArtikelEin(String titel, int menge, double d)  { // hier fehlt ArtikelExistiertBereitsException
		Artikel art = new Artikel(titel, menge, d);
		artVer.einfuegen(art);
		//erVer.ereignisEinfuegen(aktuellerBenutzer, jahrestag, artVer.getArtikelBestand().get(artVer.getArtikelBestand().size()), menge, "Artikel zum Bestand hinzugefuegt.");
	}
	/**
	 * Methode um einen User einzufügen
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName)  { // hier fehlt ArtikelExistiertBereitsException
		userVer.einfuegen(name, passwort, anrede, vorUndZuName);	
	}
	/**
	 * Methode um einen User einzufügen.
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land)  { // hier fehlt ArtikelExistiertBereitsException
		userVer.einfuegen(name, passwort, anrede, vorUndZuName, strasse, plz, ort, land);	
	}
	/**
	 * Methode die einen neuen Artikel in der Warenkorb ein und den Auftrag an die Warenkorbverwaltung weiterreicht.
	 * @param menge 
	 * @param titel Titel des einzufügenden Artikels.
	 * @param nummer Nummer des einzufügenden Artikels.
	 * @param menge Menge des einzufügenden Artikels
	 * @throws ArtikelNummerFalsch 
	 */
	public void artikelInWarenkorb(int artID, int menge, User akteur) throws ArtikelNichtVerfuegbarException, ArtikelMengeReichtNichtException {	
		Iterator<Artikel> it = gibAlleArtikel().iterator();
		// Artikel erstellen
		Artikel artikel = null;
		// Artikelverzeichnis durchlaufen
		while (it.hasNext()) {
			artikel = it.next();
			// gesuchte Artikel ID gefunden
			if(artID==artikel.getNummer()){
				// Gewollte Menge ist kleinergleich der vorhandenen Menge
				if(menge<=artikel.getMenge()){ 
					// neuen Artikel erstellen, in Warenkorb tun 
					Artikel einArtikel= new Artikel(artikel.getName(),artID, artikel.getPreis(), menge);
					warkoVer.artikelInWarenkorb(einArtikel, akteur);
					// neue Menge setzen und Ereignis loggen
					artikel.setMenge(artikel.getMenge()-menge);
					erVer.ereignisEinfuegen(akteur, jahrestag, artikel, artikel.getMenge(), "Artikel in den Warenkorb gelegt.");
				} else { // gewollte Menge ist größer als die vorhandene Menge
					ArtikelMengeReichtNichtException e = new ArtikelMengeReichtNichtException(menge, artikel.getMenge());
					throw e;
				}
			} else if (!it.hasNext() && !(artID == artikel.getNummer())){ // gesuchte Artikel ID nicht gefunden
				ArtikelNichtVerfuegbarException e = new ArtikelNichtVerfuegbarException(artID);
				throw e; 
			}
		}
	}
	/**
	 * Methode die einen artikel einliest und an die warenkorb verwaltung durchreicht
	 * @param artID Artikel ID des zu entfernenden Artikels
	 */
	public void artikelAusWarenkorb(int artID, User akteur) throws ArtikelNichtVerfuegbarException {
		Iterator<Artikel> it = gibWarenkorb(akteur).iterator();
		// Warenkorb des Users durchlaufen
		while (it.hasNext()) {
			Artikel artikel = it.next();
			// Artikel gefunden
			if(artID==artikel.getNummer()){
				Iterator<Artikel> it2 = gibAlleArtikel().iterator();
				// gesamten Artikelbestand durchlaufen
				while (it2.hasNext()) {
					// Artikel aus dem Warenkorb
					Artikel alterArtikel = it2.next();
					// Artikel aus dem Warenkorb hat die selbe Nr wie der Artikel aus dem Artikelbestand
					if(artikel.getNummer()==alterArtikel.getNummer()){
						// Aus dem Warenkorb herausgenommene Menge wieder auf den Bestand aufaddieren
						alterArtikel.setMenge(alterArtikel.getMenge()+artikel.getMenge());
						// Artikel aus Warenkorb entfernen
						warkoVer.artikelAusWarenkorb(artikel, akteur);
						// Ereignis loggen
						erVer.ereignisEinfuegen(akteur, jahrestag, artikel, artikel.getMenge(), "Artikel aus dem Warenkorb genommen.");
					}
				}
			// Artikelnr nicht im Warenkorb des Users vorhanden
			} else if (!it.hasNext()){
				ArtikelNichtVerfuegbarException e = new ArtikelNichtVerfuegbarException(artID);
				throw e; 
			}
		}
	}
	/**
	 * Methode die, die Warenkorb Liste löscht.
	 * @return Die Artikelliste.
	 */
	public void warenkorbLeeren(User akteur){
		Iterator<Artikel> it = gibWarenkorb(akteur).iterator();
		while (it.hasNext()) {
			Artikel artikel = it.next();			
				Iterator<Artikel> it2 = gibAlleArtikel().iterator();
				while (it2.hasNext()) {
					Artikel alterArtikel = it2.next();
					if(artikel.getNummer()==alterArtikel.getNummer()){
						alterArtikel.setMenge(alterArtikel.getMenge()+artikel.getMenge());
					}
				}
		}
		warkoVer.warenkorbLeeren(akteur);
	}
	/** 
	 * Methode um den Warenkorb einzukaufen.
	 * @param User aktuellerBenutzer
	 */
	public void rechnungErstellen(User akteur){
		Rechnung rechnung = new Rechnung(akteur, gibWarenkorb(akteur), 0);
		warkoVer.warenkorbLeeren(akteur);
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
	 * Methode um die Benutzerliste zurückzugeben.
	 * @return Die Benutzerliste.
	 */
	public List<Artikel> gibWarenkorb(User user){
		return warkoVer.getWarenkorb(user);
	}	

	public List<Ereignis> gibProtokoll() {
		return erVer.gibProtokoll();
	}
	
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
		// es muss auch übergeben werden, wer die änderung vollzogen hat, am besten gleich als objekt
		// am besten auch den artikel gleich als objekt übergeben
		
		// aus nummer und anzahl muss ich den rest herausfinden
		erVer.ereignisEinfuegen(akteur, jahrestag, derWars, anzahl, "Bestandsanzahl geändert.");
	}
	public void artikelNachNamenOrdnen() {
		Collections.sort(gibAlleArtikel(), new comperatorArtikelName()); 
	}
	
	public void artikelNachZahlenOrdnen() { 
		Collections.sort(gibAlleArtikel(), new comperatorArtikelNummer());
	}

	public void loescheUser(int userName, User aktuellerBenutzer) {
		userVer.loescheUser(userName, aktuellerBenutzer);
	}
}

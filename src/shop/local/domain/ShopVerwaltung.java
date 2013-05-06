package shop.local.domain;

import java.util.Iterator;
import java.util.List;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.User;


public class ShopVerwaltung {

	private ArtikelVerwaltung artVer;
	private UserVerwaltung userVer;
	private WarenkorbVerwaltung warkoVer;
	
	public ShopVerwaltung() {
		artVer = new ArtikelVerwaltung();
		userVer = new UserVerwaltung();
		warkoVer = new WarenkorbVerwaltung();
	}
	
	/**
	 * Methode die einen neuen Artikel einfügt und den Auftrag an die Artikelverwaltung weiterreicht.
	 * @param titel Titel des einzufügenden Artikels.
	 * @param nummer Nummer des einzufügenden Artikels.
	 * @param menge Menge des einzufügenden Artikels
	 */
	public void fuegeArtikelEin(String titel, int nummer, int menge)  { // hier fehlt ArtikelExistiertBereitsException
		Artikel art = new Artikel(titel, nummer, menge);
		artVer.einfuegen(art);
	}
	/**
	 * Methode um einen User einzufügen
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 */
	public void fuegeUserEin(String name, String passwort)  { // hier fehlt ArtikelExistiertBereitsException
		User user = new User(name, passwort);
		userVer.einfuegen(user);	
	}
	/**
	 * Methode um einen User einzufügen.
	 * @param name Name des einzufügenden Users.
	 * @param passwort Passwort des einzufügenden User.
	 * @param angestellt Boolean, ob der neue Nutzer ein Mitarbeiter ist. True: Mitarbeiter, False: Kunde.
	 */
	public void fuegeUserEin(String name, String passwort, boolean angestellt)  { // hier fehlt ArtikelExistiertBereitsException
		User user = new User(name, passwort, angestellt);
		userVer.einfuegen(user);	
	}
	/**
	 * Methode die einen neuen Artikel in der Warenkorb ein und den Auftrag an die Warenkorbverwaltung weiterreicht.
	 * @param menge 
	 * @param titel Titel des einzufügenden Artikels.
	 * @param nummer Nummer des einzufügenden Artikels.
	 * @param menge Menge des einzufügenden Artikels
	 */
	public void artikelInWarenkorb(int artID, int menge)  { // hier fehlt ArtikelExistiertNichtException	
		Iterator<Artikel> it = artVer.getArtikelBestand().iterator();
		while (it.hasNext()) {
			Artikel artikel = it.next();
			if(artID==artikel.getNummer()){ 
				Artikel einArtikel= new Artikel(artikel.getName(),artID, menge);
				warkoVer.einfuegen(einArtikel);
			}
		}		
		
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
	public List<Artikel> gibWarenkorb(){
		return warkoVer.getWarenkorb();
	}	

	public void mengeAendern(int nummer, int anzahl) {
		artVer.setArtikelMenge(nummer, anzahl);
	}
	
}

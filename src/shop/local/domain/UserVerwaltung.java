package shop.local.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.User;
/**
 * UserVerwaltung: Verwaltet die Userliste und fügt neue User ein.
 * 
 * @author philipp, kevin, jan
 *
 */


public class UserVerwaltung {
	private List<User> userBestand = new Vector<User>();
	private int laufnr = 0;
	/**
	 * Methode um einen neuen User in die Liste einzufügen.
	 * @param einUser der neue Nutzer der übergeben wird.
	 */
	public void einfuegen(String name, String passwort, String anrede, String vorUndZuName) {
		int nr = bestimmeNr();
		User einUser = new Mitarbeiter(name, passwort, nr, anrede, vorUndZuName);
		userBestand.add(einUser);
	}
	/**
	 * Bestimmen der laufnr eines neu angelegten Nutzers
	 * @return counter laufnr
	 */
	private int bestimmeNr() {
		int counter;
		laufnr++;
		counter = laufnr;
		return counter;
	}
	/**
	 * Methode, die die aktuelle Liste von Benutzern zurück gibt.
	 * @return gibt die Liste userBestand zurück.
	 */
	public List<User> getUserBestand() {
		return userBestand;
	}
	/**
	 * Methode zum einfuegen eines neuen Kunden
	 * @param name Benutzername
	 * @param passwort Passwort
	 * @param anrede Anrede
	 * @param vorUndZuName Vor und Nachname
	 * @param strasse Strasse
	 * @param plz Postleitzahl
	 * @param ort Ort
	 * @param land Land
	 */
	public void einfuegen(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) {
		int nr = bestimmeNr();
		User einUser = new Kunde(name, passwort, nr, anrede, vorUndZuName, strasse, plz, ort, land);
		userBestand.add(einUser);		
	}
	/**
	 * Methode zum Löschen eines Users
	 * @param userName ID der Nutzerkennung
	 * @param aktuellerBenutzer aktueller Benutzer
	 */
	public void loescheUser(int userName, User aktuellerBenutzer) {
		/*
		erst den eingegebenen int abgleichen und user bestimmen
		*/
		userBestand.remove(--userName);
	}
	/**
	 * Methode zum ausgeben der Benutzerliste auf der Console
	 */
	public void gibBenutzerlisteAus() {
		if(userBestand.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<User> it = userBestand.iterator();
			while (it.hasNext()) {
				User user = it.next();
				System.out.println(user.toString());
			}			
		}
		System.out.println(" ");
	}

}

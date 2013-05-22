package shop.local.domain;

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
	public void einfuegen(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) {
		int nr = bestimmeNr();
		User einUser = new Kunde(name, passwort, nr, anrede, vorUndZuName, strasse, plz, ort, land);
		userBestand.add(einUser);		
	}
	public void loescheUser(int userName, User aktuellerBenutzer) {
		/*
		erst den eingegebenen int abgleichen und user bestimmen
		*/
		userBestand.remove(++userName);
	}

}

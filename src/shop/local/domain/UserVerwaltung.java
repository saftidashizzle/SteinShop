package shop.local.domain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.User;
/**
 * UserVerwaltung: Verwaltet die Userliste und f�gt neue User ein.
 * 
 * @author philipp, kevin, jan
 *
 */


public class UserVerwaltung implements Serializable {
	private List<User> userBestand = new Vector<User>();
	private int laufnr = 0;
	/**
	 * Methode um einen neuen User in die Liste einzuf�gen.
	 * @param einUser der neue Nutzer der �bergeben wird.
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
	 * Methode, die die aktuelle Liste von Benutzern zur�ck gibt.
	 * @return gibt die Liste userBestand zur�ck.
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
		userBestand.remove(--userName);
	}
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
	public void schreibeDaten() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UserVerwaltung.ser")); 
		out.writeObject(this);		
		out.close();
	}	

}

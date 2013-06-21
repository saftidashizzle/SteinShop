package shop.local.domain;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.InkorrekteRegWerteException;
import shop.local.domain.exceptions.LoginFehlgeschlagenException;
import shop.local.domain.exceptions.MitarbeiterNichtVorhandenException;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.User;
/**
 * UserVerwaltung: Verwaltet die Userliste und fügt neue User ein.
 * 
 * @author philipp, kevin, jan
 *
 */


public class UserVerwaltung implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8982487175917305106L;
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
	public void einfuegen(String name, String passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) throws InkorrekteRegWerteException{
		if(plz>99999 || plz<10000){
			throw new InkorrekteRegWerteException();
		}
		else {
			int nr = bestimmeNr();
			User einUser = new Kunde(name, passwort, nr, anrede, vorUndZuName, strasse, plz, ort, land);
			userBestand.add(einUser);
		}
	}
	public void loescheUser(int userName, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException{
		if(findUserByNumber(userName)!=null){
			userBestand.remove(--userName);
		}		
		else{
			throw new MitarbeiterNichtVorhandenException();
		}
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
	public void schreibeDaten2() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("User.ser")); 
		// hier schleife in der dir jeweiligen objekte (artikel, user, ereignisse durchgegangen werden
		
		Iterator<User> it = userBestand.iterator();
		// Artikel erstellen
		User user = null;
		// Artikelverzeichnis durchlaufen
		int count = 0;
		while (it.hasNext()) {
			user = it.next();
			// artikel in Datei speichern
			out.writeObject(user);
			count ++;
		}
		System.out.println(count + " User gespeichert.");
		// muss aufgerufen werden, bevor der datenstrom zur eingabe verwendet werden soll
		out.close();
	}
	public User findUserByNumber(int artID) throws MitarbeiterNichtVorhandenException {
		Iterator<User> it = userBestand.iterator();
		// Artikel erstellen
		User user = null;
		// Artikelverzeichnis durchlaufen
		while (it.hasNext()) {
			user = it.next();
			// gesuchte Artikel ID gefunden
			if(artID==user.getNummer()){
				return user;				
			} else if (!(artID==user.getNummer())&&!it.hasNext()){ // gesuchte Artikel ID nicht gefunden
				throw new MitarbeiterNichtVorhandenException(); 
			}
		}
		return null;
	}
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		int count = 0;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("User.ser"));
		userBestand.clear();
		try {  
			User u = null;
			for(;;) {
				u = (User) in.readObject();
				count++;
				userBestand.add(u);
				if (u.getNummer() > this.laufnr)
					this.laufnr = u.getNummer();
			}
		} catch (EOFException e) { // wg. readObject
			System.out.println("Es wurden " + count + " User geladen.");
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) { // wg readObject
			System.out.println(e);
		} finally {
			try {
				if (in!=null) {
					in.close();
				} 
			} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	public User userLogin(String name, String passwort) throws LoginFehlgeschlagenException {
		Iterator<User> it = userBestand.iterator();
		while  (it.hasNext()) {
			User user = it.next();
			if(user.getName().equals(name) && (user.getPasswort().equals(passwort))){
				return user;
			}
		}
		throw new LoginFehlgeschlagenException();
	}	

}

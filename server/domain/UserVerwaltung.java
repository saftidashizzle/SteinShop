package domain;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import valueobjects.Kunde;
import valueobjects.Mitarbeiter;
import valueobjects.User;
import domain.exceptions.InkorrekteRegWerteException;
import domain.exceptions.LoginFehlgeschlagenException;
import domain.exceptions.MitarbeiterNichtVorhandenException;
/**
 * UserVerwaltung: Verwaltet die Userliste und fügt neue User ein.
 * 
 * @author philipp, kevin, jan
 *
 */


public class UserVerwaltung implements Serializable {
	
	private static final long serialVersionUID = -8982487175917305106L;
	private List<User> userBestand = new Vector<User>();
	private int laufnr = 0;
	/**
	 * Methode um einen neuen User in die Liste einzufügen.
	 * @param einUser der neue Nutzer der übergeben wird.
	 */
	public void einfuegen(String name, char[] passwort, String anrede, String vorUndZuName) {
		int nr = bestimmeNr();
		User einUser = new Mitarbeiter(name, passwort, nr, anrede, vorUndZuName);
		userBestand.add(einUser);
	}
	/**
	 * Methode, die eine fortlaufende Nummer vergibt
	 * @return laufnr
	 */
	private int bestimmeNr() {
		return ++laufnr;
	}
	/**
	 * Methode, die die aktuelle Liste von Benutzern zurück gibt.
	 * @return gibt die Liste userBestand zurück.
	 */
	public List<User> getUserBestand() {
		return userBestand;
	}
	/**
	 * Methode, die einen Kunden hinzufügt
	 * @param name
	 * @param passwort
	 * @param anrede
	 * @param vorUndZuName
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param land
	 * @throws InkorrekteRegWerteException
	 */
	public void einfuegen(String name, char[] passwort, String anrede, String vorUndZuName, String strasse, int plz, String ort, String land) throws InkorrekteRegWerteException{
		if(plz>99999 || plz<10000){
			throw new InkorrekteRegWerteException();
		}
		else {
			int nr = bestimmeNr();
			User einUser = new Kunde(name, passwort, nr, anrede, vorUndZuName, strasse, plz, ort, land);
			userBestand.add(einUser);
		}
	}
	/**
	 * Methode, um einen Useraccount zu löschen
	 * @param userNr
	 * @param aktuellerBenutzer
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public void loescheUser(int userNr, User aktuellerBenutzer) throws MitarbeiterNichtVorhandenException{
		if(findUserByNumber(userNr)!=null){
			userBestand.remove(findUserByNumber(userNr));
		}		
		else{
			throw new MitarbeiterNichtVorhandenException(userNr);
		}
	}
	/**
	 * Methode, um die Benutzerliste auszugeben
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
	/**
	 * Methode, zum Serialisieren/Speichern der Daten
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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
	/**
	 * Methode, um einen Benutzer nach seiner ID zu finden
	 * @param ID
	 * @return
	 * @throws MitarbeiterNichtVorhandenException
	 */
	public User findUserByNumber(int ID) throws MitarbeiterNichtVorhandenException {
		Iterator<User> it = userBestand.iterator();
		// Artikel erstellen
		User user = null;
		// Artikelverzeichnis durchlaufen
		while (it.hasNext()) {
			user = it.next();
			// gesuchter User gefunden
			if(ID==user.getNummer()){
				return user;				
			} else if (!(ID==user.getNummer())&&!it.hasNext()){ // gesuchte Artikel ID nicht gefunden
				throw new MitarbeiterNichtVorhandenException(ID); 
			}
		}
		return null;
	}
	/**
	 * Methode, zum Laden der serialisierten/gespeicherten Daten
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
	/**
	 * Methode, zum eingloggen
	 * @param name
	 * @param passwort
	 * @return
	 * @throws LoginFehlgeschlagenException
	 */
	public User userLogin(String name, char[] passwort) throws LoginFehlgeschlagenException {
		Iterator<User> it = userBestand.iterator();
		while  (it.hasNext()) {
			User user = it.next();
			if(user.getName().equals(name)){
				if (Arrays.equals(user.getPasswort(),passwort)) {
					return user;
				}
			}
		}
		throw new LoginFehlgeschlagenException();
	}
}

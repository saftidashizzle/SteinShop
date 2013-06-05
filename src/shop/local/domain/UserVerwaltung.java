package shop.local.domain;

import java.io.BufferedInputStream;
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

import shop.local.valueobjects.Artikel;
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
		System.out.println("UserVerwaltung gespeichert.");
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
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		int count = 0;
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("User.ser")));
		userBestand.clear();
		try {  
			User u = null;
			for(;;) {
				u = (User) in.readObject();
				count++;
				userBestand.add(u);
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

}

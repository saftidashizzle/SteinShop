package shop.local.domain;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
import shop.local.valueobjects.User;

public class EreignisVerwaltung {
	private List<Ereignis> protokoll = new Vector<Ereignis>();

	public void ereignisEinfuegen(User akteur, Artikel derWars, int anzahl, String aktion) {
		Ereignis ereignis = new Ereignis(akteur, derWars, anzahl, aktion);
		protokoll.add(ereignis);
	}
	/**
	 * Methode die, alle Elemente des Protokolls in der Konsole ausgibt.
	 * @param liste
	 */
	public void gibProtokollAus() {
		if(protokoll.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<Ereignis> it = protokoll.iterator();
			while (it.hasNext()) {
				Ereignis ereignis = it.next();
				System.out.println(ereignis.toString());
			}			
		}
		System.out.println(" ");
	}
	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a, int anzahlTage) {
		return null;
	}
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		int count = 0;
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Ereignisse.ser")));
		protokoll.clear();
		try {  
			Ereignis er = null;
			for(;;) {
				er = (Ereignis) in.readObject();
				count++;
				protokoll.add(er);
			}
		} catch (EOFException e) { // wg. readObject
			System.out.println("Es wurden " + count + " Ereignisse geladen.");
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
	public void schreibeDaten() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Ereignisse.ser")); 
		// hier schleife in der dir jeweiligen objekte (artikel, user, ereignisse durchgegangen werden
		
		Iterator<Ereignis> it = protokoll.iterator();
		// Artikel erstellen
		Ereignis er = null;
		// Artikelverzeichnis durchlaufen
		int count = 0;
		while (it.hasNext()) {
			er = it.next();
			// artikel in Datei speichern
			out.writeObject(er);
			count ++;
		}
		System.out.println(count + " Ereignisse gespeichert.");
		// muss aufgerufen werden, bevor der datenstrom zur eingabe verwendet werden soll
		out.close();
	}
}

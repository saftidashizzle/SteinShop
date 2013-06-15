package shop.local.domain;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
		// jetzt fehlt noch die zeit komponente
		// in einer schleife alle date's der ereignisse nacheinander durchgehen
		// mit .getDay den Tag bekommen
		// gucken, sobald ein monat (zum test eine woche) vergangen ist, dann mit dem ausgeben aufhoeren
		List<Ereignis> liste = null;
		Iterator<Ereignis> it = protokoll.iterator();
		Ereignis er = null;

		Calendar heute = Calendar.getInstance();
		Calendar ereignis = new GregorianCalendar();
		
		long zeitVergangen = ereignis.getTime().getTime() - heute.getTime().getTime();  // Differenz in ms
		long inTagen = Math.round( (double)zeitVergangen / (24. * 60.*60.*1000.) ); // Zeit Differenz in Tagen
		
			while (it.hasNext()) {
				er = it.next();
				ereignis.setTime(er.getDate());                      // zweiter Zeitpunkt
				// wenn ereignis in dem zeitraum liegt, in die liste
				if(inTagen>30){
					return liste;
				} else {
					liste.add(er); 
				}
			}
			return null;
			
	
	}
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		int count = 0;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Ereignisse.ser"));
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
		// Ereignisse durchlaufen
		int count = 0;
		while (it.hasNext()) {
			er = it.next();
			// Ereignis in Datei speichern
			out.writeObject(er);
			count ++;
		}
		System.out.println(count + " Ereignisse gespeichert.");
		// muss aufgerufen werden, bevor der datenstrom zur eingabe verwendet werden soll
		out.close();
	}
	public List<Ereignis> gibProtokollListe() {
		return protokoll;
	}
}

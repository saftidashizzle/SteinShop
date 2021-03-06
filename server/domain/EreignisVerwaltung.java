package domain;


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

import valueobjects.Artikel;
import valueobjects.Ereignis;
import valueobjects.User;
/**
 * Klasse zur Verwaltung der Ereignisse.
 *
 */
public class EreignisVerwaltung {
	private List<Ereignis> protokoll = new Vector<Ereignis>();
	/**
	 * Methode, die ein Ereignis einf�gt
	 * @param akteur Nutzer der T�tigkeit
	 * @param derWars Benutzer Artikel
	 * @param anzahl
	 * @param aktion Durchgef�hrte Aktion
	 */
	public void ereignisEinfuegen(User akteur, Artikel derWars, int anzahl, String aktion) {
		Ereignis ereignis = new Ereignis(akteur, derWars, anzahl, aktion);
		protokoll.add(ereignis);
	}
	/**
	 * Methode die, alle Elemente des Protokolls in der Konsole ausgibt.
	 * @param liste Protokoll
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
	/**
	 * Methode, die die Ereignisliste ausgibt
	 * @param a Artikel
	 * @return Ereignisliste
	 */
	public List<Ereignis> gibEreignisseNachArtikelUndTagen(Artikel a) {
	// anzahl tage wird nicht benutzt
		List<Ereignis> liste = new Vector<Ereignis>();

		Calendar heute = Calendar.getInstance();
		Calendar ereignis = new GregorianCalendar();		
		
		// hier werden alle ereignisse aus protokoll durchgegangen
		for(Ereignis e:protokoll) {
			ereignis.setTime(e.getDate());                      // zweiter Zeitpunkt
			long zeitVergangen = ereignis.getTime().getTime() - heute.getTime().getTime();  // Differenz in ms
			long inTagen = Math.round( (double)zeitVergangen / (24. * 60.*60.*1000.) ); // Zeit Differenz in Tagen
			// wenn ereignis in dem zeitraum liegt, in die liste
			// bedingung bewirkt das nichts, das laenger als 30 tage zurueck liegt in die liste gepackt wird
			if(inTagen>30){
				return liste;
			} else {
				if(e.getArtikel().getNummer()==a.getNummer()) {
					liste.add(e);
				} 
			}
		}	
		return liste;
			
	
	}
	/**
	 * Methode, zum Laden der serialisierten/gespeicherten Daten
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
	/**
	 * Methode, zum Serialisieren/Speichern der Daten
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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
	/**
	 * Gibt das Protokoll zur�ck.
	 * @return protokoll Liste von Ereignissen
	 */
	public List<Ereignis> gibProtokollListe() {
		return protokoll;
	}
}

package shop.local.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Ereignis;
import shop.local.valueobjects.User;

public class EreignisVerwaltung {
	private List<Ereignis> protokoll = new Vector<Ereignis>();

	public void ereignisEinfuegen(User akteur, int datum, Artikel derWars, int anzahl, String aktion) {
		Ereignis ereignis = new Ereignis(akteur, datum, derWars, anzahl, aktion);
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
}

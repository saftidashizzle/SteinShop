package shop.local.valueobjects;

import java.io.IOException;
import java.util.HashMap;

public class Warenkorb {

	private HashMap<Artikel, Integer> warenkorb = new HashMap<Artikel, Integer>();
	/**
	 * Methode, die einen Artikel in den Warenkorb hinzufügt
	 * @param a ausgewählter Artikel
	 * @param menge anzahl des ausgewählten Artikels
	 */
	public void artikelHinzufuegen(Artikel a, int menge) {
		// Wenn Artikel schon im Warenkorb vorhanden dann Menge erweitern
		if (warenkorb.containsKey(a)) {
			int alteMenge = warenkorb.get(a);
			warenkorb.put(a, alteMenge + menge);
		} else {
		// Ansonsten neu in Liste hinzufügen
			warenkorb.put(a, menge);
		}
	}	
	/**
	 * Methode, die einen Artikel aus dem Warenkorb löscht
	 * @param a ausgewählter Artikel
	 */
	public void artikelEntfernen(Artikel a) {
		warenkorb.remove(a);
	}
	/**
	 * Methode die, die Liste leert.
	 */
	public void leeren() {
		warenkorb.clear();
	}
	/**
	 * Methode die den Inhalt des Warenkobs zurückgibt.
	 * @return warenkorb
	 */
	public HashMap<Artikel, Integer> getInhalt(){
		return warenkorb;
	}
	
}

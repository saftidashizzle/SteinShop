package shop.local.valueobjects;

import java.io.IOException;
import java.util.HashMap;

import shop.local.domain.WarenkorbExceedsArtikelbestandException;

public class Warenkorb {

	private HashMap<Artikel, Integer> warenkorb = new HashMap<Artikel, Integer>();
	/**
	 * Methode, die einen Artikel in den Warenkorb hinzuf�gt
	 * @param a ausgew�hlter Artikel
	 * @param menge anzahl des ausgew�hlten Artikels
	 * @throws WarenkorbExceedsArtikelbestandException 
	 */
	public void artikelHinzufuegen(Artikel a, int gewuenschteMenge, int mengeNochDa) throws WarenkorbExceedsArtikelbestandException {
		// Wenn Artikel schon im Warenkorb vorhanden dann Menge erweitern
		if (warenkorb.containsKey(a)) {
			int alteMenge = warenkorb.get(a);
			if (alteMenge+gewuenschteMenge>mengeNochDa) {
				warenkorb.put(a, alteMenge + gewuenschteMenge);	
			} else {
				throw new WarenkorbExceedsArtikelbestandException();
			}
		} else {
		// Ansonsten neu in Liste hinzuf�gen
			warenkorb.put(a, gewuenschteMenge);
		}
	}	
	/**
	 * Methode, die einen Artikel aus dem Warenkorb l�scht
	 * @param a ausgew�hlter Artikel
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
	 * Methode die den Inhalt des Warenkobs zur�ckgibt.
	 * @return warenkorb
	 */
	public HashMap<Artikel, Integer> getInhalt(){
		return warenkorb;
	}
	
}

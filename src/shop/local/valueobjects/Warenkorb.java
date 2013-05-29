package shop.local.valueobjects;

import java.util.HashMap;

import shop.local.domain.exceptions.WarenkorbExceedsArtikelbestandException;

public class Warenkorb {

	private HashMap<Artikel, Integer> warenkorb = new HashMap<Artikel, Integer>();
	/**
	 * Methode, die einen Artikel in den Warenkorb hinzufügt
	 * @param a ausgewählter Artikel
	 * @param menge anzahl des ausgewählten Artikels
	 * @throws WarenkorbExceedsArtikelbestandException 
	 */
	public void artikelHinzufuegen(Artikel a, int gewuenschteMenge, int mengeNochDa) throws WarenkorbExceedsArtikelbestandException {
		// Wenn Artikel schon im Warenkorb vorhanden dann Menge erweitern
		if (warenkorb.containsKey(a)) {
			int alteMenge = warenkorb.get(a);
			if (alteMenge+gewuenschteMenge<=mengeNochDa) {
				System.out.println("Artikel im Warenkorb");
				warenkorb.put(a, alteMenge + gewuenschteMenge);	
			} else {
				throw new WarenkorbExceedsArtikelbestandException();
			}
		} else {
		// Ansonsten neu in Liste hinzufügen
			warenkorb.put(a, gewuenschteMenge);
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

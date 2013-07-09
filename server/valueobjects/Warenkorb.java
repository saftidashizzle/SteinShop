package valueobjects;

import java.io.Serializable;
import java.util.HashMap;

import domain.exceptions.WarenkorbExceedsArtikelbestandException;
import domain.exceptions.WarenkorbIstLeerException;

public class Warenkorb implements Serializable {

	/**
	 * Klasse für den Warenkorb
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Artikel, Integer> warenkorb = new HashMap<Artikel, Integer>();
	/**
	 * Methode, die einen Artikel in den Warenkorb hinzufügt
	 * @param a ausgewählter Artikel
	 * @param menge anzahl des ausgewählten Artikels
	 * @throws WarenkorbExceedsArtikelbestandException 
	 */
	public void artikelHinzufuegen(Artikel a, int gewuenschteMenge, int mengeNochDa) throws WarenkorbExceedsArtikelbestandException {
		// Wenn Artikel schon im Warenkorb vorhanden dann Menge erweitern
		if (warenkorb.containsKey(a)) { // WArenkorb hat hier nichtr den gewünschten artikel drin
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
	 * @throws WarenkorbIstLeerException 
	 */
	public void artikelEntfernen(Artikel a) throws WarenkorbIstLeerException {
		if(warenkorb.containsKey(a)) {
			warenkorb.remove(a);
		} else {
			throw new WarenkorbIstLeerException();
		}
	}
	/**
	 * Methode die, die Liste leert.
	 */
	public void leeren() throws WarenkorbIstLeerException {
		if(!warenkorb.isEmpty()){
			warenkorb.clear();
		}
		else{
			throw new WarenkorbIstLeerException();
		}
	}
	/**
	 * Methode die den Inhalt des Warenkobs zurück gibt.
	 * @return warenkorb
	 */
	public HashMap<Artikel, Integer> getInhalt(){
		return warenkorb;
	}
	/** Methode um denn Warenkorb zu setzen
	 * 
	 */
	public void setWarenkorb(HashMap<Artikel, Integer> w) {
		warenkorb = w;
	}
}

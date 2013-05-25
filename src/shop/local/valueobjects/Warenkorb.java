package shop.local.valueobjects;

import java.util.HashMap;

public class Warenkorb {

	private HashMap<Artikel, Integer> warenkorb = new HashMap<Artikel, Integer>();

	public void artikelHinzufuegen(Artikel a, int menge) {
		if (warenkorb.containsKey(a)) {
			int alteMenge = warenkorb.get(a);
			warenkorb.put(a, alteMenge + menge);
		} else {
			warenkorb.put(a, menge);
		}
	}	
	public void artikelEntfernen(Artikel a) {
		warenkorb.remove(a);
	}
	
	public void leeren() {
		warenkorb.clear();
	}
	public HashMap<Artikel, Integer> getInhalt(){
		return warenkorb;
	}
	
}

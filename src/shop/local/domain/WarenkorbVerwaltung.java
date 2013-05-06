package shop.local.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Artikel;

public class WarenkorbVerwaltung {
	private List<Artikel> warenkorb = new Vector<Artikel>();
	
	/**
	 * Methode um einen neuen Artikel in die Liste (warenkorb) einzufügen.
	 * @param einArtikel der Artikel der eingefügt werden soll.
	 */
	public void einfuegen(Artikel einArtikel) {
		boolean vorhanden=false;
		Iterator<Artikel> it = warenkorb.iterator();
		if(!it.hasNext()) {
			warenkorb.add(einArtikel);
		}
		else{
			while (it.hasNext()) {
				Artikel artikel = it.next();
				if(einArtikel.getNummer()==artikel.getNummer()){
					artikel.setMenge(artikel.getMenge()+einArtikel.getMenge());
					vorhanden= true;
				}
			}
			if (!vorhanden) {
				warenkorb.add(einArtikel);
			}
		}
		
	}
	/**
	 * Methode die die aktuelle Artikelliste zurückgibt
	 * @return die aktuelle Artikelliste.
	 */
	public List<Artikel> getWarenkorb() {
		return warenkorb;
	}
	
}

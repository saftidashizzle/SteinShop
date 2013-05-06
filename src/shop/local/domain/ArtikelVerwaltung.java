package shop.local.domain;

import java.util.List;
import java.util.Vector;

import shop.local.valueobjects.Artikel;

public class ArtikelVerwaltung {

	private List<Artikel> artikelBestand = new Vector<Artikel>();
	
	/**
	 * Methode um einen neuen Artikel in die Liste einzuf�gen.
	 * @param einArtikel der Artikel der eingef�gt werden soll.
	 */
	public void einfuegen(Artikel einArtikel) {
		
		artikelBestand.add(einArtikel);
	}
	/**
	 * Methode die die aktuelle Artikelliste zur�ckgibt
	 * @return die aktuelle Artikelliste.
	 */
	public List<Artikel> getArtikelBestand() {
		return artikelBestand;
	}
	/**
	 * Methode um die Menge eines Artikels zu �ndern.
	 * @param nummer Artikelnummer des zu �ndernden Artikels.
	 * @param anzahl Wieviel hinzugef�gt werden soll.
	 */
	public void setArtikelMenge(int nummer, int anzahl) {
		
		Iterator<Artikel> it = artikelBestand.iterator();
		while  (it.hasNext()) {
			Artikel artikel = it.next();
			if(artikel.getNummer() == nummer){
				artikel.setMenge(anzahl + artikel.getMenge());
			} else {
				System.out.println("Artikelnummer nicht vorhanden.");
			}
		}
	}
}

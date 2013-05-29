package shop.local.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.ArtikelNichtVerfuegbarException;
import shop.local.valueobjects.Artikel;


public class ArtikelVerwaltung {

	private List<Artikel> artikelBestand = new Vector<Artikel>();
	private int laufnr = 0;
	/**
	 * Methode um einen neuen Artikel in die Liste einzufügen.
	 * @param titel: Name des Artikels der eingefuegt werden soll.
	 * @param preis Preis
	 * @param menge Menge
	 */
	public Artikel einfuegen(String titel, double preis, int menge) throws WarenkorbExceedsArtikelbestandException { 
		int nr = bestimmeNr();
		Artikel einArtikel = new Artikel(titel, nr, preis, menge);
		artikelBestand.add(einArtikel);
		return einArtikel;
	}
	/**
	 * Methode die die aktuelle Artikelliste zurückgibt
	 * @return die aktuelle Artikelliste.
	 */
	public List<Artikel> getArtikelBestand() {
		return artikelBestand;
	}
	/**
	 * Methode um die Menge eines Artikels zu ändern.
	 * @param nummer Artikelnummer des zu ändernden Artikels.
	 * @param anzahl Wieviel hinzugefügt werden soll.
	 */
	public void setArtikelMenge(int nummer, int anzahl) {
		Iterator<Artikel> it = artikelBestand.iterator();
		while  (it.hasNext()) {
			Artikel artikel = it.next();
			if(artikel.getNummer() == nummer){
				artikel.setMenge(anzahl + artikel.getMenge());
			} 
		}
	}
	/**
	 * findet einen nach der ArtikelId gesuchten Artikel und gibt diesen zurück
	 * @param artID Artikel Id des zu suchenden Artikels
	 * @return Artikel gibt den Artikel zurück
	 * @throws ArtikelNichtVerfuegbarException
	 */
	public Artikel findArtikelByNumber(int artID) throws ArtikelNichtVerfuegbarException {
		Iterator<Artikel> it = artikelBestand.iterator();
		// Artikel erstellen
		Artikel artikel = null;
		// Artikelverzeichnis durchlaufen
		while (it.hasNext()) {
			artikel = it.next();
			// gesuchte Artikel ID gefunden
			if(artID==artikel.getNummer()){
				return artikel;				
			} else if (!(artID==artikel.getNummer())&&!it.hasNext()){ // gesuchte Artikel ID nicht gefunden
				throw new ArtikelNichtVerfuegbarException(artID); 
			}
		}
		return null;
	}
	/**
	 * gibt die Artikelliste aus
	 */
	public void gibArtikellisteAus(){		
		if(artikelBestand.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			Iterator<Artikel> it = artikelBestand.iterator();
			while (it.hasNext()) {
				Artikel artikel = it.next();
				if (!(artikel.getMenge() == 0)) {
					System.out.println(artikel.toString());
				}
			}			
		}
		System.out.println(" ");
	}
	/**
	 * gibt die Aktuelle Nr die für neu angelegte Artikel verwendet werden soll zurück
	 * @return laufnr zuverwendende Artikel Id
	 */
	private int bestimmeNr() {
		int counter;
		laufnr++;
		counter = laufnr;
		return counter;
	}
	
}

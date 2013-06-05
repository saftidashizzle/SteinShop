package shop.local.domain;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.ArtikelAngabenInkorrektException;
import shop.local.domain.exceptions.ArtikelMengeInkorrektException;
import shop.local.domain.exceptions.ArtikelNichtVerfuegbarException;
import shop.local.domain.exceptions.WarenkorbExceedsArtikelbestandException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.MehrfachArtikel;


public class ArtikelVerwaltung implements Serializable {

	private List<Artikel> artikelBestand = new Vector<Artikel>();
	private int laufnr;
	/**
	 * Methode um einen neuen Artikel in die Liste einzufügen.
	 * @param titel: Name des Artikels der eingefuegt werden soll.
	 * @param preis Preis
	 * @param menge Menge
	 */
	public Artikel einfuegen(String titel, double preis, int menge) throws ArtikelAngabenInkorrektException{ 
		if(preis>0||menge>0){
			throw new ArtikelAngabenInkorrektException();
		}
		else{
			int nr = bestimmeNr();
			Artikel einArtikel = new Artikel(titel, nr, preis, menge);
			artikelBestand.add(einArtikel);
			return einArtikel;
		}
	}
	/**
	 * Methode um einen neuen Artikel in die Liste einzufügen.
	 * @param titel: Name des Artikels der eingefuegt werden soll.
	 * @param preis Preis
	 * @param menge Menge
	 */
	public MehrfachArtikel einfuegen(String titel, double preis, int menge, int packungsGroesse) throws ArtikelAngabenInkorrektException{ 
		if(preis<=0||menge<=0){
			throw new ArtikelAngabenInkorrektException();
		}
		else{
			int nr = bestimmeNr();
			MehrfachArtikel einArtikel = new MehrfachArtikel(titel, nr, preis, menge, packungsGroesse);
			artikelBestand.add(einArtikel);
			return einArtikel;
		}
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
	 * int anzahl wird hinzugefügt
	 * @param nummer Artikelnummer des zu ändernden Artikels.
	 * @param anzahl Wieviel hinzugefügt werden soll.
	 */
	public void setArtikelMenge(int nummer, int anzahl) throws ArtikelMengeInkorrektException{
		Iterator<Artikel> it = artikelBestand.iterator();
		while  (it.hasNext()) {
			Artikel artikel = it.next();
			if(artikel.getNummer() == nummer){
				artikel.setMenge(artikel.getMenge() + anzahl);
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
				if (!(artikel.getMenge() <= 0)) {
					System.out.println(artikel.toString());
				} else {
					artikel = null;
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
	public void schreibeDaten() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Artikel.ser")); 
		// hier schleife in der dir jeweiligen objekte (artikel, user, ereignisse durchgegangen werden
		
		Iterator<Artikel> it = artikelBestand.iterator();
		// Artikel erstellen
		Artikel artikel = null;
		// Artikelverzeichnis durchlaufen
		int count = 0;
		while (it.hasNext()) {
			artikel = it.next();
			// artikel in Datei speichern
			out.writeObject(artikel);
			count ++;
		}
		System.out.println(count + " Artikel gespeichert.");
		// muss aufgerufen werden, bevor der datenstrom zur eingabe verwendet werden soll
		out.close();
	}
	public void ladeDaten() throws FileNotFoundException, IOException, ClassNotFoundException {
		int count = 0;
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Artikel.ser")));
		artikelBestand.clear();
		try {  
			Artikel a = null;
			for(;;) {
				a = (Artikel) in.readObject();
				count++;
				artikelBestand.add(a);
				this.laufnr = count;
			}
		} catch (EOFException e) { // wg. readObject
			System.out.println("Es wurden " + count + " Artikel geladen.");
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
//	public void schreibeDaten2() throws FileNotFoundException, IOException {
//		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ArtikelVerwaltung.ser")); 
//		out.writeObject(this);		
//		out.close();
//		System.out.println("Artikel Verwaltung gespeichert.");
//	}
}
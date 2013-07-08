package domain;

import java.util.HashMap;

import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.MehrfachArtikel;
import valueobjects.User;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNurInEinheitenVerfügbarException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;

public class WarenkorbVerwaltung {	
	/**
	 * Methode um einen neuen Artikel in die Liste (warenkorb) einzufügen.
	 * @param einArtikel der Artikel der eingefügt werden soll.
	 * @return 
	 * @throws ArtikelMengeReichtNichtException 
	 */
	public Kunde artikelInWarenkorb(Artikel einArtikel, int menge, Kunde k) throws ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerfügbarException {		
		if(menge<=einArtikel.getMenge()){
			if (einArtikel instanceof MehrfachArtikel) {
				MehrfachArtikel b = (MehrfachArtikel) einArtikel;
				int packungsGroesse = b.getPackungsgroesse();
				if (menge % packungsGroesse == 0) {
					k.getWarenkorb().artikelHinzufuegen(einArtikel, menge, einArtikel.getMenge());
					
					return k;
				} else {
					throw new ArtikelNurInEinheitenVerfügbarException(packungsGroesse);
				}
			} else {
				k.getWarenkorb().artikelHinzufuegen(einArtikel, menge, einArtikel.getMenge());
				return k;
			}
		} else { // gewollte Menge ist größer als die vorhandene Menge
			ArtikelMengeReichtNichtException e = new ArtikelMengeReichtNichtException(menge, einArtikel.getMenge());
			throw e;
		}
	}
	/**
	 * Methode um einen Artikel aus der Liste (warenkorb) zu löschen.
	 * @param einArtikel der Artikel der rausgenommen werden soll.
	 */
	public void artikelAusWarenkorb(Artikel artikel, Kunde user){		
		user.getWarenkorb().artikelEntfernen(artikel);
	}
	/**
	 * Methode um die Menge eines Artikels im Warenkorb zu ändern.
	 * @param nummer Artikelnummer des zu ändernden Artikels.
	 * @param anzahl Wieviel hinzugefügt werden soll.
	 * @return 
	 */
	public HashMap<Artikel, Integer> setArtikelMenge(Artikel a, int anzahl, Kunde user) {
		HashMap<Artikel, Integer> w = user.getWarenkorb().getInhalt();
		int alteMenge = w.remove(a);
		w.put(a, alteMenge+anzahl);
		return w;
	}
	/**
	 * Methode um den Warenkorbinhalt auszugeben.
	 * @param user Der User dessen Warenkorb ausgegeben werden soll.
	 */
	public void getWarenkorbInhalt(User user){
		HashMap<Artikel, Integer> warenkorb = ((Kunde) user).getWarenkorb().getInhalt();
		//wenn warenkorb leer ist
		if(warenkorb.isEmpty()){
			System.out.println("Warenkorb ist leer.");
		}
		else{
		//die einzelnen Elemente aus der HashMap durchgehen und ausgeben
			for(Artikel key : warenkorb.keySet())
		    {
		      System.out.print(key.getNummer() + " | " + key.getName() + " | " + key.getPreis() + " | Anzahl: " + warenkorb.get(key) + " | Gesamtpreis: " + key.getPreis()*warenkorb.get(key) + "\n");
		    }
		}
	}
}

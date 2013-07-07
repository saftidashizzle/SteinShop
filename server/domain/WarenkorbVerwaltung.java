package domain;

import java.util.HashMap;

import valueobjects.Artikel;
import valueobjects.Kunde;
import valueobjects.MehrfachArtikel;
import valueobjects.User;
import domain.exceptions.ArtikelMengeReichtNichtException;
import domain.exceptions.ArtikelNurInEinheitenVerf�gbarException;
import domain.exceptions.WarenkorbExceedsArtikelbestandException;

public class WarenkorbVerwaltung {	
	/**
	 * Methode um einen neuen Artikel in die Liste (warenkorb) einzuf�gen.
	 * @param einArtikel der Artikel der eingef�gt werden soll.
	 * @throws ArtikelMengeReichtNichtException 
	 */
	public void artikelInWarenkorb(Artikel einArtikel, int menge, User user) throws ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerf�gbarException {		
		// neuen Artikel erstellen, in Warenkorb tun 
		//Artikel einArtikel= new Artikel(artikel.getName(),artID, artikel.getPreis(), menge);
		//warkoVer.artikelInWarenkorb(einArtikel, akteur);
		// neue Menge setzen und Ereignis loggen
		//artikel.setMenge(artikel.getMenge()-menge);
		//erVer.ereignisEinfuegen(akteur, jahrestag, artikel, artikel.getMenge(), "Artikel in den Warenkorb gelegt.");
		if (user instanceof Kunde) {
			Kunde k = (Kunde) user;
			if(menge<=einArtikel.getMenge()){
				if (einArtikel instanceof MehrfachArtikel) {
					MehrfachArtikel b = (MehrfachArtikel) einArtikel;
					int packungsGroesse = b.getPackungsgroesse();
					if (menge % packungsGroesse == 0) {
						k.getWarenkorb().artikelHinzufuegen(einArtikel, menge, einArtikel.getMenge());
					} else {
						throw new ArtikelNurInEinheitenVerf�gbarException(packungsGroesse);
					}
				} else {
					k.getWarenkorb().artikelHinzufuegen(einArtikel, menge, einArtikel.getMenge());
				}
			} else { // gewollte Menge ist gr��er als die vorhandene Menge
				ArtikelMengeReichtNichtException e = new ArtikelMengeReichtNichtException(menge, einArtikel.getMenge());
				throw e;
			}
		}		
	}
	/**
	 * Methode um einen Artikel aus der Liste (warenkorb) zu l�schen.
	 * @param einArtikel der Artikel der rausgenommen werden soll.
	 */
	public void artikelAusWarenkorb(Artikel artikel, Kunde user){		
		user.getWarenkorb().artikelEntfernen(artikel);
	}
	/**
	 * Methode um die Menge eines Artikels im Warenkorb zu �ndern.
	 * @param nummer Artikelnummer des zu �ndernden Artikels.
	 * @param anzahl Wieviel hinzugef�gt werden soll.
	 */
	public void setArtikelMenge(Artikel a, int anzahl, Kunde user) {
		HashMap<Artikel, Integer> w = user.getWarenkorb().getInhalt();
		int alteMenge = w.remove(a);
		w.put(a, alteMenge+anzahl);
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
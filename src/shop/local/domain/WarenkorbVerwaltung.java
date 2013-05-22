package shop.local.domain;

import java.util.Iterator;

import shop.local.domain.exceptions.ArtikelMengeReichtNichtException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.User;

public class WarenkorbVerwaltung {	
	/**
	 * Methode um einen neuen Artikel in die Liste (warenkorb) einzufügen.
	 * @param einArtikel der Artikel der eingefügt werden soll.
	 * @throws ArtikelMengeReichtNichtException 
	 */
	public void artikelInWarenkorb(Artikel einArtikel, int menge, User user) throws ArtikelMengeReichtNichtException {
		
		// neuen Artikel erstellen, in Warenkorb tun 
		//Artikel einArtikel= new Artikel(artikel.getName(),artID, artikel.getPreis(), menge);
		//warkoVer.artikelInWarenkorb(einArtikel, akteur);
		// neue Menge setzen und Ereignis loggen
		//artikel.setMenge(artikel.getMenge()-menge);
		//erVer.ereignisEinfuegen(akteur, jahrestag, artikel, artikel.getMenge(), "Artikel in den Warenkorb gelegt.");
		 
			
		
		
		
		if (user instanceof Kunde) {
			Kunde k = (Kunde) user;
			if(menge<=einArtikel.getMenge()){
				k.getWarenkorb().artikelHinzufuegen(einArtikel, menge);
			} else { // gewollte Menge ist größer als die vorhandene Menge
				ArtikelMengeReichtNichtException e = new ArtikelMengeReichtNichtException(menge, einArtikel.getMenge());
				throw e;
			}
		}
		
//		boolean vorhanden=false;
//		Iterator<Artikel> it = user.getWarenkorb().iterator();
//		if(!it.hasNext()) {
//			user.getWarenkorb().add(einArtikel);
//		}
//		else{
//			while (it.hasNext()) {
//				Artikel artikel = it.next();
//				if(einArtikel.getNummer()==artikel.getNummer()){
//					artikel.setMenge(artikel.getMenge()+einArtikel.getMenge());
//					vorhanden= true;
//				}
//			}
//			if (!vorhanden) {
//				user.getWarenkorb().add(einArtikel);
//			}
//		}
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
	 */
	public void setArtikelMenge(int nummer, int anzahl, User user) {
		
//		Iterator<Artikel> it = getWarenkorb(user).iterator();
//		while  (it.hasNext()) {
//			Artikel artikel = it.next();
//			if(artikel.getNummer() == nummer){
//				artikel.setMenge(anzahl + artikel.getMenge());
//			} 
//		}
	}
}

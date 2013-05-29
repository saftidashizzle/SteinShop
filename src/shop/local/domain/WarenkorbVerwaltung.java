package shop.local.domain;

import java.util.HashMap;
import shop.local.domain.exceptions.ArtikelMengeReichtNichtException;
import shop.local.domain.exceptions.ArtikelNurInEinheitenVerfügbarException;
import shop.local.domain.exceptions.WarenkorbExceedsArtikelbestandException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.MehrfachArtikel;
import shop.local.valueobjects.User;

public class WarenkorbVerwaltung {	
	/**
	 * Methode um einen neuen Artikel in die Liste (warenkorb) einzufügen.
	 * @param einArtikel der Artikel der eingefügt werden soll.
	 * @throws ArtikelMengeReichtNichtException 
	 */
	public void artikelInWarenkorb(Artikel einArtikel, int menge, User user) throws ArtikelMengeReichtNichtException, WarenkorbExceedsArtikelbestandException, ArtikelNurInEinheitenVerfügbarException {		
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
						throw new ArtikelNurInEinheitenVerfügbarException(packungsGroesse);
					}
				} else {
					k.getWarenkorb().artikelHinzufuegen(einArtikel, menge, einArtikel.getMenge());
				}
			} else { // gewollte Menge ist größer als die vorhandene Menge
				ArtikelMengeReichtNichtException e = new ArtikelMengeReichtNichtException(menge, einArtikel.getMenge());
				throw e;
			}
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

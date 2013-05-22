package shop.local.domain;

import java.util.Iterator;
import java.util.List;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.User;

public class WarenkorbVerwaltung {	
	/**
	 * Methode um einen neuen Artikel in die Liste (warenkorb) einzuf�gen.
	 * @param einArtikel der Artikel der eingef�gt werden soll.
	 */
	public void artikelInWarenkorb(Artikel einArtikel, User user) {
		boolean vorhanden=false;
		Iterator<Artikel> it = user.getWarenkorb().iterator();
		if(!it.hasNext()) {
			user.getWarenkorb().add(einArtikel);
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
				user.getWarenkorb().add(einArtikel);
			}
		}
	}
	/**
	 * Methode um einen Artikel aus der Liste (warenkorb) zu l�schen.
	 * @param einArtikel der Artikel der rausgenommen werden soll.
	 */
	public void artikelAusWarenkorb(Artikel artikel, User user){		
		getWarenkorb(user).remove(artikel);
	}
	/**
	 * Methode um die Menge eines Artikels im Warenkorb zu �ndern.
	 * @param nummer Artikelnummer des zu �ndernden Artikels.
	 * @param anzahl Wieviel hinzugef�gt werden soll.
	 */
	public void setArtikelMenge(int nummer, int anzahl, User user) {
		
		Iterator<Artikel> it = getWarenkorb(user).iterator();
		while  (it.hasNext()) {
			Artikel artikel = it.next();
			if(artikel.getNummer() == nummer){
				artikel.setMenge(anzahl + artikel.getMenge());
			} 
		}
	}
	public void warenkorbLeeren(User user){
		getWarenkorb(user).clear();
	}

	/**
	 * Methode die die aktuelle Artikelliste zur�ckgibt
	 * @return die aktuelle Artikelliste.
	 */
	public List<Artikel> getWarenkorb(User user) {
		return user.getWarenkorb();
	}
}

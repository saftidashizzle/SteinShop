package shop.local.valueobjects;

import java.util.Iterator;
import java.util.List;

public class Rechnung { 
	private User kunde;
	private Warenkorb warenkorb;
	private int jahrestag;
	private double gesamtbetrag;
	
	public Rechnung(User kunde, Warenkorb warenkorb, int jahrestag) {
		this.kunde=kunde;
		this.warenkorb=warenkorb;
		this.jahrestag = jahrestag;
		System.out.println("Rechnung: \n" +
				"Kunde: " + kunde.getName() + 
				" | Datum: " + jahrestag);
			
//		Iterator<Artikel> it = warenkorb.iterator();
//		while (it.hasNext()) {
//			Artikel artikel = it.next();
//			gesamtbetrag= gesamtbetrag+artikel.getPreis() * artikel.getMenge();
//			System.out.println("Name: " + artikel.getName() + " | Einzelpreis: " + artikel.getPreis() + " | Anzahl: " + artikel.getMenge() );
//		}
//		System.out.println("Gesamtbetrag: " + gesamtbetrag + "\n");
	}
}
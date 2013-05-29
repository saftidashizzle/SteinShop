package shop.local.valueobjects;

import java.util.HashMap;

public class Rechnung { 
	private Kunde kunde;
	private Warenkorb warenkorb;
	private int jahrestag;
	
	public Rechnung(Kunde kunde, Warenkorb warenkorb, int jahrestag) {
		this.kunde=kunde;
		this.jahrestag = jahrestag;
		System.out.println("Rechnung: \n" +
				"Kunde: " + kunde.getName() + 
				" | Datum: " + jahrestag);
		this.warenkorb = warenkorb;
		HashMap<Artikel, Integer> wkorb = warenkorb.getInhalt();
		
		for (Artikel a : wkorb.keySet()) {
			System.out.println(a.getName() + " | Anzahl: " + wkorb.get(a) + " | Einzelpreis: " + a.getPreis() + " | Gesamtpreis: " + wkorb.get(a)*a.getPreis());
		}
	}
}
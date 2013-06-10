package shop.local.valueobjects;

import java.util.Date;
import java.util.HashMap;

public class Rechnung { 
	private Kunde kunde;
	private Warenkorb warenkorb;
	private Date datum;
	/**
	 * Konstruktor: erzeugt eine Rechnung, mit Kunde, Jahrestag/Datum und dem Warenkorb
	 * @param kunde
	 * @param warenkorb
	 * @param jahrestag
	 */
	public Rechnung(Kunde kunde, Warenkorb warenkorb, Date datum) {
		this.kunde=kunde;
		this.datum = datum;
		System.out.println("Rechnung: \n" +
				"Kunde: " + kunde.getName() + 
				" | Datum: " + datum);
		this.warenkorb = warenkorb;
		HashMap<Artikel, Integer> wkorb = warenkorb.getInhalt();
		double gesamtpreis = 0;
		for (Artikel a : wkorb.keySet()) {
			System.out.println(a.getName() + " | Anzahl: " + wkorb.get(a) + " | Einzelpreis: " + a.getPreis() + " | Gesamtpreis: " + wkorb.get(a)*a.getPreis());
			gesamtpreis = gesamtpreis + wkorb.get(a)*a.getPreis();			
		}
		System.out.println("Gesamtpreis: " + gesamtpreis);
	}
}
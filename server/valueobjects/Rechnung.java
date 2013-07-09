package valueobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Rechnung implements Serializable { 
	/**
	 * Klasse für eine Rechnung
	 */
	private static final long serialVersionUID = -4107167793509247770L;
	private Kunde kunde;
	private Warenkorb warenkorb;
	private Date datum;
	private double gesamtpreis;
	/**
	 * Konstruktor: erzeugt eine Rechnung, mit Kunde, Jahrestag/Datum und dem Warenkorb
	 * @param kunde der Kunde
	 * @param warenkorb der Warenkorb
	 * @param datum das Datum
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
		this.gesamtpreis = gesamtpreis;
	}
	public String getAnrede() {		
		return kunde.getAnrede();
	}
	public String getVorZuname() {		
		return kunde.getVorUndZuName();
	}
	public String getAdresse() {		
		return kunde.getAdresse();
	}
	public Date getDatum() {
		return this.datum;
	}
	public double getGesamtbetrag() {
		return this.gesamtpreis;
	}
	public Warenkorb getWarenkorb(){
		return this.warenkorb;
	}
}
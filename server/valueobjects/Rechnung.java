package valueobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Rechnung implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4107167793509247770L;
	private Kunde kunde;
	private Warenkorb warenkorb;
	private Date datum;
	private double gesamtpreis;
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
		this.gesamtpreis = gesamtpreis;
	}
	/**
	 * Methode, die die Anrede des Kunden zurückgibt
	 * @return zurückgegebene Anrede
	 */
	public String getAnrede() {		
		return kunde.getAnrede();
	}
	/**
	 * Methode, die den Vor und Zunamen des Kunden zurückgibt
	 * @return zurückgegebenen Vor und Zunamen
	 */
	public String getVorZuname() {		
		return kunde.getVorUndZuName();
	}
	/**
	 * Methode, die die Adressdaten des Kunden zurückgibt
	 * @return zurückgegebene Adressdaten
	 */
	public String getAdresse() {		
		return kunde.getAdresse();
	}
	/**
	 * Methode, die das Datum zurückgibt
	 * @return zurückgegebenes Datum
	 */
	public Date getDatum() {
		return this.datum;
	}
	/**
	 * Methode, die den Gesamtbetrag zurückgibt
	 * @return zurückgegebenen Gesamtbetrag
	 */
	public double getGesamtbetrag() {
		return this.gesamtpreis;
	} 
	/**
	 * Methode, die den Warenkorb zurückgibt
	 * @return zurückgegebenen Warenkorb
	 */
	public Warenkorb getWarenkorb(){
		return this.warenkorb;
	}
}
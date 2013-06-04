
package shop.local.valueobjects;

import java.io.Serializable;

public class Artikel implements Serializable {
	private String titel;
	private int nummer;
	private double preis;
	private int menge;
	
	/**
	 * Konstruktor: wenn keine Menge angegeben wird, ist per default die Menge 1
	 * @param name der Name es Artikels, der erzeugt werden soll
	 * @param nr die Artikelnummer des Artikels, der erzeugt werden soll
	 */
	
	public Artikel(String name, int nr, double preis) {
		this(name, nr, preis, 1);
	}
	
	/**
	 * Konstruktor: erzeugt einen Artikel mit Name, Nr und Menge
	 * @param name der Name des zu erzeugenden Artikels
	 * @param nr die Nr des zu erzeugenden Artikels
	 * @param meng die Menge des zu erzeugenden Artikels
	 */
	public Artikel(String name, int nr, double prei, int meng) {
		this.titel = name;
		this.nummer = nr;
		this.preis = prei;
		this.menge = meng;
	}
	/**
	 * Methode die einen String in der Form: "Nr: * | Titel: * | Anzahl: * "zurückgibt. * ist ein Platzhalter für die jeweilige Variable.
	 */
	public String toString() {
		return ("Nr: " + nummer + " | Titel: " + titel + " | Anzahl: " + menge + " | Einzelpreis: " + preis);
		
	}
	
	/**
	 * Methode die den Namen des Artikel Objektes zurück gibt.
	 * @return der zurückgegebene Name.
	 */
	public String getName(){
		return titel;
	}
	/** 
	 * Methode die die Nummer des Artikelobjektes zurück gibt.
	 * @return die zurückgegebene Nummer.
	 */
	public int getNummer() {
		return nummer;
	}
	/**
	 * Methode die die Menge des Artikelobjektes zurück gibt.
	 * @return Die zurückgegebene Menge.
	 */
	public int getMenge(){
		return menge;
	}
	/**
	 * Methode die die Menge des Artikelobjektes setzt.
	 * @return Die zurückgegebene Menge.
	 */
	public void setMenge(int zahl){
		menge = zahl;
	}
	/**
	 * Methode die den Preis des Artikelobjektes zurück gibt.
	 * @return Die Preis (float)
	 */
	public double getPreis(){
		return preis;
	}
}
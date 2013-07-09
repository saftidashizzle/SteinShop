package valueobjects;

public class MehrfachArtikel extends Artikel {
	
	/**
	 * Klasse f�r einen Mehrfachartikel, erbt von Artikel
	 */
	private static final long serialVersionUID = 3694103134172879212L;
	private int packungsGroesse;
	/**
	 * Konstruktor: erzeugt einen Mehrfachartikel mit angabe der PAckungsgroesse
	 * @param name geerbt von der Klasse Artikel
	 * @param nr geerbt von der Klasse Artikel
	 * @param preis geerbt von der Klasse Artikel
	 * @param menge geerbt von der Klasse Artikel
	 * @param packungsGroesse
	 */
	public MehrfachArtikel(String name, int nr, double preis, int menge, int packungsGroesse) {
		super(name, nr, preis, menge);
		this.packungsGroesse = packungsGroesse;
	}
	/**
	 * Methode die einen String mit der Packungsgroesse zur�ck gibt
	 */
	public String toString() {
		return (super.toString() + " Packungsgroesse: " + packungsGroesse);
	}
	/**
	 * Methode, die die Packungsgroesse zur�ck gibt
	 * @return die zur�ckgegebene Packungsgroesse
	 */
	public int getPackungsgroesse( ) {
		return packungsGroesse;
	}

}

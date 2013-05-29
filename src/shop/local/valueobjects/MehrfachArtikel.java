package shop.local.valueobjects;

public class MehrfachArtikel extends Artikel {
	
	private int packungsGroesse;

	public MehrfachArtikel(String name, int nr, double preis, int menge, int packungsGroesse) {
		super(name, nr, preis, menge);
		this.packungsGroesse = packungsGroesse;
	}
	public String toString() {
		return (super.toString() + " Packungsgroesse: " + packungsGroesse);
	}
	public int getPackungsgroesse( ) {
		return packungsGroesse;
	}

}

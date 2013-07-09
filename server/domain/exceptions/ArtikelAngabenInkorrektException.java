package domain.exceptions;

public class ArtikelAngabenInkorrektException extends Exception {
	/**
	 * Exception, die sagt, dass die angegebenen Daten für einen neuen Artikel inkorrekt waren
	 */
	private static final long serialVersionUID = 1L;

	public ArtikelAngabenInkorrektException(String name, double preis, int menge) {
		super("Die Preis- oder Mengenangabe ist inkorrekt. (Name: " + name + ", Preis: " + preis + " , Menge: " + menge + ")");
	}
}

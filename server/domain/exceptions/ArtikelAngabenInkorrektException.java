package domain.exceptions;
/**
 * Exception, die sagt, dass die angegebenen Daten für einen neuen Artikel inkorrekt waren
 */
public class ArtikelAngabenInkorrektException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ArtikelAngabenInkorrektException(String name, double preis, int menge) {
		super("Die Preis- oder Mengenangabe ist inkorrekt. (Name: " + name + ", Preis: " + preis + " , Menge: " + menge + ")");
	}
}

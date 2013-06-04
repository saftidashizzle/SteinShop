package shop.local.domain.exceptions;
/**
 * Exception, ??
 */
public class ArtikelMengeInkorrektException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ArtikelMengeInkorrektException() {
		super("Ihre angegebene Menge ist nicht durchführbar");
	}
}

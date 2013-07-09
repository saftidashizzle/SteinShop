package domain.exceptions;
/**
 * Exception, die sagt, dass die eingegebene Menge nicht durchf�hrbar ist
 */
public class ArtikelMengeInkorrektException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ArtikelMengeInkorrektException(int zahl) {
		super("Ihre angegebene Menge(" + zahl + ") ist nicht durchf�hrbar.");
	}
}

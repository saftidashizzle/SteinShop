package domain.exceptions;

public class ArtikelMengeInkorrektException extends Exception {
	/**
	* Exception, die sagt, dass die eingegebene Menge nicht durchf�hrbar ist
	*/
	private static final long serialVersionUID = 1L;

	public ArtikelMengeInkorrektException(int zahl) {
		super("Ihre angegebene Menge(" + zahl + ") ist nicht durchf�hrbar.");
	}
}

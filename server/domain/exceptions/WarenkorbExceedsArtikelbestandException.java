package domain.exceptions;

public class WarenkorbExceedsArtikelbestandException extends Exception {
	/**
	 * Exception, die sagt, dass der Artikelbestand nicht ausreicht um den Einkauf
	 * abschließen zu können
	 */
	private static final long serialVersionUID = 1L;

	public WarenkorbExceedsArtikelbestandException() {
		super("Artikelbestand reicht nicht.");
	}
}

package shop.local.domain.exceptions;
/**
 * Exception, die sagt, dass der Artikelbestand nicht ausreicht um den Einkauf
 * abschließen zu können
 */
public class WarenkorbExceedsArtikelbestandException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public WarenkorbExceedsArtikelbestandException() {
		super("Artikelbestand reicht nicht.");
	}
}

package shop.local.domain.exceptions;

public class WarenkorbExceedsArtikelbestandException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public WarenkorbExceedsArtikelbestandException() {
		super("Artikelbestand reicht nicht.");
	}
}

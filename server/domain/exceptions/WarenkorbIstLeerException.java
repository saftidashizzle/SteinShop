package domain.exceptions;

public class WarenkorbIstLeerException extends Exception {
	/**
	 * Exception, die sagt, dass der angeforderte Warenkorb leer ist
	 */
	private static final long serialVersionUID = 1L;

	public WarenkorbIstLeerException() {
		super("Im Warenkorb sind keine Artikel enthalten.");
	}
}

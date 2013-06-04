package shop.local.domain.exceptions;
/**
 * Exception, die sagt, dass der angeforderte Warenkorb leer ist
 */
public class WarenkorbIstLeerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public WarenkorbIstLeerException() {
		super("Im Warenkorb sind keine Artikel enthalten");
	}
}

package shop.local.domain.exceptions;
/**
 * Exception, die sagt, dass die angegebenen Daten für den Login fehlschlugen
 */
public class InkorrekteLoginWerteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InkorrekteLoginWerteException() {
		super("Loginwerte waren inkorrekt");
	}
}

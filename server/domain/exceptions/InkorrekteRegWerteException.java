package domain.exceptions;
/**
 * Exception, die sagt, dass die angegebenen Daten für den Login fehlschlugen
 */
public class InkorrekteRegWerteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InkorrekteRegWerteException() {
		super("Loginwerte waren inkorrekt");
	}
}

package domain.exceptions;
/**
 * Exception, die sagt, dass die angegebenen Daten für den Login fehlschlugen
 */
public class InkorrekteRegWerteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InkorrekteRegWerteException() {
	
	super("Falsche Werte bei der Registrierung. Wahrscheinlich ist die PLZ ungültig.");

	}
}